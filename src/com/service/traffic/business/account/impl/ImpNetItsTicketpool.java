package com.service.traffic.business.account.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.service.traffic.business.account.INetItsTicketpool;
import com.service.traffic.dao.ItsTicketpoolDao;
import com.service.traffic.dao.SamLogDetailDao;
import com.service.util.StringUtils;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.basic.jdbc.PoolAlias;
import com.zhima.basic.jdbc.PoolHandler;
import com.zhima.traffic.model.ItsTicketpool;
public class ImpNetItsTicketpool implements INetItsTicketpool {

	@Override
	public ItsTicketpool insert(ItsTicketpool itsTicketpool,Map<String, Object> config)throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			ItsTicketpoolDao itsTicketpoolDao = new ItsTicketpoolDao(conn);
			List<ItsTicketpool> itsTicketpools = itsTicketpoolDao.queryByValid(itsTicketpool);
			if (null != itsTicketpools && itsTicketpools.size()>0){
				throw new UserBusinessException("票据号段重复，操作结束");
			}
			ItsTicketpool ticketpool = itsTicketpoolDao.insert(itsTicketpool,config);
			conn.commit();
			return ticketpool;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public void update(ItsTicketpool itsTicketpool,Map<String, Object> config)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			ItsTicketpoolDao itsTicketpoolDao = new ItsTicketpoolDao(conn);
			List<ItsTicketpool> itsTicketpools = itsTicketpoolDao.queryByValid(itsTicketpool);
			if (null != itsTicketpools && itsTicketpools.size()>0){
				throw new UserBusinessException("票据号段重复，操作结束。");
			}
			itsTicketpoolDao.update(itsTicketpool,config);
			conn.commit();
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public void delete(List<ItsTicketpool> itsTicketpools,Map<String, Object> config)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			ItsTicketpoolDao itsTicketpoolDao = new ItsTicketpoolDao(conn);
			SamLogDetailDao samLogDetailDao = new SamLogDetailDao(conn);
			if (null != itsTicketpools && itsTicketpools.size()>0){
				for (int i = 0; i < itsTicketpools.size(); i++) {
					samLogDetailDao.deleteDataLog(itsTicketpools.get(i).getTicketpoolSeq(), new ItsTicketpool(),config);
					itsTicketpoolDao.deleteByPK(itsTicketpools.get(i).getTicketpoolSeq());
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
	public List<ItsTicketpool> queryByPK(String ticketpoolSeq) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			ItsTicketpoolDao itsTicketpoolDao = new ItsTicketpoolDao(conn);
			List<ItsTicketpool> itsTicketpools = itsTicketpoolDao.queryByPK(ticketpoolSeq);
			conn.commit();
			return itsTicketpools;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}
	
	@Override
	public List<ItsTicketpool> queryByOrganizeSeq(String organizeSeq)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			ItsTicketpoolDao itsTicketpoolDao = new ItsTicketpoolDao(conn);
			List<ItsTicketpool> itsTicketpools = itsTicketpoolDao.queryByOrganizeSeq(organizeSeq);
			conn.commit();
			return itsTicketpools;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}


	@Override
	public List<ItsTicketpool> queryPageByCustom(String organizeSeq, String tickettypeSeq, String operType,
			String userCode, String poolStatus, int start, int limit) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			ItsTicketpoolDao itsTicketpoolDao = new ItsTicketpoolDao(conn);
			List<ItsTicketpool> itsTicketpools = itsTicketpoolDao.queryPageByCustom(organizeSeq,
					tickettypeSeq, operType, userCode, poolStatus, start, limit);
			conn.commit();
			if (null != itsTicketpools && itsTicketpools.size()>0){
				for (int i = 0; i < itsTicketpools.size(); i++) {
					itsTicketpools.get(i).setTotalNum((int) (Long.valueOf(StringUtils.getNumbers(itsTicketpools.get(i).getTicketLimit()))
							-Long.valueOf(StringUtils.getNumbers(itsTicketpools.get(i).getTicketStart())))+1);
					itsTicketpools.get(i).setUseNum((int) (Long.valueOf(StringUtils.getNumbers(itsTicketpools.get(i).getTicketCurrent()))
							-Long.valueOf(StringUtils.getNumbers(itsTicketpools.get(i).getTicketStart()))));
					itsTicketpools.get(i).setUnuseNum(itsTicketpools.get(i).getTotalNum()-itsTicketpools.get(i).getUseNum());
				}
			}
			return itsTicketpools;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public List<ItsTicketpool> queryAllByCustom(String organizeSeq, String tickettypeSeq, String operType,
			String userCode, String poolStatus) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			ItsTicketpoolDao itsTicketpoolDao = new ItsTicketpoolDao(conn);
			List<ItsTicketpool> itsTicketpools = itsTicketpoolDao.queryAllByCustom(organizeSeq,
					tickettypeSeq, operType, userCode, poolStatus);
			conn.commit();
			if (null != itsTicketpools && itsTicketpools.size()>0){
				for (int i = 0; i < itsTicketpools.size(); i++) {
					itsTicketpools.get(i).setTotalNum((int) (Long.valueOf(StringUtils.getNumbers(itsTicketpools.get(i).getTicketLimit()))
							-Long.valueOf(StringUtils.getNumbers(itsTicketpools.get(i).getTicketStart())))+1);
					itsTicketpools.get(i).setUseNum((int) (Long.valueOf(StringUtils.getNumbers(itsTicketpools.get(i).getTicketCurrent()))
							-Long.valueOf(StringUtils.getNumbers(itsTicketpools.get(i).getTicketStart()))));
					itsTicketpools.get(i).setUnuseNum(itsTicketpools.get(i).getTotalNum()-itsTicketpools.get(i).getUseNum());
				}
			}
			return itsTicketpools;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}

	@Override
	public int queryCountByCustom(String organizeSeq, String tickettypeSeq, String operType,
			String userCode, String poolStatus) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			ItsTicketpoolDao itsTicketpoolDao = new ItsTicketpoolDao(conn);
			int count = itsTicketpoolDao.queryCountByCustom(organizeSeq,
					tickettypeSeq, operType, userCode, poolStatus);
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
	public ItsTicketpool send(ItsTicketpool itsTicketpool,
			ItsTicketpool newTicketpool, Map<String, Object> config)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			ItsTicketpoolDao itsTicketpoolDao = new ItsTicketpoolDao(conn);
			ItsTicketpool ticketpool = itsTicketpoolDao.insert(newTicketpool,config);
			itsTicketpoolDao.update(itsTicketpool, config);
			conn.commit();
			return ticketpool;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

}