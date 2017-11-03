/**
 * 
 * 开发人员：Young
 * 开发时间：2017-10-20 下午1:53:24
 */
package cn.appinfo.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.appinfo.pojo.BackendUser;
import cn.appinfo.pojo.DevUser;
import cn.appinfo.service.BackendUserService;
import cn.appinfo.service.DevUserService;
import cn.appinfo.util.PageUtil;

/**
 * 
 * 开发人员：Young
 * 开发时间：2017-10-20 下午1:53:24
 */
public class Test {

	/**
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-20 下午1:53:24
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");

		BackendUserService dus = (BackendUserService)ac.getBean("backendUserService");

		Map<String, Object> objMap = new HashMap<String, Object>();
		objMap.put("index", 0);
		objMap.put("size", 5);
		
		
		PageUtil list = dus.getAppInfoList(objMap);

		System.out.println(list.getAppList().size());

	}

}
