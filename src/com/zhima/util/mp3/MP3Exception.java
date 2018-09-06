package com.zhima.util.mp3;

import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * MP3播放异常类
 * @author Administrator
 *
 */
public class MP3Exception extends Exception {

	private static final long serialVersionUID = 1L;
	/**
	 * 异常消息
	 */
	private String message;
	/**
	 * 异常原因
	 */
	private Throwable cause;
	
	public MP3Exception(String message) {
		super(message);
		this.message = message;
	}
	
	public MP3Exception(String message,Throwable ex) {
		super(message,ex);
		this.message = message;
		this.cause = ex;
	}
	
	public Throwable getCause() {
		return (this.cause == null ? null :this.cause);
	}
	
	public String getMessage(){   
		Throwable cause = getCause();   
		if(cause != null){   
		    message = message + ";MP3Exception 源 is " + cause;   
		}
		return message;   
	}
	
	public void printStackTrace(PrintStream ps){   
		if(getCause() == null){   
			super.printStackTrace(ps);      
		}else{   
			ps.println(this);   
			getCause().printStackTrace(ps);   
		}   
	}  
	
	public void printStackTrace(PrintWriter pw){   
		if(getCause() == null){   
			super.printStackTrace(pw);   
		}else{
			pw.println(this);   
			getCause().printStackTrace(pw);   
		}
	}
	
	public void printStackTrace(){   
		printStackTrace(System.err);   
	}
}
