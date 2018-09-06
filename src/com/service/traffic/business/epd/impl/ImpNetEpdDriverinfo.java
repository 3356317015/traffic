package com.service.traffic.business.epd.impl;

import java.sql.Connection;
import java.util.List;

import com.service.traffic.business.epd.INetEpdDriverinfo;
import com.service.traffic.dao.EpdDriverinfoDao;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.basic.jdbc.PoolAlias;
import com.zhima.basic.jdbc.PoolHandler;
import com.zhima.traffic.model.EpdDriverinfo;

public class ImpNetEpdDriverinfo implements INetEpdDriverinfo {

	@Override
	public List<EpdDriverinfo> queryByDriverSeq(String driverSeq) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdDriverinfoDao driverinfoDao = new EpdDriverinfoDao(conn);
			List<EpdDriverinfo> driverinfos = driverinfoDao.queryByDriverSeq(driverSeq);
			conn.commit();
			return driverinfos;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

}