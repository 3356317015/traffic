package com.zhima.frame.model;

import com.zhima.basic.jdbc.FieldTag;
import com.zhima.basic.jdbc.TableTag;

@TableTag(cName="模块权限",eName="SamModuleRight",tabName="sam_module_right")
public class SamModuleRight implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@FieldTag(cName="权限序号",eName="rightSeq",field="right_seq",pk=true)
	private String rightSeq="";
	
	@FieldTag(cName="模块序号",eName="moduleSeq",field="module_seq")
	private String moduleSeq="";
	
	@FieldTag(cName="操作类别",eName="rightType",field="right_type")
	private String rightType="";
	
	@FieldTag(cName="操作名称",eName="rightName",field="right_name")
	private String rightName="";
	
	@FieldTag(cName="快捷键",eName="shortcutKey",field="shortcut_key")
	private String shortcutKey="";
	
	@FieldTag(cName="执行方法",eName="rightMethod",field="right_method")
	private String rightMethod="";
	
	@FieldTag(cName="顺序",eName="sn",field="sn")
	private Integer sn=0;
	
	@FieldTag(cName="权限图标",eName="rightIcon",field="right_icon")
	private String rightIcon;
	
	@FieldTag(cName="权限描述",eName="rightDesc",field="right_desc")
	private String rightDesc="";
	
	@FieldTag(cName="状态",eName="status",field="status")
	private String status="";
	
	@FieldTag(cName="是否记录日志",eName="operLog",field="oper_log")
	private String operLog="";
	
	@FieldTag(cName="备注",eName="remark",field="remark")
	private String remark="";
	
	@FieldTag(cName="创建时间",eName="createTime",field="create_time")
	private String createTime="";
	
	@FieldTag(cName="更新时间",eName="updateTime",field="update_time")
	private String updateTime="";
	
	@FieldTag(cName="模块名称",eName="moduleName",field="module_name",db=false)
	private String moduleName="";
	
	@FieldTag(cName="类名",eName="moduleClass",field="module_class",db=false)
	private String moduleClass="";
	
	@FieldTag(cName="",eName="packName",field="pack_name",db=false)
	private String packName="";

	// Constructors
	
	/** default constructor */
	public SamModuleRight() {
	}

	/** full constructor */
	

	// Property accessors
	public String getRightSeq() {
		return rightSeq;
	}

	public void setRightSeq(String rightSeq) {
		this.rightSeq = rightSeq;
	}

	public String getModuleSeq() {
		return moduleSeq;
	}

	public void setModuleSeq(String moduleSeq) {
		this.moduleSeq = moduleSeq;
	}

	public String getRightType() {
		return rightType;
	}

	public void setRightType(String rightType) {
		this.rightType = rightType;
	}

	public String getRightName() {
		return rightName;
	}

	public void setRightName(String rightName) {
		this.rightName = rightName;
	}

	public String getShortcutKey() {
		return shortcutKey;
	}

	public void setShortcutKey(String shortcutKey) {
		this.shortcutKey = shortcutKey;
	}

	public String getRightMethod() {
		return rightMethod;
	}

	public void setRightMethod(String rightMethod) {
		this.rightMethod = rightMethod;
	}

	public Integer getSn() {
		return sn;
	}

	public void setSn(Integer sn) {
		this.sn = sn;
	}

	public String getRightIcon() {
		return rightIcon;
	}

	public void setRightIcon(String rightIcon) {
		this.rightIcon = rightIcon;
	}

	public String getRightDesc() {
		return rightDesc;
	}

	public void setRightDesc(String rightDesc) {
		this.rightDesc = rightDesc;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOperLog() {
		return operLog;
	}

	public void setOperLog(String operLog) {
		this.operLog = operLog;
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
	

}