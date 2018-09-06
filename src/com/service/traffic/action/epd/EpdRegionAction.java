
package com.service.traffic.action.epd;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.service.comm.JsonUtil;
import com.service.comm.SecurityFinal;
import com.service.traffic.business.epd.INetEpdRegion;
import com.service.traffic.business.epd.impl.ImpNetEpdRegion;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.model.EpdRegion;

/**
 * IEpdRegion概要说明：城市区域接口
 * @author lcy
 */
public class EpdRegionAction {
	INetEpdRegion iNetEpdRegion = new ImpNetEpdRegion();
	/**
	 * insert方法描述：添加城市区域
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-22 上午09:55:08
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param epdRegion 城市区域
	 * @return EpdRegion 城市区域
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String insert(String security,String parameter) {
		Map<String, String> result = new HashMap<String, String>();
		result.put("ResultCode", "0");
		result.put("Message", "操作成功");
		try {
			//1.安全认证
			SecurityFinal.certification(security);
			//2.解析parameter
			String strParameter = JSONObject.fromObject(parameter).toString();
	        JSONObject jsonParameter = JSONObject.fromObject(strParameter);
	        Map mapParameter = (Map)jsonParameter;

	        EpdRegion epdRegion =(EpdRegion)JsonUtil.convertObject(
	        		mapParameter.get("epdRegion").toString(),EpdRegion.class);
	        
	        String strConfig = JSONObject.fromObject(mapParameter.get("config").toString()).toString();
			JSONObject jsonConfig = JSONObject.fromObject(strConfig);
			Map mapConfig = (Map)jsonConfig;
	        //3.调用业务
			EpdRegion newEpdRegion = iNetEpdRegion.insert(epdRegion, mapConfig);
	        result.put("epdRegion", JSONObject.fromObject(newEpdRegion).toString());
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}
	/**
	 * update方法描述：修改城市区域
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-22 上午09:56:01
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param epdRegion 城市区域
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String update(String security,String parameter) {
		Map<String, String> result = new HashMap<String, String>();
		result.put("ResultCode", "0");
		result.put("Message", "操作成功");
		try {
			//1.安全认证
			SecurityFinal.certification(security);
			//2.解析parameter
			String strParameter = JSONObject.fromObject(parameter).toString();
	        JSONObject jsonParameter = JSONObject.fromObject(strParameter);
	        Map mapParameter = (Map)jsonParameter;

	        EpdRegion epdRegion =(EpdRegion)JsonUtil.convertArray(
	        		mapParameter.get("epdRegion").toString(),EpdRegion.class);
	        
	        String strConfig = JSONObject.fromObject(mapParameter.get("config").toString()).toString();
			JSONObject jsonConfig = JSONObject.fromObject(strConfig);
			Map mapConfig = (Map)jsonConfig;
	        //3.调用业务
			iNetEpdRegion.update(epdRegion, mapConfig);
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}
	/**
	 * deleteByPK方法描述：根据主键查询城市区域
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-22 上午09:56:56
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param epdRegions 城市区域
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String delete(String security,String parameter) {
		Map<String, String> result = new HashMap<String, String>();
		result.put("ResultCode", "0");
		result.put("Message", "操作成功");
		try {
			//1.安全认证
			SecurityFinal.certification(security);
			//2.解析parameter
			String strParameter = JSONObject.fromObject(parameter).toString();
	        JSONObject jsonParameter = JSONObject.fromObject(strParameter);
	        Map mapParameter = (Map)jsonParameter;

			List<EpdRegion> epdRegions = (List<EpdRegion>)JsonUtil.convertArray(
					mapParameter.get("epdRegions").toString(), new EpdRegion());

	        String strConfig = JSONObject.fromObject(mapParameter.get("config").toString()).toString();
			JSONObject jsonConfig = JSONObject.fromObject(strConfig);
			Map mapConfig = (Map)jsonConfig;
	        //3.调用业务
			iNetEpdRegion.delete(epdRegions, mapConfig);
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}
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
	@SuppressWarnings("rawtypes")
	public String queryByPK(String security,String parameter) {
		Map<String, String> result = new HashMap<String, String>();
		result.put("ResultCode", "0");
		result.put("Message", "操作成功");
		try {
			//1.安全认证
			SecurityFinal.certification(security);
			//2.解析parameter
			String strParameter = JSONObject.fromObject(parameter).toString();
	        JSONObject jsonParameter = JSONObject.fromObject(strParameter);
	        Map mapParameter = (Map)jsonParameter;
	        String regionSeq = String.valueOf(mapParameter.get("regionSeq"));
	        //3.调用业务
			List<EpdRegion> epdRegions = iNetEpdRegion.queryByPK(regionSeq);
			JSONArray jsonarray = JSONArray.fromObject(epdRegions);
			result.put("epdRegions", jsonarray.toString());
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}
	/**
	 * queryByAll方法描述：查询所有区域
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-23 下午01:37:24
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @return List<EpdRegion> 区域
	 * @throws UserBusinessException
	 */
	public String queryByAll(String security) {
		Map<String, String> result = new HashMap<String, String>();
		result.put("ResultCode", "0");
		result.put("Message", "操作成功");
		try {
			//1.安全认证
			SecurityFinal.certification(security);
			//2.解析parameter
	        //3.调用业务
			List<EpdRegion> epdRegions = iNetEpdRegion.queryByAll();
			JSONArray jsonarray = JSONArray.fromObject(epdRegions);
			result.put("epdRegions", jsonarray.toString());
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
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
	 * @return List<EpdRegion> 城市区域
	 */
	@SuppressWarnings("rawtypes")
	public String queryPageByCustom(String security,String parameter) {
		Map<String, String> result = new HashMap<String, String>();
		result.put("ResultCode", "0");
		result.put("Message", "操作成功");
		try {
			//1.安全认证
			SecurityFinal.certification(security);
			//2.解析parameter
			String strParameter = JSONObject.fromObject(parameter).toString();
	        JSONObject jsonParameter = JSONObject.fromObject(strParameter);
	        Map mapParameter = (Map)jsonParameter;
	        String organizeSeq = String.valueOf(mapParameter.get("organizeSeq"));
	        String administrationCode = String.valueOf(mapParameter.get("administrationCode"));
	        String regionSpell = String.valueOf(mapParameter.get("regionSpell"));
	        String city = String.valueOf(mapParameter.get("city"));
	        String county = String.valueOf(mapParameter.get("county"));
	        String towns = String.valueOf(mapParameter.get("towns"));
	        int start = Integer.valueOf(mapParameter.get("start").toString());
	        int limit = Integer.valueOf(mapParameter.get("limit").toString());
	        //3.调用业务
			List<EpdRegion> epdRegions = iNetEpdRegion.queryPageByCustom(
					organizeSeq, administrationCode, regionSpell, city, county, towns, start, limit);
			JSONArray jsonarray = JSONArray.fromObject(epdRegions);
			result.put("epdRegions", jsonarray.toString());
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
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
	@SuppressWarnings("rawtypes")
	public String queryAllByCustom(String security,String parameter) {
		Map<String, String> result = new HashMap<String, String>();
		result.put("ResultCode", "0");
		result.put("Message", "操作成功");
		try {
			//1.安全认证
			SecurityFinal.certification(security);
			//2.解析parameter
			String strParameter = JSONObject.fromObject(parameter).toString();
	        JSONObject jsonParameter = JSONObject.fromObject(strParameter);
	        Map mapParameter = (Map)jsonParameter;
	        String organizeSeq = String.valueOf(mapParameter.get("organizeSeq"));
	        String administrationCode = String.valueOf(mapParameter.get("administrationCode"));
	        String regionSpell = String.valueOf(mapParameter.get("regionSpell"));
	        String city = String.valueOf(mapParameter.get("city"));
	        String county = String.valueOf(mapParameter.get("county"));
	        String towns = String.valueOf(mapParameter.get("towns"));
	        //3.调用业务
			List<EpdRegion> epdRegions = iNetEpdRegion.queryAllByCustom(
					organizeSeq, administrationCode, regionSpell, city, county, towns);
			JSONArray jsonarray = JSONArray.fromObject(epdRegions);
			result.put("epdRegions", jsonarray.toString());
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
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
	@SuppressWarnings("rawtypes")
	public String queryCountByCustom(String security,String parameter) {
		Map<String, String> result = new HashMap<String, String>();
		result.put("ResultCode", "0");
		result.put("Message", "操作成功");
		try {
			//1.安全认证
			SecurityFinal.certification(security);
			//2.解析parameter
			String strParameter = JSONObject.fromObject(parameter).toString();
	        JSONObject jsonParameter = JSONObject.fromObject(strParameter);
	        Map mapParameter = (Map)jsonParameter;
	        String organizeSeq = String.valueOf(mapParameter.get("organizeSeq"));
	        String administrationCode = String.valueOf(mapParameter.get("administrationCode"));
	        String regionSpell = String.valueOf(mapParameter.get("regionSpell"));
	        String city = String.valueOf(mapParameter.get("city"));
	        String county = String.valueOf(mapParameter.get("county"));
	        String towns = String.valueOf(mapParameter.get("towns"));
	        //3.调用业务
			int count = iNetEpdRegion.queryCountByCustom(
					organizeSeq, administrationCode, regionSpell, city, county, towns);
			result.put("count", String.valueOf(count));
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}
	
	/**
	 * queryByCity方法描述：查询省(市)分组
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2015-10-24 上午09:35:30
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @throws UserBusinessException
	 * @return List<EpdRegion> 省(市)分组
	 */
	@SuppressWarnings("rawtypes")
	public String queryGroupCity(String security,String parameter) {
		Map<String, String> result = new HashMap<String, String>();
		result.put("ResultCode", "0");
		result.put("Message", "操作成功");
		try {
			//1.安全认证
			SecurityFinal.certification(security);
			//2.解析parameter
			String strParameter = JSONObject.fromObject(parameter).toString();
	        JSONObject jsonParameter = JSONObject.fromObject(strParameter);
	        Map mapParameter = (Map)jsonParameter;
	        String organizeSeq = String.valueOf(mapParameter.get("organizeSeq"));
	        //3.调用业务
	        List<EpdRegion> epdRegions = iNetEpdRegion.queryGroupCity(organizeSeq);
			JSONArray jsonarray = JSONArray.fromObject(epdRegions);
			result.put("epdRegions", jsonarray.toString());
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}
	/**
	 * queryGroupCounty方法描述：查询组分组
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2015-10-24 上午09:36:49
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @throws UserBusinessException
	 * @return List<EpdRegion> 县分组
	 */
	@SuppressWarnings("rawtypes")
	public String queryGroupCounty(String security,String parameter) {
		Map<String, String> result = new HashMap<String, String>();
		result.put("ResultCode", "0");
		result.put("Message", "操作成功");
		try {
			//1.安全认证
			SecurityFinal.certification(security);
			//2.解析parameter
			String strParameter = JSONObject.fromObject(parameter).toString();
	        JSONObject jsonParameter = JSONObject.fromObject(strParameter);
	        Map mapParameter = (Map)jsonParameter;
	        String organizeSeq = String.valueOf(mapParameter.get("organizeSeq"));
	        String city = String.valueOf(mapParameter.get("city"));
	        //3.调用业务
	        List<EpdRegion> epdRegions = iNetEpdRegion.queryGroupCounty(organizeSeq, city);
			JSONArray jsonarray = JSONArray.fromObject(epdRegions);
			result.put("epdRegions", jsonarray.toString());
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}
	/**
	 * queryGroupTowns方法描述：查询乡(镇)分组
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2015-10-24 上午09:37:51
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @throws UserBusinessException
	 * @return List<EpdRegion> 乡(镇)
	 */
	@SuppressWarnings("rawtypes")
	public String queryGroupTowns(String security,String parameter) {
		Map<String, String> result = new HashMap<String, String>();
		result.put("ResultCode", "0");
		result.put("Message", "操作成功");
		try {
			//1.安全认证
			SecurityFinal.certification(security);
			//2.解析parameter
			String strParameter = JSONObject.fromObject(parameter).toString();
	        JSONObject jsonParameter = JSONObject.fromObject(strParameter);
	        Map mapParameter = (Map)jsonParameter;
	        String organizeSeq = String.valueOf(mapParameter.get("organizeSeq"));
	        String city = String.valueOf(mapParameter.get("city"));
	        String county = String.valueOf(mapParameter.get("county"));
	        //3.调用业务
	        List<EpdRegion> epdRegions = iNetEpdRegion.queryGroupTowns(organizeSeq, city, county);
			JSONArray jsonarray = JSONArray.fromObject(epdRegions);
			result.put("epdRegions", jsonarray.toString());
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}
}
	
	
