package com.cqqyd2014.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
/**
 * 
 * 
 * 
 * @author User
 *
 *关于时间工具类包
 *
 */
public class DateUtil {
	/*
	 //Calendar转化为Date
	private static java.util.Date calender_to_date(Calendar cal){
		Date date=cal.getTime();
		return date;
	}
	
	 */

	 //Date转化为Calendar
	private static Calendar date_to_calender(java.util.Date date){
		Calendar cal=Calendar.getInstance();
		 cal.setTime(date);
		 return cal;
	}
	
	
	/*
	 * 返回昨天，输入起点时间 ，返回前一天时间
	 */
	public static String getYesterday(java.util.Date date) {
		Calendar cal = date_to_calender(date);
		cal.add(Calendar.DATE, -1);
		String yesterday = new SimpleDateFormat("yyyy-MM-dd ").format(cal.getTime());
		return yesterday;
	}
	

	/*
	 * 返回之前或者之后的某一天，时间倒退或者前进 i个24小时,时间倒退i为负数，时间前进i为正数 
	 */

	public static java.util.Date getNearDays(java.util.Date dat, int i) {
		java.util.Calendar Cal = java.util.Calendar.getInstance();
		Cal.setTime(dat);
		Cal.add(java.util.Calendar.HOUR_OF_DAY, 24 * i);
		return (Date) Cal.getTime();
	}

	/*
	 * 返回标准的年月日字符串，比如"2017-06-24"，对于特殊日期“2999-12-31”，返回空字符串
	 */

	public static String JDateToSimpleString(java.util.Date date) {
		
		java.sql.Timestamp t = new java.sql.Timestamp(date.getTime());
		// 生成标准的年月日
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String s = df.format(t);
		if (s.equals("1900-01-01")||s.equals("2999-12-31")){
			return "";
		}
		else{
			return s;
		}


	}
	

	/*
	 * 返回日期的0点0分0秒
	 */
	public static java.util.Date JDateToStartDate(java.util.Date date){
		String s=JDateToSimpleString(date);
		
		return FullStringToJDate(s+" 00:00:00");
	}
	/*
	 * 
	 * 
	 */

	/*
	 * 返回日期的23点59分59秒
	 */
	public static java.util.Date JDateToEndDate(java.util.Date date){
		String s=JDateToSimpleString(date);
		
		return FullStringToJDate(s+" 23:59:59");
	}
	

	public static String ChineseDate(java.util.Date date)  {
		if (getDistanceSecends(date,ShortStringToJDate("1970-1-1"))>0) {
			SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日 E", new Locale("zh", "CN"));

			String s = df.format(date);
			return s;
		}
		else {
			return "";
		}
		

	}


	/*
	 * 返回日期的全长字符串年月日，时分秒，不含微妙 2017-6-24 15:56:01
	 */
	public static String JDateToFullString(java.util.Date date) {
		if (date==null){
			return "";
		}
		
		java.sql.Timestamp t = new java.sql.Timestamp(date.getTime());
		// 生成标准的年月日
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String s = df.format(t);
		if (s.equals("2999-12-31")||s.equals("1900-01-01")){
			return "";
		}
		else{
			return s;
		}
	}

