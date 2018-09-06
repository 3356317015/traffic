package com.service.traffic.business.epd.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.service.traffic.business.epd.INetEpdPlanstation;
import com.service.traffic.dao.EpdPlanstationDao;
import com.service.traffic.dao.SamLogDetailDao;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.basic.jdbc.PoolAlias;
import com.zhima.basic.jdbc.PoolHandler;
import com.zhima.traffic.model.EpdPlanstation;

public class ImpNetEpdPlanstation implements INetEpdPlanstation {

	@Override
	public EpdPlanstation insert(EpdPlanstation epdPlanstation,Map<String, Object> config)throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdPlanstationDao epdPlanstationDao = new EpdPlanstationDao(conn);
			EpdPlanstation newPlanstation = epdPlanstationDao.insert(epdPlanstation,config);
			conn.commit();
			return newPlanstation;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public void update(EpdPlanstation epdPlanstation,Map<String, Object> config)throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdPlanstationDao epdPlanstationDao = new EpdPlanstationDao(conn);
			epdPlanstationDao.update(epdPlanstation,config);
			conn.commit();
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public void delete(List<EpdPlanstation> planstations,Map<String, Object> config)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdPlanstationDao epdPlanstationDao = new EpdPlanstationDao(conn);
			SamLogDetailDao samLogDetailDao = new SamLogDetailDao(conn);
			if (null != planstations && planstations.size()>0){
				for (int i = 0; i < planstations.size(); i++) {
					samLogDetailDao.deleteDataLog(planstations.get(i).getPlanstationSeq()
							, new EpdPlanstation(),config);
					epdPlanstationDao.deleteByPK(planstations.get(i).getPlanstationSeq());
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
	public List<EpdPlanstation> queryByPK(String planstationSeq) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdPlanstationDao epdPlanstationDao = new EpdPlanstationDao(conn);
			List<EpdPlanstation> planstations = epdPlanstationDao.queryByPK(planstationSeq);
			conn.commit();
			return planstations;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}
	

	@Override
	public List<EpdPlanstation> queryByPlanSeqAndPlanId(String planSeq, String planId)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdPlanstationDao epdPlanstationDao = new EpdPlanstationDao(conn);
			List<EpdPlanstation> planstations = epdPlanstationDao.queryByPlanSeqAndPlanId(planSeq, planId);
			conn.commit();
			return planstations;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}

}