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
import com.zhima.frame.action.sam.ISamGroup;
import com.zhima.frame.action.sam.impl.ImpSamGroup;
import com.zhima.frame.model.SamGroup;
import com.zhima.util.DateUtils;
import com.zhima.util.ImageUtil;
import com.zhima.util.SWTResourceManager;
import com.zhima.util.log4j.LogUtil;
import com.zhima.widget.CImage;
import com.zhima.widget.KLabel;
import com.zhima.widget.MsgBox;
import com.zhima.widget.grid.GridView;

public class SamGroupEditUi extends Dialog {
	
	private GridView tbGroup;
	
	private String operType;
	
	/** 角色名称 */
	private Text txtGroupName;
	
	/** 角色代码 */
	private Text txtGroupCode;
	
	/** 图标 */
	private CImage lbGroupIcon;
	
	/** 状态 */
	private Button statusYes;
	private Button statusNo;
	
	/** 备注 */
	private Text txtRemark;
	
	public SamGroupEditUi(Shell shell,GridView tbGroup,String operType) {
		super(shell);
		this.tbGroup = tbGroup;
		this.operType = operType;
	}

	protected void configureShell(Shell shell){
		super.configureShell(shell);
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		shell.setBackground(StyleFinal.DIALOG_BACK_GROUND);
		shell.setText("角色设置-"+operType);
	}
	
	@Override
	protected Point getInitialLocation(Point initialSize) {
		Rectangle screenSize = Display.getDefault().getClientArea();
		return new Point((screenSize.width - initialSize.x)/2, (screenSize.height - initialSize.y)/2);
	}
	
	@Override
	protected Point getInitialSize(){
        return new Point(315,235);
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
		groupMain.setText("角色信息");
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
		
		KLabel lbGroupName = new KLabel(groupMain, SWT.RIGHT);
		lbGroupName.setFont(StyleFinal.SYS_FONT);
		lbGroupName.setText("角色名称:");
		GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbGroupName.setLayoutData(gridData);
		
		txtGroupName = new Text(groupMain, SWT.BORDER);
		txtGroupName.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtGroupName.setLayoutData(gridData);

		lbGroupIcon = new CImage(groupMain, SWT.RIGHT|SWT.BORDER);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_CENTER);
		gridData.verticalSpan=2;
		gridData.widthHint=48;
		gridData.heightHint=48;
		lbGroupIcon.setLayoutData(gridData);
		
		KLabel lbGroupCode = new KLabel(groupMain, SWT.RIGHT);
		lbGroupCode.setFont(StyleFinal.SYS_FONT);
		lbGroupCode.setText("角色代码:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbGroupCode.setLayoutData(gridData);
		
		txtGroupCode = new Text(groupMain, SWT.BORDER);
		txtGroupCode.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtGroupCode.setLayoutData(gridData);
		
		KLabel lbStatus = new KLabel(groupMain, SWT.RIGHT);
		lbStatus.setFont(StyleFinal.SYS_FONT);
		lbStatus.setText("角色状态:");
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
		
		Label lbRemark = new Label(groupMain, SWT.RIGHT);
		lbRemark.setFont(StyleFinal.SYS_FONT);
		lbRemark.setText("备注:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		lbRemark.setLayoutData(gridData);
		
		txtRemark = new Text(groupMain, SWT.BORDER);
		txtRemark.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.horizontalSpan=2;
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtRemark.setLayoutData(gridData);
		initData();
		callMethod.bindEnterEvent(this, txtGroupName, txtGroupCode, "");
		callMethod.bindEnterEvent(this, txtGroupCode, statusYes, "");
		callMethod.bindEnterEvent(this, statusYes, txtRemark, "");
		callMethod.bindEnterEvent(this, statusNo, txtRemark, "");
		callMethod.bindEnterEvent(this, txtRemark, null, "");
		return compMain;
	}
	
	private void initData(){
		if (CommFinal.OPER_TYPE_UPDATE.equals(operType)){
			SamGroup samGroup = (SamGroup) tbGroup.getSelection();
			if (null != samGroup && !"".equals(samGroup.getGroupSeq())){
				txtGroupName.setText(samGroup.getGroupName());
				txtGroupName.selectAll();
				txtGroupCode.setText(samGroup.getGroupCode());
				lbGroupIcon.setImage(ImageUtil.Base64ToBlob(samGroup.getGroupIcon()),48,48);
				if ("1".equals(samGroup.getStatus())){
					statusYes.setSelection(true);
					statusNo.setSelection(false);
				} else{
					statusYes.setSelection(false);
					statusNo.setSelection(true);
				}
				txtRemark.setText(samGroup.getRemark());
			}
		}
	}
	
	protected void buttonPressed(int buttonId){
		try {
			if(1 == buttonId){
				/**
				 * 确定
				 */
				SamGroup samGroup = validData();
				if (null != samGroup){
					ISamGroup iSamGroup = new ImpSamGroup();
					if (CommFinal.OPER_TYPE_ADD.equals(operType)){
						SamGroup newSamGroup = iSamGroup.insert(samGroup,CommFinal.initConfig());
						tbGroup.addRow(newSamGroup);
					} else if(CommFinal.OPER_TYPE_UPDATE.equals(operType)){
						iSamGroup.update(samGroup,CommFinal.initConfig());
						tbGroup.updateRow(tbGroup.getSelectionIndex(), samGroup);
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
	
	private SamGroup validData(){
		String currTime = DateUtils.getNow(DateUtils.FORMAT_LONG);
		SamGroup samGroup = new SamGroup();
		samGroup.setOrganizeSeq(CommFinal.organize.getOrganizeSeq());
		if (CommFinal.OPER_TYPE_ADD.equals(operType)){
			samGroup.setCreateTime(currTime);
		}else if (CommFinal.OPER_TYPE_UPDATE.equals(operType)){
			samGroup = (SamGroup) tbGroup.getSelection();
		}
		samGroup.setUpdateTime(currTime);
		if (null != txtGroupName.getText() && !"".equals(txtGroupName.getText())){
			samGroup.setGroupName(txtGroupName.getText());
		}else{
			MsgBox.warning(getParentShell(),"请输入角色名称！");
			txtGroupName.forceFocus();
			return null;
		}
		samGroup.setGroupCode(txtGroupCode.getText());
		samGroup.setGroupIcon(ImageUtil.blobToBase64(lbGroupIcon.getBlob()));	
		if (statusYes.getSelection() == true){
			samGroup.setStatus("1");
		}else{
			samGroup.setStatus("0");
		}
		samGroup.setRemark(txtRemark.getText());
		
		return samGroup;
	}
	
}
