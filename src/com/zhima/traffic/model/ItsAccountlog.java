package com.zhima.traffic.model;

import com.zhima.basic.jdbc.FieldTag;
import com.zhima.basic.jdbc.TableTag;

@TableTag(cName="结算日志",eName="ItsAccountlog",tabName="its_accountlog")
public class ItsAccountlog {

	@FieldTag(cName="结算单日志号",eName="accountLogSeq",field="accountLog_seq",pk=true)
	public String accountLogSeq="";
	
	@FieldTag(cName="机构序号",eName="organizeSeq",field="organize_seq")
	private String organizeSeq="";
	
	@FieldTag(cName="结算笔数",eName="accountCount",field="account_count")
	public Integer accountCount;
	
	@FieldTag(cName="结算费用",eName="accountAmount",field="account_amount")
	public Double accountAmount;

	@FieldTag(cName="结算类型",eName="accountType",field="account_type")
	public String accountType;
	
	@FieldTag(cName="结算人",eName="accountUser",field="account_user")
	public String accountUser;
	
	@FieldTag(cName="结算人姓名",eName="accountUsername",field="account_username")
	public String accountUsername;
	
	@FieldTag(cName="结算时间",eName="accountTime",field="account_time")
	public String accountTime;

	@FieldTag(cName="结算状态",eName="accountState",field="account_state")
	public Integer accountState;
	
	@FieldTag(cName="取消人",eName="cancelUser",field="cancel_user")
	public String cancelUser;
	
	@FieldTag(cName="取消人",eName="cancelUsername",field="cancel_username")
	public String cancelUsername;
	
	@FieldTag(cName="取消时间",eName="cancelTime",field="cancel_time")
	public String cancelTime;
	
	@FieldTag(cName="备注",eName="remark",field="remark")
	public String remark;
	
	@FieldTag(cName="修改时间",eName="updateTime",field="update_time")
	private String updateTime="";

	public String getAccountLogSeq() {
		return accountLogSeq;
	}

	public void setAccountLogSeq(String accountLogSeq) {
		this.accountLogSeq = accountLogSeq;
	}

	public String getOrganizeSeq() {
		return organizeSeq;
	}

	public void setOrganizeSeq(String organizeSeq) {
		this.organizeSeq = organizeSeq;
	}

	public Integer getAccountCount() {
		return accountCount;
	}

	public void setAccountCount(Integer accountCount) {
		this.accountCount = accountCount;
	}

	public Double getAccountAmount() {
		return accountAmount;
	}

	public void setAccountAmount(Double accountAmount) {
		this.accountAmount = accountAmount;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getAccountUser() {
		return accountUser;
	}

	public void setAccountUser(String accountUser) {
		this.accountUser = accountUser;
	}

	public String getAccountUsername() {
		return accountUsername;
	}

	public void setAccountUsername(String accountUsername) {
		this.accountUsername = accountUsername;
	}

	public String getAccountTime() {
		return accountTime;
	}

	public void setAccountTime(String accountTime) {
		this.accountTime = accountTime;
	}

	public Integer getAccountState() {
		return accountState;
	}

	public void setAccountState(Integer accountState) {
		this.accountState = accountState;
	}

	public String getCancelUser() {
		return cancelUser;
	}

	public void setCancelUser(String cancelUser) {
		this.cancelUser = cancelUser;
	}

	public String getCancelUsername() {
		return cancelUsername;
	}

	public void setCancelUsername(String cancelUsername) {
		this.cancelUsername = cancelUsername;
	}

	public String getCancelTime() {
		return cancelTime;
	}

	public void setCancelTime(String cancelTime) {
		this.cancelTime = cancelTime;
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
