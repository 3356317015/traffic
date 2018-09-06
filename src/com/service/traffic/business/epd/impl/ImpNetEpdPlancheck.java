package com.service.traffic.business.epd.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.service.traffic.business.epd.INetEpdPlancheck;
import com.service.traffic.dao.EpdPlancheckDao;
import com.service.traffic.dao.SamLogDetailDao;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.basic.jdbc.PoolAlias;
import com.zhima.basic.jdbc.PoolHandler;
import com.zhima.traffic.model.EpdPlancheck;

public class ImpNetEpdPlancheck implements INetEpdPlancheck {

	@Override
	public EpdPlancheck insert(EpdPlancheck epdPlancheck,Map<String, Object> config)throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdPlancheckDao epdPlancheckDao = new EpdPlancheckDao(conn);
			EpdPlancheck newPlancheck = epdPlancheckDao.insert(epdPlancheck,config);
			conn.commit();
			return newPlancheck;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public void update(EpdPlancheck epdPlancheck,Map<String, Object> config)throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdPlancheckDao epdPlancheckDao = new EpdPlancheckDao(conn);
			epdPlancheckDao.update(epdPlancheck,config);
			conn.commit();
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public void delete(List<EpdPlancheck> planchecks,Map<String, Object> config)throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdPlancheckDao epdPlancheckDao = new EpdPlancheckDao(conn);
			SamLogDetailDao samLogDetailDao = new SamLogDetailDao(conn);
			if (null != planchecks && planchecks.size()>0){
				for (int i = 0; i < planchecks.size(); i++) {
					samLogDetailDao.deleteDataLog(planchecks.get(i).getPlancheckSeq()
							, new EpdPlancheck(),config);
					epdPlancheckDao.deleteByPK(planchecks.get(i).getPlancheckSeq());
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
	public List<EpdPlancheck> queryByPK(String plancheckSeq) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdPlancheckDao epdPlancheckDao = new EpdPlancheckDao(conn);
			List<EpdPlancheck> planchecks = epdPlancheckDao.queryByPK(plancheckSeq);
			conn.commit();
			return planchecks;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}
	

	@Override
	public List<EpdPlancheck> queryByPlanSeqAndPlanId(String planSeq, String planId)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdPlancheckDao epdPlancheckDao = new EpdPlancheckDao(conn);
			List<EpdPlancheck> planchecks = epdPlancheckDao.queryByPlanSeqAndPlanId(planSeq, planId);
			conn.commit();
			return planchecks;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}

}