
package com.service.traffic.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zhima.basic.jdbc.BaseDao;
import com.zhima.traffic.model.EpdRegion;

/**
 * EpdCityAreaDao概要说明：城市区域信息数据库操作类
 * @author lcy
 */
public class EpdRegionDao extends BaseDao{
	public EpdRegionDao(Connection conn){
		super(conn);
	}
	
	/**
	 * insert方法描述：添加城市区域
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-22 上午09:55:08
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param epdRegion 城市区域
	 * @return EpdRegion 城市区域
	 */
	public EpdRegion insert(EpdRegion epdRegion, Map<String, Object> config){
		String pk = super.insert(epdRegion,config);
		epdRegion.setRegionSeq(pk);
		return epdRegion;
	}
	
	/**
	 * update方法描述：修改城市区域
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-22 上午09:56:01
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param epdRegion 城市区域
	 */
	public void update(EpdRegion epdRegion, Map<String, Object> config){
		super.update(epdRegion,config);
	}

	/**
	 * deleteByPK方法描述：根据主键查询城市区域
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-22 上午09:56:56
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param regionSeq 城市区域
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteByPK(String regionSeq){
		String strSql = "delete from epd_region where region_seq=?";
		List params = new ArrayList();
		params.add(regionSeq);
		super.executeSql(strSql, params);
	}
	
	/**
	 * queryByPK方法描述：根据主键查询行政区域
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2015-10-24 上午11:39:44
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param regionSeq
	 * @return
	 * @return List<EpdRegion>
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdRegion> queryByPK(String regionSeq){
		String strSql = "select * from epd_region where region_seq=?";
		List params = new ArrayList();
		params.add(regionSeq);
		List<EpdRegion> epdRegions = (List<EpdRegion>) super.queryAll(strSql,params,new EpdRegion());
		return epdRegions;
	}
	
	/**
	 * queryByAll方法描述：查询所有城市区域
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-22 上午09:57:26
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @return List<EpdCityArea> 城市区域
	 */
	@SuppressWarnings("unchecked")
	public List<EpdRegion> queryByAll(){
		String strSql = "select * from epd_region";
		List<EpdRegion> epdRegions = (List<EpdRegion>) super.queryAll(strSql,null,new EpdRegion());
		return epdRegions;
	}
	
