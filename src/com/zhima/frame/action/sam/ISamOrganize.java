
package com.zhima.frame.action.sam;

import java.util.List;
import java.util.Map;

import com.zhima.basic.exception.UserBusinessException;
import com.zhima.frame.model.SamOrganize;

/**
 * ISamOrganize概要说明：组织机构接口
 * @author lcy
 */
public interface ISamOrganize {
	
	/**
	 * insert方法描述：添加组织机构
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-23 下午01:56:57
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param samOrganize 组织机构
	 * @return SamOrganize 组织机构
	 * @throws UserBusinessException
	 */
	public SamOrganize insert(SamOrganize samOrganize,Map<String, Object> config) throws UserBusinessException;
	
	/**
	 * update方法描述：修改组织机构
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-23 下午01:57:24
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param samOrganize 组织机构
	 * @throws UserBusinessException
	 */
	public void update(SamOrganize samOrganize,Map<String, Object> config) throws UserBusinessException;
	
	/**
	 * delete方法描述：根据主键删除组织机构
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-23 下午01:57:43
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param samOrganizes 组织机构
	 * @throws UserBusinessException
	 */
	public void delete(List<SamOrganize> samOrganizes,Map<String, Object> config) throws UserBusinessException;
	
	/**
	 * queryPageByCustom方法描述：自定义分页查询组织机构
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-23 下午01:58:15
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param organizeCode 组织机构代码
	 * @param organizeName 组织机构名称
	 * @param cityAreaSeq 区域序号
	 * @param companySeq 公司序号
	 * @param start 分页开始行
	 * @param limit 分页结束行
	 * @return List<SamOrganize> 组织机构
	 * @throws UserBusinessException
	 */
	public List<SamOrganize> queryPageByCustom(String organizeCode,String organizeName,
			String organizeLevel,String organizeType,String organizeStatus,int start,int limit) throws UserBusinessException;
	
	/**
	 * queryAllByCustom方法描述：自定义查询组织机构
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-23 下午01:59:12
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param organizeCode 组织机构代码
	 * @param organizeName 组织机构名称
	 * @param cityAreaSeq 区域序号
	 * @param companySeq 公司序号
	 * @return List<SamOrganize> 组织机构
	 * @throws UserBusinessException
	 */
	public List<SamOrganize> queryAllByCustom(String organizeCode,String organizeName,
			String organizeLevel,String organizeType,String organizeStatus) throws UserBusinessException;
	
	/**
	 * queryCountByCustom方法描述：自定义查询组织机构记录数
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-23 下午01:59:38
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param organizeCode 组织机构代码
	 * @param organizeName 组织机构名称
	 * @param cityAreaSeq 区域序号
	 * @param companySeq 公司序号
	 * @return int 记录数
	 * @throws UserBusinessException
	 */
	public int queryCountByCustom(String organizeCode,String organizeName,
			String organizeLevel,String organizeType,String organizeStatus) throws UserBusinessException;

	/**
	 * queryByAll方法描述：查询所有组织机构
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-23 下午02:00:09
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @return List<SamOrganize> 组织机构
	 * @throws UserBusinessException
	 */
	public List<SamOrganize> queryByAll() throws UserBusinessException;

	public List<SamOrganize> queryByOrganizeType(String organizeType) throws UserBusinessException;
	
	public List<SamOrganize> queryDealByOrganizeSeq(String organizeSeq) throws UserBusinessException;

}