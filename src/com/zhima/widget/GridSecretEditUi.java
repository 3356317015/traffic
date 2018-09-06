package com.zhima.widget;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.zhima.basic.CommFinal;
import com.zhima.basic.StyleFinal;
import com.zhima.frame.action.sam.ISamUser;
import com.zhima.frame.action.sam.ISamUserSecret;
import com.zhima.frame.action.sam.impl.ImpSamUser;
import com.zhima.frame.action.sam.impl.ImpSamUserSecret;
import com.zhima.frame.model.SamUser;
import com.zhima.frame.model.SamUserSecret;
import com.zhima.util.SWTResourceManager;
import com.zhima.util.log4j.LogUtil;
import com.zhima.widget.grid.GridColumn;
import com.zhima.widget.grid.GridConfig;
import com.zhima.widget.grid.GridView;

public class GridSecretEditUi extends Dialog {
	private GridView gridView;
	private GridView gridUser;
	private GridConfig gridConfig;
	private List<SamUserSecret> secrets = new ArrayList<SamUserSecret>();
	private List<SamUser> users;
	
	public GridSecretEditUi(Shell shell, GridConfig gridConfig) {
		super(shell);
		this.gridConfig = gridConfig;
	}

	protected void configureShell(Shell shell){
		super.configureShell(shell);
		shell.setText("保密项设置");
	}
	
	@Override
	protected Point getInitialLocation(Point initialSize) {
		Rectangle screenSize =  Display.getDefault().getClientArea();
		return new Point((screenSize.width - initialSize.x)/2, (screenSize.height - initialSize.y)/2);
	}
	
	@Override
	protected Point getInitialSize(){
        return new Point(635,425);
    }
	
	protected void createButtonsForButtonBar(Composite parent){
		GridData btnData = new GridData(StyleFinal.DIALOGBAR_ALIGNMENT);
		parent.setLayoutData(btnData);
		Button confirm = createButton(parent, 1, "确定(&O)", false);
		confirm.setFont(StyleFinal.SYS_FONT);
		Button cancel = createButton(parent, 0, "取消(&C)", false);
		cancel.setFont(StyleFinal.SYS_FONT);
		if (StyleFinal.BTN_SHOWIMAGE == true){
			confirm.setImage(SWTResourceManager.getImage(CommFinal.IMAGE_BUTTON_OK));
			cancel.setImage(SWTResourceManager.getImage(CommFinal.IMAGE_BUTTON_CANCEL));
		}
	}
	
