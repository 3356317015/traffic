package com.service.traffic.business.epd.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.service.traffic.business.epd.INetEpdPlan;
import com.service.traffic.dao.EpdPlanDao;
import com.service.traffic.dao.EpdPlancarDao;
import com.service.traffic.dao.EpdPlancheckDao;
import com.service.traffic.dao.EpdPlandealDao;
import com.service.traffic.dao.EpdPlanseatDao;
import com.service.traffic.dao.EpdPlanserviceDao;
import com.service.traffic.dao.EpdPlanstationDao;
import com.service.traffic.dao.SamLogDetailDao;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.basic.jdbc.PoolAlias;
import com.zhima.basic.jdbc.PoolHandler;
import com.zhima.traffic.model.EpdCheckgate;
import com.zhima.traffic.model.EpdPlan;
import com.zhima.traffic.model.EpdPlancheck;
import com.zhima.traffic.model.EpdPlandeal;
import com.zhima.traffic.model.EpdPlanseat;
import com.zhima.traffic.model.EpdPlanservice;
import com.zhima.traffic.model.EpdPlanstation;
import com.zhima.widget.seatBar.Seat;
import com.zhima.widget.stationBar.Station;

public class ImpNetEpdPlan implements INetEpdPlan {

	@Override
	public EpdPlan insert(EpdPlan epdPlan,List<EpdCheckgate> checkgates,
			List<Station> stations,List<Seat> seats,List<EpdPlandeal> plandeals,
			List<EpdPlanservice> planservices,Map<String, Object> config)throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdPlanDao epdPlanDao = new EpdPlanDao(conn);
			EpdPlancheckDao epdPlancheckDao = new EpdPlancheckDao(conn);
			EpdPlanstationDao epdPlanstationDao = new EpdPlanstationDao(conn);
			EpdPlandealDao epdPlandealDao = new EpdPlandealDao(conn);
			EpdPlanseatDao epdPlanseatDao = new EpdPlanseatDao(conn);
			EpdPlanserviceDao epdPlanserviceDao = new EpdPlanserviceDao(conn);
			
			List<EpdPlan> epdPlans = epdPlanDao.queryByValid(epdPlan);
			if (null != epdPlans && epdPlans.size()>0){
				throw new UserBusinessException("计划车次已存在，不允许重复。");
			}
			
			EpdPlan newPlan = epdPlanDao.insert(epdPlan,config);
			
			if (null != checkgates && checkgates.size()>0){
				for (int i = 0; i < checkgates.size(); i++) {
					EpdPlancheck plancheck = new EpdPlancheck();
					plancheck.setPlanSeq(newPlan.getPlanSeq());
					plancheck.setPlanId(newPlan.getPlanId());
					plancheck.setCheckgateSeq(checkgates.get(i).getCheckgateSeq());
					plancheck.setUpdateTime(newPlan.getUpdateTime());
					epdPlancheckDao.insert(plancheck,config);
				}
			}
			
			if (null != stations && stations.size()>0){
				for (int i = 0; i < stations.size(); i++) {
					EpdPlanstation planstation = new EpdPlanstation();
					planstation.setPlanSeq(newPlan.getPlanSeq());
					planstation.setPlanId(newPlan.getPlanId());
					planstation.setRouteSeq(newPlan.getRouteSeq());
					planstation.setStationSeq(stations.get(i).getStationSeq());
					planstation.setStationOrder(i+1);
					planstation.setIfSale(stations.get(i).getIfSale());
					planstation.setStationNum(stations.get(i).getStationNum());
					planstation.setUpdateTime(newPlan.getUpdateTime());
					epdPlanstationDao.insert(planstation,config);
				}
			}

			if (null != seats && seats.size()>0){
				for (int i = 0; i < seats.size(); i++) {
					EpdPlanseat planseat = new EpdPlanseat();
					planseat.setPlanSeq(newPlan.getPlanSeq());
					planseat.setPlanId(newPlan.getPlanId());
					planseat.setSeatId(Integer.valueOf(seats.get(i).getSeatId()));
					planseat.setSeatState(seats.get(i).getSeatState());
					planseat.setSeatType(1);
					planseat.setUpdateTime(newPlan.getUpdateTime());
					epdPlanseatDao.insert(planseat,config);
				}
			}
			
