
package com.service.traffic.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zhima.basic.exception.UserBusinessException;
import com.zhima.basic.jdbc.BaseDao;
import com.zhima.frame.model.SamOrganize;

/**
 * SamOrganizeDao概要说明：组织机构信息数据库操作类
 * @author lcy
 */
public class SamOrganizeDao extends BaseDao{
	public SamOrganizeDao(Connection conn){
		super(conn);
	}

	/**
	 * insert方法描述：添加组织机构信息
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2012-12-10 下午05:03:33
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param epdOrganize 添加的组织机构
	 * @return epdOrganize 添加后带主键的组织机构
	 */
	public SamOrganize insert(SamOrganize epdOrganize, Map<String, Object> config){
		String pk = super.insert(epdOrganize,config);
		epdOrganize.setOrganizeSeq(pk);
		return epdOrganize;
	}
	
	/**
	 * update方法描述：修改组织机构信息
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2012-12-10 下午05:04:06
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param epdOrganize 组织机构
	 */
	public void update(SamOrganize epdOrganize, Map<String, Object> config){
		super.update(epdOrganize,config);
	}
	
	/**
	 * deleteByPK方法描述：根所主键删除组织机构信息
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2012-12-10 下午05:04:29
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param organizeSeq 主键
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteByPK(String organizeSeq){
		String strSql = "delete from sam_organize where organize_seq=?";
		List params = new ArrayList();
		params.add(organizeSeq);
		super.executeSql(strSql, params);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<SamOrganize> queryByPK(String organizeSeq) {
		String strSql = "select * from sam_organize where organize_seq =?";
		List params = new ArrayList();
		params.add(organizeSeq);
		List<SamOrganize> samOrganizes = (List<SamOrganize>) super.queryAll(strSql,params,new SamOrganize());
		return samOrganizes;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<SamOrganize> queryByValid(SamOrganize samOrganize) {
		StringBuffer strSql = new StringBuffer("select * from sam_organize where (organize_code = ? or organize_spell=? or organize_name=?)");
		List params = new ArrayList();
		params.add(samOrganize.getOrganizeCode());
		params.add(samOrganize.getOrganizeSpell());
		params.add(samOrganize.getOrganizeName());
		String organizeSeq = samOrganize.getOrganizeSeq();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and organize_seq <> ?");
			params.add(organizeSeq);
		}
		List<SamOrganize> organizes = (List<SamOrganize>) super.queryAll(strSql.toString(),
				params,new SamOrganize());
		return organizes;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<SamOrganize> queryByLocalhost(SamOrganize samOrganize) {
		StringBuffer strSql = new StringBuffer("select * from sam_organize where organize_type = ?");
		List params = new ArrayList();
		params.add("1");
		String organizeSeq = samOrganize.getOrganizeSeq();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and organize_seq <> ?");
			params.add(organizeSeq);
		}
		List<SamOrganize> organizes = (List<SamOrganize>) super.queryAll(strSql.toString(),
				params,new SamOrganize());
		return organizes;
	}
	
	/**
	 * queryByAll方法描述：查询所有组织机构信息
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2012-12-10 下午05:05:02
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @return List<SamOrganize> 组织机构信息
	 */
	@SuppressWarnings("unchecked")
	public List<SamOrganize> queryByAll(){
		String strSql = "select * from sam_organize";
		List<SamOrganize> samOrganizes = (List<SamOrganize>) super.queryAll(strSql,null,new SamOrganize());
		return samOrganizes;
	}
	
