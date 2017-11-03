/**
 * 
 * 开发人员：Young
 * 开发时间：2017-10-26 下午5:21:09
 */
package cn.appinfo.service;

import java.util.List;

import cn.appinfo.pojo.AppCategory;

/**
 * TODO(APP信息管理系统------App分类----业务层接口)
 * 开发人员：Young
 * 开发时间：2017-10-26 下午5:21:09
 */
public interface AppCategoryService {

	/**
	 * TODO(APP信息管理系统------根据id查询分类级别信息)
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-26 下午5:22:38
	 * @param appCategoryId
	 * @return
	 */
	List<AppCategory> getAppCategoryListById(Integer appCategoryId);
	
}