	/*
	 * 标准全长字符串变为日期
	 */
	public static java.util.Date FullStringToJDate(String tDate) {
		if (tDate==null||tDate.length()==0){
			return null;
		}

		// “2014-07-07 00:00:11”这个模式转换成java.util.date标准日期
		try {
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			// tDate = tDate.replaceAll("T", "-");
			Date dDate;

			dDate = format.parse(tDate);
			return dDate;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/*
	 * 短字符串变为日期
	 */
	public static java.util.Date ShortStringToJDate(String tDate) {
		if (tDate==null||tDate.length()==0){
			return null;
		}

		// “2014-07-07 00:00:11”这个模式转换成java.util.date标准日期
		try {
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			// tDate = tDate.replaceAll("T", "-");
			Date dDate;

			dDate = format.parse(tDate);
			return dDate;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * 日前变为紧凑型字符串 20170624
	 */

	public static String JDateToCompatString(java.util.Date date) {
		if (date==null){
			return "";
		}
		if (date.equals(com.cqqyd2014.util.DateUtil.ShortStringToJDate("1900-01-01"))||date.equals(com.cqqyd2014.util.DateUtil.ShortStringToJDate("2999-12-31"))){
			return "";
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");

		String s = df.format(date);
		// s=s.replace(' ', 'T');
		return s;

	}

	/*
	 * 日期变为全长紧凑型字符串201670624160101
	 */

	public static String JDateToFullCompatString(java.util.Date date) {
		if (date==null){
			return "";
		}
		if (date.equals(com.cqqyd2014.util.DateUtil.ShortStringToJDate("1900-01-01"))||date.equals(com.cqqyd2014.util.DateUtil.ShortStringToJDate("2999-12-31"))){
			return "";
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");

		String s = df.format(date);
		// s=s.replace(' ', 'T');
		return s;

	}



	/*
	 * 返回中文的全长日期
	 */

	public static String getLocalFullString(java.util.Date dat) {
		if (dat==null){
			return "";
		}
		if (dat.equals(com.cqqyd2014.util.DateUtil.ShortStringToJDate("1900-01-01"))||dat.equals(com.cqqyd2014.util.DateUtil.ShortStringToJDate("2999-12-31"))){
			return "";
		}
		// 返回结果为“2014-07-07 00:00:11”
		java.text.DateFormat format1 = new java.text.SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒", new Locale("zh", "CN"));
		String s = format1.format(dat);

		return s;
	}


	/*
	 * 两个时间相差距离多少秒，d1为后面的时间,d2为前面的时间
	 * 
	 */
	public static long getDistanceSecends(java.util.Date d1, java.util.Date d2)  {

		long time1 = d1.getTime();
		long time2 = d2.getTime();
		long diff;
		
			diff = time1 - time2;
			
		
		return diff / 1000;

	}
	/**
	 * 
	 * 间隔时间的中文标识X年X月X天，其中年以365计算，月以30天计算
	 * @param d1
	 * @param d2
	 * @return
	 * @throws Exception 
	 */
	public static String getDistanceChinese(java.util.Date d1, java.util.Date d2) {
		long secends=getDistanceSecends(d1,d2);
		return getDistanceChinese(secends);
	}
	public static String getDistanceChinese(long secends) {
		
		String r="";
		//测试有几年
		if (secends>60*60*24*365){
			//大于1年
			long years=secends/(60*60*24*365);
			r=years+"年";
			secends=secends-years*60*60*24*365;
			
		}
		if (secends>60*60*24*30){
			//大于1月
			long months=secends/(60*60*24*30);
			r=r+months+"月";
			secends=secends-months*60*60*24*30;
		}
		if (secends>60*60*24){
			//大于1天
			long days=secends/(60*60*24);
			r=r+days+"天";
			secends=secends-days*60*60*24;
		}
		if (secends>60*60){
			//大于1小时
			long hours=secends/(60*60);
			r=r+hours+"小时";
			secends=secends-hours*60*60;
			
		}
		if (secends>60){
			long min=secends/60;
			r=r+min+"分";
			secends=secends-min*60;
		}
		if (secends>0){
			r=r+secends+"秒";
		}
		
		return r;
	}


	/*
	 * 标准时间自1970年01月01日至今的秒数
	 */
	

	public static java.util.Date paser1970SecendsToJDate(int time) {

		System.setProperty("user.timezone", "Asia/Shanghai");

		TimeZone tz = TimeZone.getTimeZone("Asia/Shanghai");

		TimeZone.setDefault(tz);

		//SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		//String times = format.format(new Date(time * 1000L));

		return new java.util.Date(time * 1000L);

	}
/*
 * 转换为标准时间1970
 */

	public static Long paserJDateTo1970Secends(java.util.Date date) {
		Long l = Long.valueOf(date.getTime());
		return l;

	}
}