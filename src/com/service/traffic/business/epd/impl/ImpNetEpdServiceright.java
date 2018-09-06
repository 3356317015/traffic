package com.service.traffic.business.epd.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.service.traffic.business.epd.INetEpdServiceright;
import com.service.traffic.dao.EpdServicerightDao;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.basic.jdbc.PoolAlias;
import com.zhima.basic.jdbc.PoolHandler;
import com.zhima.traffic.model.EpdServiceright;

/**
 * ImpEpdRegion概要说明：城市区域
 * @author lcy
 */
public class ImpNetEpdServiceright implements INetEpdServiceright {
	
	@Override
	public List<EpdServiceright> queryByServiceSeq(String serviceSeq)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdServicerightDao epdServicerightDao = new EpdServicerightDao(conn);
			List<EpdServiceright> servicerights = epdServicerightDao.queryByServiceSeq(serviceSeq);
			conn.commit();
			return servicerights;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}

	@Override
	public void saveRight(String serviceSeq, List<EpdServiceright> addRights, Map<String, Object> config)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdServicerightDao epdServicerightDao = new EpdServicerightDao(conn);
			epdServicerightDao.deleteByServiceSeq(serviceSeq);
			if (null != addRights && addRights.size()>0){
				for (int i = 0; i < addRights.size(); i++) {
					epdServicerightDao.insert(addRights.get(i), config);
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