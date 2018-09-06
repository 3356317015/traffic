package com.zhima.traffic.model;

import com.zhima.basic.jdbc.FieldTag;
import com.zhima.basic.jdbc.TableTag;

@TableTag(cName="结算协议",eName="EpdContract",tabName="epd_contract")
public class EpdContract implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	@FieldTag(cName="协议序号",eName="contractSeq",field="contract_seq",pk=true)
	private String contractSeq="";
	
	@FieldTag(cName="机构序号",eName="organizeSeq",field="organize_seq")
	private String organizeSeq="";
	
	@FieldTag(cName="协议代码",eName="contractCode",field="contract_code")
	private String contractCode="";
	
	@FieldTag(cName="协议名称",eName="contractName",field="contract_name")
	private String contractName="";
	
	@FieldTag(cName="备注",eName="remark",field="remark")
	private String remark="";
	
	@FieldTag(cName="修改时间",eName="updateTime",field="update_time")
	private String updateTime="";
	
	// Constructors

	/** default constructor */
	public EpdContract() {
	}

	/** full constructor */
	
	// Property accessors
	
	public String getContractSeq() {
		return contractSeq;
	}

	public void setContractSeq(String contractSeq) {
		this.contractSeq = contractSeq;
	}

	public String getOrganizeSeq() {
		return organizeSeq;
	}

	public void setOrganizeSeq(String organizeSeq) {
		this.organizeSeq = organizeSeq;
	}

	public String getContractCode() {
		return contractCode;
	}

	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}

	public String getContractName() {
		return contractName;
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
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