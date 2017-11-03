/**
 * 
 * 开发人员：Young
 * 开发时间：2017-10-12 下午2:27:55
 */
package cn.smbms.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.mysql.jdbc.Constants;
import com.mysql.jdbc.StringUtils;

import cn.smbms.pojo.Role;
import cn.smbms.util.PageUtil;
import cn.smbms.pojo.User;
import cn.smbms.service.UserService;
import cn.smbms.service.RoleService;

/**
 * 
 * 开发人员：Young
 * 开发时间：2017-10-12 下午2:27:55
 */
@Controller
public class UserController {

	@Autowired
	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Autowired
	private RoleService roleService;
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	@RequestMapping(value="login.html",method=RequestMethod.GET)
	public String isLogin(){
		return "../../login";
	}
	
	/**
	 * 登录
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-12 下午5:04:09
	 * @param model
	 * @param userCode
	 * @param password
	 * @param session
	 * @return
	 */
	@RequestMapping(value="login.html",method=RequestMethod.POST)
	public String login(Model model, 
			@RequestParam("userCode")String userCode, 
			@RequestParam("userPassword")String password, 
			HttpSession session){

		User user = userService.findByUserCode(userCode);
		if (user != null){			
			if (user.getUserPassword().equals(password)){
				if (session.getAttribute("USER_CODE") != null){
					session.removeAttribute("USER_CODE");					
				}
				session.setAttribute("USER_CODE", user);
				model.addAttribute("userCode", userCode);
				return "frame";
			} else {				
				model.addAttribute("message", "抱歉！密码不正确！");
				return  "../../login";				
			}			
		} else {				
			model.addAttribute("message", "抱歉！该用户不存在！");
			return  "../../login";			
		}
	}

	/**
	 * 退出
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-12 下午5:04:19
	 * @return
	 */
	@RequestMapping("logout.html")
	public String logout(Model model,  
			HttpSession session){
		session.removeAttribute("USER_CODE");
		return "../../login";

	}

	@RequestMapping(value="userList.html", method=RequestMethod.GET)
	public String isUserList(Model model, @RequestParam("method")String method, 
			HttpServletRequest request, 
			HttpServletResponse response)
					throws ServletException, IOException {

		if (method.equals("query")){ //动态查询
			query(request, response);
			return "userlist";
		} else if (method.equals("add")){ //添加用户
			if (add(request, response) == true){
				return "userlist";
			} else {
				return "useradd";
			}	
		} else if (method.equals("modifyexe")){ //修改
			if (modify(request, response) == true){
				return "userlist";
			} else {
				return "usermodify";
			}	
			
		}

		return "userlist";
	}
	
	/**
	 * 修改
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-14 下午5:10:57
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public boolean modify(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		boolean flag = false;
		
		Integer id = Integer.parseInt(request.getParameter("uid"));
		String userName = request.getParameter("userName");
		Integer gender = Integer.parseInt(request.getParameter("gender"));
		String birthdayStr = request.getParameter("birthday");
		Date birthday = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			birthday = sdf.parse(birthdayStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		Integer userRole = Integer.parseInt(request.getParameter("userRole"));
		
		if (userName != null && !"".equals(userName)){			
			userName = new String(userName.getBytes("ISO-8859-1"), "utf-8");
		}
		if (phone != null && !"".equals(phone)){			
			phone = new String(phone.getBytes("ISO-8859-1"), "utf-8");
		}
		if (address != null && !"".equals(address)){			
			address = new String(address.getBytes("ISO-8859-1"), "utf-8");
		}
		
		User user = new User();
		user.setId(id);
		user.setUserName(userName);
		user.setGender(gender);
		user.setBirthday(birthday);
		user.setAddress(address);
		user.setPhone(phone);
		user.setUserRole(userRole);
		
		Integer result = userService.updateUser(user);
		
		if (result == 1){
			flag = true;
		}
		return flag;
	}

	/**
	 * add时获取用户角色列表
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-14 下午2:39:23
	 * @return
	 */
	@RequestMapping(value = "userrolelist.json", method = RequestMethod.GET)
	@ResponseBody
	public Object getUserRole(){

		List<Role> listRole = roleService.getUserRoleList();

		return listRole;

	}

	/**
	 * 添加 判断用户code是否可以使用
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-14 上午11:50:51
	 * @param userCode
	 * @return
	 */
	@RequestMapping(value = "getRole.html", method = RequestMethod.GET)
	@ResponseBody
	public Object getRoleList(@RequestParam("userCode")String userCode){


		Map<String, String> resultMap = new HashMap<String, String>();

		if (StringUtils.isNullOrEmpty(userCode)){
			resultMap.put("userCode", "exist");
		} else {
			User resultUser = userService.findByUserCode(userCode);
			if (null != resultUser){
				resultMap.put("userCode", "exist");
			} else {
				resultMap.put("userCode", "noexist");
			}
		}

		return JSONArray.toJSONString(resultMap);

	}

