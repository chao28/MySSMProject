/**
 * 
 * 开发人员：Young
 * 开发时间：2017-10-14 下午8:48:33
 */
package cn.smbms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.smbms.pojo.Provider;

/**
 * 
 * 开发人员：Young
 * 开发时间：2017-10-14 下午8:48:33
 */
public interface ProMapper {

	/**
	 * 获取供应商列表
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-14 下午8:51:10
	 * @return
	 */
	List<Provider> findProviderAll();

	/**
	 * 获取供应商总数量
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-15 上午9:58:52
	 * @param proMap
	 * @return
	 */
	Integer findProCount(Map<String, Object> proMap);

	/**
	 * 动态查询
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-15 上午9:59:12
	 * @param proMap
	 * @return
	 */
	List<Provider> findProAll(Map<String, Object> proMap);

	/**
	 * 添加
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-15 上午10:18:45
	 * @param pro
	 * @return
	 */
	Integer addPro(Provider pro);

	/**
	 * 根据id查询供应商
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-15 上午10:26:38
	 * @param id
	 * @return
	 */
	Provider findById(@Param("id")Integer id);

	/**
	 * 修改
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-15 上午10:37:09
	 * @param pro
	 * @return
	 */
	Integer updatePro(Provider pro);

	/**
	 * 判断与供应商关联的订单数量
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-15 上午10:47:05
	 * @param id
	 * @return
	 */
	Integer findProOrBillById(@Param("id")Integer id);

	/**
	 * 删除
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-15 上午10:50:30
	 * @param id
	 * @return
	 */
	Integer deletePro(@Param("id")Integer id);

}
