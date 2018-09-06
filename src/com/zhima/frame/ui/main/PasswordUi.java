
package com.zhima.frame.ui.main;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
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
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.frame.action.sam.ISamUser;
import com.zhima.frame.action.sam.impl.ImpSamUser;
import com.zhima.frame.model.SamUser;
import com.zhima.util.DateUtils;
import com.zhima.util.SWTResourceManager;
import com.zhima.util.log4j.LogUtil;
import com.zhima.widget.MsgBox;

public class PasswordUi extends Dialog {
	private Text txtUser;
	private Text txtOldPwd;
	private Text txtNewPwd;
	private Text txtTestPwd;
	private SamUser samUser = CommFinal.user;
	
	protected PasswordUi(Shell shell) {
		super(shell);
	}

	protected void configureShell(Shell shell){
		super.configureShell(shell);
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		shell.setBackground(StyleFinal.DIALOG_BACK_GROUND);
		shell.setText("修改密码");
	}
	
	@Override
	protected Point getInitialLocation(Point initialSize) {
		Rectangle screenSize =  Display.getDefault().getClientArea();
		return new Point((screenSize.width - initialSize.x)/2, (screenSize.height - initialSize.y)/2);
	}
	
	@Override
	protected Point getInitialSize(){
        return new Point(250,215);
    }
	
	protected void createButtonsForButtonBar(Composite parent){
		GridData btnData = new GridData(GridData.HORIZONTAL_ALIGN_CENTER);
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
		CallMethod callMethod = new CallMethod();
		compMain.setLayout(new FormLayout());
		Group groupMain = new Group(compMain,SWT.SHADOW_IN);
		groupMain.setLayout(new GridLayout(2, false));
		groupMain.setText("密码信息");
		groupMain.setFont(StyleFinal.SYS_FONT);
		FormData data = new FormData();
		data.top = new FormAttachment(0);
		data.left = new FormAttachment(0);
		data.right = new FormAttachment(100);
		data.bottom = new FormAttachment(100);
		groupMain.setLayoutData(data);
		
		Label lbUser = new Label(groupMain, SWT.NONE|SWT.RIGHT);
		lbUser.setAlignment(SWT.RIGHT);
		lbUser.setText("用户帐号:");
		lbUser.setFont(StyleFinal.SYS_FONT);
		GridData gridData= new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbUser.setLayoutData(gridData);

		txtUser = new Text(groupMain, SWT.BORDER|SWT.READ_ONLY);
		txtUser.setFont(StyleFinal.SYS_FONT);
		gridData= new GridData(GridData.FILL_HORIZONTAL);
		txtUser.setLayoutData(gridData);
		
		Label lbOldPwd = new Label(groupMain, SWT.NONE|SWT.RIGHT);
		lbOldPwd.setAlignment(SWT.RIGHT);
		lbOldPwd.setText("原始密码:");
		lbOldPwd.setFont(StyleFinal.SYS_FONT);
		gridData= new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbOldPwd.setLayoutData(gridData);
		
		txtOldPwd = new Text(groupMain, SWT.BORDER|SWT.PASSWORD);
		txtOldPwd.setFont(StyleFinal.SYS_FONT);
		gridData= new GridData(GridData.FILL_HORIZONTAL);
		txtOldPwd.setLayoutData(gridData);
		txtOldPwd.setFocus();
		
		Label lbNewPwd = new Label(groupMain, SWT.NONE|SWT.RIGHT);
		lbNewPwd.setAlignment(SWT.RIGHT);
		lbNewPwd.setText("新密码:");
		lbNewPwd.setFont(StyleFinal.SYS_FONT);
		gridData= new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbNewPwd.setLayoutData(gridData);
		
		txtNewPwd = new Text(groupMain, SWT.BORDER|SWT.PASSWORD);
		txtNewPwd.setFont(StyleFinal.SYS_FONT);
		gridData= new GridData(GridData.FILL_HORIZONTAL);
		txtNewPwd.setLayoutData(gridData);
		
		Label lbTestPwd = new Label(groupMain, SWT.NONE|SWT.RIGHT);
		lbTestPwd.setAlignment(SWT.RIGHT);
		lbTestPwd.setText("密码验证:");
		lbTestPwd.setFont(StyleFinal.SYS_FONT);
		gridData= new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbTestPwd.setLayoutData(gridData);
		
		txtTestPwd = new Text(groupMain, SWT.BORDER|SWT.PASSWORD);
		txtTestPwd.setFont(StyleFinal.SYS_FONT);
		gridData= new GridData(GridData.FILL_HORIZONTAL);
		txtTestPwd.setLayoutData(gridData);
		
		initValue();
		callMethod.bindEnterEvent(this, txtUser, txtOldPwd, "");
		callMethod.bindEnterEvent(this, txtOldPwd, txtNewPwd, "");
		callMethod.bindEnterEvent(this, txtNewPwd, txtTestPwd, "");
		return compMain;
	}

	protected void buttonPressed(int buttonId){
		/**
		 * 确定
		 */
		if(1 == buttonId){
			try {
				if (!txtOldPwd.getText().equals(samUser.getPassword())){
					MsgBox.warning(getParentShell(), "原始密码不正确。");
					txtOldPwd.setFocus();
					txtOldPwd.selectAll();
					return;
				}
				
				if (!txtNewPwd.getText().equals(txtTestPwd.getText())){
					MsgBox.warning(getParentShell(), "密码验证不正确，请重新设置新密码。");
					txtNewPwd.setText("");
					txtTestPwd.setText("");
					txtNewPwd.setFocus();
					return;
				}
				if (!txtOldPwd.getText().equals(txtNewPwd.getText())){
					String curTime = DateUtils.getNow(DateUtils.FORMAT_LONG);
					CommFinal.user.setPassword(txtNewPwd.getText());
					CommFinal.user.setUpdateTime(curTime);
					ISamUser iSamUser = new ImpSamUser();
					iSamUser.update(CommFinal.user,CommFinal.initConfig());
				}
				close();
			} catch (UserBusinessException e) {
				LogUtil.operLog(e,"E",true,"修改用户密码失败。");
			}
		} 
		/**
		 * 取消
		 */
		else if (0 == buttonId) {
			close();
		}
	}
	
	/**
	 * initValue方法描述：窗口初始值
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-7 上午10:37:41
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 */
	private void initValue(){
		txtUser.setText(samUser.getUserCode()+"|"+samUser.getUserName());
	}
	
	
}
