package com.service.traffic.business.epd.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.service.traffic.business.epd.INetEpdPlandeal;
import com.service.traffic.dao.EpdPlandealDao;
import com.service.traffic.dao.SamLogDetailDao;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.basic.jdbc.PoolAlias;
import com.zhima.basic.jdbc.PoolHandler;
import com.zhima.traffic.model.EpdPlandeal;

public class ImpNetEpdPlandeal implements INetEpdPlandeal {

	@Override
	public EpdPlandeal insert(EpdPlandeal epdPlandeal,Map<String, Object> config)throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdPlandealDao epdPlandealDao = new EpdPlandealDao(conn);
			EpdPlandeal newPlandeal = epdPlandealDao.insert(epdPlandeal,config);
			conn.commit();
			return newPlandeal;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public void update(EpdPlandeal epdPlandeal,Map<String, Object> config)throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdPlandealDao epdPlandealDao = new EpdPlandealDao(conn);
			epdPlandealDao.update(epdPlandeal,config);
			conn.commit();
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public void delete(List<EpdPlandeal> Plandeals,Map<String, Object> config)throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdPlandealDao epdPlandealDao = new EpdPlandealDao(conn);
			SamLogDetailDao samLogDetailDao = new SamLogDetailDao(conn);
			if (null != Plandeals && Plandeals.size()>0){
				for (int i = 0; i < Plandeals.size(); i++) {
					samLogDetailDao.deleteDataLog(Plandeals.get(i).getPlandealSeq(), new EpdPlandeal(),config);
					epdPlandealDao.deleteByPK(Plandeals.get(i).getPlandealSeq());
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
	public List<EpdPlandeal> queryByPK(String PlandealSeq) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdPlandealDao epdPlandealDao = new EpdPlandealDao(conn);
			List<EpdPlandeal> Plandeals = epdPlandealDao.queryByPK(PlandealSeq);
			conn.commit();
			return Plandeals;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}
	

	@Override
	public List<EpdPlandeal> queryByPlanSeqAndPlanId(String planSeq, String planId)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdPlandealDao epdPlandealDao = new EpdPlandealDao(conn);
			List<EpdPlandeal> Plandeals = epdPlandealDao.queryByPlanSeqAndPlanId(planSeq, planId);
			conn.commit();
			return Plandeals;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}

}