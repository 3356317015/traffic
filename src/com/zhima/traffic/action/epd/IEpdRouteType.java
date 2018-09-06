
package com.zhima.traffic.action.epd;

import java.util.List;
import java.util.Map;

import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.model.EpdRoutetype;

/**
 * IEpdRouteType概要说明：线路类型
 * @author lcy
 */
public interface IEpdRouteType {
	
	/**
	 * insert方法描述：添加线路类型
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-22 上午09:55:08
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param epdRoutetype 城市区域
	 * @return EpdRoutetype 城市区域
	 */
	public EpdRoutetype insert(EpdRoutetype epdRoutetype,Map<String, Object> config) throws UserBusinessException;
	
	/**
	 * update方法描述：修改线路类型
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-22 上午09:56:01
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param epdRoutetype 城市区域
	 */
	public void update(EpdRoutetype epdRoutetype,Map<String, Object> config) throws UserBusinessException;
	
	/**
	 * deleteByPK方法描述：根据主键删除线路类型
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-22 上午09:56:56
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param epdRoutetypes 线路类型
	 */
	public void delete(List<EpdRoutetype> epdRoutetypes,Map<String, Object> config) throws UserBusinessException;
	
	/**
	 * queryByPK方法描述：根据主键查询线路类型
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2015-10-24 上午11:42:45
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param routetypeSeq
	 * @throws UserBusinessException
	 * @return List<EpdRoutetype> 线路类型
	 */
	public List<EpdRoutetype> queryByPK(String routetypeSeq) throws UserBusinessException;
	
	/**
	 * queryPageByCustom方法描述：自定义条件分页查询线路类型
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-22 上午10:43:29
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param routetypeCode 线路类型代码
	 * @param routetypeName 线路类型名称
	 * @param start 分页开始行
	 * @param limit 分页结束行
	 * @return List<EpdRoutetype> 线路类型
	 */
	public List<EpdRoutetype> queryPageByCustom(String organizeSeq, String routetypeCode, String routetypeName
			,int start,int limit) throws UserBusinessException;
	
	/**
	 * queryAllByCustom方法描述：自定义条件查询线路类型
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-22 上午10:45:11
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param routetypeCode 线路类型代码
	 * @param routetypeName 线路类型名称
	 * @return List<EpdRoutetype> 线路类型
	 */
	public List<EpdRoutetype> queryAllByCustom(String organizeSeq, String routetypeCode, String routetypeName) throws UserBusinessException;
	
	/**
	 * queryCountByCustom方法描述：自定义条件查询线路类型记录数
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-22 上午10:46:15
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param routetypeCode 线路类型代码
	 * @param routetypeName 线路类型名称
	 * @return int 线路类型记录数
	 */
	public int queryCountByCustom(String organizeSeq, String routetypeCode, String routetypeName) throws UserBusinessException;

	public List<EpdRoutetype> queryByOrganizeSeq(String organizeSeq) throws UserBusinessException;

	/**
	 * queryByAll方法描述：查询线路类型
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-23 下午01:37:24
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @return List<EpdRoutetype> 线路类型
	 * @throws UserBusinessException
	 */
	public List<EpdRoutetype> queryByAll() throws UserBusinessException;
	
}
	
	
