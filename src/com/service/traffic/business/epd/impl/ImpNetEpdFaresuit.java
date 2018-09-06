package com.service.traffic.business.epd.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.service.traffic.business.epd.INetEpdFaresuit;
import com.service.traffic.dao.EpdFaresuitDao;
import com.service.traffic.dao.SamLogDetailDao;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.basic.jdbc.PoolAlias;
import com.zhima.basic.jdbc.PoolHandler;
import com.zhima.traffic.model.EpdFaresuit;

public class ImpNetEpdFaresuit implements INetEpdFaresuit {

	@Override
	public EpdFaresuit insert(EpdFaresuit epdFaresuit, Map<String, Object> config)throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdFaresuitDao epdFaresuitDao = new EpdFaresuitDao(conn);
			List<EpdFaresuit> faresuits = epdFaresuitDao.queryByValid(epdFaresuit);
			if (null != faresuits && faresuits.size()>0){
				throw new UserBusinessException("价套代码已存在，请使用其他代码。");
			}
			EpdFaresuit faresuit = epdFaresuitDao.insert(epdFaresuit,config);
			conn.commit();
			return faresuit;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public void update(EpdFaresuit epdFaresuit, Map<String, Object> config)throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdFaresuitDao epdFaresuitDao = new EpdFaresuitDao(conn);
			List<EpdFaresuit> faresuits = epdFaresuitDao.queryByValid(epdFaresuit);
			if (null != faresuits && faresuits.size()>0){
				throw new UserBusinessException("价套代码已存在，请使用其他代码。");
			}
			epdFaresuitDao.update(epdFaresuit,config);
			conn.commit();
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public void delete(List<EpdFaresuit> faresuits, Map<String, Object> config)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdFaresuitDao epdFaresuitDao = new EpdFaresuitDao(conn);
			SamLogDetailDao samLogDetailDao = new SamLogDetailDao(conn);
			if (null != faresuits && faresuits.size()>0){
				for (int i = 0; i < faresuits.size(); i++) {
					samLogDetailDao.deleteDataLog(faresuits.get(i).getFaresuitSeq()
							, new EpdFaresuit(),config);
					epdFaresuitDao.deleteByPK(faresuits.get(i).getFaresuitSeq());
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
	public List<EpdFaresuit> queryByPK(String faresuitSeq) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdFaresuitDao epdFaresuitDao = new EpdFaresuitDao(conn);
			List<EpdFaresuit> faresuits = epdFaresuitDao.queryByPK(faresuitSeq);
			conn.commit();
			return faresuits;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}
	
	@Override
	public List<EpdFaresuit> queryByAll(String organizeSeq) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdFaresuitDao epdFaresuitDao = new EpdFaresuitDao(conn);
			List<EpdFaresuit> faresuits = epdFaresuitDao.queryByAll(organizeSeq);
			conn.commit();
			return faresuits;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}

	@Override
	public List<EpdFaresuit> queryPageByCustom(String organizeSeq, String faresuitCode, String faresuitName,
			int start, int limit) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdFaresuitDao epdFaresuitDao = new EpdFaresuitDao(conn);
			List<EpdFaresuit> faresuits = epdFaresuitDao.queryPageByCustom(organizeSeq,faresuitCode, 
					faresuitName, start, limit);
			conn.commit();
			return faresuits;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public List<EpdFaresuit> queryAllByCustom(String organizeSeq, String faresuitCode, String faresuitName) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdFaresuitDao epdFaresuitDao = new EpdFaresuitDao(conn);
			List<EpdFaresuit> faresuits = epdFaresuitDao.queryAllByCustom(organizeSeq,faresuitCode, 
					faresuitName);
			conn.commit();
			return faresuits;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public int queryCountByCustom(String organizeSeq, String faresuitCode, String faresuitName)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdFaresuitDao epdFaresuitDao = new EpdFaresuitDao(conn);
			int count = epdFaresuitDao.queryCountByCustom(organizeSeq,faresuitCode, 
					faresuitName);
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
	public List<EpdFaresuit> queryByOrganizeSeq(String organizeSeq)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdFaresuitDao epdFaresuitDao = new EpdFaresuitDao(conn);
			List<EpdFaresuit> faresuits = epdFaresuitDao.queryByOrganizeSeq(organizeSeq);
			conn.commit();
			return faresuits;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}

}