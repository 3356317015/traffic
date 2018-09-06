package com.service.traffic.business.epd.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.service.traffic.business.epd.INetEpdReturnrate;
import com.service.traffic.dao.EpdReturnrateDao;
import com.service.traffic.dao.SamLogDetailDao;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.basic.jdbc.PoolAlias;
import com.zhima.basic.jdbc.PoolHandler;
import com.zhima.traffic.model.EpdReturnrate;

public class ImpNetEpdReturnrate implements INetEpdReturnrate {

	@Override
	public EpdReturnrate insert(EpdReturnrate epdReturnrate, Map<String, Object> config)throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdReturnrateDao epdReturnrateDao = new EpdReturnrateDao(conn);
			List<EpdReturnrate> returnrates = epdReturnrateDao.queryByValid(epdReturnrate);
			if (null != returnrates && returnrates.size()>0){
				throw new UserBusinessException("退票费率代码已存在，请使用其他代码。");
			}
			EpdReturnrate returnrate = epdReturnrateDao.insert(epdReturnrate,config);
			conn.commit();
			return returnrate;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public void update(EpdReturnrate epdReturnrate, Map<String, Object> config)throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdReturnrateDao epdReturnrateDao = new EpdReturnrateDao(conn);
			List<EpdReturnrate> returnrates = epdReturnrateDao.queryByValid(epdReturnrate);
			if (null != returnrates && returnrates.size()>0){
				throw new UserBusinessException("退票费率代码已存在，请使用其他代码。");
			}
			epdReturnrateDao.update(epdReturnrate,config);
			conn.commit();
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public void delete(List<EpdReturnrate> epdReturnrates, Map<String, Object> config)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdReturnrateDao epdReturnrateDao = new EpdReturnrateDao(conn);
			SamLogDetailDao samLogDetailDao = new SamLogDetailDao(conn);
			if (null != epdReturnrates && epdReturnrates.size()>0){
				for (int i = 0; i < epdReturnrates.size(); i++) {
					samLogDetailDao.deleteDataLog(epdReturnrates.get(i).getReturnrateSeq()
							, new EpdReturnrate(),config);
					epdReturnrateDao.deleteByPK(epdReturnrates.get(i).getReturnrateSeq());
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
	public List<EpdReturnrate> queryByPK(String returnrateSeq) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdReturnrateDao epdReturnrateDao = new EpdReturnrateDao(conn);
			List<EpdReturnrate> returnrates = epdReturnrateDao.queryByPK(returnrateSeq);
			conn.commit();
			return returnrates;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}
	
	@Override
	public List<EpdReturnrate> queryByAll() throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdReturnrateDao epdReturnrateDao = new EpdReturnrateDao(conn);
			List<EpdReturnrate> returnrates = epdReturnrateDao.queryByAll();
			conn.commit();
			return returnrates;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}

	@Override
	public List<EpdReturnrate> queryPageByCustom(String organizeSeq, String returnrateCode, String returnrateName,
			int start, int limit) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdReturnrateDao epdReturnrateDao = new EpdReturnrateDao(conn);
			List<EpdReturnrate> returnrates = epdReturnrateDao.queryPageByCustom(organizeSeq,
					returnrateCode, returnrateName, start, limit);
			conn.commit();
			return returnrates;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public List<EpdReturnrate> queryAllByCustom(String organizeSeq, String returnrateCode,
			String returnrateName) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdReturnrateDao epdReturnrateDao = new EpdReturnrateDao(conn);
			List<EpdReturnrate> returnrates = epdReturnrateDao.queryAllByCustom(organizeSeq,
					returnrateCode, returnrateName);
			conn.commit();
			return returnrates;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public int queryCountByCustom(String organizeSeq, String returnrateCode,
			String returnrateName) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdReturnrateDao epdReturnrateDao = new EpdReturnrateDao(conn);
			int count = epdReturnrateDao.queryCountByCustom(organizeSeq, returnrateCode, returnrateCode);
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
	public List<EpdReturnrate> queryByOrganizeSeq(String organizeSeq)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdReturnrateDao epdReturnrateDao = new EpdReturnrateDao(conn);
			List<EpdReturnrate> returnrates = epdReturnrateDao.queryByOrganizeSeq(organizeSeq);
			conn.commit();
			return returnrates;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

}