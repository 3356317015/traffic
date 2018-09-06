package com.zhima.traffic.model;

import com.zhima.basic.jdbc.FieldTag;
import com.zhima.basic.jdbc.TableTag;

@TableTag(cName="线路途径站",eName="EpdRoutestation",tabName="epd_routestation")
public class EpdRoutestation implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	@FieldTag(cName="线路站点序号",eName="routestationSeq",field="routestation_seq",pk=true)
	private String routestationSeq="";
	
	@FieldTag(cName="线路序号",eName="routeSeq",field="route_seq")
	private String routeSeq="";
	
	@FieldTag(cName="发布票价",eName="ifRelease",field="if_release")
	private String ifRelease="";
	
	@FieldTag(cName="站点序号",eName="stationSeq",field="station_seq")
	private String stationSeq="";
	
	@FieldTag(cName="站点顺序",eName="stationOrder",field="station_order")
	private Integer stationOrder=0;
	
	@FieldTag(cName="备注",eName="remark",field="remark")
	private String remark="";
	
	@FieldTag(cName="修改时间",eName="updateTime",field="update_time")
	private String updateTime="";
	
	@FieldTag(cName="站点名称",eName="stationName",field="station_name",db=false)
	private String stationName="";
	
	@FieldTag(cName="站点距离",eName="stationMileage",field="station_mileage",db=false)
	private Double stationMileage=0.00;
	
	@FieldTag(cName="行政区划名称",eName="regionName",field="region_name",db=false)
	private String regionName="";

	
	// Constructors

	/** default constructor */
	public EpdRoutestation() {
	}

	/** full constructor */

	// Property accessors
	public String getStationName() {
		return stationName;
	}

	public String getRoutestationSeq() {
		return routestationSeq;
	}

	public void setRoutestationSeq(String routestationSeq) {
		this.routestationSeq = routestationSeq;
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

	public String getIfRelease() {
		return ifRelease;
	}

	public void setIfRelease(String ifRelease) {
		this.ifRelease = ifRelease;
	}

	public Integer getStationOrder() {
		return stationOrder;
	}

	public void setStationOrder(Integer stationOrder) {
		this.stationOrder = stationOrder;
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

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public Double getStationMileage() {
		return stationMileage;
	}

	public void setStationMileage(Double stationMileage) {
		this.stationMileage = stationMileage;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	

}