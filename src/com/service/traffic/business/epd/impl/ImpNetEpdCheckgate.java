package com.service.traffic.business.epd.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.service.traffic.business.epd.INetEpdCheckgate;
import com.service.traffic.dao.EpdCheckgateDao;
import com.service.traffic.dao.SamLogDetailDao;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.basic.jdbc.PoolAlias;
import com.zhima.basic.jdbc.PoolHandler;
import com.zhima.traffic.model.EpdCheckgate;

public class ImpNetEpdCheckgate implements INetEpdCheckgate {

	@Override
	public EpdCheckgate insert(EpdCheckgate epdCheckgate,Map<String, Object> config)throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdCheckgateDao epdCheckgateDao = new EpdCheckgateDao(conn);
			List<EpdCheckgate> checkgates = epdCheckgateDao.queryByValid(epdCheckgate);
			if (null != checkgates && checkgates.size()>0){
				throw new UserBusinessException("检票口代码已存在，请使用其他代码。");
			}
			EpdCheckgate checkgate = epdCheckgateDao.insert(epdCheckgate,config);
			conn.commit();
			return checkgate;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public void update(EpdCheckgate epdCheckgate,Map<String, Object> config)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdCheckgateDao epdCheckgateDao = new EpdCheckgateDao(conn);
			List<EpdCheckgate> checkgates = epdCheckgateDao.queryByValid(epdCheckgate);
			if (null != checkgates && checkgates.size()>0){
				throw new UserBusinessException("检票口代码已存在，请使用其他代码。");
			}
			epdCheckgateDao.update(epdCheckgate,config);
			conn.commit();
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public void delete(List<EpdCheckgate> checkgates,Map<String, Object> config)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdCheckgateDao epdCheckgateDao = new EpdCheckgateDao(conn);
			SamLogDetailDao samLogDetailDao = new SamLogDetailDao(conn);
			if (null != checkgates && checkgates.size()>0){
				for (int i = 0; i < checkgates.size(); i++) {
					samLogDetailDao.deleteDataLog(checkgates.get(i).getCheckgateSeq()
							, new EpdCheckgate(),config);
					epdCheckgateDao.deleteByPK(checkgates.get(i).getCheckgateSeq());
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
	public List<EpdCheckgate> queryByPK(String checkgateSeq) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdCheckgateDao epdCheckgateDao = new EpdCheckgateDao(conn);
			List<EpdCheckgate> checkgates = epdCheckgateDao.queryByPK(checkgateSeq);
			conn.commit();
			return checkgates;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}
	
	@Override
	public List<EpdCheckgate> queryByAll() throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdCheckgateDao epdCheckgateDao = new EpdCheckgateDao(conn);
			List<EpdCheckgate> checkgates = epdCheckgateDao.queryByAll();
			conn.commit();
			return checkgates;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}

	@Override
	public List<EpdCheckgate> queryPageByCustom(String organizeSeq, String checkCode,
			String checkName, int start, int limit) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdCheckgateDao epdCheckgateDao = new EpdCheckgateDao(conn);
			List<EpdCheckgate> checkgates = epdCheckgateDao.queryPageByCustom(organizeSeq,
					checkCode, checkName, start, limit);
			conn.commit();
			return checkgates;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public List<EpdCheckgate> queryAllByCustom(String organizeSeq, String checkCode,
			String checkName) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdCheckgateDao epdCheckgateDao = new EpdCheckgateDao(conn);
			List<EpdCheckgate> checkgates = epdCheckgateDao.queryAllByCustom(organizeSeq, checkCode, 
					checkName);
			conn.commit();
			return checkgates;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public int queryCountByCustom(String organizeSeq, String checkCode, String checkName)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdCheckgateDao epdCheckgateDao = new EpdCheckgateDao(conn);
			int count = epdCheckgateDao.queryCountByCustom(organizeSeq, checkCode, 
					checkName);
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
	public List<EpdCheckgate> queryByOrganizeSeq(String organizeSeq)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdCheckgateDao epdCheckgateDao = new EpdCheckgateDao(conn);
			List<EpdCheckgate> checkgates = epdCheckgateDao.queryByOrganizeSeq(organizeSeq);
			conn.commit();
			return checkgates;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}

}