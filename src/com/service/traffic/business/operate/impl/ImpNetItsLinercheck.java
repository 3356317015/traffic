package com.service.traffic.business.operate.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.service.traffic.business.operate.INetItsLinercheck;
import com.service.traffic.dao.ItsLinerDao;
import com.service.traffic.dao.ItsLinercheckDao;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.basic.jdbc.PoolAlias;
import com.zhima.basic.jdbc.PoolHandler;
import com.zhima.traffic.model.ItsLiner;
import com.zhima.traffic.model.ItsLinercheck;

public class ImpNetItsLinercheck implements INetItsLinercheck {

	@Override
	public List<ItsLinercheck> queryByLinerSeq(String linerSeq)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			ItsLinercheckDao itsLinercheckDao = new ItsLinercheckDao(conn);
			List<ItsLinercheck> linerchecks = itsLinercheckDao.queryByLinerSeq(linerSeq);
			conn.commit();
			return linerchecks;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public void updateAttribute(ItsLiner itsLiner, List<ItsLinercheck> linerchecks,
			Map<String, Object> config)throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			ItsLinerDao itsLinerDao = new ItsLinerDao(conn);
			ItsLinercheckDao itsLinercheckDao = new ItsLinercheckDao(conn);
			itsLinerDao.updateCheckgate(itsLiner);
			itsLinercheckDao.deleteByLinerSeq(itsLiner.getLinerSeq());
			if (null != linerchecks && linerchecks.size()>0){
				for (int i = 0; i < linerchecks.size(); i++) {
					itsLinercheckDao.insert(linerchecks.get(i), config);
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