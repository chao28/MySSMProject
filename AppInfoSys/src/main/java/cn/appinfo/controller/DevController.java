/**
 * 
 * 开发人员：Young
 * 开发时间：2017-10-30 上午8:54:55
 */
package cn.appinfo.controller;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.mysql.jdbc.StringUtils;

import cn.appinfo.pojo.AppCategory;
import cn.appinfo.pojo.AppInfo;
import cn.appinfo.pojo.AppVersion;
import cn.appinfo.pojo.DataDictionary;
import cn.appinfo.pojo.DevUser;
import cn.appinfo.service.AppCategoryService;
import cn.appinfo.service.BackendUserService;
import cn.appinfo.service.DataDictionaryService;
import cn.appinfo.service.DevUserService;
import cn.appinfo.util.Constants;
import cn.appinfo.util.PageUtil;

/**
 * TODO(APP信息管理系统------开发者平台--控制器类)
 * 开发人员：Young
 * 开发时间：2017-10-30 上午8:54:55
 */
@Controller
@RequestMapping(value = "/devUser")
public class DevController {

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

	@Autowired
	private DevUserService devUserService;
	public void setDevUserService(DevUserService devUserService) {
		this.devUserService = devUserService;
	}

	//TODO(APP信息管理系统------跳转到主页面)
	@RequestMapping(value = "/main")
	public String toMain(){

		return "developer/main";
	}

	//TODO(APP信息管理系统------跳转到App信息添加页面)
	@RequestMapping(value = "/toAppInfoAdd")
	public String toAppInfoAdd(@ModelAttribute("appInfo")AppInfo appInfo){

		return "developer/appinfoadd";
	}


	/**
	 * TODO(APP信息管理系统------动态查询+分页)
	 * 查询App信息，并显示信息列表
	 * 开发人员：Young
	 * 开发时间：2017-10-30 上午9:09:54
	 * @param model
	 * @param session
	 * @param querySoftwareName
	 * @param queryStatusStr
	 * @param queryCategoryLevel1Str
	 * @param queryCategoryLevel2Str
	 * @param queryCategoryLevel3Str
	 * @param queryFlatformIdStr
	 * @param pageIndexStr
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/appInfoList")
	public String appInfoList(Model model, HttpSession session, 
			@RequestParam(value = "querySoftwareName", required = false)String querySoftwareName, 
			@RequestParam(value = "queryStatus", required = false)String queryStatusStr, 
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

		//判断App状态是否为空，不为空时，将String类型转换为Integer类型
		Integer queryStatus = null;
		if (queryStatusStr != null && !queryStatusStr.equals("")){
			queryStatus = Integer.parseInt(queryStatusStr);
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
		appMap.put("status", queryStatus);

		//添加分页信息
		appMap.put("index", (pageIndex - 1) * PageUtil.SIZE);
		appMap.put("size", PageUtil.SIZE);

		//调用service层方法，进行查询，返回分页信息
		PageUtil appInfoPage = backendUserService.getAppInfoList(appMap); //信息展示
		List<DataDictionary> flatFormList = this.getDataDictionary("APP_FLATFORM"); //所属平台
		List<DataDictionary> statusList = this.getDataDictionary("APP_STATUS"); //App状态
		List<AppCategory> categoryLevel1List = appCategoryService.getAppCategoryListById(null); //1级分类
		List<AppCategory> categoryLevel2List = null; //2级分类
		List<AppCategory> categoryLevel3List = null; //3级分类

		//给工具类存放当前页码
		appInfoPage.setIndex(pageIndex);

		//返回结果
		request.setAttribute("appInfoPage", appInfoPage);
		model.addAttribute("appInfoPage", appInfoPage);
		model.addAttribute("flatFormList", flatFormList);
		model.addAttribute("statusList", statusList);
		model.addAttribute("categoryLevel1List", categoryLevel1List);

		//参数回执
		model.addAttribute("queryCategoryLevel1", queryCategoryLevel1);
		model.addAttribute("queryCategoryLevel2", queryCategoryLevel2);
		model.addAttribute("queryCategoryLevel3", queryCategoryLevel3);
		model.addAttribute("queryFlatformId", queryFlatformId);
		model.addAttribute("querySoftwareName", querySoftwareName);

		if(queryCategoryLevel2 != null && !queryCategoryLevel2.equals("")){
			categoryLevel2List = getCategoryList(queryCategoryLevel1.toString());
			model.addAttribute("categoryLevel2List", categoryLevel2List);
		}
		if(queryCategoryLevel3 != null && !queryCategoryLevel3.equals("")){
			categoryLevel3List = getCategoryList(queryCategoryLevel2.toString());
			model.addAttribute("categoryLevel3List", categoryLevel3List);
		}
		
		return "developer/appinfolist";
	}

	/**
	 * TODO(APP信息管理系统------根据类型编码获取所属相应数据字典)
	 * 一级分类
	 * 开发人员：Young
	 * 开发时间：2017-10-26 下午5:33:01
	 * @param typeCode
	 * @return
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
	 * TODO(APP信息管理系统------平台列表)
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-30 上午9:50:56
	 * @param tcode
	 * @return
	 */
	@RequestMapping(value = "datadictionarylist.json")
	@ResponseBody
	public List<DataDictionary> datadictionarylist(@RequestParam("tcode")String tcode){

		if (tcode.equals("")){
			tcode = null;
		}

		return getDataDictionary(tcode);
	}

