package com.zhima.frame.model;

import com.zhima.basic.jdbc.FieldTag;
import com.zhima.basic.jdbc.TableTag;

@TableTag(cName="操作日志信息",eName="SamLogOper",tabName="sam_log_oper")
public class SamLogOper implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@FieldTag(cName="模块日志序号",eName="operLogSeq",field="oper_log_seq",pk=true)
	private String operLogSeq="";
	
	@FieldTag(cName="服务网点序号",eName="organizeSeq",field="organize_seq")
	private String organizeSeq="";
	
	@FieldTag(cName="模块代码",eName="moduleCode",field="module_code")
	private String moduleCode="";
	
	@FieldTag(cName="模块名称",eName="moduleName",field="module_name")
	private String moduleName="";
	
	@FieldTag(cName="操作类型",eName="rightMethod",field="right_method")
	private String rightMethod="";
	
	@FieldTag(cName="操作名称",eName="rightName",field="right_name")
	private String rightName="";
	
	@FieldTag(cName="操作人代码",eName="operCode",field="oper_code")
	private String operCode="";
	
	@FieldTag(cName="状态",eName="status",field="status",match= "1/是,0/否")
	private String status="";
	
	@FieldTag(cName="操作描述",eName="operDesc",field="oper_desc")
	private String operDesc="";
	
	@FieldTag(cName="操作人姓名",eName="operName",field="oper_name")
	private String operName="";
	
	@FieldTag(cName="操作时间",eName="operTime",field="oper_time")
	private String operTime="";

	// Constructors
	
	/** default constructor */
	public SamLogOper() {
	}
	
	/** full constructor */

	// Property accessors
	
	public String getOrganizeSeq() {
		return organizeSeq;
	}

	public String getOperLogSeq() {
		return operLogSeq;
	}

	public void setOperLogSeq(String operLogSeq) {
		this.operLogSeq = operLogSeq;
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

	public String getRightMethod() {
		return rightMethod;
	}

	public void setRightMethod(String rightMethod) {
		this.rightMethod = rightMethod;
	}

	public String getRightName() {
		return rightName;
	}

	public void setRightName(String rightName) {
		this.rightName = rightName;
	}

	public String getOperCode() {
		return operCode;
	}

	public void setOperCode(String operCode) {
		this.operCode = operCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOperDesc() {
		return operDesc;
	}

	public void setOperDesc(String operDesc) {
		this.operDesc = operDesc;
	}

	public String getOperName() {
		return operName;
	}

	public void setOperName(String operName) {
		this.operName = operName;
	}

	public String getOperTime() {
		return operTime;
	}

	public void setOperTime(String operTime) {
		this.operTime = operTime;
	}

}