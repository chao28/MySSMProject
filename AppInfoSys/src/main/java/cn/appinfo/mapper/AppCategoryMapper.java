/**
 * 
 * 开发人员：Young
 * 开发时间：2017-10-26 下午5:24:02
 */
package cn.appinfo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.appinfo.pojo.AppCategory;

/**
 * TODO(APP信息管理系统------APP分类----DAO层)
 * 开发人员：Young
 * 开发时间：2017-10-26 下午5:24:02
 */
public interface AppCategoryMapper {

	/**
	 * TODO(APP信息管理系统------根据id查询分类级别信息)
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-26 下午5:25:25
	 * @param appCategoryId
	 * @return
	 */
	List<AppCategory> getAppCategoryListById(@Param("parentId")Integer appCategoryId);

	
	
}
