package com.service.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 * @author EwinLive
 */
public class StringUtils {
	/**
	 * 默认的空值
	 */
	public static final String EMPTY = "";

	/**
	 * 检查字符串是否为空
	 * @param str 字符串
	 * @return
	 */
	public static boolean isEmpty(String str) {
		if (str == null) {
			return true;
		} else if (str.length() == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 检查字符串是否为空
	 * @param str 字符串
	 * @return
	 */
	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}
	
	/**
	 * format方法描述：格式化字化串 
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2017-11-12 下午09:33:29
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param str
	 * @return
	 * @return String
	 */
	public static String format(String str){
		if (null != str && str.length()>0){
			return str;
		}else{
			return "";
		}
	}

	/**
	 * 截取并保留标志位之前的字符串
	 * @param str 字符串
	 * @param expr 分隔符
	 * @return
	 */
	public static String substringBefore(String str, String expr) {
		if (isEmpty(str) || expr == null) {
			return str;
		}
		if (expr.length() == 0) {
			return EMPTY;
		}
		int pos = str.indexOf(expr);
		if (pos == -1) {
			return str;
		}
		return str.substring(0, pos);
	}

	/**
	 * 截取并保留标志位之后的字符串
	 * @param str 字符串
	 * @param expr 分隔符
	 * @return
	 */
	public static String substringAfter(String str, String expr) {
		if (isEmpty(str)) {
			return str;
		}
		if (expr == null) {
			return EMPTY;
		}
		int pos = str.indexOf(expr);
		if (pos == -1) {
			return EMPTY;
		}
		return str.substring(pos + expr.length());
	}

	/**
	 * 截取并保留最后一个标志位之前的字符串
	 * @param str 字符串
	 * @param expr 分隔符
	 * @return
	 */
	 public static String substringBeforeLast(String str, String expr) {
	        if (isEmpty(str) || isEmpty(expr)) {
	            return str;
	        }
	        int pos = str.lastIndexOf(expr);
	        if (pos == -1) {
	            return str;
	        }
	        return str.substring(0, pos);
	    }
	 
	 /**
	  * 截取并保留最后一个标志位之后的字符串
	  * @param str
	  * @param expr 分隔符
	  * @return
	  */
	 public static String substringAfterLast(String str, String expr) {
	        if (isEmpty(str)) {
	            return str;
	        }
	        if (isEmpty(expr)) {
	            return EMPTY;
	        }
	        int pos = str.lastIndexOf(expr);
	        if (pos == -1 || pos == (str.length() - expr.length())) {
	            return EMPTY;
	        }
	        return str.substring(pos + expr.length());
	    }
	 
	 /**
	  * 把字符串按分隔符转换为数组
	  * @param string 字符串
	  * @param expr 分隔符
	  * @return
	  */
	 public static String[] stringToArray(String string, String expr){
		 return string.split(expr);
	 }
	 
	 /**
	  * 去除字符串中的空格
	  * @param str
	  * @return
	  */
	 public static String noSpace(String str){
		 str = str.trim();
		 str = str.replace(" ", "_");
		 return str;
	 }

	 /**
	  * isNumeric方法描述：字符串是否为数值
	  * 创  建  人 ：鲁承毅
	  * 创建时间：2013-3-7 上午09:55:42
	  * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	  * 修改时间：(请填上修改时间)
	  * @param str 字符串
	  * @return boolean true=数值,false=字符串
	  */
	 public static boolean isNumeric(String str){
		if (str.matches("\\d+(.\\d+)?")) {
		    if (str.indexOf(".")>0) {return true;}//浮点类型
		    else {return true;}//整形类型
		} else {
			return false;//不是数值类型
		}
	 }
	 
	 /**
	  * excuteForm方法描述：格式化字符串指定位数小数
	  * 创  建  人 ：鲁承毅
	  * 创建时间：2013-3-20 下午04:40:49
	  * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	  * 修改时间：(请填上修改时间)
	  * @param inputStr 字符串
	  * @param i　小数位数
	  * @return String
	  */
	 public static String excuteForm(String inputStr, int i) {
		 try {
			 java.text.NumberFormat format = java.text.NumberFormat
			 		.getInstance();
			 format.setMaximumFractionDigits(i);
			 if (null != inputStr && !"".equals(inputStr)) {
				 Double temp = Double.parseDouble(inputStr);
				 return format.format(temp);
			 }
		 } catch (Exception e) {
			 return null;
		 }
		 return null;
	 }
	 
	 /**
	  * getNumbers方法描述：截取数字
	  * 创  建  人 ：鲁承毅
	  * 创建时间：2013-12-23 下午09:51:23
	  * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	  * 修改时间：(请填上修改时间)
	  * @param content
	  * @return
	  * @return String
	  */
	 public static String getNumbers(String content) {
		 String regEx="[^0-9]";   
		 Pattern p = Pattern.compile(regEx);   
		 Matcher m = p.matcher(content);   
		 return m.replaceAll("").trim();
	 }


}
