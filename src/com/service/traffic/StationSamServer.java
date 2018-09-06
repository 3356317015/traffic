package com.service.traffic;

import com.service.traffic.action.sam.SamGroupAction;
import com.service.traffic.action.sam.SamGroupModuleAction;
import com.service.traffic.action.sam.SamLogDetailAction;
import com.service.traffic.action.sam.SamLogOperAction;
import com.service.traffic.action.sam.SamLoginAction;
import com.service.traffic.action.sam.SamModuleAction;
import com.service.traffic.action.sam.SamModuleRightAction;
import com.service.traffic.action.sam.SamOrganizeAction;
import com.service.traffic.action.sam.SamParameterAction;
import com.service.traffic.action.sam.SamServiceAction;
import com.service.traffic.action.sam.SamTableseqAction;
import com.service.traffic.action.sam.SamUpgradeAction;
import com.service.traffic.action.sam.SamUpinfoAction;
import com.service.traffic.action.sam.SamUserAction;
import com.service.traffic.action.sam.SamUserColumnAction;
import com.service.traffic.action.sam.SamUserGroupAction;
import com.service.traffic.action.sam.SamUserOnlineAction;
import com.service.traffic.action.sam.SamUserSecretAction;
import com.service.traffic.action.sam.SamUserToolbarAction;
import com.service.traffic.action.sam.SamVariablesAction;
import com.zhima.util.DesUtils;

/**
 * StationSamServer概要说明：系统服务接口
 * @author lcy
 */
public class StationSamServer {
	DesUtils desUtils = new DesUtils();
	
