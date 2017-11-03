/**
 * 
 * 开发人员：Young
 * 开发时间：2017-10-13 下午12:22:28
 */
package cn.bdqn.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.smbms.pojo.Role;
import cn.smbms.service.RoleService;
import cn.smbms.service.impl.RoleServiceImpl;

/**
 * 
 * 开发人员：Young
 * 开发时间：2017-10-13 下午12:22:28
 */
public class Test {

	public static void main(String[] args) {
		
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		RoleService rs = (RoleService)ac.getBean("roleService");
	
		List<Role> list = rs.getUserRoleList();
		
		System.out.println(list.size());
		
	}
	
}
