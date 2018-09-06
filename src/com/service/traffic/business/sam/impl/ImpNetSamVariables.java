package com.service.traffic.business.sam.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.service.traffic.business.sam.INetSamVariables;
import com.service.traffic.dao.SamLogDetailDao;
import com.service.traffic.dao.SamVariablesDao;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.basic.jdbc.PoolAlias;
import com.zhima.basic.jdbc.PoolHandler;
import com.zhima.frame.model.SamVariables;


public class ImpNetSamVariables implements INetSamVariables {

	@Override
	public SamVariables insert(SamVariables samVariables,Map<String, Object> config)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			SamVariablesDao samVariablesDao = new SamVariablesDao(conn);
			List<SamVariables> variables = samVariablesDao.queryByValid(samVariables);
			if (null != variables && variables.size()>0){
				throw new UserBusinessException("变量已存在，不允许重复。");
			}
			SamVariables samVariable = samVariablesDao.insert(samVariables,config);
			conn.commit();
			return samVariable;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public void update(SamVariables samVariables,Map<String, Object> config) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			SamVariablesDao samVariablesDao = new SamVariablesDao(conn);
			List<SamVariables> variables = samVariablesDao.queryByValid(samVariables);
			if (null != variables && variables.size()>0){
				throw new UserBusinessException("变量已存在，不允许重复。");
			}
			samVariablesDao.update(samVariables,config);
			conn.commit();
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public void delete(List<SamVariables> samVariables,Map<String, Object> config)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			SamVariablesDao samVariablesDao = new SamVariablesDao(conn);
			SamLogDetailDao samLogDetailDao = new SamLogDetailDao(conn);
			if (null != samVariables && samVariables.size()>0){
				for (int i = 0; i < samVariables.size(); i++) {
					samLogDetailDao.deleteDataLog(samVariables.get(i).getVariablesSeq()
							, new SamVariables(), config);
					samVariablesDao.deleteByPK(samVariables.get(i).getVariablesSeq());
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
	public List<SamVariables> queryPageByCustom(String organizeSeq,String variableType,
			String variableCode, String variableName, String status, int start,
			int limit) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			SamVariablesDao samVariablesDao = new SamVariablesDao(conn);
			List<SamVariables> samVariables = samVariablesDao.queryPageByCustom(organizeSeq,variableType, 
					variableCode, variableName, status, start, limit);
			conn.commit();
			return samVariables;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public List<SamVariables> queryAllByCustom(String organizeSeq,String variableType,
			String variableCode, String variableName, String status)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			SamVariablesDao samVariablesDao = new SamVariablesDao(conn);
			List<SamVariables> samVariables = samVariablesDao.queryAllByCustom(organizeSeq,variableType, 
					variableCode, variableName, status);
			conn.commit();
			return samVariables;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}

	@Override
	public int queryCountByCustom(String organizeSeq,String variableType, String variableCode,
			String variableName, String status) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			SamVariablesDao samVariablesDao = new SamVariablesDao(conn);
			int count = samVariablesDao.queryCountByCustom(organizeSeq,variableType, 
					variableCode, variableName, status);
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
	public List<SamVariables> queryByAll() throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			SamVariablesDao samVariablesDao = new SamVariablesDao(conn);
			List<SamVariables> samVariables = samVariablesDao.queryByAll();
			conn.commit();
			return samVariables;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public List<SamVariables> queryByVariableType(String organizeSeq,String variableType)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			SamVariablesDao samVariablesDao = new SamVariablesDao(conn);
			List<SamVariables> samVariables = samVariablesDao.queryByVariableType(organizeSeq,variableType);
			conn.commit();
			return samVariables;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

}