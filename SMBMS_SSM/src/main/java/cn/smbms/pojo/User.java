/**
 * 
 * 开发人员：Young
 * 开发时间：2017-9-14 下午3:29:55
 */
package cn.smbms.pojo;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 超市管理系统--用户实体类
 * 开发人员：Young
 * 开发时间：2017-9-14 下午3:29:55
 */
public class User {

	//用户ID
	private Integer id;
	//用户编码
	private String userCode;
	//用户名称
	private String userName;
	//密码
	private String userPassword;
	//性别
	private Integer gender;
	//生日
	private Date birthday;
	//电话
	private String phone;
	//地址
	private String address;
	//角色ID
	private Integer userRole;
	//创建人ID
	private Integer createdBy;
	//创建时间
	private Date creationDate;
	//修改人ID
	private Integer modifyBy;
	//修改时间
	private Date modifyDate;
	//角色名称
	private String roleName;
	//年龄
	private Integer age;
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getUserRole() {
		return userRole;
	}
	public void setUserRole(Integer userRole) {
		this.userRole = userRole;
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
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Integer getAge() {
		Integer sy = new Date().getYear();
		Integer by = birthday.getYear();
		return (sy - by);
	}
	public void setAge(Integer age) {
		this.age = age;
	}
}
