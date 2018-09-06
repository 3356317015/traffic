package com.zhima.traffic.model;

import com.zhima.basic.jdbc.FieldTag;
import com.zhima.basic.jdbc.TableTag;

@TableTag(cName="基础价格表",eName="EpdFare",tabName="epd_fare")
public class EpdFare implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	@FieldTag(cName="票价序号",eName="fareSeq",field="fare_seq",pk=true)
	private String fareSeq="";
	
	@FieldTag(cName="机构序号",eName="organizeSeq",field="organize_seq")
	private String organizeSeq="";
	
	@FieldTag(cName="线路序号",eName="routeSeq",field="route_seq")
	private String routeSeq="";
	
	@FieldTag(cName="站点序号",eName="stationSeq",field="station_seq")
	private String stationSeq="";
	
	@FieldTag(cName="车型序号",eName="cargradeSeq",field="cargrade_seq")
	private String cargradeSeq="";
	
	@FieldTag(cName="基础价",eName="baseFare",field="base_fare")
	private Double baseFare=0.0;
	
	@FieldTag(cName="站务费",eName="stationFare",field="station_fare")
	private Double stationFare=0.0;
	
	@FieldTag(cName="燃油费",eName="fuelFare",field="fuel_fare")
	private Double fuelFare=0.0;
	
	@FieldTag(cName="其他费用1",eName="otherOne",field="other_one")
	private Double otherOne=0.0;
	
	@FieldTag(cName="其他费用2",eName="otherTwo",field="other_two")
	private Double otherTwo=0.0;
	
	@FieldTag(cName="其他费用3",eName="otherThree",field="other_three")
	private Double otherThree=0.0;
	
	@FieldTag(cName="其他费用4",eName="otherFour",field="other_four")
	private Double otherFour=0.0;
	
	@FieldTag(cName="其他费用5",eName="otherFive",field="other_five")
	private Double otherFive=0.0;
	
	@FieldTag(cName="全价",eName="fullFare",field="full_fare")
	private Double fullFare=0.0;
	
	@FieldTag(cName="备注",eName="remark",field="remark")
	private String remark="";
	
	@FieldTag(cName="修改时间",eName="updateTime",field="update_time")
	private String updateTime="";
	
	@FieldTag(cName = "线路名称", eName = "routeName", field = "route_name", db = false)
	private String routeName = "";

	@FieldTag(cName = "线路代码", eName = "routeCode", field = "route_code", db = false)
	private String routeCode = "";

	@FieldTag(cName = "拼音代码", eName = "routeSpell", field = "route_spell", db = false)
	private String routeSpell = "";
	
	@FieldTag(cName="站点名称",eName="stationName",field="station_name", db = false)
	private String stationName="";
	
	@FieldTag(cName="车辆等级名称",eName="cargradeName",field="cargrade_name", db = false)
	private String cargradeName="";
	
	@FieldTag(cName="发布票价",eName="ifRelease",field="if_release", db = false)
	private String ifRelease="";
	
	// Constructors

	/** default constructor */
	public EpdFare() {
	}
	
	/** full constructor */

	// Property accessors

	public String getFareSeq() {
		return fareSeq;
	}


	public void setFareSeq(String fareSeq) {
		this.fareSeq = fareSeq;
	}


	public String getOrganizeSeq() {
		return organizeSeq;
	}

	public void setOrganizeSeq(String organizeSeq) {
		this.organizeSeq = organizeSeq;
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


	public String getCargradeSeq() {
		return cargradeSeq;
	}


	public void setCargradeSeq(String cargradeSeq) {
		this.cargradeSeq = cargradeSeq;
	}


	public Double getBaseFare() {
		return baseFare;
	}


	public void setBaseFare(Double baseFare) {
		this.baseFare = baseFare;
	}


	public Double getStationFare() {
		return stationFare;
	}


	public void setStationFare(Double stationFare) {
		this.stationFare = stationFare;
	}


	public Double getFuelFare() {
		return fuelFare;
	}


	public void setFuelFare(Double fuelFare) {
		this.fuelFare = fuelFare;
	}


	public Double getOtherOne() {
		return otherOne;
	}


	public void setOtherOne(Double otherOne) {
		this.otherOne = otherOne;
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


	public Double getFullFare() {
		return fullFare;
	}


	public void setFullFare(Double fullFare) {
		this.fullFare = fullFare;
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

	public String getRouteCode() {
		return routeCode;
	}

	public void setRouteCode(String routeCode) {
		this.routeCode = routeCode;
	}

	public String getRouteSpell() {
		return routeSpell;
	}

	public void setRouteSpell(String routeSpell) {
		this.routeSpell = routeSpell;
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

	public String getIfRelease() {
		return ifRelease;
	}

	public void setIfRelease(String ifRelease) {
		this.ifRelease = ifRelease;
	}
	
}