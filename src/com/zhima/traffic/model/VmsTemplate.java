package com.zhima.traffic.model;

import com.zhima.basic.jdbc.FieldTag;
import com.zhima.basic.jdbc.TableTag;

@TableTag(cName = "播音模板", eName = "VmsTemplate", tabName = "vms_template")
public class VmsTemplate implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	@FieldTag(cName = "播音模板序号", eName = "templateSeq", field = "template_seq", pk = true)
	private String templateSeq = "";
	
	@FieldTag(cName = "机构序号",eName="organizeSeq",field="organize_seq")
	private String organizeSeq="";
	
	@FieldTag(cName = "模板类型", eName = "templateType", field = "template_type")
	private String templateType = "";

	@FieldTag(cName = "模板代码", eName = "templateCode", field = "template_code")
	private String templateCode = "";
	
	@FieldTag(cName = "模板名称", eName = "templateName", field = "template_name")
	private String templateName = "";

	@FieldTag(cName = "中文模板", eName = "templateCn", field = "template_cn")
	private String templateCn = "";
	
	@FieldTag(cName = "英文模板", eName = "templateEn", field = "template_en")
	private String templateEn = "";

	@FieldTag(cName = "备注", eName = "remark", field = "remark")
	private String remark = "";

	@FieldTag(cName = "修改时间", eName = "updateTime", field = "update_time")
	private String updateTime = "";
	
	// Constructors

	/** default constructor */
	public VmsTemplate() {
	}
	
	/** full constructor */

	// Property accessors
	public String getTemplateSeq() {
		return templateSeq;
	}

	public void setTemplateSeq(String templateSeq) {
		this.templateSeq = templateSeq;
	}

	public String getOrganizeSeq() {
		return organizeSeq;
	}

	public void setOrganizeSeq(String organizeSeq) {
		this.organizeSeq = organizeSeq;
	}

	public String getTemplateType() {
		return templateType;
	}

	public void setTemplateType(String templateType) {
		this.templateType = templateType;
	}

	public String getTemplateCode() {
		return templateCode;
	}

	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getTemplateCn() {
		return templateCn;
	}

	public void setTemplateCn(String templateCn) {
		this.templateCn = templateCn;
	}

	public String getTemplateEn() {
		return templateEn;
	}

	public void setTemplateEn(String templateEn) {
		this.templateEn = templateEn;
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