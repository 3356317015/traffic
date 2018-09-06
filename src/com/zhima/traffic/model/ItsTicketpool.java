package com.zhima.traffic.model;

import com.zhima.basic.jdbc.FieldTag;
import com.zhima.basic.jdbc.TableTag;

@TableTag(cName="票据库",eName="ItsTicketpool",tabName="its_ticketpool")
public class ItsTicketpool implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	@FieldTag(cName="票据库序号",eName="ticketpoolSeq",field="ticketpool_seq",pk=true)
	private String ticketpoolSeq="";
	
	@FieldTag(cName="机构序号",eName="organizeSeq",field="organize_seq")
	private String organizeSeq="";
	
	@FieldTag(cName="票据类型序号",eName="tickettypeSeq",field="tickettype_seq")
	private String tickettypeSeq="";
	
	@FieldTag(cName="票据名称",eName="ticketName",field="ticket_name")
	private String ticketName="";
	
	@FieldTag(cName="使用人员",eName="userCode",field="user_code")
	private String userCode="";
	
	@FieldTag(cName="使用人员姓名",eName="userName",field="user_name")
	private String userName="";
	
	@FieldTag(cName="发票代码",eName="invoiceCode",field="invoice_code")
	private String invoiceCode="";
	
	@FieldTag(cName="条码起号",eName="ticketStart",field="ticket_start")
	private String ticketStart="";
	
	@FieldTag(cName="条码止号",eName="ticketLimit",field="ticket_limit")
	private String ticketLimit="";
	
	@FieldTag(cName="条码当前号",eName="ticketCurrent",field="ticket_current")
	private String ticketCurrent="";

	@FieldTag(cName="发票起始号",eName="taxcodeStart",field="taxcode_start")
	private String taxcodeStart="";
	
	@FieldTag(cName="发票终止号",eName="taxcodeLimit",field="taxcode_limit")
	private String taxcodeLimit="";
	
	@FieldTag(cName="操作类型",eName="operType",field="oper_type")
	private String operType="";
	
	@FieldTag(cName="操作员",eName="operUser",field="oper_user")
	private String operUser="";
	
	@FieldTag(cName="操作员姓名",eName="operName",field="oper_name")
	private String operName="";
	
	@FieldTag(cName="操作时间",eName="operTime",field="oper_time")
	private String operTime="";
	
	@FieldTag(cName="状态",eName="poolStatus",field="pool_status")
	private String poolStatus="";
	
	@FieldTag(cName="备注",eName="remark",field="remark")
	private String remark="";
	
	@FieldTag(cName="修改时间",eName="updateTime",field="update_time")
	private String updateTime="";
	
	@FieldTag(cName="总张数",eName="totalNum",field="total_num", db=false)
	private Integer totalNum=0;
	
	@FieldTag(cName="已用张数",eName="useNum",field="use_num", db=false)
	private Integer useNum=0;
	
	@FieldTag(cName="未用张数",eName="unuseNum",field="unuse_num", db=false)
	private Integer unuseNum=0;
	// Constructors

	/** default constructor */
	public ItsTicketpool() {
	}

	public String getTicketpoolSeq() {
		return ticketpoolSeq;
	}

	public void setTicketpoolSeq(String ticketpoolSeq) {
		this.ticketpoolSeq = ticketpoolSeq;
	}

	public String getOrganizeSeq() {
		return organizeSeq;
	}

	public void setOrganizeSeq(String organizeSeq) {
		this.organizeSeq = organizeSeq;
	}

	public String getTickettypeSeq() {
		return tickettypeSeq;
	}

	public void setTickettypeSeq(String tickettypeSeq) {
		this.tickettypeSeq = tickettypeSeq;
	}

	public String getTicketName() {
		return ticketName;
	}

	public void setTicketName(String ticketName) {
		this.ticketName = ticketName;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getInvoiceCode() {
		return invoiceCode;
	}

	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}

	public String getTicketStart() {
		return ticketStart;
	}

	public void setTicketStart(String ticketStart) {
		this.ticketStart = ticketStart;
	}

	public String getTicketLimit() {
		return ticketLimit;
	}

	public void setTicketLimit(String ticketLimit) {
		this.ticketLimit = ticketLimit;
	}

	public String getTicketCurrent() {
		return ticketCurrent;
	}

	public void setTicketCurrent(String ticketCurrent) {
		this.ticketCurrent = ticketCurrent;
	}

	public String getTaxcodeStart() {
		return taxcodeStart;
	}

	public void setTaxcodeStart(String taxcodeStart) {
		this.taxcodeStart = taxcodeStart;
	}

	public String getTaxcodeLimit() {
		return taxcodeLimit;
	}

	public void setTaxcodeLimit(String taxcodeLimit) {
		this.taxcodeLimit = taxcodeLimit;
	}

	public String getOperType() {
		return operType;
	}

	public void setOperType(String operType) {
		this.operType = operType;
	}

	public String getOperUser() {
		return operUser;
	}

	public void setOperUser(String operUser) {
		this.operUser = operUser;
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

	public String getPoolStatus() {
		return poolStatus;
	}

	public void setPoolStatus(String poolStatus) {
		this.poolStatus = poolStatus;
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

	public Integer getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}

	public Integer getUseNum() {
		return useNum;
	}

	public void setUseNum(Integer useNum) {
		this.useNum = useNum;
	}

	public Integer getUnuseNum() {
		return unuseNum;
	}

	public void setUnuseNum(Integer unuseNum) {
		this.unuseNum = unuseNum;
	}
	
	
	
}