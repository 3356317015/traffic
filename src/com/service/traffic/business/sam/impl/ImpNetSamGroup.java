package com.service.traffic.business.sam.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.service.traffic.business.sam.INetSamGroup;
import com.service.traffic.dao.SamGroupDao;
import com.service.traffic.dao.SamGroupModuleDao;
import com.service.traffic.dao.SamUserGroupDao;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.basic.jdbc.PoolAlias;
import com.zhima.basic.jdbc.PoolHandler;
import com.zhima.frame.model.SamGroup;

/**
 * ImpSamGroup概要说明：角色管理接口
 * @author lcy
 */
public class ImpNetSamGroup implements INetSamGroup {
	
	@Override
	public SamGroup insert(SamGroup samGroup,Map<String, Object> config) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			SamGroupDao samGroupDao = new SamGroupDao(conn);
			SamGroup newSamGroup = samGroupDao.insert(samGroup,config);
			conn.commit();
			return newSamGroup;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}		
	}

	@Override
	public void update(SamGroup samGroup,Map<String, Object> config) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			SamGroupDao samGroupDao = new SamGroupDao(conn);
			samGroupDao.update(samGroup,config);
			conn.commit();
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}		
	}
	
	@Override
	public void deleteByPk(List<SamGroup> samGroups,Map<String, Object> config) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			SamGroupDao samGroupDao = new SamGroupDao(conn);
			SamGroupModuleDao samGroupModuleDao = new SamGroupModuleDao(conn);
			SamUserGroupDao samUserGroupDao = new SamUserGroupDao(conn);
			if (null != samGroups && samGroups.size()>0){
				for (int i = 0; i < samGroups.size(); i++) {
					samGroupDao.deleteByPK(samGroups.get(i).getGroupSeq());
					samGroupModuleDao.deleteByGroupSeq(samGroups.get(i).getGroupSeq());
					samUserGroupDao.deleteByGroupSeq(samGroups.get(i).getGroupSeq());
				}
			}
			conn.commit();
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}		
	}
	
	@Override
	public List<SamGroup> queryByAll() throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			SamGroupDao samGroupDao = new SamGroupDao(conn);
			List<SamGroup> samGroups = samGroupDao.queryByAll();
			conn.commit();
			return samGroups;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}		
	}

	@Override
	public List<SamGroup> queryByOrganizeSeq(String organizeSeq)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			SamGroupDao samGroupDao = new SamGroupDao(conn);
			List<SamGroup> samGroups = samGroupDao.queryByOrganizeSeq(organizeSeq);
			conn.commit();
			return samGroups;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}
	
}