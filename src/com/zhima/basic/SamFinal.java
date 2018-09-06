package com.zhima.basic;


/**
 * SamFinal概要说明：系统服务接口定义
 * @author lcy
 */
public class SamFinal {
	public static String ServerAddres="http://127.0.0.1:8080/axis2/services/StationSamServer";
	public static String ServerNamespace="http://traffic.service.com";
	
	/**
	 * SamLoginAction
	 * 登录业务
	 */
	public static final String Sam_Login = "Sam_Login";
	public static final String Sam_Close = "Sam_Close";
	public static final String Sam_Update = "Sam_Update";
	public static final String Sam_GetServerTime = "Sam_GetServerTime";
	
	/**
	 * SamModuleAction
	 * Module设置业务
	 */
	public static final String SamModule_Insert = "SamModule_Insert";
	public static final String SamModule_Update = "SamModule_Update";
	public static final String SamModule_DeleteByPk = "SamModule_DeleteByPk";
	public static final String SamModule_QueryTopModule = "SamModule_QueryTopModule";
	public static final String SamModule_QuerySubModule = "SamModule_QuerySubModule";
	public static final String SamModule_QuerySubAll = "SamModule_QuerySubAll";
	public static final String SamModule_QueryModuleByUser = "SamModule_QueryModuleByUser";
	public static final String SamModule_QueryByGroupSeq = "SamModule_QueryByGroupSeq";
	public static final String SamModule_QueryModuleParent = "SamModule_QueryModuleParent";
	public static final String SamModule_QueryByUserSeq = "SamModule_QueryByUserSeq";
	public static final String SamModule_QueryToolbarByUserSeq = "SamModule_QueryToolbarByUserSeq";
	public static final String SamModule_QueryByType = "SamModule_QueryByType";
	
	/**
	 * SamOrganizeAction
	 * 机构设置业务
	 */
	public static final String Organize_Insert = "Organize_Insert";
	public static final String Organize_Update = "Organize_Update";
	public static final String Organize_Delete = "Organize_Delete";
	public static final String Organize_QueryByAll = "Organize_QueryByAll";
	public static final String Organize_QueryPageByCustom = "Organize_QueryPageByCustom";
	public static final String Organize_QueryAllByCustom = "Organize_QueryAllByCustom";
	public static final String Organize_QueryCountByCustom = "Organize_QueryCountByCustom";
	public static final String Organize_QueryByOrganizeType = "Organize_QueryByOrganizeType";
	public static final String Organize_QueryDealByOrganizeSeq = "Organize_QueryDealByOrganizeSeq";
	
	/**
	 * SamService
	 * 销售点设置
	 */
	public static final String Service_Insert = "Service_Insert";
	public static final String Service_Update = "Service_Update";
	public static final String Service_Delete = "Service_Delete";
	public static final String Service_QueryPageByCustom = "Service_QueryPageByCustom";
	public static final String Service_QueryAllByCustom = "Service_QueryAllByCustom";
	public static final String Service_QueryCountByCustom = "Service_QueryCountByCustom";
	public static final String Service_QueryByOrganizeSeq = "Service_QueryByOrganizeSeq";
	public static final String Service_QueryByOrganizeAndSaleType = "Service_QueryByOrganizeAndSaleType";
	
	/**
	 * SamGroupAction
	 * 角色设置
	 */
	public static final String Group_Insert = "Group_Insert";
	public static final String Group_Update = "Group_Update";
	public static final String Group_DeleteByPk = "Group_DeleteByPk";
	public static final String Group_QueryByOrganizeSeq= "Group_QueryByOrganizeSeq";
	
	/**
	 * 角色权限设置
	 * SamGroupModuleAction
	 */
	public static final String GroupModule_UpdateGroupRight= "GroupModule_UpdateGroupRight";
	
	/**
	 * 日志明细
	 * SamLogDetailAction
	 */
	public static final String LogDetail_QueryByOperSeq="LogDetail_QueryByOperSeq";
	
	/**
	 * 操作日志
	 * SamLogOperAction
	 */
	public static final String LogOper_Insert = "LogOper_Insert";
	public static final String LogOper_Update = "LogOper_Update";
	public static final String LogOper_QueryPageByCustom = "LogOper_QueryPageByCustom";
	public static final String LogOper_QueryAllByCustom = "LogOper_QueryAllByCustom";
	public static final String LogOper_QueryCountByCustom = "LogOper_QueryCountByCustom";
	public static final String LogOper_DeleteByOperSeq = "LogOper_DeleteByOperSeq";

