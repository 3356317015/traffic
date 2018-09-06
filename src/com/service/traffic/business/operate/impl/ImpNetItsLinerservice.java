package com.service.traffic.business.operate.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.service.traffic.business.operate.INetItsLinerservice;
import com.service.traffic.dao.ItsLinerDao;
import com.service.traffic.dao.ItsLinerserviceDao;
import com.service.traffic.dao.ItsTicketDao;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.basic.jdbc.PoolAlias;
import com.zhima.basic.jdbc.PoolHandler;
import com.zhima.traffic.model.ItsLiner;
import com.zhima.traffic.model.ItsLinerservice;
import com.zhima.traffic.model.ItsTicket;

public class ImpNetItsLinerservice implements INetItsLinerservice {

	@Override
	public void updateAttribute(ItsLiner itsLiner, List<ItsLinerservice> linerservices,
			Map<String, Object> config)throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			ItsLinerDao itsLinerDao = new ItsLinerDao(conn);
			ItsLinerserviceDao itsLinerserviceDao = new ItsLinerserviceDao(conn);
			itsLinerDao.updateService(itsLiner);
			itsLinerserviceDao.deleteByLinerSeq(itsLiner.getLinerSeq());
			if (null != linerservices && linerservices.size()>0){
				for (int i = 0; i < linerservices.size(); i++) {
					itsLinerserviceDao.insert(linerservices.get(i), config);
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
	public List<ItsLinerservice> queryByLinerSeq(String linerSeq)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			ItsLinerserviceDao itsLinerserviceDao = new ItsLinerserviceDao(conn);
			ItsTicketDao itsTicketDao = new ItsTicketDao(conn);
			List<ItsLinerservice> linerservices = itsLinerserviceDao.queryByLinerSeq(linerSeq);
			List<ItsTicket> itsTickets = itsTicketDao.queryByLinerSeq(linerSeq);
			if (null !=  itsTickets && itsTickets.size()>0){
				if (null != linerservices && linerservices.size()>0){
					for (int i = 0; i < itsTickets.size(); i++) {
						for (int j = 0; j < linerservices.size(); j++) {
							if (itsTickets.get(i).getServiceSeq().equals(linerservices.get(j).getServiceSeq())){
								if (1==itsTickets.get(i).getTicketStatus()||2==itsTickets.get(i).getTicketStatus()){
									linerservices.get(j).setSaleNum(linerservices.get(j).getSaleNum()+1);
									if (2==itsTickets.get(i).getTicketStatus()){
										linerservices.get(j).setCheckNum(linerservices.get(j).getCheckNum()+1);
									}
								}
								break;
							}
						}
					}
				}
			}
			conn.commit();
			return linerservices;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

}