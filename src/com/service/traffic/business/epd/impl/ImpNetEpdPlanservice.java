package com.service.traffic.business.epd.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.service.traffic.business.epd.INetEpdPlanservice;
import com.service.traffic.dao.EpdPlanserviceDao;
import com.service.traffic.dao.SamLogDetailDao;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.basic.jdbc.PoolAlias;
import com.zhima.basic.jdbc.PoolHandler;
import com.zhima.traffic.model.EpdPlanservice;

public class ImpNetEpdPlanservice implements INetEpdPlanservice {

	@Override
	public EpdPlanservice insert(EpdPlanservice epdPlanservice,Map<String, Object> config)throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdPlanserviceDao epdPlanserviceDao = new EpdPlanserviceDao(conn);
			EpdPlanservice newEpdPlanservice = epdPlanserviceDao.insert(epdPlanservice,config);
			conn.commit();
			return newEpdPlanservice;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public void update(EpdPlanservice epdPlanservice,Map<String, Object> config)throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdPlanserviceDao epdPlanserviceDao = new EpdPlanserviceDao(conn);
			epdPlanserviceDao.update(epdPlanservice,config);
			conn.commit();
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public void delete(List<EpdPlanservice> planservices,Map<String, Object> config)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdPlanserviceDao epdPlanserviceDao = new EpdPlanserviceDao(conn);
			SamLogDetailDao samLogDetailDao = new SamLogDetailDao(conn);
			if (null != planservices && planservices.size()>0){
				for (int i = 0; i < planservices.size(); i++) {
					samLogDetailDao.deleteDataLog(planservices.get(i).getPlanserviceSeq()
							, new EpdPlanservice(),config);
					epdPlanserviceDao.deleteByPK(planservices.get(i).getPlanserviceSeq());
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
	public List<EpdPlanservice> queryByPK(String planserviceSeq) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdPlanserviceDao epdPlanserviceDao = new EpdPlanserviceDao(conn);
			List<EpdPlanservice> planservices = epdPlanserviceDao.queryByPK(planserviceSeq);
			conn.commit();
			return planservices;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}
	
	@Override
	public List<EpdPlanservice> queryByPlanSeq(String planSeq) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdPlanserviceDao epdPlanserviceDao = new EpdPlanserviceDao(conn);
			List<EpdPlanservice> planservices = epdPlanserviceDao.queryByPlanSeq(planSeq);
			conn.commit();
			return planservices;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}
	


}