/**
 * 
 * 开发人员：Young
 * 开发时间：2017-9-14 下午5:04:07
 */
package cn.smbms.util;

import java.util.ArrayList;
import java.util.List;

import cn.smbms.pojo.Bill;
import cn.smbms.pojo.Provider;
import cn.smbms.pojo.User;

/**
 * 分页工具类
 * 开发人员：Young
 * 开发时间：2017-9-14 下午5:04:07
 */
public class PageUtil {

	//每页显示的记录数
	public static final Integer SIZE = 5;

	//总共有多少条记录
	private Integer count = 0;

	//当前第几页
	private Integer index = 1;

	//总共多少页
	private Integer total = 1;

	//每页显示的数据集合--订单
	private List<Bill> list = new ArrayList<Bill>();

	//每页显示的数据集合--供应商
	private List<Provider> proList = new ArrayList<Provider>();

	//每页显示的数据集合--用户
	private List<User> userList = new ArrayList<User>();

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public List<Provider> getProList() {
		return proList;
	}

	public void setProList(List<Provider> proList) {
		this.proList = proList;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		if (count > 0){

			this.total = count % SIZE == 0 ? count / SIZE : count / SIZE + 1;			
		}
		this.count = count;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}


	public List<Bill> getList() {
		return list;
	}

	public void setList(List<Bill> list) {
		this.list = list;
	}

	public static Integer getSize() {
		return SIZE;
	}



}
