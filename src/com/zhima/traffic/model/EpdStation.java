package com.zhima.traffic.model;

import com.zhima.basic.jdbc.FieldTag;
import com.zhima.basic.jdbc.TableTag;

@TableTag(cName="站点",eName="EpdStation",tabName="epd_station")
public class EpdStation implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	@FieldTag(cName="站点序号",eName="stationSeq",field="station_seq",pk=true)
	private String stationSeq="";
	
	@FieldTag(cName="机构序号",eName="organizeSeq",field="organize_seq")
	private String organizeSeq="";
	
	@FieldTag(cName="站点名称",eName="stationName",field="station_name")
	private String stationName="";
	
	@FieldTag(cName="站点数字代码",eName="stationCode",field="station_code")
	private String stationCode="";
	
	@FieldTag(cName="站点拼音代码",eName="stationSpell",field="station_spell")
	private String stationSpell="";
	
	@FieldTag(cName="站点距离",eName="stationMileage",field="station_mileage")
	private Double stationMileage=0.00;

	@FieldTag(cName="站点状态",eName="stationStatus",field="station_status")
	private String stationStatus="";
	
	@FieldTag(cName="行政区划序号",eName="regionSeq",field="region_seq")
	private String regionSeq="";
	
	@FieldTag(cName="备注",eName="remark",field="remark")
	private String remark="";
	
	@FieldTag(cName="修改时间",eName="updateTime",field="update_time")
	private String updateTime="";
	
	@FieldTag(cName="行政区划名称",eName="regionName",field="region_name",db=false)
	private String regionName="";
	
	@FieldTag(cName="省(市)",eName="city",field="city",db=false)
	private String city="";

	@FieldTag(cName="县",eName="county",field="county",db=false)
	private String county="";
	
	@FieldTag(cName="乡(镇)",eName="towns",field="towns",db=false)
	private String towns="";

	public String getStationSeq() {
		return stationSeq;
	}

	public void setStationSeq(String stationSeq) {
		this.stationSeq = stationSeq;
	}

	public String getOrganizeSeq() {
		return organizeSeq;
	}

	public void setOrganizeSeq(String organizeSeq) {
		this.organizeSeq = organizeSeq;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public String getStationCode() {
		return stationCode;
	}

	public void setStationCode(String stationCode) {
		this.stationCode = stationCode;
	}

	public String getStationSpell() {
		return stationSpell;
	}

	public void setStationSpell(String stationSpell) {
		this.stationSpell = stationSpell;
	}

	public Double getStationMileage() {
		return stationMileage;
	}

	public void setStationMileage(Double stationMileage) {
		this.stationMileage = stationMileage;
	}

	public String getStationStatus() {
		return stationStatus;
	}

	public void setStationStatus(String stationStatus) {
		this.stationStatus = stationStatus;
	}

	public String getRegionSeq() {
		return regionSeq;
	}

	public void setRegionSeq(String regionSeq) {
		this.regionSeq = regionSeq;
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

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getTowns() {
		return towns;
	}

	public void setTowns(String towns) {
		this.towns = towns;
	}
	
}