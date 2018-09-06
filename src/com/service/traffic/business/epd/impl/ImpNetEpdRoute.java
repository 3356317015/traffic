package com.service.traffic.business.epd.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.service.traffic.business.epd.INetEpdRoute;
import com.service.traffic.dao.EpdRouteDao;
import com.service.traffic.dao.EpdRouteServiceDao;
import com.service.traffic.dao.EpdRouteStationDao;
import com.service.traffic.dao.SamLogDetailDao;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.basic.jdbc.PoolAlias;
import com.zhima.basic.jdbc.PoolHandler;
import com.zhima.traffic.model.EpdRoute;
import com.zhima.traffic.model.EpdRouteservice;
import com.zhima.traffic.model.EpdRoutestation;
import com.zhima.traffic.model.EpdStation;
import com.zhima.util.DateUtils;

/**
 * ImpEpdRegion概要说明：城市区域
 * @author lcy
 */
public class ImpNetEpdRoute implements INetEpdRoute {

	@Override
	public EpdRoute insert(EpdRoute epdRoute,List<EpdRoutestation> routestations,List<EpdRouteservice> routeservices,
			Map<String, Object> config) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdRouteDao routeDao = new EpdRouteDao(conn);
			EpdRouteStationDao routeStationDao = new EpdRouteStationDao(conn); 
			EpdRouteServiceDao routeServiceDao = new EpdRouteServiceDao(conn); 
			List<EpdRoute> routes = routeDao.queryByValid(epdRoute);
			if (null != routes && routes.size()>0){
				for (int i = 0; i < routes.size(); i++) {
					if (epdRoute.getRouteCode().equals(routes.get(i).getRouteCode())){
						throw new UserBusinessException("站点代码已存在，请使用其他代码。");
					}
					if (epdRoute.getRouteSpell().equals(routes.get(i).getRouteSpell())){
						throw new UserBusinessException("拼音代码已存在，请使用其他代码。");
					}
				}
			}
			String currTime = DateUtils.getNow(DateUtils.FORMAT_LONG);
			epdRoute.setUpdateTime(currTime);
			EpdRoute newRoute = routeDao.insert(epdRoute,config);
			if (null != routestations && routestations.size()>0){
				for (int i = 0; i < routestations.size(); i++) {
					routestations.get(i).setUpdateTime(currTime);
					routestations.get(i).setRouteSeq(newRoute.getRouteSeq());
					routeStationDao.insert(routestations.get(i),config);
				}
			}
			if (null != routeservices && routeservices.size()>0){
				for (int i = 0; i < routeservices.size(); i++) {
					routeservices.get(i).setUpdateTime(currTime);
					routeservices.get(i).setRouteSeq(newRoute.getRouteSeq());
					routeServiceDao.insert(routeservices.get(i),config);
				}
			}
			conn.commit();
			return newRoute;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public void update(EpdRoute epdRoute,List<EpdRoutestation> routestations,
			List<EpdRoutestation> delstations,List<EpdRouteservice> routeservices,Map<String, Object> config)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdRouteDao routeDao = new EpdRouteDao(conn);
			EpdRouteStationDao routeStationDao = new EpdRouteStationDao(conn);
			EpdRouteServiceDao routeServiceDao = new EpdRouteServiceDao(conn); 
			SamLogDetailDao samLogDetailDao = new SamLogDetailDao(conn);
			List<EpdRoute> routes = routeDao.queryByValid(epdRoute);
			if (null != routes && routes.size()>0){
				for (int i = 0; i < routes.size(); i++) {
					if (epdRoute.getRouteCode().equals(routes.get(i).getRouteCode())){
						throw new UserBusinessException("站点代码已存在，请使用其他代码。");
					}
					if (epdRoute.getRouteSpell().equals(routes.get(i).getRouteSpell())){
						throw new UserBusinessException("拼音代码已存在，请使用其他代码。");
					}
				}
			}
			String currTime = DateUtils.getNow(DateUtils.FORMAT_LONG);
			epdRoute.setUpdateTime(currTime);
			routeDao.update(epdRoute,config);
			
