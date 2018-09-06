package com.zhima.basic;

import java.util.HashMap;
import java.util.Map;

import com.zhima.basic.exception.UserBusinessException;
import com.zhima.basic.exception.UserSystemException;
import com.zhima.frame.action.sam.ISamLogOper;
import com.zhima.frame.action.sam.ISamParameter;
import com.zhima.frame.action.sam.impl.ImpSamLogOper;
import com.zhima.frame.action.sam.impl.ImpSamParameter;
import com.zhima.frame.model.SamLogOper;
import com.zhima.frame.model.SamModule;
import com.zhima.frame.model.SamModuleRight;
import com.zhima.frame.model.SamOrganize;
import com.zhima.frame.model.SamParameter;
import com.zhima.frame.model.SamUser;
import com.zhima.util.DateUtils;

/**
 * ConstantFinal概要说明：常量
 * @author lcy
 */
public class CommFinal {
	
	
	/**
	 * 登录用户
	 */
	public static int CONNECT_MODE = 1;//1=连接数据库，2=连接WebService
	public static int CLIENT_TYPE = 1;//1=系统模式,2=售票厅LED屏,3=检票厅LED屏
	public static String CLIENT_MAC="";
	public static String CLIENT_ADDRESS="";
	public static String CLIENT_USERCODE="";
	public static String CLIENT_PASSWORD="";
	public static String TELNEET_CONN="";
	public static int TIME_OUT =60000;

	public static SamOrganize organize = new SamOrganize();
	public static SamUser user = new SamUser();
	//记录日志序号
	public static SamLogOper operLog = new SamLogOper();
	
	/**
	 * 在线用户刷新时间
	 */
	public static final int ONLINE_UPDATE_TIME = 600;
	
	
	/**
	 * 操作类型
	 */
	public static final String OPER_TYPE_ADD ="添加";
	public static final String OPER_TYPE_UPDATE ="修改";
	public static final String OPER_TYPE_COPY ="复制";
	
	/**
	 * ImagePath
	 */
	public static final String IMAGE_BUTTON_FIND ="images/pop_find.gif";
	public static final String IMAGE_BUTTON_OK ="images/pop_ok.gif";
	public static final String IMAGE_BUTTON_CANCEL ="images/pop_cancel.gif";
	/**
	 * 操作日志状态
	 */
	public static final String ARR_LOG_STATUS[] = new String[]{"0-失败","1-正常"};
	/**
	 * 变量类型
	 */
	public static final String ARR_VARIABLE_TYPE[] = new String[]{"fun-函数","0-普通","1-收费","2-结算"};
	/**
	 * 变量状态
	 */
	public static final String ARR_VARIABLE_STATUS[] = new String[]{"0-无效","1-有效"};
	
	/**
	 * 销售方式
	 */
	public static final String ARR_SALE_TYPE[] = new String[]{"1-销售点","2-自助机","3-异站","4-代售","5-网售","6-手机","7-微信"};
	
	/**
	 * 销售权限
	 */
	public static final String ARR_SALE_RIGHT[] = new String[]{"0-无限制","1-授权销售"};
	/**
	 * getAllName方法描述：得到字符串数组对应名称
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-23 下午08:45:29
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param arrString 字符串数组
	 * @return String[] 名称
	 */
	public static String[] getAllName(String arrString[]){
		if (null != arrString && arrString.length>0){
			String list[] = new String[arrString.length];
			for (int i = 0; i < arrString.length; i++) {
				String arrItem[] = arrString[i].split("-");
				list[i] = arrItem[1];
			}
			return list;
		}
		return null;
	}
	
	/**
	 * getValue方法描述：得到字符串健对值
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-23 下午08:46:18
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param arrString 字符串数组
	 * @param name 名称
	 * @return String 值
	 */
	public static String getItemValue(String arrString[],String name){
		if (null != arrString && arrString.length>0){
			for (int i = 0; i < arrString.length; i++) {
				String arrItem[] = arrString[i].split("-");
				if (arrItem[1].equals(name)){
					return arrItem[0];
				}
			}
		}
		return "";
	}
	
	/**
	 * getItemName方法描述：得到字符串健对名称
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-23 下午09:12:42
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param arrString 字符串数组
	 * @param value 值
	 * @return String 名称
	 */
	public static String getItemName(String arrString[],String value){
		if (null != arrString && arrString.length>0){
			for (int i = 0; i < arrString.length; i++) {
				String arrItem[] = arrString[i].split("-");
				if (arrItem[0].equals(value)){
					return arrItem[1];
				}
			}
		}
		return "";
	}
	
	public static SamParameter getParamValue(String[] param) throws UserBusinessException{
		if (null != param && param.length==3){
			try {
				ISamParameter iSamParameter = new ImpSamParameter();
				SamParameter parameter = iSamParameter.queryByCode(CommFinal.organize.getOrganizeSeq(),
						param[0], param[1], param[2]);
				return parameter;
			} catch (UserBusinessException e) {
				throw new UserBusinessException(e.getMessage());
			}
		}else{
			throw new UserSystemException("系统参数不合法!");
		}
	}
	
