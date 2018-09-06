package com.service.traffic.business.epd.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.service.traffic.business.epd.INetEpdCar;
import com.service.traffic.dao.EpdCarDao;
import com.service.traffic.dao.EpdCardriverDao;
import com.service.traffic.dao.EpdCarinfoDao;
import com.service.traffic.dao.SamLogDetailDao;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.basic.jdbc.PoolAlias;
import com.zhima.basic.jdbc.PoolHandler;
import com.zhima.traffic.model.EpdCar;
import com.zhima.traffic.model.EpdCardriver;
import com.zhima.traffic.model.EpdCarinfo;

/**
 * ImpEpdCar概要说明：车辆接口实现
 * @author lcy
 */
public class ImpNetEpdCar implements INetEpdCar {

	@Override
	public EpdCar insert(EpdCar epdCar, List<EpdCarinfo> carinfos, List<EpdCardriver> cardrivers
			,Map<String, Object> config) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdCarDao epdCarDao = new EpdCarDao(conn);
			EpdCarinfoDao epdCarinfoDao = new EpdCarinfoDao(conn);
			EpdCardriverDao epdCardriverDao = new EpdCardriverDao(conn);
			List<EpdCar> cars = epdCarDao.queryByValid(epdCar);
			if (null != cars && cars.size()>0){
				throw new UserBusinessException("车辆车牌号、车辆编号、车辆卡号或已存在，不允许重复。");
			}
			EpdCar car = epdCarDao.insert(epdCar,config);
			if (null != carinfos && carinfos.size()>0){
				for (int i = 0; i < carinfos.size(); i++) {
					carinfos.get(i).setCarSeq(car.getCarSeq());
					epdCarinfoDao.insert(carinfos.get(i),config);
				}
			}
			if (null != cardrivers && cardrivers.size()>0){
				for (int i = 0; i < cardrivers.size(); i++) {
					cardrivers.get(i).setCarSeq(car.getCarSeq());
					epdCardriverDao.insert(cardrivers.get(i),config);
				}
			}
			conn.commit();
			return car;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public void update(EpdCar epdCar, List<EpdCarinfo> carinfos, List<EpdCardriver> cardrivers,
			List<EpdCardriver> delCardrivers,Map<String, Object> config) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdCarDao epdCarDao = new EpdCarDao(conn);
			EpdCarinfoDao epdCarinfoDao = new EpdCarinfoDao(conn);
			EpdCardriverDao epdCardriverDao = new EpdCardriverDao(conn);
			List<EpdCar> cars = epdCarDao.queryByValid(epdCar);
			if (null != cars && cars.size()>0){
				throw new UserBusinessException("车辆车牌号、车辆编号、车辆卡号或已存在，不允许重复。");
			}
			epdCarDao.update(epdCar,config);
			if (null != carinfos && carinfos.size()>0){
				for (int i = 0; i < carinfos.size(); i++) {
					if (null != carinfos.get(i).getCarinfoSeq() && carinfos.get(i).getCarinfoSeq().length()>0){
						carinfos.get(i).setCarSeq(epdCar.getCarSeq());
						epdCarinfoDao.update(carinfos.get(i),config);
					}else{
						carinfos.get(i).setCarSeq(epdCar.getCarSeq());
						epdCarinfoDao.insert(carinfos.get(i),config);
					}
				}
			}
			if (null != delCardrivers && delCardrivers.size()>0){
				for (int i = 0; i < delCardrivers.size(); i++) {
					epdCardriverDao.deleteByPK(delCardrivers.get(i).getCardriverSeq());
				}
			}
			if (null != cardrivers && cardrivers.size()>0){
				for (int i = 0; i < cardrivers.size(); i++) {
					if (null != cardrivers.get(i).getCardriverSeq() && cardrivers.get(i).getCardriverSeq().length()>0){
						cardrivers.get(i).setCarSeq(epdCar.getCarSeq());
						epdCardriverDao.update(cardrivers.get(i),config);
					}else{
						cardrivers.get(i).setCarSeq(epdCar.getCarSeq());
						epdCardriverDao.insert(cardrivers.get(i),config);
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
	public void delete(List<EpdCar> epdCars,Map<String, Object> config) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdCarDao epdCarDao = new EpdCarDao(conn);
			EpdCarinfoDao epdCarinfoDao = new EpdCarinfoDao(conn);
			EpdCardriverDao epdCardriverDao = new EpdCardriverDao(conn);
			SamLogDetailDao samLogDetailDao = new SamLogDetailDao(conn);
			if (null != epdCars && epdCars.size()>0){
				for (int i = 0; i < epdCars.size(); i++) {
					samLogDetailDao.deleteDataLog(epdCars.get(i).getCarSeq(), new EpdCar(), config);
					epdCarDao.deleteByPK(epdCars.get(i).getCarSeq());
					epdCarinfoDao.deleteByCarSeq(epdCars.get(i).getCarSeq());
					epdCardriverDao.deleteByCarSeq(epdCars.get(i).getCarSeq());
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
	public List<EpdCar> queryPageByCustom(String organizeSeq, String routeSeq,String carCode,
			String carNumber,String companyName,String status,int start,int limit) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdCarDao epdCarDao = new EpdCarDao(conn);
			List<EpdCar> cars = epdCarDao.queryPageByCustom(organizeSeq, routeSeq, carCode, carNumber, companyName, status, start, limit);
			conn.commit();
			return cars;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public List<EpdCar> queryAllByCustom(String organizeSeq, String routeSeq,String carCode,String carNumber,
			String companyName,String status)throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdCarDao epdCarDao = new EpdCarDao(conn);
			List<EpdCar> cars = epdCarDao.queryAllByCustom(organizeSeq, routeSeq, carCode, carNumber, companyName, status);
			conn.commit();
			return cars;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public int queryCountByCustom(String organizeSeq, String routeSeq,String carCode,String carNumber,
			String companyName,String status) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdCarDao epdCarDao = new EpdCarDao(conn);
			int count = epdCarDao.queryCountByCustom(organizeSeq,  routeSeq, carCode, carNumber, companyName, status);
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
	public List<EpdCar> queryByAll() throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdCarDao epdCarDao = new EpdCarDao(conn);
			List<EpdCar> cars = epdCarDao.queryByAll();
			conn.commit();
			return cars;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}
	
	@Override
	public List<EpdCar> queryByPlanId(String organizeSeq, String planId) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdCarDao epdCarDao = new EpdCarDao(conn);
			List<EpdCar> cars = epdCarDao.queryByPlanId(organizeSeq, planId);
			conn.commit();
			return cars;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public List<EpdCar> queryByRouteSeq(String routeSeq) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdCarDao epdCarDao = new EpdCarDao(conn);
			List<EpdCar> cars = epdCarDao.queryByRouteSeq(routeSeq);
			conn.commit();
			return cars;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public List<EpdCar> queryByOrganizeSeq(String organizeSeq)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdCarDao epdCarDao = new EpdCarDao(conn);
			List<EpdCar> cars = epdCarDao.queryByOrganizeSeq(organizeSeq);
			conn.commit();
			return cars;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

}