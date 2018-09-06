package com.service.traffic.business.epd.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.service.traffic.business.epd.INetEpdFare;
import com.service.traffic.dao.EpdFareDao;
import com.service.traffic.dao.EpdPlanDao;
import com.service.traffic.dao.SamLogDetailDao;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.basic.jdbc.PoolAlias;
import com.zhima.basic.jdbc.PoolHandler;
import com.zhima.traffic.model.EpdFare;

public class ImpNetEpdFare implements INetEpdFare {

	@Override
	public EpdFare insert(EpdFare epdFare,Map<String, Object> config)throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdFareDao epdFareDao = new EpdFareDao(conn);
			EpdFare newFare = epdFareDao.insert(epdFare,config);
			conn.commit();
			return newFare;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public void update(EpdFare epdFare,Map<String, Object> config)throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdFareDao epdFareDao = new EpdFareDao(conn);
			epdFareDao.update(epdFare,config);
			conn.commit();
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public void delete(List<EpdFare> epdFares,Map<String, Object> config)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdFareDao epdFareDao = new EpdFareDao(conn);
			EpdPlanDao epdPlanDao = new EpdPlanDao(conn);
			SamLogDetailDao samLogDetailDao = new SamLogDetailDao(conn);
			if (null != epdFares && epdFares.size()>0){
				List<String> routeSeqs = new ArrayList<String>();
				List<String> routeNames = new ArrayList<String>();
				boolean isAdd = true;
				for (int i = 0; i < epdFares.size(); i++) {
					isAdd = true;
					if (null != routeSeqs && routeSeqs.size()>0){
						for (int j = 0; j < routeSeqs.size(); j++) {
							if (epdFares.get(i).getRouteSeq().equals(routeSeqs.get(j))){
								isAdd = false;
								break;
							}
						}
					}
					if(isAdd == true){
						routeSeqs.add(epdFares.get(i).getRouteSeq());
						routeNames.add(epdFares.get(i).getRouteName());
					}
				}
				for (int i = 0; i < routeSeqs.size(); i++) {
					//如果线路运价已有计划车次启运，不允许删除.
					int count = epdPlanDao.queryCountByRouteSeq(routeSeqs.get(i));
					if (count>0){
						throw new UserBusinessException(routeNames.get(i) +"存在运营计划，不允许删除运价。");
					}
					List<EpdFare> fares = epdFareDao.queryByRouteSeq(routeSeqs.get(i));
					if (null != fares && fares.size()>0){
						for (int j = 0; j < fares.size(); j++) {
							samLogDetailDao.deleteDataLog(epdFares.get(j).getFareSeq()
									, new EpdFare(),config);
						}
					}
					epdFareDao.deleteByRouteSeq(routeSeqs.get(i));
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
	public List<EpdFare> queryByPK(String fareSeq) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdFareDao epdFareDao = new EpdFareDao(conn);
			List<EpdFare> fares = epdFareDao.queryByPK(fareSeq);
			conn.commit();
			return fares;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}
	
	@Override
	public List<EpdFare> queryByAll() throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdFareDao epdFareDao = new EpdFareDao(conn);
			List<EpdFare> fares = epdFareDao.queryByAll();
			conn.commit();
			return fares;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}

	@Override
	public List<EpdFare> queryPageByCustom(String routeSeq, String stationSeq,
			String cargradeSeq, int start, int limit) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdFareDao epdFareDao = new EpdFareDao(conn);
			List<EpdFare> fares = epdFareDao.queryPageByCustom(routeSeq, stationSeq,
					cargradeSeq, start, limit);
			conn.commit();
			return fares;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public  List<EpdFare> queryAllByCustom(String routeSeq, String stationSeq,
			String cargradeSeq) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdFareDao epdFareDao = new EpdFareDao(conn);
			List<EpdFare> fares = epdFareDao.queryAllByCustom(routeSeq, stationSeq, cargradeSeq);
			conn.commit();
			return fares;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public int queryCountByCustom(String routeSeq, String stationSeq,
			String cargradeSeq) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdFareDao epdFareDao = new EpdFareDao(conn);
			int count = epdFareDao.queryCountByCustom(routeSeq, 
					stationSeq, cargradeSeq);
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
	public List<EpdFare> queryByRouteSeq(String routeSeq) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdFareDao epdFareDao = new EpdFareDao(conn);
			List<EpdFare> fares = epdFareDao.queryByRouteSeq(routeSeq);
			conn.commit();
			return fares;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public void updateBatch(String routeSeq, List<EpdFare> epdFares, Map<String, Object> config)
			throws UserBusinessException {
		// TODO Auto-generated method stub
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdFareDao epdFareDao = new EpdFareDao(conn);
			epdFareDao.deleteByRouteSeq(routeSeq);
			if (null != epdFares && epdFares.size()>0){
				for (int i = 0; i < epdFares.size(); i++) {
					if (null != epdFares.get(i).getFareSeq() && epdFares.get(i).getFareSeq().length()>0){
						epdFareDao.update(epdFares.get(i),config);
					}else{
						epdFareDao.insert(epdFares.get(i),config);
					}
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