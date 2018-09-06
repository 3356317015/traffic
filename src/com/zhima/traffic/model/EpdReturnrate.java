package com.zhima.traffic.model;

import com.zhima.basic.jdbc.FieldTag;
import com.zhima.basic.jdbc.TableTag;

@TableTag(cName="退票费率",eName="EpdReturnrate",tabName="epd_returnrate")
public class EpdReturnrate implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	@FieldTag(cName="退票费率序号",eName="returnrateSeq",field="returnrate_seq",pk=true)
	private String returnrateSeq="";
	
	@FieldTag(cName="机构序号",eName="organizeSeq",field="organize_seq")
	private String organizeSeq="";
	
	@FieldTag(cName="退票费率代码",eName="returnrateCode",field="returnrate_code")
	private String returnrateCode="";
	
	@FieldTag(cName="退标费率名称",eName="returnrateName",field="returnrate_name")
	private String returnrateName="";
	
	@FieldTag(cName="上限时间",eName="timeStart",field="time_start")
	private Integer timeStart=0;
	
	@FieldTag(cName="下限时间",eName="timeLimit",field="time_limit")
	private Integer timeLimit=0;
	
	@FieldTag(cName="退票费率公式",eName="formula",field="formula")
	private String formula="";
	
	@FieldTag(cName="退标费率公式名称",eName="formulaDesc",field="formula_desc")
	private String formulaDesc="";

	@FieldTag(cName="取整单位",eName="roundUnit",field="round_unit")
	private String roundUnit="";
	
	@FieldTag(cName="进位方式",eName="carryMode",field="carry_mode")
	private String carryMode="";
	
	@FieldTag(cName="备注",eName="remark",field="remark")
	private String remark="";
	
	@FieldTag(cName="修改时间",eName="updateTime",field="update_time")
	private String updateTime="";

	// Constructors

	/** default constructor */
	public EpdReturnrate() {
	}
	// Property accessors

	public String getReturnrateSeq() {
		return returnrateSeq;
	}

	public void setReturnrateSeq(String returnrateSeq) {
		this.returnrateSeq = returnrateSeq;
	}

	public String getOrganizeSeq() {
		return organizeSeq;
	}

	public void setOrganizeSeq(String organizeSeq) {
		this.organizeSeq = organizeSeq;
	}

	public String getReturnrateCode() {
		return returnrateCode;
	}

	public void setReturnrateCode(String returnrateCode) {
		this.returnrateCode = returnrateCode;
	}

	public String getReturnrateName() {
		return returnrateName;
	}

	public void setReturnrateName(String returnrateName) {
		this.returnrateName = returnrateName;
	}

	public Integer getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(Integer timeStart) {
		this.timeStart = timeStart;
	}

	public Integer getTimeLimit() {
		return timeLimit;
	}

	public void setTimeLimit(Integer timeLimit) {
		this.timeLimit = timeLimit;
	}

	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}

	public String getFormulaDesc() {
		return formulaDesc;
	}

	public void setFormulaDesc(String formulaDesc) {
		this.formulaDesc = formulaDesc;
	}

	public String getRoundUnit() {
		return roundUnit;
	}

	public void setRoundUnit(String roundUnit) {
		this.roundUnit = roundUnit;
	}

	public String getCarryMode() {
		return carryMode;
	}

	public void setCarryMode(String carryMode) {
		this.carryMode = carryMode;
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