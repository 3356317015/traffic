package com.zhima.traffic.model;

import com.zhima.basic.jdbc.FieldTag;
import com.zhima.basic.jdbc.TableTag;

@TableTag(cName="计划运营车辆",eName="EpdPlancar",tabName="epd_plancar")
public class EpdPlancar implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	@FieldTag(cName="计划运力序号",eName="plancarSeq",field="plancar_seq",pk=true)
	private String plancarSeq="";
	
	@FieldTag(cName="计划序号",eName="planSeq",field="plan_seq")
	private String planSeq="";
	
	@FieldTag(cName="计划ID",eName="planId",field="plan_id")
	private String planId="";

	@FieldTag(cName="车牌号",eName="carNumber",field="car_number")
	private String carNumber="";
	
	@FieldTag(cName="备注",eName="remark",field="remark")
	private String remark="";
	
	@FieldTag(cName="修改时间",eName="updateTime",field="update_time")
	private String updateTime="";

	// Constructors

	/** default constructor */
	public EpdPlancar() {
	}
	/** full constructor */

	// Property accessors


	public String getPlancarSeq() {
		return plancarSeq;
	}
	public void setPlancarSeq(String plancarSeq) {
		this.plancarSeq = plancarSeq;
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

	public String getCarNumber() {
		return carNumber;
	}
	
	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
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