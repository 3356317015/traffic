package com.zhima.traffic.model;

import com.zhima.basic.jdbc.FieldTag;
import com.zhima.basic.jdbc.TableTag;

@TableTag(cName="车辆",eName="EpdCar",tabName="epd_car")
public class EpdCar implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	@FieldTag(cName="车辆序号",eName="carSeq",field="car_seq",pk=true)
	private String carSeq="";
	
	@FieldTag(cName="机构序号",eName="organizeSeq",field="organize_seq")
	private String organizeSeq="";
	
	@FieldTag(cName="车辆编号",eName="carCode",field="car_code")
	private String carCode="";
	
	@FieldTag(cName="车牌号",eName="carNumber",field="car_number")
	private String carNumber="";
	
	@FieldTag(cName="卡号",eName="carId",field="car_id")
	private String carId="";
	
	@FieldTag(cName="购车日期",eName="buyDate",field="buy_date")
	private String buyDate="";

	@FieldTag(cName="强保日",eName="checkDays",field="check_days")
	private String checkDays="";
	
	@FieldTag(cName="座位数",eName="seatNum",field="seat_num")
	private Integer seatNum=0;
	
	@FieldTag(cName="准载数",eName="quasiNum",field="quasi_num")
	private Integer quasiNum=0;

	@FieldTag(cName="车辆等级",eName="cargradeSeq",field="cargrade_seq")
	private String cargradeSeq="";
	
	@FieldTag(cName="车辆等级有效期",eName="cargradeValid",field="cargrade_valid")
	private String cargradeValid="";
	
	@FieldTag(cName="运营证号",eName="operateCode",field="operate_code")
	private String operateCode="";
	
	@FieldTag(cName="运营证有效期",eName="operateValid",field="operate_valid")
	private String operateValid="";
	
	@FieldTag(cName="例检有效",eName="safecarValid",field="safecar_valid")
	private Integer safecarValid=0;
	
	@FieldTag(cName="例检类型",eName="safecarType",field="safecar_type")
	private String safecarType="";
	
	@FieldTag(cName="证检有效",eName="safecerValid",field="safecer_valid")
	private Integer safecerValid=0;
	
	@FieldTag(cName="证检类型",eName="safecerType",field="safecer_type")
	private String safecerType="";
	
	@FieldTag(cName="车辆经营线路",eName="routeSeq",field="route_seq")
	private String routeSeq="";
	
	@FieldTag(cName="运营合同序号",eName="contractSeq",field="contract_seq")
	private String contractSeq="";
	
	@FieldTag(cName="结算公司序号",eName="companySeq",field="company_seq")
	private String companySeq="";
	
	@FieldTag(cName="车属公司号",eName="carCompany",field="car_company")
	private String carCompany="";
	
	@FieldTag(cName="车属公司",eName="carCompanyname",field="car_companyname")
	private String carCompanyname="";
	
	@FieldTag(cName="车身图片",eName="carImage",field="car_image")
	private String carImage;

	@FieldTag(cName="车辆状态",eName="carStatus",field="car_status")
	private String carStatus="";
	
	@FieldTag(cName="备注",eName="remark",field="remark")
	private String remark="";
	
	@FieldTag(cName="最后修改时间",eName="updateTime",field="update_time")
	private String updateTime="";
	
	@FieldTag(cName="结算公司代码",eName="safecarLimit",field="safecar_limit",db=false)
	private String safecarLimit="";
	
	@FieldTag(cName="结算公司名称",eName="safecerLimit",field="safecer_limit",db=false)
	private String safecerLimit="";
	
	@FieldTag(cName="线路名称",eName="routeName",field="route_name",db=false)
	private String routeName="";
	
	@FieldTag(cName="车型名称",eName="cargradeName",field="cargrade_name",db=false)
	private String cargradeName="";
	
	@FieldTag(cName="结算公司代码",eName="companyCode",field="company_code",db=false)
	private String companyCode="";
	
	@FieldTag(cName="结算公司名称",eName="companyName",field="company_name",db=false)
	private String companyName="";
	
	@FieldTag(cName="运营合同名称",eName="contractName",field="contract_name",db=false)
	private String contractName="";
	
	@FieldTag(cName="驾驶员",eName="driverName",field="driver_name",db=false)
	private String driverName="";


	// Constructors

	/** default constructor */
	public EpdCar() {
	}

	// Property accessors

	/** full constructor */
	public String getCarSeq() {
		return carSeq;
	}

	public void setCarSeq(String carSeq) {
		this.carSeq = carSeq;
	}

	public String getOrganizeSeq() {
		return organizeSeq;
	}

	public void setOrganizeSeq(String organizeSeq) {
		this.organizeSeq = organizeSeq;
	}

	public String getCarCode() {
		return carCode;
	}

	public void setCarCode(String carCode) {
		this.carCode = carCode;
	}

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

	public String getCarId() {
		return carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}

	public String getBuyDate() {
		return buyDate;
	}

	public void setBuyDate(String buyDate) {
		this.buyDate = buyDate;
	}

	public String getCheckDays() {
		return checkDays;
	}

	public void setCheckDays(String checkDays) {
		this.checkDays = checkDays;
	}

	public Integer getSeatNum() {
		return seatNum;
	}

	public void setSeatNum(Integer seatNum) {
		this.seatNum = seatNum;
	}

	public Integer getQuasiNum() {
		return quasiNum;
	}

	public void setQuasiNum(Integer quasiNum) {
		this.quasiNum = quasiNum;
	}

	public String getCargradeSeq() {
		return cargradeSeq;
	}

	public void setCargradeSeq(String cargradeSeq) {
		this.cargradeSeq = cargradeSeq;
	}

	public String getCargradeValid() {
		return cargradeValid;
	}

	public void setCargradeValid(String cargradeValid) {
		this.cargradeValid = cargradeValid;
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

	public Integer getSafecarValid() {
		return safecarValid;
	}

	public void setSafecarValid(Integer safecarValid) {
		this.safecarValid = safecarValid;
	}

	public String getSafecarType() {
		return safecarType;
	}

	public void setSafecarType(String safecarType) {
		this.safecarType = safecarType;
	}

	public Integer getSafecerValid() {
		return safecerValid;
	}

	public void setSafecerValid(Integer safecerValid) {
		this.safecerValid = safecerValid;
	}

	public String getSafecerType() {
		return safecerType;
	}

	public void setSafecerType(String safecerType) {
		this.safecerType = safecerType;
	}

	public String getRouteSeq() {
		return routeSeq;
	}

	public void setRouteSeq(String routeSeq) {
		this.routeSeq = routeSeq;
	}

	public String getContractSeq() {
		return contractSeq;
	}

	public void setContractSeq(String contractSeq) {
		this.contractSeq = contractSeq;
	}

	public String getCompanySeq() {
		return companySeq;
	}

	public void setCompanySeq(String companySeq) {
		this.companySeq = companySeq;
	}

	public String getCarCompany() {
		return carCompany;
	}

	public void setCarCompany(String carCompany) {
		this.carCompany = carCompany;
	}

	public String getCarCompanyname() {
		return carCompanyname;
	}

	public void setCarCompanyname(String carCompanyname) {
		this.carCompanyname = carCompanyname;
	}

	public String getCarImage() {
		return carImage;
	}

	public void setCarImage(String carImage) {
		this.carImage = carImage;
	}

	public String getCarStatus() {
		return carStatus;
	}

	public void setCarStatus(String carStatus) {
		this.carStatus = carStatus;
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

	public String getSafecarLimit() {
		return safecarLimit;
	}

	public void setSafecarLimit(String safecarLimit) {
		this.safecarLimit = safecarLimit;
	}

	public String getSafecerLimit() {
		return safecerLimit;
	}

	public void setSafecerLimit(String safecerLimit) {
		this.safecerLimit = safecerLimit;
	}

	public String getRouteName() {
		return routeName;
	}

	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}

	public String getCargradeName() {
		return cargradeName;
	}

	public void setCargradeName(String cargradeName) {
		this.cargradeName = cargradeName;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getContractName() {
		return contractName;
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

}