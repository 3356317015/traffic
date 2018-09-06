
package com.service.traffic.action.sam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.service.comm.JsonUtil;
import com.service.comm.SecurityFinal;
import com.service.traffic.business.sam.INetSamGroup;
import com.service.traffic.business.sam.impl.ImpNetSamGroup;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.frame.model.SamGroup;

/**
 * ISamGroup概要说明：组(角色)接口
 * @author lcy
 */
public class SamGroupAction {
	INetSamGroup iNetSamGroup = new ImpNetSamGroup();
	/**
	 * insert方法描述：添加角色
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-23 下午12:31:14
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param samGroup 角色
	 * @return SamGroup 角色
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

	        SamGroup group =(SamGroup)JsonUtil.convertObject(
	        		mapParameter.get("samGroup").toString(),SamGroup.class);
	        
	        String strConfig = JSONObject.fromObject(mapParameter.get("config").toString()).toString();
			JSONObject jsonConfig = JSONObject.fromObject(strConfig);
			Map mapConfig = (Map)jsonConfig;
	        //3.调用业务
	        SamGroup samGroup = iNetSamGroup.insert(group,mapConfig);
	        result.put("samGroup", JSONObject.fromObject(samGroup).toString());
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}
	
	/**
	 * update方法描述：修改角色
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-23 下午12:31:26
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param samGroup 角色
	 * @throws UserBusinessException void
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
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
	        
	        SamGroup group =(SamGroup)JsonUtil.convertObject(
	        		mapParameter.get("samGroup").toString(),SamGroup.class);
	        
	        String strConfig = JSONObject.fromObject(mapParameter.get("config").toString()).toString();
			JSONObject jsonConfig = JSONObject.fromObject(strConfig);
			Map mapConfig = (Map)jsonConfig;
	        //3.调用业务
			iNetSamGroup.update(group,mapConfig);
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}
	
	/**
	 * deleteByPk方法描述：根据主键删除角色
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-23 下午12:31:32
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param groupSeq 角色主键
	 * @throws UserBusinessException void
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String deleteByPk(String security,String parameter){
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
			List<SamGroup> samGroups = (List<SamGroup>)JsonUtil.convertArray(
					mapParameter.get("samGroups").toString(), new SamGroup());
	        
	        String strConfig = JSONObject.fromObject(mapParameter.get("config").toString()).toString();
			JSONObject jsonConfig = JSONObject.fromObject(strConfig);
			Map mapConfig = (Map)jsonConfig;
	        //3.调用业务
			iNetSamGroup.deleteByPk(samGroups, mapConfig);
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}
	
	@SuppressWarnings("rawtypes")
	public String queryByOrganizeSeq(String security,String parameter){
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
			List<SamGroup> organizes = iNetSamGroup.queryByOrganizeSeq(organizeSeq);
			JSONArray jsonarray = JSONArray.fromObject(organizes);
			result.put("samGroups", jsonarray.toString());
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}

}
