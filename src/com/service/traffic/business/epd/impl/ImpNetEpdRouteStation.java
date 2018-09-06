package com.service.traffic.business.epd.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.service.traffic.business.epd.INetEpdRouteStation;
import com.service.traffic.dao.EpdRouteStationDao;
import com.service.traffic.dao.SamLogDetailDao;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.basic.jdbc.PoolAlias;
import com.zhima.basic.jdbc.PoolHandler;
import com.zhima.traffic.model.EpdRoute;
import com.zhima.traffic.model.EpdRoutestation;
import com.zhima.util.DateUtils;

/**
 * ImpEpdRouteStation概要说明：线路站点
 * @author lcy
 */
public class ImpNetEpdRouteStation implements INetEpdRouteStation {

	@Override
	public EpdRoutestation insert(EpdRoutestation epdRoutestation,Map<String, Object> config)throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdRouteStationDao routeStationDao = new EpdRouteStationDao(conn);
			String currTime = DateUtils.getNow(DateUtils.FORMAT_LONG);
			epdRoutestation.setUpdateTime(currTime);
			EpdRoutestation routestation = routeStationDao.insert(epdRoutestation,config);
			conn.commit();
			return routestation;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public void update(EpdRoutestation epdRoutestation,Map<String, Object> config)throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdRouteStationDao routeStationDao = new EpdRouteStationDao(conn); 
			String currTime = DateUtils.getNow(DateUtils.FORMAT_LONG);
			epdRoutestation.setUpdateTime(currTime);
			routeStationDao.update(epdRoutestation,config);
			conn.commit();
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public void delete(List<EpdRoutestation> epdRoutestations,Map<String, Object> config)throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdRouteStationDao routeStationDao = new EpdRouteStationDao(conn);
			SamLogDetailDao samLogDetailDao = new SamLogDetailDao(conn);
			if (null != epdRoutestations && epdRoutestations.size()>0){
				for (int i = 0; i < epdRoutestations.size(); i++) {
					samLogDetailDao.deleteDataLog(epdRoutestations.get(i).getRoutestationSeq()
							, new EpdRoute(),config);
					routeStationDao.deleteByPK(epdRoutestations.get(i).getRoutestationSeq());
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
	public List<EpdRoutestation> queryByPK(String routestationSeq) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdRouteStationDao routeStationDao = new EpdRouteStationDao(conn);
			List<EpdRoutestation> routestations = routeStationDao.queryByPK(routestationSeq);
			conn.commit();
			return routestations;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}

	@Override
	public List<EpdRoutestation> queryByRouteSeq(String routeSeq) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdRouteStationDao routeStationDao = new EpdRouteStationDao(conn);
			List<EpdRoutestation> routestations = routeStationDao.queryByRouteSeq(routeSeq);
			conn.commit();
			return routestations;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}
	

}