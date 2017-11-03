package cn.appinfo.service;

import java.util.List;
import java.util.Map;

import cn.appinfo.pojo.AppInfo;
import cn.appinfo.pojo.AppVersion;
import cn.appinfo.pojo.DevUser;

/**
 * TODO(APP信息管理系统------开发者用户----业务层接口)
 * 
 * 开发人员：Young
 * 开发时间：2017-10-17 下午5:07:10
 */
public interface DevUserService {

	/**
	 * TODO(APP信息管理系统------根据编号获取开发人员信息（登录）)
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-17 下午5:28:49
	 * @param devCode
	 * @return
	 */
	DevUser findByObject(Map<String, Object> objMap);

	/**
	 * TODO(APP信息管理系统------添加APP信息)
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-30 下午2:08:22
	 * @param appInfo
	 * @return
	 */
	Integer addAppInfo(AppInfo appInfo);

	/**
	 * TODO(APP信息管理系统------根据id获取APP版本信息)
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-30 下午2:48:29
	 * @param appinfoid
	 * @return
	 */
	List<AppVersion> getAppVersion(Integer appinfoid);

	/**
	 * TODO(APP信息管理系统------新增版本信息)
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-30 下午3:58:16
	 * @param appVersion
	 * @return
	 */
	boolean saveAppVersion(AppVersion appVersion);

	/**
	 * TODO(APP信息管理系统------根据id获取APP版本信息)
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-31 上午8:37:15
	 * @param versionid
	 * @return
	 */
	AppVersion getAppVersionById(Integer versionid);

	/**
	 * TODO(APP信息管理系统------修改版本信息)
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-31 上午9:39:18
	 * @param appVersion
	 * @return
	 */
	Integer updateAppVersion(AppVersion appVersion);

	/**
	 * TODO(APP信息管理系统------删除app信息)
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-31 下午1:30:09
	 * @param parseInt
	 * @return
	 */
	boolean appsysdeleteAppById(Integer appinfoid);

	/**
	 * TODO(APP信息管理系统------删除logo图片)
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-31 下午4:43:53
	 * @param id
	 * @return
	 */
	boolean deleteAppLogo(Integer id);

	/**
	 * TODO(APP信息管理系统------删除apk文件)
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-31 下午4:44:33
	 * @param id
	 * @return
	 */
	boolean deleteApkFile(Integer id);

	/**
	 * TODO(APP信息管理系统------修改app信息)
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-31 下午5:02:23
	 * @param appInfo
	 * @return
	 */
	boolean modify(AppInfo appInfo);

	/**
	 * TODO(APP信息管理系统------上下架操作)
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-11-1 上午8:25:45
	 * @param appInfo
	 * @return
	 */
	boolean appsysUpdateSaleStatusByAppId(AppInfo appInfo);

	
}
