package com.zhima.traffic.model;

import com.zhima.basic.jdbc.FieldTag;
import com.zhima.basic.jdbc.TableTag;

@TableTag(cName="资质审查",eName="ItsSafecer",tabName="its_safecer")
public class ItsSafecer implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	@FieldTag(cName="资质审查序号",eName="safecerSeq",field="safecer_seq",pk=true)
	private String safecerSeq="";
	
	@FieldTag(cName="机构序号",eName="organizeSeq",field="organize_seq")
	private String organizeSeq="";
	
	@FieldTag(cName="安检序号",eName="safecarSeq",field="safecar_seq")
	private String safecarSeq="";
	
	@FieldTag(cName="车牌号",eName="carNumber",field="car_number")
	private String carNumber="";
	
	@FieldTag(cName="安检驾驶员",eName="safeDriver",field="safe_driver")
	private String safeDriver="";
	
	@FieldTag(cName="安检驾驶员证件号",eName="safeDrivercard",field="safe_drivercard")
	private String safeDrivercard="";
	
	@FieldTag(cName="主驾驶员",eName="mainDirver",field="main_dirver")
	private String mainDirver="";
	
	@FieldTag(cName="主驾驶员姓名",eName="mainDirvername",field="main_dirvername")
	private String mainDirvername="";
	
	@FieldTag(cName="副驾驶员",eName="viceDirver",field="vice_driver")
	private String viceDirver="";
	
	@FieldTag(cName="副驾驶员姓名",eName="viceDirvername",field="vice_dirvername")
	private String viceDirvername="";
	
	@FieldTag(cName="有效期",eName="validTime",field="valid_time")
	private String validTime="";
	
	@FieldTag(cName="车辆安检结果",eName="safeResult",field="safe_result")
	private String safeResult="";
	
	@FieldTag(cName="操作员ID",eName="safeUser",field="safe_user")
	private String safeUser="";
	
	@FieldTag(cName="操作员姓名",eName="safeUsername",field="safe_username")
	private String safeUsername="";
	
	@FieldTag(cName="操作时间",eName="safeTime",field="safe_time")
	private String safeTime="";
	
	@FieldTag(cName="是否报班",eName="ifReport",field="if_report")
	private Integer ifReport=0;
	
	@FieldTag(cName="班次日期",eName="linerDate",field="liner_date")
	private String linerDate="";
	
	@FieldTag(cName="班次号",eName="linerId",field="liner_id")
	private String linerId="";
	
	@FieldTag(cName="安检状态",eName="checkStatus",field="check_status")
	private Integer checkStatus=0;
	
	@FieldTag(cName="备注",eName="remark",field="remark")
	private String remark="";
	
	@FieldTag(cName="修改时间",eName="updateTime",field="update_time")
	private String updateTime="";
	
	// Constructors
	public ItsSafecer() {
	}
	
	// Property accessors
	public String getSafecerSeq() {
		return safecerSeq;
	}

	public void setSafecerSeq(String safecerSeq) {
		this.safecerSeq = safecerSeq;
	}

	public String getOrganizeSeq() {
		return organizeSeq;
	}

	public void setOrganizeSeq(String organizeSeq) {
		this.organizeSeq = organizeSeq;
	}

	public String getSafecarSeq() {
		return safecarSeq;
	}

	public void setSafecarSeq(String safecarSeq) {
		this.safecarSeq = safecarSeq;
	}

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

	public String getSafeDrivercard() {
		return safeDrivercard;
	}

	public void setSafeDrivercard(String safeDrivercard) {
		this.safeDrivercard = safeDrivercard;
	}

	public String getSafeDriver() {
		return safeDriver;
	}

	public void setSafeDriver(String safeDriver) {
		this.safeDriver = safeDriver;
	}

	public String getMainDirver() {
		return mainDirver;
	}

	public void setMainDirver(String mainDirver) {
		this.mainDirver = mainDirver;
	}

	public String getMainDirvername() {
		return mainDirvername;
	}

	public void setMainDirvername(String mainDirvername) {
		this.mainDirvername = mainDirvername;
	}

	public String getViceDirver() {
		return viceDirver;
	}

	public void setViceDirver(String viceDirver) {
		this.viceDirver = viceDirver;
	}

	public String getViceDirvername() {
		return viceDirvername;
	}

	public void setViceDirvername(String viceDirvername) {
		this.viceDirvername = viceDirvername;
	}

	public String getValidTime() {
		return validTime;
	}

	public void setValidTime(String validTime) {
		this.validTime = validTime;
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

	public Integer getIfReport() {
		return ifReport;
	}

	public void setIfReport(Integer ifReport) {
		this.ifReport = ifReport;
	}

	public String getLinerDate() {
		return linerDate;
	}

	public void setLinerDate(String linerDate) {
		this.linerDate = linerDate;
	}

	public String getLinerId() {
		return linerId;
	}

	public void setLinerId(String linerId) {
		this.linerId = linerId;
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