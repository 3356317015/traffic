package com.zhima.traffic.model;

import com.zhima.basic.jdbc.FieldTag;
import com.zhima.basic.jdbc.TableTag;

@TableTag(cName="计划席位",eName="EpdPlanseat",tabName="epd_planseat")
public class EpdPlanseat implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	@FieldTag(cName="计划席位序号",eName="planseatSeq",field="planseat_seq",pk=true)
	private String planseatSeq="";
	
	@FieldTag(cName="计划序号",eName="planSeq",field="plan_seq")
	private String planSeq="";
	
	@FieldTag(cName="计划序号",eName="planId",field="plan_id")
	private String planId="";
	
	@FieldTag(cName="席位号",eName="seatId",field="seat_id")
	private Integer seatId=0;
	
	//(0禁售,1可售,2已售,3预留)
	@FieldTag(cName="席位状态",eName="seatState",field="seat_state")
	private Integer seatState=0;
	
	@FieldTag(cName="席位类别",eName="seatType",field="seat_type")
	private Integer seatType=0;
	
	@FieldTag(cName="备注",eName="remark",field="remark")
	private String remark="";
	
	@FieldTag(cName="修改时间",eName="updateTime",field="update_time")
	private String updateTime="";

	// Constructors

	/** default constructor */
	public EpdPlanseat() {
	}
	/** full constructor */

	// Property accessors
	public String getPlanseatSeq() {
		return planseatSeq;
	}

	public void setPlanseatSeq(String planseatSeq) {
		this.planseatSeq = planseatSeq;
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

	public Integer getSeatId() {
		return seatId;
	}

	public void setSeatId(Integer seatId) {
		this.seatId = seatId;
	}

	public Integer getSeatState() {
		return seatState;
	}

	public void setSeatState(Integer seatState) {
		this.seatState = seatState;
	}

	public Integer getSeatType() {
		return seatType;
	}

	public void setSeatType(Integer seatType) {
		this.seatType = seatType;
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