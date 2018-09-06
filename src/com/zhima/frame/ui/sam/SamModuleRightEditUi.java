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
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.frame.action.sam.ISamModuleRight;
import com.zhima.frame.action.sam.impl.ImpSamModuleRight;
import com.zhima.frame.model.SamModule;
import com.zhima.frame.model.SamModuleRight;
import com.zhima.util.DateUtils;
import com.zhima.util.ImageUtil;
import com.zhima.util.SWTResourceManager;
import com.zhima.util.log4j.LogUtil;
import com.zhima.widget.CImage;
import com.zhima.widget.KLabel;
import com.zhima.widget.MsgBox;
import com.zhima.widget.TextVerifyListener;
import com.zhima.widget.grid.GridView;

public class SamModuleRightEditUi extends Dialog {
	
	private SamModule samModule;
	
	private GridView tbRight;
	
	private String operType;
	
	/** 父项节点 */
	private Text txtModuleName;
	
	/** 权限类型 */
	private CCombo comRightType;
	
	/** 图标 */
	private CImage lbRightIcon;
	
	/** 权限名称 */
	private Text txtRightName;
	
	/** 方法 */
	private Text txtRightEvent;
	
	/** 快捷键 */
	private Text txtShortcutKey;
	
	/** 序号 */
	private Text txtSn;
	
	/** 描述 */
	private Text txtRightDesc;
	
	/** 记录日志  */
	private Button operLogYes;
	private Button operLogNo;
	
	/** 状态 */
	private Button statusYes;
	private Button statusNo;
	
	/** 备注 */
	private Text txtRemark;
	
	protected SamModuleRightEditUi(Shell shell,SamModule samModule,GridView tbRight,String operType) {
		super(shell);
		this.samModule = samModule;
		this.tbRight = tbRight;
		this.operType = operType;
	}

	protected void configureShell(Shell shell){
		super.configureShell(shell);
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		shell.setBackground(StyleFinal.DIALOG_BACK_GROUND);
		shell.setText("权限设置-"+operType);
	}
	
	@Override
	protected Point getInitialLocation(Point initialSize) {
		Rectangle screenSize =  Display.getDefault().getClientArea();
		return new Point((screenSize.width - initialSize.x)/2, (screenSize.height - initialSize.y)/2);
	}
	