	/**
	 * TODO(APP信息管理系统------判断APKName名称是否存在)
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-30 上午9:52:59
	 * @param APKName
	 * @return
	 */
	@RequestMapping(value = "apkexist.json")
	@ResponseBody
	public Object apkexist(@RequestParam("APKName")String APKName){

		Map<String, String> jsonMap = new HashMap<String, String>();

		AppInfo appinfo = null;

		if (APKName == null || APKName.equals("")){
			jsonMap.put("APKName", "empty");
		} else {
			appinfo = backendUserService.getAppInfo(null, APKName);
			if (appinfo != null){
				jsonMap.put("APKName", "exist");
			} else {
				jsonMap.put("APKName", "noexist");
			}
		}
		return JSONArray.toJSON(jsonMap);
	}

	/**
	 * TODO(APP信息管理系统------添加APP信息)
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-30 上午10:11:38
	 * @param appinfo
	 * @param session
	 * @param request
	 * @param attach
	 * @return
	 */
	@RequestMapping(value = "/appinfoaddsave", method = RequestMethod.POST)
	public String addAppInfo(AppInfo appInfo, HttpSession session, HttpServletRequest request, 
			@RequestParam(value="a_logoPicPath",required= false)MultipartFile attach){

		String logoPicPath =  null;
		String logoLocPath =  null;
		if(!attach.isEmpty()){
			String path = request.getSession().getServletContext().getRealPath("statics"+java.io.File.separator+"uploadfiles");
			String oldFileName = attach.getOriginalFilename();//原文件名
			String prefix = FilenameUtils.getExtension(oldFileName);//原文件后缀
			int filesize = 500000;
			if(attach.getSize() > filesize){//上传大小不得超过 50k
				request.setAttribute("fileUploadError", Constants.FILEUPLOAD_ERROR_4);
				return "developer/appinfoadd";
			}else if(prefix.equalsIgnoreCase("jpg") || prefix.equalsIgnoreCase("png") 
					||prefix.equalsIgnoreCase("jepg") || prefix.equalsIgnoreCase("pneg")){//上传图片格式
				String fileName = appInfo.getAPKName() + ".jpg";//上传LOGO图片命名:apk名称.apk
				File targetFile = new File(path,fileName);
				if(!targetFile.exists()){
					targetFile.mkdirs();
				}
				try {
					attach.transferTo(targetFile);
				} catch (Exception e) {
					e.printStackTrace();
					request.setAttribute("fileUploadError", Constants.FILEUPLOAD_ERROR_2);
					return "developer/appinfoadd";
				} 
				logoPicPath = request.getContextPath()+"/statics/uploadfiles/"+fileName;
				logoLocPath = path+File.separator+fileName;
			}else{
				request.setAttribute("fileUploadError", Constants.FILEUPLOAD_ERROR_3);
				return "developer/appinfoadd";
			}
		}
		appInfo.setCreatedBy(((DevUser)session.getAttribute(Constants.DEV_USER_SESSION)).getId());
		appInfo.setCreationDate(new Date());
		appInfo.setLogoPicPath(logoPicPath);
		appInfo.setLogoLocPath(logoLocPath);
		appInfo.setDevId(((DevUser)session.getAttribute(Constants.DEV_USER_SESSION)).getId());
		appInfo.setStatus(1);

		Integer result = devUserService.addAppInfo(appInfo);

		if (result > 0){
			return "developer/appinfolist";
		}

		return "developer/appinfoadd";
	}

