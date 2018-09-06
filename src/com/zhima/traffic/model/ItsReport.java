package com.zhima.traffic.model;

import com.zhima.basic.jdbc.FieldTag;
import com.zhima.basic.jdbc.TableTag;

@TableTag(cName="报班表",eName="ItsReport",tabName="its_report")
public class ItsReport implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	@FieldTag(cName="报班序号",eName="reportSeq",field="report_seq",pk=true)
	private String reportSeq="";
	
	@FieldTag(cName="机构序号",eName="organizeSeq",field="organize_seq")
	private String organizeSeq="";
	
	@FieldTag(cName="班次日期",eName="linerDate",field="liner_date")
	private String linerDate="";
	
	@FieldTag(cName="班次号",eName="linerId",field="liner_id")
	private String linerId="";
	
	@FieldTag(cName="线路序号",eName="routeSeq",field="route_seq")
	private String routeSeq="";
	
	@FieldTag(cName="线路名称",eName="routeName",field="route_name")
	private String routeName="";

	@FieldTag(cName="车牌号",eName="carNumber",field="car_number")
	private String carNumber="";
	
	@FieldTag(cName="主驾驶",eName="mainDriver",field="main_driver")
	private String mainDriver="";
	
	@FieldTag(cName="主驾驶姓名",eName="mainDrivername",field="main_drivername")
	private String mainDrivername="";
	
	@FieldTag(cName="副驾驶",eName="viceDriver",field="vice_driver")
	private String viceDriver="";
	
	@FieldTag(cName="副驾驶姓名",eName="viceDrivername",field="vice_drivername")
	private String viceDrivername="";
	
	@FieldTag(cName="车辆安检序号",eName="safecarSeq",field="safecar_seq")
	private String safecarSeq="";
	
	@FieldTag(cName="车辆证检序号",eName="safecerSeq",field="safecer_seq")
	private String safecerSeq="";
	
	@FieldTag(cName="报班人",eName="reportUser",field="report_user")
	private String reportUser="";
	
	@FieldTag(cName="报班人姓名",eName="reportUsername",field="report_username")
	private String reportUsername="";
	
	@FieldTag(cName="报班时间",eName="reportTime",field="report_time")
	private String reportTime="";
	
	@FieldTag(cName="备注",eName="remark",field="remark")
	private String remark="";
	
	@FieldTag(cName="修改时间",eName="updateTime",field="update_time")
	private String updateTime="";

	// Constructors

	/** default constructor */
	public ItsReport() {
	}

	/** full constructor */

	// Property accessors
	public String getReportSeq() {
		return reportSeq;
	}

	public void setReportSeq(String reportSeq) {
		this.reportSeq = reportSeq;
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

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

	public String getMainDriver() {
		return mainDriver;
	}

	public void setMainDriver(String mainDriver) {
		this.mainDriver = mainDriver;
	}

	public String getMainDrivername() {
		return mainDrivername;
	}

	public void setMainDrivername(String mainDrivername) {
		this.mainDrivername = mainDrivername;
	}

	public String getViceDriver() {
		return viceDriver;
	}

	public void setViceDriver(String viceDriver) {
		this.viceDriver = viceDriver;
	}

	public String getViceDrivername() {
		return viceDrivername;
	}

	public void setViceDrivername(String viceDrivername) {
		this.viceDrivername = viceDrivername;
	}

	public String getSafecarSeq() {
		return safecarSeq;
	}

	public void setSafecarSeq(String safecarSeq) {
		this.safecarSeq = safecarSeq;
	}

	public String getSafecerSeq() {
		return safecerSeq;
	}

	public void setSafecerSeq(String safecerSeq) {
		this.safecerSeq = safecerSeq;
	}

	public String getReportUser() {
		return reportUser;
	}

	public void setReportUser(String reportUser) {
		this.reportUser = reportUser;
	}

	public String getReportUsername() {
		return reportUsername;
	}

	public void setReportUsername(String reportUsername) {
		this.reportUsername = reportUsername;
	}

	public String getReportTime() {
		return reportTime;
	}

	public void setReportTime(String reportTime) {
		this.reportTime = reportTime;
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