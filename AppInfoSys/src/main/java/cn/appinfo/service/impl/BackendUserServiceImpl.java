/**
 * 
 * 开发人员：Young
 * 开发时间：2017-10-19 下午2:24:32
 */
package cn.appinfo.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.appinfo.mapper.BackendUserMapper;
import cn.appinfo.pojo.AppInfo;
import cn.appinfo.pojo.AppVersion;
import cn.appinfo.pojo.BackendUser;
import cn.appinfo.service.BackendUserService;
import cn.appinfo.util.PageUtil;

/**
 * TODO(APP信息管理系统------后台管理----业务层实现类)
 * 开发人员：Young
 * 开发时间：2017-10-19 下午2:24:32
 */
@Service("backendUserService")
public class BackendUserServiceImpl implements BackendUserService {

	@Autowired
	private BackendUserMapper backendUserMapper;
	public void setBackendUserMapper(BackendUserMapper backendUserMapper) {
		this.backendUserMapper = backendUserMapper;
	}


	/**
	 * TODO(APP信息管理系统------获取用户信息（登录）)
	 * 构造函数 
	 *
	 * 开发人员：Young
	 * 开发时间：2017-10-19 下午2:24:32
	 * @param objMap
	 * @return
	 */
	@Override
	public BackendUser findByObject(Map<String, Object> objMap) {
		
		return backendUserMapper.findByObject(objMap);
	}


	/**
	 * TODO(APP信息管理系统------动态查询+分页)
	 * 构造函数 
	 *
	 * 开发人员：Young
	 * 开发时间：2017-10-26 下午4:01:41
	 * @param objMap
	 * @return
	 */
	@Override
	public PageUtil getAppInfoList(Map<String, Object> objMap) {
		
		PageUtil pageUtil = new PageUtil();
		
		pageUtil.setCount(backendUserMapper.getAppInfoCount(objMap));
		pageUtil.setAppList(backendUserMapper.getAppInfoList(objMap));
		
		return pageUtil;
	}


	/**
	 * TODO(APP信息管理系统------根据id获取APP信息列表)
	 * 构造函数 
	 *
	 * 开发人员：Young
	 * 开发时间：2017-10-28 下午3:57:01
	 * @param id
	 * @return
	 */
	@Override
	public AppInfo getAppInfo(Integer id, String APKName) {
		
		return backendUserMapper.getAppInfo(id, APKName);
	}


	/**
	 * TODO(APP信息管理系统------根据id获取版本号信息)
	 * 构造函数 
	 *
	 * 开发人员：Young
	 * 开发时间：2017-10-28 下午3:57:31
	 * @param id
	 * @return
	 */
	@Override
	public AppVersion getAppVersionById(Integer id) {

		return backendUserMapper.getAppVersionById(id);
	}


	/**
	 * TODO(APP信息管理系统------修改App状态)
	 * 构造函数 
	 *
	 * 开发人员：Young
	 * 开发时间：2017-10-28 下午5:10:23
	 * @param statusId
	 * @param id
	 * @return
	 */
	@Override
	public Integer updateAppStatus(Integer statusId, Integer id) {
		
		return backendUserMapper.updateAppStatus(statusId, id);
	}

}
