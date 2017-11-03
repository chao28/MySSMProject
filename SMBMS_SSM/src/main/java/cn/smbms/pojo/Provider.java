/**
 * 
 * 开发人员：Young
 * 开发时间：2017-9-14 下午3:28:31
 */
package cn.smbms.pojo;

import java.util.Date;

/**
 * 超市管理系统--供应商实体类
 * 开发人员：Young
 * 开发时间：2017-9-14 下午3:28:31
 */
public class Provider {

	//供应商ID
	private Integer id;
	//供应商编码
	private String proCode;
	//供应商名称
	private String proName;
	//供应商描述
	private String proDesc;
	//供应商负责人
	private String proContact;
	//供应商负责人联系电话
	private String proPhone;
	//供应商地址
	private String proAddress;
	//供应商座机电话
	private String proFax;
	//创建人ID
	private Integer createdBy;
	//创建时间
	private Date creationDate;
	//修改人时间
	private Date modifyDate;
	//修改人ID
	private Integer modifyBy;
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getProCode() {
		return proCode;
	}
	public void setProCode(String proCode) {
		this.proCode = proCode;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public String getProDesc() {
		return proDesc;
	}
	public void setProDesc(String proDesc) {
		this.proDesc = proDesc;
	}
	public String getProContact() {
		return proContact;
	}
	public void setProContact(String proContact) {
		this.proContact = proContact;
	}
	public String getProPhone() {
		return proPhone;
	}
	public void setProPhone(String proPhone) {
		this.proPhone = proPhone;
	}
	public String getProAddress() {
		return proAddress;
	}
	public void setProAddress(String proAddress) {
		this.proAddress = proAddress;
	}
	public String getProFax() {
		return proFax;
	}
	public void setProFax(String proFax) {
		this.proFax = proFax;
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
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	public Integer getModifyBy() {
		return modifyBy;
	}
	public void setModifyBy(Integer modifyBy) {
		this.modifyBy = modifyBy;
	}
	
	
}