	/**
	 * 功能权限
	 * SamModuleRightAction
	 */
	public static final String ModuleRight_Insert = "ModuleRight_Insert";
	public static final String ModuleRight_Update = "ModuleRight_Update";
	public static final String ModuleRight_DeleteByPk = "ModuleRight_DeleteByPk";
	public static final String ModuleRight_QueryModuleRight = "ModuleRight_QueryModuleRight";
	public static final String ModuleRight_QueryByModuleSeq = "ModuleRight_QueryByModuleSeq";
	public static final String ModuleRight_QueryByGroupSeq = "ModuleRight_QueryByGroupSeq";
	public static final String ModuleRight_QueryByUserSeq = "ModuleRight_QueryByUserSeq";
	public static final String ModuleRight_QueryRightByUser = "ModuleRight_QueryRightByUser";
	public static final String ModuleRight_QueryRightByModuleSeq = "ModuleRight_QueryRightByModuleSeq";
	public static final String ModuleRight_QueryRightByOrganizeSeq= "ModuleRight_QueryRightByOrganizeSeq";
	/**
	 * 参数
	 * SamParameterAction
	 */
	public static final String SamParameter_Insert = "SamParameter_Insert";
	public static final String SamParameter_Update = "SamParameter_Update";
	public static final String SamParameter_Delete = "SamParameter_Delete";
	public static final String SamParameter_QueryPageByCustom = "SamParameter_QueryPageByCustom";
	public static final String SamParameter_QueryAllByCustom = "SamParameter_QueryAllByCustom";
	public static final String SamParameter_QueryCountByCustom = "SamParameter_QueryCountByCustom";
	public static final String SamParameter_QueryByAll = "SamParameter_QueryByAll";
	public static final String SamParameter_QueryByCode = "SamParameter_QueryByCode";
	/**
	 * 主键
	 * SamTableseqAction
	 */
	public static final String SamTableseq_Insert = "SamTableseq_Insert";
	public static final String SamTableseq_Update = "SamTableseq_Update";
	public static final String SamTableseq_Delete = "SamTableseq_Delete";
	public static final String SamTableseq_QueryByPk = "SamTableseq_QueryByPk";
	public static final String SamTableseq_QueryPageByCustom = "SamTableseq_QueryPageByCustom";
	public static final String SamTableseq_QueryCountByCustom = "SamTableseq_QueryCountByCustom";
	public static final String SamTableseq_QueryAllByCustom = "SamTableseq_QueryAllByCustom";


	/**
	 * 升级
	 * SamUpgradeAction
	 */
	public static final String SamUpgrade_Insert = "SamUpgrade_Insert";
	public static final String SamUpgrade_QueryPageByCustom = "SamUpgrade_QueryPageByCustom";
	public static final String SamUpgrade_QueryCountByCustom = "SamUpgrade_QueryCountByCustom";
	public static final String SamUpgrade_Delete = "SamUpgrade_Delete";
	public static final String SamUpgrade_QueryMaxVer = "SamUpgrade_QueryMaxVer";
	public static final String SamUpgrade_QueryUpgradeByVer = "SamUpgrade_QueryUpgradeByVer";

	/**
	 * 升级信息
	 * SamUpinfoAction
	 */
	public static final String SamUpinfo_Insert = "SamUpinfo_Insert";

	/**
	 * 用户
	 * SamUserAction
	 */
	public static final String SamUser_Insert = "SamUser_Insert";
	public static final String SamUser_Update = "SamUser_Update";
	public static final String SamUser_DeleteByPk = "SamUser_DeleteByPk";
	public static final String SamUser_QueryByAll = "SamUser_QueryByAll";
	public static final String SamUser_QueryByOrganize = "SamUser_QueryByOrganize";

	/**
	 * 用户表格
	 * SamUserColumnAction
	 */
	public static final String SamUserColumn_QueryByUserColumn = "SamUserColumn_QueryByUserColumn";
	public static final String SamUserColumn_QueryByGridColumn = "SamUserColumn_QueryByGridColumn";
	public static final String SamUserColumn_QueryByValidColumn = "SamUserColumn_QueryByValidColumn";
	public static final String SamUserColumn_UpdateUserColumn = "SamUserColumn_UpdateUserColumn";
	public static final String SamUserColumn_DeleteByUserGrid = "SamUserColumn_DeleteByUserGrid";

	/**
	 * 用户角色
	 * SamUserGroupAction
	 */
	public static final String SamUserGroup_Insert = "SamUserGroup_Insert";
	public static final String SamUserGroup_Update = "SamUserGroup_Update";
	public static final String SamUserGroup_DeleteByPk = "SamUserGroup_DeleteByPk";
	public static final String SamUserGroup_QueryByUserSeq = "SamUserGroup_QueryByUserSeq";
	public static final String SamUserGroup_UpdateUserRight = "SamUserGroup_UpdateUserRight";

	/**
	 * 在线用户
	 * SamUserOnlineAction
	 */
	public static final String SamUserOnline_QueryPageByAll = "SamUserOnline_QueryPageByAll";
	public static final String SamUserOnline_QueryCountByAll = "SamUserOnline_QueryCountByAll";
	public static final String SamUserOnline_ClearUser = "SamUserOnline_ClearUser";

	/**
	 * 变量
	 * SamVariablesAction
	 */
	public static final String SamVariables_Insert = "SamVariables_Insert";
	public static final String SamVariables_Update = "SamVariables_Update";
	public static final String SamVariables_Delete = "SamVariables_Delete";
	public static final String SamVariables_QueryPageByCustom = "SamVariables_QueryPageByCustom";
	public static final String SamVariables_QueryAllByCustom = "SamVariables_QueryAllByCustom";
	public static final String SamVariables_QueryCountByCustom = "SamVariables_QueryCountByCustom";
	public static final String SamVariables_QueryByAll = "SamVariables_QueryByAll";
	public static final String SamVariables_QueryByVariableType = "SamVariables_QueryByVariableType";

	/**
	 * 用户工具栏
	 * SamUserToolbarAction
	 */
	public static final String SamUserToolbar_Insert = "SamUserToolbar_Insert";
	public static final String SamUserToolbar_Update = "SamUserToolbar_Update";
	public static final String SamUserToolbar_DeleteByPk = "SamUserToolbar_DeleteByPk";
	public static final String SamUserToolbar_DeleteByUserSeq = "SamUserToolbar_DeleteByUserSeq";
	public static final String SamUserToolbar_QueryByUserSeq = "SamUserToolbar_QueryByUserSeq";

	/**
	 * 用户自定义
	 * SamUserSecretAction
	 */
	public static final String SamUserSecret_QueryByGridSecret = "SamUserSecret_QueryByGridSecret";
	public static final String SamUserSecret_UpdateUserSecret = "SamUserSecret_UpdateUserSecret";
	
}
