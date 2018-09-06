package com.zhima.widget;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import com.zhima.basic.exception.UserSystemException;
import com.zhima.util.SWTResourceManager;

/**
 * ThreadWaiting概要说明：多线程等待执行方法
 * @author lcy
 */
public class ThreadWaiting {
	private Shell shell;

	private Object object;
	private String methodName;
	@SuppressWarnings("rawtypes")
	private Class[] paramClass;
	private Object paramValue[];
	
	private Shell parentShell;
	private String text;

	/**
	 * 
	 * 构造函数:构造等候提示窗口
	 * @param object 调用的父类
	 * @param methodName 执行方法
	 * @param paramClass 方法参数类型
	 * @param paramValue 方法参数值
	 */
	@SuppressWarnings("rawtypes")
	public ThreadWaiting(Object object, String methodName,Class[] paramClass,Object paramValue[]) {
		this.object = object;
		this.methodName = methodName;
		this.paramClass = paramClass;
		this.paramValue = paramValue;
	}
	
	public void task(){
		new ThreadTask().start();
	}
	
	public void task(Shell shell){
		this.parentShell = shell;
		new ThreadTask().start();
	}
	
	public void task(String text){
		this.text = text;
		new ThreadTask().start();
	}
	
	public void task(Shell shell,String text){
		this.parentShell = shell;
		this.text = text;
		new ThreadTask().start();
	}

	private class ThreadTask extends Thread {
		public void run() {
			Display.getDefault().asyncExec(new Runnable() {
				@SuppressWarnings({ "rawtypes", "unchecked" })
				@Override
				public void run() {
					try {
						shell = new Shell(new Shell(),SWT.ON_TOP|SWT.BORDER|SWT.TOOL);
						shell.setSize(425,80);
						shell.setLayout(new FormLayout());
	
						Label label = new Label(shell, SWT.NONE);
						label.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
						if (null == text || "".equals(text)){
							label.setText("操作正在执行，请您稍等片刻...");
						}else{
							label.setText(text);
						}
						label.setAlignment(SWT.CENTER);
						label.setFont(SWTResourceManager.getFont("宋体", 20, SWT.NORMAL));
						FormData data = new FormData();
						data.top = new FormAttachment(0,22);
						data.left = new FormAttachment(0);
						data.right = new FormAttachment(100);
						label.setLayoutData(data);
						
						setCenter();
						shell.open();
						Class cls = object.getClass();
						cls.getDeclaredMethod(methodName, paramClass).invoke(object, paramValue);
						shell.close();
						shell.dispose();
					} catch (Exception e) {
						throw new UserSystemException(e.getMessage());
					} 
				}
			});
		}
	}
	
	/**
	 * setCenter方法描述：设置居中
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-28 下午07:00:24
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间) void
	 */
	private void setCenter(){
		if (null != parentShell){
			Rectangle parentBounds = parentShell.getBounds();  
			Rectangle shellBounds = shell.getBounds();  
			shell.setLocation(parentBounds.x + (parentBounds.width - shellBounds.width)/2, 
					parentBounds.y + (parentBounds.height - shellBounds.height)/2);  
		}else{
			Rectangle bounds = Display.getDefault().getPrimaryMonitor().getBounds();
			Rectangle rect = shell.getBounds();
			int x = bounds.x + (bounds.width - rect.width) / 2;
			int y = bounds.y + (bounds.height - rect.height) / 2;
			shell.setLocation(x, y);
		}
	}
	
	public void close(){
		shell.setVisible(false);
	}
}
