package com.service.traffic.business.epd.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.service.traffic.business.epd.INetEpdDealorganize;
import com.service.traffic.dao.EpdDealorganizeDao;
import com.service.traffic.dao.SamLogDetailDao;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.basic.jdbc.PoolAlias;
import com.zhima.basic.jdbc.PoolHandler;
import com.zhima.traffic.model.EpdDealorganize;

/**
 * ImpEpdStation概要说明：站点设置接口
 * @author lcy
 */
public class ImpNetEpdDealorganize implements INetEpdDealorganize {

	@Override
	public EpdDealorganize insert(EpdDealorganize epdDealorganize,Map<String, Object> config)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdDealorganizeDao epdDealorganizeDao = new EpdDealorganizeDao(conn);
			List<EpdDealorganize> dealorganizes = epdDealorganizeDao.queryByValid(epdDealorganize);
			if (null != dealorganizes && dealorganizes.size()>0){
				throw new UserBusinessException("配载站已存在，不允许重复。");
			}
			EpdDealorganize dealorganize = epdDealorganizeDao.insert(epdDealorganize,config);
			conn.commit();
			return dealorganize;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public void update(EpdDealorganize epdDealorganize,Map<String, Object> config) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdDealorganizeDao epdDealorganizeDao = new EpdDealorganizeDao(conn);
			List<EpdDealorganize> dealorganizes = epdDealorganizeDao.queryByValid(epdDealorganize);
			if (null != dealorganizes && dealorganizes.size()>0){
				throw new UserBusinessException("配载站已存在，不允许重复。");
			}
			epdDealorganizeDao.update(epdDealorganize,config);
			conn.commit();
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
		
	}

	@Override
	public void delete(List<EpdDealorganize> dealorganizes,Map<String, Object> config)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdDealorganizeDao epdDealorganizeDao = new EpdDealorganizeDao(conn);
			SamLogDetailDao samLogDetailDao = new SamLogDetailDao(conn);
			if (null != dealorganizes && dealorganizes.size()>0){
				for (int i = 0; i < dealorganizes.size(); i++) {
					samLogDetailDao.deleteDataLog(dealorganizes.get(i).getDealorganizeSeq()
							, new EpdDealorganize(),config);
					epdDealorganizeDao.deleteByPK(dealorganizes.get(i).getDealorganizeSeq());
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
	public List<EpdDealorganize> queryPageByCustom(String organizeSeq, String dealorganize,
			String dealStatus,int start,int limit) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdDealorganizeDao epdDealorganizeDao = new EpdDealorganizeDao(conn);
			List<EpdDealorganize> epdDealorganizes = epdDealorganizeDao.queryPageByCustom(organizeSeq, 
					dealorganize, dealStatus, start, limit);
			conn.commit();
			return epdDealorganizes;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public List<EpdDealorganize> queryAllByCustom(String organizeSeq, String dealorganize, String dealStatus)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdDealorganizeDao epdDealorganizeDao = new EpdDealorganizeDao(conn);
			List<EpdDealorganize> epdDealorganizes = epdDealorganizeDao.queryAllByCustom(organizeSeq, 
					dealorganize, dealStatus);
			conn.commit();
			return epdDealorganizes;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public int queryCountByCustom(String organizeSeq, String dealorganize, String dealStatus)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdDealorganizeDao epdDealorganizeDao = new EpdDealorganizeDao(conn);
			int count = epdDealorganizeDao.queryCountByCustom(organizeSeq, dealorganize, dealStatus);
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
	public List<EpdDealorganize> queryByAll() throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdDealorganizeDao epdDealorganizeDao = new EpdDealorganizeDao(conn);
			List<EpdDealorganize> epdDealorganizes = epdDealorganizeDao.queryByAll();
			conn.commit();
			return epdDealorganizes;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public List<EpdDealorganize> queryByOrganizeSeqAndStatus(
			String organizeSeq, String dealStatus) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdDealorganizeDao epdDealorganizeDao = new EpdDealorganizeDao(conn);
			List<EpdDealorganize> epdDealorganizes = epdDealorganizeDao.queryByOrganizeSeqAndStatus(organizeSeq, dealStatus);
			conn.commit();
			return epdDealorganizes;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

}