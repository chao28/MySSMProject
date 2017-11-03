/**
 * 
 * 开发人员：Young
 * 开发时间：2017-9-14 下午3:29:22
 */
package cn.smbms.pojo;

import java.util.Date;

/**
 * 超市管理系统--用户角色实体类
 * 开发人员：Young
 * 开发时间：2017-9-14 下午3:29:22
 */
public class Role {

	//用户角色id
	private Integer id;
	//用户角色编码
	private String roleCode;
	//用户角色名称
	private String roleName;
	//创建人ID
	private Integer createdBy;
	//创建时间
	private Date creationDate;
	//修改人ID
	private Integer modifyBy;
	//修改时间
	private Date modifyDate;
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Integer getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Integer getModifyBy() {
		return modifyBy;
	}
	public void setModifyBy(Integer modifyBy) {
		this.modifyBy = modifyBy;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	
	
	
}
