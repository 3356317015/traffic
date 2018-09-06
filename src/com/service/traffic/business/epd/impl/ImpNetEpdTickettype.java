package com.service.traffic.business.epd.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.service.traffic.business.epd.INetEpdTickettype;
import com.service.traffic.dao.EpdTickettypeDao;
import com.service.traffic.dao.SamLogDetailDao;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.basic.jdbc.PoolAlias;
import com.zhima.basic.jdbc.PoolHandler;
import com.zhima.traffic.model.EpdTickettype;
public class ImpNetEpdTickettype implements INetEpdTickettype {

	@Override
	public EpdTickettype insert(EpdTickettype epdTickettype,Map<String, Object> config)throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdTickettypeDao epdTickettypeDao = new EpdTickettypeDao(conn);
			List<EpdTickettype> epdTickettypes = epdTickettypeDao.queryByValid(epdTickettype);
			if (null != epdTickettypes && epdTickettypes.size()>0){
				throw new UserBusinessException("票据类型名称已存在，请使用其他名称。");
			}
			EpdTickettype tickettype = epdTickettypeDao.insert(epdTickettype,config);
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
	public void update(EpdTickettype epdTickettype,Map<String, Object> config)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdTickettypeDao epdTickettypeDao = new EpdTickettypeDao(conn);
			List<EpdTickettype> epdTickettypes = epdTickettypeDao.queryByValid(epdTickettype);
			if (null != epdTickettypes && epdTickettypes.size()>0){
				throw new UserBusinessException("票据类型名称已存在，请使用其他名称。");
			}
			epdTickettypeDao.update(epdTickettype,config);
			conn.commit();
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public void delete(List<EpdTickettype> epdTickettypes,Map<String, Object> config)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdTickettypeDao epdTickettypeDao = new EpdTickettypeDao(conn);
			SamLogDetailDao samLogDetailDao = new SamLogDetailDao(conn);
			if (null != epdTickettypes && epdTickettypes.size()>0){
				for (int i = 0; i < epdTickettypes.size(); i++) {
					samLogDetailDao.deleteDataLog(epdTickettypes.get(i).getTickettypeSeq(), new EpdTickettype(),config);
					epdTickettypeDao.deleteByPK(epdTickettypes.get(i).getTickettypeSeq());
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
	public List<EpdTickettype> queryByPK(String tickettypeSeq) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdTickettypeDao epdTickettypeDao = new EpdTickettypeDao(conn);
			List<EpdTickettype> tickettypes = epdTickettypeDao.queryByPK(tickettypeSeq);
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
	public List<EpdTickettype> queryByOrganizeSeq(String organizeSeq)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdTickettypeDao epdTickettypeDao = new EpdTickettypeDao(conn);
			List<EpdTickettype> tickettypes = epdTickettypeDao.queryByOrganizeSeq(organizeSeq);
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
	public List<EpdTickettype> queryPageByCustom(String organizeSeq, String category,
			int start, int limit) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdTickettypeDao epdTickettypeDao = new EpdTickettypeDao(conn);
			List<EpdTickettype> tickettypes = epdTickettypeDao.queryPageByCustom(organizeSeq,
					category, start, limit);
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
	public List<EpdTickettype> queryAllByCustom(String organizeSeq, String category) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdTickettypeDao epdTickettypeDao = new EpdTickettypeDao(conn);
			List<EpdTickettype> tickettypes = epdTickettypeDao.queryAllByCustom(organizeSeq,category);
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
	public int queryCountByCustom(String organizeSeq, String category)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdTickettypeDao epdTickettypeDao = new EpdTickettypeDao(conn);
			int count = epdTickettypeDao.queryCountByCustom(organizeSeq,category);
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