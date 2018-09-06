package com.service.traffic.business.epd.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.service.traffic.business.epd.INetEpdPlanseat;
import com.service.traffic.dao.EpdPlanseatDao;
import com.service.traffic.dao.SamLogDetailDao;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.basic.jdbc.PoolAlias;
import com.zhima.basic.jdbc.PoolHandler;
import com.zhima.traffic.model.EpdPlanseat;

public class ImpNetEpdPlanseat implements INetEpdPlanseat {

	@Override
	public EpdPlanseat insert(EpdPlanseat epdPlanseat,Map<String, Object> config)throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdPlanseatDao epdPlanseatDao = new EpdPlanseatDao(conn);
			EpdPlanseat newPlanseat = epdPlanseatDao.insert(epdPlanseat,config);
			conn.commit();
			return newPlanseat;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public void update(EpdPlanseat epdPlanseat,Map<String, Object> config)throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdPlanseatDao epdPlanseatDao = new EpdPlanseatDao(conn);
			epdPlanseatDao.update(epdPlanseat,config);
			conn.commit();
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public void delete(List<EpdPlanseat> planseats,Map<String, Object> config)throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdPlanseatDao epdPlanseatDao = new EpdPlanseatDao(conn);
			SamLogDetailDao samLogDetailDao = new SamLogDetailDao(conn);
			if (null != planseats && planseats.size()>0){
				for (int i = 0; i < planseats.size(); i++) {
					samLogDetailDao.deleteDataLog(planseats.get(i).getPlanseatSeq()
							, new EpdPlanseat(),config);
					epdPlanseatDao.deleteByPK(planseats.get(i).getPlanseatSeq());
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
	public List<EpdPlanseat> queryByPK(String planseatSeq) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdPlanseatDao epdPlanseatDao = new EpdPlanseatDao(conn);
			List<EpdPlanseat> planseats = epdPlanseatDao.queryByPK(planseatSeq);
			conn.commit();
			return planseats;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}
	

	@Override
	public List<EpdPlanseat> queryByPlanSeqAndPlanId(String planSeq, String planId)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdPlanseatDao epdPlanseatDao = new EpdPlanseatDao(conn);
			List<EpdPlanseat> planseats = epdPlanseatDao.queryByPlanSeqAndPlanId(planSeq, planId);
			conn.commit();
			return planseats;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}

}