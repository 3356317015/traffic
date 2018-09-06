package com.service.traffic.business.insurance.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.service.traffic.business.insurance.INetInsTicketpool;
import com.service.traffic.dao.InsTicketpoolDao;
import com.service.traffic.dao.SamLogDetailDao;
import com.service.util.StringUtils;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.basic.jdbc.PoolAlias;
import com.zhima.basic.jdbc.PoolHandler;
import com.zhima.traffic.model.InsTicketpool;
public class ImpNetInsTicketpool implements INetInsTicketpool {

	@Override
	public InsTicketpool insert(InsTicketpool insTicketpool,Map<String, Object> config)throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Insurance);
		try{
			PoolHandler.pool.beginConn(conn);
			InsTicketpoolDao insTicketpoolDao = new InsTicketpoolDao(conn);
			List<InsTicketpool> insTicketpools = insTicketpoolDao.queryByValid(insTicketpool);
			if (null != insTicketpools && insTicketpools.size()>0){
				throw new UserBusinessException("票据号段重复，操作结束");
			}
			InsTicketpool ticketpool = insTicketpoolDao.insert(insTicketpool,config);
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
	public void update(InsTicketpool insTicketpool,Map<String, Object> config)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Insurance);
		try{
			PoolHandler.pool.beginConn(conn);
			InsTicketpoolDao insTicketpoolDao = new InsTicketpoolDao(conn);
			List<InsTicketpool> insTicketpools = insTicketpoolDao.queryByValid(insTicketpool);
			if (null != insTicketpools && insTicketpools.size()>0){
				throw new UserBusinessException("票据号段重复，操作结束。");
			}
			insTicketpoolDao.update(insTicketpool,config);
			conn.commit();
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public void delete(List<InsTicketpool> insTicketpools,Map<String, Object> config)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Insurance);
		try{
			PoolHandler.pool.beginConn(conn);
			InsTicketpoolDao insTicketpoolDao = new InsTicketpoolDao(conn);
			SamLogDetailDao samLogDetailDao = new SamLogDetailDao(conn);
			if (null != insTicketpools && insTicketpools.size()>0){
				for (int i = 0; i < insTicketpools.size(); i++) {
					samLogDetailDao.deleteDataLog(insTicketpools.get(i).getTicketpoolSeq(), new InsTicketpool(),config);
					insTicketpoolDao.deleteByPK(insTicketpools.get(i).getTicketpoolSeq());
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
	public List<InsTicketpool> queryByPK(String ticketpoolSeq) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Insurance);
		try{
			PoolHandler.pool.beginConn(conn);
			InsTicketpoolDao insTicketpoolDao = new InsTicketpoolDao(conn);
			List<InsTicketpool> insTicketpools = insTicketpoolDao.queryByPK(ticketpoolSeq);
			conn.commit();
			return insTicketpools;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}
	
	@Override
	public List<InsTicketpool> queryByOrganizeSeq(String organizeSeq)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Insurance);
		try{
			PoolHandler.pool.beginConn(conn);
			InsTicketpoolDao insTicketpoolDao = new InsTicketpoolDao(conn);
			List<InsTicketpool> insTicketpools = insTicketpoolDao.queryByOrganizeSeq(organizeSeq);
			conn.commit();
			return insTicketpools;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}


	@Override
	public List<InsTicketpool> queryPageByCustom(String organizeSeq, String companySeq, String tickettypeSeq, String operType,
			String userCode, String poolStatus, int start, int limit) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Insurance);
		try{
			PoolHandler.pool.beginConn(conn);
			InsTicketpoolDao insTicketpoolDao = new InsTicketpoolDao(conn);
			List<InsTicketpool> insTicketpools = insTicketpoolDao.queryPageByCustom(organizeSeq,
					companySeq, tickettypeSeq, operType, userCode, poolStatus, start, limit);
			conn.commit();
			if (null != insTicketpools && insTicketpools.size()>0){
				for (int i = 0; i < insTicketpools.size(); i++) {
					insTicketpools.get(i).setTotalNum((int) (Long.valueOf(StringUtils.getNumbers(insTicketpools.get(i).getInsuranceLimit()))
							-Long.valueOf(StringUtils.getNumbers(insTicketpools.get(i).getInsuranceStart())))+1);
					insTicketpools.get(i).setUseNum((int) (Long.valueOf(StringUtils.getNumbers(insTicketpools.get(i).getInsuranceCurrent()))
							-Long.valueOf(StringUtils.getNumbers(insTicketpools.get(i).getInsuranceStart()))));
					insTicketpools.get(i).setUnuseNum(insTicketpools.get(i).getTotalNum()-insTicketpools.get(i).getUseNum());
				}
			}
			return insTicketpools;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public List<InsTicketpool> queryAllByCustom(String organizeSeq, String companySeq, String tickettypeSeq,
			String operType, String userCode, String poolStatus) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Insurance);
		try{
			PoolHandler.pool.beginConn(conn);
			InsTicketpoolDao insTicketpoolDao = new InsTicketpoolDao(conn);
			List<InsTicketpool> insTicketpools = insTicketpoolDao.queryAllByCustom(organizeSeq,
					companySeq, tickettypeSeq, operType, userCode, poolStatus);
			conn.commit();
			return insTicketpools;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}

	@Override
	public int queryCountByCustom(String organizeSeq, String companySeq, String tickettypeSeq, String operType,
			String userCode, String poolStatus) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Insurance);
		try{
			PoolHandler.pool.beginConn(conn);
			InsTicketpoolDao insTicketpoolDao = new InsTicketpoolDao(conn);
			int count = insTicketpoolDao.queryCountByCustom(organizeSeq,
					companySeq, tickettypeSeq, operType, userCode, poolStatus);
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
	public InsTicketpool send(InsTicketpool insTicketpool,
			InsTicketpool newTicketpool, Map<String, Object> config)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Insurance);
		try{
			PoolHandler.pool.beginConn(conn);
			InsTicketpoolDao insTicketpoolDao = new InsTicketpoolDao(conn);
			InsTicketpool ticketpool = insTicketpoolDao.insert(newTicketpool,config);
			insTicketpoolDao.update(insTicketpool, config);
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
	public List<InsTicketpool> queryValid(String organizeSeq,
			String tickettypeSeq, String operType, String userCode) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Insurance);
		try{
			PoolHandler.pool.beginConn(conn);
			InsTicketpoolDao insTicketpoolDao = new InsTicketpoolDao(conn);
			List<InsTicketpool> insTicketpools = insTicketpoolDao.queryValid(organizeSeq,
					 tickettypeSeq, operType, userCode, "2");
			if (null== insTicketpools || insTicketpools.size()<=0){
				insTicketpools = insTicketpoolDao.queryValid(organizeSeq,
						 tickettypeSeq, operType, userCode, "1");
			}
			conn.commit();
			return insTicketpools;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}

}