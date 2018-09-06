package com.zhima.frame.ui.sam;

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
import com.zhima.frame.drop.DropOrganize;
import com.zhima.frame.model.SamOrganize;
import com.zhima.frame.model.SamUser;
import com.zhima.util.DateUtils;
import com.zhima.util.ImageUtil;
import com.zhima.util.SWTResourceManager;
import com.zhima.util.log4j.LogUtil;
import com.zhima.widget.CImage;
import com.zhima.widget.KLabel;
import com.zhima.widget.MsgBox;
import com.zhima.widget.grid.GridView;

public class SamUserEditUi extends Dialog {
	
	private GridView tbUser;
	
	private String operType;
	
	/** 网点代码 */
	private DropOrganize dropOrganize;
	
	/** 用户名称 */
	private Text txtUserName;
	
	/** 用户代码 */
	private Text txtUserCode;
	
	/** 密码 */
	private Text txtPasswrod;
	
	/** 电话 */
	private Text txtTelephone;
	
	/** 邮箱 */
	private Text txtEmail;
	
	/** 图标 */
	private CImage lbUserIcon;
	
	/** 状态 */
	private Button statusYes;
	private Button statusNo;
	
	/** 备注 */
	private Text txtRemark;
	
	protected SamUserEditUi(Shell shell,GridView tbUser,String operType) {
		super(shell);
		this.tbUser = tbUser;
		this.operType = operType;
	}

	protected void configureShell(Shell shell){
		super.configureShell(shell);
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		shell.setBackground(StyleFinal.DIALOG_BACK_GROUND);
		shell.setText("用户设置-"+operType);
	}
	
	@Override
	protected Point getInitialLocation(Point initialSize) {
		Rectangle screenSize =  Display.getDefault().getClientArea();
		return new Point((screenSize.width - initialSize.x)/2, (screenSize.height - initialSize.y)/2);
	}
	
