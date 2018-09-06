package com.service.traffic.business.epd.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.service.traffic.business.epd.INetEpdPlancar;
import com.service.traffic.dao.EpdPlancarDao;
import com.service.traffic.dao.SamLogDetailDao;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.basic.jdbc.PoolAlias;
import com.zhima.basic.jdbc.PoolHandler;
import com.zhima.traffic.model.EpdCar;
import com.zhima.traffic.model.EpdPlan;
import com.zhima.traffic.model.EpdPlancar;
import com.zhima.traffic.model.EpdPlancheck;
import com.zhima.util.DateUtils;

public class ImpNetEpdPlancar implements INetEpdPlancar {

	@Override
	public EpdPlancar insert(EpdPlancar epdPlancar,Map<String, Object> config)throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdPlancarDao epdPlancarDao = new EpdPlancarDao(conn);
			EpdPlancar newPlancar = epdPlancarDao.insert(epdPlancar,config);
			conn.commit();
			return newPlancar;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public void update(EpdPlancar epdPlancar,Map<String, Object> config)throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdPlancarDao epdPlancarDao = new EpdPlancarDao(conn);
			epdPlancarDao.update(epdPlancar,config);
			conn.commit();
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public void delete(List<EpdPlancar> plancars,Map<String, Object> config)throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdPlancarDao epdPlancarDao = new EpdPlancarDao(conn);
			SamLogDetailDao samLogDetailDao = new SamLogDetailDao(conn);
			if (null != plancars && plancars.size()>0){
				for (int i = 0; i < plancars.size(); i++) {
					samLogDetailDao.deleteDataLog(plancars.get(i).getPlancarSeq()
							, new EpdPlancheck(),config);
					epdPlancarDao.deleteByPK(plancars.get(i).getPlancarSeq());
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
	public List<EpdPlancar> queryByPK(String plancarSeq) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdPlancarDao epdPlancarDao = new EpdPlancarDao(conn);
			List<EpdPlancar> plancars = epdPlancarDao.queryByPK(plancarSeq);
			conn.commit();
			return plancars;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}
	

	@Override
	public List<EpdPlancar> queryByPlanSeqAndPlanId(String planSeq, String planId)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdPlancarDao epdPlancarDao = new EpdPlancarDao(conn);
			List<EpdPlancar> plancars = epdPlancarDao.queryByPlanSeqAndPlanId(planSeq, planId);
			conn.commit();
			return plancars;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}

	@Override
	public void updateBatch(EpdPlan epdPlan, List<EpdCar> epdCars,Map<String, Object> config)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdPlancarDao epdPlancarDao = new EpdPlancarDao(conn);
			epdPlancarDao.deleteByPlanSeqAndPlanId(epdPlan.getPlanSeq(), epdPlan.getPlanId());
			if (null != epdCars && epdCars.size()>0){
				String currTime = DateUtils.getNow(DateUtils.FORMAT_LONG);
				for (int i = 0; i < epdCars.size(); i++) {
					EpdPlancar epdPlancar = new EpdPlancar();
					epdPlancar.setPlanSeq(epdPlan.getPlanSeq());
					epdPlancar.setPlanId(epdPlan.getPlanId());
					epdPlancar.setCarNumber(epdCars.get(i).getCarNumber());
					epdPlancar.setUpdateTime(currTime);
					epdPlancarDao.insert(epdPlancar,config);
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

}