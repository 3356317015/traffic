package com.service.traffic.business.sam.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.service.traffic.business.sam.INetSamLogOper;
import com.service.traffic.dao.SamLogDetailDao;
import com.service.traffic.dao.SamLogOperDao;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.basic.jdbc.PoolAlias;
import com.zhima.basic.jdbc.PoolHandler;
import com.zhima.frame.model.SamLogOper;

/**
 * ImpSamLogOper概要说明：日志操作接口
 * @author lcy
 */
public class ImpNetSamLogOper implements INetSamLogOper {
	
	@Override
	public SamLogOper insert(SamLogOper samLogOper,Map<String, Object> config)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			SamLogOperDao samLogOperDao = new SamLogOperDao(conn);
			SamLogOper newSamLogOper = samLogOperDao.insert(samLogOper,config);
			conn.commit();
			return newSamLogOper;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}

	@Override
	public void update(SamLogOper samLogOper, String status, String operDesc)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			SamLogOperDao samLogOperDao = new SamLogOperDao(conn);
			samLogOperDao.updateBypk(samLogOper.getOperLogSeq(), status, operDesc);
			conn.commit();
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
		
	}

	@Override
	public List<SamLogOper> queryPageByCustom(String organizeSeq, String moduleCode,
			String operType, String status, String operUser, String startDate,
			String endDate, int start, int limit) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			SamLogOperDao samLogOperDao = new SamLogOperDao(conn);
			List<SamLogOper> samLogOpers = samLogOperDao.queryPageByCustom(organizeSeq,moduleCode, operType, 
					status, operUser, startDate, endDate, start, limit);
			conn.commit();
			return samLogOpers;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}

	@Override
	public List<SamLogOper> queryAllByCustom(String organizeSeq, String moduleCode,
			String operType, String status, String operUser, String startDate,
			String endDate) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			SamLogOperDao samLogOperDao = new SamLogOperDao(conn);
			List<SamLogOper> samLogOpers = samLogOperDao.queryAllByCustom(organizeSeq, moduleCode, operType,
					status, operUser, startDate, endDate);
			conn.commit();
			return samLogOpers;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}

	@Override
	public int queryCountByCustom(String organizeSeq, String moduleCode, String operType,
			String status, String operUser, String startDate, String endDate)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			SamLogOperDao samLogOperDao = new SamLogOperDao(conn);
			int count = samLogOperDao.queryCountByCustom(organizeSeq, moduleCode, operType,
					status, operUser, startDate, endDate);
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
	public void deleteByOperSeq(List<SamLogOper> samLogOpers,Map<String, Object> config)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			SamLogOperDao samLogOperDao = new SamLogOperDao(conn);
			SamLogDetailDao samLogDetailDao = new SamLogDetailDao(conn);
			if (null != samLogOpers && samLogOpers.size()>0){
				for (int i = 0; i < samLogOpers.size(); i++) {
					samLogOperDao.deleteByPK(samLogOpers.get(i).getOperLogSeq());
					samLogDetailDao.deleteByOperSeq(samLogOpers.get(i).getOperLogSeq());
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