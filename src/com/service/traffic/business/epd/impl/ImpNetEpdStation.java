package com.service.traffic.business.epd.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.service.traffic.business.epd.INetEpdStation;
import com.service.traffic.dao.EpdRegionDao;
import com.service.traffic.dao.EpdStationDao;
import com.service.traffic.dao.SamLogDetailDao;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.basic.jdbc.PoolAlias;
import com.zhima.basic.jdbc.PoolHandler;
import com.zhima.traffic.model.EpdRegion;
import com.zhima.traffic.model.EpdStation;
import com.zhima.util.PinyinUtil;

/**
 * ImpEpdStation概要说明：站点设置接口
 * @author lcy
 */
public class ImpNetEpdStation implements INetEpdStation {

	@Override
	public EpdStation insert(String organizeSeq,EpdStation epdStation,Map<String, Object> config)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdStationDao epdStationDao = new EpdStationDao(conn);
			EpdRegionDao epdRegionDao = new EpdRegionDao(conn);
			List<EpdStation> stations = epdStationDao.queryByValid(epdStation);
			if (null != stations && stations.size()>0){
				for (int i = 0; i < stations.size(); i++) {
					if (epdStation.getStationCode().equals(stations.get(i).getStationCode())){
						throw new UserBusinessException("站点代码已存在，请使用其他代码。");
					}
					if (epdStation.getStationSpell().equals(stations.get(i).getStationSpell())){
						throw new UserBusinessException("拼音代码已存在，请使用其他代码。");
					}
				}
			}
			//设置行政区域
			String regionName = epdStation.getCity()+epdStation.getCounty()+epdStation.getTowns();
			if (null != regionName && regionName.length()>0){
				List<EpdRegion> regions = epdRegionDao.queryByRegionName(organizeSeq,regionName);
				if (null != regions && regions.size()>0){
					epdStation.setRegionSeq(regions.get(0).getRegionSeq());
				}else{
					EpdRegion epdRegion = new EpdRegion();
					PinyinUtil pinyinUtil = new PinyinUtil();  
					String regionSpell = pinyinUtil.String2Alpha(regionName);
					epdRegion.setAdministrationCode(regionSpell);
					epdRegion.setRegionSpell(regionSpell);
					epdRegion.setCity(epdStation.getCity());
					epdRegion.setCounty(epdStation.getCounty());
					epdRegion.setTowns(epdStation.getTowns());
					epdRegion.setUpdateTime(epdStation.getUpdateTime());
					
					EpdRegion newRegion = epdRegionDao.insert(epdRegion,config);
					epdStation.setRegionSeq(newRegion.getRegionSeq());
				}
			}
			
			EpdStation station = epdStationDao.insert(epdStation,config);
			conn.commit();
			return station;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public void update(String organizeSeq, EpdStation epdStation,Map<String, Object> config) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdStationDao epdStationDao = new EpdStationDao(conn);
			EpdRegionDao epdRegionDao = new EpdRegionDao(conn);
			List<EpdStation> stations = epdStationDao.queryByValid(epdStation);
			if (null != stations && stations.size()>0){
				for (int i = 0; i < stations.size(); i++) {
					if (epdStation.getStationCode().equals(stations.get(i).getStationCode())){
						throw new UserBusinessException("站点代码已存在，请使用其他代码。");
					}
					if (epdStation.getStationSpell().equals(stations.get(i).getStationSpell())){
						throw new UserBusinessException("拼音代码已存在，请使用其他代码。");
					}
				}
			}
			//设置行政区域
			String regionName = epdStation.getCity()+epdStation.getCounty()+epdStation.getTowns();
			if (null != regionName && regionName.length()>0){
				List<EpdRegion> regions = epdRegionDao.queryByRegionName(organizeSeq,regionName);
				if (null != regions && regions.size()>0){
					epdStation.setRegionSeq(regions.get(0).getRegionSeq());
				}else{
					EpdRegion epdRegion = new EpdRegion();
					PinyinUtil pinyinUtil = new PinyinUtil();  
					String regionSpell = pinyinUtil.String2Alpha(regionName);
					epdRegion.setAdministrationCode(regionSpell);
					epdRegion.setRegionSpell(regionSpell);
					epdRegion.setCity(epdStation.getCity());
					epdRegion.setCounty(epdStation.getCounty());
					epdRegion.setTowns(epdStation.getTowns());
					epdRegion.setUpdateTime(epdStation.getUpdateTime());
					
					EpdRegion newRegion = epdRegionDao.insert(epdRegion,config);
					epdStation.setRegionSeq(newRegion.getRegionSeq());
				}
			}
			epdStationDao.update(epdStation,config);
			conn.commit();
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
		
	}

	@Override
	public void delete(List<EpdStation> epdStations,Map<String, Object> config)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdStationDao epdStationDao = new EpdStationDao(conn);
			SamLogDetailDao samLogDetailDao = new SamLogDetailDao(conn);
			if (null != epdStations && epdStations.size()>0){
				for (int i = 0; i < epdStations.size(); i++) {
					samLogDetailDao.deleteDataLog(epdStations.get(i).getStationSeq()
							, new EpdStation(),config);
					epdStationDao.deleteByPK(epdStations.get(i).getStationSeq());
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
	public List<EpdStation> queryPageByCustom(String organizeSeq,String stationCode,String stationSpell,String stationName,
			String stationStatus,int start,int limit) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdStationDao epdStationDao = new EpdStationDao(conn);
			List<EpdStation> epdStations = epdStationDao.queryPageByCustom(organizeSeq,stationCode, 
					stationSpell, stationName, stationStatus, start, limit);
			conn.commit();
			return epdStations;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public List<EpdStation> queryAllByCustom(String organizeSeq,String stationCode,String stationSpell,String stationName,
			String stationStatus)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdStationDao epdStationDao = new EpdStationDao(conn);
			List<EpdStation> epdStations = epdStationDao.queryAllByCustom(organizeSeq,stationCode, 
					stationSpell, stationName, stationStatus);
			conn.commit();
			return epdStations;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public int queryCountByCustom(String organizeSeq,String stationCode,String stationSpell,String stationName,
			String stationStatus)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdStationDao epdStationDao = new EpdStationDao(conn);
			int count = epdStationDao.queryCountByCustom(organizeSeq,stationCode, 
					stationSpell, stationName, stationStatus);
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
	public List<EpdStation> queryByAll() throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdStationDao epdStationDao = new EpdStationDao(conn);
			List<EpdStation> epdStations = epdStationDao.queryByAll();
			conn.commit();
			return epdStations;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public List<EpdStation> queryByStatus(String organizeSeq,String stationStatus) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdStationDao epdStationDao = new EpdStationDao(conn);
			List<EpdStation> epdStations = epdStationDao.queryByStatus(organizeSeq,stationStatus);
			conn.commit();
			return epdStations;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public List<EpdStation> queryByOrganizeSeq(String organizeSeq)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdStationDao epdStationDao = new EpdStationDao(conn);
			List<EpdStation> epdStations = epdStationDao.queryByOrganizeSeq(organizeSeq);
			conn.commit();
			return epdStations;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

}