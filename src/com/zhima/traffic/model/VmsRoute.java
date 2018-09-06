package com.zhima.traffic.model;

import com.zhima.basic.jdbc.FieldTag;
import com.zhima.basic.jdbc.TableTag;

@TableTag(cName = "播音线路", eName = "VmsRoute", tabName = "vms_route")
public class VmsRoute implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	@FieldTag(cName = "线路序号", eName = "routeSeq", field = "route_seq", pk = true)
	private String routeSeq = "";
	
	@FieldTag(cName = "机构序号",eName="organizeSeq",field="organize_seq")
	private String organizeSeq="";

	@FieldTag(cName = "线路名称", eName = "routeName", field = "route_name")
	private String routeName = "";

	@FieldTag(cName = "线路代码", eName = "routeCode", field = "route_code")
	private String routeCode = "";
	
	@FieldTag(cName = "途径站", eName = "stationName", field = "station_name")
	private String stationName = "";
	
	@FieldTag(cName = "是否播音", eName = "voiceStatus", field = "voice_status")
	private String voiceStatus = "";

	@FieldTag(cName = "备注", eName = "remark", field = "remark")
	private String remark = "";

	@FieldTag(cName = "修改时间", eName = "updateTime", field = "update_time")
	private String updateTime = "";
	
	// Constructors

	/** default constructor */
	public VmsRoute() {
	}

	/** full constructor */

	// Property accessors
	
	public String getRouteSeq() {
		return routeSeq;
	}

	public void setRouteSeq(String routeSeq) {
		this.routeSeq = routeSeq;
	}

	public String getOrganizeSeq() {
		return organizeSeq;
	}

	public void setOrganizeSeq(String organizeSeq) {
		this.organizeSeq = organizeSeq;
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

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public void setRouteCode(String routeCode) {
		this.routeCode = routeCode;
	}

	public String getVoiceStatus() {
		return voiceStatus;
	}

	public void setVoiceStatus(String voiceStatus) {
		this.voiceStatus = voiceStatus;
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