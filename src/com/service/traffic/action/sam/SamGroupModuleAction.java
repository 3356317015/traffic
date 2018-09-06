
package com.service.traffic.action.sam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.service.comm.JsonUtil;
import com.service.comm.SecurityFinal;
import com.service.traffic.business.sam.INetSamGroupModule;
import com.service.traffic.business.sam.impl.ImpNetSamGroupModule;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.frame.model.SamGroupModule;

public class SamGroupModuleAction {
	INetSamGroupModule iNetSamGroupModule = new ImpNetSamGroupModule(); 
	/**
	 * updateGroupRight方法描述：更新角色权限
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-24 上午12:32:11
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param groupSeq 角色序号
	 * @param samGroupModules 角色权限列表
	 * @throws UserBusinessException void
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String updateGroupRight(String security,String parameter){
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
	        String groupSeq =  mapParameter.get("groupSeq").toString();
			List<SamGroupModule> samGroupModules = (List<SamGroupModule>)JsonUtil.convertArray(
					mapParameter.get("samGroupModules").toString(), new SamGroupModule());
	        
	        String strConfig = JSONObject.fromObject(mapParameter.get("config").toString()).toString();
			JSONObject jsonConfig = JSONObject.fromObject(strConfig);
			Map mapConfig = (Map)jsonConfig;
	        //3.调用业务
			iNetSamGroupModule.updateGroupRight(groupSeq, samGroupModules, mapConfig);
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}

}
