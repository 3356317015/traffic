package com.zhima.frame.model;

import com.zhima.basic.jdbc.FieldTag;
import com.zhima.basic.jdbc.TableTag;

@TableTag(cName="用户信息",eName="SamUser",tabName="sam_user")
public class SamUser implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@FieldTag(cName="用户序号",eName="userSeq",field="user_seq",pk=true)
	private String userSeq="";
	
	@FieldTag(cName="销售点序号",eName="serviceSeq",field="service_seq")
	private String serviceSeq="";
	
	@FieldTag(cName="机构序号",eName="organizeSeq",field="organize_seq")
	private String organizeSeq="";
	
	@FieldTag(cName="用户代码",eName="userCode",field="user_code")
	private String userCode="";
	
	@FieldTag(cName="用户姓名",eName="userName",field="user_name")
	private String userName="";
	
	@FieldTag(cName="密码",eName="password",field="password")
	private String password="";
	
	@FieldTag(cName="电话",eName="telephone",field="telephone")
	private String telephone="";
	
	@FieldTag(cName="邮箱",eName="email",field="email")
	private String email="";
	
	@FieldTag(cName="用户图片",eName="userIcon",field="user_icon")
	private String userIcon;
	
	@FieldTag(cName="状态",eName="status",field="status")
	private String status="";
	
	@FieldTag(cName="用户描述",eName="userDesc",field="user_desc")
	private String userDesc="";

	@FieldTag(cName="备注",eName="remark",field="remark")
	private String remark="";
	
	@FieldTag(cName="创建时间",eName="createTime",field="create_time")
	private String createTime="";
	
	@FieldTag(cName="更新时间",eName="updateTime",field="update_time")
	private String updateTime="";
	
	@FieldTag(cName="销售点名称",eName="serviceName",field="service_name",db=false)
	private String serviceName="";
	
	@FieldTag(cName="服务网点名称",eName="organizeName",field="organize_name", db=false)
	private String organizeName="";
	
	@FieldTag(cName="在线用户序号",eName="onlineSeq",field="online_seq", db=false)
	private String onlineSeq="";
	
	// Constructors
	/** default constructor */
	public SamUser() {
	}
	
	/** full constructor */	

	// Property accessors
	public String getUserSeq() {
		return userSeq;
	}

	public void setUserSeq(String userSeq) {
		this.userSeq = userSeq;
	}
	
	public String getOrganizeSeq() {
		return organizeSeq;
	}

	public void setOrganizeSeq(String organizeSeq) {
		this.organizeSeq = organizeSeq;
	}

	public String getServiceSeq() {
		return serviceSeq;
	}

	public void setServiceSeq(String serviceSeq) {
		this.serviceSeq = serviceSeq;
	}
	
	public String getUserIcon() {
		return userIcon;
	}

	public void setUserIcon(String userIcon) {
		this.userIcon = userIcon;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUserDesc() {
		return userDesc;
	}

	public void setUserDesc(String userDesc) {
		this.userDesc = userDesc;
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

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getOrganizeName() {
		return organizeName;
	}

	public void setOrganizeName(String organizeName) {
		this.organizeName = organizeName;
	}

	public String getOnlineSeq() {
		return onlineSeq;
	}

	public void setOnlineSeq(String onlineSeq) {
		this.onlineSeq = onlineSeq;
	}

}