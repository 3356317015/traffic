
package com.zhima.frame.action.sam;

import java.util.List;
import java.util.Map;

import com.zhima.basic.exception.UserBusinessException;
import com.zhima.frame.model.SamTableseq;

public interface ISamTableseq {
	
	/**
	 * insert方法描述：添加主键设置
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-7-3 下午04:24:31
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param samTableseq　主键设置
	 * @return SamTableseq 主键设置
	 * @throws UserBusinessException
	 */
	public SamTableseq insert(SamTableseq samTableseq,Map<String, Object> config) throws UserBusinessException;
	
	/**
	 * update方法描述：修改主键设置
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-7-3 下午04:25:07
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param samTableseq　主键设置
	 * @throws UserBusinessException
	 */
	public void update(SamTableseq samTableseq,Map<String, Object> config) throws UserBusinessException;
	
	/**
	 * delete方法描述：删除主键设置
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-7-3 下午04:25:41
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param samTableseqs 主键设置
	 * @throws UserBusinessException
	 */
	public void delete(List<SamTableseq> samTableseqs,Map<String, Object> config) throws UserBusinessException;
	
	/**
	 * queryByTableName方法描述：要据表名查询主键设置
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-7-3 下午10:51:05
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param tableName 表名
	 * @return SamTableseq 主键设置
	 * @throws UserBusinessException
	 */
	public SamTableseq queryByPk(String organizeSeq, String tableName) throws UserBusinessException;
	
	/**
	 * queryPageByAll方法描述：分页查询主键设置
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-7-3 下午04:26:24
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param start 分页开始行号
	 * @param limit 分页结束行号
	 * @return List<SamTableseq> 主键设置
	 * @throws UserBusinessException
	 */
	public List<SamTableseq> queryPageByCustom(String organizeSeq, int start,int limit) throws UserBusinessException;
	
	/**
	 * queryCountByAll方法描述：查询主键设置记录数
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-7-3 下午04:27:09
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @return int 记录数
	 * @throws UserBusinessException
	 */
	public int queryCountByCustom(String organizeSeq) throws UserBusinessException;
	
	/**
	 * queryByAll方法描述：查询所有主键设置
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-7-3 下午04:27:42
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @return List<SamTableseq> 主键设置
	 * @throws UserBusinessException
	 */
	public List<SamTableseq> queryAllByCustom(String organizeSeq) throws UserBusinessException;

}
	
	
