package com.service.traffic.business.epd.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.service.traffic.business.epd.INetEpdRouteService;
import com.service.traffic.dao.EpdRouteServiceDao;
import com.service.traffic.dao.SamLogDetailDao;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.basic.jdbc.PoolAlias;
import com.zhima.basic.jdbc.PoolHandler;
import com.zhima.traffic.model.EpdRouteservice;
import com.zhima.util.DateUtils;

public class ImpNetEpdRouteService implements INetEpdRouteService {

	@Override
	public EpdRouteservice insert(EpdRouteservice epdRouteservice,Map<String, Object> config)throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdRouteServiceDao routeServiceDao= new EpdRouteServiceDao(conn);
			String currTime = DateUtils.getNow(DateUtils.FORMAT_LONG);
			epdRouteservice.setUpdateTime(currTime);
			EpdRouteservice routeservice = routeServiceDao.insert(epdRouteservice,config);
			conn.commit();
			return routeservice;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public void update(EpdRouteservice epdRouteservice,Map<String, Object> config)throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdRouteServiceDao routeServiceDao= new EpdRouteServiceDao(conn);
			String currTime = DateUtils.getNow(DateUtils.FORMAT_LONG);
			epdRouteservice.setUpdateTime(currTime);
			routeServiceDao.update(epdRouteservice,config);
			conn.commit();
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public void delete(List<EpdRouteservice> epdRouteservices,Map<String, Object> config)throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdRouteServiceDao routeServiceDao= new EpdRouteServiceDao(conn);
			SamLogDetailDao samLogDetailDao = new SamLogDetailDao(conn);
			if (null != epdRouteservices && epdRouteservices.size()>0){
				for (int i = 0; i < epdRouteservices.size(); i++) {
					samLogDetailDao.deleteDataLog(epdRouteservices.get(i).getRouteserviceSeq()
							, new EpdRouteservice(),config);
					routeServiceDao.deleteByPK(epdRouteservices.get(i).getRouteserviceSeq());
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
	public List<EpdRouteservice> queryByPK(String routeserviceSeq) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdRouteServiceDao routeServiceDao= new EpdRouteServiceDao(conn);
			List<EpdRouteservice> routeservices = routeServiceDao.queryByPK(routeserviceSeq);
			conn.commit();
			return routeservices;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}

	@Override
	public List<EpdRouteservice> queryByRouteSeq(String routeSeq) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdRouteServiceDao routeServiceDao= new EpdRouteServiceDao(conn);
			List<EpdRouteservice> routeservices = routeServiceDao.queryByRouteSeq(routeSeq);
			conn.commit();
			return routeservices;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}
	

}