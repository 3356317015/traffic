
package com.zhima.frame.action.sam;

import java.util.List;

import com.zhima.basic.exception.UserBusinessException;
import com.zhima.frame.model.SamLogDetail;

/** ISamLogDetail概要说明:日志详细操作接口
 * @author Administrator
 */
public interface ISamLogDetail {
	
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
	public List<SamLogDetail> queryByOperSeq(String operSeq) throws UserBusinessException;

}
	
	
