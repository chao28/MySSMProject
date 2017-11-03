/**
 * 
 * 开发人员：Young
 * 开发时间：2017-10-26 下午2:15:05
 */
package cn.appinfo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.appinfo.pojo.AppCategory;
import cn.appinfo.pojo.AppInfo;
import cn.appinfo.pojo.AppVersion;
import cn.appinfo.pojo.DataDictionary;
import cn.appinfo.service.AppCategoryService;
import cn.appinfo.service.BackendUserService;
import cn.appinfo.service.DataDictionaryService;
import cn.appinfo.util.PageUtil;

/**
 * TODO（APP信息管理系统————（后台管理控制器））
 * 开发人员：Young
 * 开发时间：2017-10-26 下午2:15:05 
 */
@Controller
@RequestMapping(value = "/backendUser")
public class BackendController {

	@Autowired
	private BackendUserService backendUserService;
	public void setBackendUserService(BackendUserService backendUserService) {
		this.backendUserService = backendUserService;
	}

	@Autowired
	private DataDictionaryService dataDictionaryService;
	public void setDataDictionaryService(DataDictionaryService dataDictionaryService) {
		this.dataDictionaryService = dataDictionaryService;
	}

	@Autowired
	private AppCategoryService appCategoryService;
	public void setAppCategoryService(AppCategoryService appCategoryService) {
		this.appCategoryService = appCategoryService;
	}

	// TODO(APP信息管理系统------跳转到主页面main)
	@RequestMapping(value = "/main")
	public String toMain(){
		
		return "backend/main";
	}
	
	/**
	 * TODO(APP信息管理系统------后台管理--动态查询+分页)
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-27 下午5:31:30
	 * @param model
	 * @param session
	 * @param querySoftwareName App名称
	 * @param queryCategoryLevel1Str 一级分类
	 * @param queryCategoryLevel2Str 二级分类
	 * @param queryCategoryLevel3Str 三级分类
	 * @param queryFlatformIdStr App平台ID
	 * @param pageIndexStr 当前页码
	 * @return
	 */
	@RequestMapping(value = "/backendAppList")
	public String toAppInfoList(Model model, HttpSession session, 
			@RequestParam(value = "querySoftwareName", required = false)String querySoftwareName, 
			@RequestParam(value = "queryCategoryLevel1", required = false)String queryCategoryLevel1Str,
			@RequestParam(value = "queryCategoryLevel2", required = false)String queryCategoryLevel2Str,
			@RequestParam(value = "queryCategoryLevel3", required = false)String queryCategoryLevel3Str,
			@RequestParam(value = "queryFlatformId", required = false)String queryFlatformIdStr,
			@RequestParam(value = "pageIndex", required = false)String pageIndexStr, 
			HttpServletRequest request){

		//创建map集合用来存储数据
		Map<String, Object> appMap = new HashMap<String, Object>();

		//判断页码是否为空，如果为空则给他赋值为1,并将String类型转换为Integer类型
		if (pageIndexStr == null || "".equals(pageIndexStr)){
			pageIndexStr = "1";
		}
		Integer pageIndex = Integer.parseInt(pageIndexStr);

		//判断1、2、3级分类是否为空，不为空时，将String类型转换为Integer类型
		Integer queryCategoryLevel1 = null;
		if (queryCategoryLevel1Str != null && !queryCategoryLevel1Str.equals("")){
			queryCategoryLevel1 = Integer.parseInt(queryCategoryLevel1Str);
		}
		Integer queryCategoryLevel2 = null;
		if (queryCategoryLevel2Str != null && !queryCategoryLevel2Str.equals("")){
			queryCategoryLevel2 = Integer.parseInt(queryCategoryLevel2Str);
		}
		Integer queryCategoryLevel3 = null;
		if (queryCategoryLevel3Str != null && !queryCategoryLevel3Str.equals("")){
			queryCategoryLevel3 = Integer.parseInt(queryCategoryLevel3Str);
		}

		//判断所属平台是否为空，不为空时，将String类型转换为Integer类型
		Integer queryFlatformId = null;
		if (queryFlatformIdStr != null && !queryFlatformIdStr.equals("")){
			queryFlatformId = Integer.parseInt(queryFlatformIdStr);
		}

		//向集合中添加数据
		//判断APP名称是否为空 不为空时添加到集合当中
		if (querySoftwareName != null && !querySoftwareName.equals("")){
			appMap.put("softwareName", querySoftwareName);
		}
		appMap.put("categoryLevel1", queryCategoryLevel1);
		appMap.put("categoryLevel2", queryCategoryLevel2);
		appMap.put("categoryLevel3", queryCategoryLevel3);
		appMap.put("flatformId", queryFlatformId);
		appMap.put("status", 1);

		//添加分页信息
		appMap.put("index", (pageIndex - 1) * PageUtil.SIZE);
		appMap.put("size", PageUtil.SIZE);

		//调用service层方法，进行查询，返回分页信息
		PageUtil appInfoPage = backendUserService.getAppInfoList(appMap); //信息展示
		List<DataDictionary> flatFormList = this.getDataDictionary("APP_FLATFORM"); //所属平台
		List<AppCategory> categoryLevel1List = appCategoryService.getAppCategoryListById(null); //1级分类列表
		List<AppCategory> categoryLevel2List = null; //2级分类列表
		List<AppCategory> categoryLevel3List = null; //3级分类列表

		//给工具类存放当前页码
		appInfoPage.setIndex(pageIndex);

		//返回结果
		request.setAttribute("appInfoPage", appInfoPage); //分页信息 
		model.addAttribute("appInfoPage", appInfoPage);
		model.addAttribute("flatFormList", flatFormList);
		model.addAttribute("categoryLevel1List", categoryLevel1List);

		//参数回执
		model.addAttribute("queryCategoryLevel1", queryCategoryLevel1);
		model.addAttribute("queryCategoryLevel2", queryCategoryLevel2);
		model.addAttribute("queryCategoryLevel3", queryCategoryLevel3);
		model.addAttribute("queryFlatformId", queryFlatformId);
		model.addAttribute("querySoftwareName", querySoftwareName);
		
		//级联列表回执
		if(queryCategoryLevel2 != null && !queryCategoryLevel2.equals("")){
			categoryLevel2List = getCategoryList(queryCategoryLevel1.toString());
			model.addAttribute("categoryLevel2List", categoryLevel2List);
		}
		if(queryCategoryLevel3 != null && !queryCategoryLevel3.equals("")){
			categoryLevel3List = getCategoryList(queryCategoryLevel2.toString());
			model.addAttribute("categoryLevel3List", categoryLevel3List);
		}

		return "backend/applist";
	}

