
package com.zhima.frame.action.login;

import java.util.Map;

import com.zhima.basic.exception.UserBusinessException;

/** ISysrightOrganize概要说明:用户业务操作接口
 * @author Administrator
 */
public interface ILogin {
	
	/**
	 * login方法描述：验证用户信息
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2012-12-7 下午05:27:25
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param userCode 用户代码
	 * @param password 用户密码
	 * @throws UserBusinessException
	 */
	public void login(String organizeSeq, String userCode,String password, String sysVer, String loginIp, Map<String, Object> config) throws UserBusinessException;
	
	/**
	 * close方法描述：用户退出系统
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2012-12-14 上午10:54:32
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param samUserOnline 在线用户信息
	 * @throws UserBusinessException void
	 */
	public void close(String onlineSeq, String organizeSeq, String userCode, String userName, Map<String, Object> config) throws UserBusinessException;
	
	/**
	 * update方法描述：更新在线用户信息
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2012-12-14 上午11:19:39
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param samUserOnline 在线用户信息
	 * @throws UserBusinessException
	 */
	public void update(String onlineSeq) throws UserBusinessException;
	
	/**
	 * getServerTime方法描述：获取服务器时间
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-1-27 上午02:26:07
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @return
	 * @throws UserBusinessException String
	 */
	public String getServerTime() throws UserBusinessException;
}