	/**
	 * 查询用户详细信息
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-14 上午11:52:01
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value="viewUser.html", method=RequestMethod.GET)
	public String viewUser(Model model, @RequestParam("uid")Integer id){

		User user = userService.getUserById(id);

		model.addAttribute("user", user);

		return "userview";
	}

	/**
	 * 修改显示信息
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-14 下午2:46:18
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "modifyUser.html", method=RequestMethod.GET)
	public String modifyUser(Model model, @RequestParam("uid")Integer id){

		User user = userService.getUserById(id);

		model.addAttribute("user", user);

		return "usermodify";
	}

	/**
	 * 删除
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-14 下午3:03:44
	 * @param userCode
	 * @return
	 */
	@RequestMapping(value="delUser", method=RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object delUser(@RequestParam("uid")Integer id){
		Map<String, String> resultMap = new HashMap<String, String>();

		User resultUser = userService.findById(id);
		if (null != resultUser){
			Integer resultDel = userService.deleteUser(id);
			if (resultDel == 1){
				resultMap.put("delResult", "true");
			} else {
				resultMap.put("delResult", "false");
			}
		} else {
			resultMap.put("delResult", "notexist");
		}

		return JSONArray.toJSONString(resultMap);

	}
	
	/**
	 * 修改密码
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-14 下午7:23:29
	 * @param id
	 * @param olPpwd
	 * @return
	 */
	@RequestMapping(value="pwdModify", method=RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object pwdModify(@RequestParam("uid")Integer id, 
			@RequestParam("oldpassword")String olPpwd,
			HttpSession session){
		
		Map<String, String> map = new HashMap<String, String>();
		
		User resultUser = userService.findById(id);
		
		if (session.getAttribute("USER_CODE") != null){
			if (null != resultUser){
				if (resultUser.getUserPassword().equals(olPpwd)){
					map.put("result", "true");
				} else {
					map.put("result", "false");
				}
			} else {
				map.put("result", "error");
			}
		} else {
			map.put("result", "sessionerror");
		}
		
		return JSONArray.toJSONString(map);
	}

	@RequestMapping(value="modifyPwd.html", method=RequestMethod.POST)
	public String modifyPwd(@RequestParam("uid")Integer id, 
			@RequestParam("rnewpassword")String newPwd,
			HttpSession session){
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("newPwd", newPwd);
		
		Integer result = userService.updatePwd(map);
		
		if (result == 1){
			if (session.getAttribute("USER_CODE") != null){
				session.removeAttribute("USER_CODE");					
			}
			return "../../error";
		} else {
			return "pwdmodify";
		}
	}
	

	/**
	 * 添加用户
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-14 上午11:52:22
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public boolean add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		boolean flag = false;

		String userCode = request.getParameter("userCode");
		String userName = request.getParameter("userName");
		String password = request.getParameter("ruserPassword");
		Integer gender = Integer.parseInt(request.getParameter("gender"));
		String birthdayStr = request.getParameter("birthday");
		Date birthday = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			birthday = sdf.parse(birthdayStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		Integer userRole = Integer.parseInt(request.getParameter("userRole"));

		if (userName != null && !"".equals(userName)){			
			userName = new String(userName.getBytes("ISO-8859-1"), "utf-8");
		}
		if (userCode != null && !"".equals(userCode)){			
			userCode = new String(userCode.getBytes("ISO-8859-1"), "utf-8");
		}
		if (password != null && !"".equals(password)){			
			password = new String(password.getBytes("ISO-8859-1"), "utf-8");
		}
		if (phone != null && !"".equals(phone)){			
			phone = new String(phone.getBytes("ISO-8859-1"), "utf-8");
		}
		if (address != null && !"".equals(address)){			
			address = new String(address.getBytes("ISO-8859-1"), "utf-8");
		}

		User user = new User();
		user.setUserName(userName);
		user.setUserCode(userCode);
		user.setUserPassword(password);
		user.setPhone(phone);
		user.setAddress(address);
		user.setBirthday(birthday);
		user.setGender(gender);
		user.setUserRole(userRole);

		Integer result = userService.addUser(user);

		if (result == 1){
			flag = true;
		}

		return flag;
	}



	/**
	 * 动态查询 
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-14 上午11:52:47
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void query(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//获取当前页码
		String index = request.getParameter("pageIndex");
		//获取用户名
		String userName = request.getParameter("queryname");
		//获取用角色
		String userRole = request.getParameter("queryUserRole");

		if (userName != null && !"".equals(userName)){			
			userName = new String(userName.getBytes("ISO-8859-1"), "utf-8");
		}

		//创建map集合用来存储数据
		Map<String, Object> userMap = new HashMap<String, Object>();

		//如果页码为空 给页码赋值 “1”
		if (index == null || "".equals(index)){

			index = "1";
		}
		//当数据不为空时，向集合中添加数据
		if (userName != null && !"".equals(userName)){

			userMap.put("userName", userName);
		}
		if (userRole != null && !"".equals(userRole)){

			userMap.put("userRole", userRole);
		}
		//向集合中添加分页信息
		userMap.put("index", (Integer.parseInt(index) - 1) * PageUtil.SIZE);
		userMap.put("size", PageUtil.SIZE);

		PageUtil userPage = userService.getUserList(userMap);

		//给工具类存放当前页码
		userPage.setIndex(Integer.parseInt(index));

		//获取角色列表
		List<Role> roleList = roleService.getUserRoleList();

		request.setAttribute("userPage", userPage);
		request.setAttribute("roleList", roleList);

		//参数回执
		request.setAttribute("queryUserName", userName);
		request.setAttribute("queryUserRole", userRole);
	}

	@RequestMapping(value="toAddUser.html")
	public String toAddUser(){	
		return "useradd";
	}
	@RequestMapping(value="pwdmodify.html")
	public String pwdmodify(){	
		return "pwdmodify";
	}
}
