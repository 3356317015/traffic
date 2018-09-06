package com.zhima.traffic.model;

import com.zhima.basic.jdbc.FieldTag;
import com.zhima.basic.jdbc.TableTag;

@TableTag(cName="发车位",eName="EpdParking",tabName="epd_parking")
public class EpdParking implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	@FieldTag(cName="发车位序号",eName="parkingSeq",field="parking_seq",pk=true)
	private String parkingSeq="";
	
	@FieldTag(cName="检票口序号",eName="checkgateSeq",field="checkgate_seq")
	private String checkgateSeq="";
	
	@FieldTag(cName="发车位号",eName="organizeSeq",field="organize_seq")
	private String organizeSeq="";
	
	@FieldTag(cName="发车位号",eName="parkingCode",field="parking_code")
	private String parkingCode="";
	
	@FieldTag(cName="发车位名称",eName="parkingName",field="parking_name")
	private String parkingName="";
	
	@FieldTag(cName="座位数",eName="seatNum",field="seat_num")
	private Integer seatNum=0;
	
	@FieldTag(cName="备注",eName="remark",field="remark")
	private String remark="";
	
	@FieldTag(cName="修改时间",eName="updateTime",field="update_time")
	private String updateTime="";
	
	@FieldTag(cName="检票口名称",eName="checkName",field="check_name",db=false)
	private String checkName="";

	// Constructors

	/** default constructor */
	public EpdParking() {
	}

	/** full constructor */

	// Property accessors
	public String getParkingSeq() {
		return parkingSeq;
	}

	public void setParkingSeq(String parkingSeq) {
		this.parkingSeq = parkingSeq;
	}

	public String getOrganizeSeq() {
		return organizeSeq;
	}

	public void setOrganizeSeq(String organizeSeq) {
		this.organizeSeq = organizeSeq;
	}

	public String getCheckgateSeq() {
		return checkgateSeq;
	}

	public void setCheckgateSeq(String checkgateSeq) {
		this.checkgateSeq = checkgateSeq;
	}

	public String getParkingCode() {
		return parkingCode;
	}

	public void setParkingCode(String parkingCode) {
		this.parkingCode = parkingCode;
	}

	public String getParkingName() {
		return parkingName;
	}

	public void setParkingName(String parkingName) {
		this.parkingName = parkingName;
	}

	public Integer getSeatNum() {
		return seatNum;
	}

	public void setSeatNum(Integer seatNum) {
		this.seatNum = seatNum;
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

	public String getCheckName() {
		return checkName;
	}

	public void setCheckName(String checkName) {
		this.checkName = checkName;
	}

}