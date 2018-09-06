package com.service.traffic.business.login.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.service.traffic.business.login.INetLogin;
import com.service.traffic.dao.SamLogOperDao;
import com.service.traffic.dao.SamOrganizeDao;
import com.service.traffic.dao.SamUserDao;
import com.service.traffic.dao.SamUserOnlineDao;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.basic.jdbc.PoolAlias;
import com.zhima.basic.jdbc.PoolHandler;
import com.zhima.frame.model.SamLogOper;
import com.zhima.frame.model.SamOrganize;
import com.zhima.frame.model.SamUser;
import com.zhima.frame.model.SamUserOnline;
import com.zhima.util.DateUtils;

/**
 * ImpLogin概要说明：用户登录接口
 * @author lcy
 */
public class ImpNetLogin implements INetLogin {	
	/**
	 * 用户登录
	 */
	@Override
	public SamUser login(String organizeSeq, String userCode, String password, String sysVer, String loginIp,
			Map<String, Object> config)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		SamUser samUser = null;
		try{
			PoolHandler.pool.beginConn(conn);
			SamOrganizeDao samOrganizeDao = new SamOrganizeDao(conn);
			SamUserDao samUserDao = new SamUserDao(conn);
			SamUserOnlineDao samUserOnlineDao = new SamUserOnlineDao(conn);
			SamLogOperDao samLogMudelDao = new SamLogOperDao(conn);
			List<SamOrganize> organizes = samOrganizeDao.queryByPK(organizeSeq);
			if (null != organizes && organizes.size()>0){
			} else {
				throw new UserBusinessException("公司信息未定义，不允许登录。");
			}
			List<SamUser> users = samUserDao.queryByUserCode(organizeSeq,userCode);
			if (null != users && users.size()>0){
				if (!password.equals(users.get(0).getPassword())){
					throw new UserBusinessException("帐号：" +userCode+ " 不存在或密码有误，请与管理员联系。");
				}else{
					if (!"1".equals(users.get(0).getStatus())){
						throw new UserBusinessException("帐号：" +userCode+ " 不允许登录，请与管理员联系。");
					} else {
						samUser = users.get(0);
						String now = DateUtils.getNow(DateUtils.FORMAT_LONG);
						//添加在线用户
						SamUserOnline samUserOnline = new SamUserOnline();
						samUserOnline.setOrganizeSeq(organizeSeq);
						samUserOnline.setUserCode(samUser.getUserCode());
						samUserOnline.setUserName(samUser.getUserName());
						samUserOnline.setSysVersion(sysVer);
						samUserOnline.setLoginIp(loginIp);
						samUserOnline.setOnlineTime(now);
						samUserOnline.setLoginTime(now);
						SamUserOnline userOnline = samUserOnlineDao.insert(samUserOnline,config);
						samUser.setOnlineSeq(userOnline.getOnlineSeq());
						//记录登录日志
						SamLogOper samLogOper = new SamLogOper();
						samLogOper.setOrganizeSeq(organizeSeq);
						samLogOper.setModuleCode("SYSTEM");
						samLogOper.setModuleName("应用系统");
						samLogOper.setRightMethod("login");
						samLogOper.setRightName("系统登录");
						samLogOper.setStatus("1");
						samLogOper.setOperCode(samUser.getUserCode());
						samLogOper.setOperName(samUser.getUserName());
						samLogOper.setOperTime(now);
						samLogMudelDao.insert(samLogOper,config);
					}
				}
			} else {
				throw new UserBusinessException("帐号：" +userCode+ " 不存在或密码有误，请与管理员联系。");
			}
			conn.commit();
		} catch (UserBusinessException ue) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(ue.getMessage());
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
		return samUser;
	}

	@Override
	public void close(String onlineSeq, String organizeSeq, String userCode, String userName, Map<String,
			Object> config) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			SamUserOnlineDao samUserOnlineDao = new SamUserOnlineDao(conn);
			SamLogOperDao samLogMudelDao = new SamLogOperDao(conn);
			String now = DateUtils.getNow(DateUtils.FORMAT_LONG);
			//删除在线用户
			samUserOnlineDao.deleteByPK(onlineSeq);

			//记录退出日志
			SamLogOper samLogOper = new SamLogOper();
			samLogOper.setOrganizeSeq(organizeSeq);
			samLogOper.setModuleCode("SYSTEM");
			samLogOper.setModuleName("应用系统");
			samLogOper.setRightMethod("exit");
			samLogOper.setRightName("系统退出");
			samLogOper.setStatus("1");
			samLogOper.setOperCode(userCode);
			samLogOper.setOperName(userName);
			samLogOper.setOperTime(now);
			samLogMudelDao.insert(samLogOper,config);
			conn.commit();
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}

	@Override
	public void update(String onlineSeq)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			SamUserOnlineDao samUserOnlineDao = new SamUserOnlineDao(conn);
			samUserOnlineDao.updateByPK(onlineSeq);
			conn.commit();
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}

	@Override
	public String getServerTime() throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			String serverTime = null;
			PoolHandler.pool.beginConn(conn);
			SamUserDao samUserDao = new SamUserDao(conn);
			serverTime = samUserDao.getDBTime();
			conn.commit();
			return serverTime;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}

}