	/**
	 * queryPageByCustom方法描述：自定义条件分页查询城市
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-22 上午10:43:29
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param administrationCode 行政代码
	 * @param regionSpell 拼音代码
	 * @param city 省(市)
	 * @param county 县
	 * @param towns 乡(镇)
	 * @param start 分页开始行
	 * @param limit 分页结束行
	 * @return List<EpdCityArea> 城市区域
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdRegion> queryPageByCustom(String organizeSeq, String administrationCode, String regionSpell, String city,
			String county, String towns,int start,int limit){
		StringBuffer strSql = new StringBuffer("select * from epd_region where 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and organize_seq = ?");
			params.add(organizeSeq);
		}
		if (null != administrationCode && !"".equals(administrationCode)){
			strSql.append(" and administration_code like ?");
			params.add("%" + administrationCode + "%");
		}
		if (null != regionSpell && !"".equals(regionSpell)){
			strSql.append(" and region_spell like ?");
			params.add("%" + regionSpell + "%");
		}
		if (null != city && !"".equals(city)){
			strSql.append(" and city like ?");
			params.add("%" + city + "%");
		}
		if (null != county && !"".equals(county)){
			strSql.append(" and county like ?");
			params.add("%" + county + "%");
		}
		if (null != towns && !"".equals(towns)){
			strSql.append(" and towns like ?");
			params.add("%" + towns + "%");
		}
		strSql.append(" order by administration_code");
		List<EpdRegion> epdRegions = (List<EpdRegion>) super.queryPage(strSql.toString(),params,new EpdRegion(),start,limit);
		return epdRegions;
	}
	
	/**
	 * queryAllByCustom方法描述：自定义条件查询城市
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-22 上午10:45:11
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param administrationCode 行政代码
	 * @param regionSpell 拼音代码
	 * @param city 省(市)
	 * @param county 县
	 * @param towns 乡(镇)
	 * @return List<EpdRegion> 城市区域
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdRegion> queryAllByCustom(String organizeSeq, String administrationCode, String regionSpell, String city,
			String county, String towns){
		StringBuffer strSql = new StringBuffer("select * from epd_region where 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and organize_seq = ?");
			params.add(organizeSeq);
		}
		if (null != administrationCode && !"".equals(administrationCode)){
			strSql.append(" and administration_code like ?");
			params.add("%" + administrationCode + "%");
		}
		if (null != regionSpell && !"".equals(regionSpell)){
			strSql.append(" and region_spell like ?");
			params.add("%" + regionSpell + "%");
		}
		if (null != city && !"".equals(city)){
			strSql.append(" and city like ?");
			params.add("%" + city + "%");
		}
		if (null != county && !"".equals(county)){
			strSql.append(" and county like ?");
			params.add("%" + county + "%");
		}
		if (null != towns && !"".equals(towns)){
			strSql.append(" and towns like ?");
			params.add("%" + towns + "%");
		}
		strSql.append(" order by administration_code");
		List<EpdRegion> epdRegions = (List<EpdRegion>) super.queryAll(strSql.toString(),params,new EpdRegion());
		return epdRegions;
	}
	/**
	 * queryCountByCustom方法描述：自定义条件查询城市记录数
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-22 上午10:46:15
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param administrationCode 行政代码
	 * @param regionSpell 拼音代码
	 * @param city 省(市)
	 * @param county 县
	 * @param towns 乡(镇)
	 * @return int 城市区域记录数
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int queryCountByCustom(String organizeSeq, String administrationCode, String regionSpell, String city,
			String county, String towns){
		StringBuffer strSql = new StringBuffer("select count(1) from epd_region where 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and organize_seq = ?");
			params.add(organizeSeq);
		}
		if (null != administrationCode && !"".equals(administrationCode)){
			strSql.append(" and administration_code like ?");
			params.add("%" + administrationCode + "%");
		}
		if (null != regionSpell && !"".equals(regionSpell)){
			strSql.append(" and region_spell like ?");
			params.add("%" + regionSpell + "%");
		}
		if (null != city && !"".equals(city)){
			strSql.append(" and city like ?");
			params.add("%" + city + "%");
		}
		if (null != county && !"".equals(county)){
			strSql.append(" and county like ?");
			params.add("%" + county + "%");
		}
		if (null != towns && !"".equals(towns)){
			strSql.append(" and towns like ?");
			params.add("%" + towns + "%");
		}
		return super.queryCount(strSql.toString(),params);
	}

	/**
	 * queryGroupCity方法描述：查询省(市)
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2015-10-24 上午09:49:51
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @return List<EpdRegion> 省(市)
	 */
	@SuppressWarnings("unchecked")
	public List<EpdRegion> queryGroupCity() {
		StringBuffer strSql = new StringBuffer("select city from epd_region group by city");
		return (List<EpdRegion>) super.queryAll(strSql.toString(),null,new EpdRegion());
	}

	/**
	 * queryGroupCounty方法描述：查询县
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2015-10-24 上午09:50:17
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @return
	 * @return List<EpdRegion>县
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdRegion> queryGroupCounty(String organizeSeq, String city) {
		StringBuffer strSql = new StringBuffer("select county from epd_region where 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and organize_seq = ?");
			params.add(organizeSeq);
		}
		if (null != city && !"".equals(city)){
			strSql.append(" and city = ?");
			params.add(city);
		}
		strSql.append(" group by county");
		return (List<EpdRegion>) super.queryAll(strSql.toString(),params,new EpdRegion());
	}

	/**
	 * queryGroupTowns方法描述：查询乡(镇)
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2015-10-24 上午09:50:31
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @return List<EpdRegion>乡(镇)
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EpdRegion> queryGroupTowns(String organizeSeq, String city, String county) {
		StringBuffer strSql = new StringBuffer("select towns from epd_region where 1=1");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and organize_seq = ?");
			params.add(organizeSeq);
		}
		if (null != city && !"".equals(city)){
			strSql.append(" and city = ?");
			params.add(city);
		}
		if (null != county && !"".equals(county)){
			strSql.append(" and county = ?");
			params.add(county);
		}
		strSql.append(" group by towns");
		return (List<EpdRegion>) super.queryAll(strSql.toString(),params,new EpdRegion());
	}

	/**
	 * queryByRegionName方法描述：根据行政区域名称查询
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2015-10-24 上午11:16:32
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param regionName 行政区域名称
	 * @return List<EpdRegion>
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<EpdRegion> queryByRegionName(String organizeSeq, String regionName) {
		StringBuffer strSql = new StringBuffer("select * from epd_region" +
				" where CONCAT(IFNULL(city,''),IFNULL(county,''),IFNULL(towns,''))=?");
		List params = new ArrayList();
		if (null != organizeSeq && !"".equals(organizeSeq)){
			strSql.append(" and organize_seq = ?");
			params.add(organizeSeq);
		}
		params.add(regionName);
		return (List<EpdRegion>) super.queryAll(strSql.toString(),params,new EpdRegion());
	}

}
