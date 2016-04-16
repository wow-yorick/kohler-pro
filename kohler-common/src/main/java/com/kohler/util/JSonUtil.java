/**
 * Copyright (C) 2000-2006 
Kohler Company. All Rights \
Reserved.
 */
package com.kohler.util;

import java.io.StringWriter;
import java.text.SimpleDateFormat;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * Json工具类
 * 
 * @author shefeng
 * @Date 2014年9月25日
 */
public class JSonUtil {
	
    /**
     * 将对象转换成json字符串
     * @param obj
     * @return
     * @author shefeng Date 2014年9月30日
     * @version
     */
	public static String toJSonString(Object obj){
		ObjectMapper mapper = new ObjectMapper();
		StringWriter out = new StringWriter();
		
		try {
			JsonGenerator gen = new JsonFactory().createJsonGenerator(out);
			gen.configure(JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS, true);
			mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
			mapper.writeValue(gen, obj);
			gen.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return out.toString();
	}
	
	
	
	/**
     * 将json字符串转换成对象
     * @param clazz
     * @param json
     * @return
     * @author shefeng Date 2014年9月30日
     * @version
     */
	public static <T> T toObject(String json,Class<T> clazz){
		ObjectMapper mapper = new ObjectMapper();
		T t = null;
		try {
			mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
			t = mapper.readValue(json, clazz);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return t;
	}
	
	
	
	/**
     * 将日期类型转化为json字符串
     * @param clazz
     * @param json
     * @return
     * @author shefeng Date 2014年9月30日
     * @version
     */
	public static String toJSonStringForSimpleDate(Object obj){
		ObjectMapper mapper = new ObjectMapper();
		StringWriter out = new StringWriter();
		
		try {
			JsonGenerator gen = new JsonFactory().createJsonGenerator(out);
			gen.configure(JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS, true);
			mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
			mapper.writeValue(gen, obj);
			gen.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return out.toString();
	}
	
	
	
	/**
     * 将日期类型的json字符串转化为日期类型
     * @param clazz
     * @param json
     * @return
     * @author shefeng Date 2014年9月30日
     * @version
     */
	public static <T> T toObjectForSimpleDate(String json,Class<T> clazz){
		ObjectMapper mapper = new ObjectMapper();
		T t = null;
		try {
			mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
			t = mapper.readValue(json, clazz);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return t;
	}
}
