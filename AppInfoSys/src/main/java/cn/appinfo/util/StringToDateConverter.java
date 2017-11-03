/**
 * 
 * 开发人员：Young
 * 开发时间：2017-10-26 上午9:10:59
 */
package cn.appinfo.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

/**
 * TODO(APP信息管理系统------自定义日期转换器类)
 * 开发人员：Young
 * 开发时间：2017-10-26 上午9:10:59
 */
public class StringToDateConverter implements Converter<String, Date>{
	
	private String datePattern;
	public StringToDateConverter(String datePattern){
		this.datePattern = datePattern;
	}	
	@Override
	public Date convert(String s) {	
		
		Date date = null;	
		try {
			date = new SimpleDateFormat(datePattern).parse(s);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return date;
	}
	
}
