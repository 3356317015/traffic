package com.service.traffic.business.epd.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.service.traffic.business.epd.INetEpdFaretype;
import com.service.traffic.dao.EpdFaretypeDao;
import com.service.traffic.dao.SamLogDetailDao;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.basic.jdbc.PoolAlias;
import com.zhima.basic.jdbc.PoolHandler;
import com.zhima.traffic.model.EpdFaretype;

public class ImpNetEpdFaretype implements INetEpdFaretype {

	@Override
	public EpdFaretype insert(EpdFaretype epdFaretype,Map<String, Object> config)throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdFaretypeDao epdFaretypeDao = new EpdFaretypeDao(conn);
			List<EpdFaretype> faretypes = epdFaretypeDao.queryByValid(epdFaretype);
			if (null != faretypes && faretypes.size()>0){
				throw new UserBusinessException("票型代码已存在，请使用其他代码。");
			}
			EpdFaretype faretype = epdFaretypeDao.insert(epdFaretype,config);
			conn.commit();
			return faretype;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public void update(EpdFaretype epdFaretype,Map<String, Object> config)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdFaretypeDao epdFaretypeDao = new EpdFaretypeDao(conn);
			List<EpdFaretype> faretypes = epdFaretypeDao.queryByValid(epdFaretype);
			if (null != faretypes && faretypes.size()>0){
				throw new UserBusinessException("票型代码已存在，请使用其他代码。");
			}
			epdFaretypeDao.update(epdFaretype,config);
			conn.commit();
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public void delete(List<EpdFaretype> epdFaretypes,Map<String, Object> config)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdFaretypeDao epdFaretypeDao = new EpdFaretypeDao(conn);
			SamLogDetailDao samLogDetailDao = new SamLogDetailDao(conn);
			if (null != epdFaretypes && epdFaretypes.size()>0){
				for (int i = 0; i < epdFaretypes.size(); i++) {
					samLogDetailDao.deleteDataLog(epdFaretypes.get(i).getFaretypeSeq()
							, new EpdFaretype(),config);
					epdFaretypeDao.deleteByPK(epdFaretypes.get(i).getFaretypeSeq());
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
	public List<EpdFaretype> queryByPK(String faretypeSeq) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdFaretypeDao epdFaretypeDao = new EpdFaretypeDao(conn);
			List<EpdFaretype> faretypes = epdFaretypeDao.queryByPK(faretypeSeq);
			conn.commit();
			return faretypes;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}
	
	@Override
	public List<EpdFaretype> queryByAll() throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdFaretypeDao epdFaretypeDao = new EpdFaretypeDao(conn);
			List<EpdFaretype> faretypes = epdFaretypeDao.queryByAll();
			conn.commit();
			return faretypes;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}

	@Override
	public List<EpdFaretype> queryPageByCustom(String organizeSeq,
			String farettypeCode, String faretypeName, String faretypeStatus,
			int start, int limit) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdFaretypeDao epdFaretypeDao = new EpdFaretypeDao(conn);
			List<EpdFaretype> faretypes = epdFaretypeDao.queryPageByCustom(organizeSeq,
					farettypeCode, faretypeName, faretypeStatus, start, limit);
			conn.commit();
			return faretypes;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public List<EpdFaretype> queryAllByCustom(String organizeSeq,
			String farettypeCode, String faretypeName, String faretypeStatus) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdFaretypeDao epdFaretypeDao = new EpdFaretypeDao(conn);
			List<EpdFaretype> faretypes = epdFaretypeDao.queryAllByCustom(organizeSeq,
					farettypeCode, faretypeName, faretypeStatus);
			conn.commit();
			return faretypes;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public int queryCountByCustom(String organizeSeq,
			String farettypeCode, String faretypeName, String faretypeStatus)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdFaretypeDao epdFaretypeDao = new EpdFaretypeDao(conn);
			int count = epdFaretypeDao.queryCountByCustom(organizeSeq,
					farettypeCode, faretypeName, faretypeStatus);
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
	public List<EpdFaretype> queryByOrganizeSeq(String organizeSeq)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdFaretypeDao epdFaretypeDao = new EpdFaretypeDao(conn);
			List<EpdFaretype> epdFaretypes = epdFaretypeDao.queryByOrganizeSeq(organizeSeq);
			conn.commit();
			return epdFaretypes;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

}