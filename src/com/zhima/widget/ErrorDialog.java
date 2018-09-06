package com.zhima.widget;

import java.awt.Frame;
import java.awt.Toolkit;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.hexapixel.widgets.generic.Utils;
import com.zhima.util.ImageViewer;
import com.zhima.util.SWTResourceManager;

public class ErrorDialog extends Dialog {

	protected Object result;
	protected Shell shell;
	
	private Label lbMessage;
	private Text txtDetail;
	
	private boolean isDetail = false;
	
	private String message;
	private String detail;
	

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public ErrorDialog(Shell parent, String message, String detail, int style) {
		super(parent, style|SWT.APPLICATION_MODAL);
		this.message = message;
		this.detail = detail;
		Frame frame=new Frame();  
		Toolkit toolkit = frame.getToolkit();  
		toolkit.beep();  
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shell = new Shell(getParent(), getStyle());
		shell.setSize(535, 190);
		shell.setText("异常信息");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		Utils.centerDialogOnScreen(shell.getShell());
		Composite composite = new Composite(shell, SWT.NONE);
		final ImageViewer iVicon = new ImageViewer(composite);
		iVicon.setBounds(24, 34, 37, 36);
		iVicon.setImage(System.getProperty("user.dir") + "\\images\\pop_warning.png");
		

		lbMessage = new Label(composite, SWT.NONE|SWT.WRAP);
		lbMessage.setBounds(67, 34, 452, 85);
		lbMessage.setText(message);
		
		Button btOk = new Button(composite, SWT.NONE);
		btOk.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				shell.close();
			}
		});
		btOk.setBounds(326, 125, 85, 26);
		btOk.setText("OK");
		
		Button btDetail = new Button(composite, SWT.NONE);
		btDetail.setBounds(417, 125, 85, 26);
		btDetail.setText("Details");
		btDetail.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if (isDetail == false){
					isDetail = true;
					txtDetail.setVisible(true);
					shell.setSize(535, 340);
				}else{
					isDetail = false;
					txtDetail.setVisible(false);
					shell.setSize(535, 190);
				}
			}
		});
		
		txtDetail = new Text(composite, SWT.BORDER|SWT.H_SCROLL|SWT.V_SCROLL);
		txtDetail.setVisible(false);
		txtDetail.setBounds(10, 160, 509, 142);
		txtDetail.setForeground(SWTResourceManager.getColor(124, 252, 0));
		txtDetail.setBackground(SWTResourceManager.getColor(70, 130, 180));
		txtDetail.setText(detail);
	}
	
}
