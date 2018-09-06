package com.zhima.traffic.model;

import com.zhima.basic.jdbc.FieldTag;
import com.zhima.basic.jdbc.TableTag;

@TableTag(cName="票价比例",eName="EpdFaretype",tabName="epd_faretype")
public class EpdFaretype implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	@FieldTag(cName="票型序号",eName="faretypeSeq",field="faretype_seq",pk=true)
	private String faretypeSeq="";
	
	@FieldTag(cName="机构序号",eName="organizeSeq",field="organize_seq")
	private String organizeSeq="";
	
	@FieldTag(cName="票型代码",eName="faretypeCode",field="faretype_code")
	private String faretypeCode="";
	
	@FieldTag(cName="票型名称",eName="faretypeName",field="faretype_name")
	private String faretypeName="";
	
	@FieldTag(cName="是否带免票",eName="ifHavefree",field="if_havefree")
	private String ifHavefree= "";
	//作为打印结算单的统计依据
	@FieldTag(cName="票型类别",eName="faretypeClass",field="faretype_class")
	private String faretypeClass="";

	@FieldTag(cName="票价公式",eName="formula",field="formula")
	private String formula="";
	
	@FieldTag(cName="票价公式名称",eName="formulaDesc",field="formula_desc")
	private String formulaDesc="";
	
	@FieldTag(cName="取整单位",eName="roundUnit",field="round_unit")
	private String roundUnit="";
	
	@FieldTag(cName="进位方式",eName="carryMode",field="carry_mode")
	private String carryMode="";
	
	@FieldTag(cName="票型状态",eName="faretypeStatus",field="faretype_status")
	private String faretypeStatus="";
	
	@FieldTag(cName="备注",eName="remark",field="remark")
	private String remark="";
	
	@FieldTag(cName="修改时间",eName="updateTime",field="update_time")
	private String updateTime="";

	// Constructors

	/** default constructor */
	public EpdFaretype() {
	}
	
	/** full constructor */
	
	// Property accessors

	public String getFaretypeSeq() {
		return faretypeSeq;
	}

	public void setFaretypeSeq(String faretypeSeq) {
		this.faretypeSeq = faretypeSeq;
	}

	public String getOrganizeSeq() {
		return organizeSeq;
	}

	public void setOrganizeSeq(String organizeSeq) {
		this.organizeSeq = organizeSeq;
	}

	public String getFaretypeCode() {
		return faretypeCode;
	}

	public void setFaretypeCode(String faretypeCode) {
		this.faretypeCode = faretypeCode;
	}

	public String getFaretypeName() {
		return faretypeName;
	}

	public void setFaretypeName(String faretypeName) {
		this.faretypeName = faretypeName;
	}

	public String getIfHavefree() {
		return ifHavefree;
	}

	public void setIfHavefree(String ifHavefree) {
		this.ifHavefree = ifHavefree;
	}

	public String getFaretypeClass() {
		return faretypeClass;
	}

	public void setFaretypeClass(String faretypeClass) {
		this.faretypeClass = faretypeClass;
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

	public String getFaretypeStatus() {
		return faretypeStatus;
	}

	public void setFaretypeStatus(String faretypeStatus) {
		this.faretypeStatus = faretypeStatus;
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