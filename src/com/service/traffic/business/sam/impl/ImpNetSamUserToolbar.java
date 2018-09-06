package com.service.traffic.business.sam.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.service.traffic.business.sam.INetSamUserToolbar;
import com.service.traffic.dao.SamModuleDao;
import com.service.traffic.dao.SamUserToolbarDao;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.basic.jdbc.PoolAlias;
import com.zhima.basic.jdbc.PoolHandler;
import com.zhima.frame.model.SamModule;
import com.zhima.frame.model.SamUserToolbar;

public class ImpNetSamUserToolbar implements INetSamUserToolbar {
	
	@Override
	public SamUserToolbar insert(String userSeq,SamUserToolbar userToolbar,Map<String, Object> config)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			SamUserToolbarDao userToolbarDao = new SamUserToolbarDao(conn);
			SamModuleDao moduleDao = new SamModuleDao(conn);
			List<SamUserToolbar> userToolbars = userToolbarDao.queryByUserSeq(userSeq,userToolbar.getModuleSeq());
			int count = userToolbarDao.queryCountByUserSeq(userSeq);
			if(count>=25){
				throw new UserBusinessException("我的收藏不允许大于25。");
			}
			if (null != userToolbars && userToolbars.size()>0){
				throw new UserBusinessException("我的收藏已存在，不允许重复添加。");
			}
			SamUserToolbar newUserToolbar = userToolbarDao.insert(userToolbar,config);
			//第一次验证上级
			SamModule module = moduleDao.queryByPK(userToolbar.getModuleSeq());
			if (null != module && null != module.getParentSeq()
					&& module.getParentSeq().length()>0){
				userToolbars = userToolbarDao.queryByUserSeq(userSeq,module.getParentSeq());
				if (null != userToolbars && userToolbars.size()>0){
					
				}else{
					SamUserToolbar toolbar = new SamUserToolbar();
					toolbar.setUserSeq(userToolbar.getUserSeq());
					toolbar.setModuleSeq(module.getParentSeq());
					userToolbarDao.insert(toolbar,config);
				}
				//第二次验证上级
				module = moduleDao.queryByPK(module.getParentSeq());
				if (null != module && null != module.getParentSeq()
						&& module.getParentSeq().length()>0){
					userToolbars = userToolbarDao.queryByUserSeq(userSeq,module.getModuleSeq());
					if (null != userToolbars && userToolbars.size()>0){
						
					}else{
						SamUserToolbar toolbar = new SamUserToolbar();
						toolbar.setUserSeq(userToolbar.getUserSeq());
						toolbar.setModuleSeq(module.getModuleSeq());
						userToolbarDao.insert(toolbar,config);
					}
					//第三次验证上级
					module = moduleDao.queryByPK(module.getParentSeq());
					if (null != module && null != module.getParentSeq()
							&& module.getParentSeq().length()>0){
						userToolbars = userToolbarDao.queryByUserSeq(userSeq,module.getModuleSeq());
						if (null != userToolbars && userToolbars.size()>0){
							
						}else{
							SamUserToolbar toolbar = new SamUserToolbar();
							toolbar.setUserSeq(userToolbar.getUserSeq());
							toolbar.setModuleSeq(module.getModuleSeq());
							userToolbarDao.insert(toolbar,config);
						}
						//第四次验证上级
						module = moduleDao.queryByPK(module.getParentSeq());
						if (null != module && null != module.getParentSeq()
								&& module.getParentSeq().length()>0){
							userToolbars = userToolbarDao.queryByUserSeq(userSeq,module.getModuleSeq());
							if (null != userToolbars && userToolbars.size()>0){
								
							}else{
								SamUserToolbar toolbar = new SamUserToolbar();
								toolbar.setUserSeq(userToolbar.getUserSeq());
								toolbar.setModuleSeq(module.getModuleSeq());
								userToolbarDao.insert(toolbar,config);
							}
						}
					}
				}
			}
			conn.commit();
			return newUserToolbar;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}	
	}

	@Override
	public void update(SamUserToolbar userToolbar,Map<String, Object> config) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			SamUserToolbarDao userToolbarDao = new SamUserToolbarDao(conn);
			userToolbarDao.update(userToolbar,config);
			conn.commit();
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}		
	}

	@Override
	public void deleteByPk(String toolBarSeq) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			SamUserToolbarDao userToolbarDao = new SamUserToolbarDao(conn);
			userToolbarDao.deleteByPK(toolBarSeq);
			conn.commit();
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}		
	}
	
	@Override
	public void deleteByUserSeq(String userSeq, String moduleSeq) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			SamUserToolbarDao userToolbarDao = new SamUserToolbarDao(conn);
			SamModuleDao moduleDao = new SamModuleDao(conn);
			userToolbarDao.deleteByModuleSeqAndUserSeq(userSeq,moduleSeq);
			
			SamModule module = moduleDao.queryByPK(moduleSeq);
			if (null != module && null != module.getParentSeq()
						&& module.getParentSeq().length()>0){
				//删除父项
				List<SamModule> modules = moduleDao.queryByParentAndUser(module.getParentSeq(),userSeq);
				if (null != modules && modules.size()>0){
				}else{
					//第一次验证删除父级
					userToolbarDao.deleteByModuleSeqAndUserSeq(userSeq,module.getParentSeq());
					module = moduleDao.queryByPK(module.getParentSeq());
					if (null != module && null != module.getParentSeq()
							&& module.getParentSeq().length()>0){
						modules = moduleDao.queryByParentAndUser(module.getParentSeq(),userSeq);
						if (null != modules && modules.size()>0){
						}else{
							//第二次验证删除父级
							userToolbarDao.deleteByModuleSeqAndUserSeq(userSeq,module.getParentSeq());
							module = moduleDao.queryByPK(module.getParentSeq());
							if (null != module && null != module.getParentSeq()
									&& module.getParentSeq().length()>0){
								modules = moduleDao.queryByParentAndUser(module.getParentSeq(),userSeq);
								if (null != modules && modules.size()>0){
									
								}else{
									//第三次验证删除父级
									userToolbarDao.deleteByModuleSeqAndUserSeq(userSeq,module.getParentSeq());
								}
							}
						}
					}
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
	public List<SamUserToolbar> queryByUserSeq(String userSeq,String moduleSeq) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			SamUserToolbarDao userToolbarDao = new SamUserToolbarDao(conn);
			List<SamUserToolbar> userToolbars = userToolbarDao.queryByUserSeq(userSeq, moduleSeq);
			conn.commit();
			return userToolbars;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}		
	}

}
