package com.zhima.traffic.model;

import com.zhima.basic.jdbc.FieldTag;
import com.zhima.basic.jdbc.TableTag;

@TableTag(cName="驾驶员",eName="EpdDriver",tabName="epd_driver")
public class EpdDriver implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	@FieldTag(cName="驾驶员序号",eName="driverSeq",field="driver_seq",pk=true)
	private String driverSeq="";
	
	@FieldTag(cName="机构序号",eName="organizeSeq",field="organize_seq")
	private String organizeSeq="";
	
	@FieldTag(cName="驾驶员号",eName="driverCode",field="driver_code")
	private String driverCode="";
	
	@FieldTag(cName="驾驶员姓名",eName="driverName",field="driver_name")
	private String driverName="";
	
	@FieldTag(cName="性别",eName="sex",field="sex")
	private String sex="";

	@FieldTag(cName="身份证",eName="idNumber",field="id_number")
	private String idNumber="";
	
	@FieldTag(cName="联系电话",eName="telephone",field="telephone")
	private String telephone="";
	
	@FieldTag(cName="驾照类型",eName="drivingType",field="driving_type")
	private String drivingType="";
	
	@FieldTag(cName="驾照有效期",eName="drivingValid",field="driving_valid")
	private String drivingValid="";
	
	@FieldTag(cName="从业资格证",eName="permitNumber",field="permit_number")
	private String permitNumber="";
	
	@FieldTag(cName="从业资格证有效期",eName="permitValid",field="permit_valid")
	private String permitValid="";
	
	@FieldTag(cName="所属单位",eName="driverCompany",field="driver_company")
	private String driverCompany="";
	
	@FieldTag(cName="所属单位名称",eName="driverCompanyname",field="driver_companyname")
	private String driverCompanyname="";
	
	@FieldTag(cName="驾驶员图片",eName="driverImage",field="driver_image")
	private String driverImage;
	
	@FieldTag(cName="驾驶员状态",eName="driverStatus",field="driver_status")
	private String driverStatus="";
	
	@FieldTag(cName="备注",eName="remark",field="remark")
	private String remark="";
	
	@FieldTag(cName="修改时间",eName="updateTime",field="update_time")
	private String updateTime="";

	// Constructors

	/** default constructor */
	public EpdDriver() {
	}
	/** full constructor */
	
	// Property accessors

	public String getDriverSeq() {
		return driverSeq;
	}

	public void setDriverSeq(String driverSeq) {
		this.driverSeq = driverSeq;
	}

	public String getOrganizeSeq() {
		return organizeSeq;
	}
	
	public void setOrganizeSeq(String organizeSeq) {
		this.organizeSeq = organizeSeq;
	}
	
	public String getDriverCode() {
		return driverCode;
	}

	public void setDriverCode(String driverCode) {
		this.driverCode = driverCode;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	public String getDrivingType() {
		return drivingType;
	}
	public void setDrivingType(String drivingType) {
		this.drivingType = drivingType;
	}
	public String getDrivingValid() {
		return drivingValid;
	}
	public void setDrivingValid(String drivingValid) {
		this.drivingValid = drivingValid;
	}
	public String getPermitNumber() {
		return permitNumber;
	}
	public void setPermitNumber(String permitNumber) {
		this.permitNumber = permitNumber;
	}
	public String getPermitValid() {
		return permitValid;
	}
	public void setPermitValid(String permitValid) {
		this.permitValid = permitValid;
	}
	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	
	public String getDriverCompany() {
		return driverCompany;
	}
	
	public void setDriverCompany(String driverCompany) {
		this.driverCompany = driverCompany;
	}
	
	public String getDriverCompanyname() {
		return driverCompanyname;
	}
	
	public void setDriverCompanyname(String driverCompanyname) {
		this.driverCompanyname = driverCompanyname;
	}
	
	public String getDriverImage() {
		return driverImage;
	}
	
	public void setDriverImage(String driverImage) {
		this.driverImage = driverImage;
	}
	
	public String getDriverStatus() {
		return driverStatus;
	}
	public void setDriverStatus(String driverStatus) {
		this.driverStatus = driverStatus;
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