package com.zhima.frame.model;

import com.zhima.basic.jdbc.FieldTag;
import com.zhima.basic.jdbc.TableTag;

@TableTag(cName="模块信息",eName="SamModule",tabName="sam_module")
public class SamModule implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@FieldTag(cName="模块序号",eName="moduleSeq",field="module_seq",pk=true)
	private String moduleSeq="";
	
	@FieldTag(cName="服务网点序号",eName="organizeSeq",field="organize_seq")
	private String organizeSeq="";
	
	@FieldTag(cName="模块代码",eName="moduleCode",field="module_code")
	private String moduleCode="";
	
	@FieldTag(cName="模块名称",eName="moduleName",field="module_name")
	private String moduleName="";
	
	@FieldTag(cName="类名",eName="moduleClass",field="module_class")
	private String moduleClass="";
	
	@FieldTag(cName="包名",eName="packName",field="pack_name")
	private String packName="";
	
	@FieldTag(cName="模块图标",eName="moduleIcon",field="module_icon")
	private String moduleIcon;
	
	@FieldTag(cName="模块描述",eName="moduleDesc",field="module_desc")
	private String moduleDesc="";
	
	@FieldTag(cName="顺序",eName="sn",field="sn")
	private Integer sn=0;
	
	@FieldTag(cName="父项代码",eName="parentSeq",field="parent_seq")
	private String parentSeq="";
	
	@FieldTag(cName="父项名称",eName="parentName",field="parent_name")
	private String parentName="";
	
	@FieldTag(cName="类型",eName="moduleType",field="module_type")
	private String moduleType="";
	
	@FieldTag(cName="分隔线",eName="separate",field="separate")
	private String separate="";
	
	@FieldTag(cName="组名称",eName="groupName",field="group_name")
	private String groupName="";
	
	@FieldTag(cName="状态",eName="status",field="status")
	private String status="";
	
	@FieldTag(cName="备注",eName="remark",field="remark")
	private String remark="";
	
	@FieldTag(cName="创建时间",eName="createTime",field="create_time")
	private String createTime="";
	
	@FieldTag(cName="更新时间",eName="updateTime",field="update_time")
	private String updateTime="";
	
	// Constructors
	/** default constructor */
	public SamModule() {
	}
	
	/** full constructor */	
	
	// Property accessors
	public String getModuleSeq() {
		return moduleSeq;
	}

	public void setModuleSeq(String moduleSeq) {
		this.moduleSeq = moduleSeq;
	}

	public String getOrganizeSeq() {
		return organizeSeq;
	}

	public void setOrganizeSeq(String organizeSeq) {
		this.organizeSeq = organizeSeq;
	}

	public String getModuleCode() {
		return moduleCode;
	}


	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode;
	}


	public String getModuleName() {
		return moduleName;
	}


	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getModuleClass() {
		return moduleClass;
	}

	public void setModuleClass(String moduleClass) {
		this.moduleClass = moduleClass;
	}

	public String getPackName() {
		return packName;
	}


	public void setPackName(String packName) {
		this.packName = packName;
	}


	public String getModuleIcon() {
		return moduleIcon;
	}


	public void setModuleIcon(String moduleIcon) {
		this.moduleIcon = moduleIcon;
	}

	public String getModuleDesc() {
		return moduleDesc;
	}


	public void setModuleDesc(String moduleDesc) {
		this.moduleDesc = moduleDesc;
	}


	public Integer getSn() {
		return sn;
	}


	public void setSn(Integer sn) {
		this.sn = sn;
	}


	public String getParentSeq() {
		return parentSeq;
	}


	public void setParentSeq(String parentSeq) {
		this.parentSeq = parentSeq;
	}


	public String getParentName() {
		return parentName;
	}


	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	

	public String getModuleType() {
		return moduleType;
	}
	

	public void setModuleType(String moduleType) {
		this.moduleType = moduleType;
	}
	

	public String getSeparate() {
		return separate;
	}
	

	public void setSeparate(String separate) {
		this.separate = separate;
	}
	

	public String getGroupName() {
		return groupName;
	}


	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}


	public String getCreateTime() {
		return createTime;
	}


	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}


	public String getUpdateTime() {
		return updateTime;
	}


	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

}