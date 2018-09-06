package com.zhima.traffic.model;

import com.zhima.basic.jdbc.FieldTag;
import com.zhima.basic.jdbc.TableTag;

@TableTag(cName="车型等级",eName="EpdCargrade",tabName="epd_cargrade")
public class EpdCargrade implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	@FieldTag(cName="车辆等级序号",eName="cargradeSeq",field="cargrade_seq",pk=true)
	private String cargradeSeq="";
	
	@FieldTag(cName="机构序号",eName="organizeSeq",field="organize_seq")
	private String organizeSeq="";
	
	@FieldTag(cName="车辆等级拼音代码",eName="cargradeCode",field="cargrade_code")
	private String cargradeCode="";
	
	@FieldTag(cName="车辆等级名称",eName="cargradeName",field="cargrade_name")
	private String cargradeName="";
	
	@FieldTag(cName="备注",eName="remark",field="remark")
	private String remark="";
	
	@FieldTag(cName="修改时间",eName="updateTime",field="update_time")
	private String updateTime="";
	// Constructors

	/** default constructor */
	public EpdCargrade() {
	}

	/** full constructor */

	// Property accessors
	public String getCargradeSeq() {
		return cargradeSeq;
	}

	public void setCargradeSeq(String cargradeSeq) {
		this.cargradeSeq = cargradeSeq;
	}

	public String getOrganizeSeq() {
		return organizeSeq;
	}

	public void setOrganizeSeq(String organizeSeq) {
		this.organizeSeq = organizeSeq;
	}

	public String getCargradeCode() {
		return cargradeCode;
	}

	public void setCargradeCode(String cargradeCode) {
		this.cargradeCode = cargradeCode;
	}

	public String getCargradeName() {
		return cargradeName;
	}

	public void setCargradeName(String cargradeName) {
		this.cargradeName = cargradeName;
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