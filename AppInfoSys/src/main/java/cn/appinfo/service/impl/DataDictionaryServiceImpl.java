/**
 * 
 * 开发人员：Young
 * 开发时间：2017-10-26 下午4:59:56
 */
package cn.appinfo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.appinfo.mapper.DataDictionaryMapper;
import cn.appinfo.pojo.DataDictionary;
import cn.appinfo.service.DataDictionaryService;

/**
 * TODO(APP信息管理系统------数据字典----业务层实现类)
 * 开发人员：Young
 * 开发时间：2017-10-26 下午4:59:56
 */
@Service("dataDictionaryService")
public class DataDictionaryServiceImpl implements DataDictionaryService {

	@Autowired
	private DataDictionaryMapper dataDictionaryMapper;
	public void setDataDictionaryMapper(DataDictionaryMapper dataDictionaryMapper) {
		this.dataDictionaryMapper = dataDictionaryMapper;
	}


	/**
	 * TODO(APP信息管理系统------获取所属平台列表)
	 * 构造函数 
	 *
	 * 开发人员：Young
	 * 开发时间：2017-10-26 下午5:01:57
	 * @param typeCode
	 * @return
	 */
	@Override
	public List<DataDictionary> getDataDictionaryList(String typeCode) {
		
		return dataDictionaryMapper.getDataDictionaryList(typeCode);
	}

	
	
}
