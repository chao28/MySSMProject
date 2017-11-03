/**
 * 
 * 开发人员：Young
 * 开发时间：2017-10-28 上午10:37:32
 */
package cn.appinfo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.appinfo.pojo.BackendUser;
import cn.appinfo.pojo.DevUser;
import cn.appinfo.util.Constants;

/**
 * TODO(APP信息管理系统------拦截器类)
 * 开发人员：Young
 * 开发时间：2017-10-28 上午10:37:32
 */
public class SysInterceptor extends HandlerInterceptorAdapter{


	public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler) throws Exception{
		HttpSession session = request.getSession();

		BackendUser backendUser = (BackendUser)session.getAttribute(Constants.BACKEND_USER_SESSION);
		DevUser devUser = (DevUser)session.getAttribute(Constants.DEV_USER_SESSION);

		if(null != devUser){ //devUserSession
			return true;
		}else if(null != backendUser){ //backendUserSession
			return true;
		}else{
			response.sendRedirect(request.getContextPath()+"/403.jsp");
			return false;
		}

	}

}
