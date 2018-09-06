
package com.service.traffic.business.epd;

import java.util.List;
import java.util.Map;

import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.model.EpdRegion;

/**
 * IEpdRegion概要说明：城市区域接口
 * @author lcy
 */
public interface INetEpdRegion {
	
	/**
	 * insert方法描述：添加城市区域
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-22 上午09:55:08
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param epdRegion 城市区域
	 * @return EpdRegion 城市区域
	 */
	public EpdRegion insert(EpdRegion epdRegion,Map<String, Object> config) throws UserBusinessException;
	
	/**
	 * update方法描述：修改城市区域
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-22 上午09:56:01
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param epdRegion 城市区域
	 */
	public void update(EpdRegion epdRegion,Map<String, Object> config) throws UserBusinessException;
	
	/**
	 * deleteByPK方法描述：根据主键查询城市区域
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-22 上午09:56:56
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param epdRegions 城市区域
	 */
	public void delete(List<EpdRegion> epdRegions,Map<String, Object> config) throws UserBusinessException;
	
	/**
	 * queryByPK方法描述：根据主键查询行政区域
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2015-10-24 上午11:42:45
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param regionSeq
	 * @return
	 * @throws UserBusinessException
	 * @return List<EpdRegion>
	 */
	public List<EpdRegion> queryByPK(String regionSeq) throws UserBusinessException;
	
	/**
	 * queryByAll方法描述：查询所有区域
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-23 下午01:37:24
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @return List<EpdRegion> 区域
	 * @throws UserBusinessException
	 */
	public List<EpdRegion> queryByAll() throws UserBusinessException;
	
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
	 * @return List<EpdRegion> 城市区域
	 */
	public List<EpdRegion> queryPageByCustom(String organizeSeq, String administrationCode, String regionSpell, String city,
			String county, String towns,int start,int limit) throws UserBusinessException;
	
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
	public List<EpdRegion> queryAllByCustom(String organizeSeq, String administrationCode, String regionSpell, String city,
			String county, String towns) throws UserBusinessException;
	
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
	public int queryCountByCustom(String organizeSeq, String administrationCode, String regionSpell, String city,
			String county, String towns) throws UserBusinessException;
	
	/**
	 * queryByCity方法描述：查询省(市)分组
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2015-10-24 上午09:35:30
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @throws UserBusinessException
	 * @return List<EpdRegion> 省(市)分组
	 */
	public List<EpdRegion> queryGroupCity(String organizeSeq) throws UserBusinessException;
	
	/**
	 * queryGroupCounty方法描述：查询组分组
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2015-10-24 上午09:36:49
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @throws UserBusinessException
	 * @return List<EpdRegion> 县分组
	 */
	public List<EpdRegion> queryGroupCounty(String organizeSeq, String city) throws UserBusinessException;
	
	/**
	 * queryGroupTowns方法描述：查询乡(镇)分组
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2015-10-24 上午09:37:51
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @throws UserBusinessException
	 * @return List<EpdRegion> 乡(镇)
	 */
	public List<EpdRegion> queryGroupTowns(String organizeSeq, String city, String county) throws UserBusinessException;

}
	
	
