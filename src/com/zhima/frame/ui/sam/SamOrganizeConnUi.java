package com.zhima.frame.ui.sam;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.zhima.basic.CallMethod;
import com.zhima.basic.CommFinal;
import com.zhima.basic.StyleFinal;
import com.zhima.frame.action.sam.ISamOrganize;
import com.zhima.frame.action.sam.impl.ImpSamOrganize;
import com.zhima.frame.model.SamOrganize;
import com.zhima.util.DateUtils;
import com.zhima.util.SWTResourceManager;
import com.zhima.util.log4j.LogUtil;
import com.zhima.widget.KLabel;
import com.zhima.widget.MsgBox;
import com.zhima.widget.grid.GridView;

public class SamOrganizeConnUi extends Dialog {
	private GridView gridView;
	private String operType;
	
	private CCombo cConnClass;
	
	private Text txtConnUrl;
	
	private Text txtConnDbname;
	
	private Text txtConnUser;
	
	private Text txtConnPassword;
	
	private Text txtCenterUrl;

	protected SamOrganizeConnUi(Shell shell,GridView gridView,String operType) {
		super(shell);
		this.gridView = gridView;
		this.operType = operType;
	}

	protected void configureShell(Shell shell){
		super.configureShell(shell);
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		shell.setBackground(StyleFinal.DIALOG_BACK_GROUND);
		shell.setText("客运站连接设置");
	}
	
	@Override
	protected Point getInitialLocation(Point initialSize) {
		Rectangle screenSize =  Display.getDefault().getClientArea();
		return new Point((screenSize.width - initialSize.x)/2, (screenSize.height - initialSize.y)/2);
	}
	
