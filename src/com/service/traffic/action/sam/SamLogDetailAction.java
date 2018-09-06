
package com.service.traffic.action.sam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.service.comm.SecurityFinal;
import com.service.traffic.business.sam.INetSamLogDetail;
import com.service.traffic.business.sam.impl.ImpNetSamLogDetail;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.frame.model.SamLogDetail;

/** ISamLogDetail概要说明:日志详细操作接口
 * @author Administrator
 */
public class SamLogDetailAction {
	INetSamLogDetail iNetSamLogDetail = new ImpNetSamLogDetail();
	/**
	 * queryByOperSeq方法描述：根据操作日志序号查询
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-29 下午01:21:43
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param operSeq 操作日志序号
	 * @return List<SamLogDetail> 日志明细
	 * @throws UserBusinessException
	 */
	@SuppressWarnings("rawtypes")
	public String queryByOperSeq(String security,String parameter){
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
	        String operSeq = String.valueOf(mapParameter.get("operSeq"));
	        //3.调用业务
			List<SamLogDetail> logDetails = iNetSamLogDetail.queryByOperSeq(operSeq);
			JSONArray jsonarray = JSONArray.fromObject(logDetails);
			result.put("samLogDetails", jsonarray.toString());
		} catch (UserBusinessException e) {
			e.printStackTrace();
			result.put("ResultCode", "1");
			result.put("Message", e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}
}