package com.zhima.util.jTTS;

import com.zhima.basic.jdbc.FieldTag;

/**
 * JttsVoice概要说明：语音播放内容
 * @author lcy
 */
public class JttsVoice {

	@FieldTag(cName="播音序号",eName="voiceSeq",field="voiceSeq",pk=true)
	private String voiceSeq="";
	/**
	 * 播放类型(1=班次公告,2=文本公告，3=语音公告)
	 */
	@FieldTag(cName="播音类型",eName="voiceType",field="voiceType")
	private String voiceType = ""; 

	@FieldTag(cName="播音名称",eName="voiceName",field="voiceName")
	private String voiceName = ""; 
	
	@FieldTag(cName="播音时间",eName="voiceTime",field="voiceTime")
	private String voiceTime = "";
	
	@FieldTag(cName="排序",eName="voiceOrder",field="voiceOrder")
	private Double voiceOrder =0.000;
	/**
	 * type为1、2＝播报的文本内容
	 * type为3=播报的声音路径
	 */
	@FieldTag(cName="播音内容",eName="voiceContent",field="voiceContent")
	private String voiceContent = "";
	
	
	public String getVoiceSeq() {
		return voiceSeq;
	}
	public void setVoiceSeq(String voiceSeq) {
		this.voiceSeq = voiceSeq;
	}
	public String getVoiceType() {
		return voiceType;
	}
	public void setVoiceType(String voiceType) {
		this.voiceType = voiceType;
	}
	public String getVoiceName() {
		return voiceName;
	}
	public void setVoiceName(String voiceName) {
		this.voiceName = voiceName;
	}
	public String getVoiceTime() {
		return voiceTime;
	}
	public void setVoiceTime(String voiceTime) {
		this.voiceTime = voiceTime;
	}
	public String getVoiceContent() {
		return voiceContent;
	}
	public void setVoiceContent(String voiceContent) {
		this.voiceContent = voiceContent;
	}
	public Double getVoiceOrder() {
		return voiceOrder;
	}
	public void setVoiceOrder(Double voiceOrder) {
		this.voiceOrder = voiceOrder;
	}

}
