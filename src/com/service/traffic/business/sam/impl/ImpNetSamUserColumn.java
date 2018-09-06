package com.service.traffic.business.sam.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.service.traffic.business.sam.INetSamUserColumn;
import com.service.traffic.dao.SamUserColumnDao;
import com.service.traffic.dao.SamUserSecretDao;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.basic.jdbc.PoolAlias;
import com.zhima.basic.jdbc.PoolHandler;
import com.zhima.frame.model.SamUserColumn;
import com.zhima.frame.model.SamUserSecret;
import com.zhima.widget.grid.GridColumn;

public class ImpNetSamUserColumn implements INetSamUserColumn {
	
	@Override
	public List<SamUserColumn> queryByUserColumn(String className,
			String gridName, String userSeq) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			SamUserColumnDao userColumnDao = new SamUserColumnDao(conn);
			SamUserSecretDao userSecretDao = new SamUserSecretDao(conn);
			List<SamUserColumn> userColumns = userColumnDao.queryByUserSeq(className, gridName,userSeq);
			List<SamUserSecret> userSecrets = userSecretDao.queryByUserSeq(className, gridName,userSeq);
			conn.commit();
			if (null != userSecrets && userSecrets.size()>0){
				for (int i = 0; i < userSecrets.size(); i++) {
					if (null != userColumns && userColumns.size()>0){
						for (int j = 0; j < userColumns.size(); j++) {
							if (userSecrets.get(i).getColumnEname().equals(userColumns.get(j).getColumnEname())){
								userColumns.remove(j);
								break;
							}
						}
					}
				}
			}
			return userColumns;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}
	
	@Override
	public List<GridColumn> queryByGridColumn(List<GridColumn> columns,
			String className, String gridName, String userSeq) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			SamUserColumnDao userColumnDao = new SamUserColumnDao(conn);
			SamUserSecretDao userSecretDao = new SamUserSecretDao(conn);
			List<SamUserColumn> userColumns = userColumnDao.queryByUserSeq(className, gridName,userSeq);
			List<SamUserSecret> userSecrets = userSecretDao.queryByUserSeq(className, gridName,userSeq);
			conn.commit();
			List<GridColumn> gridColumns = new ArrayList<GridColumn>();
			if (null != userColumns && userColumns.size()>0){
				boolean isNew = true;
				GridColumn gridColumn = new GridColumn();
				for (int i = 0; i < userColumns.size(); i++) {
					gridColumn = new GridColumn();
					if (null != columns && columns.size()>0){
						for (int j = 0; j < columns.size(); j++) {
							if (userColumns.get(i).getColumnEname().equals(columns.get(j).getEName())){
								isNew = false;
								BeanUtils.copyProperties(gridColumn, columns.get(j));
								gridColumn.setCName(userColumns.get(i).getColumnCname());
								gridColumn.setWidth(userColumns.get(i).getWidth());
								gridColumn.setAlignment(userColumns.get(i).getAlignment());
								break;
							}
						}
					}
					if (isNew == true){
						gridColumn.setCName(userColumns.get(i).getColumnCname());
						gridColumn.setWidth(userColumns.get(i).getWidth());
						gridColumn.setAlignment(userColumns.get(i).getAlignment());
					}
					gridColumns.add(gridColumn);
				}
			}
			if (null != gridColumns && gridColumns.size()>0){
				if (null != userSecrets && userSecrets.size()>0){
					for (int i = 0; i < userSecrets.size(); i++) {
						for (int j = 0; j < gridColumns.size(); j++) {
							if (userSecrets.get(i).getColumnEname().equals(gridColumns.get(j).getEName())){
								gridColumns.remove(j);
								break;
							}
						}
					}
				}
				return gridColumns;
			}else{
				for (int i = 0; i < columns.size(); i++) {
					if (null != userSecrets && userSecrets.size()>0){
						for (int j = 0; j < userSecrets.size(); j++) {
							if (userSecrets.get(j).getColumnEname().equals(columns.get(i).getEName())){
								columns.remove(i);
								break;
							}
						}
					}
				}
				return columns;
			}
		} catch (Exception e) {
			e.printStackTrace();
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}		
	}

	@Override
	public List<SamUserColumn> queryByValidColumn(List<GridColumn> columns,
			String className, String gridName,String userSeq) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			SamUserSecretDao userSecretDao = new SamUserSecretDao(conn);
			List<SamUserSecret> userSecrets = userSecretDao.queryByUserSeq(className, gridName,userSeq);
			conn.commit();
			List<SamUserColumn> userColumns = new ArrayList<SamUserColumn>();
			if (null != columns && columns.size()>0){
				boolean isAdd = true;
				for (int i = 0; i < columns.size(); i++) {
					isAdd = true;
					if (null != userSecrets && userSecrets.size()>0){
						for (int j = 0; j < userSecrets.size(); j++) {
							if (columns.get(i).getEName().equals(userSecrets.get(j).getColumnEname())){
								isAdd = false;
							}
						}
					}
					if (isAdd == true){
						SamUserColumn userColumn = new SamUserColumn();
						userColumn.setColumnEname(columns.get(i).getEName());
						userColumn.setColumnCname(columns.get(i).getCName());
						userColumn.setWidth(columns.get(i).getWidth());
						userColumn.setSn("0");
						userColumn.setAlignment("left");
						userColumns.add(userColumn);
					}
				}
			}
			return userColumns;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public void updateUserColumn(List<SamUserColumn> userColumns, String className,
			String gridName, String userSeq, Map<String, Object> config)throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			SamUserColumnDao userColumnDao = new SamUserColumnDao(conn);
			userColumnDao.deleteByUser(className
					,gridName,userSeq);
			if (null != userColumns && userColumns.size()>0){
				for (int i = 0; i < userColumns.size(); i++) {
					userColumns.get(i).setUserSeq(userSeq);
					userColumnDao.insert(userColumns.get(i),config);
					
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
	public void deleteByUserGrid(String className,String gridName,String userSeq,Map<String, Object> config)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			SamUserColumnDao userColumnDao = new SamUserColumnDao(conn);
			//String userSeq = CommFinal.user.getUserSeq();
			userColumnDao.deleteByUser(className,gridName,userSeq);
			conn.commit();
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

}
