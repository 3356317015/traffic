package com.zhima.traffic.model;

import com.zhima.basic.jdbc.FieldTag;
import com.zhima.basic.jdbc.TableTag;

@TableTag(cName="语音设置",eName="VmsSoundset",tabName="vms_soundset")
public class VmsSoundset implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	@FieldTag(cName="语音设置序号",eName="soundsetSeq",field="soundset_seq",pk=true)
	private String soundsetSeq="";
	
	@FieldTag(cName="语音序号",eName="soundSeq",field="sound_seq")
	private String soundSeq="";
	
	@FieldTag(cName="播放时间",eName="playTime",field="play_time")
	private String playTime="";
	
	@FieldTag(cName="播放次数",eName="playNumber",field="play_number")
	private String playNumber="";
	
	@FieldTag(cName="备注",eName="remark",field="remark")
	private String remark="";
	
	@FieldTag(cName="修改时间",eName="updateTime",field="update_time")
	private String updateTime="";
	

	// Constructors

	/** default constructor */
	public VmsSoundset() {
	}

	/** full constructor */

	// Property accessors
	
	public String getSoundsetSeq() {
		return soundsetSeq;
	}

	public void setSoundsetSeq(String soundsetSeq) {
		this.soundsetSeq = soundsetSeq;
	}

	public String getSoundSeq() {
		return soundSeq;
	}

	public void setSoundSeq(String soundSeq) {
		this.soundSeq = soundSeq;
	}

	public String getPlayTime() {
		return playTime;
	}

	public void setPlayTime(String playTime) {
		this.playTime = playTime;
	}

	public String getPlayNumber() {
		return playNumber;
	}

	public void setPlayNumber(String playNumber) {
		this.playNumber = playNumber;
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