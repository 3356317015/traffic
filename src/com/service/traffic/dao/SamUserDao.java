
package com.service.traffic.dao;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zhima.basic.exception.UserSystemException;
import com.zhima.basic.jdbc.BaseDao;
import com.zhima.frame.model.SamUser;

/**
 * SamUserDao概要说明：用户数据操作类
 * @author lcy
 */
public class SamUserDao extends BaseDao{
	
	/**
	 * 定义JDBC连接
	 */
	private Connection conn;
	
	public SamUserDao(Connection conn){
		super(conn);
		this.conn = conn;
	}

	/**
	 * insert方法描述：添加用户
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2012-12-10 下午05:08:32
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param samUser 添加前的用户信息
	 * @return SamUser 添加后带主键的用户信息
	 */
	public SamUser insert(SamUser samUser, Map<String, Object> config){
		String pk = super.insert(samUser,config);
		samUser.setUserSeq(pk);
		return samUser;
	}
	
	/**
	 * update方法描述：修改用户
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2012-12-10 下午05:09:04
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param samUser 修改的用户信息
	 */
	public void update(SamUser samUser, Map<String, Object> config){
		super.update(samUser,config);
	}
	
	/**
	 * deleteByPK方法描述：删除用户
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2012-12-10 下午05:09:24
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param userSeq 根据用户主键除用户信息
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteByPK(String userSeq){
		String strSql = "delete from sam_user where user_seq=?";
		List params = new ArrayList();
		params.add(userSeq);
		super.executeSql(strSql, params);
	}

	/**
	 * queryByUserCode方法描述：根据帐号查询用户信息
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2012-12-10 下午05:10:00
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param userCode 帐号
	 * @return List<SamUser> 用户信息
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<SamUser> queryByUserCode(String organizeSeq, String userCode){
		String strSql = "select * from sam_user where organize_seq =? and user_code=?";
		List params = new ArrayList();
		params.add(organizeSeq);
		params.add(userCode);
		List<SamUser> samUsers = (List<SamUser>) super.queryAll(strSql,params,new SamUser());
		if (null != samUsers && samUsers.size()>0){
			return samUsers;
		} else{
			return null;
		}
	}
	
	/**
	 * getDBTime方法描述：获取数据库服务器时间
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-1-27 上午02:20:54
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @return String
	 */
	public String getDBTime(){
		ResultSet rs = null;
		String date = null;
		PreparedStatement psmt = null;
		try {
			DatabaseMetaData daMe = conn.getMetaData();
			String dbType = daMe.getDatabaseProductName();
			if(dbType.equals("MySQL")){
				psmt = conn.prepareStatement("select now()");
			}else{
				psmt = conn.prepareStatement("select to_char(sysdate,'yyyy-mm-dd hh24:mi:ss') from dual");
			}
			rs = psmt.executeQuery();
			while(rs.next()){
				date = rs.getTimestamp(1).toString();
			}
			return date;
		} catch (SQLException e) {
			throw new UserSystemException("操作数据库更新出错！");
		} finally {
			try {
				if(null != rs){
					rs.close();
				}
				if(null != psmt){
					psmt.close();
				}
			} catch (SQLException e) {
				throw new UserSystemException("操作数据库更新出错！");
			}
		}
	}

	/**
	 * queryByAll方法描述：查询所有用户
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-24 上午10:03:58
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @return List<SamUser> 用户清单
	 */
	@SuppressWarnings("unchecked")
	public List<SamUser> queryByAll() {
		String strSql = "select sam_user.*,sam_organize.organize_name from sam_user" +
				" left join sam_organize on sam_organize.organize_seq = sam_user.organize_seq" +
				" order by sam_user.user_code";
		List<SamUser> samUsers = (List<SamUser>) super.queryAll(strSql,null,new SamUser());
		return samUsers;
	}

	/**
	 * queryByOrganize方法描述：根据网点序号查询用户
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-12-21 下午11:49:50
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param organizeSeq
	 * @return
	 * @return List<SamUser>
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<SamUser> queryByOrganize(String organizeSeq) {
		StringBuffer strSql = new StringBuffer(
				"select sam_user.*," +
					"sam_organize.organize_name," +
					"sam_service.service_name" +
				" from sam_user" +
					" left join sam_organize on sam_organize.organize_seq = sam_user.organize_seq" +
					" left join sam_service on sam_service.service_seq = sam_user.service_seq" +
				" where 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and sam_user.organize_seq = ?");
			params.add(organizeSeq);
		}
		strSql.append(" order by sam_user.service_seq,sam_user.user_code");
		return (List<SamUser>) super.queryAll(strSql.toString(),params,new SamUser());
	}
}
