/**
 * 
 * 开发人员：Young
 * 开发时间：2017-10-14 下午8:28:07
 */
package cn.smbms.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.smbms.mapper.BillMapper;
import cn.smbms.pojo.Bill;
import cn.smbms.service.BillService;
import cn.smbms.util.PageUtil;

/**
 * 订单业务实现类
 * 开发人员：Young
 * 开发时间：2017-10-14 下午8:28:07
 */
@Service("billService")
public class BillServiceImpl implements BillService {

	@Autowired
	private BillMapper billMapper;
	public void setBillMapper(BillMapper billMapper) {
		this.billMapper = billMapper;
	}
	/**
	 * 动态查询
	 * 构造函数 
	 *
	 * 开发人员：Young
	 * 开发时间：2017-10-14 下午8:57:25
	 * @param map
	 * @return
	 */
	@Override
	public PageUtil getBillList(Map<String, Object> map) {

		PageUtil billPage = new PageUtil();
		billPage.setCount(billMapper.getBillCount(map)); 
		billPage.setList(billMapper.getBillList(map));

		return billPage;
	}
	/**
	 * 显示详情
	 * 构造函数 
	 *
	 * 开发人员：Young
	 * 开发时间：2017-10-15 上午8:44:16
	 * @param id
	 * @return
	 */
	@Override
	public Bill findById(Integer id) {
		
		return billMapper.findById(id);
	}
	/**
	 * 添加
	 * 构造函数 
	 *
	 * 开发人员：Young
	 * 开发时间：2017-10-15 上午9:07:27
	 * @param bill
	 * @return
	 */
	@Override
	public Integer addBill(Bill bill) {
		
		return billMapper.addBill(bill);
	}
	
	/**
	 * 修改
	 * 构造函数 
	 *
	 * 开发人员：Young
	 * 开发时间：2017-10-15 上午9:28:48
	 * @param bill
	 * @return
	 */
	@Override
	public Integer updateBill(Bill bill) {
		
		return billMapper.updateBill(bill);
	}
	
	/**
	 * 删除
	 * 构造函数 
	 *
	 * 开发人员：Young
	 * 开发时间：2017-10-15 上午9:40:22
	 * @param id
	 * @return
	 */
	@Override
	public Integer deleteBill(Integer id) {
		
		return billMapper.deleteBill(id);
	}



}
