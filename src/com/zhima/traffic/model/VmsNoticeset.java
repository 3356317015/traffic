package com.zhima.traffic.model;

import com.zhima.basic.jdbc.FieldTag;
import com.zhima.basic.jdbc.TableTag;

@TableTag(cName="公告播放设置",eName="VmsNoticeset",tabName="vms_noticeset")
public class VmsNoticeset implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	@FieldTag(cName="公告播放设置序号",eName="noticesetSeq",field="noticeset_seq",pk=true)
	private String noticesetSeq="";
	
	@FieldTag(cName="公告序号",eName="noticeSeq",field="notice_seq")
	private String noticeSeq="";
	
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
	public VmsNoticeset() {
	}

	/** full constructor */

	// Property accessors
	public String getNoticesetSeq() {
		return noticesetSeq;
	}

	public void setNoticesetSeq(String noticesetSeq) {
		this.noticesetSeq = noticesetSeq;
	}

	public String getNoticeSeq() {
		return noticeSeq;
	}

	public void setNoticeSeq(String noticeSeq) {
		this.noticeSeq = noticeSeq;
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