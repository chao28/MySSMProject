package cn.appinfo.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.appinfo.pojo.AppInfo;
import cn.appinfo.pojo.AppVersion;
import cn.appinfo.pojo.DevUser;

/**
 * TODO(APP信息管理系统------开发者----DAO层)
 * 
 * 开发人员：Young
 * 开发时间：2017-11-1 下午1:37:32
 */
public interface DevUserMapper {

	/**
	 * TODO(APP信息管理系统------根据编号获取开发人员信息（登录））
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-17 下午5:30:14
	 * @param devCode
	 * @return
	 */
	DevUser findByObject(Map<String, Object> objMap);

	/**
	 * TODO(APP信息管理系统------添加APP信息）
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-30 下午2:11:06
	 * @param appInfo
	 * @return
	 */
	Integer addAppInfo(AppInfo appInfo);

	/**
	 * TODO(APP信息管理系统------根据id获取APP版本信息)
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-30 下午2:54:49
	 * @param appinfoid
	 * @return
	 */
	List<AppVersion> getAppVersion(@Param("appinfoid")Integer appinfoid);

	/**
	 * TODO(APP信息管理系统------新增版本信息)
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-30 下午3:59:23
	 * @param appVersion
	 * @return
	 */
	Integer saveAppVersion(AppVersion appVersion);

	/**
	 * TODO(APP信息管理系统------将APP版本信息添加到APP表中)
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-30 下午5:18:07
	 * @param versionId
	 * @param appId
	 * @return
	 */
	Integer updateAppVersionId(@Param("versionId")Integer versionId, @Param("id")Integer appId);

	/**
	 * TODO(APP信息管理系统------根据id获取APP版本信息)
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-31 上午8:38:37
	 * @param versionid
	 * @return
	 */
	AppVersion getAppVersionById(@Param("versionId")Integer versionid);

	/**
	 * TODO(APP信息管理系统------修改版本信息)
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-31 上午9:41:00
	 * @param appVersion
	 * @return
	 */
	Integer updateAppVersion(AppVersion appVersion);

	/**
	 * TODO(APP信息管理系统------查询app版本记录)
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-31 下午1:36:16
	 * @param appinfoid
	 * @return
	 */
	Integer getVersionCountByAppId(@Param("appinfoid")Integer appinfoid);

	/**
	 * TODO(APP信息管理系统------删除app版本)
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-31 下午2:32:25
	 * @param appinfoid
	 */
	void deleteVersionByAppId(@Param("appinfoid")Integer appinfoid);

	/**
	 * TODO(APP信息管理系统------删除app信息)
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-31 下午2:43:30
	 * @param appinfoid
	 * @return
	 */
	Integer deleteApp(@Param("appinfoid")Integer appinfoid);

	/**
	 * TODO(APP信息管理系统------ 删除logo图片)
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-31 下午4:50:55
	 * @param id
	 * @return
	 */
	Integer deleteAppLogo(@Param("id")Integer id);

	/**
	 * TODO(APP信息管理系统------删除apk文件)
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-31 下午4:51:24
	 * @param id
	 * @return
	 */
	Integer deleteApkLogo(@Param("id")Integer id);

	/**
	 * TODO(APP信息管理系统------修改app信息)
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-31 下午5:04:40
	 * @param appInfo
	 * @return
	 */
	Integer updateAppInfo(AppInfo appInfo);

}
