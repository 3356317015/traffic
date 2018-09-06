package com.zhima.traffic.model;

import com.zhima.basic.jdbc.FieldTag;
import com.zhima.basic.jdbc.TableTag;

@TableTag(cName="行政区域",eName="EpdRegion",tabName="epd_region")
public class EpdRegion implements java.io.Serializable {
	private static final long serialVersionUID = 1L;	

	@FieldTag(cName="行政区域序号",eName="regionSeq",field="region_seq",pk=true)
	private String regionSeq="";
	
	@FieldTag(cName="机构序号",eName="organizeSeq",field="organize_seq")
	private String organizeSeq="";
	
	@FieldTag(cName="行政代码",eName="administrationCode",field="administration_code")
	private String administrationCode="";
	
	@FieldTag(cName="拼音代码",eName="regionSpell",field="region_spell")
	private String regionSpell="";
	
	@FieldTag(cName="省(市)",eName="city",field="city")
	private String city="";

	@FieldTag(cName="县",eName="county",field="county")
	private String county="";
	
	@FieldTag(cName="乡(镇)",eName="towns",field="towns")
	private String towns="";
	
	@FieldTag(cName="备注",eName="remark",field="remark")
	private String remark="";

	@FieldTag(cName="更新时间",eName="updateTime",field="update_time")
	private String updateTime="";
	
	// Constructors
	/** default constructor */
	public EpdRegion() {
	}
	
	/** full constructor */
	
	// Property accessors
	

	public String getRegionSeq() {
		return regionSeq;
	}

	public void setRegionSeq(String regionSeq) {
		this.regionSeq = regionSeq;
	}

	public String getOrganizeSeq() {
		return organizeSeq;
	}

	public void setOrganizeSeq(String organizeSeq) {
		this.organizeSeq = organizeSeq;
	}

	public String getAdministrationCode() {
		return administrationCode;
	}

	public void setAdministrationCode(String administrationCode) {
		this.administrationCode = administrationCode;
	}

	public String getRegionSpell() {
		return regionSpell;
	}

	public void setRegionSpell(String regionSpell) {
		this.regionSpell = regionSpell;
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