	/**
	 * TODO(APP信息管理系统------获取APP旧版本信息--跳转到APP版本新增页面)
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-30 下午3:11:11
	 * @param appinfoid
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "appversionadd")
	public String appversionadd(@RequestParam(value = "appinfoid", required = false)Integer appinfoid, 
			@RequestParam(value="error",required= false)String fileUploadError,
			AppVersion appVersion, Model model){

		if(null != fileUploadError && fileUploadError.equals("error1")){
			fileUploadError = Constants.FILEUPLOAD_ERROR_1;
		}else if(null != fileUploadError && fileUploadError.equals("error2")){
			fileUploadError	= Constants.FILEUPLOAD_ERROR_2;
		}else if(null != fileUploadError && fileUploadError.equals("error3")){
			fileUploadError = Constants.FILEUPLOAD_ERROR_3;
		}

		List<AppVersion> appVersionList = devUserService.getAppVersion(appinfoid);

		appVersion.setAppId(appinfoid);

		appVersion.setAppName((backendUserService.getAppInfo(appinfoid, null)).getSoftwareName());

		model.addAttribute("appVersion", appVersion);
		model.addAttribute("appVersionList", appVersionList);
		model.addAttribute("fileUploadError",fileUploadError);

		return "developer/appversionadd";
	}

	/**
	 * TODO(APP信息管理系统------新增APP版本信息)
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-30 下午3:15:07
	 * @param appVersion
	 * @param session
	 * @param request
	 * @param attach
	 * @return
	 */
	@RequestMapping(value = "/addversionsave", method = RequestMethod.POST)
	public String addversionsave(AppVersion appVersion, HttpSession session,HttpServletRequest request,
			@RequestParam(value="a_downloadLink",required= false) MultipartFile attach){

		String downloadLink =  null;
		String apkLocPath = null;
		String apkFileName = null;
		if(!attach.isEmpty()){
			String path = request.getSession().getServletContext().getRealPath("statics"+File.separator+"uploadfiles");
			String oldFileName = attach.getOriginalFilename();//原文件名
			String prefix = FilenameUtils.getExtension(oldFileName);//原文件后缀
			if(prefix.equalsIgnoreCase("apk")){//apk文件命名：apk名称+版本号+.apk
				String apkName = null;
				try {
					apkName = backendUserService.getAppInfo(appVersion.getAppId(), null).getAPKName();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(apkName == null || "".equals(apkName)){
					return "redirect:/devUser/appversionadd?appinfoid="+appVersion.getAppId()
							+"&error=error1";
				}
				apkFileName = apkName + "-" +appVersion.getVersionNo() + ".apk";
				File targetFile = new File(path,apkFileName);
				if(!targetFile.exists()){
					targetFile.mkdirs();
				}
				try {
					attach.transferTo(targetFile);
				} catch (Exception e) {
					e.printStackTrace();
					return "redirect:/devUser/appversionadd?appinfoid="+appVersion.getAppId()
							+"&error=error2";
				} 
				downloadLink = request.getContextPath()+"/statics/uploadfiles/"+apkFileName;
				apkLocPath = path+File.separator+apkFileName;
			}else{
				return "redirect:/devUser/appversionadd?appinfoid="+appVersion.getAppId()
						+"&error=error3";
			}
		}
		appVersion.setCreatedBy(((DevUser)session.getAttribute(Constants.DEV_USER_SESSION)).getId()); //创建人
		appVersion.setCreationDate(new Date()); //创建时间
		appVersion.setDownloadLink(downloadLink);
		appVersion.setApkLocPath(apkLocPath);
		appVersion.setApkFileName(apkFileName);

		boolean result = devUserService.saveAppVersion(appVersion);

		if (result == true){
			return "developer/appinfolist";
		}

		return "redirect:/devUser/appversionadd/"+appVersion.getAppId();
	}

	/**
	 * TODO(APP信息管理系统------跳转到修改版本信息页面)
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-30 下午5:27:41
	 * @param model
	 * @param versionid
	 * @param appinfoid
	 * @return
	 */
	@RequestMapping(value = "/appversionmodify")
	public String modifyversion(Model model, 
			@RequestParam(value = "versionid", required = false)Integer versionid, 
			@RequestParam(value = "appinfoid", required = false)Integer appinfoid, 
			@RequestParam(value="error",required= false)String fileUploadError){


		if(null != fileUploadError && fileUploadError.equals("error1")){
			fileUploadError = Constants.FILEUPLOAD_ERROR_1;
		}else if(null != fileUploadError && fileUploadError.equals("error2")){
			fileUploadError	= Constants.FILEUPLOAD_ERROR_2;
		}else if(null != fileUploadError && fileUploadError.equals("error3")){
			fileUploadError = Constants.FILEUPLOAD_ERROR_3;
		}

		AppVersion appVersion = devUserService.getAppVersionById(versionid);
		List<AppVersion> appVersionList = devUserService.getAppVersion(appinfoid);


		model.addAttribute("appVersionList", appVersionList);
		model.addAttribute(appVersion);
		model.addAttribute("fileUploadError", fileUploadError);

		return "developer/appversionmodify";
	}

	/**
	 * TODO(APP信息管理系统------修改版本信息后保存)
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-31 上午8:46:09
	 * @param appVersion
	 * @param session
	 * @param request
	 * @param attach
	 * @return
	 */
	@RequestMapping(value = "/appversionmodifysave", method = RequestMethod.POST)
	public String appversionmodifysave(AppVersion appVersion,HttpSession session,HttpServletRequest request,
			@RequestParam(value="attach",required= false) MultipartFile attach){

		String downloadLink =  null;
		String apkLocPath = null;
		String apkFileName = null;
		if(!attach.isEmpty()){
			String path = request.getSession().getServletContext().getRealPath("statics"+File.separator+"uploadfiles");
			String oldFileName = attach.getOriginalFilename();//原文件名
			String prefix = FilenameUtils.getExtension(oldFileName);//原文件后缀
			if(prefix.equalsIgnoreCase("apk")){//apk文件命名：apk名称+版本号+.apk
				String apkName = null;
				try {
					apkName = backendUserService.getAppInfo(appVersion.getAppId(), null).getAPKName();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				if(apkName == null || "".equals(apkName)){
					return "redirect:/devUser/appversionmodify?versionid="+appVersion.getId()
							+"&appinfoid="+appVersion.getAppId()
							+"&error=error1";
				}
				apkFileName = apkName + "-" +appVersion.getVersionNo() + ".apk";
				File targetFile = new File(path,apkFileName);
				if(!targetFile.exists()){
					targetFile.mkdirs();
				}
				try {
					attach.transferTo(targetFile);
				} catch (Exception e) {
					e.printStackTrace();
					return "redirect:/devUser/appversionmodify?versionid="+appVersion.getId()
							+"&appinfoid="+appVersion.getAppId()
							+"&error=error2";
				} 
				downloadLink = request.getContextPath()+"/statics/uploadfiles/"+apkFileName;
				apkLocPath = path+File.separator+apkFileName;
			}else{
				return "redirect:/devUser/appversionmodify?versionid="+appVersion.getId()
						+"&appinfoid="+appVersion.getAppId()
						+"&error=error3";
			}
		}
		appVersion.setModifyBy(((DevUser)session.getAttribute(Constants.DEV_USER_SESSION)).getId());
		appVersion.setModifyDate(new Date());
		appVersion.setDownloadLink(downloadLink);
		appVersion.setApkLocPath(apkLocPath);
		appVersion.setApkFileName(apkFileName);

		Integer result = devUserService.updateAppVersion(appVersion);

		if (result > 0){
			return "developer/appinfolist";
		}

		return "developer/appversionmodify";
	}

	/**
	 * TODO(APP信息管理系统------查看app信息)
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-31 上午10:07:09
	 * @param model
	 * @param appinfoid
	 * @return
	 */
	@RequestMapping(value = "appview/{appinfoid}")
	public String appview(Model model, @PathVariable Integer appinfoid){

		AppInfo appInfo = backendUserService.getAppInfo(appinfoid, null);

		List<AppVersion> appVersionList = devUserService.getAppVersion(appinfoid);

		model.addAttribute(appInfo);
		model.addAttribute("appVersionList", appVersionList);

		return "developer/appinfoview";
	}

	/**
	 * TODO(APP信息管理系统------删除app信息)
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-31 下午3:19:59
	 * @param appinfoid
	 * @return
	 */
	@RequestMapping(value = "/delapp.json", method = RequestMethod.GET)
	@ResponseBody
	public String deleApp(@RequestParam("id")String appinfoid){

		HashMap<String, String> resultMap = new HashMap<String, String>();
		if(StringUtils.isNullOrEmpty(appinfoid)){
			resultMap.put("delResult", "notexist");
		}else{
			try {
				if(devUserService.appsysdeleteAppById(Integer.parseInt(appinfoid))) {				
					resultMap.put("delResult", "true");
				} else {
					resultMap.put("delResult", "false");
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return JSONArray.toJSONString(resultMap);
	}

	/**
	 * TODO(APP信息管理系统------跳转到app修改页面)
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-31 下午4:16:04
	 * @param id
	 * @param fileUploadError
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/appinfomodify")
	public String toModiryAppInfo(@RequestParam("id")Integer id,
			@RequestParam(value="error",required= false)String fileUploadError,
			Model model){

		if(null != fileUploadError && fileUploadError.equals("error1")){
			fileUploadError = Constants.FILEUPLOAD_ERROR_1;
		}else if(null != fileUploadError && fileUploadError.equals("error2")){
			fileUploadError	= Constants.FILEUPLOAD_ERROR_2;
		}else if(null != fileUploadError && fileUploadError.equals("error3")){
			fileUploadError = Constants.FILEUPLOAD_ERROR_3;
		}else if(null != fileUploadError && fileUploadError.equals("error4")){
			fileUploadError = Constants.FILEUPLOAD_ERROR_4;
		}

		AppInfo appInfo = backendUserService.getAppInfo(id, null);

		model.addAttribute(appInfo);
		model.addAttribute("fileUploadError",fileUploadError);

		return "developer/appinfomodify";
	}

	/**
	 * TODO(APP信息管理系统------删除logo图片    ||  删除apk文件)
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-31 下午4:55:03
	 * @param flag
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "delfile.json")
	@ResponseBody
	public Object delFile(@RequestParam(value="flag",required=false) String flag,
			@RequestParam(value="id",required=false)Integer id){

		HashMap<String, String> resultMap = new HashMap<String, String>();

		String fileLocPath = null;

		if(flag == null || flag.equals("") ||
				id == null || id.equals("")){
			resultMap.put("result", "failed");
		}else if(flag.equals("logo")){//删除logo图片（操作app_info）
			try {
				fileLocPath = backendUserService.getAppInfo(id, null).getLogoLocPath();

				File file = new File(fileLocPath);

				if(file.exists()){
					if(file.delete()){//删除服务器存储的物理文件
						if(devUserService.deleteAppLogo(id)){//更新表
							resultMap.put("result", "success");
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(flag.equals("apk")){//删除apk文件（操作app_version）
			try {
				fileLocPath = devUserService.getAppVersionById(id).getApkLocPath();
				File file = new File(fileLocPath);
				if(file.exists())
					if(file.delete()){//删除服务器存储的物理文件
						if(devUserService.deleteApkFile(id)){//更新表
							resultMap.put("result", "success");
						}
					}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return JSONArray.toJSONString(resultMap);
	}

	/**
 	 * TODO(APP信息管理系统------修改App信息后保存)
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-11-1 上午8:50:49
	 * @param appInfo
	 * @param session
	 * @param request
	 * @param attach
	 * @return
	 */
	@RequestMapping(value = "/appinfomodifysave", method = RequestMethod.POST)
	public String appinfomodifysave(AppInfo appInfo,HttpSession session,HttpServletRequest request,
			@RequestParam(value="attach",required= false) MultipartFile attach){
		String logoPicPath =  null;
		String logoLocPath =  null;
		String APKName = appInfo.getAPKName();
		if(!attach.isEmpty()){
			String path = request.getSession().getServletContext().getRealPath("statics"+File.separator+"uploadfiles");
			String oldFileName = attach.getOriginalFilename();//原文件名
			String prefix = FilenameUtils.getExtension(oldFileName);//原文件后缀
			int filesize = 500000;
			if(attach.getSize() > filesize){//上传大小不得超过 50k
				return "redirect:/devUser/appinfomodify?id="+appInfo.getId()
						+"&error=error4";
			}else if(prefix.equalsIgnoreCase("jpg") || prefix.equalsIgnoreCase("png") 
					||prefix.equalsIgnoreCase("jepg") || prefix.equalsIgnoreCase("pneg")){//上传图片格式
				String fileName = APKName + ".jpg";//上传LOGO图片命名:apk名称.apk
				File targetFile = new File(path,fileName);
				if(!targetFile.exists()){
					targetFile.mkdirs();
				}
				try {
					attach.transferTo(targetFile);
				} catch (Exception e) {
					e.printStackTrace();
					return "redirect:/devUser/app/appinfomodify?id="+appInfo.getId()
							+"&error=error2";
				} 
				logoPicPath = request.getContextPath()+"/statics/uploadfiles/"+fileName;
				logoLocPath = path+File.separator+fileName;
			}else{
				return "redirect:/devUser/appinfomodify?id="+appInfo.getId()
						+"&error=error3";
			}
		}
		appInfo.setModifyBy(((DevUser)session.getAttribute(Constants.DEV_USER_SESSION)).getId());
		appInfo.setModifyDate(new Date());
		appInfo.setLogoLocPath(logoLocPath);
		appInfo.setLogoPicPath(logoPicPath);

		if(devUserService.modify(appInfo)){
			
			return "developer/appinfolist";
		}

		return "developer/appinfomodify";
	}
	
	
	/**
	 * TODO(APP信息管理系统------上下架操作)
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-11-1 上午8:49:51
	 * @param appid
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/{appid}/sale",method=RequestMethod.PUT)
	@ResponseBody
	public Object sale(@PathVariable String appid,HttpSession session){
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		Integer appIdInteger = 0;
		try{
			appIdInteger = Integer.parseInt(appid);
		}catch(Exception e){
			appIdInteger = 0;
		}
		resultMap.put("errorCode", "0");
		resultMap.put("appId", appid);
		if(appIdInteger>0){
			try {
				DevUser devUser = (DevUser)session.getAttribute(Constants.DEV_USER_SESSION);
				AppInfo appInfo = new AppInfo();
				appInfo.setId(appIdInteger);
				appInfo.setModifyBy(devUser.getId());
				if(devUserService.appsysUpdateSaleStatusByAppId(appInfo)){
					resultMap.put("resultMsg", "success");
				}else{
					resultMap.put("resultMsg", "success");
				}		
			} catch (Exception e) {
				resultMap.put("errorCode", "exception000001");
			}
		}else{
			//errorCode:0为正常
			resultMap.put("errorCode", "param000001");
		}
		
		return resultMap;
	
	}
}
