package com.service.traffic.business.epd.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.service.traffic.business.epd.INetEpdRegion;
import com.service.traffic.dao.EpdRegionDao;
import com.service.traffic.dao.SamLogDetailDao;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.basic.jdbc.PoolAlias;
import com.zhima.basic.jdbc.PoolHandler;
import com.zhima.traffic.model.EpdRegion;

/**
 * ImpEpdRegion概要说明：城市区域
 * @author lcy
 */
public class ImpNetEpdRegion implements INetEpdRegion {

	@Override
	public EpdRegion insert(EpdRegion epdRegion, Map<String, Object> config)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdRegionDao epdRegionDao = new EpdRegionDao(conn);
			EpdRegion epdCityArea = epdRegionDao.insert(epdRegion, config);
			conn.commit();
			return epdCityArea;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public void update(EpdRegion epdRegion, Map<String, Object> config)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdRegionDao epdRegionDao = new EpdRegionDao(conn);
			epdRegionDao.update(epdRegion, config);
			conn.commit();
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public void delete(List<EpdRegion> epdRegions, Map<String, Object> config)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdRegionDao epdRegionDao = new EpdRegionDao(conn);
			SamLogDetailDao samLogDetailDao = new SamLogDetailDao(conn);
			if (null != epdRegions && epdRegions.size()>0){
				for (int i = 0; i < epdRegions.size(); i++) {
					samLogDetailDao.deleteDataLog(epdRegions.get(i).getRegionSeq()
							, new EpdRegion(),config);
					epdRegionDao.deleteByPK(epdRegions.get(i).getRegionSeq());
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
	public List<EpdRegion> queryByPK(String regionSeq) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdRegionDao epdRegionDao = new EpdRegionDao(conn);
			List<EpdRegion> epdRegions = epdRegionDao.queryByPK(regionSeq);
			conn.commit();
			return epdRegions;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public List<EpdRegion> queryPageByCustom(String organizeSeq, String administrationCode, String regionSpell, String city,
			String county, String towns, int start, int limit) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdRegionDao epdRegionDao = new EpdRegionDao(conn);
			List<EpdRegion> epdRegions = epdRegionDao.queryPageByCustom(organizeSeq,administrationCode, 
					regionSpell, city, county, towns, start, limit);
			conn.commit();
			return epdRegions;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public List<EpdRegion> queryAllByCustom(String organizeSeq, String administrationCode, String regionSpell, String city,
			String county, String towns) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdRegionDao epdRegionDao = new EpdRegionDao(conn);
			List<EpdRegion> epdRegions = epdRegionDao.queryAllByCustom(organizeSeq,administrationCode, 
					regionSpell, city, county, towns);
			conn.commit();
			return epdRegions;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public int queryCountByCustom(String organizeSeq, String administrationCode, String regionSpell, String city,
			String county, String towns)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdRegionDao epdRegionDao = new EpdRegionDao(conn);
			int count = epdRegionDao.queryCountByCustom(organizeSeq,administrationCode, 
					regionSpell, city, county, towns);
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
	public List<EpdRegion> queryByAll() throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdRegionDao epdRegionDao = new EpdRegionDao(conn);
			List<EpdRegion> epdRegions = epdRegionDao.queryByAll();
			conn.commit();
			return epdRegions;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}

	@Override
	public List<EpdRegion> queryGroupCity(String organizeSeq) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdRegionDao epdRegionDao = new EpdRegionDao(conn);
			List<EpdRegion> epdRegions = epdRegionDao.queryGroupCity();
			conn.commit();
			return epdRegions;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}

	@Override
	public List<EpdRegion> queryGroupCounty(String organizeSeq, String city) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdRegionDao epdRegionDao = new EpdRegionDao(conn);
			List<EpdRegion> epdRegions = epdRegionDao.queryGroupCounty(organizeSeq,city);
			conn.commit();
			return epdRegions;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}

	@Override
	public List<EpdRegion> queryGroupTowns(String organizeSeq, String city, String county) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdRegionDao epdRegionDao = new EpdRegionDao(conn);
			List<EpdRegion> epdRegions = epdRegionDao.queryGroupTowns(organizeSeq,city,county);
			conn.commit();
			return epdRegions;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}


}