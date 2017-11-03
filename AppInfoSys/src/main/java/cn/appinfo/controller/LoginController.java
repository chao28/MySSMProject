/**
 * 
 * 开发人员：Young
 * 开发时间：2017-10-17 下午4:21:23
 */
package cn.appinfo.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.appinfo.pojo.BackendUser;
import cn.appinfo.pojo.DevUser;
import cn.appinfo.service.BackendUserService;
import cn.appinfo.service.DevUserService;
import cn.appinfo.util.Constants;

/**
 * TODO(APP信息管理系统------登录控制器)
 * 开发人员：Young
 * 开发时间：2017-10-17 下午4:21:23
 */
@Controller
public class LoginController {

	private Logger logger = Logger.getLogger(LoginController.class);
	
	@Autowired
	private DevUserService devUserService;
	public void setDevUserService(DevUserService devUserService) {
		this.devUserService = devUserService;
	}
	
	@Autowired
	private BackendUserService backendUserService;
	public void setBackendUserService(BackendUserService backendUserService) {
		this.backendUserService = backendUserService;
	}

	//跳转到后台管理系统登录页面
	@RequestMapping(value = "backendLogin")
	public String toBackendLogin(){
		
		return "backendlogin";
	}
	
	//跳转到开发者平台登录页面
	@RequestMapping(value = "devLogin")
	public String toDevLogin(){
		
		return "devlogin";
	}
	
	@RequestMapping(value = "mains")
	public String toMains(){
		return "../../mains";
	}
	
	/**
	 * TODO(APP信息管理系统------开发者平台登录方法)
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-18 下午5:41:51
	 * @param devCode 用户编码(或名称)
	 * @param devPassword 用户密码
	 * @param request
	 * @param session
	 * @return 登陆成功，跳转到主页面
	 */
	@RequestMapping(value = "devLogin", method = RequestMethod.POST)
	public String doDevLogin(@RequestParam(value = "devCode")String devCode, 
			@RequestParam(value = "devPassword")String devPassword, 
			Model model, HttpSession session){
		
		Map<String, Object> objMap = new HashMap<String, Object>();
		objMap.put("devCode", devCode);
		
		DevUser devUser = devUserService.findByObject(objMap);
		
		if (devUser != null){
			if (devUser.getDevPassword().equals(devPassword)){
				if (session.getAttribute(Constants.DEV_USER_SESSION) != null){
					session.removeAttribute(Constants.DEV_USER_SESSION);
				}
				session.setAttribute(Constants.DEV_USER_SESSION, devUser);
				return "developer/main";
			} else {
				model.addAttribute(Constants.SYS_MESSAGE, "密码错误！");
				return "devlogin";
			}
		} else {
			model.addAttribute(Constants.SYS_MESSAGE, "该用户不存在！");
			return "devlogin";
		}
	}
	
	/**
	 * TODO(APP信息管理系统------后台管理登录方法)
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-19 下午2:18:01
	 * @param devCode 用户编码(或名称)
	 * @param devPassword 用户密码
	 * @param model
	 * @param session
	 * @return 登陆成功，跳转到主页面
	 */
	@RequestMapping(value = "backendLogin", method = RequestMethod.POST)
	public String doBackendLogin(@RequestParam(value = "userCode")String userCode, 
			@RequestParam(value = "userPassword")String userPassword, 
			Model model, HttpSession session){
		
		Map<String, Object> objMap = new HashMap<String, Object>();
		objMap.put("userCode", userCode);
		
		BackendUser backendUser = backendUserService.findByObject(objMap);
		
		
		if (backendUser != null){
			if (backendUser.getUserPassword().equals(userPassword)){
				if (session.getAttribute(Constants.BACKEND_USER_SESSION) != null){
					session.removeAttribute(Constants.BACKEND_USER_SESSION);
				}
				session.setAttribute(Constants.BACKEND_USER_SESSION, backendUser);
				return "backend/main";
			} else {
				model.addAttribute(Constants.SYS_MESSAGE, "密码错误！");
				return "backendlogin";
			}
		} else {
			model.addAttribute(Constants.SYS_MESSAGE, "该用户不存在！");
			return "backendlogin";
		}
	}

	/**
	 * TODO(APP信息管理系统------后台管理退出方法)
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-26 下午2:01:14
	 * @param session
	 * @param request
	 * @return 返回到后台管理登录页面
	 */
	@RequestMapping(value = "backendLogout")
	public String doBackendLogout(HttpSession session, HttpServletRequest request){
		
		if (session.getAttribute(Constants.BACKEND_USER_SESSION) != null){
			session.removeAttribute(Constants.BACKEND_USER_SESSION);
		}
		
		return "../../index";
	}
	
	/**
	 * TODO(APP信息管理系统------开发者平台退出方法)
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-27 上午8:31:46
	 * @param session
	 * @param request
	 * @return 返回到开发者平台登录页面
	 */
	@RequestMapping(value = "devLogout")
	public String doDevLogout(HttpSession session, HttpServletRequest request){
		
		if (session.getAttribute(Constants.DEV_USER_SESSION) != null){
			session.removeAttribute(Constants.DEV_USER_SESSION);
		}
		
		return "../../index";
		
	}
	
	
}
