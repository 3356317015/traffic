package com.service.traffic.business.sam.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.service.traffic.business.sam.INetSamUserGroup;
import com.service.traffic.dao.SamUserGroupDao;
import com.service.traffic.dao.SamUserModuleDao;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.basic.jdbc.PoolAlias;
import com.zhima.basic.jdbc.PoolHandler;
import com.zhima.frame.model.SamGroup;
import com.zhima.frame.model.SamUserGroup;
import com.zhima.frame.model.SamUserModule;

public class ImpNetSamUserGroup implements INetSamUserGroup {

	@Override
	public SamUserGroup insert(SamUserGroup samUserGroup,Map<String, Object> config) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			SamUserGroupDao samUserGroupDao = new SamUserGroupDao(conn);
			SamUserGroup newUserGroup = samUserGroupDao.insert(samUserGroup,config);
			conn.commit();
			return newUserGroup;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}

	@Override
	public void update(SamUserGroup samUserGroup,Map<String, Object> config) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			SamUserGroupDao samUserGroupDao = new SamUserGroupDao(conn);
			samUserGroupDao.update(samUserGroup,config);
			conn.commit();
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}

	@Override
	public void deleteByPk(String userGroupSeq,Map<String, Object> config) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			SamUserGroupDao samUserGroupDao = new SamUserGroupDao(conn);
			samUserGroupDao.deleteByPk(userGroupSeq);
			conn.commit();
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public List<SamUserGroup> queryByUserSeq(String userSeq) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			SamUserGroupDao samUserGroupDao = new SamUserGroupDao(conn);
			List<SamUserGroup> samUserGroups = samUserGroupDao.queryByUserSeq(userSeq);
			conn.commit();
			return samUserGroups;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}		
	}

	@Override
	public void updateUserRight(String userSeq, List<SamGroup> samGroups,
			List<SamUserModule> samUserModules,Map<String, Object> config) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			SamUserGroupDao samUserGroupDao = new SamUserGroupDao(conn);
			SamUserModuleDao samUserModuleDao = new SamUserModuleDao(conn);
			samUserGroupDao.deleteByUserSeq(userSeq);
			samUserModuleDao.deleteByUserSeq(userSeq);
			for (int i = 0; i < samGroups.size(); i++) {
				SamUserGroup samUserGroup = new SamUserGroup();
				samUserGroup.setUserSeq(userSeq);
				samUserGroup.setGroupSeq(samGroups.get(i).getGroupSeq());
				samUserGroupDao.insert(samUserGroup,config);
			}
			for (int i = 0; i < samUserModules.size(); i++) {
				samUserModuleDao.insert(samUserModules.get(i),config);
			}
			conn.commit();
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}		
	}

}
