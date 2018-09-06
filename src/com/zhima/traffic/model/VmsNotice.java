package com.zhima.traffic.model;

import com.zhima.basic.jdbc.FieldTag;
import com.zhima.basic.jdbc.TableTag;

@TableTag(cName="公告信息",eName="VmsNotice",tabName="vms_notice")
public class VmsNotice implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	@FieldTag(cName="公告序号",eName="noticeSeq",field="notice_seq",pk=true)
	private String noticeSeq="";
	
	@FieldTag(cName="机构序号",eName="organizeSeq",field="organize_seq")
	private String organizeSeq="";

	@FieldTag(cName="公告名称",eName="noticeName",field="notice_name")
	private String noticeName="";
	
	@FieldTag(cName="公告内容",eName="noticeContent",field="notice_content")
	private String noticeContent="";
	
	@FieldTag(cName="公告状态",eName="noticeStatus",field="notice_status")
	private String noticeStatus="";
	
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
	public VmsNotice() {
	}
	
	
	/** full constructor */

	// Property accessors
	public String getNoticeSeq() {
		return noticeSeq;
	}



	public void setNoticeSeq(String noticeSeq) {
		this.noticeSeq = noticeSeq;
	}



	public String getOrganizeSeq() {
		return organizeSeq;
	}



	public void setOrganizeSeq(String organizeSeq) {
		this.organizeSeq = organizeSeq;
	}


	public String getNoticeName() {
		return noticeName;
	}

	

	public void setNoticeName(String noticeName) {
		this.noticeName = noticeName;
	}


	
	public String getNoticeContent() {
		return noticeContent;
	}



	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}
	


	public String getNoticeStatus() {
		return noticeStatus;
	}



	public void setNoticeStatus(String noticeStatus) {
		this.noticeStatus = noticeStatus;
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