	/**
	 * 登录业务
	 * SamLoginAction
	 */
	SamLoginAction loginAction = new SamLoginAction();
	public String Sam_Login(String security,String parameter){
		return loginAction.login(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String Sam_Close(String security,String parameter){
		return loginAction.close(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String Sam_Update(String security,String parameter){
		return loginAction.update(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String Sam_GetServerTime(String security){
		return loginAction.getServerTime(
				desUtils.getDesString(security));
	}
	
	/**
	 * 系统module业务
	 * SamModuleAction
	 */
	SamModuleAction moduleAction = new SamModuleAction();
	public String SamModule_Insert(String security,String parameter){
		return moduleAction.insert(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String SamModule_Update(String security,String parameter){
		return moduleAction.update(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	public String SamModule_DeleteByPk(String security,String parameter){
		return moduleAction.deleteByPk(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	public String SamModule_QueryTopModule(String security,String parameter){
		return moduleAction.queryTopModule(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	public String SamModule_QuerySubModule(String security,String parameter){
		return moduleAction.querySubModule(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	public String SamModule_QuerySubAll(String security,String parameter){
		return moduleAction.querySubAll(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String SamModule_QueryModuleByUser(String security,String parameter){
		return moduleAction.queryModuleByUser(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String SamModule_QueryByGroupSeq(String security,String parameter){
		return moduleAction.queryByGroupSeq(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	public String SamModule_QueryModuleParent(String security,String parameter){
		return moduleAction.queryModuleParent(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	public String SamModule_QueryByUserSeq(String security,String parameter){
		return moduleAction.queryByUserSeq(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String SamModule_QueryByType(String security,String parameter){
		return moduleAction.queryByType(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	public String SamModule_QueryToolbarByUserSeq(String security,String parameter){
		return moduleAction.queryToolbarByUserSeq(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	/**
	 * 机构设置
	 */
	SamOrganizeAction organizeAction = new SamOrganizeAction();
	public String Organize_Insert(String security,String parameter){
		return organizeAction.insert(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String Organize_Update(String security,String parameter){
		return organizeAction.update(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String Organize_Delete(String security,String parameter){
		return organizeAction.delete(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String Organize_QueryPageByCustom(String security,String parameter){
		return organizeAction.queryPageByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	public String Organize_QueryAllByCustom(String security,String parameter){
		return organizeAction.queryAllByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String Organize_QueryCountByCustom(String security,String parameter){
		return organizeAction.queryCountByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String Organize_QueryByAll(String security){
		return organizeAction.queryByAll(
				desUtils.getDesString(security));
	}

	public String Organize_QueryByOrganizeType(String security,String parameter){
		return organizeAction.queryByOrganizeType(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	public String Organize_QueryDealByOrganizeSeq(String security,String parameter){
		return organizeAction.queryDealByOrganizeSeq(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	/**
	 * 销售点设置业务
	 * SamOrganizeAction
	 */
	SamServiceAction serviceAction = new SamServiceAction();
	public String Service_Insert(String security,String parameter){
		return serviceAction.insert(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String Service_Update(String security,String parameter){
		return serviceAction.update(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String Service_Delete(String security,String parameter){
		return serviceAction.delete(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String Service_QueryPageByCustom(String security,String parameter){
		return serviceAction.queryPageByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	public String Service_QueryAllByCustom(String security,String parameter){
		return serviceAction.queryAllByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String Service_QueryCountByCustom(String security,String parameter){
		return serviceAction.queryCountByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	
	public String Service_QueryByOrganizeSeq(String security,String parameter){
		return serviceAction.queryByOrganizeSeq(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	public String Service_QueryByOrganizeAndSaleType(String security,String parameter){
		return serviceAction.queryByOrganizeAndSaleType(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	/**
	 * 角色设置
	 * SamGroupAction
	 */
	SamGroupAction groupAction = new SamGroupAction();
	public String Group_Insert(String security,String parameter){
		return groupAction.insert(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String Group_Update(String security,String parameter){
		return groupAction.update(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	public String Group_DeleteByPk(String security,String parameter){
		return groupAction.deleteByPk(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	public String Group_QueryByOrganizeSeq(String security,String parameter){
		return groupAction.queryByOrganizeSeq(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	/**
	 * 角色权限设置
	 * SamGroupModuleAction
	 */
	SamGroupModuleAction groupModuleAction = new SamGroupModuleAction();
	public String GroupModule_UpdateGroupRight(String security,String parameter){
		return groupModuleAction.updateGroupRight(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	/**
	 * 日志明细操作类
	 * SamLogDetailAction
	 */
	SamLogDetailAction logDetailAction = new SamLogDetailAction();
	public String LogDetail_QueryByOperSeq(String security,String parameter){
		return logDetailAction.queryByOperSeq(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));	
	}
	
	/**
	 * 日志类
	 * SamLogOperAction
	 */
	SamLogOperAction logOperAction = new SamLogOperAction();
	public String LogOper_Insert(String security,String parameter){
		return logOperAction.insert(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String LogOper_Update(String security,String parameter){
		return logOperAction.update(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String LogOper_QueryPageByCustom(String security,String parameter) {
		return logOperAction.queryPageByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String LogOper_QueryAllByCustom(String security,String parameter){
		return logOperAction.queryAllByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));	
	}

	public String LogOper_QueryCountByCustom(String security,String parameter){
		return logOperAction.queryCountByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String LogOper_DeleteByOperSeq(String security,String parameter){
		return logOperAction.deleteByOperSeq(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	/**
	 * 功能权限
	 * SamModuleRightAction
	 */
	SamModuleRightAction moduleRightAction = new SamModuleRightAction();
	public String ModuleRight_Insert(String security,String parameter){
		return moduleRightAction.insert(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String ModuleRight_Update(String security,String parameter){
		return moduleRightAction.update(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	public String ModuleRight_DeleteByPk(String security,String parameter){
		return moduleRightAction.deleteByPk(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String ModuleRight_QueryModuleRight (String security,String parameter){
		return moduleRightAction.queryModuleRight(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String ModuleRight_QueryByModuleSeq(String security,String parameter){
		return moduleRightAction.queryByModuleSeq(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String ModuleRight_QueryByGroupSeq(String security,String parameter){
		return moduleRightAction.queryByGroupSeq(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String ModuleRight_QueryByUserSeq(String security,String parameter){
		return moduleRightAction.queryByUserSeq(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String ModuleRight_QueryRightByUser(String security,String parameter){
		return moduleRightAction.queryRightByUser(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String ModuleRight_QueryRightByModuleSeq(String security,String parameter){
		return moduleRightAction.queryRightByModuleSeq(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	public String ModuleRight_QueryRightByOrganizeSeq(String security,String parameter){
		return moduleRightAction.queryRightByOrganizeSeq(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	/**
	 * 参数接口
	 * SamParameterAction
	 */
	SamParameterAction parameterAction = new SamParameterAction();
	public String SamParameter_Insert(String security,String parameter){
		return parameterAction.insert(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	public String SamParameter_Update(String security,String parameter){
		return parameterAction.update(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	public String SamParameter_Delete(String security,String parameter){
		return parameterAction.delete(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	public String SamParameter_QueryPageByCustom(String security,String parameter){
		return parameterAction.queryPageByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	public String SamParameter_QueryAllByCustom(String security,String parameter){
		return parameterAction.queryAllByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	public String SamParameter_QueryCountByCustom(String security,String parameter){
		return parameterAction.queryCountByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String SamParameter_QueryByCode(String security,String parameter){
		return parameterAction.queryByCode(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	/**
	 * 主键
	 * SamTableseqAction
	 */
	SamTableseqAction tableseqAction = new SamTableseqAction();
	public String SamTableseq_Insert(String security,String parameter) {
		return tableseqAction.insert(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String SamTableseq_Update(String security,String parameter) {
		return tableseqAction.update(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String SamTableseq_Delete(String security,String parameter) {
		return tableseqAction.delete(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	public String SamTableseq_QueryByPk(String security,String parameter) {
		return tableseqAction.queryByPk(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String SamTableseq_QueryPageByCustom(String security,String parameter) {
		return tableseqAction.queryPageByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String SamTableseq_QueryCountByCustom(String security,String parameter) {
		return tableseqAction.queryCountByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String SamTableseq_QueryAllByCustom(String security,String parameter) {
		return tableseqAction.queryAllByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	/**
	 * 升级
	 * SamUpgradeAction
	 */
	SamUpgradeAction upgradeAction = new SamUpgradeAction();
	public String SamUpgrade_Insert(String security,String parameter){
		return upgradeAction.insert(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String SamUpgrade_QueryPageByCustom(String security,String parameter){
		return upgradeAction.queryPageByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String SamUpgrade_QueryCountByCustom(String security,String parameter){
		return upgradeAction.queryCountByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String SamUpgrade_Delete(String security,String parameter){
		return upgradeAction.delete(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String SamUpgrade_QueryMaxVer(String security,String parameter){
		return upgradeAction.queryMaxVer(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String SamUpgrade_QueryUpgradeByVer(String security,String parameter){
		return upgradeAction.queryUpgradeByVer(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	/**
	 * 升级信息
	 * SamUpinfoAction
	 */
	SamUpinfoAction samUpinfoAction = new SamUpinfoAction();
	public String SamUpinfo_Insert(String security,String parameter){
		return samUpinfoAction.insert(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	/**
	 * 用户
	 * SamUserAction
	 */
	SamUserAction userAction = new SamUserAction();
	public String SamUser_Insert(String security,String parameter){
		return userAction.insert(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String SamUser_Update(String security,String parameter){
		return userAction.update(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String SamUser_DeleteByPk(String security,String parameter){
		return userAction.deleteByPk(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String SamUser_QueryByAll(String security){
		return userAction.queryByAll(
				desUtils.getDesString(security));
	}

	public String SamUser_QueryByOrganize(String security,String parameter){
		return userAction.queryByOrganize(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	/**
	 * 用户表格列
	 * SamUserColumnAction
	 */
	SamUserColumnAction userColumnAction = new SamUserColumnAction();
	public String SamUserColumn_QueryByUserColumn(String security,String parameter) {
		return userColumnAction.queryByUserColumn(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String SamUserColumn_QueryByGridColumn(String security,String parameter) {
		return userColumnAction.queryByGridColumn(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String SamUserColumn_QueryByValidColumn(String security,String parameter) {
		return userColumnAction.queryByValidColumn(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String SamUserColumn_UpdateUserColumn(String security,String parameter) {
		return userColumnAction.updateUserColumn(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String SamUserColumn_DeleteByUserGrid(String security,String parameter) {
		return userColumnAction.deleteByUserGrid(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	/**
	 * 用户角色
	 * SamUserGroupAction
	 */
	SamUserGroupAction userGroupAction = new SamUserGroupAction();
	public String SamUserGroup_Insert(String security,String parameter) {
		return userGroupAction.insert(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String SamUserGroup_Update(String security,String parameter) {
		return userGroupAction.update(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String SamUserGroup_DeleteByPk(String security,String parameter) {
		return userGroupAction.deleteByPk(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String SamUserGroup_QueryByUserSeq(String security,String parameter) {
		return userGroupAction.queryByUserSeq(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String SamUserGroup_UpdateUserRight(String security,String parameter) {
		return userGroupAction.updateUserRight(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	/**
	 * 在线用户
	 * SamUserOnlineAction
	 */
	SamUserOnlineAction userOnlineAction = new SamUserOnlineAction();
	public String SamUserOnline_QueryPageByAll(String security,String parameter) {
		return userOnlineAction.queryPageByAll(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String SamUserOnline_QueryCountByAll(String security,String parameter) {
		return userOnlineAction.queryCountByAll(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String SamUserOnline_ClearUser(String security,String parameter) {
		return userOnlineAction.clearUser(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	/**
	 * 变量
	 * SamVariablesAction
	 */
	SamVariablesAction variablesAction = new SamVariablesAction();
	public String SamVariables_Insert(String security,String parameter) {
		return variablesAction.insert(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	public String SamVariables_Update(String security,String parameter) {
		return variablesAction.update(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	public String SamVariables_Delete(String security,String parameter) {
		return variablesAction.delete(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	public String SamVariables_QueryPageByCustom(String security,String parameter) {
		return variablesAction.queryPageByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	public String SamVariables_QueryAllByCustom(String security,String parameter) {
		return variablesAction.queryAllByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	public String SamVariables_QueryCountByCustom(String security,String parameter) {
		return variablesAction.queryCountByCustom(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String SamVariables_QueryByAll(String security) {
		return variablesAction.queryByAll(
				desUtils.getDesString(security));
	}

	public String SamVariables_QueryByVariableType(String security,String parameter) {
		return variablesAction.queryByVariableType(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	/**
	 * 用户工具栏
	 * SamUserToolbarAction
	 */
	SamUserToolbarAction userToolbarAction = new SamUserToolbarAction();
	public String SamUserToolbar_Insert(String security,String parameter) {
		return userToolbarAction.insert(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	public String SamUserToolbar_Update(String security,String parameter) {
		return userToolbarAction.update(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	public String SamUserToolbar_DeleteByPk(String security,String parameter) {
		return userToolbarAction.deleteByPk(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	public String SamUserToolbar_DeleteByUserSeq(String security,String parameter) {
		return userToolbarAction.deleteByUserSeq(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	
	public String SamUserToolbar_QueryByUserSeq(String security,String parameter) {
		return userToolbarAction.queryByUserSeq(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
	/**
	 * 用户自定义表格
	 * SamUserSecretAction
	 */
	SamUserSecretAction userSecretAction = new SamUserSecretAction();
	public String SamUserSecret_QueryByGridSecret(String security,String parameter) {
		return userSecretAction.queryByGridSecret(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}

	public String SamUserSecret_UpdateUserSecret(String security,String parameter) {
		return userSecretAction.updateUserSecret(
				desUtils.getDesString(security),
				desUtils.getDesString(parameter));
	}
}