	public static Map<String, Object> initConfig() throws UserBusinessException{
		Map<String, Object> config = new HashMap<String, Object>();
		config.put("operLogSeq", getOperSeq());
		config.put("organizeSeq", CommFinal.organize.getOrganizeSeq());
		config.put("organizeCode", CommFinal.organize.getOrganizeCode());
		config.put("userCode", CommFinal.user.getUserCode());
		return config;
	}
	
	/**
	 * insertOperLog方法描述：添加权限操作日志
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-4 下午07:13:55
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param samModuleRightBo 操作权限
	 * @param status 运行状态
	 * @param desc 运行描述
	 */
	public static void insertOperLog(SamModuleRight samModuleRight,String status,String desc)
		throws UserBusinessException{
		try {
			initOperSeq("");
			if (null != samModuleRight && "1".equals(samModuleRight.getOperLog())){
				ISamLogOper iSamLogOper = new ImpSamLogOper();
				SamLogOper samLogOper = new SamLogOper();
				samLogOper.setOrganizeSeq(CommFinal.organize.getOrganizeSeq());
				samLogOper.setModuleCode(samModuleRight.getModuleClass());
				samLogOper.setModuleName(samModuleRight.getModuleName());
				samLogOper.setRightMethod(samModuleRight.getRightMethod());
				samLogOper.setRightName(samModuleRight.getRightName());
				samLogOper.setStatus(status);
				samLogOper.setOperDesc(desc);
				samLogOper.setOperCode(CommFinal.user.getUserCode());
				samLogOper.setOperName(CommFinal.user.getUserName());
				samLogOper.setOperTime(DateUtils.getNow(DateUtils.FORMAT_LONG));
				operLog = iSamLogOper.insert(samLogOper,initConfig());
			}
		} catch (UserBusinessException e) {
			throw new UserBusinessException(e.getMessage());
		}
	}
	
	/**
	 * insertOperLog方法描述：添加功能运行日志
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-4 下午07:14:45
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param samModule 功能
	 * @param rightType 操作类型
	 * @param rightName 操作类型名称
	 * @param status 状态
	 * @param desc 运行描述
	 */
	public static void insertOperLog(SamModule samModule,String rightEvent,String rightName,String status,String desc)
		throws UserBusinessException{
		try {
			if (null != samModule){
				initOperSeq("");
				ISamLogOper iSamLogOper = new ImpSamLogOper();
				SamLogOper samLogOper = new SamLogOper();
				samLogOper.setOrganizeSeq(CommFinal.organize.getOrganizeSeq());
				samLogOper.setModuleCode(samModule.getModuleClass());
				samLogOper.setModuleName(samModule.getModuleName());
				samLogOper.setRightMethod(rightEvent);
				samLogOper.setRightName(rightName);
				samLogOper.setStatus(status);
				samLogOper.setOperDesc(desc);
				samLogOper.setOperCode(CommFinal.user.getUserCode());
				samLogOper.setOperName(CommFinal.user.getUserName());
				samLogOper.setOperTime(DateUtils.getNow(DateUtils.FORMAT_LONG));
				iSamLogOper.insert(samLogOper,initConfig());
			}
		} catch (UserBusinessException e) {
			throw new UserBusinessException(e.getMessage());
		}
	}
	
	/**
	 * updateOperLog方法描述：更新运行日志
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-4 下午07:52:46
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param samModuleRightBo 操作权限
	 * @param status 状态
	 * @param operDesc 操作描述
	 */
	public static void updateOperLog(SamModuleRight samModuleRight,String status,String operDesc)
		throws UserBusinessException{
		try {
			if (null != samModuleRight && "1".equals(samModuleRight.getOperLog())){
				ISamLogOper iSamLogOper = new ImpSamLogOper();
				iSamLogOper.update(operLog, status, operDesc);
			}
		} catch (UserBusinessException e) {
			throw new UserBusinessException(e.getMessage());
		}
	}
	
	/**
	 * getOperSeq方法描述：得到操作日志序号主键
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-28 下午09:48:52
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @return String
	 */
	public static String getOperSeq(){
		if (null != operLog && !"".equals(operLog.getOperLogSeq())){
			return operLog.getOperLogSeq();
		}
		return "";
	}
	
	/**
	 * initOperSeq方法描述：初始化操作日志主键
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-28 下午09:50:13
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param config void
	 */
	public static void initOperSeq(String operLogSeq){
		if (null != operLog){
			operLog.setOperLogSeq(operLogSeq);
		}
	}

	/*public static void main(String[] args) {
		System.out.println(getItemValue(ARR_ORGANIZE_TYPE,"无效"));;
		
	}*/
	
}
