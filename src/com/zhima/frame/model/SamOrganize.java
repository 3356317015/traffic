package com.zhima.frame.model;

import com.zhima.basic.jdbc.FieldTag;
import com.zhima.basic.jdbc.TableTag;

@TableTag(cName="客运站信息",eName="SamOrganize",tabName="sam_organize")
public class SamOrganize implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@FieldTag(cName="客运站序号",eName="organizeSeq",field="organize_seq",pk=true)
	private String organizeSeq="";

	@FieldTag(cName="客运站代码",eName="organizeCode",field="organize_code")
	private String organizeCode="";
	
	@FieldTag(cName="拼音代码",eName="organizeSpell",field="organize_spell")
	private String organizeSpell="";
	
	@FieldTag(cName="客运站名称",eName="organizeName",field="organize_name")
	private String organizeName="";
	
	@FieldTag(cName="电话",eName="operateCode",field="operate_code")
	private String operateCode="";
	
	@FieldTag(cName="传真",eName="operateValid",field="operate_valid")
	private String operateValid="";
	
	@FieldTag(cName="客运站级别",eName="organizeLevel",field="organize_level")
	private String organizeLevel="";
	
	@FieldTag(cName="电话",eName="telephone",field="telephone")
	private String telephone="";
	
	@FieldTag(cName="传真",eName="faxNumber",field="fax_number")
	private String faxNumber="";
	
	@FieldTag(cName="邮箱",eName="email",field="email")
	private String email="";
	
	@FieldTag(cName="地址",eName="address",field="address")
	private String address="";
	
	@FieldTag(cName="客运站类型",eName="organizeType",field="organize_type")
	private String organizeType="";
	
	@FieldTag(cName="连接类型",eName="connClass",field="conn_class")
	private String connClass="";
	
	@FieldTag(cName="连接地址",eName="connUrl",field="conn_url")
	private String connUrl="";
	
	@FieldTag(cName="数据库名",eName="connDbname",field="conn_dbname")
	private String connDbname="";
	
	@FieldTag(cName="登录用户",eName="connUser",field="conn_user")
	private String connUser="";
	
	@FieldTag(cName="登录密码",eName="connPassword",field="conn_password")
	private String connPassword="";
	
	@FieldTag(cName="中心接口地址",eName="centerUrl",field="center_url")
	private String centerUrl="";
	
	@FieldTag(cName="状态",eName="status",field="status")
	private String status="";
	
	@FieldTag(cName="备注",eName="remark",field="remark")
	private String remark="";
	
	@FieldTag(cName="创建时间",eName="createTime",field="create_time")
	private String createTime="";

	@FieldTag(cName="更新时间",eName="updateTime",field="update_time")
	private String updateTime="";
	
	@FieldTag(cName="是否销售点",eName="ifService",field="if_service",db=false)
	private String ifService="0";
	
	// Constructors
	/** default constructor */
	public SamOrganize() {
	}
	
	/** full constructor */
	
	// Property accessors
	public String getOrganizeSeq() {
		return organizeSeq;
	}

	public void setOrganizeSeq(String organizeSeq) {
		this.organizeSeq = organizeSeq;
	}

	public String getOrganizeCode() {
		return organizeCode;
	}

	public void setOrganizeCode(String organizeCode) {
		this.organizeCode = organizeCode;
	}

	public String getOrganizeSpell() {
		return organizeSpell;
	}

	public void setOrganizeSpell(String organizeSpell) {
		this.organizeSpell = organizeSpell;
	}

	public String getOrganizeName() {
		return organizeName;
	}

	public void setOrganizeName(String organizeName) {
		this.organizeName = organizeName;
	}

	public String getTelephone() {
		return telephone;
	}

	public String getOperateCode() {
		return operateCode;
	}

	public void setOperateCode(String operateCode) {
		this.operateCode = operateCode;
	}

	public String getOperateValid() {
		return operateValid;
	}

	public void setOperateValid(String operateValid) {
		this.operateValid = operateValid;
	}

	public String getOrganizeLevel() {
		return organizeLevel;
	}

	public void setOrganizeLevel(String organizeLevel) {
		this.organizeLevel = organizeLevel;
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

	public String getOrganizeType() {
		return organizeType;
	}

	public void setOrganizeType(String organizeType) {
		this.organizeType = organizeType;
	}

	public String getConnClass() {
		return connClass;
	}

	public void setConnClass(String connClass) {
		this.connClass = connClass;
	}

	public String getConnUrl() {
		return connUrl;
	}

	public void setConnUrl(String connUrl) {
		this.connUrl = connUrl;
	}

	public String getConnDbname() {
		return connDbname;
	}

	public void setConnDbname(String connDbname) {
		this.connDbname = connDbname;
	}

	public String getConnUser() {
		return connUser;
	}

	public void setConnUser(String connUser) {
		this.connUser = connUser;
	}

	public String getConnPassword() {
		return connPassword;
	}

	public void setConnPassword(String connPassword) {
		this.connPassword = connPassword;
	}

	public String getCenterUrl() {
		return centerUrl;
	}

	public void setCenterUrl(String centerUrl) {
		this.centerUrl = centerUrl;
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

	public String getIfService() {
		return ifService;
	}

	public void setIfService(String ifService) {
		this.ifService = ifService;
	}
	
}