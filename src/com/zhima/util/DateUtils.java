package com.zhima.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.zhima.util.time.JNative;
import com.zhima.util.time.NativeFactory;

/**
 * 日期工具类
 * 默认使用 "yyyy-MM-dd HH:mm:ss" 格式化日期
 * @author EwinLive
 * @version1.0
 */
public final class DateUtils {
	
    /**
     * 英文简写（默认）如：2010-12-01
     */
    public static String FORMAT_SHORT = "yyyy-MM-dd";
    
    /**
     * 英文全称  如：2010-12-01 23:15:06
     */
    public static String FORMAT_LONG = "yyyy-MM-dd HH:mm:ss";
    
    /**
     * 精确到毫秒的完整时间    如：yyyy-MM-dd HH:mm:ss.S
     */
    public static String FORMAT_FULL = "yyyy-MM-dd HH:mm:ss.S";
    
    /**
     * 中文简写  如：2010年12月01日
     */
    public static String FORMAT_SHORT_CN = "yyyy年MM月dd"; 
    
    /**
     * 中文全称  如：2010年12月01日  23时15分06秒
     */
    public static String FORMAT_LONG_CN = "yyyy年MM月dd日  HH时mm分ss秒";
    
    /**
     * 精确到毫秒的完整中文时间
     */
    public static String FORMAT_FULL_CN = "yyyy年MM月dd日  HH时mm分ss秒SSS毫秒";
    
    /**
     * 获得默认的 date pattern
     */
    public static String getDatePattern() {
        return FORMAT_LONG;
    }
    
    /**
     * 根据预设格式返回当前日期
     * @return
     */
    public static String getNow() {
        return format(new Date());
    }
    
    /**
     * 根据用户格式返回当前日期
     * @param format
     * @return
     */
    public static String getNow(String format) {
        return format(new Date(), format);
    }
    
    public static String formatDateTime(String baseDate,String strFormat){
		SimpleDateFormat df = new SimpleDateFormat(strFormat);
		Date tmpDate = null;
		try{
			tmpDate = df.parse(baseDate);
		}catch(Exception e){
			
		}
		return df.format(tmpDate);
	}
    
    /**
     * 使用预设格式格式化日期
     * @param date
     * @return
     */
    public static String format(Date date) {
        return format(date, getDatePattern());
    }
    
    /**
     * 使用用户格式格式化日期
     * @param date 日期
     * @param pattern 日期格式
     * @return
     */
    public static String format(Date date, String pattern) {
        String returnValue = "";
        if (date != null) {
        	SimpleDateFormat df = new SimpleDateFormat(pattern);
            returnValue = df.format(date);
        }
        return (returnValue);
    }
    
    /**
     * 使用预设格式提取字符串日期
     * @param strDate 日期字符串
     * @return
     */
    public static Date parse(String strDate) {
        return parse(strDate, getDatePattern());
    }
    
    /**
     * 使用用户格式提取字符串日期
     * @param strDate 日期字符串
     * @param pattern 日期格式
     * @return
     */
    public static Date parse(String strDate, String pattern) {
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        try {
            return df.parse(strDate);
        } catch (ParseException e) {
        	e.printStackTrace();
            return null;
        }
    }
    
