package com.service.traffic.business.sam.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.service.traffic.business.sam.INetSamModule;
import com.service.traffic.dao.SamGroupModuleDao;
import com.service.traffic.dao.SamModuleDao;
import com.service.traffic.dao.SamModuleRightDao;
import com.service.traffic.dao.SamUserColumnDao;
import com.service.traffic.dao.SamUserModuleDao;
import com.service.traffic.dao.SamUserToolbarDao;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.basic.jdbc.PoolAlias;
import com.zhima.basic.jdbc.PoolHandler;
import com.zhima.frame.model.SamModule;
import com.zhima.frame.model.SamModuleRight;

/**
 * ImpSamModule概要说明：用户登录接口
 * @author lcy
 */
public class ImpNetSamModule implements INetSamModule {
	
	@Override
	public SamModule insert(SamModule samModule,String copyModuleSeq,Map<String, Object> config) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			SamModuleDao samModuleDao = new SamModuleDao(conn);
			if ("0".equals(samModule.getModuleType())){
				int layer = 0;
				String parentSeq = samModule.getParentSeq();
				for (int i = 0; i < 3; i++) {
					SamModule module = samModuleDao.queryByPK(parentSeq);
					if (null != module){
						layer += 1;
						parentSeq = module.getParentSeq();
					}
				}
				if (layer >= 3){
					throw new UserBusinessException("上级菜单["+samModule.getParentName()+"]已是系统最底层，\r\n不允许再向该层增加节点。");
				}
			}
			SamModule newSamModule = samModuleDao.insert(samModule,config);
			if (null != copyModuleSeq && copyModuleSeq.length()>0){
				SamModuleRightDao samModuleRightDao = new SamModuleRightDao(conn);
				List<SamModuleRight> moduleRights = samModuleRightDao.queryByModuleSeq(copyModuleSeq);
				if (null != moduleRights && moduleRights.size()>0){
					for (int i = 0; i < moduleRights.size(); i++) {
						moduleRights.get(i).setRightSeq("");
						moduleRights.get(i).setModuleSeq(newSamModule.getModuleSeq());
						samModuleRightDao.insert(moduleRights.get(i),config);
					}
				}
			}
			conn.commit();
			return newSamModule;
		} catch(UserBusinessException ue){
			PoolHandler.pool.rolbackConn(conn);
			throw ue;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}

	@Override
	public void update(SamModule samModule,Map<String, Object> config) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			SamModuleDao samModuleDao = new SamModuleDao(conn);
			samModuleDao.update(samModule,config);
			conn.commit();
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}		
	}
	
	@Override
	public void deleteByPk(SamModule samModule,Map<String, Object> config) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			SamModuleDao samModuleDao = new SamModuleDao(conn);
			SamModuleRightDao samModuleRightDao = new SamModuleRightDao(conn);
			SamGroupModuleDao samGroupModuleDao = new SamGroupModuleDao(conn);
			SamUserModuleDao samUserModuleDao = new SamUserModuleDao(conn);
			SamUserToolbarDao samUserToolbarDao = new SamUserToolbarDao(conn);
			SamUserColumnDao samUserColumnDao = new SamUserColumnDao(conn);
			samModuleDao.deleteByPK(samModule.getModuleSeq());
			samModuleRightDao.deleteByModuleSeq(samModule.getModuleSeq());
			samGroupModuleDao.deleteByModuleSeq(samModule.getModuleSeq());
			samUserModuleDao.deleteByModuleSeq(samModule.getModuleSeq());
			samUserToolbarDao.deleteByModuleSeq(samModule.getModuleSeq());
			samUserColumnDao.deleteByModuleClass(samModule.getPackName()+samModule.getModuleClass());
			conn.commit();
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}		
		
	}
	
	@Override
	public List<SamModule> queryTopModule(String organizeSeq) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			SamModuleDao samModuleDao = new SamModuleDao(conn);
			List<SamModule> samModules = new ArrayList<SamModule>();
			samModules = samModuleDao.queryTopModule(organizeSeq);
			conn.commit();
			return samModules;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}

	@Override
	public List<SamModule> querySubModule(String organizeSeq) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			SamModuleDao samModuleDao = new SamModuleDao(conn);
			List<SamModule> samModules = new ArrayList<SamModule>();
			samModules = samModuleDao.querySubModule(organizeSeq);
			conn.commit();
			return samModules;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}
	
	@Override
	public List<SamModule> querySubAll(String parentSeq)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			SamModuleDao samModuleDao = new SamModuleDao(conn);
			List<SamModule> samModules = new ArrayList<SamModule>();
			samModules = samModuleDao.querySubAll(parentSeq);
			conn.commit();
			return samModules;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}

	@Override
	public List<SamModule> queryModuleByUser(String userSeq)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			SamModuleDao samModuleDao = new SamModuleDao(conn);
			List<SamModule> samModules = new ArrayList<SamModule>();
			samModules = samModuleDao.queryByUser(userSeq);
			conn.commit();
			return samModules;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}
	
	@Override
	public List<SamModule> queryByGroupSeq(String groupSeq)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			SamModuleDao samModuleDao = new SamModuleDao(conn);
			List<SamModule> samModules = new ArrayList<SamModule>();
			samModules = samModuleDao.queryByGroupSeq(groupSeq);
			conn.commit();
			return samModules;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}
	
	@Override
	public List<SamModule> queryByUserSeq(String userSeq)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			SamModuleDao samModuleDao = new SamModuleDao(conn);
			List<SamModule> samModules = new ArrayList<SamModule>();
			samModules = samModuleDao.queryByUserSeq(userSeq);
			conn.commit();
			return samModules;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}

	@Override
	public List<SamModule> queryModuleParent(String organizeSeq) throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			SamModuleDao samModuleDao = new SamModuleDao(conn);
			List<SamModule> samModules = new ArrayList<SamModule>();
			samModules = samModuleDao.queryModuleParent(organizeSeq);
			conn.commit();
			return samModules;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}

	@Override
	public List<SamModule> queryByType(String organizeSeq, String moduleType)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			SamModuleDao samModuleDao = new SamModuleDao(conn);
			List<SamModule> samModules = new ArrayList<SamModule>();
			samModules = samModuleDao.queryByType(organizeSeq, moduleType);
			conn.commit();
			return samModules;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}

	@Override
	public List<SamModule> queryToolbarByUserSeq(String userSeq)
			throws UserBusinessException {
		Connection conn = PoolHandler.pool.getConnection(PoolAlias.Traffic);
		try{
			PoolHandler.pool.beginConn(conn);
			SamModuleDao samModuleDao = new SamModuleDao(conn);
			List<SamModule> samModules = new ArrayList<SamModule>();
			samModules = samModuleDao.queryToolbarByUserSeq(userSeq);
			conn.commit();
			return samModules;
		} catch (Exception e) {
			PoolHandler.pool.rolbackConn(conn);
			throw new UserBusinessException(e.getMessage());
		} finally {
			PoolHandler.pool.freeConnection(conn);
		}
	}
	
}