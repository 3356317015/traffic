
package com.service.traffic.action.epd;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.service.comm.JsonUtil;
import com.service.comm.SecurityFinal;
import com.service.traffic.business.epd.INetEpdCompany;
import com.service.traffic.business.epd.impl.ImpNetEpdCompany;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.model.EpdCompany;

/**
 * IEpdCompany概要说明：公司接口
 * @author lcy
 */
public class EpdCompanyAction {
	INetEpdCompany iNetEpdCompany = new ImpNetEpdCompany();
	/**
	 * insert方法描述：添加公司
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-22 上午09:55:08
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param epdCompany 公司
	 * @return epdCompany 公司
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

	        EpdCompany epdCompany =(EpdCompany)JsonUtil.convertObject(
	        		mapParameter.get("epdCompany").toString(),EpdCompany.class);

	        String strConfig = JSONObject.fromObject(mapParameter.get("config").toString()).toString();
			JSONObject jsonConfig = JSONObject.fromObject(strConfig);
			Map mapConfig = (Map)jsonConfig;
	        //3.调用业务
			EpdCompany newEpdCompany = iNetEpdCompany.insert(epdCompany, mapConfig);
	        result.put("epdCompany", JSONObject.fromObject(newEpdCompany).toString());
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}
	
	/**
	 * update方法描述：修改公司
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-22 上午09:56:01
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param epdCompany 公司
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

	        EpdCompany epdCompany =(EpdCompany)JsonUtil.convertObject(
	        		mapParameter.get("epdCompany").toString(),EpdCompany.class);

	        String strConfig = JSONObject.fromObject(mapParameter.get("config").toString()).toString();
			JSONObject jsonConfig = JSONObject.fromObject(strConfig);
			Map mapConfig = (Map)jsonConfig;
	        //3.调用业务
			iNetEpdCompany.insert(epdCompany, mapConfig);
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}
	
	/**
	 * deleteByPK方法描述：根据主键删除公司
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-22 上午09:56:56
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param companySeq 公司主键
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

			List<EpdCompany> epdCompanies = (List<EpdCompany>)JsonUtil.convertArray(
					mapParameter.get("epdCompanyies").toString(), new EpdCompany());

	        String strConfig = JSONObject.fromObject(mapParameter.get("config").toString()).toString();
			JSONObject jsonConfig = JSONObject.fromObject(strConfig);
			Map mapConfig = (Map)jsonConfig;
	        //3.调用业务
			iNetEpdCompany.delete(epdCompanies, mapConfig);
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}
	
	/**
	 * queryPageByCustom方法描述：自定义分页查询公司
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-22 下午05:08:15
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param companyCode 公司代码
	 * @param companyName 公司名称
	 * @param companyType 公司性质
	 * @param industry 行业类型
	 * @param start 分页开始行
	 * @param limit 分页结束行
	 * @return List<EpdCompany> 公司
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
	        String companyCode = String.valueOf(mapParameter.get("companyCode"));
	        String companyName = String.valueOf(mapParameter.get("companyName"));
	        String companyType = String.valueOf(mapParameter.get("companyType"));
	        String industry = String.valueOf(mapParameter.get("industry"));
	        int start = Integer.valueOf(mapParameter.get("start").toString());
	        int limit = Integer.valueOf(mapParameter.get("limit").toString());
	        //3.调用业务
			List<EpdCompany> epdCompanies = iNetEpdCompany.queryPageByCustom(
					organizeSeq, companyCode, companyName, companyType, industry, start, limit);
			JSONArray jsonarray = JSONArray.fromObject(epdCompanies);
			result.put("epdCompanies", jsonarray.toString());
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}
	
	/**
	 * queryAllByCustom方法描述：自定义查询公司
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-22 下午05:10:46
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param companyCode 公司代码
	 * @param companyName 公司名称
	 * @param companyType 公司性质
	 * @param industry 行业类型
	 * @return List<EpdCompany> 公司
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
	        String companyCode = String.valueOf(mapParameter.get("companyCode"));
	        String companyName = String.valueOf(mapParameter.get("companyName"));
	        String companyType = String.valueOf(mapParameter.get("companyType"));
	        String industry = String.valueOf(mapParameter.get("industry"));
	        //3.调用业务
			List<EpdCompany> epdCompanies = iNetEpdCompany.queryAllByCustom(
					organizeSeq, companyCode, companyName, companyType, industry);
			JSONArray jsonarray = JSONArray.fromObject(epdCompanies);
			result.put("epdCompanies", jsonarray.toString());
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}
	
	/**
	 * queryCountByCustom方法描述：自定义查询公司记录数
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-22 下午05:11:58
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param companyCode 公司代码
	 * @param companyName 公司名称
	 * @param companyType 公司性质
	 * @param industry 行业类型
	 * @return int 记录数
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
	        String companyCode = String.valueOf(mapParameter.get("companyCode"));
	        String companyName = String.valueOf(mapParameter.get("companyName"));
	        String companyType = String.valueOf(mapParameter.get("companyType"));
	        String industry = String.valueOf(mapParameter.get("industry"));
	        //3.调用业务
			int count = iNetEpdCompany.queryCountByCustom(
					organizeSeq, companyCode, companyName, companyType, industry);
			result.put("count", String.valueOf(count));
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}

	/**
	 * queryByAll方法描述：查询所有公司
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-23 下午01:27:09
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @return List<EpdCompany> 公司
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
			List<EpdCompany> epdCompanies = iNetEpdCompany.queryByAll();
			JSONArray jsonarray = JSONArray.fromObject(epdCompanies);
			result.put("epdCompanies", jsonarray.toString());
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
			List<EpdCompany> epdCompanies = iNetEpdCompany.queryByOrganizeSeq(organizeSeq);
			JSONArray jsonarray = JSONArray.fromObject(epdCompanies);
			result.put("epdCompanies", jsonarray.toString());
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}
}
	
	
