package com.zhima.basic.exception;

import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * UserSystemException概要说明：自定义系统异常
 * @author lcy
 */
public class UserSystemException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	private Throwable cause;
	
	public UserSystemException(String message, Throwable ex) {
		super(message, ex);
		this.cause = ex;
	}

	public UserSystemException(String message) {
		super(message);
	}

	public UserSystemException(Throwable ex) {
		this.cause = ex;
	}
	
	public UserSystemException() {
	}

	public Throwable getCause() {
		return (this.cause == null ? null :this.cause);
	}
	public String getMessage(){   
		String message = super.getMessage();   
		Throwable cause = getCause();   
		if(cause != null){   
		    message = message + ";systemException 源 is " + cause;
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