	/**
	 * queryBylocalhost方法描述：根据是否本地网点查询
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-21 下午10:48:40
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param localhost 本地网点
	 * @return List<SamOrganize> 组织机构
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<SamOrganize> queryBylocalhost(String localhost){
		String strSql = "select * from sam_organize where localhost =?";
		List params = new ArrayList();
		params.add(localhost);
		List<SamOrganize> samOrganizes = (List<SamOrganize>) super.queryAll(strSql,params,new SamOrganize());
		return samOrganizes;
	}

	/**
	 * queryPageByCustom方法描述：自定义分页查询组织机构
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-23 下午01:58:15
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param organizeCode 组织机构代码
	 * @param organizeName 组织机构名称
	 * @param cityAreaSeq 区域序号
	 * @param companySeq 公司序号
	 * @param start 分页开始行
	 * @param limit 分页结束行
	 * @return List<SamOrganize> 组织机构
	 * @throws UserBusinessException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<SamOrganize> queryPageByCustom(String organizeCode,String organizeName,
			String organizeLevel,String organizeType,String organizeStatus, int start, int limit) {
		StringBuffer strSql = new StringBuffer(
				" select sam_organize.*" +
				" from sam_organize" +
				" where 1=1");
		List params = new ArrayList();
		if (null != organizeCode && !"".equals(organizeCode)){
			strSql.append(" and (sam_organize.organize_code like ? or sam_organize.organize_spell like ?)");
			params.add("%" + organizeCode + "%");
			params.add("%" + organizeCode + "%");
		}
		if (null != organizeName && !"".equals(organizeName)){
			strSql.append(" and sam_organize.organize_name like ?");
			params.add("%" + organizeName + "%");
		}
		if (null != organizeLevel && !"".equals(organizeLevel)){
			strSql.append(" and sam_organize.organize_level = ?");
			params.add(organizeLevel);
		}
		if (null != organizeType && !"".equals(organizeType)){
			strSql.append(" and sam_organize.organize_type = ?");
			params.add(organizeType);
		}
		if (null != organizeStatus && !"".equals(organizeStatus)){
			strSql.append(" and sam_organize.status = ?");
			params.add(organizeStatus);
		}
		strSql.append(" order by sam_organize.organize_code");
		List<SamOrganize> organizes = (List<SamOrganize>) super.queryPage(strSql.toString(),params,new SamOrganize(),start,limit);
		return organizes;
	}

	/**
	 * queryAllByCustom方法描述：自定义查询组织机构
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-23 下午01:59:12
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param organizeCode 组织机构代码
	 * @param organizeName 组织机构名称
	 * @param cityAreaSeq 区域序号
	 * @param companySeq 公司序号
	 * @return List<SamOrganize> 组织机构
	 * @throws UserBusinessException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<SamOrganize> queryAllByCustom(String organizeCode,String organizeName,
			String organizeLevel,String organizeType,String organizeStatus) {
		StringBuffer strSql = new StringBuffer(
				" select sam_organize.*" +
				" from sam_organize" +
				" where 1=1");
		List params = new ArrayList();
		if (null != organizeCode && !"".equals(organizeCode)){
			strSql.append(" and (sam_organize.organize_code like ? or sam_organize.organize_spell like ?)");
			params.add("%" + organizeCode + "%");
			params.add("%" + organizeCode + "%");
		}
		if (null != organizeName && !"".equals(organizeName)){
			strSql.append(" and sam_organize.organize_name like ?");
			params.add("%" + organizeName + "%");
		}
		if (null != organizeLevel && !"".equals(organizeLevel)){
			strSql.append(" and sam_organize.organize_level = ?");
			params.add(organizeLevel);
		}
		if (null != organizeType && !"".equals(organizeType)){
			strSql.append(" and sam_organize.organize_type = ?");
			params.add(organizeType);
		}
		if (null != organizeStatus && !"".equals(organizeStatus)){
			strSql.append(" and sam_organize.status = ?");
			params.add(organizeStatus);
		}
		strSql.append(" order by sam_organize.organize_code");
		List<SamOrganize> organizes = (List<SamOrganize>) super.queryAll(strSql.toString(),params,new SamOrganize());
		return organizes;
	}

	/**
	 * queryCountByCustom方法描述：自定义查询组织机构记录数
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-23 下午01:59:38
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param organizeCode 组织机构代码
	 * @param organizeName 组织机构名称
	 * @param cityAreaSeq 区域序号
	 * @param companySeq 公司序号
	 * @return int 记录数
	 * @throws UserBusinessException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public int queryCountByCustom(String organizeCode, String organizeName,
			String organizeLevel,String organizeType,String organizeStatus) {
		StringBuffer strSql = new StringBuffer("select count(1) from sam_organize where 1=1");
		List params = new ArrayList();
		if (null != organizeCode && !"".equals(organizeCode)){
			strSql.append(" and (organize_code like ? or organize_spell like ?)");
			params.add("%" + organizeCode + "%");
			params.add("%" + organizeCode + "%");
		}
		if (null != organizeName && !"".equals(organizeName)){
			strSql.append(" and organize_name like ?");
			params.add("%" + organizeName + "%");
		}
		if (null != organizeLevel && !"".equals(organizeLevel)){
			strSql.append(" and organize_level = ?");
			params.add(organizeLevel);
		}
		if (null != organizeType && !"".equals(organizeType)){
			strSql.append(" and organize_type = ?");
			params.add(organizeType);
		}
		if (null != organizeStatus && !"".equals(organizeStatus)){
			strSql.append(" and status = ?");
			params.add(organizeStatus);
		}
		return super.queryCount(strSql.toString(),params);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<SamOrganize> queryByOrganizeType(String organizeType) {
		String strSql = "select * from sam_organize where organize_type =?";
		List params = new ArrayList();
		params.add(organizeType);
		List<SamOrganize> samOrganizes = (List<SamOrganize>) super.queryAll(strSql,params,new SamOrganize());
		return samOrganizes;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<SamOrganize> queryDealByOrganizeSeq(String organizeSeq) {
		String strSql = "select sam_organize.*" +
				" from sam_organize,epd_dealorganize" +
				" where sam_organize.organize_seq = epd_dealorganize.dealorganize" +
				"	and epd_dealorganize.organize_seq =?";
		List params = new ArrayList();
		params.add(organizeSeq);
		List<SamOrganize> samOrganizes = (List<SamOrganize>) super.queryAll(strSql,params,new SamOrganize());
		return samOrganizes;
	}



}
