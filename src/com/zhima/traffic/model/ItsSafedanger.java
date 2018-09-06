package com.zhima.traffic.model;

import com.zhima.basic.jdbc.FieldTag;
import com.zhima.basic.jdbc.TableTag;

@TableTag(cName="危险物品登记",eName="ItsSafedanger",tabName="its_safedanger")
public class ItsSafedanger implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	@FieldTag(cName="三危物品序号",eName="safedangerSeq",field="safedanger_seq",pk=true)
	private String safedangerSeq="";
	
	@FieldTag(cName="机构序号",eName="organizeSeq",field="organize_seq")
	private String organizeSeq="";
	
	@FieldTag(cName="检查日期",eName="safeDate",field="safe_date")
	private String safeDate="";
	
	@FieldTag(cName="旅客姓名",eName="peopleName",field="people_name")
	private String peopleName="";
	
	@FieldTag(cName="证件号码",eName="identityId",field="identity_id")
	private String identityId="";
	
	@FieldTag(cName="到达地",eName="stationName",field="station_name")
	private String stationName="";
	
	@FieldTag(cName="易燃物品",eName="flammable",field="flammable")
	private String flammable="";
	
	@FieldTag(cName="易爆物品",eName="explosive",field="explosive")
	private String explosive="";
	
	@FieldTag(cName="易腐馈物品",eName="corrosion",field="corrosion")
	private String corrosion="";
	
	@FieldTag(cName="其他违禁品",eName="otherGoods",field="other_goods")
	private String otherGoods="";
	
	@FieldTag(cName="处理结果",eName="checkResult",field="check_result")
	private String checkResult="";
	
	@FieldTag(cName="登记时间",eName="safeTime",field="safe_time")
	private String safeTime="";
	
	@FieldTag(cName="移交人",eName="trunName",field="trun_name")
	private String trunName="";
	
	@FieldTag(cName="移交时间",eName="trunTime",field="trun_time")
	private String trunTime="";
		
	@FieldTag(cName="接收人",eName="acceptName",field="accept_name")
	private String acceptName="";
	
	@FieldTag(cName="接收单位",eName="acceptUnit",field="accept_unit")
	private String acceptUnit="";
	
	@FieldTag(cName="备注",eName="remark",field="remark")
	private String remark="";
	
	@FieldTag(cName="修改时间",eName="updateTime",field="update_time")
	private String updateTime="";
	
	// Constructors
	public ItsSafedanger() {
	}

	public String getSafedangerSeq() {
		return safedangerSeq;
	}

	public void setSafedangerSeq(String safedangerSeq) {
		this.safedangerSeq = safedangerSeq;
	}

	public String getOrganizeSeq() {
		return organizeSeq;
	}

	public void setOrganizeSeq(String organizeSeq) {
		this.organizeSeq = organizeSeq;
	}

	public String getSafeDate() {
		return safeDate;
	}

	public void setSafeDate(String safeDate) {
		this.safeDate = safeDate;
	}

	public String getPeopleName() {
		return peopleName;
	}

	public void setPeopleName(String peopleName) {
		this.peopleName = peopleName;
	}

	public String getIdentityId() {
		return identityId;
	}

	public void setIdentityId(String identityId) {
		this.identityId = identityId;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public String getFlammable() {
		return flammable;
	}

	public void setFlammable(String flammable) {
		this.flammable = flammable;
	}

	public String getExplosive() {
		return explosive;
	}

	public void setExplosive(String explosive) {
		this.explosive = explosive;
	}

	public String getCorrosion() {
		return corrosion;
	}

	public void setCorrosion(String corrosion) {
		this.corrosion = corrosion;
	}

	public String getOtherGoods() {
		return otherGoods;
	}

	public void setOtherGoods(String otherGoods) {
		this.otherGoods = otherGoods;
	}

	public String getCheckResult() {
		return checkResult;
	}

	public void setCheckResult(String checkResult) {
		this.checkResult = checkResult;
	}
	
	public String getSafeTime() {
		return safeTime;
	}

	public void setSafeTime(String safeTime) {
		this.safeTime = safeTime;
	}

	public String getTrunName() {
		return trunName;
	}

	public void setTrunName(String trunName) {
		this.trunName = trunName;
	}

	public String getTrunTime() {
		return trunTime;
	}

	public void setTrunTime(String trunTime) {
		this.trunTime = trunTime;
	}

	public String getAcceptName() {
		return acceptName;
	}

	public void setAcceptName(String acceptName) {
		this.acceptName = acceptName;
	}

	public String getAcceptUnit() {
		return acceptUnit;
	}

	public void setAcceptUnit(String acceptUnit) {
		this.acceptUnit = acceptUnit;
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