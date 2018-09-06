package com.service.traffic.business.sam.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.service.traffic.business.sam.INetSamModuleRight;
import com.service.traffic.dao.SamGroupModuleDao;
import com.service.traffic.dao.SamModuleRightDao;
import com.service.traffic.dao.SamUserModuleDao;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.basic.jdbc.PoolAlias;
import com.zhima.basic.jdbc.PoolHandler;
import com.zhima.frame.model.SamModuleRight;

/**
 * ImpLogin概要说明：用户登录接口
 * @author lcy
 */
public class ImpNetSamModuleRight implements INetSamModuleRight {
	
	@Override
	public SamModuleRight insert(SamModuleRight samModuleRight,Map<String, Object> config) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			SamModuleRightDao samModuleRightDao = new SamModuleRightDao(conn);
			SamModuleRight newSamModuleRight = samModuleRightDao.insert(samModuleRight,config);
			conn.commit();
			return newSamModuleRight;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}

	@Override
	public void update(SamModuleRight samModuleRight,Map<String, Object> config) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			SamModuleRightDao samModuleRightDao = new SamModuleRightDao(conn);
			samModuleRightDao.update(samModuleRight,config);
			conn.commit();
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}		
	}
	
	@Override
	public void deleteByPk(List<SamModuleRight> samModuleRights, Map<String, Object> config) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			SamModuleRightDao samModuleRightDao = new SamModuleRightDao(conn);
			SamGroupModuleDao samGroupModuleDao = new SamGroupModuleDao(conn);
			SamUserModuleDao samUserModuleDao = new SamUserModuleDao(conn);
			if (null != samModuleRights && samModuleRights.size()>0){
				for (int i = 0; i < samModuleRights.size(); i++) {
					samModuleRightDao.deleteByPK(samModuleRights.get(i).getRightSeq());
					samGroupModuleDao.deleteByRightSeq(samModuleRights.get(i).getRightSeq());
					samUserModuleDao.deleteByRightSeq(samModuleRights.get(i).getRightSeq());
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
	public List<SamModuleRight> queryModuleRight(String moduleSeq)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			SamModuleRightDao samModuleRightDao = new SamModuleRightDao(conn);
			List<SamModuleRight> samModuleRights = samModuleRightDao.queryByModuleSeq(moduleSeq);
			conn.commit();
			return samModuleRights;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}

	@Override
	public List<SamModuleRight> queryByModuleSeq(String moduleSeq) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			SamModuleRightDao samModuleRightDao = new SamModuleRightDao(conn);
			List<SamModuleRight> samModuleRights = samModuleRightDao.queryByModuleSeq(moduleSeq);
			conn.commit();
			return samModuleRights;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}

	@Override
	public List<SamModuleRight> queryByGroupSeq(String groupSeq)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			SamModuleRightDao samModuleRightDao = new SamModuleRightDao(conn);
			List<SamModuleRight> samModuleRights = samModuleRightDao.queryByGroupSeq(groupSeq);
			conn.commit();
			return samModuleRights;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}

	@Override
	public List<SamModuleRight> queryByUserSeq(String userSeq)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			SamModuleRightDao samModuleRightDao = new SamModuleRightDao(conn);
			List<SamModuleRight> samModuleRights = samModuleRightDao.queryByUserSeq(userSeq);
			conn.commit();
			return samModuleRights;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}
	
	@Override
	public List<SamModuleRight> queryRightByUser(String userSeq)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			SamModuleRightDao samModuleRightDao = new SamModuleRightDao(conn);
			List<SamModuleRight> samModuleRights = samModuleRightDao.queryByUser(userSeq);
			conn.commit();
			return samModuleRights;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}

	@Override
	public List<SamModuleRight> queryRightByModuleSeq(String moduleSeq)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			SamModuleRightDao samModuleRightDao = new SamModuleRightDao(conn);
			List<SamModuleRight> samModuleRights = samModuleRightDao.queryRightByModuleSeq(moduleSeq);
			conn.commit();
			return samModuleRights;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}

	@Override
	public List<SamModuleRight> queryRightByOrganizeSeq(String organizeSeq)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			SamModuleRightDao samModuleRightDao = new SamModuleRightDao(conn);
			List<SamModuleRight> samModuleRights = samModuleRightDao.queryRightByOrganizeSeq(organizeSeq);
			conn.commit();
			return samModuleRights;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}
	
}