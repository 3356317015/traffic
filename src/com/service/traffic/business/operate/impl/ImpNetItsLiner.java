package com.service.traffic.business.operate.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.service.traffic.business.operate.INetItsLiner;
import com.service.traffic.dao.EpdFareDao;
import com.service.traffic.dao.EpdFaresuitDetailDao;
import com.service.traffic.dao.EpdFaretypeDao;
import com.service.traffic.dao.ItsLinerDao;
import com.service.traffic.dao.ItsLinercheckDao;
import com.service.traffic.dao.ItsLinerdealDao;
import com.service.traffic.dao.ItsLinerfareDao;
import com.service.traffic.dao.ItsLinerseatDao;
import com.service.traffic.dao.ItsLinerserviceDao;
import com.service.traffic.dao.ItsLinerstationDao;
import com.service.traffic.dao.SamLogDetailDao;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.basic.jdbc.PoolAlias;
import com.zhima.basic.jdbc.PoolHandler;
import com.zhima.traffic.model.EpdFare;
import com.zhima.traffic.model.EpdFaresuitdetail;
import com.zhima.traffic.model.EpdFaretype;
import com.zhima.traffic.model.ItsLiner;
import com.zhima.traffic.model.ItsLinercheck;
import com.zhima.traffic.model.ItsLinerdeal;
import com.zhima.traffic.model.ItsLinerfare;
import com.zhima.traffic.model.ItsLinerseat;
import com.zhima.traffic.model.ItsLinerservice;
import com.zhima.traffic.model.ItsLinerstation;
import com.zhima.util.MoneyUtil;

public class ImpNetItsLiner implements INetItsLiner {

	@Override
	public ItsLiner insert(ItsLiner itsLiner,List<ItsLinerstation> itsLinerstations,
			List<ItsLinerseat> itsLinerseats,List<ItsLinerfare> itsLinerfares,
			List<ItsLinercheck> itsLinerchecks, List<ItsLinerdeal> itsLinerdeals,
			List<ItsLinerservice> itsLinerservices,Map<String, Object> config)throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			ItsLinerDao itsLinerDao = new ItsLinerDao(conn);
			ItsLinerstationDao itsLinerstationDao = new ItsLinerstationDao(conn);
			ItsLinerseatDao itsLinerseatDao = new ItsLinerseatDao(conn);
			ItsLinerfareDao itsLinerfareDao = new ItsLinerfareDao(conn);
			ItsLinercheckDao itsLinercheckDao = new ItsLinercheckDao(conn);
			ItsLinerdealDao itsLinerdealDao = new ItsLinerdealDao(conn);
			ItsLinerserviceDao itsLinerserviceDao = new ItsLinerserviceDao(conn);
			ItsLiner newLiner = itsLinerDao.insert(itsLiner, config);
			if (null != itsLinerstations && itsLinerstations.size()>0){
				for (int i = 0; i < itsLinerstations.size(); i++) {
					itsLinerstations.get(i).setLinerSeq(newLiner.getLinerSeq());
					itsLinerstationDao.insert(itsLinerstations.get(i), config);
				}
			}
			if (null != itsLinerseats && itsLinerseats.size()>0){
				for (int i = 0; i < itsLinerseats.size(); i++) {
					itsLinerseats.get(i).setLinerSeq(newLiner.getLinerSeq());
					itsLinerseatDao.insert(itsLinerseats.get(i), config);
				}
			}
			if (null != itsLinerfares && itsLinerfares.size()>0){
				for (int i = 0; i < itsLinerfares.size(); i++) {
					itsLinerfares.get(i).setLinerSeq(newLiner.getLinerSeq());
					itsLinerfareDao.insert(itsLinerfares.get(i), config);
				}
			}
			if (null != itsLinerchecks && itsLinerchecks.size()>0){
				for (int i = 0; i < itsLinerchecks.size(); i++) {
					itsLinerchecks.get(i).setLinerSeq(newLiner.getLinerSeq());
					itsLinercheckDao.insert(itsLinerchecks.get(i), config);
				}
			}
			if (null != itsLinerdeals && itsLinerdeals.size()>0){
				for (int i = 0; i < itsLinerdeals.size(); i++) {
					itsLinerdeals.get(i).setLinerSeq(newLiner.getLinerSeq());
					itsLinerdealDao.insert(itsLinerdeals.get(i), config);
				}
			}
			if (null != itsLinerservices && itsLinerservices.size()>0){
				for (int i = 0; i < itsLinerservices.size(); i++) {
					itsLinerservices.get(i).setLinerSeq(newLiner.getLinerSeq());
					itsLinerserviceDao.insert(itsLinerservices.get(i), config);
				}
			}
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
	public void updateLiner(ItsLiner itsLiner,Map<String, Object> config)throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			ItsLinerDao itsLinerDao = new ItsLinerDao(conn);
			itsLinerDao.update(itsLiner, config);
			
