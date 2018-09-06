package com.zhima.traffic.model;

import com.zhima.basic.jdbc.FieldTag;
import com.zhima.basic.jdbc.TableTag;


@TableTag(cName="结算单",eName="ItsAccountbill",tabName="its_accountbill")
public class ItsAccountbill implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	@FieldTag(cName="结算序号",eName="accountbillSeq",field="accountbill_seq",pk=true)
	private String accSeq="";
	
	@FieldTag(cName="机构序号",eName="organizeSeq",field="organize_seq")
	private String organizeSeq="";
	
	@FieldTag(cName="结算单位代码",eName="companyCode",field="company_code")
	private String companyCode="";

	@FieldTag(cName="结算单位名称",eName="companyName",field="company_name")
	private String companyName="";
	
	@FieldTag(cName="车牌号",eName="carNumber",field="car_number")
	private String carNumber="";
	
	@FieldTag(cName="班次日期",eName="linerDate",field="liner_date")
	private String linerDate="";
	
	@FieldTag(cName="班次号",eName="linerId",field="liner_id")
	private String linerId="";

	@FieldTag(cName="线路序号",eName="routeSeq",field="route_seq")
	private String routeSeq="";
	
	@FieldTag(cName="线路名称",eName="routeName",field="route_name")
	private String routeName="";
	
	@FieldTag(cName="报班序号",eName="reportSeq",field="report_seq")
	private String reportSeq="";
	
	@FieldTag(cName="结算单类型",eName="billType",field="bill_type")
	private Integer billType=0;
	
	@FieldTag(cName="座位数",eName="seatNum",field="seat_num")
	private Integer seatNum=0;
	
	@FieldTag(cName="全票数",eName="fullNum",field="full_num")
	private Integer fullNum=0;
	
	@FieldTag(cName="半票数",eName="halfNum",field="half_num")
	private Integer halfNum=0;
	
	@FieldTag(cName="免票数",eName="freeNum",field="free_num")
	private Integer freeNum=0;
	
	@FieldTag(cName="检票数",eName="checkNum",field="check_num")
	private Integer checkNum=0;
	
	@FieldTag(cName="周转量",eName="distanceNum",field="distance_num")
	private Integer distanceNum=0;
	
	@FieldTag(cName="运费",eName="trafficFee",field="traffic_fee")
	private Double trafficFee=0.00;
	
	@FieldTag(cName="站务费",eName="stationFee",field="station_fee")
	private Double stationFee=0.00;
	
	@FieldTag(cName="管理费",eName="manageFee",field="manage_fee")
	private Double manageFee=0.00;
	
	@FieldTag(cName="清洁费",eName="clearFee",field="clear_fee")
	private Double clearFee=0.00;
	
	@FieldTag(cName="停车费",eName="stopcarFee",field="stopcar_fee")
	private Double stopcarFee=0.00;
	
	@FieldTag(cName="安检费",eName="safecarFee",field="safecar_fee")
	private Double safecarFee=0.00;
	
	@FieldTag(cName="出站费",eName="outstationFee",field="outstation_fee")
	private Double outstationFee=0.00;
	
	@FieldTag(cName="罚款",eName="penaltyFee",field="penalty_fee")
	private Double penaltyFee=0.00;
	
	@FieldTag(cName="其他费用1",eName="otherOne",field="other_one")
	private Double otherfeeOne=0.00;
	
	@FieldTag(cName="其他费用2",eName="otherTwo",field="other_two")
	private Double otherTwo=0.00;
	
	@FieldTag(cName="其他费用3",eName="otherThree",field="other_three")
	private Double otherThree=0.00;
	
	@FieldTag(cName="其他费用4",eName="otherFour",field="other_four")
	private Double otherFour=0.00;
	
	@FieldTag(cName="其他费用5",eName="otherFive",field="other_five")
	private Double otherFive=0.00;
	
	@FieldTag(cName="打印人",eName="printUser",field="print_user")
	private String printUser="";
	
	@FieldTag(cName="打印人姓名",eName="printUsername",field="print_username")
	private String printUsername="";
	
	@FieldTag(cName="打印时间",eName="printTime",field="print_time")
	private String printTime="";
	
	@FieldTag(cName="结算单状态",eName="billStatus",field="bill_status")
	private Integer billStatus=0;
	
	@FieldTag(cName="结算号",eName="accountlogSeq",field="accountlog_seq")
	private String accountlogSeq;
	
    @FieldTag(cName="结算人",eName="accountUser",field="account_user")
	private String accountUser="";
    
    @FieldTag(cName="结算人姓名",eName="accountUsername",field="account_username")
	private String accountUsername="";
	
	@FieldTag(cName="结算时间",eName="accountTime",field="account_time")
	private String accountTime="";
	
	@FieldTag(cName="是否出站",eName="ifOutstation",field="if_outstation")
	private Integer ifOutstation=0;
	
	@FieldTag(cName="稽查号",eName="checkCode",field="check_code")
	private String checkCode="";
	
	@FieldTag(cName="稽查状态",eName="checkState",field="check_state")
	private Integer checkState=0;
	
	@FieldTag(cName="备注",eName="remark",field="remark")
	private String remark="";
	
	@FieldTag(cName="修改时间",eName="updateTime",field="update_time")
	private String updateTime="";
	
	// Constructors

	/** default constructor */
	public ItsAccountbill() {
	}
	
	/** full constructor */
	
	
	// Property accessors

	public String getAccSeq() {
		return accSeq;
	}

	public void setAccSeq(String accSeq) {
		this.accSeq = accSeq;
	}

	public String getOrganizeSeq() {
		return organizeSeq;
	}

	public void setOrganizeSeq(String organizeSeq) {
		this.organizeSeq = organizeSeq;
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

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
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

	public String getRouteSeq() {
		return routeSeq;
	}

	public void setRouteSeq(String routeSeq) {
		this.routeSeq = routeSeq;
	}

	public String getRouteName() {
		return routeName;
	}

	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}

	public String getReportSeq() {
		return reportSeq;
	}

	public void setReportSeq(String reportSeq) {
		this.reportSeq = reportSeq;
	}

	public Integer getBillType() {
		return billType;
	}

	public void setBillType(Integer billType) {
		this.billType = billType;
	}

	public Integer getSeatNum() {
		return seatNum;
	}

	public void setSeatNum(Integer seatNum) {
		this.seatNum = seatNum;
	}

	public Integer getFullNum() {
		return fullNum;
	}

	public void setFullNum(Integer fullNum) {
		this.fullNum = fullNum;
	}

	public Integer getHalfNum() {
		return halfNum;
	}

	public void setHalfNum(Integer halfNum) {
		this.halfNum = halfNum;
	}

	public Integer getFreeNum() {
		return freeNum;
	}

	public void setFreeNum(Integer freeNum) {
		this.freeNum = freeNum;
	}

	public Integer getCheckNum() {
		return checkNum;
	}

	public void setCheckNum(Integer checkNum) {
		this.checkNum = checkNum;
	}

	public Integer getDistanceNum() {
		return distanceNum;
	}

	public void setDistanceNum(Integer distanceNum) {
		this.distanceNum = distanceNum;
	}

	public Double getTrafficFee() {
		return trafficFee;
	}

	public void setTrafficFee(Double trafficFee) {
		this.trafficFee = trafficFee;
	}

	public Double getStationFee() {
		return stationFee;
	}

	public void setStationFee(Double stationFee) {
		this.stationFee = stationFee;
	}

	public Double getManageFee() {
		return manageFee;
	}

	public void setManageFee(Double manageFee) {
		this.manageFee = manageFee;
	}

	public Double getClearFee() {
		return clearFee;
	}

	public void setClearFee(Double clearFee) {
		this.clearFee = clearFee;
	}

	public Double getStopcarFee() {
		return stopcarFee;
	}

	public void setStopcarFee(Double stopcarFee) {
		this.stopcarFee = stopcarFee;
	}

	public Double getSafecarFee() {
		return safecarFee;
	}

	public void setSafecarFee(Double safecarFee) {
		this.safecarFee = safecarFee;
	}

	public Double getOutstationFee() {
		return outstationFee;
	}

	public void setOutstationFee(Double outstationFee) {
		this.outstationFee = outstationFee;
	}

	public Double getPenaltyFee() {
		return penaltyFee;
	}

	public void setPenaltyFee(Double penaltyFee) {
		this.penaltyFee = penaltyFee;
	}

	public Double getOtherfeeOne() {
		return otherfeeOne;
	}

	public void setOtherfeeOne(Double otherfeeOne) {
		this.otherfeeOne = otherfeeOne;
	}

	public Double getOtherTwo() {
		return otherTwo;
	}

	public void setOtherTwo(Double otherTwo) {
		this.otherTwo = otherTwo;
	}

	public Double getOtherThree() {
		return otherThree;
	}

	public void setOtherThree(Double otherThree) {
		this.otherThree = otherThree;
	}

	public Double getOtherFour() {
		return otherFour;
	}

	public void setOtherFour(Double otherFour) {
		this.otherFour = otherFour;
	}

	public Double getOtherFive() {
		return otherFive;
	}

	public void setOtherFive(Double otherFive) {
		this.otherFive = otherFive;
	}

	public String getPrintUser() {
		return printUser;
	}

	public void setPrintUser(String printUser) {
		this.printUser = printUser;
	}

	public String getPrintUsername() {
		return printUsername;
	}

	public void setPrintUsername(String printUsername) {
		this.printUsername = printUsername;
	}

	public String getPrintTime() {
		return printTime;
	}

	public void setPrintTime(String printTime) {
		this.printTime = printTime;
	}

	public Integer getBillStatus() {
		return billStatus;
	}

	public void setBillStatus(Integer billStatus) {
		this.billStatus = billStatus;
	}

	public String getAccountlogSeq() {
		return accountlogSeq;
	}

	public void setAccountlogSeq(String accountlogSeq) {
		this.accountlogSeq = accountlogSeq;
	}

	public String getAccountUser() {
		return accountUser;
	}

	public void setAccountUser(String accountUser) {
		this.accountUser = accountUser;
	}

	public String getAccountUsername() {
		return accountUsername;
	}

	public void setAccountUsername(String accountUsername) {
		this.accountUsername = accountUsername;
	}

	public String getAccountTime() {
		return accountTime;
	}

	public void setAccountTime(String accountTime) {
		this.accountTime = accountTime;
	}

	public Integer getIfOutstation() {
		return ifOutstation;
	}

	public void setIfOutstation(Integer ifOutstation) {
		this.ifOutstation = ifOutstation;
	}

	public String getCheckCode() {
		return checkCode;
	}

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}

	public Integer getCheckState() {
		return checkState;
	}

	public void setCheckState(Integer checkState) {
		this.checkState = checkState;
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