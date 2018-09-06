package com.service.traffic.business.sam.impl;

import java.sql.Connection;
import java.util.List;

import com.service.traffic.business.sam.INetSamLogDetail;
import com.service.traffic.dao.SamLogDetailDao;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.basic.jdbc.PoolAlias;
import com.zhima.basic.jdbc.PoolHandler;
import com.zhima.frame.model.SamLogDetail;

/**
 * ImpSamLogOper概要说明：日志操作接口
 * @author lcy
 */
public class ImpNetSamLogDetail implements INetSamLogDetail {

	@Override
	public List<SamLogDetail> queryByOperSeq(String operSeq)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			SamLogDetailDao samLogDetailDao = new SamLogDetailDao(conn);
			List<SamLogDetail> samLogDetails = samLogDetailDao.queryByOperSeq(operSeq);
			conn.commit();
			return samLogDetails;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}

}