			if (null != plandeals && plandeals.size()>0){
				for (int i = 0; i < plandeals.size(); i++) {
					plandeals.get(i).setPlanSeq(newPlan.getPlanSeq());
					plandeals.get(i).setPlanId(newPlan.getPlanId());
					plandeals.get(i).setUpdateTime(newPlan.getUpdateTime());
					epdPlandealDao.insert(plandeals.get(i),config);
				}
			}
			if (null != planservices && planservices.size()>0){
				for (int i = 0; i < planservices.size(); i++) {
					planservices.get(i).setPlanSeq(newPlan.getPlanSeq());
					planservices.get(i).setPlanId(newPlan.getPlanId());
					planservices.get(i).setRouteSeq(newPlan.getRouteSeq());
					planservices.get(i).setUpdateTime(newPlan.getUpdateTime());
					epdPlanserviceDao.insert(planservices.get(i), config);
				}
			}
			conn.commit();
			return newPlan;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public void update(EpdPlan epdPlan,List<EpdCheckgate> checkgates,List<Station> stations,
			List<Seat> seats,List<EpdPlandeal> plandeals,List<EpdPlanservice> planservices,
			Map<String, Object> config)throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdPlanDao epdPlanDao = new EpdPlanDao(conn);
			EpdPlancheckDao epdPlancheckDao = new EpdPlancheckDao(conn);
			EpdPlanstationDao epdPlanstationDao = new EpdPlanstationDao(conn);
			EpdPlandealDao epdPlandealDao = new EpdPlandealDao(conn);
			EpdPlanseatDao epdPlanseatDao = new EpdPlanseatDao(conn);
			EpdPlanserviceDao epdPlanserviceDao = new EpdPlanserviceDao(conn);
			
			List<EpdPlan> epdPlans = epdPlanDao.queryByValid(epdPlan);
			if (null != epdPlans && epdPlans.size()>0){
				throw new UserBusinessException("计划车次已存在，不允许重复。");
			}
			
			epdPlanDao.update(epdPlan,config);
			
			epdPlancheckDao.deleteByPlanSeqAndPlanId(epdPlan.getPlanSeq(),epdPlan.getPlanId());
			if (null != checkgates && checkgates.size()>0){
				for (int i = 0; i < checkgates.size(); i++) {
					EpdPlancheck plancheck = new EpdPlancheck();
					plancheck.setPlanSeq(epdPlan.getPlanSeq());
					plancheck.setPlanId(epdPlan.getPlanId());
					plancheck.setCheckgateSeq(checkgates.get(i).getCheckgateSeq());
					plancheck.setUpdateTime(epdPlan.getUpdateTime());
					epdPlancheckDao.insert(plancheck,config);
				}
			}
			
			epdPlanstationDao.deleteByPlanSeqAndPlanId(epdPlan.getPlanSeq(),epdPlan.getPlanId());
			if (null != stations && stations.size()>0){
				for (int i = 0; i < stations.size(); i++) {
					EpdPlanstation planstation = new EpdPlanstation();
					planstation.setPlanSeq(epdPlan.getPlanSeq());
					planstation.setPlanId(epdPlan.getPlanId());
					planstation.setRouteSeq(epdPlan.getRouteSeq());
					planstation.setStationSeq(stations.get(i).getStationSeq());
					planstation.setStationOrder(i+1);
					planstation.setIfSale(stations.get(i).getIfSale());
					planstation.setStationNum(stations.get(i).getStationNum());
					planstation.setUpdateTime(epdPlan.getUpdateTime());
					epdPlanstationDao.insert(planstation,config);
				}
			}
			
			epdPlanseatDao.deleteByPlanSeqAndPlanId(epdPlan.getPlanSeq(),epdPlan.getPlanId());
			if (null != seats && seats.size()>0){
				for (int i = 0; i < seats.size(); i++) {
					EpdPlanseat planseat = new EpdPlanseat();
					planseat.setPlanSeq(epdPlan.getPlanSeq());
					planseat.setPlanId(epdPlan.getPlanId());
					planseat.setSeatId(Integer.valueOf(seats.get(i).getSeatId()));
					planseat.setSeatState(seats.get(i).getSeatState());
					planseat.setSeatType(1);
					planseat.setUpdateTime(epdPlan.getUpdateTime());
					epdPlanseatDao.insert(planseat,config);
				}
			}
			
