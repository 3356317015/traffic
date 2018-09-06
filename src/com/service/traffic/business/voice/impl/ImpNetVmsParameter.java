package com.service.traffic.business.voice.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.service.traffic.business.voice.INetVmsParameter;
import com.service.traffic.dao.VmsParameterDao;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.basic.jdbc.PoolAlias;
import com.zhima.basic.jdbc.PoolHandler;
import com.zhima.traffic.model.VmsParameter;

public class ImpNetVmsParameter implements INetVmsParameter {

	@Override
	public void update(List<VmsParameter> vmsParameters,Map<String, Object> config)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			VmsParameterDao vmsParameterDao = new VmsParameterDao(conn);
			if (null != vmsParameters && vmsParameters.size()>0){
				for (int i = 0; i < vmsParameters.size(); i++) {
					vmsParameterDao.update(vmsParameters.get(i),config);
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
	public List<VmsParameter> queryByOrganizeSeq(String organizeSeq)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			VmsParameterDao vmsParameterDao = new VmsParameterDao(conn);
			List<VmsParameter> vmsParameters = vmsParameterDao.queryByOrganizeSeq(organizeSeq);
			conn.commit();
			return vmsParameters;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}

	@Override
	public List<VmsParameter> queryByParameterCode(String organizeSeq,
			String parameterCode) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			VmsParameterDao vmsParameterDao = new VmsParameterDao(conn);
			List<VmsParameter> vmsParameters = vmsParameterDao.queryByParameterCode(organizeSeq,parameterCode);
			conn.commit();
			return vmsParameters;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}

}