/**
 * 
 * 开发人员：Young
 * 开发时间：2017-10-26 下午4:59:20
 */
package cn.appinfo.service;

import java.util.List;

import cn.appinfo.pojo.DataDictionary;

/**
 * TODO(APP信息管理系统------数据字典----业务层接口)
 * 开发人员：Young
 * 开发时间：2017-10-26 下午4:59:20
 */
public interface DataDictionaryService {

	/**
	 * TODO(APP信息管理系统------获取所属平台列表)
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-26 下午5:01:35
	 * @param typeCode
	 * @return
	 */
	List<DataDictionary> getDataDictionaryList(String typeCode);

	
	
}
