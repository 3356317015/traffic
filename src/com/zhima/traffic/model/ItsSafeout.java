package com.zhima.traffic.model;

import com.zhima.basic.jdbc.FieldTag;
import com.zhima.basic.jdbc.TableTag;

@TableTag(cName="出站表",eName="ItsSafeout",tabName="its_safeout")
public class ItsSafeout implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	@FieldTag(cName="出站序号",eName="safeoutSeq",field="safeout_seq",pk=true)
	private String safeoutSeq="";
	
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
	
	@FieldTag(cName="驾驶员",eName="driverName",field="driver_name")
	private String driverName="";
	
	@FieldTag(cName="结算单序号",eName="accbillSeq",field="accbill_seq")
	private String accbillSeq="";
	
	@FieldTag(cName="天气",eName="weather",field="weather")
	private String weather="";
	
	@FieldTag(cName="全票数",eName="fullNum",field="full_num")
	private Integer fullNum=0;
	
	@FieldTag(cName="半票数",eName="halfNum",field="half_num")
	private Integer halfNum=0;
	
	@FieldTag(cName="免票数",eName="freeNum",field="free_num")
	private Integer freeNum=0;
	
	@FieldTag(cName="实载数",eName="factNum",field="fact_num")
	private Integer factNum=0;
	
	@FieldTag(cName="出站状态",eName="checkStatus",field="check_status")
	private Integer checkStatus=0;
	
	@FieldTag(cName="操作员ID",eName="safeUser",field="safe_user")
	private String safeUser="";
	
	@FieldTag(cName="操作员姓名",eName="safeName",field="safe_name")
	private String safeName="";
	
	@FieldTag(cName="操作时间",eName="safeTime",field="safe_time")
	private String safeTime="";
	
	@FieldTag(cName="备注",eName="remark",field="remark")
	private String remark="";
	
	@FieldTag(cName="修改时间",eName="updateTime",field="update_time")
	private String updateTime;

	// Constructors
	

	/** default constructor */
	public ItsSafeout() {
	}
	/** full constructor */

	// Property accessors
	public String getSafeoutSeq() {
		return safeoutSeq;
	}

	public void setSafeoutSeq(String safeoutSeq) {
		this.safeoutSeq = safeoutSeq;
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
	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getAccbillSeq() {
		return accbillSeq;
	}

	public void setAccbillSeq(String accbillSeq) {
		this.accbillSeq = accbillSeq;
	}

	public String getWeather() {
		return weather;
	}

	public void setWeather(String weather) {
		this.weather = weather;
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

	public Integer getFactNum() {
		return factNum;
	}

	public void setFactNum(Integer factNum) {
		this.factNum = factNum;
	}

	public Integer getCheckStatus() {
		return checkStatus;
	}
	
	public void setCheckStatus(Integer checkStatus) {
		this.checkStatus = checkStatus;
	}
	public String getSafeUser() {
		return safeUser;
	}

	public void setSafeUser(String safeUser) {
		this.safeUser = safeUser;
	}

	public String getSafeName() {
		return safeName;
	}

	public void setSafeName(String safeName) {
		this.safeName = safeName;
	}

	public String getSafeTime() {
		return safeTime;
	}

	public void setSafeTime(String safeTime) {
		this.safeTime = safeTime;
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