	@Override
	protected Point getInitialSize(){
        return new Point(395,285);
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
		CallMethod callMethod = new CallMethod();
		
		Group groupMain = new Group(compMain,SWT.NONE);
		groupMain.setText("连接信息");
		groupMain.setFont(StyleFinal.SYS_FONT);
		GridLayout gridLayout = new GridLayout(2,false);
		gridLayout.verticalSpacing=StyleFinal.VERTICAL_SPACING;
		groupMain.setLayout(gridLayout);
		
		FormData data = new FormData();
		data.top = new FormAttachment(0,5);
		data.left = new FormAttachment(0,5);
		data.right = new FormAttachment(100,-5);
		data.bottom = new FormAttachment(100);
		groupMain.setLayoutData(data);

		KLabel lbConnClass = new KLabel(groupMain, SWT.RIGHT);
		lbConnClass.setFont(StyleFinal.SYS_FONT);
		lbConnClass.setText("连接类型:");
		GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbConnClass.setLayoutData(gridData);
		
		cConnClass = new CCombo(groupMain, SWT.BORDER|SWT.READ_ONLY);
		cConnClass.setItems(new String[] {"com.mysql.jdbc.Driver", "oracle.jdbc.driver.OracleDriver"});
		cConnClass.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		cConnClass.setLayoutData(gridData);

		KLabel lbConnUrl = new KLabel(groupMain, SWT.RIGHT);
		lbConnUrl.setFont(StyleFinal.SYS_FONT);
		lbConnUrl.setText("连接地址:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL|GridData.HORIZONTAL_ALIGN_END);
		lbConnUrl.setLayoutData(gridData);
		
		txtConnUrl = new Text(groupMain, SWT.BORDER);
		txtConnUrl.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtConnUrl.setLayoutData(gridData);
		
		KLabel lbConnDbname = new KLabel(groupMain, SWT.RIGHT);
		lbConnDbname.setFont(StyleFinal.SYS_FONT);
		lbConnDbname.setText("数据库名:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL|GridData.HORIZONTAL_ALIGN_END);
		lbConnDbname.setLayoutData(gridData);
		
		txtConnDbname = new Text(groupMain, SWT.BORDER);
		txtConnDbname.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtConnDbname.setLayoutData(gridData);
		
		KLabel lbConnUser = new KLabel(groupMain, SWT.RIGHT);
		lbConnUser.setFont(StyleFinal.SYS_FONT);
		lbConnUser.setText("帐号:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL|GridData.HORIZONTAL_ALIGN_END);
		lbConnUser.setLayoutData(gridData);
		
		txtConnUser = new Text(groupMain, SWT.BORDER);
		txtConnUser.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		gridData.widthHint = 140;
		txtConnUser.setLayoutData(gridData);

		KLabel lbConnPassword = new KLabel(groupMain, SWT.RIGHT);
		lbConnPassword.setFont(StyleFinal.SYS_FONT);
		lbConnPassword.setText("密码:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL|GridData.HORIZONTAL_ALIGN_END);
		lbConnPassword.setLayoutData(gridData);
		
		txtConnPassword = new Text(groupMain, SWT.BORDER|SWT.PASSWORD);
		txtConnPassword.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtConnPassword.setLayoutData(gridData);
		
		Label lbCenterUrl = new Label(groupMain, SWT.RIGHT);
		lbCenterUrl.setFont(StyleFinal.SYS_FONT);
		lbCenterUrl.setText("中心通信地址:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		lbCenterUrl.setLayoutData(gridData);
		
		txtCenterUrl = new Text(groupMain, SWT.BORDER|SWT.PASSWORD);
		txtCenterUrl.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtCenterUrl.setLayoutData(gridData);
		
		initData();
		cConnClass.forceFocus();
		callMethod.bindEnterEvent(this, cConnClass, txtConnUrl, "");
		callMethod.bindEnterEvent(this, txtConnUrl, txtConnDbname, "");
		callMethod.bindEnterEvent(this, txtConnDbname, txtConnUser, "");
		callMethod.bindEnterEvent(this, txtConnUser, txtConnPassword, "");
		callMethod.bindEnterEvent(this, txtConnPassword, txtCenterUrl, "");
		callMethod.bindEnterEvent(this, txtCenterUrl, null, "");
		return compMain;
	}
	
	private void initData(){
		if (CommFinal.OPER_TYPE_UPDATE.equals(operType)){
			SamOrganize samOrganize = (SamOrganize) gridView.getSelection();
			if (null != samOrganize && !"".equals(samOrganize.getOrganizeSeq())){
				cConnClass.setText(samOrganize.getConnClass());
				txtConnUrl.setText(samOrganize.getConnUrl());
				txtConnDbname.setText(samOrganize.getConnDbname());
				txtConnUser.setText(samOrganize.getConnUser());
				txtConnPassword.setText(samOrganize.getConnPassword());
				txtCenterUrl.setText(samOrganize.getCenterUrl());
			}
		}
	}

	protected void buttonPressed(int buttonId){
		try {
			if(1 == buttonId){
				SamOrganize organize = validData();
				if (null != organize){
					ISamOrganize iSamOrganize = new ImpSamOrganize();
					if (CommFinal.OPER_TYPE_ADD.equals(operType)){
						SamOrganize newOrganize = iSamOrganize.insert(organize,CommFinal.initConfig());
						gridView.addRow(newOrganize);
						clearData();
					} else if(CommFinal.OPER_TYPE_UPDATE.equals(operType)){
						iSamOrganize.update(organize,CommFinal.initConfig());
						gridView.updateRow(gridView.getSelectionIndex(), organize);
						close();
					}
				}
			} else if (0 == buttonId) {
				close();
			}
		} catch (Exception e) {
			LogUtil.operLog(e,"E",true,true);
		}
	}
	
	private SamOrganize validData(){
		String currTime = DateUtils.getNow(DateUtils.FORMAT_LONG);
		SamOrganize samOrganize = new SamOrganize();
		if (CommFinal.OPER_TYPE_ADD.equals(operType)){
			samOrganize.setCreateTime(currTime);
		}else if (CommFinal.OPER_TYPE_UPDATE.equals(operType)){
			samOrganize = (SamOrganize) gridView.getSelection();
		}
		samOrganize.setUpdateTime(currTime);
		if (null == cConnClass.getText() || "".equals(cConnClass.getText())){
			MsgBox.warning(getParentShell(),"请选择数据库类型！");
			cConnClass.forceFocus();
			return null;
		}
		
		if (null == txtConnUrl.getText() || "".equals(txtConnUrl.getText())){
			MsgBox.warning(getParentShell(),"请输入连接地址！");
			txtConnUrl.forceFocus();
			return null;
		}
		
		if (null == txtConnDbname.getText() || "".equals(txtConnDbname.getText())){
			MsgBox.warning(getParentShell(),"请输入连接数据库名！");
			txtConnDbname.forceFocus();
			return null;
		}
		
		if (null == txtConnUser.getText() || "".equals(txtConnUser.getText())){
			MsgBox.warning(getParentShell(),"请输入登录用户！");
			txtConnUser.forceFocus();
			return null;
		}
		
		if (null == txtConnPassword.getText() || "".equals(txtConnPassword.getText())){
			MsgBox.warning(getParentShell(),"请输入登录密码！");
			txtConnPassword.forceFocus();
			return null;
		}
		
		samOrganize.setConnClass(cConnClass.getText());
		samOrganize.setConnUrl(txtConnUrl.getText());
		samOrganize.setConnDbname(txtConnDbname.getText());
		samOrganize.setConnUser(txtConnUser.getText());
		samOrganize.setConnPassword(txtConnPassword.getText());
		samOrganize.setCenterUrl(txtCenterUrl.getText());
		return samOrganize;
	}
	
	private void clearData(){
		cConnClass.setText("");
		txtConnUrl.setText("");
		txtConnDbname.setText("");
		txtConnUser.setText("");
		txtConnPassword.setText("");
		txtCenterUrl.setText("");
	}
	
}
