/**
 * 
 * 开发人员：Young
 * 开发时间：2017-10-17 下午5:09:47
 */
package cn.appinfo.service.impl;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.appinfo.mapper.BackendUserMapper;
import cn.appinfo.mapper.DevUserMapper;
import cn.appinfo.pojo.AppInfo;
import cn.appinfo.pojo.AppVersion;
import cn.appinfo.pojo.DevUser;
import cn.appinfo.service.DevUserService;

/**
 * TODO(APP信息管理系统------开发者平台----业务层实现类)
 * 开发人员：Young
 * 开发时间：2017-10-17 下午5:09:47
 */
@Service("devUserService")
public class DevUserServiceImpl implements DevUserService {

	@Autowired
	private DevUserMapper devUserMapper;
	public void setDevUserMapper(DevUserMapper devUserMapper) {
		this.devUserMapper = devUserMapper;
	}

	@Autowired
	private BackendUserMapper backendUserMapper;
	public void setBackendUserMapper(BackendUserMapper backendUserMapper) {
		this.backendUserMapper = backendUserMapper;
	}

	/**
	 * TODO(APP信息管理系统------根据编号获取开发人员信息（登录）)
	 * 构造函数 
	 *
	 * 开发人员：Young
	 * 开发时间：2017-10-17 下午5:29:32
	 * @param devCode
	 * @return
	 */
	@Override
	public DevUser findByObject(Map<String, Object> objMap) {

		return devUserMapper.findByObject(objMap);
	}

	/**
	 * TOOD(APP信息管理系统------添加APP信息)
	 * 构造函数 
	 *
	 * 开发人员：Young
	 * 开发时间：2017-10-30 下午2:08:49
	 * @param appInfo
	 * @return
	 */
	@Override
	public Integer addAppInfo(AppInfo appInfo) {

		return devUserMapper.addAppInfo(appInfo);
	}

	/**
	 * TODO(APP信息管理系统------根据id获取APP版本信息)
	 * 构造函数 
	 *
	 * 开发人员：Young
	 * 开发时间：2017-10-30 下午2:49:10
	 * @param appinfoid
	 * @return
	 */
	@Override
	public List<AppVersion> getAppVersion(Integer appinfoid) {

		return devUserMapper.getAppVersion(appinfoid);
	}

	/**
	 * TODO(APP信息管理系统------新增版本信息)
	 * 构造函数 
	 *
	 * 开发人员：Young
	 * 开发时间：2017-10-30 下午3:58:46
	 * @param appVersion
	 * @return
	 */
	@Override
	public boolean saveAppVersion(AppVersion appVersion) {

		boolean falg = false;

		Integer resultUpdateVersion = devUserMapper.saveAppVersion(appVersion);
		Integer versionId = null;
		Integer appId = null;

		if (resultUpdateVersion > 0){

			versionId = appVersion.getId();
			appId = appVersion.getAppId();

			Integer resultUpdateAppList = devUserMapper.updateAppVersionId(versionId, appId);

			if (resultUpdateAppList > 0){
				falg = true;
			}

		}

		return falg;
	}

	/**
	 * TODO(APP信息管理系统------根据id获取APP版本信息)
	 * 构造函数 
	 *
	 * 开发人员：Young
	 * 开发时间：2017-10-31 上午8:37:56
	 * @param versionid
	 * @return
	 */
	@Override
	public AppVersion getAppVersionById(Integer versionid) {

		return devUserMapper.getAppVersionById(versionid);
	}

	/**
	 * TODO(APP信息管理系统------修改版本信息)
	 * 构造函数 
	 *
	 * 开发人员：Young
	 * 开发时间：2017-10-31 上午9:40:02
	 * @param appVersion
	 * @return
	 */
	@Override
	public Integer updateAppVersion(AppVersion appVersion) {

		return devUserMapper.updateAppVersion(appVersion);
	}

