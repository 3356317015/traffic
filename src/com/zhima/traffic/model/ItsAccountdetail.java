package com.zhima.traffic.model;

import com.zhima.basic.jdbc.FieldTag;
import com.zhima.basic.jdbc.TableTag;

@TableTag(cName="结算明细",eName="ItsAccountdetail",tabName="its_accountdetail")
public class ItsAccountdetail implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	@FieldTag(cName="结算单明细序号",eName="accdetailSeq",field="accdetail_seq",pk=true)
	private String accdetailSeq="";
	
	@FieldTag(cName="结算单序号",eName="accountbillSeq",field="accountbill_seq")
	private String accountbillSeq="";
	
	@FieldTag(cName="班次日期",eName="linerDate",field="liner_date")
	private String linerDate="";
	
	@FieldTag(cName="班次号",eName="linerId",field="liner_id")
	private String linerId="";
	
	@FieldTag(cName="线路序号",eName="routeSeq",field="route_seq")
	private String routeSeq="";
	
	@FieldTag(cName="线路名称",eName="routeName",field="route_name")
	private String routeName="";
	
	@FieldTag(cName="站点序号",eName="stationSeq",field="station_seq")
	private String stationSeq="";
	
	@FieldTag(cName="站点名称",eName="stationName",field="station_name")
	private String stationName="";
	
	@FieldTag(cName="全票站务费金额",eName="trafficFee",field="traffic_fee")
	private Double trafficFee=0.00;
	
	@FieldTag(cName="站务费",eName="stationFee",field="station_fee")
	private Double stationFee=0.0;
	
	@FieldTag(cName="站务费",eName="manageFee",field="manage_fee")
	private Double manageFee=0.0;
	
	@FieldTag(cName="全票数量",eName="fullNum",field="full_num")
	private Integer fullNum=0;
	//扣除站务费
	@FieldTag(cName="全票金额",eName="fullFare",field="full_fare")
	private Double fullFare=0.0;
	
	@FieldTag(cName="全票站务费金额",eName="fullStationFee",field="full_station_fee")
	private Double fullStationFee=0.00;
	
	@FieldTag(cName="半票数量",eName="halfNum",field="half_num")
	private Integer halfNum=0;
	//扣除站务费
	@FieldTag(cName="半票金额",eName="halfFare",field="half_fare")
	private Double halfFare=0.0;
	
	@FieldTag(cName="半票站务费金额",eName="half_station_fee",field="half_station_fee")
	private Double halfStationFee=0.00;
	
	@FieldTag(cName="免票数",eName="freeNum",field="free_num")
	private Integer freeNum=0;

	@FieldTag(cName="备注",eName="remark",field="remark")
	private String remark="";
	
	@FieldTag(cName="修改时间",eName="updateTime",field="update_time")
	private String updateTime="";

	// Constructors

	/** default constructor */
	public ItsAccountdetail() {
	}
	/** full constructor */

	public String getAccdetailSeq() {
		return accdetailSeq;
	}

	public void setAccdetailSeq(String accdetailSeq) {
		this.accdetailSeq = accdetailSeq;
	}

	public String getAccountbillSeq() {
		return accountbillSeq;
	}

	public void setAccountbillSeq(String accountbillSeq) {
		this.accountbillSeq = accountbillSeq;
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

	public String getStationSeq() {
		return stationSeq;
	}

	public void setStationSeq(String stationSeq) {
		this.stationSeq = stationSeq;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
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

	public Integer getFullNum() {
		return fullNum;
	}

	public void setFullNum(Integer fullNum) {
		this.fullNum = fullNum;
	}

	public Double getFullFare() {
		return fullFare;
	}

	public void setFullFare(Double fullFare) {
		this.fullFare = fullFare;
	}

	public Double getFullStationFee() {
		return fullStationFee;
	}

	public void setFullStationFee(Double fullStationFee) {
		this.fullStationFee = fullStationFee;
	}

	public Integer getHalfNum() {
		return halfNum;
	}

	public void setHalfNum(Integer halfNum) {
		this.halfNum = halfNum;
	}

	public Double getHalfFare() {
		return halfFare;
	}

	public void setHalfFare(Double halfFare) {
		this.halfFare = halfFare;
	}

	public Double getHalfStationFee() {
		return halfStationFee;
	}

	public void setHalfStationFee(Double halfStationFee) {
		this.halfStationFee = halfStationFee;
	}

	public Integer getFreeNum() {
		return freeNum;
	}

	public void setFreeNum(Integer freeNum) {
		this.freeNum = freeNum;
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