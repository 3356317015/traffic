package com.service.traffic.business.voice.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.service.traffic.business.voice.INetVmsLiner;
import com.service.traffic.dao.SamLogDetailDao;
import com.service.traffic.dao.VmsLinerDao;
import com.service.traffic.dao.VmsRouteDao;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.basic.jdbc.PoolAlias;
import com.zhima.basic.jdbc.PoolHandler;
import com.zhima.traffic.model.VmsLiner;
import com.zhima.traffic.model.VmsRoute;
import com.zhima.util.DateUtils;

/**
 * ImpNetVmsLiner概要说明：播音班次
 * @author lcy
 */
public class ImpNetVmsLiner implements INetVmsLiner {

	@Override
	public VmsLiner insert(VmsLiner vmsLiner,Map<String, Object> config)
		throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			VmsLinerDao linerDao = new VmsLinerDao(conn);
			VmsLiner newLiner = linerDao.insert(vmsLiner,config);
			
			conn.commit();
			return newLiner;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}
	
	@Override
	public void update(VmsLiner vmsLiner,Map<String, Object> config)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			VmsLinerDao linerDao = new VmsLinerDao(conn);
			linerDao.update(vmsLiner,config);
			conn.commit();
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public void delete(List<VmsLiner> vmsLiners,Map<String, Object> config)throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			VmsLinerDao linerDao = new VmsLinerDao(conn);
			SamLogDetailDao samLogDetailDao = new SamLogDetailDao(conn);
			if (null != vmsLiners && vmsLiners.size()>0){
				for (int i = 0; i < vmsLiners.size(); i++) {
					samLogDetailDao.deleteDataLog(vmsLiners.get(i).getLinerSeq()
							, new VmsLiner(),config);
					linerDao.deleteByPK(vmsLiners.get(i).getLinerSeq());
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
	public void deleteByLinerDate(String organizeSeq,String linerDate,Map<String, Object> config
			)throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			VmsLinerDao linerDao = new VmsLinerDao(conn);
			linerDao.deleteByLinerDate(organizeSeq, linerDate);
			conn.commit();
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}
	
	@Override
	public List<VmsLiner> queryByPK(String linerSeq) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			VmsLinerDao linerDao = new VmsLinerDao(conn);
			List<VmsLiner> liners = linerDao.queryByPK(linerSeq);
			conn.commit();
			return liners;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}
	
	@Override
	public List<VmsLiner> queryByOrganizeSeq(String organizeSeq)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			VmsLinerDao linerDao = new VmsLinerDao(conn);
			List<VmsLiner> liners = linerDao.queryByOrganizeSeq(organizeSeq);
			conn.commit();
			return liners;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}

	@Override
	public List<VmsLiner> queryPageByCustom(String organizeSeq, String routeCode, String linerId,
			String reportStatus, String printbillStatus, int start,int limit) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			VmsLinerDao linerDao = new VmsLinerDao(conn);
			List<VmsLiner> liners = linerDao.queryPageByCustom(organizeSeq, routeCode,
					linerId,reportStatus,printbillStatus, start, limit);
			conn.commit();
			return liners;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public List<VmsLiner> queryAllByCustom(String organizeSeq, String routeCode, String linerId,
			String reportStatus, String printbillStatus) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			VmsLinerDao linerDao = new VmsLinerDao(conn);
			List<VmsLiner> liners = linerDao.queryAllByCustom(organizeSeq, routeCode, linerId,
					reportStatus,printbillStatus);
			conn.commit();
			return liners;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public int queryCountByCustom(String organizeSeq, String routeCode, String linerId,
			String reportStatus, String printbillStatus) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			VmsLinerDao linerDao = new VmsLinerDao(conn);
			int count = linerDao.queryCountByCustom(organizeSeq, routeCode, linerId,
					reportStatus,printbillStatus);
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
	public List<VmsLiner> queryByStatusAndTime(String organizeSeq,String linerStatus, String voiceTime,
			String linerMinute)throws UserBusinessException {
		String[] minutes = linerMinute.split(",");
		if (null == minutes || minutes.length<=0){
			return null;
		}
		
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			VmsLinerDao linerDao = new VmsLinerDao(conn);
			List<VmsLiner> vmsLiners = new ArrayList<VmsLiner>();
			for (int i = 0; i < minutes.length; i++) {
				List<VmsLiner> liners = linerDao.queryByStatusAndTime(organizeSeq, linerStatus,
						DateUtils.minuteAfter(voiceTime, Integer.valueOf(minutes[i])));
				if (null != liners && liners.size()>0){
					for (int j = 0; j < liners.size(); j++) {
						vmsLiners.add(liners.get(j));
					}
				}
			}
			conn.commit();
			return vmsLiners;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public void importTrafficLiner(String organizeSeq,String strSql,Map<String, Object> config
			) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Voice);
		List<VmsLiner> voiceLiners;
		try{
			PoolHandler.pool.beginConn(conn);
			VmsLinerDao vmsLinerDao = new VmsLinerDao(conn);
			voiceLiners = vmsLinerDao.queryTrafficLiner(strSql);
			conn.commit();
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
		if (null!= voiceLiners && voiceLiners.size()>0){
			Connection connTraffic = PoolHandler.pool.getConnection(PoolAlias.Traffic);
			try{
				PoolHandler.pool.beginConn(connTraffic);
				VmsRouteDao vmsRouteDao = new VmsRouteDao(connTraffic);
				VmsLinerDao vmsLinerDao = new VmsLinerDao(connTraffic);
				vmsLinerDao.deleteByLinerDate(organizeSeq,DateUtils.getNow(DateUtils.FORMAT_SHORT));
				List<VmsRoute> vmsRoutes = vmsRouteDao.queryByOrganizeSeq(organizeSeq);
				if (null != vmsRoutes && vmsRoutes.size()>0){
					List<VmsLiner> trafficLiners = vmsLinerDao.queryByOrganizeSeq(organizeSeq);
					boolean isNew = true;
					for (int i = 0; i < voiceLiners.size(); i++) {
						for (int j = 0; j < vmsRoutes.size(); j++) {
							if (vmsRoutes.get(j).getRouteCode().equals(voiceLiners.get(i).getRouteCode())){
								isNew = true;
								voiceLiners.get(i).setOrganizeSeq(organizeSeq);
								voiceLiners.get(i).setUpdateTime(DateUtils.getNow(DateUtils.FORMAT_LONG));
								if (null == voiceLiners.get(i).getStationName() || voiceLiners.get(i).getStationName().length()<=0){
									if (null != vmsRoutes.get(j).getStationName() && vmsRoutes.get(j).getStationName().length()>0){
										voiceLiners.get(i).setStationName(vmsRoutes.get(j).getStationName());
									}else{
										voiceLiners.get(i).setStationName(voiceLiners.get(i).getRouteName());
									}
								}
								if(null != trafficLiners && trafficLiners.size()>0){
									for (int k = 0; k < trafficLiners.size(); k++) {
										if(voiceLiners.get(i).getLinerId().equals(trafficLiners.get(k).getLinerId())){
											isNew = false;
											trafficLiners.get(k).setLinerDate(voiceLiners.get(i).getLinerDate());
											trafficLiners.get(k).setLinerId(voiceLiners.get(i).getLinerId());
											trafficLiners.get(k).setLinerTime(voiceLiners.get(i).getLinerTime());
											trafficLiners.get(k).setLinerType(voiceLiners.get(i).getLinerType());
											trafficLiners.get(k).setRouteCode(voiceLiners.get(i).getRouteCode());
											trafficLiners.get(k).setRouteName(voiceLiners.get(i).getRouteName());
											trafficLiners.get(k).setStationName(voiceLiners.get(i).getStationName());
											trafficLiners.get(k).setCargradeName(voiceLiners.get(i).getCargradeName());
											trafficLiners.get(k).setCheckCode(voiceLiners.get(i).getCheckCode());
											trafficLiners.get(k).setParkingCode(voiceLiners.get(i).getParkingCode());
											trafficLiners.get(k).setLinerStatus(voiceLiners.get(i).getLinerStatus());
											trafficLiners.get(k).setReportStatus(voiceLiners.get(i).getReportStatus());
											trafficLiners.get(k).setPrintbillStatus(voiceLiners.get(i).getPrintbillStatus());
											trafficLiners.get(k).setCarNumber(voiceLiners.get(i).getCarNumber());
											trafficLiners.get(k).setUpdateTime(DateUtils.getNow(DateUtils.FORMAT_LONG));
											vmsLinerDao.update(trafficLiners.get(k), config);
											break;
										}
									}
								}
								if (isNew == true){
									vmsLinerDao.insert(voiceLiners.get(i), config);
								}
								break;
							}
						}
					}
				}
				
				
				connTraffic.commit();
			} catch (Exception e) {
				PoolHandler.pool.rolbackConn(connTraffic);
				throw new UserBusinessException(e.getMessage());
			} finally {
				PoolHandler.pool.freeConnection(connTraffic);
			}
		}
	}

}