	protected Control createDialogArea(Composite parent) {
		Composite compMain = (Composite) super.createDialogArea(parent);
		compMain.setLayout(new FormLayout());

		List<GridColumn> columns=new ArrayList<GridColumn>();
		columns.add(new GridColumn("表格列名","EName",150));
		columns.add(new GridColumn("显示名称","CName",150));
		GridConfig gridConfig = new GridConfig();
		gridConfig.setShowPage(false);
		gridConfig.setCheck(false);
		gridConfig.setShowSeq(false);
		gridConfig.setColumns(columns);
		gridView = new GridView(compMain, SWT.BORDER);
		gridView.CreateTabel(gridConfig);
		FormData data = new FormData();
		data.top = new FormAttachment(0);
		data.left = new FormAttachment(0);
		data.bottom = new FormAttachment(100);
		data.right = new FormAttachment(50);
		gridView.setLayoutData(data);
		gridView.table.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if (null != users && users.size()>0){
					for (int i = 0; i < users.size(); i++) {
						SamUser user = users.get(i);
						user.setStatus("N");
						gridUser.updateRow(i, user);
					}
				}
				if (null != secrets && secrets.size()>0){
					GridColumn gridColumn = (GridColumn) gridView.getSelection();
					for (int i = 0; i < secrets.size(); i++) {
						if (gridColumn.getEName().equals(secrets.get(i).getColumnEname())){
							if (null != users && users.size()>0){
								for (int j = 0; j < users.size(); j++) {
									if (secrets.get(i).getUserSeq().equals(users.get(j).getUserSeq())){
										SamUser user = users.get(j);
										user.setStatus("Y");
										gridUser.updateRow(j, user);
									}	
								}
							}
						}
					}
				}
			}
		});
		
		gridUser = new GridView(compMain, SWT.BORDER);
		columns=new ArrayList<GridColumn>();
		columns.add(new GridColumn("姓名","userName",120));
		columns.add(new GridColumn("帐号","userCode",120));
		columns.add(new GridColumn("保密","status",60));
		gridConfig = new GridConfig();
		
		gridConfig.setShowSeq(false);
		gridConfig.setShowPage(false);
		gridConfig.setCheck(false);
		gridConfig.setColumns(columns);
		gridUser.CreateTabel(gridConfig);
		data = new FormData();
		data.top = new FormAttachment(0);
		data.left = new FormAttachment(gridView);
		data.bottom = new FormAttachment(100);
		data.right = new FormAttachment(100);
		gridUser.setLayoutData(data);
		gridUser.table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent arg0) {
				if (null != gridView.getSelection()){
					GridColumn gridColumn = (GridColumn) gridView.getSelection();
					if (null != gridUser.getSelection()){
						SamUser user = (SamUser) gridUser.getSelection();
						if ("N".equals(user.getStatus())){
							SamUserSecret userSecret = new SamUserSecret();
							userSecret.setColumnEname(gridColumn.getEName());
							userSecret.setUserSeq(user.getUserSeq());
							secrets.add(userSecret);
							user.setStatus("Y");
							gridUser.updateRow(gridUser.getSelectionIndex(), user);
						}else{
							if(null != secrets && secrets.size()>0){
								for (int j = 0; j < secrets.size(); j++) {
									if (user.getUserSeq().equals(secrets.get(j).getUserSeq())
											&& gridColumn.getEName().equals(secrets.get(j).getColumnEname())){
										secrets.remove(j);
									}
								}
							}
							user.setStatus("N");
							gridUser.updateRow(gridUser.getSelectionIndex(), user);
						}
					}
				}
			}
		});
		initData();

		return compMain;
	}
	
	private void initData(){
		try {
			gridView.setDataList(gridConfig.getBasecols());
			ISamUser iSamUser = new ImpSamUser();
			users = iSamUser.queryByOrganize(CommFinal.organize.getOrganizeSeq());
			if (null != users && users.size()>0){
				for (int i = 0; i < users.size(); i++) {
					users.get(i).setStatus("N");
				}
			}
			gridUser.setDataList(users);
			if (null != users && users.size()>0){
				for (int i = 0; i < users.size(); i++) {
					gridUser.table.getItem(i).setForeground(2,SWTResourceManager.getColor(SWT.COLOR_RED));
				}
			}
			ISamUserSecret iSamUserSecret = new ImpSamUserSecret();
			secrets = iSamUserSecret.queryByGridSecret(gridConfig.getObj().getClass().getName(), gridConfig.getGridName());
			if (null != secrets && secrets.size()>0){
			}else{
				secrets = new ArrayList<SamUserSecret>();
			}
		} catch (Exception e) {
			LogUtil.operLog(e, "E", true, true);
		}
	}

	protected void buttonPressed(int buttonId){
		try {
			if(1 == buttonId){
				if (null != secrets && secrets.size()>0){
					for (int i = 0; i < secrets.size(); i++) {
						secrets.get(i).setClassName(gridConfig.getObj().getClass().getName());
						secrets.get(i).setGridName(gridConfig.getGridName());
					}
				}
				ISamUserSecret iSamUserSecret = new ImpSamUserSecret();
				iSamUserSecret.updateUserSecret(secrets,gridConfig.getObj().getClass().getName(), gridConfig.getGridName(),CommFinal.initConfig());
				close();
			} else if (0 == buttonId) {
				close();
			}
		} catch (Exception e) {
			LogUtil.operLog(e,"E",true,true);
		}
		
	}

	
}
