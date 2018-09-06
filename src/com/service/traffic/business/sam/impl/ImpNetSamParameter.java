package com.service.traffic.business.sam.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.service.traffic.business.sam.INetSamParameter;
import com.service.traffic.dao.SamLogDetailDao;
import com.service.traffic.dao.SamParameterDao;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.basic.jdbc.PoolAlias;
import com.zhima.basic.jdbc.PoolHandler;
import com.zhima.frame.model.SamParameter;

public class ImpNetSamParameter implements INetSamParameter {

	@Override
	public SamParameter insert(SamParameter samParameter,Map<String, Object> config) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			SamParameterDao samParameterDao = new SamParameterDao(conn);
			SamParameter parameter = samParameterDao.insert(samParameter,config);
			conn.commit();
			return parameter;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public void update(SamParameter samParameter,Map<String, Object> config) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			SamParameterDao samParameterDao = new SamParameterDao(conn);
			samParameterDao.update(samParameter,config);
			conn.commit();
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public void delete(List<SamParameter> parameters,Map<String, Object> config) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			SamParameterDao samParameterDao = new SamParameterDao(conn);
			SamLogDetailDao samLogDetailDao = new SamLogDetailDao(conn);
			if (null != parameters && parameters.size()>0){
				for (int i = 0; i < parameters.size(); i++) {
					samLogDetailDao.deleteDataLog(parameters.get(i).getParameterSeq()
							, new SamParameter(),config);
					samParameterDao.deleteByPK(parameters.get(i).getParameterSeq());
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
	public List<SamParameter> queryPageByCustom(String organizeSeq,
			String groupName, int start,int limit) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			SamParameterDao samParameterDao = new SamParameterDao(conn);
			List<SamParameter> parameters = samParameterDao.queryPageByCustom(organizeSeq, 
					groupName, start, limit);
			conn.commit();
			return parameters;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public List<SamParameter> queryAllByCustom(String organizeSeq,
			String groupName) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			SamParameterDao samParameterDao = new SamParameterDao(conn);
			List<SamParameter> parameters = samParameterDao.queryAllByCustom(organizeSeq, 
					groupName);
			conn.commit();
			return parameters;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public int queryCountByCustom(String organizeSeq,
			String groupName) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			SamParameterDao samParameterDao = new SamParameterDao(conn);
			int count = samParameterDao.queryCountByCustom(organizeSeq, 
					groupName);
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
	public SamParameter queryByCode(String organizeSeq,String parameterCode, String parameterName,
			String defalutValue) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			SamParameterDao samParameterDao = new SamParameterDao(conn);
			SamParameter parameter = samParameterDao.queryByCode(organizeSeq, parameterCode, parameterName, defalutValue);
			conn.commit();
			return parameter;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}


}