    /**
     * 在日期上增加数个整月
     * @param date 日期
     * @param n 要增加的月数
     * @return
     */
    public static Date addMonth(Date date, int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, n);
        return cal.getTime();
    }
    
    /**
     * 在日期上增加天数
     * @param date 日期
     * @param n 要增加的天数
     * @return
     */
    public static Date addDay(Date date, int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, n);
        return cal.getTime();
    }
    
    /**
     * upDownDay方法描述：上下调整日期
     * 创  建  人 ：鲁承毅
     * 创建时间：2013-6-19 下午05:12:08
     * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
     * 修改时间：(请填上修改时间)
     * @param date 日期
     * @param n　天数
     * @return newDate 日期
     */
	public static String upDownDay(String date,int n){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date newDate = null;
		try{
			newDate = df.parse(date);
		}catch(Exception e){
			
		}
		long nDay = (newDate.getTime() / (24 * 60 * 60 * 1000) +1 + n)*(24*60*60*1000);
		newDate.setTime(nDay);
		return df.format(newDate);
	}
    
    /**
     * 获取时间戳
     */
    public static String getTimeString() {
        SimpleDateFormat df = new SimpleDateFormat(FORMAT_FULL);
        Calendar calendar = Calendar.getInstance();
        return df.format(calendar.getTime());
    }
    
    /**
     * 获取日期年份
     * @param date 日期
     * @return
     */
    public static String getYear(Date date) {
        return format(date).substring(0, 4);
    }
    
    /**
     * 按默认格式的字符串距离今天的天数
     * @param date 日期字符串
     * @return
     */
    public static int countDays (String date) {
        long t = Calendar.getInstance().getTime().getTime();
        Calendar c = Calendar.getInstance();
        c.setTime(parse(date));
        long t1 = c.getTime().getTime();
        return (int)(t/1000 - t1/1000)/3600/24;
    }
    
    /**
     * 按用户格式字符串距离今天的天数
     * @param date 日期字符串
     * @param format 日期格式
     * @return
     */
    public static int countDays (String date, String format) {
        long t = Calendar.getInstance().getTime().getTime();
        Calendar c = Calendar.getInstance();
        c.setTime(parse(date, format));
        long t1 = c.getTime().getTime();
        return (int)(t/1000 - t1/1000)/3600/24;
    }
 
	public static void setComputerDate(String dateTime) throws Exception{
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date newTime = null;
		try{
			newTime = df.parse(dateTime);
			JNative jNative = NativeFactory.newNative();
			jNative.setLocalTime(newTime);
		}catch(Exception e){
			throw e;
		}
	}
	
	/**
	 * secondInterval方法描述：计算两个时间相隔秒
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-29 下午10:08:12
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param startTime
	 * @param endTime
	 * @return long
	 */
	public static long secondInterval(String startTime,String endTime){
		SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT_LONG);
		long between = 0;
		try {
			java.util.Date begin=dateFormat.parse(startTime);
			java.util.Date end = dateFormat.parse(endTime);
			between=(end.getTime()- begin.getTime())/1000; //除以1000是为了转换成秒,除以60转换为分
		} catch (ParseException e) {
			
		}
		return between;
	}
	
	/**
	 * minuteInterval方法慨述:得到指定格式两个时间的间隔分钟数(date2 - date1)
	 * @param strFormat 格式"yyyy-MM-dd HH:mm:ss"
	 * @param date1  时间"HH:mm"
	 * @param date2 时间"HH:mm"
	 * @return long 返回间隔分钟数
	 * @throws
	 */
	public static long minuteInterval(String strFormat,String date1,String date2){
		SimpleDateFormat dfs = new SimpleDateFormat(strFormat);
		long between = 0;
		try {
			java.util.Date begin=dfs.parse(date1);
			java.util.Date end = dfs.parse(date2);
			between=(end.getTime()- begin.getTime())/1000/60; //除以1000是为了转换成秒,除以60转换为分
		} catch (ParseException e) {
		}
		return between;
	}
	
	/**
	 * 得到当前日期加减n天后的日期，返回为String (yyyy-MM-dd)
	 * @param n   正表后几天，负表示前几天
	 * @return    以yyyy-MM-dd 格式的字符串
	 */
	public static String nDaysAfterStringDate(int n){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar curDate = Calendar.getInstance();
		curDate.add(Calendar.DAY_OF_MONTH,+n);
		return df.format(curDate.getTime());
	}
	
	/**
	 * 计算两个日期间隔天数（secondDate - firstDate）
	 * @param firstDate 为String型
	 * @param secondDate  为String型
	 * @return
	 * @throws Exception 
	 */
	public static int nDaysBetweenTwoDate(String firstString, String secondString) throws Exception {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date firstDate = null;
		Date secondDate = null;
		
		try {
			firstDate = df.parse(firstString);
			secondDate = df.parse(secondString);
		} catch (Exception e) {
			throw new Exception("日期格式不正确！");
		}
		int nDay =(int) ((secondDate.getTime() - firstDate.getTime()) / (24 * 60 * 60 * 1000));
		return nDay;
	}
	
	/**
	 * minuteAfter方法慨述:得到time时间，minute分钟数之后的时间
	 * @param time 时间，格式为“HH:mm”
	 * @param minute 分钟数
	 * @return String 返回时间“HH:mm”
	 * @throws
	 */
	public static String minuteAfter(String time,int minute){
		SimpleDateFormat dfs = new SimpleDateFormat("HH:mm");
		java.util.Date myDate = new java.util.Date();
		try {
			java.util.Date date = dfs.parse(time);
			myDate.setTime(date.getTime()+(minute*60*1000));	
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dfs.format(myDate);
	}
	

	/**
	 * nHoursAfterOneDateString方法慨述:给定一个日期型字符串，返回加减n小时的日期型字符串
	 * 创 建  人：刘发忠
	 * 创建时间：2012-11-9 下午06:09:26
	 * 修 改  人：刘发忠
	 * 修改日期：2012-11-9 下午06:09:26
	 * @param baseDate
	 * @param n
	 * @return String 
	 * @throws
	 */
	public static String nHoursAfterOneDateString(String baseDate,int n){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date tmpDate = null;
		try{
			tmpDate = df.parse(baseDate);
		}catch(Exception e){
			
		}
		long nDay = (tmpDate.getTime() / (60 * 60 * 1000) +1 + n)*(60*60*1000);
		tmpDate.setTime(nDay);
		return df.format(tmpDate);
	}
	
	/**
	 * 给定一个日期型字符串，返回加减n天后的日期型字符串
	 * @param baseDate  字符串型
	 * @param n   正表后几天，负表示前几天
	 * @return
	 */
	public static String nDaysAfterOneDateString(String baseDate,int n){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date tmpDate = null;
		try{
			tmpDate = df.parse(baseDate);
		}catch(Exception e){
			
		}
		long nDay = (tmpDate.getTime() / (24 * 60 * 60 * 1000) +1 + n)*(24*60*60*1000);
		tmpDate.setTime(nDay);
		return df.format(tmpDate);
	}
	
	/**
	 * 计算两个日期间隔天数（secondDate - firstDate）
	 * @param firstDate 为Date型
	 * @param secondDate  为Date型
	 * @return
	 */
	public static int nDaysBetweenTwoDate(Date firstDate, Date secondDate) {
		int nDay =(int) ((secondDate.getTime() - firstDate.getTime()) / (24 * 60 * 60 * 1000));
		return nDay;
	}
	
	/**
	 * StringToDate方法慨述:字符转换为日期
	 * @param dateStr
	 * @param formatStr
	 * @return Date 
	 * @throws
	 */
	public static Date StringToDate(String dateStr,String formatStr){		
		DateFormat dd=new SimpleDateFormat(formatStr);		
		Date date=null;		
		try {			
			date = dd.parse(dateStr);		
		} catch (ParseException e) {
			e.printStackTrace();		
		}		
		return date;	
	}
	
	public static boolean isValidDate(String string,String format) {
        boolean convertSuccess = true;
        // 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        try {
            // 设置lenient为false.
            // 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
        	dateFormat.setLenient(false);
        	dateFormat.parse(string);
        } catch (ParseException e) {
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            convertSuccess = false;
        }
        return convertSuccess;
    }
	
	
	
	public static void main(String[] args){
		//System.out.println(secondInterval("2012-12-12 12:23:25","2012-12-12 12:23:28"));
		//System.out.println(minuteAfter("12:05",-2));
		System.out.println(isValidDate("2016-01-01","yyyy-MM-dd"));
		/*//Operating system name
		String osName = System.getProperty("os.name");
		String cmd = "";
		try {
		    if (osName.matches("^(?i)Windows.*$")) {// Window 系统
		    // 格式 HH:mm:ss
		    cmd = "  cmd /c time 22:35:00";
		    Runtime.getRuntime().exec(cmd);
		    // 格式：yyyy-MM-dd
		    cmd = " cmd /c date 2009-03-26";
		    Runtime.getRuntime().exec(cmd);
		} else {// Linux 系统
		   // 格式：yyyyMMdd
		   cmd = "  date -s 20090326";
		   Runtime.getRuntime().exec(cmd);
		   // 格式 HH:mm:ss
		   cmd = "  date -s 22:35:00";
		   Runtime.getRuntime().exec(cmd);
		}
		} catch (IOException e) {
		    e.printStackTrace();
		}*/

	}
}
