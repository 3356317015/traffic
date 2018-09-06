
package com.service.traffic.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zhima.basic.jdbc.BaseDao;
import com.zhima.frame.model.SamUserOnline;
import com.zhima.util.DateUtils;

/**
 * SamUserOnlineDao概要说明：在线用户数据操作类
 * @author lcy
 */
public class SamUserOnlineDao extends BaseDao{
	
	public SamUserOnlineDao(Connection conn){
		super(conn);
	}

	/**
	 * insert方法描述：添加在线用户
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2012-12-13 下午11:53:23
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param samUserOnline 添加前在线用户
	 * @return SamUserOnline 添加后带主键的在线用户
	 */
	public SamUserOnline insert(SamUserOnline samUserOnline, Map<String, Object> config){
		String pk = super.insert(samUserOnline,config);
		samUserOnline.setOnlineSeq(pk);
		return samUserOnline;
	}
	
	/**
	 * update方法描述：修改在线用户
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2012-12-13 下午11:52:47
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param samUserOnline 修改前的在线用户
	 */
	public void update(SamUserOnline samUserOnline, Map<String, Object> config){
		super.update(samUserOnline,config);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void updateByPK(String onlineSeq){
		String strSql = "update sam_user_online set online_time= ? where online_seq=?";
		List params = new ArrayList();
		params.add(DateUtils.getNow(DateUtils.FORMAT_LONG));
		params.add(onlineSeq);
		super.executeSql(strSql, params);
	}
	
	
	/**
	 * deleteByPK方法描述：删除在线用户
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2012-12-13 下午11:52:27
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param onlineSeq void
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteByPK(String onlineSeq){
		String strSql = "delete from sam_user_online where online_seq=?";
		List params = new ArrayList();
		params.add(onlineSeq);
		super.executeSql(strSql, params);
	}
	
	/**
	 * queryPageByAll方法描述：分页查询在线用户
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-29 下午09:45:04
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param start 分页开始行
	 * @param limit 分页结束行
	 * @return List<SamUserOnline> 在线用户
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<SamUserOnline> queryPageByAll(String organizeSeq, int start, int limit){
		String strSql = "select * from sam_user_online where organize_seq=? order by online_time";
		List params = new ArrayList();
		params.add(organizeSeq);
		List<SamUserOnline> samUserOnlines = (List<SamUserOnline>) super.queryPage(strSql,params,new SamUserOnline(),start,limit);
		return samUserOnlines;
	}
	
	/**
	 * queryCountByAll方法描述：查询在线用户记录数
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-29 下午09:47:18
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @return int 记录数
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int queryCountByAll(String organizeSeq){
		String strSql = "select count(1) from sam_user_online where organize_seq=?";
		List params = new ArrayList();
		params.add(organizeSeq);
		return super.queryCount(strSql,params);
	}
	
	/**
	 * queryByAll方法描述：查询所有在线用户
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-29 下午09:43:14
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @return List<SamUserOnline> 在线用户
	 */
	@SuppressWarnings("unchecked")
	public List<SamUserOnline> queryByAll(){
		String strSql = "select * from sam_user_online";
		List<SamUserOnline> samUserOnlines = (List<SamUserOnline>) super.queryAll(strSql,null,new SamUserOnline());
		return samUserOnlines;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<SamUserOnline> queryByOrganizeSeq(String organizeSeq){
		String strSql = "select * from sam_user_online where organize_seq=?";
		List params = new ArrayList();
		params.add(organizeSeq);
		List<SamUserOnline> samUserOnlines = (List<SamUserOnline>) super.queryAll(strSql,params,new SamUserOnline());
		return samUserOnlines;
	}
}
