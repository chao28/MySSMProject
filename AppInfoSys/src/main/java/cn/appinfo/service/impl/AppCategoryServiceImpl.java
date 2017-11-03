/**
 * 
 * 开发人员：Young
 * 开发时间：2017-10-26 下午5:21:28
 */
package cn.appinfo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.appinfo.mapper.AppCategoryMapper;
import cn.appinfo.pojo.AppCategory;
import cn.appinfo.service.AppCategoryService;

/**
 * TODO(APP信息管理系统------App分类----业务层实现类)
 * 开发人员：Young
 * 开发时间：2017-10-26 下午5:21:28
 */
@Service("appCategoryService")
public class AppCategoryServiceImpl implements AppCategoryService {

	@Autowired
	private AppCategoryMapper appCategoryMapper;
	public void setAppCategoryMapper(AppCategoryMapper appCategoryMapper) {
		this.appCategoryMapper = appCategoryMapper;
	}


	/**
	 * TODO(APP信息管理系统------根据id查询分类级别信息)
	 * 构造函数 
	 *
	 * 开发人员：Young
	 * 开发时间：2017-10-26 下午5:23:03
	 * @param appCategoryId
	 * @return
	 */
	@Override
	public List<AppCategory> getAppCategoryListById(Integer appCategoryId) {
		return appCategoryMapper.getAppCategoryListById(appCategoryId);
	}

}
