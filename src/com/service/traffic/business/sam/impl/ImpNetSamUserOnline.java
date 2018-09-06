package com.service.traffic.business.sam.impl;

import java.sql.Connection;
import java.util.List;

import com.service.traffic.business.sam.INetSamUserOnline;
import com.service.traffic.dao.SamUserOnlineDao;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.basic.jdbc.PoolAlias;
import com.zhima.basic.jdbc.PoolHandler;
import com.zhima.frame.model.SamUserOnline;
import com.zhima.util.DateUtils;

public class ImpNetSamUserOnline implements INetSamUserOnline {

	@Override
	public List<SamUserOnline> queryPageByAll(String organizeSeq, int start, int limit) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			SamUserOnlineDao samUserOnlineDao = new SamUserOnlineDao(conn);
			List<SamUserOnline> samUserOnlines = samUserOnlineDao.queryPageByAll(organizeSeq,start, limit);
			conn.commit();
			return samUserOnlines;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}
	
	@Override
	public int queryCountByAll(String organizeSeq) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			SamUserOnlineDao samUserOnlineDao = new SamUserOnlineDao(conn);
			int count = samUserOnlineDao.queryCountByAll(organizeSeq);
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
	public void clearUser(String organizeSeq, int onlineUpdateTime) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			SamUserOnlineDao samUserOnlineDao = new SamUserOnlineDao(conn);
			List<SamUserOnline> samUserOnlines = samUserOnlineDao.queryByOrganizeSeq(organizeSeq);
			String currTime = DateUtils.getNow(DateUtils.FORMAT_LONG);
			for (int i = 0; i < samUserOnlines.size(); i++) {
				long count = DateUtils.secondInterval(samUserOnlines.get(i).getOnlineTime(), currTime);
				if (count -10 > onlineUpdateTime){
					samUserOnlineDao.deleteByPK(samUserOnlines.get(i).getOnlineSeq());
				}
			}
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}



	

}
