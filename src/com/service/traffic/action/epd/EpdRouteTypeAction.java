
package com.service.traffic.action.epd;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.service.comm.JsonUtil;
import com.service.comm.SecurityFinal;
import com.service.traffic.business.epd.INetEpdRouteType;
import com.service.traffic.business.epd.impl.ImpNetEpdRouteType;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.model.EpdRoutetype;

/**
 * IEpdRouteType概要说明：线路类型
 * @author lcy
 */
public class EpdRouteTypeAction {
	INetEpdRouteType iNetEpdRouteType = new ImpNetEpdRouteType();
	/**
	 * insert方法描述：添加线路类型
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-22 上午09:55:08
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param epdRoutetype 城市区域
	 * @return EpdRoutetype 城市区域
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
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

	        EpdRoutetype epdRoutetype =(EpdRoutetype)JsonUtil.convertObject(
	        		mapParameter.get("epdRoutetype").toString(),EpdRoutetype.class);
	        
	        String strConfig = JSONObject.fromObject(mapParameter.get("config").toString()).toString();
			JSONObject jsonConfig = JSONObject.fromObject(strConfig);
			Map mapConfig = (Map)jsonConfig;
	        //3.调用业务
			EpdRoutetype newEpdRoutetype = iNetEpdRouteType.insert(epdRoutetype, mapConfig);
	        result.put("epdRoutetype", JSONObject.fromObject(newEpdRoutetype).toString());
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}
	/**
	 * update方法描述：修改线路类型
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-22 上午09:56:01
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param epdRoutetype 城市区域
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
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

	        EpdRoutetype epdRoutetype =(EpdRoutetype)JsonUtil.convertObject(
	        		mapParameter.get("epdRoutetype").toString(),EpdRoutetype.class);
	        
	        String strConfig = JSONObject.fromObject(mapParameter.get("config").toString()).toString();
			JSONObject jsonConfig = JSONObject.fromObject(strConfig);
			Map mapConfig = (Map)jsonConfig;
	        //3.调用业务
			iNetEpdRouteType.update(epdRoutetype, mapConfig);
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}
	/**
	 * deleteByPK方法描述：根据主键删除线路类型
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-22 上午09:56:56
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param epdRoutetypes 线路类型
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

			List<EpdRoutetype> epdRoutetypes = (List<EpdRoutetype>)JsonUtil.convertArray(
					mapParameter.get("epdRoutetypes").toString(), new EpdRoutetype());
	        
	        String strConfig = JSONObject.fromObject(mapParameter.get("config").toString()).toString();
			JSONObject jsonConfig = JSONObject.fromObject(strConfig);
			Map mapConfig = (Map)jsonConfig;
	        //3.调用业务
			iNetEpdRouteType.delete(epdRoutetypes, mapConfig);
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}
	/**
	 * queryByPK方法描述：根据主键查询线路类型
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2015-10-24 上午11:42:45
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param routetypeSeq
	 * @throws UserBusinessException
	 * @return List<EpdRoutetype> 线路类型
	 */
	@SuppressWarnings({ "rawtypes" })
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

	        String routetypeSeq = String.valueOf(mapParameter.get("routetypeSeq"));

	        //3.调用业务
			List<EpdRoutetype> epdRoutetypes = iNetEpdRouteType.queryByPK(routetypeSeq);
			JSONArray jsonarray = JSONArray.fromObject(epdRoutetypes);
			result.put("epdRoutetypes", jsonarray.toString());
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}
	/**
	 * queryPageByCustom方法描述：自定义条件分页查询线路类型
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-22 上午10:43:29
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param routetypeCode 线路类型代码
	 * @param routetypeName 线路类型名称
	 * @param start 分页开始行
	 * @param limit 分页结束行
	 * @return List<EpdRoutetype> 线路类型
	 */
	@SuppressWarnings({ "rawtypes" })
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
	        String routetypeCode = String.valueOf(mapParameter.get("routetypeCode"));
	        String routetypeName = String.valueOf(mapParameter.get("routetypeName"));
	        int start = Integer.valueOf(mapParameter.get("start").toString());
	        int limit = Integer.valueOf(mapParameter.get("limit").toString());
	        //3.调用业务
			List<EpdRoutetype> epdRoutetypes = iNetEpdRouteType.queryPageByCustom(
					organizeSeq, routetypeCode, routetypeName, start, limit);
			JSONArray jsonarray = JSONArray.fromObject(epdRoutetypes);
			result.put("epdRoutetypes", jsonarray.toString());
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}
	/**
	 * queryAllByCustom方法描述：自定义条件查询线路类型
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-22 上午10:45:11
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param routetypeCode 线路类型代码
	 * @param routetypeName 线路类型名称
	 * @return List<EpdRoutetype> 线路类型
	 */
	@SuppressWarnings({ "rawtypes" })
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
	        String routetypeCode = String.valueOf(mapParameter.get("routetypeCode"));
	        String routetypeName = String.valueOf(mapParameter.get("routetypeName"));
	        
	        //3.调用业务
			List<EpdRoutetype> epdRoutetypes = iNetEpdRouteType.queryAllByCustom(
					organizeSeq, routetypeCode, routetypeName);
			JSONArray jsonarray = JSONArray.fromObject(epdRoutetypes);
			result.put("epdRoutetypes", jsonarray.toString());
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}
	/**
	 * queryCountByCustom方法描述：自定义条件查询线路类型记录数
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-22 上午10:46:15
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param routetypeCode 线路类型代码
	 * @param routetypeName 线路类型名称
	 * @return int 线路类型记录数
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
	        String routetypeCode = String.valueOf(mapParameter.get("routetypeCode"));
	        String routetypeName = String.valueOf(mapParameter.get("routetypeName"));
	        //3.调用业务
			int count = iNetEpdRouteType.queryCountByCustom(
					organizeSeq, routetypeCode, routetypeName);
			result.put("count", String.valueOf(count));
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}
	@SuppressWarnings("rawtypes")
	public String queryByOrganizeSeq(String security,String parameter) {
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
			List<EpdRoutetype> epdRoutetypes = iNetEpdRouteType.queryByOrganizeSeq(
					organizeSeq);
			JSONArray jsonarray = JSONArray.fromObject(epdRoutetypes);
			result.put("epdRoutetypes", jsonarray.toString());
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}
	/**
	 * queryByAll方法描述：查询线路类型
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-23 下午01:37:24
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @return List<EpdRoutetype> 线路类型
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
			List<EpdRoutetype> epdRoutetypes = iNetEpdRouteType.queryByAll();
			JSONArray jsonarray = JSONArray.fromObject(epdRoutetypes);
			result.put("epdRoutetypes", jsonarray.toString());
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}
}
	
	
