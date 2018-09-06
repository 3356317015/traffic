package com.zhima.traffic.model;

import com.zhima.basic.jdbc.FieldTag;
import com.zhima.basic.jdbc.TableTag;

@TableTag(cName = "线路", eName = "EpdRoute", tabName = "epd_route")
public class EpdRoute implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	@FieldTag(cName = "线路序号", eName = "routeSeq", field = "route_seq", pk = true)
	private String routeSeq = "";
	
	@FieldTag(cName = "机构序号",eName="organizeSeq",field="organize_seq")
	private String organizeSeq="";

	@FieldTag(cName = "线路名称", eName = "routeName", field = "route_name")
	private String routeName = "";

	@FieldTag(cName = "线路代码", eName = "routeCode", field = "route_code")
	private String routeCode = "";

	@FieldTag(cName = "拼音代码", eName = "routeSpell", field = "route_spell")
	private String routeSpell = "";

	@FieldTag(cName = "线路类型", eName = "routetypeSeq", field = "routetype_seq")
	private String routetypeSeq = "";

	@FieldTag(cName = "线路种类", eName = "roadType", field = "road_type")
	private String roadType = "";

	@FieldTag(cName = "到达方式", eName = "arriveType", field = "arrive_type")
	private String arriveType = "";
	
	@FieldTag(cName = "启用乘车点", eName = "usingService", field = "using_service")
	private Integer usingService = 0;

	@FieldTag(cName = "站点状态", eName = "routeStatus", field = "route_status")
	private String routeStatus = "";

	@FieldTag(cName = "备注", eName = "remark", field = "remark")
	private String remark = "";

	@FieldTag(cName = "修改时间", eName = "updateTime", field = "update_time")
	private String updateTime = "";
	
	@FieldTag(cName = "线路类型名称", eName = "routetypeName", field = "routetype_name",db=false)
	private String routetypeName = "";
	
	@FieldTag(cName = "途经站", eName = "stationName", field = "station_name",db=false)
	private String stationName = "";

	// Constructors

	/** default constructor */
	public EpdRoute() {
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

	public void setRouteCode(String routeCode) {
		this.routeCode = routeCode;
	}

	public String getRouteSpell() {
		return routeSpell;
	}

	public void setRouteSpell(String routeSpell) {
		this.routeSpell = routeSpell;
	}

	public String getRoutetypeSeq() {
		return routetypeSeq;
	}

	public void setRoutetypeSeq(String routetypeSeq) {
		this.routetypeSeq = routetypeSeq;
	}

	public String getRoadType() {
		return roadType;
	}

	public void setRoadType(String roadType) {
		this.roadType = roadType;
	}

	public String getArriveType() {
		return arriveType;
	}

	public void setArriveType(String arriveType) {
		this.arriveType = arriveType;
	}

	public Integer getUsingService() {
		return usingService;
	}

	public void setUsingService(Integer usingService) {
		this.usingService = usingService;
	}

	public String getRouteStatus() {
		return routeStatus;
	}

	public void setRouteStatus(String routeStatus) {
		this.routeStatus = routeStatus;
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

	public String getRoutetypeName() {
		return routetypeName;
	}

	public void setRoutetypeName(String routetypeName) {
		this.routetypeName = routetypeName;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

}