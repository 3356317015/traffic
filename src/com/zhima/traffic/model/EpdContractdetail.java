package com.zhima.traffic.model;

import com.zhima.basic.jdbc.FieldTag;
import com.zhima.basic.jdbc.TableTag;

@TableTag(cName="结算协议明细",eName="EpdContractdetail",tabName="epd_contractdetail")
public class EpdContractdetail implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	@FieldTag(cName="协议明细序号",eName="contractdetailSeq",field="contractdetail_seq",pk=true)
	private String contractdetailSeq="";
	
	@FieldTag(cName="协议序号",eName="contractSeq",field="contract_seq")
	private String contractSeq="";
	
	@FieldTag(cName="费用归属",eName="itemBelong",field="item_belong")
	private String itemBelong="";
	
	@FieldTag(cName="项目代码",eName="itemCode",field="item_code")
	private String itemCode="";
	
	@FieldTag(cName="费用公式",eName="formula",field="formula")
	private String formula="";
	
	@FieldTag(cName="费用公式",eName="formulaDesc",field="formula_desc")
	private String formulaDesc="";

	@FieldTag(cName="费用单位",eName="roundUnit",field="round_unit")
	private String roundUnit="";
	
	@FieldTag(cName="进位方式",eName="carryMode",field="carry_mode")
	private String carryMode="";

	@FieldTag(cName="备注",eName="remark",field="remark")
	private String remark="";
	
	@FieldTag(cName="修改时间",eName="updateTime",field="update_time")
	private String updateTime="";
	
	public EpdContractdetail() {
	}

	public String getContractdetailSeq() {
		return contractdetailSeq;
	}

	public void setContractdetailSeq(String contractdetailSeq) {
		this.contractdetailSeq = contractdetailSeq;
	}

	public String getContractSeq() {
		return contractSeq;
	}

	public void setContractSeq(String contractSeq) {
		this.contractSeq = contractSeq;
	}

	public String getItemBelong() {
		return itemBelong;
	}

	public void setItemBelong(String itemBelong) {
		this.itemBelong = itemBelong;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
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
