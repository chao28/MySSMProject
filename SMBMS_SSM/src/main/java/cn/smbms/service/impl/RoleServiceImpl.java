/**
 * 
 * 开发人员：Young
 * 开发时间：2017-10-13 上午10:53:52
 */
package cn.smbms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.smbms.mapper.RoleMapper;
import cn.smbms.pojo.Role;
import cn.smbms.service.RoleService;

/**
 * 
 * 开发人员：Young
 * 开发时间：2017-10-13 上午10:53:52
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleMapper roleMapper;
	
	public void setRoleMapper(RoleMapper roleMapper) {
		this.roleMapper = roleMapper;
	}
	/**
	 * 获取角色列表
	 * 构造函数 
	 *
	 * 开发人员：Young
	 * 开发时间：2017-10-13 上午10:49:25
	 * @return
	 */
	@Override
	public List<Role> getUserRoleList() {
		
		return roleMapper.getUserRoleList();
	}

}
