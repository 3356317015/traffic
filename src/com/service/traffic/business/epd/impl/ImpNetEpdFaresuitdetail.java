package com.service.traffic.business.epd.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.service.traffic.business.epd.INetEpdFaresuitdetail;
import com.service.traffic.dao.EpdFaresuitDetailDao;
import com.service.traffic.dao.EpdPlanDao;
import com.service.traffic.dao.SamLogDetailDao;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.basic.jdbc.PoolAlias;
import com.zhima.basic.jdbc.PoolHandler;
import com.zhima.traffic.model.EpdFaresuitdetail;

public class ImpNetEpdFaresuitdetail implements INetEpdFaresuitdetail {

	@Override
	public EpdFaresuitdetail insert(EpdFaresuitdetail epdFaresuitdetail,Map<String, Object> config)throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdFaresuitDetailDao epdFaresuitDetailDao = new EpdFaresuitDetailDao(conn);
			EpdFaresuitdetail newFaresuitdetail = epdFaresuitDetailDao.insert(
					epdFaresuitdetail,config);
			conn.commit();
			return newFaresuitdetail;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public void update(EpdFaresuitdetail epdFaresuitdetail,Map<String, Object> config)throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdFaresuitDetailDao epdFaresuitDetailDao = new EpdFaresuitDetailDao(conn);
			epdFaresuitDetailDao.update(epdFaresuitdetail,config);
			conn.commit();
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public void delete(List<EpdFaresuitdetail> epdFaresuitdetails,Map<String, Object> config)throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdFaresuitDetailDao epdFaresuitDetailDao = new EpdFaresuitDetailDao(conn);
			EpdPlanDao epdPlanDao = new EpdPlanDao(conn);
			SamLogDetailDao samLogDetailDao = new SamLogDetailDao(conn);
			if (null != epdFaresuitdetails && epdFaresuitdetails.size()>0){
				List<String> routeSeqs = new ArrayList<String>();
				List<String> routeNames = new ArrayList<String>();
				boolean isAdd = true;
				for (int i = 0; i < epdFaresuitdetails.size(); i++) {
					isAdd = true;
					if (null != routeSeqs && routeSeqs.size()>0){
						for (int j = 0; j < routeSeqs.size(); j++) {
							if (epdFaresuitdetails.get(i).getRouteSeq().equals(routeSeqs.get(j))){
								isAdd = false;
								break;
							}
						}
					}
					if(isAdd == true){
						routeSeqs.add(epdFaresuitdetails.get(i).getRouteSeq());
						routeNames.add(epdFaresuitdetails.get(i).getRouteName());
					}
				}
				for (int i = 0; i < routeSeqs.size(); i++) {
					//如果线路运价已有计划车次启运，不允许删除.
					int count = epdPlanDao.queryCountByRouteSeq(routeSeqs.get(i));
					if (count>0){
						throw new UserBusinessException(routeNames.get(i) +"存在运营计划，不允许删除运价。");
					}
					List<EpdFaresuitdetail> faresuitdetails = epdFaresuitDetailDao.queryByRouteSeq(routeSeqs.get(i));
					if (null != faresuitdetails && faresuitdetails.size()>0){
						for (int j = 0; j < faresuitdetails.size(); j++) {
							samLogDetailDao.deleteDataLog(faresuitdetails.get(j).getFaresuitdetailSeq()
									, new EpdFaresuitdetail(),config);
						}
					}
					epdFaresuitDetailDao.deleteByRouteSeq(routeSeqs.get(i));
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
	public List<EpdFaresuitdetail> queryByPK(String faresuitdetailSeq) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdFaresuitDetailDao epdFaresuitDetailDao = new EpdFaresuitDetailDao(conn);
			List<EpdFaresuitdetail> faresuitdetails = epdFaresuitDetailDao.queryByPK(faresuitdetailSeq);
			conn.commit();
			return faresuitdetails;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}
	
	@Override
	public List<EpdFaresuitdetail> queryByAll() throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdFaresuitDetailDao epdFaresuitDetailDao = new EpdFaresuitDetailDao(conn);
			List<EpdFaresuitdetail> epdFaresuitdetails = epdFaresuitDetailDao.queryByAll();
			conn.commit();
			return epdFaresuitdetails;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}

	@Override
	public List<EpdFaresuitdetail> queryPageByCustom(String faresuitSeq, String routeSeq, String stationSeq,
			String cargradeSeq, int start, int limit) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdFaresuitDetailDao epdFaresuitDetailDao = new EpdFaresuitDetailDao(conn);
			List<EpdFaresuitdetail> epdFaresuitdetails = epdFaresuitDetailDao.queryPageByCustom(faresuitSeq,
					routeSeq, stationSeq, cargradeSeq, start, limit);
			conn.commit();
			return epdFaresuitdetails;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public  List<EpdFaresuitdetail> queryAllByCustom(String faresuitSeq, String routeSeq, String stationSeq,
			String cargradeSeq) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdFaresuitDetailDao epdFaresuitDetailDao = new EpdFaresuitDetailDao(conn);
			List<EpdFaresuitdetail> epdFaresuitdetails = epdFaresuitDetailDao.queryAllByCustom(faresuitSeq,
					routeSeq, stationSeq, cargradeSeq);
			conn.commit();
			return epdFaresuitdetails;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public int queryCountByCustom(String faresuitSeq, String routeSeq, String stationSeq,
			String cargradeSeq) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdFaresuitDetailDao epdFaresuitDetailDao = new EpdFaresuitDetailDao(conn);
			int count = epdFaresuitDetailDao.queryCountByCustom(faresuitSeq, routeSeq, 
					stationSeq, cargradeSeq);
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
	public List<EpdFaresuitdetail> queryByRouteSeq(String routeSeq) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdFaresuitDetailDao epdFaresuitDetailDao = new EpdFaresuitDetailDao(conn);
			List<EpdFaresuitdetail> epdFaresuitdetails = epdFaresuitDetailDao.queryByRouteSeq(routeSeq);
			conn.commit();
			return epdFaresuitdetails;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public void updateBatch(String routeSeq, List<EpdFaresuitdetail> faresuitdetails
			,Map<String, Object> config) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdFaresuitDetailDao epdFaresuitDetailDao = new EpdFaresuitDetailDao(conn);
			epdFaresuitDetailDao.deleteByRouteSeq(routeSeq);
			if (null != faresuitdetails && faresuitdetails.size()>0){
				for (int i = 0; i < faresuitdetails.size(); i++) {
					if (null != faresuitdetails.get(i).getFaresuitdetailSeq() && faresuitdetails.get(i).getFaresuitdetailSeq().length()>0){
						epdFaresuitDetailDao.update(faresuitdetails.get(i),config);
					}else{
						epdFaresuitDetailDao.insert(faresuitdetails.get(i),config);
					}
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
	public List<EpdFaresuitdetail> queryByRouteSeqAndFaresuitSeq(String routeSeq,
			String faresuitSeq) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdFaresuitDetailDao epdFaresuitDetailDao = new EpdFaresuitDetailDao(conn);
			List<EpdFaresuitdetail> epdFaresuitdetails = epdFaresuitDetailDao.queryByRouteSeqAndFaresuitSeq(
					routeSeq,faresuitSeq);
			conn.commit();
			return epdFaresuitdetails;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

}