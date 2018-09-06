package com.zhima.traffic.model;

import com.zhima.basic.jdbc.FieldTag;
import com.zhima.basic.jdbc.TableTag;

@TableTag(cName="例检用户表",eName="EpdSafeuser",tabName="epd_safeuser")
public class EpdSafeuser implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	@FieldTag(cName="安检用户序号",eName="safeuserSeq",field="safeuser_seq",pk=true)
	private String safeuserSeq="";
	
	@FieldTag(cName="机构序号",eName="organizeSeq",field="organize_seq")
	private String organizeSeq="";
	
	@FieldTag(cName="用户代码",eName="userCode",field="user_code")
	private String userCode="";
	
	@FieldTag(cName="姓名",eName="userName",field="user_name")
	private String userName="";
	
	@FieldTag(cName="密码",eName="password",field="password")
	private String password="";
	
	@FieldTag(cName="性别",eName="sex",field="sex")
	private String sex="";
	
	@FieldTag(cName="资格证号",eName="cardId",field="card_id")
	private String cardId="";

	@FieldTag(cName="资格证有效期",eName="cardidValid",field="cardid_valid")
	private String cardidValid="";
	
	@FieldTag(cName="身份证号",eName="idNumber",field="id_number")
	private String idNumber="";
	
	@FieldTag(cName="电话",eName="telephone",field="telephone")
	private String telephone="";
	
	@FieldTag(cName="状态",eName="userValid",field="user_valid")
	private String userValid="";
	
	@FieldTag(cName="备注",eName="remark",field="remark")
	private String remark="";
	
	@FieldTag(cName="修改时间",eName="updateTime",field="update_time")
	private String updateTime="";
	
	// Constructors
	public EpdSafeuser() {
	}

	public String getSafeuserSeq() {
		return safeuserSeq;
	}

	public void setSafeuserSeq(String safeuserSeq) {
		this.safeuserSeq = safeuserSeq;
	}

	public String getOrganizeSeq() {
		return organizeSeq;
	}

	public void setOrganizeSeq(String organizeSeq) {
		this.organizeSeq = organizeSeq;
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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getCardidValid() {
		return cardidValid;
	}

	public void setCardidValid(String cardidValid) {
		this.cardidValid = cardidValid;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getUserValid() {
		return userValid;
	}

	public void setUserValid(String userValid) {
		this.userValid = userValid;
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