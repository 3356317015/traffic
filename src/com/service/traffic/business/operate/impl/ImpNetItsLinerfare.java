package com.service.traffic.business.operate.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.service.traffic.business.operate.INetItsLinerfare;
import com.service.traffic.dao.ItsLinerfareDao;
import com.service.traffic.dao.SamLogDetailDao;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.basic.jdbc.PoolAlias;
import com.zhima.basic.jdbc.PoolHandler;
import com.zhima.traffic.model.ItsLinerfare;

public class ImpNetItsLinerfare implements INetItsLinerfare {

	@Override
	public ItsLinerfare insert(ItsLinerfare itsLinerfare,Map<String, Object> config)throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			ItsLinerfareDao ItsLinerfareDao = new ItsLinerfareDao(conn);
			ItsLinerfare newLinerfare = ItsLinerfareDao.insert(itsLinerfare,config);
			conn.commit();
			return newLinerfare;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public void update(ItsLinerfare itsLinerfare,Map<String, Object> config)throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			ItsLinerfareDao ItsLinerfareDao = new ItsLinerfareDao(conn);
			ItsLinerfareDao.update(itsLinerfare,config);
			conn.commit();
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public void delete(List<ItsLinerfare> itsLinerfares,Map<String, Object> config)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			ItsLinerfareDao ItsLinerfareDao = new ItsLinerfareDao(conn);
			SamLogDetailDao samLogDetailDao = new SamLogDetailDao(conn);
			if (null != itsLinerfares && itsLinerfares.size()>0){
				for (int i = 0; i < itsLinerfares.size(); i++) {
					samLogDetailDao.deleteDataLog(itsLinerfares.get(i).getLinerfareSeq(), new ItsLinerfare(),config);
					ItsLinerfareDao.deleteByPK(itsLinerfares.get(i).getLinerfareSeq());
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
	public List<ItsLinerfare> queryByPK(String linerfareSeq) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			ItsLinerfareDao ItsLinerfareDao = new ItsLinerfareDao(conn);
			List<ItsLinerfare> fares = ItsLinerfareDao.queryByPK(linerfareSeq);
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
	public List<ItsLinerfare> queryByAll() throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			ItsLinerfareDao ItsLinerfareDao = new ItsLinerfareDao(conn);
			List<ItsLinerfare> linerfares = ItsLinerfareDao.queryByAll();
			conn.commit();
			return linerfares;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}

	@Override
	public List<ItsLinerfare> queryPageByCustom(String organizeSeq,
			String routeSeq, String stationSeq,
			String linerId, String startDate, String limitDate, int start, int limit
			) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			ItsLinerfareDao ItsLinerfareDao = new ItsLinerfareDao(conn);
			List<ItsLinerfare> linerfares = ItsLinerfareDao.queryPageByCustom(organizeSeq,
					routeSeq, stationSeq,
					linerId, startDate, limitDate, start, limit);
			conn.commit();
			return linerfares;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public  List<ItsLinerfare> queryAllByCustom(String organizeSeq,
			String routeSeq, String stationSeq,
			String linerId, String startDate, String limitDate) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			ItsLinerfareDao ItsLinerfareDao = new ItsLinerfareDao(conn);
			List<ItsLinerfare> linerfares = ItsLinerfareDao.queryAllByCustom(organizeSeq,
					routeSeq, stationSeq,
					linerId, startDate, limitDate);
			conn.commit();
			return linerfares;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public int queryCountByCustom(String organizeSeq,String routeSeq, String stationSeq,
			String linerId, String startDate, String limitDate) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			ItsLinerfareDao ItsLinerfareDao = new ItsLinerfareDao(conn);
			int count = ItsLinerfareDao.queryCountByCustom(organizeSeq,
					routeSeq, stationSeq,
					linerId, startDate, limitDate);
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
	public List<ItsLinerfare> queryByLinerSeq(String linerSeq)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			ItsLinerfareDao ItsLinerfareDao = new ItsLinerfareDao(conn);
			List<ItsLinerfare> fares = ItsLinerfareDao.queryByLinerSeq(linerSeq);
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
	public void updateAttribute(List<ItsLinerfare> itsLinerfares,
			Map<String, Object> config) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			ItsLinerfareDao ItsLinerfareDao = new ItsLinerfareDao(conn);
			if (null != itsLinerfares && itsLinerfares.size()>0){
				for (int i = 0; i < itsLinerfares.size(); i++) {
					ItsLinerfareDao.update(itsLinerfares.get(i),config);
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