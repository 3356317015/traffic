package com.service.traffic.business.epd.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.service.traffic.business.epd.INetEpdRouteType;
import com.service.traffic.dao.EpdRouteTypeDao;
import com.service.traffic.dao.SamLogDetailDao;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.basic.jdbc.PoolAlias;
import com.zhima.basic.jdbc.PoolHandler;
import com.zhima.traffic.model.EpdRoutetype;

/**
 * ImpEpdRegion概要说明：线路类型
 * @author lcy
 */
public class ImpNetEpdRouteType implements INetEpdRouteType {

	@Override
	public EpdRoutetype insert(EpdRoutetype epdRoutetype,Map<String, Object> config)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdRouteTypeDao epdRouteTypeDao = new EpdRouteTypeDao(conn);
			List<EpdRoutetype> routetypes = epdRouteTypeDao.queryByValid(epdRoutetype);
			if (null != routetypes && routetypes.size()>0){
				for (int i = 0; i < routetypes.size(); i++) {
					if (epdRoutetype.getRoutetypeCode().equals(routetypes.get(i).getRoutetypeCode())){
						throw new UserBusinessException("线路类型代码已存在，请使用其他代码。");
					}
				}
			}
			EpdRoutetype routetype = epdRouteTypeDao.insert(epdRoutetype,config);
			conn.commit();
			return routetype;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public void update(EpdRoutetype epdRoutetype,Map<String, Object> config)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdRouteTypeDao epdRouteTypeDao = new EpdRouteTypeDao(conn);
			List<EpdRoutetype> routetypes = epdRouteTypeDao.queryByValid(epdRoutetype);
			if (null != routetypes && routetypes.size()>0){
				for (int i = 0; i < routetypes.size(); i++) {
					if (epdRoutetype.getRoutetypeCode().equals(routetypes.get(i).getRoutetypeCode())){
						throw new UserBusinessException("线路类型代码已存在，请使用其他代码。");
					}
				}
			}
			epdRouteTypeDao.update(epdRoutetype,config);
			conn.commit();
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public void delete(List<EpdRoutetype> epdRoutetypes,Map<String, Object> config)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdRouteTypeDao epdRouteTypeDao = new EpdRouteTypeDao(conn);
			SamLogDetailDao samLogDetailDao = new SamLogDetailDao(conn);
			if (null != epdRoutetypes && epdRoutetypes.size()>0){
				for (int i = 0; i < epdRoutetypes.size(); i++) {
					samLogDetailDao.deleteDataLog(epdRoutetypes.get(i).getRoutetypeSeq()
							, new EpdRoutetype(),config);
					epdRouteTypeDao.deleteByPK(epdRoutetypes.get(i).getRoutetypeSeq());
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
	public List<EpdRoutetype> queryByPK(String routetypeSeq) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdRouteTypeDao epdRouteTypeDao = new EpdRouteTypeDao(conn);
			List<EpdRoutetype> routetypes = epdRouteTypeDao.queryByPK(routetypeSeq);
			conn.commit();
			return routetypes;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public List<EpdRoutetype> queryPageByCustom(String organizeSeq, String routetypeCode, String routetypeName
			, int start, int limit) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdRouteTypeDao epdRouteTypeDao = new EpdRouteTypeDao(conn);
			List<EpdRoutetype> routetypes = epdRouteTypeDao.queryPageByCustom(organizeSeq, routetypeCode, 
					routetypeName, start, limit);
			conn.commit();
			return routetypes;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public List<EpdRoutetype> queryAllByCustom(String organizeSeq, String routetypeCode, String routetypeName) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdRouteTypeDao epdRouteTypeDao = new EpdRouteTypeDao(conn);
			List<EpdRoutetype> routetypes = epdRouteTypeDao.queryAllByCustom(organizeSeq, routetypeCode, 
					routetypeName);
			conn.commit();
			return routetypes;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public int queryCountByCustom(String organizeSeq, String routetypeCode, String routetypeName)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdRouteTypeDao epdRouteTypeDao = new EpdRouteTypeDao(conn);
			int count = epdRouteTypeDao.queryCountByCustom(organizeSeq, routetypeCode, 
					routetypeName);
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
	public List<EpdRoutetype> queryByOrganizeSeq(String organizeSeq)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdRouteTypeDao epdRouteTypeDao = new EpdRouteTypeDao(conn);
			List<EpdRoutetype> routetypes = epdRouteTypeDao.queryByOrganizeSeq(organizeSeq);
			conn.commit();
			return routetypes;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public List<EpdRoutetype> queryByAll() throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			EpdRouteTypeDao epdRouteTypeDao = new EpdRouteTypeDao(conn);
			List<EpdRoutetype> routetypes = epdRouteTypeDao.queryByAll();
			conn.commit();
			return routetypes;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}

}