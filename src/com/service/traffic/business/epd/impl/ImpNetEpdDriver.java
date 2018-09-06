package com.service.traffic.business.epd.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.service.traffic.business.epd.INetEpdDriver;
import com.service.traffic.dao.EpdCardriverDao;
import com.service.traffic.dao.EpdDriverDao;
import com.service.traffic.dao.EpdDriverinfoDao;
import com.service.traffic.dao.SamLogDetailDao;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.basic.jdbc.PoolAlias;
import com.zhima.basic.jdbc.PoolHandler;
import com.zhima.traffic.model.EpdDriver;
import com.zhima.traffic.model.EpdDriverinfo;

/**
 * ImpEpdGoods概要说明：物品名称接口实现
 * @author lcy
 */
public class ImpNetEpdDriver implements INetEpdDriver {

	@Override
	public EpdDriver insert(EpdDriver epdDriver, List<EpdDriverinfo> epdDriverinfos
			,Map<String, Object> config) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdDriverDao epdDriverDao = new EpdDriverDao(conn);
			EpdDriverinfoDao epdDriverinfoDao = new EpdDriverinfoDao(conn);
			List<EpdDriver> drivers = epdDriverDao.queryByValid(epdDriver);
			if (null != drivers && drivers.size()>0){
				throw new UserBusinessException("网点驾驶员已存在，不允许重复。");
			}
			EpdDriver driver = epdDriverDao.insert(epdDriver,config);
			if (null != epdDriverinfos && epdDriverinfos.size()>0){
				for (int i = 0; i < epdDriverinfos.size(); i++) {
					if (null != epdDriverinfos.get(i).getDriverinfoSeq()
							&& epdDriverinfos.get(i).getDriverinfoSeq().length()>0){
						epdDriverinfos.get(i).setDriverSeq(driver.getDriverSeq());
						epdDriverinfoDao.update(epdDriverinfos.get(i),config);
					}else{
						epdDriverinfos.get(i).setDriverSeq(driver.getDriverSeq());
						epdDriverinfoDao.insert(epdDriverinfos.get(i),config);
					}
				}
			}
			
			conn.commit();
			return driver;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public void update(EpdDriver epdDriver, List<EpdDriverinfo> epdDriverinfos
			,Map<String, Object> config) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdDriverDao epdDriverDao = new EpdDriverDao(conn);
			EpdDriverinfoDao epdDriverinfoDao = new EpdDriverinfoDao(conn);
			List<EpdDriver> drivers = epdDriverDao.queryByValid(epdDriver);
			if (null != drivers && drivers.size()>0){
				throw new UserBusinessException("网点驾驶员已存在，不允许重复。");
			}
			epdDriverDao.update(epdDriver,config);
			if (null != epdDriverinfos && epdDriverinfos.size()>0){
				for (int i = 0; i < epdDriverinfos.size(); i++) {
					if (null != epdDriverinfos.get(i).getDriverinfoSeq()
							&& epdDriverinfos.get(i).getDriverinfoSeq().length()>0){
						epdDriverinfos.get(i).setDriverSeq(epdDriver.getDriverSeq());
						epdDriverinfoDao.update(epdDriverinfos.get(i),config);
					}else{
						epdDriverinfos.get(i).setDriverSeq(epdDriver.getDriverSeq());
						epdDriverinfoDao.insert(epdDriverinfos.get(i),config);
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
	public void delete(List<EpdDriver> epdDrivers,Map<String, Object> config) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdDriverDao epdDriverDao = new EpdDriverDao(conn);
			EpdDriverinfoDao epdDriverinfoDao = new EpdDriverinfoDao(conn);
			EpdCardriverDao epdCardriverDao = new EpdCardriverDao(conn);
			SamLogDetailDao samLogDetailDao = new SamLogDetailDao(conn);
			if (null != epdDrivers && epdDrivers.size()>0){
				for (int i = 0; i < epdDrivers.size(); i++) {
					int count= epdCardriverDao.queryByDriverSeq(epdDrivers.get(i).getDriverSeq());
					if (count>0){
						throw new UserBusinessException("驾驶员" + epdDrivers.get(i).getDriverName()+ "关联了"+count+"辆车，不允许删除。");
					}
					samLogDetailDao.deleteDataLog(epdDrivers.get(i).getDriverSeq(), new EpdDriver(),config);
					epdDriverinfoDao.deleteByDriverSeq(epdDrivers.get(i).getDriverSeq());
					epdDriverDao.deleteByPK(epdDrivers.get(i).getDriverSeq());
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
	public List<EpdDriver> queryPageByCustom(String organizeSeq, String idNumber,String driverName,String companyName,
			String status,int start,int limit) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdDriverDao epdDriverDao = new EpdDriverDao(conn);
			List<EpdDriver> drivers = epdDriverDao.queryPageByCustom(organizeSeq, idNumber,
					driverName, companyName, status, start, limit);
			conn.commit();
			return drivers;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public List<EpdDriver> queryAllByCustom(String organizeSeq, String idNumber,String driverName,
			String companyName,String status)throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdDriverDao epdDriverDao = new EpdDriverDao(conn);
			List<EpdDriver> drivers = epdDriverDao.queryAllByCustom(organizeSeq, idNumber,
					driverName, companyName, status);
			conn.commit();
			return drivers;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public int queryCountByCustom(String organizeSeq, String idNumber,String driverName,String companyName,
			String status) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdDriverDao epdDriverDao = new EpdDriverDao(conn);
			int count = epdDriverDao.queryCountByCustom(organizeSeq, idNumber,
					driverName, companyName, status);
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
	public List<EpdDriver> queryByAll() throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdDriverDao epdDriverDao = new EpdDriverDao(conn);
			List<EpdDriver> drivers = epdDriverDao.queryByAll();
			conn.commit();
			return drivers;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public List<EpdDriver> queryByOrganizeSeq(String organizeSeq)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdDriverDao epdDriverDao = new EpdDriverDao(conn);
			List<EpdDriver> drivers = epdDriverDao.queryByOrganizeSeq(organizeSeq);
			conn.commit();
			return drivers;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

}