	@Override
	protected Point getInitialSize(){
        return new Point(400,350);
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
		groupMain.setText("用户信息");
		groupMain.setFont(StyleFinal.SYS_FONT);
		GridLayout gridLayout = new GridLayout(3,false);
		gridLayout.verticalSpacing=StyleFinal.VERTICAL_SPACING;
		groupMain.setLayout(gridLayout);
		
		FormData data = new FormData();
		data.top = new FormAttachment(0,5);
		data.left = new FormAttachment(0,5);
		data.right = new FormAttachment(100,-5);
		data.bottom = new FormAttachment(100);
		groupMain.setLayoutData(data);
		
		KLabel lbOrganize = new KLabel(groupMain, SWT.RIGHT);
		lbOrganize.setFont(StyleFinal.SYS_FONT);
		lbOrganize.setText("车站名称:");
		GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbOrganize.setLayoutData(gridData);
		
		dropOrganize = new DropOrganize(groupMain, SWT.BORDER);
		dropOrganize.initType("3");
		dropOrganize.droptext.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		gridData.horizontalSpan=2;
		dropOrganize.setLayoutData(gridData);
		
		KLabel lbUserName = new KLabel(groupMain, SWT.RIGHT);
		lbUserName.setFont(StyleFinal.SYS_FONT);
		lbUserName.setText("用户名称:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbUserName.setLayoutData(gridData);
		
		txtUserName = new Text(groupMain, SWT.BORDER);
		txtUserName.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtUserName.setLayoutData(gridData);
		
		
		lbUserIcon = new CImage(groupMain, SWT.RIGHT|SWT.BORDER);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_CENTER);
		gridData.verticalSpan = 4;
		gridData.widthHint=87;
		gridData.heightHint=105;
		lbUserIcon.setLayoutData(gridData);
		
		KLabel lbUserCode = new KLabel(groupMain, SWT.RIGHT);
		lbUserCode.setFont(StyleFinal.SYS_FONT);
		lbUserCode.setText("用户代码:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbUserCode.setLayoutData(gridData);
		
		txtUserCode = new Text(groupMain, SWT.BORDER);
		txtUserCode.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtUserCode.setLayoutData(gridData);
		
		Label lbPasswrod = new Label(groupMain, SWT.RIGHT);
		lbPasswrod.setFont(StyleFinal.SYS_FONT);
		lbPasswrod.setText("密码:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		lbPasswrod.setLayoutData(gridData);
		
		txtPasswrod = new Text(groupMain, SWT.BORDER|SWT.PASSWORD);
		txtPasswrod.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtPasswrod.setLayoutData(gridData);
		
		Label lbTelephone = new Label(groupMain, SWT.RIGHT);
		lbTelephone.setFont(StyleFinal.SYS_FONT);
		lbTelephone.setText("电话:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		lbTelephone.setLayoutData(gridData);
		
		txtTelephone = new Text(groupMain, SWT.BORDER);
		txtTelephone.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtTelephone.setLayoutData(gridData);
		
		Label lbEmail = new Label(groupMain, SWT.RIGHT);
		lbEmail.setFont(StyleFinal.SYS_FONT);
		lbEmail.setText("邮箱:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		lbEmail.setLayoutData(gridData);
		
		txtEmail = new Text(groupMain, SWT.BORDER);
		txtEmail.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.horizontalSpan=2;
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtEmail.setLayoutData(gridData);
			
		KLabel lbStatus = new KLabel(groupMain, SWT.RIGHT);
		lbStatus.setFont(StyleFinal.SYS_FONT);
		lbStatus.setText("用户状态:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbStatus.setLayoutData(gridData);
		
		Composite compStatus = new Composite(groupMain, SWT.NONE);
		compStatus.setLayout(new GridLayout(2,true));
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.horizontalSpan=2;
		compStatus.setLayoutData(gridData);
		
		statusYes = new Button(compStatus, SWT.RADIO);
		statusYes.setSelection(true);
		statusYes.setFont(StyleFinal.SYS_FONT);
		statusYes.setText("有效");
		gridData = new GridData(GridData.FILL_BOTH);
		gridData.horizontalAlignment = SWT.LEFT;
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		statusYes.setLayoutData(gridData);
		
		statusNo = new Button(compStatus, SWT.RADIO|SWT.CENTER);
		statusNo.setFont(StyleFinal.SYS_FONT);
		statusNo.setText("无效");
		gridData = new GridData(GridData.FILL_BOTH);
		gridData.horizontalAlignment = SWT.LEFT;
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		statusNo.setLayoutData(gridData);
		
		Label lbRemakr = new Label(groupMain, SWT.RIGHT);
		lbRemakr.setFont(StyleFinal.SYS_FONT);
		lbRemakr.setText("备注:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		lbRemakr.setLayoutData(gridData);
		
		txtRemark = new Text(groupMain, SWT.BORDER);
		txtRemark.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.horizontalSpan=2;
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtRemark.setLayoutData(gridData);
		initData();
		callMethod.bindEnterEvent(this, dropOrganize.droptext.txtShow, txtUserName, "");
		callMethod.bindEnterEvent(this, txtUserName, txtUserCode, "");
		callMethod.bindEnterEvent(this, txtUserCode, txtPasswrod, "");
		callMethod.bindEnterEvent(this, txtPasswrod, txtTelephone, "");
		callMethod.bindEnterEvent(this, txtTelephone, txtEmail, "");
		callMethod.bindEnterEvent(this, txtEmail, statusYes, "");
		callMethod.bindEnterEvent(this, statusYes, txtRemark, "");
		callMethod.bindEnterEvent(this, statusNo, txtRemark, "");
		callMethod.bindEnterEvent(this, txtRemark, null, "");
		return compMain;
	}
	
	private void initData(){
		dropOrganize.droptext.setValue(CommFinal.organize.getOrganizeSeq());
		dropOrganize.initType("3");
		//dropOrganize.droptext.setEnabled(false);
		if (CommFinal.OPER_TYPE_UPDATE.equals(operType)){
			SamUser samUser = (SamUser) tbUser.getSelection();
			if (null != samUser && !"".equals(samUser.getUserSeq())){
				if (null != samUser.getServiceSeq() && samUser.getServiceSeq().length()>0){
					dropOrganize.droptext.setValue(samUser.getServiceSeq());
				}else{
					dropOrganize.droptext.setValue(samUser.getOrganizeSeq());
				}
				txtUserName.setText(samUser.getUserName());
				lbUserIcon.setImage(ImageUtil.Base64ToBlob(samUser.getUserIcon()) ,87,105);
				txtUserCode.setText(samUser.getUserCode());
				txtPasswrod.setText(samUser.getPassword());
				txtTelephone.setText(samUser.getTelephone());
				txtEmail.setText(samUser.getEmail());
				if ("1".equals(samUser.getStatus())){
					statusYes.setSelection(true);
					statusNo.setSelection(false);
				} else{
					statusYes.setSelection(false);
					statusNo.setSelection(true);
				}
				txtRemark.setText(samUser.getRemark());
			}
		}
		dropOrganize.droptext.txtShow.forceFocus();
		dropOrganize.droptext.txtShow.selectAll();
	}
	
	protected void buttonPressed(int buttonId){
		try {
			if(1 == buttonId){
				/**
				 * 确定
				 */
				SamUser samUser = validData();
				if (null != samUser){
					ISamUser iSamUser = new ImpSamUser();
					if (CommFinal.OPER_TYPE_ADD.equals(operType)){
						SamUser newSamUser = iSamUser.insert(samUser,CommFinal.initConfig());
						tbUser.addRow(newSamUser);
					} else if(CommFinal.OPER_TYPE_UPDATE.equals(operType)){
						iSamUser.update(samUser,CommFinal.initConfig());
						tbUser.updateRow(tbUser.getSelectionIndex(), samUser);
					}
					close();
				}
			} else if (0 == buttonId) {
				/**
				 * 取消
				 */
				close();
			}
		} catch (UserBusinessException e) {
			LogUtil.operLog(e,"E",true,true);
		} catch (Exception e) {
			LogUtil.operLog(e,"E",true,true);
		}
	}
	
	private SamUser validData(){
		String currTime = DateUtils.getNow(DateUtils.FORMAT_LONG);
		SamUser samUser = new SamUser();
		if (CommFinal.OPER_TYPE_ADD.equals(operType)){
			samUser.setCreateTime(currTime);
			samUser.setOrganizeSeq(CommFinal.organize.getOrganizeSeq());
		}else if (CommFinal.OPER_TYPE_UPDATE.equals(operType)){
			samUser = (SamUser) tbUser.getSelection();
		}
		samUser.setUpdateTime(currTime);
		if (null != dropOrganize.droptext.getValue() && !"".equals(dropOrganize.droptext.getValue())){
			SamOrganize organize = (SamOrganize) dropOrganize.droptext.getObject();
			if ("1".equals(organize.getIfService())){
				samUser.setServiceSeq(dropOrganize.droptext.getValue());
				samUser.setOrganizeName(dropOrganize.droptext.getText());
			}else{
				samUser.setOrganizeSeq(dropOrganize.droptext.getValue());
				samUser.setOrganizeName(dropOrganize.droptext.getText());
			}
			
		}else{
			MsgBox.warning(getParentShell(),"请输入或选择网点名称！");
			dropOrganize.droptext.txtShow.forceFocus();
			dropOrganize.droptext.txtShow.selectAll();
			return null;
		}
		if (null != txtUserName.getText() && !"".equals(txtUserName.getText())){
			samUser.setUserName(txtUserName.getText());
		}else{
			MsgBox.warning(getParentShell(),"请输入用户名称！");
			txtUserName.forceFocus();
			return null;
		}
		samUser.setUserIcon(ImageUtil.blobToBase64(lbUserIcon.getBlob()));	
		if (null != txtUserCode.getText() && !"".equals(txtUserCode.getText())){
			samUser.setUserCode(txtUserCode.getText());
		}else{
			MsgBox.warning(getParentShell(),"请输入用户代码！");
			txtUserCode.forceFocus();
			return null;
		}
		samUser.setPassword(txtPasswrod.getText());
		samUser.setTelephone(txtTelephone.getText());
		samUser.setEmail(txtEmail.getText());
		
		if (statusYes.getSelection() == true){
			samUser.setStatus("1");
		}else{
			samUser.setStatus("0");
		}
		samUser.setRemark(txtRemark.getText());
		
		return samUser;
	}
	
}