	@Override
	protected Point getInitialSize(){
        return new Point(488,335);
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
		
		Group groupMain = new Group(compMain,SWT.FILL);
		groupMain.setText("功能权限信息");
		groupMain.setFont(StyleFinal.SYS_FONT);
		GridLayout gridLayout = new GridLayout(4,false);
		gridLayout.verticalSpacing=StyleFinal.VERTICAL_SPACING;
		groupMain.setLayout(gridLayout);
		
		FormData data = new FormData();
		data.top = new FormAttachment(0,5);
		data.left = new FormAttachment(0,5);
		data.right = new FormAttachment(100,-5);
		data.bottom = new FormAttachment(100);
		groupMain.setLayoutData(data);
		
		KLabel lbModuleName = new KLabel(groupMain, SWT.RIGHT);
		lbModuleName.setFont(StyleFinal.SYS_FONT);
		lbModuleName.setText("功能名称:");
		GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbModuleName.setLayoutData(gridData);
		
		txtModuleName = new Text(groupMain, SWT.BORDER|SWT.READ_ONLY);
		txtModuleName.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.horizontalSpan=3;
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtModuleName.setLayoutData(gridData);
		
		KLabel lbRightType = new KLabel(groupMain, SWT.RIGHT);
		lbRightType.setFont(StyleFinal.SYS_FONT);
		lbRightType.setText("类型:");
		gridData =new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbRightType.setLayoutData(gridData);
		
		comRightType = new CCombo(groupMain, SWT.BORDER|SWT.READ_ONLY);
		comRightType.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		comRightType.setLayoutData(gridData);
		comRightType.setItems(new String[] {"button", "text", "right"});
		comRightType.forceFocus();
		
		Label lbImage = new Label(groupMain, SWT.RIGHT);
		lbImage.setFont(StyleFinal.SYS_FONT);
		lbImage.setText("图标:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.widthHint = 80;
		lbImage.setLayoutData(gridData);
		
		lbRightIcon = new CImage(groupMain, SWT.RIGHT|SWT.BORDER);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_CENTER);
		gridData.widthHint=32;
		gridData.heightHint=32;
		lbRightIcon.setLayoutData(gridData);
		
		KLabel lbRightName = new KLabel(groupMain, SWT.RIGHT);
		lbRightName.setFont(StyleFinal.SYS_FONT);
		lbRightName.setText("权限名称:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbRightName.setLayoutData(gridData);
		
		txtRightName = new Text(groupMain, SWT.BORDER);
		txtRightName.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.widthHint = 135;
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtRightName.setLayoutData(gridData);
		
		KLabel lbRightEvent = new KLabel(groupMain, SWT.RIGHT);
		lbRightEvent.setFont(StyleFinal.SYS_FONT);
		lbRightEvent.setText("事件:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbRightEvent.setLayoutData(gridData);
		
		txtRightEvent = new Text(groupMain, SWT.BORDER);
		txtRightEvent.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtRightEvent.setLayoutData(gridData);
		
		Label lbShortcutKey = new Label(groupMain, SWT.RIGHT);
		lbShortcutKey.setFont(StyleFinal.SYS_FONT);
		lbShortcutKey.setText("快捷键:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		lbShortcutKey.setLayoutData(gridData);
		
		txtShortcutKey = new Text(groupMain, SWT.BORDER);
		txtShortcutKey.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtShortcutKey.setLayoutData(gridData);
		
		Label lbSn = new Label(groupMain, SWT.RIGHT);
		lbSn.setFont(StyleFinal.SYS_FONT);
		lbSn.setText("序号:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		lbSn.setLayoutData(gridData);
		
		txtSn = new Text(groupMain, SWT.BORDER);
		txtSn.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtSn.setLayoutData(gridData);
		txtSn.addVerifyListener(new TextVerifyListener(1));
		
		Label lbRightDesc = new Label(groupMain, SWT.RIGHT);
		lbRightDesc.setFont(StyleFinal.SYS_FONT);
		lbRightDesc.setText("描述:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		lbRightDesc.setLayoutData(gridData);
		
		txtRightDesc = new Text(groupMain, SWT.BORDER);
		txtRightDesc.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.horizontalSpan = 3;
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtRightDesc.setLayoutData(gridData);
		
		KLabel lbOperLog = new KLabel(groupMain, SWT.RIGHT);
		lbOperLog.setFont(StyleFinal.SYS_FONT);
		lbOperLog.setText("记录日志:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbOperLog.setLayoutData(gridData);
		
		Composite compSeparate = new Composite(groupMain, SWT.NONE);
		compSeparate.setLayout(new GridLayout(2,true));
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		compSeparate.setLayoutData(gridData);
		
		operLogYes = new Button(compSeparate, SWT.RADIO);
		operLogYes.setFont(StyleFinal.SYS_FONT);
		operLogYes.setText("是");
		gridData = new GridData(GridData.FILL_BOTH);
		gridData.horizontalAlignment = SWT.LEFT;
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		operLogYes.setLayoutData(gridData);
		
		operLogNo = new Button(compSeparate, SWT.RADIO|SWT.CENTER);
		operLogNo.setSelection(true);
		operLogNo.setFont(StyleFinal.SYS_FONT);
		operLogNo.setText("否");
		gridData = new GridData(GridData.FILL_BOTH);
		gridData.horizontalAlignment = SWT.LEFT;
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		operLogNo.setLayoutData(gridData);
		
		KLabel lbStatus = new KLabel(groupMain, SWT.RIGHT);
		lbStatus.setFont(StyleFinal.SYS_FONT);
		lbStatus.setText("状态:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbStatus.setLayoutData(gridData);
		
		Composite compStatus = new Composite(groupMain, SWT.NONE);
		compStatus.setLayout(new GridLayout(2,true));
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
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
		gridData.horizontalSpan = 3;
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtRemark.setLayoutData(gridData);
		initData();
		callMethod.bindEnterEvent(this, txtModuleName, comRightType, "");
		callMethod.bindEnterEvent(this, comRightType, txtRightName, "");
		callMethod.bindEnterEvent(this, txtRightName, txtRightEvent, "");
		callMethod.bindEnterEvent(this, txtRightEvent, txtShortcutKey, "");
		callMethod.bindEnterEvent(this, txtShortcutKey, txtSn, "");
		callMethod.bindEnterEvent(this, txtSn, txtRightDesc, "");
		callMethod.bindEnterEvent(this, txtRightDesc, operLogYes, "");
		callMethod.bindEnterEvent(this, operLogYes, statusYes, "");
		callMethod.bindEnterEvent(this, operLogNo, statusYes, "");
		callMethod.bindEnterEvent(this, statusYes, txtRemark, "");
		callMethod.bindEnterEvent(this, statusNo, txtRemark, "");
		callMethod.bindEnterEvent(this, txtRemark, null, "");
		return compMain;
	}
	
	private void initData(){
		txtModuleName.setText(samModule.getModuleName());
		if (CommFinal.OPER_TYPE_UPDATE.equals(operType)){
			SamModuleRight samModuleRight = (SamModuleRight) tbRight.getSelection();
			if (null != samModule && !"".equals(samModuleRight.getRightSeq())){
				if ("button".equals(samModuleRight.getRightType().toLowerCase())){
					comRightType.select(0);
				}else if ("text".equals(samModuleRight.getRightType().toLowerCase())){
					comRightType.select(1);
				}else if ("right".equals(samModuleRight.getRightType().toLowerCase())){
					comRightType.select(2);
				}
				lbRightIcon.setImage(ImageUtil.Base64ToBlob(samModuleRight.getRightIcon()),32,32);
				txtRightName.setText(samModuleRight.getRightName());
				txtRightEvent.setText(samModuleRight.getRightMethod());
				txtShortcutKey.setText(samModuleRight.getShortcutKey());
				txtSn.setText(samModuleRight.getSn().toString());
				txtRightDesc.setText(samModuleRight.getRightDesc());
				if ("1".equals(samModuleRight.getOperLog())){
					operLogYes.setSelection(true);
					operLogNo.setSelection(false);
				} else{
					operLogYes.setSelection(false);
					operLogNo.setSelection(true);
				}
				if ("1".equals(samModuleRight.getStatus())){
					statusYes.setSelection(true);
					statusNo.setSelection(false);
				} else{
					statusYes.setSelection(false);
					statusNo.setSelection(true);
				}
				txtRemark.setText(samModuleRight.getRemark());
			}
		}
		
	}
	
	protected void buttonPressed(int buttonId){
		try {
			if(1 == buttonId){
				/**
				 * 确定
				 */
				SamModuleRight samModuleRight = validData();
				if (null != samModuleRight){
					ISamModuleRight iSamModuleRight = new ImpSamModuleRight();
					if (CommFinal.OPER_TYPE_ADD.equals(operType)){
						SamModuleRight newSamModuleRight = iSamModuleRight.insert(samModuleRight,CommFinal.initConfig());
						tbRight.addRow(newSamModuleRight);
						clearData();
					} else if(CommFinal.OPER_TYPE_UPDATE.equals(operType)){
						iSamModuleRight.update(samModuleRight,CommFinal.initConfig());
						tbRight.updateRow(tbRight.getSelectionIndex(), samModuleRight);
						close();
					}
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

	private SamModuleRight validData(){
		String currTime = DateUtils.getNow(DateUtils.FORMAT_LONG);
		SamModuleRight samModuleRight = new SamModuleRight();
		samModuleRight.setModuleSeq(samModule.getModuleSeq());
		if (CommFinal.OPER_TYPE_ADD.equals(operType)){
			samModuleRight.setCreateTime(currTime);
		}else if (CommFinal.OPER_TYPE_UPDATE.equals(operType)){
			samModuleRight = (SamModuleRight) tbRight.getSelection();
		}
		samModuleRight.setUpdateTime(currTime);
		if (comRightType.getSelectionIndex() != -1){
			samModuleRight.setRightType(comRightType.getText());
		}else{
			MsgBox.warning(getParentShell(),"请选择权限类型！");
			comRightType.forceFocus();
			return null;
		}
		samModuleRight.setRightIcon(ImageUtil.blobToBase64(lbRightIcon.getBlob()));
	
		if (null != txtRightName.getText() && !"".equals(txtRightName.getText())){
			samModuleRight.setRightName(txtRightName.getText());
		}else{
			MsgBox.warning(getParentShell(),"请输入权限名称！");
			txtRightName.forceFocus();
			return null;
		}
		
		if (null != txtRightEvent.getText() && !"".equals(txtRightEvent.getText())){
			samModuleRight.setRightMethod(txtRightEvent.getText());
		}else{
			MsgBox.warning(getParentShell(),"请输入执行方法！");
			txtRightEvent.forceFocus();
			return null;
		}

		samModuleRight.setShortcutKey(txtShortcutKey.getText());
		if (!"".equals(txtSn.getText())){
			samModuleRight.setSn(Integer.valueOf(txtSn.getText()));
		}else{
			samModuleRight.setSn(0);
		}
		samModuleRight.setRightDesc(txtRightDesc.getText());

		if (operLogYes.getSelection() == true){
			samModuleRight.setOperLog("1");
		}else{
			samModuleRight.setOperLog("0");
		}
		if (statusYes.getSelection() == true){
			samModuleRight.setStatus("1");
		}else{
			samModuleRight.setStatus("0");
		}
		samModuleRight.setRemark(txtRemark.getText());
		
		return samModuleRight;
	}
	
	private void clearData() {
		comRightType.setText("");
		comRightType.clearSelection();
		lbRightIcon.setImage(null, 32, 32);
		txtRightName.setText("");
		txtRightEvent.setText("");
		txtShortcutKey.setText("");
		txtSn.setText("");
		txtRightDesc.setText("");
		operLogYes.setSelection(false);
		operLogNo.setSelection(true);
		statusYes.setSelection(true);
		statusNo.setSelection(false);
		txtRemark.setText("");
		comRightType.forceFocus();
	}
	
}
