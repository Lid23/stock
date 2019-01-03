package com.noodles.collectdata.utils;

import org.apache.commons.lang.time.DateFormatUtils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 文件名：DateUtils.java
 * 描述：时间工具类
 * 作者：sz05392_Leo
 * 日期：2016年4月20日上午10:52:11
 */
public class DateUtils extends org.apache.commons.lang.time.DateUtils {

	private static String[] parsePatterns = { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy/MM/dd",
			"yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyyMMddHHmmss", "yyyyMMdd" };

	/**yyyy-MM-dd HH:mm:ss*/
	public final static String FULL_DATE_STYLE = "yyyy-MM-dd HH:mm:ss";
	/**yyyy-MM-dd HH:mm*/
	public final static String MI_DATE_STYLE = "yyyy-MM-dd HH:mm";
	/**HH:mm:ss*/
	public final static String FULL_TIME_STYLE = "HH:mm:ss";
	/**HH:mm*/
	public final static String MI_TIME_STYLE = "HH:mm";
	/**yyyy-MM-dd*/
	public final static String DATE_STYLE = "yyyy-MM-dd";
	/**yyyyMMdd*/
	public final static String STANDAR_DATE_STYLE = "yyyyMMdd";
	/**MM/dd/yyyy hh:mm:ss*/
	public final static String NEW_TIME_STYLE = "MM/dd/yyyy hh:mm:ss";
	/**MM/dd/yyyy*/
	public final static String NEW_DATE_STYLE = "MM/dd/yyyy";

	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd）
	 */
	public static String getDate() {
		return getDate("yyyy-MM-dd");
	}

	/**
	 * 得到当前日期字符串 格式（yyyyMMdd）
	 */
	public static String getDate1() {
		return getDate("yyyyMMdd");
	}

	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String getDate(String pattern) {
		return DateFormatUtils.format(new Date(), pattern);
	}

	/**
	 * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String formatDate(Date date, Object... pattern) {
		String formatDate = null;
		if (pattern != null && pattern.length > 0) {
			formatDate = DateFormatUtils.format(date, pattern[0].toString());
		} else {
			formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
		}
		return formatDate;
	}

	/**
	 * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String formatDateTime(Date date) {
		return formatDate(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前时间字符串 格式（HH:mm:ss）
	 */
	public static String getTime() {
		return formatDate(new Date(), "HH:mm:ss");
	}

	/**
	 * 得到当前时间字符串 格式（HHmmss）
	 */
	public static String getTime1() {
		return formatDate(new Date(), "HHmmss");
	}

	/**
	 * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String getDateTime() {
		return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前年份字符串 格式（yyyy）
	 */
	public static String getYear() {
		return formatDate(new Date(), "yyyy");
	}

	/**
	 * 得到当前月份字符串 格式（MM）
	 */
	public static String getMonth() {
		return formatDate(new Date(), "MM");
	}

	/**
	 * 得到当天字符串 格式（dd）
	 */
	public static String getDay() {
		return formatDate(new Date(), "dd");
	}

	/**
	 * 得到当前星期字符串 格式（E）星期几
	 */
	public static String getWeek() {
		return formatDate(new Date(), "E");
	}

