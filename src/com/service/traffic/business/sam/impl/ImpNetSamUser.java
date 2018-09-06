package com.service.traffic.business.sam.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.service.traffic.business.sam.INetSamUser;
import com.service.traffic.dao.SamUserColumnDao;
import com.service.traffic.dao.SamUserDao;
import com.service.traffic.dao.SamUserGroupDao;
import com.service.traffic.dao.SamUserModuleDao;
import com.service.traffic.dao.SamUserToolbarDao;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.basic.jdbc.PoolAlias;
import com.zhima.basic.jdbc.PoolHandler;
import com.zhima.frame.model.SamUser;

public class ImpNetSamUser implements INetSamUser {

	@Override
	public SamUser insert(SamUser samUser,Map<String, Object> config) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			SamUserDao samUserDao = new SamUserDao(conn);
			SamUser newUser = samUserDao.insert(samUser,config);
			conn.commit();
			return newUser;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}

	@Override
	public void update(SamUser samUser,Map<String, Object> config) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			SamUserDao samUserDao = new SamUserDao(conn);
			samUserDao.update(samUser,config);
			conn.commit();
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}

	@Override
	public void deleteByPk(String userSeq,Map<String, Object> config) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			SamUserDao samUserDao = new SamUserDao(conn);
			SamUserGroupDao samUserGroupDao = new SamUserGroupDao(conn);
			SamUserModuleDao samUserModuleDao = new SamUserModuleDao(conn);
			SamUserToolbarDao samUserToolbarDao = new SamUserToolbarDao(conn);
			SamUserColumnDao samUserColumnDao = new SamUserColumnDao(conn);
			samUserDao.deleteByPK(userSeq);
			samUserGroupDao.deleteByUserSeq(userSeq);
			samUserModuleDao.deleteByUserSeq(userSeq);
			samUserToolbarDao.deleteByUserSeq(userSeq);
			samUserColumnDao.deleteByUserSeq(userSeq);
			conn.commit();
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public List<SamUser> queryByAll() throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			SamUserDao samUserDao = new SamUserDao(conn);
			List<SamUser> samUsers = samUserDao.queryByAll();
			conn.commit();
			return samUsers;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}		
	}

	@Override
	public List<SamUser> queryByOrganize(String organizeSeq)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			SamUserDao samUserDao = new SamUserDao(conn);
			List<SamUser> samUsers = samUserDao.queryByOrganize(organizeSeq);
			if (null != samUsers && samUsers.size()>0){
				for (int i = 0; i < samUsers.size(); i++) {
					if (null != samUsers.get(i).getServiceSeq() && samUsers.get(i).getServiceSeq().length()>0){
						samUsers.get(i).setOrganizeName(samUsers.get(i).getServiceName());
					}
				}
			}
			conn.commit();
			return samUsers;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}		
	}

}
