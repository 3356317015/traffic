package com.zhima.traffic.model;

import com.zhima.basic.jdbc.FieldTag;
import com.zhima.basic.jdbc.TableTag;

@TableTag(cName="车辆安检",eName="ItsSafecar",tabName="its_safecar")
public class ItsSafecar implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	@FieldTag(cName="车辆安检序号",eName="safecarSeq",field="safecar_seq",pk=true)
	private String safecarSeq="";
	
	@FieldTag(cName="机构序号",eName="organizeSeq",field="organize_seq")
	private String organizeSeq="";
	
	@FieldTag(cName="车牌号",eName="carNumber",field="car_number")
	private String carNumber="";
	
	@FieldTag(cName="安检驾驶员",eName="safeDirver",field="safe_dirver")
	private String safeDirver="";
	
	@FieldTag(cName="安检驾驶员证件号",eName="safeDrivercard",field="safe_drivercard")
	private String safeDrivercard="";
	
	@FieldTag(cName="安检有效期",eName="validTime",field="valid_time")
	private String validTime="";
	
	@FieldTag(cName="车上例检人ID",eName="carupUser",field="carup_user")
	private String carupUser="";
	
	@FieldTag(cName="车上例检人姓名",eName="carupName",field="carup_name")
	private String carupName="";
	
	@FieldTag(cName="车下例检人ID",eName="cardownUser",field="cardown_user")
	private String cardownUser="";
	
	@FieldTag(cName="车下例检人姓名",eName="cardownName",field="cardown_name")
	private String cardownName="";
	
	@FieldTag(cName="车辆安检结果",eName="safeResult",field="safe_result")
	private String safeResult="";
	
	@FieldTag(cName="操作员ID",eName="safeUser",field="safe_user")
	private String safeUser="";
	
	@FieldTag(cName="操作员姓名",eName="safeUsername",field="safe_username")
	private String safeUsername="";
	
	@FieldTag(cName="操作时间",eName="safeTime",field="safe_time")
	private String safeTime="";
	
	@FieldTag(cName="状态",eName="checkStatus",field="check_status")
	private Integer checkStatus=0;
	
	@FieldTag(cName="备注",eName="remark",field="remark")
	private String remark="";
	
	@FieldTag(cName="修改时间",eName="updateTime",field="update_time")
	private String updateTime="";
	
	// Constructors
	public ItsSafecar() {
	}
	
	// Property accessors
	public String getSafecarSeq() {
		return safecarSeq;
	}

	public void setSafecarSeq(String safecarSeq) {
		this.safecarSeq = safecarSeq;
	}

	public String getOrganizeSeq() {
		return organizeSeq;
	}

	public void setOrganizeSeq(String organizeSeq) {
		this.organizeSeq = organizeSeq;
	}

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

	public String getSafeDirver() {
		return safeDirver;
	}

	public void setSafeDirver(String safeDirver) {
		this.safeDirver = safeDirver;
	}

	public String getSafeDrivercard() {
		return safeDrivercard;
	}

	public void setSafeDrivercard(String safeDrivercard) {
		this.safeDrivercard = safeDrivercard;
	}

	public String getValidTime() {
		return validTime;
	}

	public void setValidTime(String validTime) {
		this.validTime = validTime;
	}

	public String getCarupUser() {
		return carupUser;
	}

	public void setCarupUser(String carupUser) {
		this.carupUser = carupUser;
	}

	public String getCarupName() {
		return carupName;
	}

	public void setCarupName(String carupName) {
		this.carupName = carupName;
	}

	public String getCardownUser() {
		return cardownUser;
	}

	public void setCardownUser(String cardownUser) {
		this.cardownUser = cardownUser;
	}

	public String getCardownName() {
		return cardownName;
	}

	public void setCardownName(String cardownName) {
		this.cardownName = cardownName;
	}

	public String getSafeResult() {
		return safeResult;
	}

	public void setSafeResult(String safeResult) {
		this.safeResult = safeResult;
	}

	public String getSafeUser() {
		return safeUser;
	}

	public void setSafeUser(String safeUser) {
		this.safeUser = safeUser;
	}

	public String getSafeUsername() {
		return safeUsername;
	}

	public void setSafeUsername(String safeUsername) {
		this.safeUsername = safeUsername;
	}

	public String getSafeTime() {
		return safeTime;
	}

	public void setSafeTime(String safeTime) {
		this.safeTime = safeTime;
	}

	public Integer getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(Integer checkStatus) {
		this.checkStatus = checkStatus;
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