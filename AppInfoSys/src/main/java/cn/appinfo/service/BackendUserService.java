/**
 * 
 * 开发人员：Young
 * 开发时间：2017-10-19 下午2:22:46
 */
package cn.appinfo.service;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.appinfo.pojo.AppInfo;
import cn.appinfo.pojo.AppVersion;
import cn.appinfo.pojo.BackendUser;
import cn.appinfo.util.PageUtil;

/**
 * TODO(APP信息管理系统------后台管理----业务层接口)
 * 开发人员：Young
 * 开发时间：2017-10-19 下午2:22:46
 */
public interface BackendUserService {

	/**
	 * TODO(APP信息管理系统------获取用户信息)
	 * 登录
	 * 开发人员：Young
	 * 开发时间：2017-10-19 下午2:23:49
	 * @param objMap
	 * @return
	 */
	BackendUser findByObject(Map<String, Object> objMap);
	
	/**
	 * TODO(APP信息管理系统------动态查询+分页)
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-26 下午4:01:13
	 * @param objMap
	 * @return
	 */
	PageUtil getAppInfoList(Map<String, Object> objMap);
	
	/**
	 * TODO(APP信息管理系统------根据id获取APP信息列表)
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-28 下午3:54:31
	 * @param id
	 * @return
	 */
	AppInfo getAppInfo(Integer id, String APKName);
	
	/**
	 * TODO(APP信息管理系统------根据id获取版本号信息)
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-28 下午3:55:31
	 * @param id
	 * @return
	 */
	AppVersion getAppVersionById(Integer id);
	
	/**
	 * TODO(APP信息管理系统------修改App状态)
	 * 审核
	 * 开发人员：Young
	 * 开发时间：2017-10-28 下午5:09:07
	 * @param statusId
	 * @param id
	 * @return
	 */
	Integer updateAppStatus(Integer statusId, Integer id);
	
}