	/**
	 * 日期型字符串转化为日期 格式
	 * { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", 
	 *   "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm" }
	 */
	public static Date parseDate(Object str) {
		if (str == null) {
			return null;
		}
		try {
			return parseDate(str.toString(), parsePatterns);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 获取过去的天数
	 * @param date
	 * @return
	 */
	public static long pastDays(Date date) {
		long t = new Date().getTime() - date.getTime();
		return t / (24 * 60 * 60 * 1000);
	}

	public static Date getDateStart(Date date) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			date = sdf.parse(formatDate(date, "yyyy-MM-dd") + " 00:00:00");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static Date getDateEnd(Date date) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			date = sdf.parse(formatDate(date, "yyyy-MM-dd") + " 23:59:59");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 判断字符串是否是日期
	 * @param timeString
	 * @return
	 */
	public static boolean isDate(String timeString) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		format.setLenient(false);
		try {
			format.parse(timeString);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * 格式化时间
	 * @param timestamp
	 * @return
	 */
	public static String dateFormat(Date timestamp) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(timestamp);
	}

	/**
	 * 获取系统时间Timestamp
	 * @return
	 */
	public static Timestamp getSysTimestamp() {
		return new Timestamp(new Date().getTime());
	}

	/**
	 * 获取系统时间Date
	 * @return
	 */
	public static Date getSysDate() {
		return new Date();
	}

	/**
	 * 生成时间随机数 
	 * @return
	 */
	public static String getDateRandom() {
		String s = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		return s;
	}

	/**
	 * 判断两个时间之间相差多少年，不足一年按少一年算
	 * 可以作为周岁的计算
	 * @param now
	 * @param old
	 * @return
	 * 作者：王承
	 * 日期：2016年5月23日下午4:14:38
	 */
	public static int calculateYearDiff(Date now, Date old) {
		long diff = now.getTime() - old.getTime();
		return (int) (diff / (1000 * 60 * 60 * 24)) / 365;
	}

	/**
	 * 将style格式的日期字符串转化为日期类型
	 * @param s
	 * @param style
	 * @return
	 * 作者：王承
	 * 日期：2016年5月23日下午4:17:13
	 */
	public static Date strToDate(String s, String style) {
		if (null == s || s.length() == 0)
			return null;
		SimpleDateFormat df = new SimpleDateFormat(style);
		try {
			Date date = df.parse(s);
			return date;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 将date转化为style格式的日期字符串
	 * @param date
	 * @param style
	 * @return
	 * 作者：王承
	 * 日期：2016年5月23日下午4:23:25
	 */
	public static String dateToStr(Date date, String style) {
		if (null == date)
			return "";
		SimpleDateFormat df = new SimpleDateFormat(style);
		try {
			String str = df.format(date);
			return str;
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * 根据出生日期获取年龄
	 * @param birthdate 出生日期 
	 * @param rule 指定的日期
	 * @return
	 * 作者：王承
	 * 日期：2016年5月25日下午2:28:26
	 */
	public static int getAge(Date birthdate, Date rule) {
		if (null == birthdate) {
			return 0;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(rule);
		int yearNow = cal.get(Calendar.YEAR);
		int monthNow = cal.get(Calendar.MONTH);
		int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);

		cal.setTime(birthdate);
		int yearBirth = cal.get(Calendar.YEAR);
		int monthBirth = cal.get(Calendar.MONTH);
		int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

		int age = yearNow - yearBirth;

		if (monthNow <= monthBirth) {
			if (monthNow == monthBirth) {

				if (dayOfMonthNow < dayOfMonthBirth) {
					age--;
				} else {
					// donothing
				}
			} else {

				age--;
			}
		} else {
			// donothing
		}

		return age;
	}

	/**
	 * 计算两个时间的相差月数
	 * @param date1
	 * @param date2
	 * @return
	 * @throws ParseException
	 * 作者：sz05392_Leo
	 * 日期：2016年6月1日上午9:14:34
	 */
	public static int getMonthSpace(Date date1, Date date2) {
		Calendar cal1 = new GregorianCalendar();
		cal1.setTime(date1);
		Calendar cal2 = new GregorianCalendar();
		cal2.setTime(date2);
		int c = (cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR)) * 12 + cal1.get(Calendar.MONTH)
				- cal2.get(Calendar.MONTH);
		return c;
	}

	/**
	 * 计算两个时间相差天数
	 * @param early
	 * @param late
	 * @return
	 * 作者：sz05392_Leo
	 * 日期：2016年6月1日上午9:16:02
	 */
	public static final int daysBetween(Date early, Date late) {

		Calendar calst = Calendar.getInstance();
		Calendar caled = Calendar.getInstance();
		calst.setTime(early);
		caled.setTime(late);
		// 设置时间为0时
		calst.set(Calendar.HOUR_OF_DAY, 0);
		calst.set(Calendar.MINUTE, 0);
		calst.set(Calendar.SECOND, 0);
		caled.set(Calendar.HOUR_OF_DAY, 0);
		caled.set(Calendar.MINUTE, 0);
		caled.set(Calendar.SECOND, 0);
		// 得到两个日期相差的天数
		int days = ((int) (caled.getTime().getTime() / 1000) - (int) (calst.getTime().getTime() / 1000)) / 3600 / 24;

		return days;
	}

	/**
	 * 每月最大天数是28号
	 * @param date
	 * @return
	 * 作者：SZ04951_jason
	 * 日期：2016年6月6日下午7:36:15
	 */
	public static Date getDueDate(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		int month = c.get(Calendar.MONTH) + 1;
		if (day >= 28) {
			day = 28;
		}
		c.set(Calendar.DATE, day);
		c.set(Calendar.MONTH, month);
		Date d = c.getTime();

		return d;
	}

	/**
	 * 返回时间的天
	 * @param date 
	 * @return
	 * 作者：SZ04951_jason
	 * 日期：2016年6月7日下午6:10:03
	 */
	public static int getDay(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		return day;
	}

	/**
	 * 获取前n天日期
	 * @param count
	 * @return
	 * 作者：欧积雨
	 * 日期：2016年6月20日下午5:51:16
	 */
	public static String getPreDate(long n) {
		long time = new Date().getTime();
		long addDate = n * 24 * 60 * 60 * 1000;
		time -= addDate;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(new Date(time));

	}

	/**
	 * 获取时间类型只有年月日
	 * @param date
	 * @return
	 * 作者：brian.li
	 * 日期：2016年7月7日下午1:55:37
	 */
	public static Date truncate(Date date) {
		return org.apache.commons.lang.time.DateUtils.truncate(date, Calendar.DAY_OF_MONTH);
	}

	/**
	 * @param args
	 * @throws ParseException
	 */
	public static void main(String[] args) throws ParseException {
		String operTime = "2017-04-17 15:10:10";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
		
		Date d = sdf.parse(operTime);
		
		
		System.out.println(sdf1.format(d));
	}
}
