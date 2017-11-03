/**
 * 
 * 开发人员：Young
 * 开发时间：2017-10-14 下午8:49:44
 */
package cn.smbms.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.smbms.mapper.ProMapper;
import cn.smbms.pojo.Provider;
import cn.smbms.service.ProService;
import cn.smbms.util.PageUtil;

/**
 * 
 * 开发人员：Young
 * 开发时间：2017-10-14 下午8:49:44
 */
@Service("proService")
public class ProServiceImpl implements ProService {

	@Autowired
	private ProMapper proMapper;
	public void setProMapper(ProMapper proMapper) {
		this.proMapper = proMapper;
	}
	/**
	 * 获取供应商列表
	 * 构造函数 
	 *
	 * 开发人员：Young
	 * 开发时间：2017-10-14 下午8:52:57
	 * @return
	 */
	@Override
	public List<Provider> findProviderAll() {

		return proMapper.findProviderAll();
	}
	/**
	 * 动态查询
	 * 构造函数 
	 *
	 * 开发人员：Young
	 * 开发时间：2017-10-15 上午9:57:58
	 * @param proMap
	 * @return
	 */
	@Override
	public PageUtil findByPage(Map<String, Object> proMap) {
		PageUtil pageUtil = new PageUtil();

		pageUtil.setCount(proMapper.findProCount(proMap));
		pageUtil.setProList(proMapper.findProAll(proMap));

		return pageUtil;
	}
	/**
	 * 添加
	 * 构造函数 
	 *
	 * 开发人员：Young
	 * 开发时间：2017-10-15 上午10:18:26
	 * @param pro
	 * @return
	 */
	@Override
	public Integer addPro(Provider pro) {
		
		return proMapper.addPro(pro);
	}
	
	/**
	 * 根据id查询供应商
	 * 构造函数 
	 *
	 * 开发人员：Young
	 * 开发时间：2017-10-15 上午10:26:00
	 * @param id
	 * @return
	 */
	@Override
	public Provider findById(Integer id) {
		
		return proMapper.findById(id);
	}
	
	/**
	 * 修改
	 * 构造函数 
	 *
	 * 开发人员：Young
	 * 开发时间：2017-10-15 上午10:36:38
	 * @param pro
	 * @return
	 */
	@Override
	public Integer updatePro(Provider pro) {
		
		return proMapper.updatePro(pro);
	}
	
	/**
	 * 判断与供应商关联的订单数量
	 * 构造函数 
	 *
	 * 开发人员：Young
	 * 开发时间：2017-10-15 上午10:46:26
	 * @param id
	 * @return
	 */
	@Override
	public Integer findProOrBillById(Integer id) {
		
		return proMapper.findProOrBillById(id);
	}
	
	/**
	 * 删除
	 * 构造函数 
	 *
	 * 开发人员：Young
	 * 开发时间：2017-10-15 上午10:50:03
	 * @param id
	 * @return
	 */
	@Override
	public Integer deletePro(Integer id) {
		
		return proMapper.deletePro(id);
	}




}
