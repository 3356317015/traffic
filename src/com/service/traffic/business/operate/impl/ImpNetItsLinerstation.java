package com.service.traffic.business.operate.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.service.traffic.business.operate.INetItsLinerstation;
import com.service.traffic.dao.ItsLinerstationDao;
import com.service.traffic.dao.ItsTicketDao;
import com.service.traffic.dao.SamLogDetailDao;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.basic.jdbc.PoolAlias;
import com.zhima.basic.jdbc.PoolHandler;
import com.zhima.traffic.model.ItsLinerstation;
import com.zhima.traffic.model.ItsTicket;

public class ImpNetItsLinerstation implements INetItsLinerstation {


	@Override
	public void updateAttribute(List<ItsLinerstation> linerstations, Map<String, Object> config)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			ItsLinerstationDao itsLinerstationDao = new ItsLinerstationDao(conn);
			if (null != linerstations && linerstations.size()>0){
				SamLogDetailDao samLogDetailDao = new SamLogDetailDao(conn);
				for (int i = 0; i < linerstations.size(); i++) {
					samLogDetailDao.executeDataLog(linerstations.get(i).getLinerstationSeq(),linerstations.get(i), config);
					itsLinerstationDao.updateAttribute(linerstations.get(i),config);
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
	public List<ItsLinerstation> queryByLinerSeq(String linerSeq)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			ItsLinerstationDao itsLinerstationDao = new ItsLinerstationDao(conn);
			ItsTicketDao itsTicketDao = new ItsTicketDao(conn);
			List<ItsLinerstation> linerstations = itsLinerstationDao.queryByLinerSeq(linerSeq);
			List<ItsTicket> itsTickets = itsTicketDao.queryByLinerSeq(linerSeq);
			if (null !=  itsTickets && itsTickets.size()>0){
				if (null != linerstations && linerstations.size()>0){
					for (int i = 0; i < itsTickets.size(); i++) {
						for (int j = 0; j < linerstations.size(); j++) {
							if (itsTickets.get(i).getStationSeq().equals(linerstations.get(j).getStationSeq())){
								if (1==itsTickets.get(i).getTicketStatus()||2==itsTickets.get(i).getTicketStatus()){
									linerstations.get(j).setSaleNum(linerstations.get(j).getSaleNum()+1);
									if (2==itsTickets.get(i).getTicketStatus()){
										linerstations.get(j).setCheckNum(linerstations.get(j).getCheckNum()+1);
									}
								}
								break;
							}
						}
					}
				}
			}
			conn.commit();
			return linerstations;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

}