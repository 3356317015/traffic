
package com.service.traffic.action.sam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.service.comm.JsonUtil;
import com.service.comm.SecurityFinal;
import com.service.traffic.business.sam.INetSamLogOper;
import com.service.traffic.business.sam.impl.ImpNetSamLogOper;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.frame.model.SamLogOper;

/** ISamLogOper概要说明:日志操作接口
 * @author Administrator
 */
public class SamLogOperAction {
	
	INetSamLogOper iNetSamLogOper = new ImpNetSamLogOper();
	/**
	 * insert方法描述：添加模块操作日志
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2012-12-17 上午11:06:34
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param samLogOper 模块操作日志
	 * @return SamLogModule 添加后带主键的模块操作日志
	 * @throws UserBusinessException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String insert(String security,String parameter){
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

	        SamLogOper logOper =(SamLogOper)JsonUtil.convertObject(
	        		mapParameter.get("samLogOper").toString(),SamLogOper.class);
	        
	        String strConfig = JSONObject.fromObject(mapParameter.get("config").toString()).toString();
			JSONObject jsonConfig = JSONObject.fromObject(strConfig);
			Map mapConfig = (Map)jsonConfig;
	        //3.调用业务
	        SamLogOper samLogOper = iNetSamLogOper.insert(logOper,mapConfig);
	        result.put("samLogOper", JSONObject.fromObject(samLogOper).toString());
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}

	/**
	 * update方法描述：更新操作日志状态
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-4 下午07:43:47
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param samLogOper 操作日志
	 * @param status 状态
	 * @param operDesc 操作信息
	 * @throws UserBusinessException void
	 */
	@SuppressWarnings("rawtypes")
	public String update(String security,String parameter){
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
	        
	        SamLogOper logOper =(SamLogOper)JsonUtil.convertObject(
	        		mapParameter.get("samLogOper").toString(),SamLogOper.class);

	        String status = String.valueOf(mapParameter.get("status"));
	        String operDesc = String.valueOf(mapParameter.get("operDesc"));
	        //3.调用业务
			iNetSamLogOper.update(logOper, status, operDesc);
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}
	/**
	 * queryPageByCustom方法描述：自定义条件分页查询操作日志
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-28 下午11:45:48
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param moduleCode 模块代码
	 * @param operType 操作类型
	 * @param status 操作状态
	 * @param operUser 操作人
	 * @param startDate 操作开始日期
	 * @param endDate 操作结束日期
	 * @param start 分页开始行
	 * @param limit 分页结束行
	 * @return List<SamLogOper> 操作日志
	 * @throws UserBusinessException
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
	        
	        //3.调用业务
			List<SamLogOper> samLogOpers = iNetSamLogOper.queryPageByCustom(
					String.valueOf(mapParameter.get("organizeSeq")), 
					String.valueOf(mapParameter.get("moduleCode")), 
					String.valueOf(mapParameter.get("operType")), 
					String.valueOf(mapParameter.get("status")), 
					String.valueOf(mapParameter.get("operUser")), 
					String.valueOf(mapParameter.get("startDate")), 
					String.valueOf(mapParameter.get("endDate")), 
					Integer.valueOf((mapParameter.get("start")).toString()), 
					Integer.valueOf(mapParameter.get("limit").toString()));
			JSONArray jsonarray = JSONArray.fromObject(samLogOpers);
			result.put("samLogOpers", jsonarray.toString());
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}
	/**
	 * queryAllByCustom方法描述：自定义条件查询操作日志
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-28 下午11:47:22
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param moduleCode 模块代码
	 * @param operType 操作类型
	 * @param status 操作状态
	 * @param operUser 操作人
	 * @param startDate 操作开始日期
	 * @param endDate 操作结束日期
	 * @return List<SamLogOper> 操作日志
	 * @throws UserBusinessException
	 */
	@SuppressWarnings("rawtypes")
	public String queryAllByCustom(String security,String parameter){
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
	        
	        //3.调用业务
			List<SamLogOper> samLogOpers = iNetSamLogOper.queryAllByCustom(
					String.valueOf(mapParameter.get("organizeSeq")), 
					String.valueOf(mapParameter.get("moduleCode")), 
					String.valueOf(mapParameter.get("operType")), 
					String.valueOf(mapParameter.get("status")), 
					String.valueOf(mapParameter.get("operUser")), 
					String.valueOf(mapParameter.get("startDate")), 
					String.valueOf(mapParameter.get("endDate")));
			JSONArray jsonarray = JSONArray.fromObject(samLogOpers);
			result.put("samLogOpers", jsonarray.toString());
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}
	/**
	 * queryCountByCustom方法描述：自定义条件查询操作日志记录数
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-28 下午11:47:59
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param moduleCode 模块代码
	 * @param operType 操作类型
	 * @param status 操作状态
	 * @param operUser 操作人
	 * @param startDate 操作开始日期
	 * @param endDate 操作结束日期
	 * @return int 记录数
	 * @throws UserBusinessException
	 */
	@SuppressWarnings("rawtypes")
	public String queryCountByCustom(String security,String parameter){
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
	        
	        //3.调用业务
			int count = iNetSamLogOper.queryCountByCustom(
					String.valueOf(mapParameter.get("organizeSeq")), 
					String.valueOf(mapParameter.get("moduleCode")), 
					String.valueOf(mapParameter.get("operType")), 
					String.valueOf(mapParameter.get("status")), 
					String.valueOf(mapParameter.get("operUser")), 
					String.valueOf(mapParameter.get("startDate")), 
					String.valueOf(mapParameter.get("endDate")));
			result.put("count", String.valueOf(count));
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}
	/**
	 * deleteByOperSeq方法描述：根据操作序号删除操作日志
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-29 下午02:11:00
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param samLogOpers 操作日志清单
	 * @throws UserBusinessException void
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String deleteByOperSeq(String security,String parameter){
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
	        
			List<SamLogOper> samLogOpers = (List<SamLogOper>)JsonUtil.convertArray(
					mapParameter.get("samLogOpers").toString(), new SamLogOper());

	        String strConfig = JSONObject.fromObject(mapParameter.get("config").toString()).toString();
			JSONObject jsonConfig = JSONObject.fromObject(strConfig);
			Map mapConfig = (Map)jsonConfig;
	        //3.调用业务
			iNetSamLogOper.deleteByOperSeq(samLogOpers, mapConfig);
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}
}
	
	
