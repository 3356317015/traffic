package com.zhima.util.log4j;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LogInit {
	
	private static Logger logger = null;
	
	public LogInit(){
		if(logger == null){
			logger = createLogger();
		}
	}
	
	public Logger getLogger(){
		if(logger == null){
			logger = createLogger();
		}
		return logger;
	}
	
	public Logger createLogger(){
		String path = System.getProperty("user.dir");
		PropertyConfigurator.configure(path+"\\log4j.properties");
		Logger logger = Logger.getLogger(this.getClass());
		if(logger != null){
			return logger;
		}else{
			return null;
		}
	}
	
	public void debug(String msg,Throwable t){
		Logger logger = getLogger();
		logger.debug(msg,t);
	}
	
	public void info(String msg,Throwable t){
		Logger logger = getLogger();
		logger.info(msg,t);
	}
	
	public void error(String msg,Throwable t){
		Logger logger = getLogger();
		logger.error(msg,t);
	}
	
	public void warn(String msg,Throwable t){
		Logger logger = getLogger();
		logger.warn(msg,t);
	}
	
	public void fatal(String msg,Throwable t){
		Logger logger = getLogger();
		logger.fatal(msg,t);
	}
}
