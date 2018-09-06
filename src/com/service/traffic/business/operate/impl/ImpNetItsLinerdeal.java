package com.service.traffic.business.operate.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.service.traffic.business.operate.INetItsLinerdeal;
import com.service.traffic.dao.ItsLinerDao;
import com.service.traffic.dao.ItsLinerdealDao;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.basic.jdbc.PoolAlias;
import com.zhima.basic.jdbc.PoolHandler;
import com.zhima.traffic.model.ItsLiner;
import com.zhima.traffic.model.ItsLinerdeal;

public class ImpNetItsLinerdeal implements INetItsLinerdeal {

	@Override
	public List<ItsLinerdeal> queryByLinerSeq(String linerSeq)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			ItsLinerdealDao itsLinerdealDao = new ItsLinerdealDao(conn);
			List<ItsLinerdeal> linerdeals = itsLinerdealDao.queryByLinerSeq(linerSeq);
			conn.commit();
			return linerdeals;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}
	
	@Override
	public void updateAttribute(ItsLiner itsLiner, List<ItsLinerdeal> itsLinerdeals, Map<String, Object> config)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			ItsLinerDao itsLinerDao = new ItsLinerDao(conn);
			ItsLinerdealDao itsLinerdealDao = new ItsLinerdealDao(conn);
			itsLinerDao.updateDeal(itsLiner);
			itsLinerdealDao.deleteByLinerSeq(itsLiner.getLinerSeq());
			if (null != itsLinerdeals && itsLinerdeals.size()>0){
				for (int i = 0; i < itsLinerdeals.size(); i++) {
					itsLinerdeals.get(i).setLinerSeq(itsLiner.getLinerSeq());
					itsLinerdeals.get(i).setLinerDate(itsLiner.getLinerDate());
					itsLinerdeals.get(i).setLinerId(itsLiner.getLinerId());
					itsLinerdeals.get(i).setUpdateTime(itsLiner.getUpdateTime());
					itsLinerdealDao.insert(itsLinerdeals.get(i), config);
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