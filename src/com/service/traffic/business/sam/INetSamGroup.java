
package com.service.traffic.business.sam;

import java.util.List;
import java.util.Map;

import com.zhima.basic.exception.UserBusinessException;
import com.zhima.frame.model.SamGroup;

/**
 * ISamGroup概要说明：组(角色)接口
 * @author lcy
 */
public interface INetSamGroup {
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
	public SamGroup insert(SamGroup samGroup,Map<String, Object> config) throws UserBusinessException;
	
	/**
	 * update方法描述：修改角色
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-23 下午12:31:26
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param samGroup 角色
	 * @throws UserBusinessException void
	 */
	public void update(SamGroup samGroup,Map<String, Object> config) throws UserBusinessException;
	
	/**
	 * deleteByPk方法描述：根据主键删除角色
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-23 下午12:31:32
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param groupSeq 角色主键
	 * @throws UserBusinessException void
	 */
	public void deleteByPk(List<SamGroup> samGroups,Map<String, Object> config) throws UserBusinessException;
	
	/**
	 * queryByAll方法描述：查询所有角色
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-23 下午12:31:37
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @return List<SamGroup> 角色清单
	 * @throws UserBusinessException
	 */
	public List<SamGroup> queryByAll() throws UserBusinessException;
	
	public List<SamGroup> queryByOrganizeSeq(String organizeSeq) throws UserBusinessException;

}
	
	