			conn.commit();
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}

	@Override
	public void delete(List<ItsLiner> itsLiners,Map<String, Object> config)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			ItsLinerDao itsLinerDao = new ItsLinerDao(conn);
			ItsLinerstationDao itsLinerstationDao = new ItsLinerstationDao(conn);
			ItsLinerseatDao itsLinerseatDao = new ItsLinerseatDao(conn);
			ItsLinerfareDao itsLinerfareDao = new ItsLinerfareDao(conn);
			ItsLinercheckDao itsLinercheckDao = new ItsLinercheckDao(conn);
			ItsLinerdealDao itsLinerdealDao = new ItsLinerdealDao(conn);
			if (null != itsLiners && itsLiners.size()>0){
				for (int i = 0; i < itsLiners.size(); i++) {
					itsLinerDao.deleteByPK(itsLiners.get(i).getLinerSeq());
					itsLinerstationDao.deleteByLinerSeq(itsLiners.get(i).getLinerSeq());
					itsLinerseatDao.deleteByLinerSeq(itsLiners.get(i).getLinerSeq());
					itsLinerfareDao.deleteByLinerSeq(itsLiners.get(i).getLinerSeq());
					itsLinercheckDao.deleteByLinerSeq(itsLiners.get(i).getLinerSeq());
					itsLinerdealDao.deleteByLinerSeq(itsLiners.get(i).getLinerSeq());
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
	public List<ItsLiner> queryByPK(String linerSeq) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			ItsLinerDao itsLinerDao = new ItsLinerDao(conn);
			List<ItsLiner> liners = itsLinerDao.queryByPK(linerSeq);
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
	public List<ItsLiner> queryPageByCustom(String organizeSeq,
			String routeSeq, String stationSeq, String linerId,
			String cargradeSeq, String linerStatus,String ifReport,
			String startDate, String limitDate,int start,int limit) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			ItsLinerDao itsLinerDao = new ItsLinerDao(conn);
			List<ItsLiner> liners = itsLinerDao.queryPageByCustom(organizeSeq, routeSeq,
					stationSeq, linerId, cargradeSeq, linerStatus,
					ifReport, startDate, limitDate, start, limit);
			//查询班次价格
			if (null != liners && liners.size()>0){
				for (int i = 0; i < liners.size(); i++) {
					liners.get(i).setMoreSeat(liners.get(i).getSeatNum()-liners.get(i).getSaleNum()-liners.get(i).getStopNum());
					liners.get(i).setMoreHalf(liners.get(i).getHalfNum()-liners.get(i).getSaleHalf());
					liners.get(i).setMoreFree(liners.get(i).getFreeNum()-liners.get(i).getSaleFree());
					liners.get(i).setPrice(queryLinerFare(conn, liners.get(i),stationSeq));
				}
			}
			conn.commit();
			return liners;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}
	
	/**
	 * queryLinerFare方法描述：查询班次价格
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2018-4-14 下午08:33:57
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param organizeSeq 组织机构
	 * @param linerDate 车次日期
	 * @param linerId 车次号
	 * @param stationSeq 到达站序号
	 * @return void
	 * @throws UserBusinessException 
	 * @throws NumberFormatException 
	 */
	private Double queryLinerFare(Connection conn, ItsLiner itsLiner, String stationSeq) throws NumberFormatException, UserBusinessException {
		EpdFaretypeDao epdFaretypeDao = new EpdFaretypeDao(conn);
		EpdFaresuitDetailDao epdFaresuitDetailDao = new EpdFaresuitDetailDao(conn);
		ItsLinerfareDao itsLinerfareDao = new ItsLinerfareDao(conn);
		EpdFareDao epdFareDao = new EpdFareDao(conn);
		//获取全票
		List<EpdFaretype> epdFaretypes = epdFaretypeDao.queryByOrganizeSeq(itsLiner.getOrganizeSeq());
		if (null != epdFaretypes && epdFaretypes.size()>0){
			for (int i = 0; i < epdFaretypes.size(); i++) {
				if("1".equals(epdFaretypes.get(i).getFaretypeClass())
						&& "0".equals(epdFaretypes.get(i).getIfHavefree())
						&& "1".equals(epdFaretypes.get(i).getFaretypeStatus())){
					//首先查询价套价格(要验证有效时间及状态)
					List<EpdFaresuitdetail> epdFaresuitdetails = epdFaresuitDetailDao.queryByRouteAndCargadeAndStation(
							itsLiner.getOrganizeSeq(),
							itsLiner.getRouteSeq(),itsLiner.getCargradeSeq(),stationSeq,itsLiner.getLinerDate());
					if (null != epdFaresuitdetails && epdFaresuitdetails.size()>0){
						for (int j = 0; j < epdFaresuitdetails.size(); j++) {
							if (null != epdFaresuitdetails.get(j).getIfRelease()
									&& "1".equals(epdFaresuitdetails.get(j).getIfRelease())){
								return MoneyUtil.convertPrice(
										Integer.valueOf(epdFaretypes.get(i).getRoundUnit()),
										Integer.valueOf(epdFaretypes.get(i).getCarryMode()),
										epdFaresuitdetails.get(j).getFullFare());
							}
						}
						return MoneyUtil.convertPrice(
								Integer.valueOf(epdFaretypes.get(i).getRoundUnit()),
								Integer.valueOf(epdFaretypes.get(i).getCarryMode()),
								epdFaresuitdetails.get(0).getFullFare());
					}
					//然后查询是日班次价格
					List<ItsLinerfare> itsLinerfares = itsLinerfareDao.queryByLinerSeqAndStationSeq(
							itsLiner.getLinerSeq(),stationSeq);
					if (null != itsLinerfares && itsLinerfares.size()>0){
						for (int j = 0; j < itsLinerfares.size(); j++) {
							if (null != itsLinerfares.get(j).getIfRelease()
									&& "1".equals(itsLinerfares.get(j).getIfRelease())){
								return MoneyUtil.convertPrice(
										Integer.valueOf(epdFaretypes.get(i).getRoundUnit()),
										Integer.valueOf(epdFaretypes.get(i).getCarryMode()),
										itsLinerfares.get(j).getFullFare());
							}
						}
						return MoneyUtil.convertPrice(
								Integer.valueOf(epdFaretypes.get(i).getRoundUnit()),
								Integer.valueOf(epdFaretypes.get(i).getCarryMode()),
								itsLinerfares.get(0).getFullFare());
					}
					//最后查询基础价格
					List<EpdFare> epdFares = epdFareDao.queryByRouteAndCargadeAndStation(
							itsLiner.getOrganizeSeq(),
							itsLiner.getRouteSeq(),itsLiner.getCargradeSeq(),stationSeq);
					if (null != epdFares && epdFares.size()>0){
						for (int j = 0; j < epdFares.size(); j++) {
							if (null != epdFares.get(j).getIfRelease()
									&& "1".equals(epdFares.get(j).getIfRelease())){
								return MoneyUtil.convertPrice(
										Integer.valueOf(epdFaretypes.get(i).getRoundUnit()),
										Integer.valueOf(epdFaretypes.get(i).getCarryMode()),
										epdFares.get(j).getFullFare());
							}
						}
						return MoneyUtil.convertPrice(
								Integer.valueOf(epdFaretypes.get(i).getRoundUnit()),
								Integer.valueOf(epdFaretypes.get(i).getCarryMode()),
								epdFares.get(0).getFullFare());
					}
				}
			}
		}
		return 0.00;
	}

	@Override
	public int queryCountByCustom(String organizeSeq,
			String routeSeq, String stationSeq, String linerId,
			String cargradeSeq, String linerStatus, String ifReport,
			String startDate, String limitDate) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			ItsLinerDao itsLinerDao = new ItsLinerDao(conn);
			Integer count = itsLinerDao.queryCountByCustom(organizeSeq, routeSeq,
					stationSeq, linerId, cargradeSeq, linerStatus,
					ifReport, startDate, limitDate);
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
	public void updateAttribute(ItsLiner itsLiner, Map<String, Object> config)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			ItsLinerDao itsLinerDao = new ItsLinerDao(conn);
			SamLogDetailDao samLogDetailDao = new SamLogDetailDao(conn);
			
			samLogDetailDao.executeDataLog(itsLiner.getLinerSeq(),itsLiner, config);
			itsLinerDao.updateAttribute(itsLiner, config);
			conn.commit();
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}

}