			epdPlandealDao.deleteByPlanSeqAndPlanId(epdPlan.getPlanSeq(),epdPlan.getPlanId());
			if (null != plandeals && plandeals.size()>0){
				for (int i = 0; i < plandeals.size(); i++) {
					plandeals.get(i).setPlanSeq(epdPlan.getPlanSeq());
					plandeals.get(i).setPlanId(epdPlan.getPlanId());
					plandeals.get(i).setUpdateTime(epdPlan.getUpdateTime());
					epdPlandealDao.insert(plandeals.get(i),config);
				}
			}
			epdPlanserviceDao.deleteByPlanSeq(epdPlan.getPlanSeq());
			if (null != planservices && planservices.size()>0){
				for (int i = 0; i < planservices.size(); i++) {
					planservices.get(i).setPlanSeq(epdPlan.getPlanSeq());
					planservices.get(i).setPlanId(epdPlan.getPlanId());
					planservices.get(i).setRouteSeq(epdPlan.getRouteSeq());
					planservices.get(i).setUpdateTime(epdPlan.getUpdateTime());
					epdPlanserviceDao.insert(planservices.get(i), config);
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
	public void delete(List<EpdPlan> epdPlans,Map<String, Object> config)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdPlanDao epdPlanDao = new EpdPlanDao(conn);
			EpdPlancarDao epdPlancarDao = new EpdPlancarDao(conn);
			EpdPlancheckDao epdPlancheckDao = new EpdPlancheckDao(conn);
			EpdPlanstationDao epdPlanstationDao = new EpdPlanstationDao(conn);
			EpdPlanseatDao epdPlanseatDao = new EpdPlanseatDao(conn);
			EpdPlandealDao epdPlandealDao = new EpdPlandealDao(conn);
			SamLogDetailDao samLogDetailDao = new SamLogDetailDao(conn);
			if (null != epdPlans && epdPlans.size()>0){
				for (int i = 0; i < epdPlans.size(); i++) {
					samLogDetailDao.deleteDataLog(epdPlans.get(i).getPlanSeq(), new EpdPlan(),config);
					epdPlanDao.deleteByPK(epdPlans.get(i).getPlanSeq());
					epdPlancarDao.deleteByPlanSeqAndPlanId(epdPlans.get(i).getPlanSeq(),epdPlans.get(i).getPlanId());
					epdPlancheckDao.deleteByPlanSeqAndPlanId(epdPlans.get(i).getPlanSeq(),epdPlans.get(i).getPlanId());
					epdPlanstationDao.deleteByPlanSeqAndPlanId(epdPlans.get(i).getPlanSeq(),epdPlans.get(i).getPlanId());
					epdPlanseatDao.deleteByPlanSeqAndPlanId(epdPlans.get(i).getPlanSeq(),epdPlans.get(i).getPlanId());
					epdPlandealDao.deleteByPlanSeqAndPlanId(epdPlans.get(i).getPlanSeq(),epdPlans.get(i).getPlanId());
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
	public List<EpdPlan> queryByPK(String planSeq) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdPlanDao epdPlanDao = new EpdPlanDao(conn);
			List<EpdPlan> plans = epdPlanDao.queryByPK(planSeq);
			conn.commit();
			return plans;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}
	

	@Override
	public List<EpdPlan> queryByRouteSeq(String routeSeq)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdPlanDao epdPlanDao = new EpdPlanDao(conn);
			List<EpdPlan> plans = epdPlanDao.queryByRouteSeq(routeSeq);
			conn.commit();
			return plans;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}
	
	@Override
	public List<EpdPlan> queryByAll() throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdPlanDao epdPlanDao = new EpdPlanDao(conn);
			List<EpdPlan> plans = epdPlanDao.queryByAll();
			conn.commit();
			return plans;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}
	
	@Override
	public List<EpdPlan> queryPageByCustom(String organizeSeq, String routeSeq,
			String stationSeq, String planId, String planType,
			String planStatus, int start, int limit)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdPlanDao epdPlanDao = new EpdPlanDao(conn);
			List<EpdPlan> plans = epdPlanDao.queryPageByCustom(organizeSeq, routeSeq, stationSeq, planId,
					planType, planStatus,start,limit);
			conn.commit();
			return plans;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}

	@Override
	public List<EpdPlan> queryAllByCustom(String organizeSeq, String routeSeq,
			String stationSeq, String planId, String planType,
			String planStatus) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdPlanDao epdPlanDao = new EpdPlanDao(conn);
			List<EpdPlan> plans = epdPlanDao.queryAllByCustom(organizeSeq, routeSeq, stationSeq, planId,
					planType, planStatus);
			conn.commit();
			return plans;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}

	@Override
	public int queryCountByCustom(String organizeSeq, String routeSeq, String stationSeq,
			String planId, String planType, String planStatus)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdPlanDao epdPlanDao = new EpdPlanDao(conn);
			int count = epdPlanDao.queryCountByCustom(organizeSeq, routeSeq, stationSeq, planId,
					planType, planStatus);
			conn.commit();
			return count;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}

	@Override
	public List<EpdPlan> queryByOrganizeSeq(String organizeSeq)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdPlanDao epdPlanDao = new EpdPlanDao(conn);
			List<EpdPlan> plans = epdPlanDao.queryByOrganizeSeq(organizeSeq);
			conn.commit();
			return plans;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}

}