package org.lk.myutils.date;


import java.text.SimpleDateFormat;

import org.lk.myutils.string.RegularUtils;

/**
 * 日期格式化工具类
 * @author Administrator
 *
 */
public class DateFormat {
	
	/**
	 * 日期类型枚举类
	 * @author Administrator
	 *
	 */
	public enum DateType{
		/**1403604292284|1403604292*/
		TYPE_NORMAL("","\\d{13}|\\d{10}"),
		/**2015-05-07:14:22:55*/
		TYPE_TYPE_SPECIAL1("yyyy-MM-dd:HH:mm:ss","\\d{4}-\\d{2}-\\d{2}:\\d{2}:\\d{2}:\\d{2}"),
		/**2015-05-07 14:22:55*/
		TYPE_TYPE_SPECIAL2("yyyy-MM-dd HH:mm:ss","\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}"),
		/**20150507*/
		TYPE_TYPE_SPECIAL3("yyyyMMdd","\\d{8}"),
		/**20150715174620*/
		TYPE_TYPE_SPECIAL4("yyyyMMddHHmmss","\\d{14}"),
		/**2015-07-15 17:46:20.462*/
		TYPE_TYPE_SPECIAL5("yyyy-MM-dd HH:mm:ss.SSS","\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}\\.\\d{3}"),
		/**2015:05:07 14:22:55*/
		TYPE_TYPE_SPECIAL6("yyyy:MM:dd HH:mm:ss","\\d{4}:\\d{2}:\\d{2} \\d{2}:\\d{2}:\\d{2}"),
		/**2015-05-07 14:22*/
		TYPE_TYPE_SPECIAL7("yyyy-MM-dd HH:mm","\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}"),
		/**2015-05-07 14*/
		TYPE_TYPE_SPECIAL8("yyyy-MM-dd HH","\\d{4}-\\d{2}-\\d{2} \\d{2}");
		// 成员变量
		/**日期格式化字符串*/
		private String format;
		/**日期字符串的正则表达式*/
		private String regular;
		
		/**
		 * 构造方法， 构造一个日期类型的枚举
		 * @param format 日期格式化字符串
		 * @param regular 日期字符串的正则表达式
		 */
		private DateType(String format, String regular) {
			this.format = format;
			this.regular = regular;
		}
		/**
		 * 获取日期格式字符串
		 * @return format
		 */
		public String getFormat() {
			return format;
		}
		
		/**
		 * 日期字符串的正则表达式
		 * @return regular
		 */
		public String getRegular() {
			return regular;
		}
	};
	
	/**
	 * 将字日期符串转换为毫秒数
	 * @param str 日期字符串
	 * @return 日期的毫秒数
	 */
	public static long formatDateToLong(String str) {
		SimpleDateFormat sdf = null;
		if(str==null||str.equals("")) return 0;
		for (DateType type : DateType.values()) {
			if(RegularUtils.match(str, type.regular)){
				try {
					if(type.format.equals("")) {
						if(str.length()==10)
							str = str+"000";
						return Long.parseLong(str);
					}	
					sdf = new SimpleDateFormat(type.format);
					return sdf.parse(str).getTime();
				} catch (Exception e) {
					e.printStackTrace();
					return 0;
				}
			}
		}
		return 0;
	}
	
	/**
	 * 将毫秒数转换为yyyy-MM-dd HH:mm:ss格式的字符串
	 * @param time 日期毫秒数
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String formatDateToString(long date) {
		if(date == 0)
			return "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}
	
	/**
	 * 将任意格式日期字符串转换为yyyy-MM-dd HH:mm:ss格式的字符串
	 * @param str 日期字符串
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String formatDateToString(String str) {
		return formatDateToString(formatDateToLong(str));
	}
	
	/**
	 * 将毫秒数转换为指定格式的字符串
	 * @param time 日期毫秒数
	 * @param format 指定时间格式
	 * @return 指定时间格式
	 */
	public static String formatDateToString(long date, String format) {
		if(date == 0)
			return "";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
	
	/**
	 * 将任意格式日期字符串转换为指定格式的字符串
	 * @param str 日期字符串
	 * @param format 指定时间格式
	 * @return 指定时间格式
	 */
	public static String formatDateToString(String str, String format) {
		return formatDateToString(formatDateToLong(str), format);
	}
	
}
