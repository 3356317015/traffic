package com.zhima.traffic.model;

import com.zhima.basic.jdbc.FieldTag;
import com.zhima.basic.jdbc.TableTag;

@TableTag(cName="计划站点",eName="EpdPlanstation",tabName="epd_planstation")
public class EpdPlanstation implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	@FieldTag(cName="计划站点序号",eName="planstationSeq",field="planstation_seq",pk=true)
	private String planstationSeq="";
	
	@FieldTag(cName="计划序号",eName="planSeq",field="plan_seq")
	private String planSeq="";

	@FieldTag(cName="计划ID",eName="planId",field="plan_id")
	private String planId="";
	
	@FieldTag(cName="线路序号",eName="routeSeq",field="route_seq")
	private String routeSeq="";
	
	@FieldTag(cName="站点序号",eName="stationSeq",field="station_seq")
	private String stationSeq="";
	
	@FieldTag(cName="站点顺序",eName="stationOrder",field="station_order")
	private Integer stationOrder=0;
	
	@FieldTag(cName="站点是否可售",eName="ifSale",field="if_sale")
	private Integer ifSale=0;
	
	@FieldTag(cName="站点可售票数",eName="stationNum",field="station_num")
	private Integer stationNum=0;
	
	@FieldTag(cName="备注",eName="remark",field="remark")
	private String remark="";
	
	@FieldTag(cName="修改时间",eName="updateTime",field="update_time")
	private String updateTime="";
	
	@FieldTag(cName="线路名称",eName="routeName",field="route_name",db=false)
	private String routeName="";
	
	@FieldTag(cName="站点名称",eName="stationName",field="station_name",db=false)
	private String stationName="";
	
	

	// Constructors

	/** default constructor */
	public EpdPlanstation() {
	}

	/** full constructor */

	// Property accessors
	public String getPlanstationSeq() {
		return planstationSeq;
	}

	public void setPlanstationSeq(String planstationSeq) {
		this.planstationSeq = planstationSeq;
	}

	public String getPlanSeq() {
		return planSeq;
	}

	public void setPlanSeq(String planSeq) {
		this.planSeq = planSeq;
	}

	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}

	public String getRouteSeq() {
		return routeSeq;
	}

	public void setRouteSeq(String routeSeq) {
		this.routeSeq = routeSeq;
	}

	public String getStationSeq() {
		return stationSeq;
	}

	public void setStationSeq(String stationSeq) {
		this.stationSeq = stationSeq;
	}

	public Integer getStationOrder() {
		return stationOrder;
	}

	public void setStationOrder(Integer stationOrder) {
		this.stationOrder = stationOrder;
	}

	public Integer getIfSale() {
		return ifSale;
	}

	public void setIfSale(Integer ifSale) {
		this.ifSale = ifSale;
	}

	public Integer getStationNum() {
		return stationNum;
	}

	public void setStationNum(Integer stationNum) {
		this.stationNum = stationNum;
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

}