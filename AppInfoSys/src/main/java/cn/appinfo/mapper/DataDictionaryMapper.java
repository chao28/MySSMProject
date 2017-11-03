/**
 * 
 * 开发人员：Young
 * 开发时间：2017-10-26 下午5:02:42
 */
package cn.appinfo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.appinfo.pojo.DataDictionary;

/**
 * TODO(APP信息管理系统------数据字典----DAO层)
 * 开发人员：Young
 * 开发时间：2017-10-26 下午5:02:42
 */
public interface DataDictionaryMapper {

	/**
	 * TODO(APP信息管理系统------获取所属平台列表)
	 * 
	 * 开发人员：Young
	 * 开发时间：2017-10-26 下午5:04:04
	 * @param typeCode
	 * @return
	 */
	List<DataDictionary> getDataDictionaryList(@Param("typeCode")String typeCode);

	
	
}
