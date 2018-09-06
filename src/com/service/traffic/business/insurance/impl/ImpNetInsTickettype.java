package com.service.traffic.business.insurance.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.service.traffic.business.insurance.INetInsTickettype;
import com.service.traffic.dao.InsTickettypeDao;
import com.service.traffic.dao.SamLogDetailDao;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.basic.jdbc.PoolAlias;
import com.zhima.basic.jdbc.PoolHandler;
import com.zhima.traffic.model.EpdTickettype;
import com.zhima.traffic.model.InsTickettype;
public class ImpNetInsTickettype implements INetInsTickettype {

	@Override
	public InsTickettype insert(InsTickettype insTickettype,Map<String, Object> config)throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Insurance);
		try{
			PoolHandler.pool.beginConn(conn);
			InsTickettypeDao insTickettypeDao = new InsTickettypeDao(conn);
			List<InsTickettype> insTickettypes = insTickettypeDao.queryByValid(insTickettype);
			if (null != insTickettypes && insTickettypes.size()>0){
				throw new UserBusinessException("保险票据名称已存在，请使用其他名称。");
			}
			InsTickettype tickettype = insTickettypeDao.insert(insTickettype,config);
			conn.commit();
			return tickettype;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public void update(InsTickettype insTickettype,Map<String, Object> config)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Insurance);
		try{
			PoolHandler.pool.beginConn(conn);
			InsTickettypeDao insTickettypeDao = new InsTickettypeDao(conn);
			List<InsTickettype> insTickettypes = insTickettypeDao.queryByValid(insTickettype);
			if (null != insTickettypes && insTickettypes.size()>0){
				throw new UserBusinessException("保险票据名称已存在，请使用其他名称。");
			}
			insTickettypeDao.update(insTickettype,config);
			conn.commit();
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public void delete(List<InsTickettype> insTickettypes,Map<String, Object> config)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Insurance);
		try{
			PoolHandler.pool.beginConn(conn);
			InsTickettypeDao insTickettypeDao = new InsTickettypeDao(conn);
			SamLogDetailDao samLogDetailDao = new SamLogDetailDao(conn);
			if (null != insTickettypes && insTickettypes.size()>0){
				for (int i = 0; i < insTickettypes.size(); i++) {
					samLogDetailDao.deleteDataLog(insTickettypes.get(i).getTickettypeSeq(), new EpdTickettype(),config);
					insTickettypeDao.deleteByPK(insTickettypes.get(i).getTickettypeSeq());
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
	public List<InsTickettype> queryByPK(String tickettypeSeq) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Insurance);
		try{
			PoolHandler.pool.beginConn(conn);
			InsTickettypeDao insTickettypeDao = new InsTickettypeDao(conn);
			List<InsTickettype> tickettypes = insTickettypeDao.queryByPK(tickettypeSeq);
			conn.commit();
			return tickettypes;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}
	
	@Override
	public List<InsTickettype> queryByOrganizeSeq(String organizeSeq)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Insurance);
		try{
			PoolHandler.pool.beginConn(conn);
			InsTickettypeDao insTickettypeDao = new InsTickettypeDao(conn);
			List<InsTickettype> tickettypes = insTickettypeDao.queryByOrganizeSeq(organizeSeq);
			conn.commit();
			return tickettypes;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}


	@Override
	public List<InsTickettype> queryPageByCustom(String organizeSeq, String companySeq,
			int start, int limit) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Insurance);
		try{
			PoolHandler.pool.beginConn(conn);
			InsTickettypeDao insTickettypeDao = new InsTickettypeDao(conn);
			List<InsTickettype> tickettypes = insTickettypeDao.queryPageByCustom(organizeSeq,
					companySeq, start, limit);
			conn.commit();
			return tickettypes;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public List<InsTickettype> queryAllByCustom(String organizeSeq, String companySeq) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Insurance);
		try{
			PoolHandler.pool.beginConn(conn);
			InsTickettypeDao insTickettypeDao = new InsTickettypeDao(conn);
			List<InsTickettype> tickettypes = insTickettypeDao.queryAllByCustom(organizeSeq,companySeq);
			conn.commit();
			return tickettypes;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public int queryCountByCustom(String organizeSeq, String companySeq)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Insurance);
		try{
			PoolHandler.pool.beginConn(conn);
			InsTickettypeDao insTickettypeDao = new InsTickettypeDao(conn);
			int count = insTickettypeDao.queryCountByCustom(organizeSeq,companySeq);
			conn.commit();
			return count;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

}