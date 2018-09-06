package com.service.traffic.business.epd.impl;

import java.sql.Connection;
import java.util.List;

import com.service.traffic.business.epd.INetEpdCarinfo;
import com.service.traffic.dao.EpdCarinfoDao;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.basic.jdbc.PoolAlias;
import com.zhima.basic.jdbc.PoolHandler;
import com.zhima.traffic.model.EpdCarinfo;

public class ImpNetEpdCarinfo implements INetEpdCarinfo {

	@Override
	public List<EpdCarinfo> queryByCarSeq(String carSeq) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdCarinfoDao carinfoDao = new EpdCarinfoDao(conn);
			List<EpdCarinfo> carinfos = carinfoDao.queryByCarSeq(carSeq);
			conn.commit();
			return carinfos;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

}