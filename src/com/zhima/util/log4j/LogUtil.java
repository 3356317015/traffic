package com.zhima.util.log4j;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.eclipse.swt.widgets.Shell;

import com.zhima.widget.MsgBox;


public class LogUtil {
	private static LogInit logInit = null;

	public static LogInit getLogger() {
		if (logInit == null) {
			logInit = new LogInit();
		}
		return logInit;
	}

	public static void debug(String msg) {
		logInit = getLogger();
		logInit.debug(msg, null);
	}
	
	public static void debug(Throwable t){
		logInit = getLogger();
		logInit.debug("",t);
	}

	public static void debug(String msg, Throwable t) {
		logInit = getLogger();
		logInit.debug(msg, t);
	}

	public static void info(String msg) {
		logInit = getLogger();
		logInit.info(msg, null);
	}

	public static void info(Throwable t) {
		logInit = getLogger();
		logInit.info("", t);
	}
	
	public static void info(String msg, Throwable t) {
		logInit = getLogger();
		logInit.info(msg, t);
	}

	public static void error(String msg) {
		logInit = getLogger();
		logInit.error(msg, null);
	}

	public static void error(Throwable t) {
		logInit = getLogger();
		logInit.error("",t);
	}
	
	public static void error(String msg, Throwable t) {
		logInit = getLogger();
		logInit.error(msg, t);
	}

	public static void warn(String msg) {
		logInit = getLogger();
		logInit.warn(msg, null);
	}

	public static void warn(Throwable t) {
		logInit = getLogger();
		logInit.warn("", t);
	}
	
	public static void warn(String msg, Throwable t) {
		logInit = getLogger();
		logInit.warn(msg, t);
	}

	public static void fatal(String msg) {
		logInit = getLogger();
		logInit.fatal(msg, null);
	}

	public static void fatal(Throwable t) {
		logInit = getLogger();
		logInit.fatal("", t);
	}
	
	public static void fatal(String msg, Throwable t) {
		logInit = getLogger();
		logInit.fatal(msg, t);
	}
	
	public static String operLog(Exception e,String logType,boolean isLog,boolean isDialog) {
		StringWriter sw = new StringWriter();  
        e.printStackTrace(new PrintWriter(sw, true));
        String string = sw.toString();
        if (isLog == true){
        	if ("E".equals(logType)){
        		error("\r\n"+string);
        	} else if ("W".equals(logType)){
        		warn("\r\n"+string);
        	} else if ("I".equals(logType)){
        		info("\r\n"+string);
        	} else if ("D".equals(logType)){
        		debug("\r\n"+string);
        	}
        }
        if (isDialog == true){
        	MsgBox.error(new Shell(), e.getMessage(), string);
        }
        return string;
	}
	
	public static String operLog(Exception e,String logType,boolean isLog,String message) {
		StringWriter sw = new StringWriter();  
        e.printStackTrace(new PrintWriter(sw, true));
        String string = sw.toString();
        if (isLog == true){
        	if ("E".equals(logType)){
        		error("\r\n"+string);
        	} else if ("W".equals(logType)){
        		warn("\r\n"+string);
        	} else if ("I".equals(logType)){
        		info("\r\n"+string);
        	} else if ("D".equals(logType)){
        		debug("\r\n"+string);
        	}
        }
        if (null != message && message.length()>0){
        	MsgBox.error(new Shell(), message, string);
        }
        return string;
	}
}
