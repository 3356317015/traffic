
package com.service.traffic.business.sam;

import java.util.List;
import java.util.Map;

import com.zhima.basic.exception.UserBusinessException;
import com.zhima.frame.model.SamLogOper;

/** ISamLogOper概要说明:日志操作接口
 * @author Administrator
 */
public interface INetSamLogOper {
	
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
	public SamLogOper insert(SamLogOper samLogOper,Map<String, Object> config) throws UserBusinessException;

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
	public void update(SamLogOper samLogOper, String status, String operDesc) throws UserBusinessException;

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
	public List<SamLogOper> queryPageByCustom(String organizeSeq, String moduleCode, String operType, String status, 
			String operUser, String startDate, String endDate, int start, int limit) throws UserBusinessException;
	
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
	public List<SamLogOper> queryAllByCustom(String organizeSeq, String moduleCode, String operType, String status, 
			String operUser, String startDate, String endDate) throws UserBusinessException;
	
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
	public int queryCountByCustom(String organizeSeq, String moduleCode, String operType, String status, 
			String operUser, String startDate, String endDate) throws UserBusinessException;

	/**
	 * deleteByOperSeq方法描述：根据操作序号删除操作日志
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-29 下午02:11:00
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param samLogOpers 操作日志清单
	 * @throws UserBusinessException void
	 */
	public void deleteByOperSeq(List<SamLogOper> samLogOpers,Map<String, Object> config) throws UserBusinessException;

}
	
	
