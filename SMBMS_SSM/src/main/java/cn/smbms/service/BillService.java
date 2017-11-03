package cn.smbms.service;

import java.util.Map;

import cn.smbms.pojo.Bill;
import cn.smbms.util.PageUtil;

/**
 * 订单业务接口
 * 
 * 开发人员：Young
 * 开发时间：2017-10-14 下午8:27:15
 */
public interface BillService {

	/**
	 * 动态查询
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-14 下午8:56:59
	 * @param map
	 * @return
	 */
	PageUtil getBillList(Map<String, Object> map);

	/**
	 * 显示详情
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-15 上午8:43:54
	 * @param id
	 * @return
	 */
	Bill findById(Integer id);

	/**
	 * 添加
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-15 上午9:07:09
	 * @param bill
	 * @return
	 */
	Integer addBill(Bill bill);

	/**
	 * 修改
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-15 上午9:28:30
	 * @param bill
	 * @return
	 */
	Integer updateBill(Bill bill);

	/**
	 * 删除
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-15 上午9:39:59
	 * @param id
	 * @return
	 */
	Integer deleteBill(Integer id);

	
	
}
