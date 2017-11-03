/**
 * 
 * 开发人员：Young
 * 开发时间：2017-10-26 下午3:55:07
 */
package cn.appinfo.util;

import java.util.ArrayList;
import java.util.List;

import cn.appinfo.pojo.AppInfo;

/**
 * TODO(APP信息管理系统------分页工具类)
 * 开发人员：Young
 * 开发时间：2017-10-26 下午3:55:07
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
	private List<AppInfo> appList = new ArrayList<AppInfo>();

	
	
	public List<AppInfo> getAppList() {
		return appList;
	}

	public void setAppList(List<AppInfo> appList) {
		this.appList = appList;
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

	public static Integer getSize() {
		return SIZE;
	}
	
	

}