	/**
	 * TODO(APP信息管理系统------删除app信息)
	 * 构造函数 
	 *
	 * 开发人员：Young
	 * 开发时间：2017-10-31 下午1:30:47
	 * @param parseInt
	 * @return
	 */
	@Override
	public boolean appsysdeleteAppById(Integer appinfoid) {

		boolean flag = false;

		Integer versionCount = devUserMapper.getVersionCountByAppId(appinfoid);

		List<AppVersion> appVersionList = null;

		if (versionCount > 0){

			appVersionList = devUserMapper.getAppVersion(appinfoid);
			//删除apk文件
			for(AppVersion appVersion:appVersionList){
				if(appVersion.getApkLocPath() != null && !appVersion.getApkLocPath().equals("")){
					File file = new File(appVersion.getApkLocPath());
					if(file.exists()){
						if(!file.delete())
							try {
								throw new Exception();
							} catch (Exception e) {
								e.printStackTrace();
							}
					}
				}
			}

			devUserMapper.deleteVersionByAppId(appinfoid); //删除app版本		
		}

		AppInfo appInfo = backendUserMapper.getAppInfo(appinfoid, null);

		//System.out.println(">>>>>>>>>>>>>>" + appInfo.getLogoLocPath());

		//删除logo图片
		if(appInfo.getLogoLocPath() != null && !appInfo.getLogoLocPath().equals("")){
			File file = new File(appInfo.getLogoLocPath());
			if(file.exists()){
				if(!file.delete())
					try {
						throw new Exception();
					} catch (Exception e) {
						e.printStackTrace();
					}
			}
		}
		
		//删除app信息
		if (devUserMapper.deleteApp(appinfoid) > 0){
			flag = true;
		}


		return flag;
	}

	/**
	 * TODO(APP信息管理系统------删除logo图片)
	 * 构造函数 
	 *
	 * 开发人员：Young
	 * 开发时间：2017-10-31 下午4:48:58
	 * @param id
	 * @return
	 */
	@Override
	public boolean deleteAppLogo(Integer id) {

		boolean flag = false;

		Integer result = devUserMapper.deleteAppLogo(id);

		if (result > 0){
			flag = true;
		}

		return flag;
	}

	/**
	 * TODO(APP信息管理系统------删除apk文件)
	 * 构造函数 
	 *
	 * 开发人员：Young
	 * 开发时间：2017-10-31 下午4:48:47
	 * @param id
	 * @return
	 */
	@Override
	public boolean deleteApkFile(Integer id) {
		boolean flag = false;

		Integer result = devUserMapper.deleteApkLogo(id);

		if (result > 0){
			flag = true;
		}

		return flag;
	}

	/**
	 * TODO(APP信息管理系统------修改app信息)
	 * 构造函数 
	 *
	 * 开发人员：Young
	 * 开发时间：2017-10-31 下午5:03:16
	 * @param appInfo
	 * @return
	 */
	@Override
	public boolean modify(AppInfo appInfo) {
		
		boolean flag = false;
		
		if (devUserMapper.updateAppInfo(appInfo) > 0){
			flag = true;
		}
		
		return flag;
	}

	/**
	 * TODO(APP信息管理系统------上下架操作)
	 * 构造函数 
	 *
	 * 开发人员：Young
	 * 开发时间：2017-11-1 上午8:26:31
	 * @param appInfo
	 * @return
	 */
	@Override
	public boolean appsysUpdateSaleStatusByAppId(AppInfo appInfo) {
		
		Integer operator = appInfo.getModifyBy();
		
		if (operator < 0 || appInfo.getId() < 0){
			try {
				throw new Exception();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		AppInfo appInfos = backendUserMapper.getAppInfo(appInfo.getId(), null);
		
		if(null == appInfos){
			return false;
		}else{
			switch (appInfos.getStatus()) {
				case 2: //当状态为审核通过时，可以进行上架操作
					onSale(appInfo,operator,4,2);
					break;
				case 5://当状态为下架时，可以进行上架操作
					onSale(appInfo,operator,4,2);
					break;
				case 4://当状态为上架时，可以进行下架操作
					offSale(appInfo,operator,5);
					break;

			default:
				return false;
			}
		}
		
		return true;
	}

	private boolean offSale(AppInfo appInfo, Integer operator,Integer appInfStatus) {
		
		AppInfo _appInfo = new AppInfo();
		_appInfo.setId(appInfo.getId());
		_appInfo.setStatus(appInfStatus);
		_appInfo.setModifyBy(operator);
		_appInfo.setOffSaleDate(new Date(System.currentTimeMillis()));
		devUserMapper.updateAppInfo(_appInfo);
		return true;
		
	}

	private void onSale(AppInfo appInfo, Integer operator, Integer appInfStatus,Integer versionStatus) {
		
		offSale(appInfo,operator,appInfStatus);
		setSaleSwitchToAppVersion(appInfo,operator,versionStatus);
		
	}

	private boolean setSaleSwitchToAppVersion(AppInfo appInfo, Integer operator,Integer saleStatus) {
		
		AppVersion appVersion = new AppVersion();
		appVersion.setId(appInfo.getVersionId());
		appVersion.setPublishStatus(saleStatus);
		appVersion.setModifyBy(operator);
		appVersion.setModifyDate(new Date(System.currentTimeMillis()));
		devUserMapper.updateAppVersion(appVersion);
		return true;
	}

}
