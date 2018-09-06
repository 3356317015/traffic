
package com.service.traffic.business.epd;

import java.util.List;
import java.util.Map;

import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.model.EpdCompany;

/**
 * IEpdCompany概要说明：公司接口
 * @author lcy
 */
public interface INetEpdCompany {
	
	/**
	 * insert方法描述：添加公司
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-22 上午09:55:08
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param epdCompany 公司
	 * @return epdCompany 公司
	 */
	public EpdCompany insert(EpdCompany epdCompany,Map<String, Object> config) throws UserBusinessException;
	
	/**
	 * update方法描述：修改公司
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-22 上午09:56:01
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param epdCompany 公司
	 */
	public void update(EpdCompany epdCompany,Map<String, Object> config) throws UserBusinessException;
	
	/**
	 * deleteByPK方法描述：根据主键删除公司
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-22 上午09:56:56
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param companySeq 公司主键
	 */
	public void delete(List<EpdCompany> companies,Map<String, Object> config) throws UserBusinessException;
	
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
	public List<EpdCompany> queryPageByCustom(String organizeSeq, String companyCode,String companyName,
			String companyType,String industry,int start,int limit) throws UserBusinessException;
	
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
	public List<EpdCompany> queryAllByCustom(String organizeSeq, String companyCode,String companyName,
			String companyType,String industry) throws UserBusinessException;
	
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
	public int queryCountByCustom(String organizeSeq, String companyCode,String companyName,
			String companyType, String industry) throws UserBusinessException;

	/**
	 * queryByAll方法描述：查询所有公司
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-23 下午01:27:09
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @return List<EpdCompany> 公司
	 * @throws UserBusinessException
	 */
	public List<EpdCompany> queryByAll() throws UserBusinessException;

	public List<EpdCompany> queryByOrganizeSeq(String organizeSeq) throws UserBusinessException;

}
	
	
