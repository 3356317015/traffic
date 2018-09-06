package com.service.traffic.business.voice.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.service.traffic.business.voice.INetVmsRoute;
import com.service.traffic.dao.SamLogDetailDao;
import com.service.traffic.dao.VmsRouteDao;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.basic.jdbc.PoolAlias;
import com.zhima.basic.jdbc.PoolHandler;
import com.zhima.traffic.model.VmsRoute;

/**
 * ImpEpdRegion概要说明：城市区域
 * @author lcy
 */
public class ImpNetVmsRoute implements INetVmsRoute {

	@Override
	public VmsRoute insert(VmsRoute vmsRoute,Map<String, Object> config)
		throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			VmsRouteDao routeDao = new VmsRouteDao(conn);
			VmsRoute newRoute = routeDao.insert(vmsRoute,config);
			
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
	public List<VmsRoute> inserts(List<VmsRoute> vmsRoutes,
			Map<String, Object> config) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			VmsRouteDao routeDao = new VmsRouteDao(conn);
			List<VmsRoute> routes = new ArrayList<VmsRoute>();
			if (null != vmsRoutes && vmsRoutes.size()>0){
				for (int i = 0; i < vmsRoutes.size(); i++) {
					VmsRoute newRoute = routeDao.insert(vmsRoutes.get(i),config);
					routes.add(newRoute);
				}
			}
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
	public void update(VmsRoute vmsRoute,Map<String, Object> config)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			VmsRouteDao routeDao = new VmsRouteDao(conn);
			routeDao.update(vmsRoute,config);
			conn.commit();
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public void delete(List<VmsRoute> vmsRoutes,Map<String, Object> config)throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			VmsRouteDao vmsRouteDao = new VmsRouteDao(conn);
			SamLogDetailDao samLogDetailDao = new SamLogDetailDao(conn);
			if (null != vmsRoutes && vmsRoutes.size()>0){
				for (int i = 0; i < vmsRoutes.size(); i++) {
					samLogDetailDao.deleteDataLog(vmsRoutes.get(i).getRouteSeq()
							, new VmsRoute(),config);
					vmsRouteDao.deleteByPK(vmsRoutes.get(i).getRouteSeq());
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
	public List<VmsRoute> queryByPK(String routeSeq) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			VmsRouteDao vmsRouteDao = new VmsRouteDao(conn);
			List<VmsRoute> routes = vmsRouteDao.queryByPK(routeSeq);
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
	public List<VmsRoute> queryByOrganizeSeq(String organizeSeq)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			VmsRouteDao vmsRouteDao = new VmsRouteDao(conn);
			List<VmsRoute> routes = vmsRouteDao.queryByOrganizeSeq(organizeSeq);
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
	public List<VmsRoute> queryPageByCustom(String organizeSeq, String routeCode, String routeName,
			String voiceStatus,int start,int limit) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			VmsRouteDao vmsRouteDao = new VmsRouteDao(conn);
			List<VmsRoute> routes = vmsRouteDao.queryPageByCustom(organizeSeq, routeCode, routeName,
					voiceStatus, start, limit);
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
	public List<VmsRoute> queryAllByCustom(String organizeSeq, String routeCode, String routeName,
			String voiceStatus) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			VmsRouteDao vmsRouteDao = new VmsRouteDao(conn);
			List<VmsRoute> routes = vmsRouteDao.queryAllByCustom(organizeSeq, routeCode, routeName, voiceStatus);
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
	public int queryCountByCustom(String organizeSeq, String routeCode, String routeName,
			String voiceStatus) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			VmsRouteDao vmsRouteDao = new VmsRouteDao(conn);
			int count = vmsRouteDao.queryCountByCustom(organizeSeq, routeCode, routeName, voiceStatus);
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
	public List<VmsRoute> queryTrafficRoute(String strSql) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Voice);
		try{
			PoolHandler.pool.beginConn(conn);
			VmsRouteDao vmsRouteDao = new VmsRouteDao(conn);
			List<VmsRoute> routes = vmsRouteDao.queryTrafficRoute(strSql);
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