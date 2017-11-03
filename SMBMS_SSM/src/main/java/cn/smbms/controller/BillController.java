/**
 * 
 * 开发人员：Young
 * 开发时间：2017-10-14 下午8:29:57
 */
package cn.smbms.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;

import cn.smbms.mapper.ProMapper;
import cn.smbms.pojo.Bill;
import cn.smbms.pojo.Provider;
import cn.smbms.pojo.Role;
import cn.smbms.service.BillService;
import cn.smbms.service.ProService;
import cn.smbms.util.PageUtil;

/**
 * 
 * 开发人员：Young
 * 开发时间：2017-10-14 下午8:29:57
 */
@Controller
public class BillController {

	@Autowired
	private BillService billService;
	public void setBillService(BillService billService) {
		this.billService = billService;
	}
	@Autowired
	private ProService proService;
	public void setProService(ProService proService) {
		this.proService = proService;
	}

	@RequestMapping(value="toAddBill.html")
	public String toAddBill(){
		return "billadd";
	}

	/**
	 * 获取供应商列表
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-15 上午9:13:43
	 * @return
	 */
	@RequestMapping(value = "getProList.json", method = RequestMethod.GET)
	@ResponseBody
	public Object getProList(){

		List<Provider> proList = proService.findProviderAll();

		return proList;
	}

	@RequestMapping(value = "bill.html", method = RequestMethod.GET)
	public String bill(@RequestParam("method")String method, 
			Model model, 
			HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {

		if (method.equals("query")){  //动态查询
			query(request, response);
			return "billlist";
		} else if (method.equals("add")){ //添加
			if (add(request, response) == true){
				return "billlist";
			} else {
				return "billadd";
			}
		} else if (method.equals("modifysave")){  //修改
			if (modifysave(request, response) == true){
				return "billlist";
			} else {
				return "billmodify";
			}
		}

		return "billlist";
	}

	/**
	 * 动态查询
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-14 下午8:37:27
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void query(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//获取当前页码
		String index = request.getParameter("pageIndex");
		//获取商品名
		String productName = request.getParameter("queryProductName");
		//获取供应商id
		String providerId = request.getParameter("queryProviderId");
		//获取是否付款
		String isPayment = request.getParameter("queryIsPayment");

		if (productName != null && !"".equals(productName)){			
			productName = new String(productName.getBytes("ISO-8859-1"), "utf-8");
		}

		if (index == null || "".equals(index)){

			index = "1"; //默认当前第一页
		}

		Map<String, Object> map = new HashMap<String, Object>();

		if (productName != null && !"".equals(productName)){

			map.put("productName", productName);
		}
		if (providerId != null && !"".equals(providerId)){

			map.put("providerId", providerId);
		}
		if (isPayment != null && !"".equals(isPayment)){

			map.put("isPayment", isPayment);
		}

		map.put("index", (Integer.parseInt(index) - 1) * PageUtil.SIZE);
		map.put("size", PageUtil.SIZE);

		PageUtil page = billService.getBillList(map);

		//给工具类存放当前页码数
		page.setIndex(Integer.parseInt(index));		
		request.setAttribute("page", page);

		//获取供应商名称
		List<Provider> proList = proService.findProviderAll();
		request.setAttribute("proList", proList);

		//参数回执
		request.setAttribute("queryProductName", productName);
		request.setAttribute("queryProviderId", providerId);
		request.setAttribute("queryIsPayment", isPayment);

	}

	/**
	 * 添加
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-15 上午8:58:49
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public boolean add(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{

		boolean flag = false;

		String billCode = request.getParameter("billCode");
		String productName = request.getParameter("productName");
		String productUnit = request.getParameter("productUnit");
		Double productCount = Double.parseDouble(request.getParameter("productCount"));
		Double totalPrice = Double.parseDouble(request.getParameter("totalPrice"));
		Integer providerId = Integer.parseInt(request.getParameter("providerId"));
		Integer isPayment = Integer.parseInt(request.getParameter("isPayment"));

		if (billCode != null && !"".equals(billCode)){			
			billCode = new String(billCode.getBytes("ISO-8859-1"), "utf-8");
		}
		if (productName != null && !"".equals(productName)){			
			productName = new String(productName.getBytes("ISO-8859-1"), "utf-8");
		}
		if (productUnit != null && !"".equals(productUnit)){			
			productUnit = new String(productUnit.getBytes("ISO-8859-1"), "utf-8");
		}

		Bill bill = new Bill();
		bill.setBillCode(billCode);
		bill.setProductName(productName);
		bill.setProductUnit(productUnit);
		bill.setProductCount(productCount);
		bill.setTotalPrice(totalPrice);
		bill.setProviderId(providerId);
		bill.setIsPayment(isPayment);

		Integer result = billService.addBill(bill);

		if (result == 1){
			flag = true;
		}

		return flag;
	}

	/**
	 * 修改
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-15 上午9:25:11
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public boolean modifysave(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{

		boolean flag = false;

		Integer id = Integer.parseInt(request.getParameter("id"));
		String billCode = request.getParameter("billCode");
		String productName = request.getParameter("productName");
		String productUnit = request.getParameter("productUnit");
		Double productCount = Double.parseDouble(request.getParameter("productCount"));
		Double totalPrice = Double.parseDouble(request.getParameter("totalPrice"));
		Integer providerId = Integer.parseInt(request.getParameter("providerId"));
		Integer isPayment = Integer.parseInt(request.getParameter("isPayment"));

		if (billCode != null && !"".equals(billCode)){			
			billCode = new String(billCode.getBytes("ISO-8859-1"), "utf-8");
		}
		if (productName != null && !"".equals(productName)){			
			productName = new String(productName.getBytes("ISO-8859-1"), "utf-8");
		}
		if (productUnit != null && !"".equals(productUnit)){			
			productUnit = new String(productUnit.getBytes("ISO-8859-1"), "utf-8");
		}

		Bill bill = new Bill();
		bill.setId(id);
		bill.setBillCode(billCode);
		bill.setProductName(productName);
		bill.setProductUnit(productUnit);
		bill.setProductCount(productCount);
		bill.setTotalPrice(totalPrice);
		bill.setProviderId(providerId);
		bill.setIsPayment(isPayment);

		Integer result = billService.updateBill(bill);
		
		if (result == 1){
			flag = true;
		}
		
		return flag;

	}

	/**
	 * 显示详情
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-15 上午8:42:15
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "viewBill.html")
	public String viewBill(Model model, @RequestParam("billid")Integer id){

		Bill bill = billService.findById(id);

		model.addAttribute("bill", bill);

		return "billview";
	}

	/**
	 * 修改前显示
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-15 上午9:21:54
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "modifyBill.html")
	public String modifyBill(Model model, @RequestParam("billid")Integer id){

		Bill bill = billService.findById(id);

		model.addAttribute("bill", bill);

		return "billmodify";
	}
	
	/**
	 * 删除
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-15 上午9:34:02
	 * @return
	 */
	@RequestMapping(value = "delBill.json", method = RequestMethod.GET)
	@ResponseBody
	public Object delBill(Model model, @RequestParam("billid")Integer id){
		
		Map<String, String> map = new HashMap<String, String>();
		
		Bill bill = billService.findById(id);
		
		if (null != bill){
			Integer resultDel = billService.deleteBill(id);
			
			if (resultDel == 1){
				map.put("result", "true");
			} else {
				map.put("result", "false");
			}
			
		} else {
			map.put("result", "noexists");
		}
		
		return JSONArray.toJSONString(map);
	}
}
