package com.service.traffic.business.epd.impl;

import java.sql.Connection;
import java.util.List;

import com.service.traffic.business.epd.INetEpdCardriver;
import com.service.traffic.dao.EpdCardriverDao;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.basic.jdbc.PoolAlias;
import com.zhima.basic.jdbc.PoolHandler;
import com.zhima.traffic.model.EpdCardriver;

public class ImpNetEpdCardriver implements INetEpdCardriver {

	@Override
	public List<EpdCardriver> queryByCarSeq(String carSeq) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdCardriverDao cardriverDao = new EpdCardriverDao(conn);
			List<EpdCardriver> cardrivers = cardriverDao.queryByCarSeq(carSeq);
			conn.commit();
			return cardrivers;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

}