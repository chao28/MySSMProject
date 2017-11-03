/**
 * 
 * 开发人员：Young
 * 开发时间：2017-10-13 上午10:52:22
 */
package cn.smbms.mapper;

import java.util.List;

import cn.smbms.pojo.Role;

/**
 * 用户角色接口
 * 开发人员：Young
 * 开发时间：2017-10-13 上午10:52:22
 */
public interface RoleMapper {

	/**
	 * 获取角色列表
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-13 上午10:48:38
	 * @return
	 */
	List<Role> getUserRoleList();
	
}