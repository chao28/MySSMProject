/**
 * 
 * 开发人员：Young
 * 开发时间：2017-10-15 上午9:45:13
 */
package cn.smbms.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;

import cn.smbms.pojo.Provider;
import cn.smbms.util.PageUtil;
import cn.smbms.service.ProService;

/**
 * 供应商控制器
 * 开发人员：Young
 * 开发时间：2017-10-15 上午9:45:13
 */
@Controller
public class ProController {

	@Autowired
	private ProService proService;
	public void setProService(ProService proService) {
		this.proService = proService;
	}

	@RequestMapping(value = "toAddPro.html")
	public String toAddPro(){
		return "provideradd";
	}

	@RequestMapping(value = "provider.html", method = RequestMethod.GET)
	public String provider(Model model, 
			@RequestParam("method")String method,
			HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {

		if (method.equals("query")){
			query(request, response);
			return "providerlist";
		} else if (method.equals("add")){
			if (add(request, response) == true){
				return "providerlist";
			} else {
				return "provideradd";
			}
		} else if (method.equals("modify")){
			if (modify(request, response) == true){
				return "providerlist";
			} else {
				return "providermodify";
			}
		}

		return "providerlist";
	}

	/**
	 * 动态查询
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-15 上午9:52:17
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void query(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//获取当前页码
		String index = request.getParameter("pageIndex");
		//获取供应商编码
		String queryProCode = request.getParameter("queryProCode");
		//获取供应商名称
		String queryProName = request.getParameter("queryProName");

		if (queryProCode != null && !"".equals(queryProCode)){			
			queryProCode = new String(queryProCode.getBytes("ISO-8859-1"), "utf-8");
		} 
		if (queryProName != null && !"".equals(queryProName)){			
			queryProName = new String(queryProName.getBytes("ISO-8859-1"), "utf-8");
		} 

		Map<String, Object> proMap = new HashMap<String, Object>();

		if (index == null || "".equals(index)){

			index = "1";
		}
		if (queryProCode != null &&  !"".equals(queryProCode)){

			proMap.put("proCode", queryProCode);
		}
		if (queryProName != null && !"".equals(queryProName)){

			proMap.put("proName", queryProName);
		}

		//向集合中添加页码
		proMap.put("index", (Integer.parseInt(index) - 1) * PageUtil.SIZE);
		proMap.put("size", PageUtil.SIZE);

		PageUtil proPage = proService.findByPage(proMap);

		proPage.setIndex(Integer.parseInt(index));
		request.setAttribute("proPage", proPage);

		request.setAttribute("queryProCode", queryProCode);
		request.setAttribute("queryProName", queryProName);

	}

	/**
	 * 添加
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-15 上午10:12:46
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public boolean add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		boolean flag = false;

		//供应商编码
		String proCode = request.getParameter("proCode");
		//供应商名称
		String proName = request.getParameter("proName");
		//联系人
		String proContact = request.getParameter("proContact");
		//联系电话
		String proPhone = request.getParameter("proPhone");
		//传真
		String proFax = request.getParameter("proFax");
		//联系地址
		String proAddress = request.getParameter("proAddress");
		//描述
		String proDesc = request.getParameter("proDesc");

		if (proCode != null && !"".equals(proCode)){			
			proCode = new String(proCode.getBytes("ISO-8859-1"), "utf-8");
		}
		if (proName != null && !"".equals(proName)){			
			proName = new String(proName.getBytes("ISO-8859-1"), "utf-8");
		}
		if (proContact != null && !"".equals(proContact)){			
			proContact = new String(proContact.getBytes("ISO-8859-1"), "utf-8");
		}
		if (proPhone != null && !"".equals(proPhone)){			
			proPhone = new String(proPhone.getBytes("ISO-8859-1"), "utf-8");
		}
		if (proFax != null && !"".equals(proFax)){			
			proFax = new String(proFax.getBytes("ISO-8859-1"), "utf-8");
		}
		if (proAddress != null && !"".equals(proAddress)){			
			proAddress = new String(proAddress.getBytes("ISO-8859-1"), "utf-8");
		}
		if (proDesc != null && !"".equals(proDesc)){			
			proDesc = new String(proDesc.getBytes("ISO-8859-1"), "utf-8");
		}

		Provider pro = new Provider();
		pro.setProCode(proCode);
		pro.setProName(proName);
		pro.setProContact(proContact);
		pro.setProPhone(proPhone);
		pro.setProFax(proFax);
		pro.setProAddress(proAddress);
		pro.setProDesc(proDesc);

		Integer result = proService.addPro(pro);

		if(result == 1){
			flag = true;
		}

		return flag;
	}

	/**
	 * 修改
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-15 上午10:35:39
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public boolean modify(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean flag = false;

		//ID
		Integer id = Integer.parseInt(request.getParameter("proid"));
		//供应商编码
		String proCode = request.getParameter("proCode");
		//供应商名称
		String proName = request.getParameter("proName");
		//联系人
		String proContact = request.getParameter("proContact");
		//联系电话
		String proPhone = request.getParameter("proPhone");
		//传真
		String proFax = request.getParameter("proFax");
		//联系地址
		String proAddress = request.getParameter("proAddress");
		//描述
		String proDesc = request.getParameter("proDesc");

		if (proCode != null && !"".equals(proCode)){			
			proCode = new String(proCode.getBytes("ISO-8859-1"), "utf-8");
		}
		if (proName != null && !"".equals(proName)){			
			proName = new String(proName.getBytes("ISO-8859-1"), "utf-8");
		}
		if (proContact != null && !"".equals(proContact)){			
			proContact = new String(proContact.getBytes("ISO-8859-1"), "utf-8");
		}
		if (proPhone != null && !"".equals(proPhone)){			
			proPhone = new String(proPhone.getBytes("ISO-8859-1"), "utf-8");
		}
		if (proFax != null && !"".equals(proFax)){			
			proFax = new String(proFax.getBytes("ISO-8859-1"), "utf-8");
		}
		if (proAddress != null && !"".equals(proAddress)){			
			proAddress = new String(proAddress.getBytes("ISO-8859-1"), "utf-8");
		}
		if (proDesc != null && !"".equals(proDesc)){			
			proDesc = new String(proDesc.getBytes("ISO-8859-1"), "utf-8");
		}

		Provider pro = new Provider();
		pro.setId(id);
		pro.setProCode(proCode);
		pro.setProName(proName);
		pro.setProContact(proContact);
		pro.setProPhone(proPhone);
		pro.setProFax(proFax);
		pro.setProAddress(proAddress);
		pro.setProDesc(proDesc);

		Integer result = proService.updatePro(pro);

		if(result == 1){
			flag = true;
		}

		return flag;
	}

	/**
	 * 现实详细信息
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-15 上午10:23:18
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "viewPro.html")
	public String viewPro(Model model, @RequestParam("proid")Integer id){

		Provider provider = proService.findById(id);

		model.addAttribute("provider", provider);

		return "providerview";
	}

	/**
	 * 修改前显示
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-15 上午10:29:59
	 * @return
	 */
	@RequestMapping(value = "modifyPro.html")
	public String modifyPro(Model model, @RequestParam("proid")Integer id){
		Provider provider = proService.findById(id);
		model.addAttribute("provider", provider);
		return "providermodify";
	}
	
	@RequestMapping(value = "deletePro.json", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object deletePro(@RequestParam("proid")Integer id){
		
		Map<String, String> map = new HashMap<String, String>();
		
		Provider provider = proService.findById(id);
		if (null != provider){
			Integer resultBill = proService.findProOrBillById(id);
			if (0 != resultBill){
				map.put("delResult", resultBill.toString());
			} else {
				Integer delResult = proService.deletePro(id);
				if (delResult == 1){
					map.put("delResult", "true");
				} else {
					map.put("delResult", "false");
				}
			}
		} else {
			map.put("delResult", "notexist");
		}
		
		return JSONArray.toJSONString(map);
	}

}
