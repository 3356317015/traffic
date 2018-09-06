package com.service.traffic.business.epd.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.service.traffic.business.epd.INetEpdCargrade;
import com.service.traffic.dao.EpdCargradeDao;
import com.service.traffic.dao.SamLogDetailDao;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.basic.jdbc.PoolAlias;
import com.zhima.basic.jdbc.PoolHandler;
import com.zhima.traffic.model.EpdCargrade;
public class ImpNetEpdCargrade implements INetEpdCargrade {

	@Override
	public EpdCargrade insert(EpdCargrade epdCargrade,Map<String, Object> config)throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdCargradeDao epdCargradeDao = new EpdCargradeDao(conn);
			List<EpdCargrade> cargrades = epdCargradeDao.queryByValid(epdCargrade);
			if (null != cargrades && cargrades.size()>0){
				throw new UserBusinessException("车型等级代码已存在，请使用其他代码。");
			}
			EpdCargrade cargrade = epdCargradeDao.insert(epdCargrade,config);
			conn.commit();
			return cargrade;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public void update(EpdCargrade epdCargrade,Map<String, Object> config)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdCargradeDao epdCargradeDao = new EpdCargradeDao(conn);
			List<EpdCargrade> cargrades = epdCargradeDao.queryByValid(epdCargrade);
			if (null != cargrades && cargrades.size()>0){
				throw new UserBusinessException("车型等级代码已存在，请使用其他代码。");
			}
			epdCargradeDao.update(epdCargrade,config);
			conn.commit();
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public void delete(List<EpdCargrade> epdCargrades,Map<String, Object> config)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdCargradeDao epdCargradeDao = new EpdCargradeDao(conn);
			SamLogDetailDao samLogDetailDao = new SamLogDetailDao(conn);
			if (null != epdCargrades && epdCargrades.size()>0){
				for (int i = 0; i < epdCargrades.size(); i++) {
					samLogDetailDao.deleteDataLog(epdCargrades.get(i).getCargradeSeq(), new EpdCargrade(),config);
					epdCargradeDao.deleteByPK(epdCargrades.get(i).getCargradeSeq());
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
	public List<EpdCargrade> queryByPK(String cargradeSeq) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdCargradeDao epdCargradeDao = new EpdCargradeDao(conn);
			List<EpdCargrade> cargrades = epdCargradeDao.queryByPK(cargradeSeq);
			conn.commit();
			return cargrades;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}
	
	@Override
	public List<EpdCargrade> queryByAll() throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdCargradeDao epdCargradeDao = new EpdCargradeDao(conn);
			List<EpdCargrade> cargrades = epdCargradeDao.queryByAll();
			conn.commit();
			return cargrades;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}
	
	@Override
	public List<EpdCargrade> queryByOrganizeSeq(String organizeSeq)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdCargradeDao epdCargradeDao = new EpdCargradeDao(conn);
			List<EpdCargrade> cargrades = epdCargradeDao.queryByByOrganizeSeq(organizeSeq);
			conn.commit();
			return cargrades;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}


	@Override
	public List<EpdCargrade> queryPageByCustom(String organizeSeq, String cargradeCode, String cargradeName, int start, int limit) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdCargradeDao epdCargradeDao = new EpdCargradeDao(conn);
			List<EpdCargrade> cargrades = epdCargradeDao.queryPageByCustom(organizeSeq,cargradeCode, cargradeName, start, limit);
			conn.commit();
			return cargrades;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public List<EpdCargrade> queryAllByCustom(String organizeSeq, String cargradeCode, String cargradeName) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdCargradeDao epdCargradeDao = new EpdCargradeDao(conn);
			List<EpdCargrade> cargrades = epdCargradeDao.queryAllByCustom(organizeSeq,cargradeCode, 
					cargradeName);
			conn.commit();
			return cargrades;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public int queryCountByCustom(String organizeSeq, String cargradeCode, String cargradeName)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdCargradeDao epdCargradeDao = new EpdCargradeDao(conn);
			int count = epdCargradeDao.queryCountByCustom(organizeSeq,cargradeCode, 
					cargradeName);
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
	public List<EpdCargrade> queryByRouteSeq(String routeSeq)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdCargradeDao epdCargradeDao = new EpdCargradeDao(conn);
			List<EpdCargrade> cargrades = epdCargradeDao.queryByRouteSeq(routeSeq);
			conn.commit();
			return cargrades;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}

}