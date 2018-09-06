package com.zhima.traffic.model;

import com.zhima.basic.jdbc.FieldTag;
import com.zhima.basic.jdbc.TableTag;

@TableTag(cName="公司信息",eName="InsCompany",tabName="ins_company")
public class InsCompany implements java.io.Serializable {
	private static final long serialVersionUID = 1L;	

	@FieldTag(cName="公司序号",eName="companySeq",field="company_seq",pk=true)
	private String companySeq="";
	
	@FieldTag(cName="机构序号",eName="organizeSeq",field="organize_seq")
	private String organizeSeq="";
	
	@FieldTag(cName="保险代码",eName="insuranceCode",field="insurance_code")
	private String insuranceCode="";
	
	@FieldTag(cName="保险名称",eName="insuranceName",field="insurance_name")
	private String insuranceName="";
	
	@FieldTag(cName="销售编号",eName="salesOrder",field="sales_order")
	private String salesOrder="";
	
	@FieldTag(cName="公司名称",eName="companyName",field="company_name")
	private String companyName="";
	
	@FieldTag(cName="单位性质",eName="companyType",field="company_type")
	private String companyType="";
	
	@FieldTag(cName="行业分类",eName="industry",field="industry")
	private String industry="";
	
	@FieldTag(cName="公司地址",eName="address",field="address")
	private String address="";
	
	@FieldTag(cName="邮政编码",eName="postalCode",field="postal_code")
	private String postalCode="";
	
	@FieldTag(cName="联系人",eName="contacts",field="contacts")
	private String contacts="";
	
	@FieldTag(cName="电话",eName="telephone",field="telephone")
	private String telephone="";
	
	@FieldTag(cName="传真",eName="faxNumber",field="fax_number")
	private String faxNumber="";
	
	@FieldTag(cName="邮箱",eName="email",field="email")
	private String email="";
	
	@FieldTag(cName="工商注册号",eName="regNum",field="reg_num")
	private String regNum="";
	
	@FieldTag(cName="税务号",eName="taxNum",field="tax_num")
	private String taxNum="";
	
	@FieldTag(cName="公司状态",eName="companyStatus",field="company_status")
	private String companyStatus="";
	
	@FieldTag(cName="备注",eName="remark",field="remark")
	private String remark="";

	@FieldTag(cName="更新时间",eName="updateTime",field="update_time")
	private String updateTime="";
	
	// Constructors
	/** default constructor */
	public InsCompany() {
	}
	/** full constructor */
	
	// Property accessors
	public String getCompanySeq() {
		return companySeq;
	}

	public void setCompanySeq(String companySeq) {
		this.companySeq = companySeq;
	}

	public String getOrganizeSeq() {
		return organizeSeq;
	}

	public void setOrganizeSeq(String organizeSeq) {
		this.organizeSeq = organizeSeq;
	}

	public String getInsuranceCode() {
		return insuranceCode;
	}

	public void setInsuranceCode(String insuranceCode) {
		this.insuranceCode = insuranceCode;
	}

	public String getInsuranceName() {
		return insuranceName;
	}

	public void setInsuranceName(String insuranceName) {
		this.insuranceName = insuranceName;
	}

	public String getSalesOrder() {
		return salesOrder;
	}

	public void setSalesOrder(String salesOrder) {
		this.salesOrder = salesOrder;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyType() {
		return companyType;
	}

	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getFaxNumber() {
		return faxNumber;
	}

	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRegNum() {
		return regNum;
	}

	public void setRegNum(String regNum) {
		this.regNum = regNum;
	}

	public String getTaxNum() {
		return taxNum;
	}

	public void setTaxNum(String taxNum) {
		this.taxNum = taxNum;
	}

	public String getCompanyStatus() {
		return companyStatus;
	}
	public void setCompanyStatus(String companyStatus) {
		this.companyStatus = companyStatus;
	}
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	
}