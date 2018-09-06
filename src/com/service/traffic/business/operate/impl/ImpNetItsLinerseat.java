package com.service.traffic.business.operate.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.service.traffic.business.operate.INetItsLinerseat;
import com.service.traffic.dao.ItsLinerDao;
import com.service.traffic.dao.ItsLinerseatDao;
import com.service.traffic.dao.ItsTicketDao;
import com.service.traffic.dao.SamLogDetailDao;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.basic.jdbc.PoolAlias;
import com.zhima.basic.jdbc.PoolHandler;
import com.zhima.traffic.model.ItsLiner;
import com.zhima.traffic.model.ItsLinerseat;
import com.zhima.traffic.model.ItsTicket;

public class ImpNetItsLinerseat implements INetItsLinerseat {


	@Override
	public void updateAttribute(ItsLiner itsLiner, List<ItsLinerseat> linerseats, Map<String, Object> config)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			ItsLinerseatDao itsLinerseatDao = new ItsLinerseatDao(conn);
			ItsLinerDao itsLinerDao = new ItsLinerDao(conn);
			if (null != linerseats && linerseats.size()>0){
				SamLogDetailDao samLogDetailDao = new SamLogDetailDao(conn);
				for (int i = 0; i < linerseats.size(); i++) {
					if (null != linerseats.get(i).getLinerseatSeq()&&linerseats.get(i).getLinerseatSeq().length()>0){
						samLogDetailDao.executeDataLog(linerseats.get(i).getLinerseatSeq(),linerseats.get(i), config);
						itsLinerseatDao.updateAttribute(linerseats.get(i),config);						
					}else{
						itsLinerseatDao.insert(linerseats.get(i), config);
					}
				}
			}
			int stopNum = itsLinerseatDao.queryCountByLinerSeqAndState(itsLiner.getLinerSeq(),0);
			itsLiner.setStopNum(stopNum);
			itsLinerDao.updateNumber(itsLiner, config);
			conn.commit();
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}
	
	@Override
	public List<ItsLinerseat> queryByLinerSeq(String linerSeq)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			ItsLinerseatDao itsLinerseatDao = new ItsLinerseatDao(conn);
			ItsTicketDao itsTicketDao = new ItsTicketDao(conn);
			List<ItsLinerseat> linerseats = itsLinerseatDao.queryByLinerSeq(linerSeq);
			List<ItsTicket> tickets = itsTicketDao.queryByLinerSeq(linerSeq);
			if (null != tickets && tickets.size()>0){
				if (null != linerseats && linerseats.size()>0)
				for (int i = 0; i < tickets.size(); i++) {
					for (int j = 0; j < linerseats.size(); j++) {
						if (tickets.get(i).getTicketSeq().equals(linerseats.get(j).getTicketSeq())){
							linerseats.get(j).setTicketId(tickets.get(i).getTicketId());
							linerseats.get(j).setStationName(tickets.get(i).getStationName());
							linerseats.get(j).setFaretypeName(tickets.get(i).getFaretypeName());
							linerseats.get(j).setTicketFare(tickets.get(i).getTicketFare());
							linerseats.get(j).setCustomer(tickets.get(i).getCustomer());
							linerseats.get(j).setTelephone(tickets.get(i).getTelephone());
							linerseats.get(j).setTicketStatus(tickets.get(i).getTicketStatus());
							linerseats.get(j).setSaleType(tickets.get(i).getSaleType());
							linerseats.get(j).setSaleUsername(tickets.get(i).getSaleUsername());
							linerseats.get(j).setSaleOrganizename(tickets.get(i).getSaleOrganizename());
							linerseats.get(j).setSaleServicename(tickets.get(i).getSaleServicename());
							linerseats.get(j).setSaleTime(tickets.get(i).getSaleTime());
							linerseats.get(j).setCheckUsername(tickets.get(i).getCheckUsername());
							linerseats.get(j).setCheckTime(tickets.get(i).getCheckTime());
							break;
						}
					}
				}
			}
			
			conn.commit();
			return linerseats;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public List<ItsLinerseat> queryPageByLinerSeq(String linerSeq, int start,
			int limit) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			ItsLinerseatDao itsLinerseatDao = new ItsLinerseatDao(conn);
			ItsTicketDao itsTicketDao = new ItsTicketDao(conn);
			List<ItsLinerseat> linerseats = itsLinerseatDao.queryPageByLinerSeq(linerSeq,start,limit);
			List<ItsTicket> tickets = itsTicketDao.queryByLinerSeq(linerSeq);
			if (null != tickets && tickets.size()>0){
				if (null != linerseats && linerseats.size()>0)
				for (int i = 0; i < tickets.size(); i++) {
					for (int j = 0; j < linerseats.size(); j++) {
						if (tickets.get(i).getTicketSeq().equals(linerseats.get(j).getTicketSeq())){
							linerseats.get(j).setTicketId(tickets.get(i).getTicketId());
							linerseats.get(j).setStationName(tickets.get(i).getStationName());
							linerseats.get(j).setFaretypeName(tickets.get(i).getFaretypeName());
							linerseats.get(j).setTicketFare(tickets.get(i).getTicketFare());
							linerseats.get(j).setCustomer(tickets.get(i).getCustomer());
							linerseats.get(j).setTelephone(tickets.get(i).getTelephone());
							linerseats.get(j).setTicketStatus(tickets.get(i).getTicketStatus());
							linerseats.get(j).setSaleType(tickets.get(i).getSaleType());
							linerseats.get(j).setSaleUsername(tickets.get(i).getSaleUsername());
							linerseats.get(j).setSaleOrganizename(tickets.get(i).getSaleOrganizename());
							linerseats.get(j).setSaleServicename(tickets.get(i).getSaleServicename());
							linerseats.get(j).setSaleTime(tickets.get(i).getSaleTime());
							linerseats.get(j).setCheckUsername(tickets.get(i).getCheckUsername());
							linerseats.get(j).setCheckTime(tickets.get(i).getCheckTime());
							break;
						}
					}
				}
			}
			
			conn.commit();
			return linerseats;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public int queryCountByLinerSeq(String linerSeq) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			ItsLinerseatDao itsLinerseatDao = new ItsLinerseatDao(conn);
			int count = itsLinerseatDao.queryCountByLinerSeq(linerSeq);
			
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