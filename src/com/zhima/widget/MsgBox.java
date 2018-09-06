
package com.zhima.widget;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

import com.zhima.util.log4j.LogUtil;

/**
 * MsgFinal概要说明：弹出消息
 * @author lcy
 */
public class MsgBox {
	/**
	 * infMsg方法描述：信息提示
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2012-12-10 下午03:20:21
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param shell
	 * @param msg 信息
	 */
	public static void infomation(Shell shell,String msg){
		if(shell.isDisposed()==false){
			MessageBox msgBox = new MessageBox(shell,SWT.ICON_INFORMATION|SWT.OK);
			msgBox.setText("提示");
			msgBox.setMessage(msg);
			msgBox.open();
		}
	}
	
	/**
	 * wrgMsg方法描述：警告提示
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2012-12-10 下午03:22:45
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param shell
	 * @param msg 警告信息
	 */
	public static void warning(Shell shell,String msg){
		if(shell.isDisposed()==false){
			MessageBox msgBox = new MessageBox(shell,SWT.ICON_WARNING|SWT.OK);
			msgBox.setText("警告");
			msgBox.setMessage(msg);
			msgBox.open();
		}
	}
	
	/**
	 * cromMsg方法描述：确认提示
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2012-12-10 下午03:23:42
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param shell
	 * @param msg 确认
	 * @return int 选择的按钮
	 */
	public static int confirm(Shell shell,String msg){
		if(shell.isDisposed()==false){
			MessageBox msgBox = new MessageBox(shell,SWT.ICON_QUESTION|SWT.YES|SWT.NO);
			msgBox.setText("确认");
			msgBox.setMessage(msg);
			int btn = msgBox.open();
			return btn;
		}
		return 0;
	}
	
	/**
	 * errMsg方法描述：错误提示
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2012-12-10 下午03:18:11
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param shell
	 * @param msg 错误信息
	 * @wbp.parser.entryPoint
	 */
	public static void error(Shell shell,String msg){
		if(shell.isDisposed()==false){
			MessageBox msgBox = new MessageBox(shell,SWT.ICON_ERROR|SWT.OK);
			msgBox.setText("错误");
			msgBox.setMessage(msg);
			msgBox.open();
		}
	}
	
	/**
	 * error方法描述：错误提示
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-7-26 下午11:32:13
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param shell
	 * @param message 错误信息
	 * @param detail 明细信息
	 */
	public static void error(Shell shell,String message,String detail){
		ErrorDialog dialog = new ErrorDialog(new Shell(), message, detail, SWT.CLOSE);
		dialog.open();
		/*StackTraceElement [] messages=e.getStackTrace();
		int length=messages.length;
		for(int i=0;i<length;i++){
			if (messages[i].getLineNumber()>0){
				System.out.println("ClassName:"+messages[i].getClassName());
				System.out.println("getFileName:"+messages[i].getFileName());
				System.out.println("getLineNumber:"+messages[i].getLineNumber());
				System.out.println("getMethodName:"+messages[i].getMethodName());
				System.out.println("toString:"+messages[i].toString());
			}
		}*/
	}
	
	public static void main(String[] args) {  
	       try {  
	    	   String aa = "";  
	           System.out.println(aa.substring(3));  
	       }catch (Exception e) {  
	           LogUtil.operLog(e,"E",true,true);
	        }  
		}  
	
}