	/**
	 * TODO(APP信息管理系统------根据类型编码获取所属平台类型)
	 * 一级分类
	 * 开发人员：Young
	 * 开发时间：2017-10-26 下午5:33:01
	 * @param typeCode 数据类型编码
	 * @return 数据类型集合
	 */
	public List<DataDictionary> getDataDictionary(String typeCode){

		List<DataDictionary> dataDictionaryList = dataDictionaryService.getDataDictionaryList(typeCode);

		return dataDictionaryList;

	}
	
	/**
	 * TODO(APP信息管理系统------根据ID获取级别列表)
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-28 下午3:15:34
	 * @param pid 
	 * @return
	 */
	public List<AppCategory> getCategoryList(String pid){
		
		List<AppCategory> appCategoryList = 
				appCategoryService.getAppCategoryListById(pid == null ? null : Integer.parseInt(pid));
		
		return appCategoryList;
	}
	
	/**
	 * TODO(APP信息管理系统------级别联动)
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-28 下午3:17:54
	 * @param pid
	 * @return
	 */
	@RequestMapping(value = "categorylevellist.json", method = RequestMethod.GET)
	@ResponseBody
	public List<AppCategory> getCategoryLevel2ListByLevel1(@RequestParam("pid")String pid){
		
		if (pid.equals("")){
			pid = null;
		}
		
		return getCategoryList(pid);
	}
	
	/**
	 * TODO(APP信息管理系统------跳转到审核页面)
	 * 根据id获取APP信息和APP版本信息
	 * 开发人员：Young
	 * 开发时间：2017-10-28 下午3:48:42
	 * @param aid
	 * @param vid
	 * @return
	 */
	@RequestMapping(value = "/check/{aid}/{vid}")
	public String toAppCheck(@PathVariable Integer aid, @PathVariable Integer vid , Model model){
		
		//APP信息
		AppInfo appInfo = backendUserService.getAppInfo(aid, null);
		
		//APP版本
		AppVersion appVersion = backendUserService.getAppVersionById(vid);
		
		model.addAttribute("appInfo", appInfo);
		model.addAttribute("appVersion", appVersion);
		
		return "backend/appcheck";
	}

	/**
	 * TODO(APP信息管理系统------APP审核)
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-28 下午5:20:45
	 * @param appInfo
	 * @return
	 */
	@RequestMapping(value = "checksave", method = RequestMethod.POST)
	public String checkSave(AppInfo appInfo){
		
		Integer result = backendUserService.updateAppStatus(appInfo.getStatus(), appInfo.getId());
		
		if (result == 1){
			return "backend/applist";
		}
		
		return "backend/appcheck";
	}
	
}
