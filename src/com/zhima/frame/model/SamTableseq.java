package com.zhima.frame.model;

import com.zhima.basic.jdbc.FieldTag;
import com.zhima.basic.jdbc.TableTag;

@TableTag(cName="表主键管理",eName="SamTableseq",tabName="sam_tableseq")
public class SamTableseq implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	@FieldTag(cName="机构序号",eName="organizeSeq",field="organize_seq",pk=true)
	private String organizeSeq="";
	
	@FieldTag(cName="表名称",eName="tableName",field="table_name")
	private String tableName="";

	@FieldTag(cName="规则",eName="seqRules",field="seq_rules")
	private String seqRules="";
	
	@FieldTag(cName="长度",eName="seqLen",field="seq_len")
	private Integer seqLen=0;
	
	@FieldTag(cName="序号值",eName="seqValue",field="seq_value")
	private String seqValue="";
	
	@FieldTag(cName="备注",eName="remark",field="remark")
	private String remark="";
	
	@FieldTag(cName="创建时间",eName="createTime",field="create_time")
	private String createTime="";
	
	@FieldTag(cName="更新时间",eName="updateTime",field="update_time")
	private String updateTime="";

	// Constructors

	/** default constructor */
	public SamTableseq() {
	}

	
	/** full constructor */

	// Property accessors
	public String getOrganizeSeq() {
		return organizeSeq;
	}

	public void setOrganizeSeq(String organizeSeq) {
		this.organizeSeq = organizeSeq;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getSeqRules() {
		return seqRules;
	}

	public void setSeqRules(String seqRules) {
		this.seqRules = seqRules;
	}

	public Integer getSeqLen() {
		return seqLen;
	}

	public void setSeqLen(Integer seqLen) {
		this.seqLen = seqLen;
	}

	public String getSeqValue() {
		return seqValue;
	}

	public void setSeqValue(String seqValue) {
		this.seqValue = seqValue;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

}