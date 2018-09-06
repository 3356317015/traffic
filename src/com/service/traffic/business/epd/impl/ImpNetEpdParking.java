package com.service.traffic.business.epd.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.service.traffic.business.epd.INetEpdParking;
import com.service.traffic.dao.EpdParkingDao;
import com.service.traffic.dao.SamLogDetailDao;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.basic.jdbc.PoolAlias;
import com.zhima.basic.jdbc.PoolHandler;
import com.zhima.traffic.model.EpdCheckgate;
import com.zhima.traffic.model.EpdParking;

public class ImpNetEpdParking implements INetEpdParking {

	@Override
	public EpdParking insert(EpdParking epdParking,Map<String, Object> config)throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdParkingDao epdParkingDao = new EpdParkingDao(conn);
			List<EpdParking> parkings = epdParkingDao.queryByValid(epdParking);
			if (null != parkings && parkings.size()>0){
				throw new UserBusinessException("发车位代码已存在，请使用其他代码。");
			}
			EpdParking parking = epdParkingDao.insert(epdParking,config);
			conn.commit();
			return parking;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public void update(EpdParking epdParking,Map<String, Object> config)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdParkingDao epdParkingDao = new EpdParkingDao(conn);
			List<EpdParking> parkings = epdParkingDao.queryByValid(epdParking);
			if (null != parkings && parkings.size()>0){
				throw new UserBusinessException("发车位代码已存在，请使用其他代码。");
			}
			epdParkingDao.update(epdParking,config);
			conn.commit();
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public void delete(List<EpdParking> parkings,Map<String, Object> config)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdParkingDao epdParkingDao = new EpdParkingDao(conn);
			SamLogDetailDao samLogDetailDao = new SamLogDetailDao(conn);
			if (null != parkings && parkings.size()>0){
				for (int i = 0; i < parkings.size(); i++) {
					samLogDetailDao.deleteDataLog(parkings.get(i).getParkingSeq()
							, new EpdParking(),config);
					epdParkingDao.deleteByPK(parkings.get(i).getParkingSeq());
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
	public List<EpdParking> queryByPK(String parkingSeq) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdParkingDao epdParkingDao = new EpdParkingDao(conn);
			List<EpdParking> parkings = epdParkingDao.queryByPK(parkingSeq);
			conn.commit();
			return parkings;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}
	
	@Override
	public List<EpdParking> queryByAll() throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdParkingDao epdParkingDao = new EpdParkingDao(conn);
			List<EpdParking> parkings = epdParkingDao.queryByAll();
			conn.commit();
			return parkings;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}

	@Override
	public List<EpdParking> queryPageByCustom(String organizeSeq, String parkingCode,
			String parkingName, int start, int limit) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdParkingDao epdParkingDao = new EpdParkingDao(conn);
			List<EpdParking> parkings = epdParkingDao.queryPageByCustom(organizeSeq,
					parkingCode, parkingCode, start, limit);
			conn.commit();
			return parkings;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public List<EpdParking> queryAllByCustom(String organizeSeq, String parkingCode,
			String parkingName) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdParkingDao epdParkingDao = new EpdParkingDao(conn);
			List<EpdParking> parkings = epdParkingDao.queryAllByCustom(organizeSeq, parkingCode, 
					parkingName);
			conn.commit();
			return parkings;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public int queryCountByCustom(String organizeSeq, String parkingCode, String parkingName)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdParkingDao epdParkingDao = new EpdParkingDao(conn);
			int count = epdParkingDao.queryCountByCustom(organizeSeq, parkingCode, 
					parkingName);
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
	public List<EpdParking> queryByOrganizeSeq(String organizeSeq)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdParkingDao epdParkingDao = new EpdParkingDao(conn);
			List<EpdParking> parkings = epdParkingDao.queryByOrganizeSeq(organizeSeq);
			conn.commit();
			return parkings;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}

	@Override
	public List<EpdParking> queryByOrganizeAndCheckSeq(String organizeSeq,
			List<EpdCheckgate> checkgates) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdParkingDao epdParkingDao = new EpdParkingDao(conn);
			List<EpdParking> parkings = epdParkingDao.queryByOrganizeAndCheckSeq(organizeSeq,checkgates);
			conn.commit();
			return parkings;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}

}