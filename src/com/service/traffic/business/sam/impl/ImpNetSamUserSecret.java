package com.service.traffic.business.sam.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.service.traffic.business.sam.INetSamUserSecret;
import com.service.traffic.dao.SamUserSecretDao;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.basic.jdbc.PoolAlias;
import com.zhima.basic.jdbc.PoolHandler;
import com.zhima.frame.model.SamUserSecret;

public class ImpNetSamUserSecret implements INetSamUserSecret {
	
	@Override
	public List<SamUserSecret> queryByGridSecret(String className,
			String gridName) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			SamUserSecretDao userSecretDao = new SamUserSecretDao(conn);
			List<SamUserSecret> userSecrets = userSecretDao.queryByGridName(className, gridName);
			conn.commit();
			return userSecrets;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public void updateUserSecret(List<SamUserSecret> secrets,
			String className,String gridName,Map<String, Object> config) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			SamUserSecretDao userSecretDao = new SamUserSecretDao(conn);
			userSecretDao.deleteByGrid(className,gridName);
			if (null != secrets && secrets.size()>0){
				for (int i = 0; i < secrets.size(); i++) {
					userSecretDao.insert(secrets.get(i),config);
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

}
