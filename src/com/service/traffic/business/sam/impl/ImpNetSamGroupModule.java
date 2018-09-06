package com.service.traffic.business.sam.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.service.traffic.business.sam.INetSamGroupModule;
import com.service.traffic.dao.SamGroupModuleDao;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.basic.jdbc.PoolAlias;
import com.zhima.basic.jdbc.PoolHandler;
import com.zhima.frame.model.SamGroupModule;

/**
 * ImpSamGroup概要说明：角色管理接口
 * @author lcy
 */
public class ImpNetSamGroupModule implements INetSamGroupModule {	
	@Override
	public void updateGroupRight(String groupSeq, List<SamGroupModule> samGroupModules, Map<String, Object> config) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			SamGroupModuleDao samGroupModuleDao = new SamGroupModuleDao(conn);
			List<SamGroupModule> groupModules = samGroupModuleDao.queryByGroupSeq(groupSeq);
			List<SamGroupModule> delRights = new ArrayList<SamGroupModule>();
			if (null != groupModules && groupModules.size()>0){
				boolean isDel = true;
				for (int i = 0; i < groupModules.size(); i++) {
					isDel = true;
					if (null != samGroupModules && samGroupModules.size()>0){
						for (int j = samGroupModules.size()-1; j >= 0; j--) {
							
							if (samGroupModules.get(j).getModuleSeq().equals(groupModules.get(i).getModuleSeq())
									&& samGroupModules.get(j).getRightSeq().equals(groupModules.get(i).getRightSeq())){
								isDel = false;
								samGroupModules.remove(j);
							}
						}
					}
					if (isDel == true){
						delRights.add(groupModules.get(i));
					}
				}
			}
			//删除角色权限
			if (null != delRights && delRights.size()>0){
				for (int i = 0; i < delRights.size(); i++) {
					samGroupModuleDao.deleteByPk(delRights.get(i).getGroupModuleSeq());
				}
			}
			//保存该角色对应的新权限
			for (int i = 0; i < samGroupModules.size(); i++) {
				samGroupModuleDao.insert(samGroupModules.get(i),config);
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