/**
 * 
 * 开发人员：Young
 * 开发时间：2017-9-14 下午3:27:24
 */
package cn.smbms.pojo;

import java.util.Date;

/**
 * 超市管理系统--订单实体类
 * 开发人员：Young
 * 开发时间：2017-9-14 下午3:27:24
 */
public class Bill {

	//订单ID
	private Integer id;
	//订单编码
	private String billCode;
	//商品名称
	private String productName;
	//商品描述
	private String productDesc;
	//商品计量单位
	private String productUnit;
	//数量
	private Double productCount;
	//总价
	private Double totalPrice;
	//是否付款 （1：未支付      2：已支付）
	private Integer isPayment;
	//创建人ID
	private Integer createdBy;
	//创建时间
	private Date creationDate;
	//修改人ID
	private Integer modifyBy;
	//修改时间
	private Date modifyDate;
	//供应商ID
	private Integer providerId;
	//供应商名称
	private String proName;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getBillCode() {
		return billCode;
	}
	public void setBillCode(String billCode) {
		this.billCode = billCode;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductDesc() {
		return productDesc;
	}
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	public String getProductUnit() {
		return productUnit;
	}
	public void setProductUnit(String productUnit) {
		this.productUnit = productUnit;
	}
	public Double getProductCount() {
		return productCount;
	}
	public void setProductCount(Double productCount) {
		this.productCount = productCount;
	}
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Integer getIsPayment() {
		return isPayment;
	}
	public void setIsPayment(Integer isPayment) {
		this.isPayment = isPayment;
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
	public Integer getProviderId() {
		return providerId;
	}
	public void setProviderId(Integer providerId) {
		this.providerId = providerId;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	
	
	
	
}
