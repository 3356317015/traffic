package com.zhima.frame.ui;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.zhima.basic.StyleFinal;
import com.zhima.frame.model.SamUser;
import com.zhima.widget.CCalendar;
import com.zhima.widget.CDropBox;
import com.zhima.widget.CImage;
import com.zhima.widget.grid.GridColumn;

public class userTester {

	protected Shell shell;
	private CDropBox qCombo;
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			userTester window = new userTester();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(581, 300);
		shell.setText("SWT Application");
		shell.setLayout(new FormLayout());
		
		List<GridColumn> columnList = new ArrayList<GridColumn>();
		columnList.add(new GridColumn("用户代码", "userCode",80));
		columnList.add(new GridColumn("用户名称", "userName", 80));
		columnList.add(new GridColumn("电话号码", "telephone", 120));
		List<SamUser> samUsers = new ArrayList<SamUser>();
		for (int i = 0; i < 10; i++) {
			SamUser samUser = new SamUser();
			samUser.setUserCode("CODE " + i);
			samUser.setUserName("NAME " + i);
			samUser.setTelephone("1360000000" +i);
			samUsers.add(samUser);
		}
		
		qCombo = new CDropBox(shell, columnList, samUsers, "userCode", "userName", SWT.BORDER);
		qCombo.create();
		FormData dataQcombo = new FormData();
		dataQcombo.top = new FormAttachment(10);
		dataQcombo.left = new FormAttachment(10);
		dataQcombo.width=120;
		qCombo.setLayoutData(dataQcombo);
		qCombo.setFont(StyleFinal.SYS_FONT);
	
		CCalendar calendar = new CCalendar(shell, SWT.BORDER);
		FormData data = new FormData();
		data.top = new FormAttachment(qCombo,0,SWT.TOP);
		data.left = new FormAttachment(qCombo,10);
		data.width=120;
		data.bottom = new FormAttachment(qCombo,0,SWT.BOTTOM);
		calendar.setLayoutData(data);
		calendar.setFont(StyleFinal.SYS_FONT);
		
		CImage cImage = new CImage(shell, SWT.BORDER);
		data = new FormData();
		data.top = new FormAttachment(qCombo,30);
		data.left = new FormAttachment(qCombo,0,SWT.LEFT);
		data.width=80;
		data.height=90;
		cImage.setLayoutData(data);
		
	


	}
}
