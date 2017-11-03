/**
 * 
 * 开发人员：Young
 * 开发时间：2017-10-14 下午8:49:15
 */
package cn.smbms.service;

import java.util.List;
import java.util.Map;

import cn.smbms.pojo.Provider;
import cn.smbms.util.PageUtil;

/**
 * 
 * 开发人员：Young
 * 开发时间：2017-10-14 下午8:49:15
 */
public interface ProService {

	/**
	 * 获取供应商列表
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-14 下午8:52:39
	 * @return
	 */
	List<Provider> findProviderAll();

	/**
	 * 动态查询
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-15 上午9:57:38
	 * @param proMap
	 * @return
	 */
	PageUtil findByPage(Map<String, Object> proMap);

	/**
	 * 添加
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-15 上午10:17:49
	 * @param pro
	 * @return
	 */
	Integer addPro(Provider pro);

	/**
	 * 根据id查询供应商
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-15 上午10:25:35
	 * @param id
	 * @return
	 */
	Provider findById(Integer id);

	/**
	 * 修改
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-15 上午10:36:11
	 * @param pro
	 * @return
	 */
	Integer updatePro(Provider pro);

	/**
	 * 判断与供应商关联的订单数量
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-15 上午10:45:40
	 * @param id
	 * @return
	 */
	Integer findProOrBillById(Integer id);

	/**
	 * 删除
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-15 上午10:49:48
	 * @param id
	 * @return
	 */
	Integer deletePro(Integer id);

}
