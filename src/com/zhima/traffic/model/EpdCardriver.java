package com.zhima.traffic.model;

import com.zhima.basic.jdbc.FieldTag;
import com.zhima.basic.jdbc.TableTag;

@TableTag(cName="车辆驾驶",eName="EpdCardriver",tabName="epd_cardriver")
public class EpdCardriver implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	@FieldTag(cName="车辆驾驶序号",eName="cardriverSeq",field="cardriver_seq",pk=true)
	private String cardriverSeq="";
	
	@FieldTag(cName="车辆序号",eName="carSeq",field="car_seq")
	private String carSeq="";
	
	@FieldTag(cName="驾驶员序号",eName="driverSeq",field="driver_seq")
	private String driverSeq="";
	
	@FieldTag(cName="驾驶员顺序",eName="driverOrder",field="driver_order")
	private Integer driverOrder=0;
	
	@FieldTag(cName="备注",eName="remark",field="remark")
	private String remark="";
	
	@FieldTag(cName="修改时间",eName="updateTime",field="update_time")
	private String updateTime="";
	
	@FieldTag(cName="驾驶员号",eName="driverCode",field="driver_code",db=false)
	private String driverCode="";
	
	@FieldTag(cName="驾驶员姓名",eName="driverName",field="driver_name",db=false)
	private String driverName="";
	
	@FieldTag(cName="性别",eName="sex",field="sex",db=false)
	private String sex="";

	@FieldTag(cName="身份证",eName="idNumber",field="id_number",db=false)
	private String idNumber="";
	
	@FieldTag(cName="联系电话",eName="telephone",field="telephone",db=false)
	private String telephone="";
	
	@FieldTag(cName="驾照类型",eName="drivingType",field="driving_type",db=false)
	private String drivingType="";
	
	@FieldTag(cName="驾照有效期",eName="drivingValid",field="driving_valid",db=false)
	private String drivingValid="";

	@FieldTag(cName="所属单位名称",eName="driverCompanyname",field="driver_companyname",db=false)
	private String driverCompanyname="";
	
	@FieldTag(cName="驾驶员状态",eName="driverStatus",field="driver_status",db=false)
	private String driverStatus="";
	


	// Constructors

	/** default constructor */
	public EpdCardriver() {
	}

	/** full constructor */

	// Property accessors

	public String getCardriverSeq() {
		return this.cardriverSeq;
	}

	public void setCardriverSeq(String cardriverSeq) {
		this.cardriverSeq = cardriverSeq;
	}

	public String getCarSeq() {
		return this.carSeq;
	}

	public void setCarSeq(String carSeq) {
		this.carSeq = carSeq;
	}

	public String getDriverSeq() {
		return this.driverSeq;
	}

	public void setDriverSeq(String driverSeq) {
		this.driverSeq = driverSeq;
	}

	public Integer getDriverOrder() {
		return this.driverOrder;
	}

	public void setDriverOrder(Integer driverOrder) {
		this.driverOrder = driverOrder;
	}

	public String getRemark() {
		return this.remark;
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

	public String getDriverCompanyname() {
		return driverCompanyname;
	}

	public void setDriverCompanyname(String driverCompanyname) {
		this.driverCompanyname = driverCompanyname;
	}

	public String getDriverStatus() {
		return driverStatus;
	}

	public void setDriverStatus(String driverStatus) {
		this.driverStatus = driverStatus;
	}

}