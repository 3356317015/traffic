package com.zhima.traffic.model;

import com.zhima.basic.jdbc.FieldTag;
import com.zhima.basic.jdbc.TableTag;

@TableTag(cName="班次站点",eName="ItsLinerstation",tabName="its_linerstation")
public class ItsLinerstation implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	@FieldTag(cName="班次站点",eName="linerstationSeq",field="linerstation_seq",pk=true)
	private String linerstationSeq="";
	
	@FieldTag(cName="班次序号",eName="linerSeq",field="liner_seq")
	private String linerSeq="";
	
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
	
	@FieldTag(cName="站点顺序",eName="stationOrder",field="station_order")
	private Integer stationOrder=0;
	
	@FieldTag(cName="是否可售",eName="ifSale",field="if_sale")
	private Integer ifSale=0;
	
	@FieldTag(cName="可售数",eName="stationNum",field="station_num")
	private Integer stationNum=0;
	
	@FieldTag(cName="备注",eName="remark",field="remark")
	private String remark="";
	
	@FieldTag(cName="修改时间",eName="updateTime",field="update_time")
	private String updateTime="";
	
	@FieldTag(cName="已售数量",eName="saleNum",field="sale_num",db=false)
	private Integer saleNum=0;
	
	@FieldTag(cName="检票数量",eName="checkNum",field="check_num",db=false)
	private Integer checkNum=0;
	
	@FieldTag(cName="站点距离",eName="stationMileage",field="station_mileage",db=false)
	private Double stationMileage=0.00;
	

	// Constructors

	/** default constructor */
	public ItsLinerstation() {
	}
	/** full constructor */

	// Property accessors
	public String getLinerstationSeq() {
		return linerstationSeq;
	}

	public void setLinerstationSeq(String linerstationSeq) {
		this.linerstationSeq = linerstationSeq;
	}

	public String getLinerSeq() {
		return linerSeq;
	}
	public void setLinerSeq(String linerSeq) {
		this.linerSeq = linerSeq;
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
	public Integer getSaleNum() {
		return saleNum;
	}
	public void setSaleNum(Integer saleNum) {
		this.saleNum = saleNum;
	}
	public Integer getCheckNum() {
		return checkNum;
	}
	public void setCheckNum(Integer checkNum) {
		this.checkNum = checkNum;
	}
	public Double getStationMileage() {
		return stationMileage;
	}
	public void setStationMileage(Double stationMileage) {
		this.stationMileage = stationMileage;
	}

}