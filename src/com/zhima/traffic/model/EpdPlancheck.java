package com.zhima.traffic.model;

import com.zhima.basic.jdbc.FieldTag;
import com.zhima.basic.jdbc.TableTag;

@TableTag(cName="计划检票口",eName="EpdPlancheck",tabName="epd_plancheck")
public class EpdPlancheck implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	@FieldTag(cName="计划检票口序号",eName="plancheckSeq",field="plancheck_seq",pk=true)
	private String plancheckSeq="";
	
	@FieldTag(cName="计划序号",eName="planSeq",field="plan_seq")
	private String planSeq="";
	
	@FieldTag(cName="计划ID",eName="planId",field="plan_id")
	private String planId="";
	
	@FieldTag(cName="检票口序号",eName="checkgateSeq",field="checkgate_seq")
	private String checkgateSeq="";

	@FieldTag(cName="备注",eName="remark",field="remark")
	private String remark="";
	
	@FieldTag(cName="修改时间",eName="updateTime",field="update_time")
	private String updateTime="";
	
	@FieldTag(cName="检票口",eName="checkCode",field="check_code",db=false)
	private String checkCode="";
	
	@FieldTag(cName="检票口名称",eName="checkName",field="check_name",db=false)
	private String checkName="";

	// Constructors

	/** default constructor */
	public EpdPlancheck() {
	}
	/** full constructor */

	// Property accessors
	public String getPlancheckSeq() {
		return plancheckSeq;
	}

	public void setPlancheckSeq(String plancheckSeq) {
		this.plancheckSeq = plancheckSeq;
	}

	public String getPlanSeq() {
		return planSeq;
	}
	public void setPlanSeq(String planSeq) {
		this.planSeq = planSeq;
	}
	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}

	public String getCheckgateSeq() {
		return checkgateSeq;
	}
	
	public void setCheckgateSeq(String checkgateSeq) {
		this.checkgateSeq = checkgateSeq;
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
	public String getCheckCode() {
		return checkCode;
	}
	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}
	public String getCheckName() {
		return checkName;
	}
	public void setCheckName(String checkName) {
		this.checkName = checkName;
	}

}