			if (null != delstations && delstations.size()>0){
				for (int i = 0; i < delstations.size(); i++) {
					samLogDetailDao.deleteDataLog(delstations.get(i).getRoutestationSeq()
							, new EpdStation(),config);
					routeStationDao.deleteByPK(delstations.get(i).getRoutestationSeq());
				}
			}
			
			if (null != routestations && routestations.size()>0){
				for (int i = 0; i < routestations.size(); i++) {
					routestations.get(i).setStationOrder(i+1);
					routestations.get(i).setUpdateTime(currTime);
					if (null != routestations.get(i).getRoutestationSeq()
							|| routestations.get(i).getRoutestationSeq().length()>0){
						routeStationDao.update(routestations.get(i),config);
					}else{
						routeStationDao.insert(routestations.get(i),config);
					}
				}
			}
			routeServiceDao.deleteByRouteSeq(epdRoute.getRouteSeq());
			if (null != routeservices && routeservices.size()>0){
				for (int i = 0; i < routeservices.size(); i++) {
					routeservices.get(i).setUpdateTime(currTime);
					routeservices.get(i).setRouteSeq(epdRoute.getRouteSeq());
					routeServiceDao.insert(routeservices.get(i),config);
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
	public void delete(List<EpdRoute> epdRoutes,Map<String, Object> config)throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdRouteDao epdRouteDao = new EpdRouteDao(conn);
			EpdRouteStationDao routeStationDao = new EpdRouteStationDao(conn);
			SamLogDetailDao samLogDetailDao = new SamLogDetailDao(conn);
			if (null != epdRoutes && epdRoutes.size()>0){
				for (int i = 0; i < epdRoutes.size(); i++) {
					samLogDetailDao.deleteDataLog(epdRoutes.get(i).getRouteSeq()
							, new EpdRoute(),config);
					epdRouteDao.deleteByPK(epdRoutes.get(i).getRouteSeq());
					routeStationDao.deleteByRouteSeq(epdRoutes.get(i).getRouteSeq());
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
	public List<EpdRoute> queryByPK(String routeSeq) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdRouteDao epdRouteDao = new EpdRouteDao(conn);
			List<EpdRoute> routes = epdRouteDao.queryByPK(routeSeq);
			conn.commit();
			return routes;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}
	
	@Override
	public List<EpdRoute> queryByAll() throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdRouteDao epdRouteDao = new EpdRouteDao(conn);
			List<EpdRoute> routes = epdRouteDao.queryByAll();
			conn.commit();
			return routes;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}
	
	@Override
	public List<EpdRoute> queryByOrganizeSeq(String organizeSeq)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdRouteDao epdRouteDao = new EpdRouteDao(conn);
			List<EpdRoute> routes = epdRouteDao.queryByOrganizeSeq(organizeSeq);
			conn.commit();
			return routes;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}

	@Override
	public List<EpdRoute> queryPageByCustom(String organizeSeq, String routeCode, String routeSpell, String routeName,
			String routeType, String roadType,String routeStatus,int start,int limit) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdRouteDao epdRouteDao = new EpdRouteDao(conn);
			List<EpdRoute> routes = epdRouteDao.queryPageByCustom(organizeSeq, routeCode, routeSpell, routeName,
					routeType, roadType, routeStatus, start, limit);
			conn.commit();
			return routes;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public List<EpdRoute> queryAllByCustom(String organizeSeq, String routeCode, String routeSpell, String routeName,
			String routeType, String roadType,String routeStatus) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdRouteDao epdRouteDao = new EpdRouteDao(conn);
			List<EpdRoute> routes = epdRouteDao.queryAllByCustom(organizeSeq, routeCode, routeSpell, routeName, routeType, roadType, routeStatus);
			conn.commit();
			return routes;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public int queryCountByCustom(String organizeSeq, String routeCode, String routeSpell, String routeName,
			String routeType, String roadType,String routeStatus) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdRouteDao epdRouteDao = new EpdRouteDao(conn);
			int count = epdRouteDao.queryCountByCustom(organizeSeq, routeCode, routeSpell, routeName, routeType, roadType, routeStatus);
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
	public List<EpdRoute> queryByNoFare(String organizeSeq) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdRouteDao epdRouteDao = new EpdRouteDao(conn);
			List<EpdRoute> routes = epdRouteDao.queryByNoFare(organizeSeq);
			conn.commit();
			return routes;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}



}