package com.kohler.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.time.DateFormatUtils;
/**
 * 日期类型与String类型的转换
 * @author zhangtingting
 * @date 20141009
 */
public class DateUtil {

	private static String str1 = "yyyy-MM-dd HH:mm:ss";
	private static String str2 = "yyyyMMddHHmmss";
	private static String str3 = "yyyy-MM-dd";
	private static String str6 = "yyyy年MM月dd日";
	private static String str7 = "yyyy-MM-dd HH:mm";
	/**
	 * yyyy-MM-dd HH:mm:ss.SSS
	 */
	public static String YYYY_MM_DD_HH_MM_SS_SSS = "yyyy-MM-dd HH:mm:ss.SSS";
	/**
	 * yyyy-MM-dd HH:mm:ss
	 */
	public static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * 将指定日期转换成字符串格式
	 * @param Date
	 * @param pattern
	 * @return
	 */
	public static String date2StrCommon(Date date, String pattern){
		return DateFormatUtils.format(date, pattern);
	}
	
	
	
	/**
	 * 将字符串转换成指定日期格式
	 * @param Date
	 * @param pattern
	 * @return
	 */
	public static Date str2DateCommon(String str, String pattern){
		Date date = null;
		try {
			date = new Timestamp(new SimpleDateFormat(pattern).parse(str).getTime());
		} catch (ParseException e) {
			//e.printStackTrace();
			return null;
		}
		return date;
	}
	
	
	
	/**
	 * 将字符串转换成 yyyy-MM-dd HH:mm:ss 日期
	 * @param date1
	 * @return
	 */
	public static Date strFormatDate1(String str){
		Date date = null;
		try {
			date = new Timestamp(new SimpleDateFormat(str1).parse(str).getTime());
		} catch (ParseException e) {
			//e.printStackTrace();
			return null;
		}
		return date;
	}
	
	
	
	/**
	 * 将字符串转换成 yyyy-MM-dd 日期
	 * @param date1
	 * @return
	 */
	public static Date strFormatDate2(String str){
		Date date = null;
		try {
			date = new Timestamp(new SimpleDateFormat(str3).parse(str).getTime());
		} catch (ParseException e) {
			//e.printStackTrace();
			return null;
		}
		return date;
	}
	
	
	
	/**
	 * 将当前传进来的日期转换成  yyyy-MM-dd  类型字符串
	 * @param date1
	 * @return
	 */
	public static String dateFormatStr3(Date date){
		String str = null;
		
		str = new SimpleDateFormat(str3).format(date);
		
		return str;
	}
	
	
	
	/**
	 * 将当前传进来的时间和日期转换成  yyyy-MM-dd HH:mm:ss  类型字符串
	 * @param date1
	 * @return
	 */
	public static String dateFormatStr4(Date date){
		
		return new SimpleDateFormat(str1).format(date);
	}
	
	
	
	/**
	 * 将当前传进来的时间和日期转换成  yyyyMMddHHmmss  类型字符串
	 * @param date1
	 * @return
	 */
	public static String dateFormatStr1(Date date1){
		String str = null;
		
		str = new SimpleDateFormat(str2).format(date1);
		
		return str;
	}
	
	
	
	/**
	 * 将当前传进来的日期转换成  yyyy年MM月dd日  类型字符串
	 * @param date1
	 * @return
	 */
	public static String dateFormatStr6(Date date){
		String str = null;
		
		str = new SimpleDateFormat(str6).format(date);
		
		return str;
	}
	
	
	
	/**
	 * 将当前传进来的日期转换成  yyyy-MM-dd HH:mm  类型字符串
	 * @param date1
	 * @return
	 */
	public static String dateFormatStr7(Date date){
		String str = null;
		
		str = new SimpleDateFormat(str7).format(date);
		
		return str;
	}
	
	
	
	/**
	 * 将字符串转换成date
	 * @param str
	 * @return
	 */
	public static Date strFormatDate3(String str){
		Date date = null;
		try {
			date = new SimpleDateFormat(str1).parse(str);
		} catch (ParseException e) {
			//e.printStackTrace();
			return null;
		}
		return date;
	}
	
	
	
	public static Date montStartDate(String str){
		Date date = null;
		try {
			str = str.substring(0,7) + "-01 00:00:00";
			date = new Timestamp(new SimpleDateFormat(str1).parse(str).getTime());
		} catch (ParseException e) {
			//e.printStackTrace();
			return null;
		}
		return date;
	}
	
	
	
	/**
	 * <p>
	 * Date to yyyy.MM
	 * </p>
	 */
	public static String dateFormatToStr6(Date date) {
		return (new SimpleDateFormat("MM.dd")).format(date);
	}
	
	
	
	/**
	 * 长整形时间转换成格式化日期
	 * @param longTime
	 * @param format
	 * @return
	 */
	public static String longTime2DateStr(String longTime, String format) {
		if(format == null) {
			format = YYYY_MM_DD_HH_MM_SS_SSS;
		}
		long lt;
		try{
			lt = Long.parseLong(longTime);
		} catch(Exception e) {
			return longTime;
		}
		Date date = new Date(lt);
		return date2StrCommon(date, format);
	}
	
	
	
	 /**
	  * 将yyyy-MM转换为yyyy年MM月
	  */
	 public static String MonthConvert(String month){
		 month=month.replace("-", "年");
		 month=month+"月";
		 return month;
	 }
}
