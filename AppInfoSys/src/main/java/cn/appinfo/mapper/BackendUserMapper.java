/**
 * 
 * 开发人员：Young
 * 开发时间：2017-10-19 下午2:19:31
 */
package cn.appinfo.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.appinfo.pojo.AppInfo;
import cn.appinfo.pojo.AppVersion;
import cn.appinfo.pojo.BackendUser;

/**
 * TODO(APP信息管理平台--后台管理登录--DAO层)
 * 开发人员：Young
 * 开发时间：2017-10-19 下午2:19:31
 */
public interface BackendUserMapper {

	/**
	 * TODO(APP信息管理系统------获取用户信息)
	 * 登录
	 * 开发人员：Young
	 * 开发时间：2017-10-19 下午2:21:52
	 * @param objMapss
	 * @return
	 */
	BackendUser findByObject(Map<String, Object> objMap);
	
	/**
	 * TODO(APP信息管理系统------动态查询+分页)
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-26 下午3:54:18
	 * @param objMap
	 * @return
	 */
	List<AppInfo> getAppInfoList(Map<String, Object> objMap);
	
	/**
	 * TODO(APP信息管理系统------获取总记录数)
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-26 下午3:54:33
	 * @param objMap
	 * @return
	 */
	Integer getAppInfoCount(Map<String, Object> objMap);
	
	/**
	 * TODO(APP信息管理系统------获取APP信息列表)
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-28 下午3:54:31
	 * @param id
	 * @return
	 */
	AppInfo getAppInfo(@Param("id")Integer id, @Param("APKName")String APKName);
	
	/**
	 * TODO(APP信息管理系统------根据id获取版本号信息)
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-28 下午3:55:31
	 * @param id
	 * @return
	 */
	AppVersion getAppVersionById(@Param("id")Integer id);
	
	/**
	 * TODO(APP信息管理系统------修改App状态)
	 * 审核
	 * 开发人员：Young
	 * 开发时间：2017-10-28 下午5:09:07
	 * @param statusId
	 * @param id
	 * @return
	 */
	Integer updateAppStatus(@Param("statusId")Integer statusId, @Param("id")Integer id);
	
}
