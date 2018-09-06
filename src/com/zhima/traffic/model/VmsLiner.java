package com.zhima.traffic.model;

import com.zhima.basic.jdbc.FieldTag;
import com.zhima.basic.jdbc.TableTag;

@TableTag(cName = "播音班次", eName = "VmsLiner" ,tabName="vms_liner")
public class VmsLiner implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	@FieldTag(cName = "班次序号", eName = "linerSeq",field="liner_seq",pk=true)
	private String linerSeq="";
	
	@FieldTag(cName="机构序号",eName="organizeSeq",field="organize_seq")
	private String organizeSeq="";
	
	@FieldTag(cName = "班次日期", eName = "linerDate",field="liner_date")
	private String linerDate="";
	
	@FieldTag(cName = "日班次号", eName = "linerId",field="liner_id")
	private String linerId="";

	@FieldTag(cName = "发车时间", eName = "linerTime",field="liner_time")
	private String linerTime="";
	
	@FieldTag(cName = "班次类型", eName = "linerType",field="liner_type")
	private String linerType="";

	@FieldTag(cName = "线路代码", eName = "routeCode", field = "route_code")
	private String routeCode = "";
	
	@FieldTag(cName = "线路名称", eName = "routeName",field="route_name")
	private String routeName="";
	
	@FieldTag(cName = "途经站", eName = "stationName", field = "station_name")
	private String stationName = "";

	@FieldTag(cName = "车型", eName = "cargradeName",field="cargrade_name")
	private String cargradeName="";
	
	@FieldTag(cName = "检票口", eName = "checkCode",field="check_code")
	private String checkCode="";
	
	@FieldTag(cName = "登车口", eName = "parkingCode",field="parking_code")
	private String parkingCode="";
	
	@FieldTag(cName = "班次状态", eName = "linerStatus",field="liner_status")
	private String linerStatus="";
	
	@FieldTag(cName = "报班状态", eName = "reportStatus",field="report_status")
	private String reportStatus="";
	
	@FieldTag(cName = "打单状态", eName = "printbillStatus",field="printbill_status")
	private String printbillStatus="";

	@FieldTag(cName = "报班车辆", eName = "carNumber",field="car_number")
	private String carNumber="";
	
	@FieldTag(cName = "备注", eName = "remark",field="remark")
	private String remark="";

	@FieldTag(cName = "修改时间", eName = "updateTime",field="update_time")
	private String updateTime="";
	

	/** default constructor */
	
	/** full constructor */

	// Property accessors
	public VmsLiner() {
	}


	public String getLinerSeq() {
		return linerSeq;
	}


	public void setLinerSeq(String linerSeq) {
		this.linerSeq = linerSeq;
	}


	public String getOrganizeSeq() {
		return organizeSeq;
	}


	public void setOrganizeSeq(String organizeSeq) {
		this.organizeSeq = organizeSeq;
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


	public String getLinerTime() {
		return linerTime;
	}


	public void setLinerTime(String linerTime) {
		this.linerTime = linerTime;
	}


	public String getLinerType() {
		return linerType;
	}


	public void setLinerType(String linerType) {
		this.linerType = linerType;
	}


	public String getRouteCode() {
		return routeCode;
	}


	public void setRouteCode(String routeCode) {
		this.routeCode = routeCode;
	}


	public String getRouteName() {
		return routeName;
	}


	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}


	public String getStationName() {
		return stationName;
	}


	public void setStationName(String stationName) {
		this.stationName = stationName;
	}


	public String getCargradeName() {
		return cargradeName;
	}


	public void setCargradeName(String cargradeName) {
		this.cargradeName = cargradeName;
	}


	public String getCheckCode() {
		return checkCode;
	}


	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}


	public String getParkingCode() {
		return parkingCode;
	}


	public void setParkingCode(String parkingCode) {
		this.parkingCode = parkingCode;
	}


	public String getLinerStatus() {
		return linerStatus;
	}


	public void setLinerStatus(String linerStatus) {
		this.linerStatus = linerStatus;
	}

	public String getReportStatus() {
		return reportStatus;
	}


	public void setReportStatus(String reportStatus) {
		this.reportStatus = reportStatus;
	}


	public String getPrintbillStatus() {
		return printbillStatus;
	}


	public void setPrintbillStatus(String printbillStatus) {
		this.printbillStatus = printbillStatus;
	}


	public String getCarNumber() {
		return carNumber;
	}


	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
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