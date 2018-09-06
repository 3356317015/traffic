package com.zhima.traffic.model;

import com.zhima.basic.jdbc.FieldTag;
import com.zhima.basic.jdbc.TableTag;

@TableTag(cName="背景音乐",eName="VmsSound",tabName="vms_sound")
public class VmsSound implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	@FieldTag(cName="背景音乐序号",eName="soundSeq",field="sound_seq",pk=true)
	private String soundSeq="";
	
	@FieldTag(cName="机构序号",eName="organizeSeq",field="organize_seq")
	private String organizeSeq="";
	
	@FieldTag(cName="音乐类型",eName="soundType",field="sound_type")
	private String soundType="";
	
	@FieldTag(cName="文件名称",eName="fileName",field="file_name")
	private String fileName="";
	
	@FieldTag(cName="音乐名称",eName="soundName",field="sound_name")
	private String soundName="";

	@FieldTag(cName="音乐路径",eName="soundPath",field="sound_path")
	private String soundPath="";
	
	@FieldTag(cName="音乐大小",eName="soundSize",field="sound_size")
	private String soundSize="";
	
	@FieldTag(cName="演唱者",eName="signer",field="signer")
	private String signer="";
	
	@FieldTag(cName="音乐时间",eName="soundTime",field="sound_time")
	private String soundTime="";
	
	@FieldTag(cName="音乐状态",eName="soundStatus",field="sound_status")
	private String soundStatus="";
	
	@FieldTag(cName="备注",eName="remark",field="remark")
	private String remark="";
	
	@FieldTag(cName="修改时间",eName="updateTime",field="update_time")
	private String updateTime="";
	
	@FieldTag(cName="播放时间",eName="playTime",field="play_time",db=false)
	private String playTime="";
	
	
	@FieldTag(cName="播放次数",eName="playNumber",field="play_number",db=false)
	private String playNumber="";

	// Constructors

	/** default constructor */
	public VmsSound() {
	}

	/** full constructor */

	// Property accessors


	public String getSoundSeq() {
		return soundSeq;
	}



	public void setSoundSeq(String soundSeq) {
		this.soundSeq = soundSeq;
	}



	public String getOrganizeSeq() {
		return organizeSeq;
	}



	public void setOrganizeSeq(String organizeSeq) {
		this.organizeSeq = organizeSeq;
	}



	public String getSoundType() {
		return soundType;
	}



	public void setSoundType(String soundType) {
		this.soundType = soundType;
	}



	public String getFileName() {
		return fileName;
	}

	
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	

	public String getSoundName() {
		return soundName;
	}



	public void setSoundName(String soundName) {
		this.soundName = soundName;
	}



	public String getSoundPath() {
		return soundPath;
	}



	public void setSoundPath(String soundPath) {
		this.soundPath = soundPath;
	}



	public String getSoundSize() {
		return soundSize;
	}



	public void setSoundSize(String soundSize) {
		this.soundSize = soundSize;
	}



	public String getSigner() {
		return signer;
	}



	public void setSigner(String signer) {
		this.signer = signer;
	}



	public String getSoundTime() {
		return soundTime;
	}



	public void setSoundTime(String soundTime) {
		this.soundTime = soundTime;
	}

	
	
	public String getSoundStatus() {
		return soundStatus;
	}



	public void setSoundStatus(String soundStatus) {
		this.soundStatus = soundStatus;
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

}