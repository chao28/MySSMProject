/**
 * 
 * 开发人员：Young
 * 开发时间：2017-10-14 下午8:26:44
 */
package cn.smbms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.smbms.pojo.Bill;
import cn.smbms.pojo.Provider;
import cn.smbms.pojo.User;

/**
 * 订单接口
 * 开发人员：Young
 * 开发时间：2017-10-14 下午8:26:44
 */
public interface BillMapper {

	/**
	 * 获取订单总数量
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-14 下午8:58:25
	 * @param map
	 * @return
	 */
	Integer getBillCount(Map<String, Object> map);

	/**
	 * 获取订单列表(动态查询)
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-14 下午8:58:54
	 * @param map
	 * @return
	 */
	List<Bill> getBillList(Map<String, Object> map);

	/**
	 * 显示详情
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-15 上午8:44:54
	 * @param id
	 * @return
	 */
	Bill findById(@Param("id")Integer id);

	/**
	 * 添加
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-15 上午9:08:10
	 * @param bill
	 * @return
	 */
	Integer addBill(Bill bill);

	/**
	 * 修改
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-15 上午9:29:25
	 * @param bill
	 * @return
	 */
	Integer updateBill(Bill bill);

	/**
	 * 删除
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-15 上午9:40:51
	 * @param id
	 * @return
	 */
	Integer deleteBill(Integer id);

	
	
}
