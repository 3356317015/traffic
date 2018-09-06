package com.zhima.frame.model;

import com.zhima.basic.jdbc.FieldTag;
import com.zhima.basic.jdbc.TableTag;

@TableTag(cName="销售点信息",eName="SamService",tabName="sam_service")
public class SamService implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	@FieldTag(cName="销售点序号",eName="serviceSeq",field="service_seq",pk=true)
	private String serviceSeq="";

	@FieldTag(cName="客运站序号",eName="organizeSeq",field="organize_seq")
	private String organizeSeq="";
	
	@FieldTag(cName="销售点代码",eName="serviceCode",field="service_code")
	private String serviceCode="";
	
	@FieldTag(cName="拼音代码",eName="serviceSpell",field="service_spell")
	private String serviceSpell="";
	
	@FieldTag(cName="销售点名称",eName="serviceName",field="service_name")
	private String serviceName="";
	
	@FieldTag(cName="销售类型",eName="saleType",field="sale_type")
	private String saleType="";
	
	@FieldTag(cName="销售权限",eName="saleRight",field="sale_right")
	private String saleRight="";
	
	@FieldTag(cName="电话",eName="telephone",field="telephone")
	private String telephone="";
	
	@FieldTag(cName="传真",eName="faxNumber",field="fax_number")
	private String faxNumber="";
	
	@FieldTag(cName="邮箱",eName="email",field="email")
	private String email="";
	
	@FieldTag(cName="地址",eName="address",field="address")
	private String address="";
	
	@FieldTag(cName="状态",eName="status",field="status")
	private String status="";
	
	@FieldTag(cName="备注",eName="remark",field="remark")
	private String remark="";
	
	@FieldTag(cName="创建时间",eName="createTime",field="create_time")
	private String createTime="";

	@FieldTag(cName="更新时间",eName="updateTime",field="update_time")
	private String updateTime="";
	
	@FieldTag(cName="启用",eName="ifUsing",field="if_using", db=false)
	private String ifUsing="";
	
	@FieldTag(cName="已售数量",eName="saleNum",field="sale_num",db=false)
	private Integer saleNum=0;
	
	@FieldTag(cName="检票数量",eName="checkNum",field="check_num",db=false)
	private Integer checkNum=0;
	
	// Constructors
	/** default constructor */
	public SamService() {
	}
	
	/** full constructor */
	
	// Property accessors
	public String getServiceSeq() {
		return serviceSeq;
	}

	public void setServiceSeq(String serviceSeq) {
		this.serviceSeq = serviceSeq;
	}

	public String getOrganizeSeq() {
		return organizeSeq;
	}

	public void setOrganizeSeq(String organizeSeq) {
		this.organizeSeq = organizeSeq;
	}

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public String getServiceSpell() {
		return serviceSpell;
	}

	public void setServiceSpell(String serviceSpell) {
		this.serviceSpell = serviceSpell;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	
	public String getSaleType() {
		return saleType;
	}

	public void setSaleType(String saleType) {
		this.saleType = saleType;
	}
	
	public String getSaleRight() {
		return saleRight;
	}

	public void setSaleRight(String saleRight) {
		this.saleRight = saleRight;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getIfUsing() {
		return ifUsing;
	}

	public void setIfUsing(String ifUsing) {
		this.ifUsing = ifUsing;
	}

	public Integer getSaleNum() {
		return saleNum;
	}

	public void setSaleNum(Integer saleNum) {
		this.saleNum = saleNum;
	}

	public Integer getCheckNum() {
		return checkNum;
	}

	public void setCheckNum(Integer checkNum) {
		this.checkNum = checkNum;
	}
	
}