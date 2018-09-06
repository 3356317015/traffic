package com.zhima.traffic.ui.epd;

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
import com.zhima.traffic.action.epd.IEpdCheckgate;
import com.zhima.traffic.action.epd.impl.ImpEpdCheckgate;
import com.zhima.traffic.model.EpdCheckgate;
import com.zhima.util.DateUtils;
import com.zhima.util.SWTResourceManager;
import com.zhima.util.log4j.LogUtil;
import com.zhima.widget.KLabel;
import com.zhima.widget.MsgBox;
import com.zhima.widget.grid.GridView;

public class EpdCheckgateEditUi extends Dialog {
	private GridView gridView;
	private String operType;
	
	private Text txtCheckCode;
	private Text txtCheckName;
	private Text txtRemark;

	protected EpdCheckgateEditUi(Shell shell,GridView gridView,String operType) {
		super(shell);
		this.gridView = gridView;
		this.operType = operType;
	}

	protected void configureShell(Shell shell){
		super.configureShell(shell);
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		shell.setBackground(StyleFinal.DIALOG_BACK_GROUND);
		shell.setText("检票口设置-"+operType);
	}
	
	@Override
	protected Point getInitialLocation(Point initialSize) {
		Rectangle screenSize =  Display.getDefault().getClientArea();
		return new Point((screenSize.width - initialSize.x)/2, (screenSize.height - initialSize.y)/2);
	}
	
	@Override
	protected Point getInitialSize(){
        return new Point(315,200);
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
		groupMain.setText("检票口信息");
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
		
		KLabel lbCheckCode = new KLabel(groupMain, SWT.RIGHT);
		lbCheckCode.setFont(StyleFinal.SYS_FONT);
		lbCheckCode.setText("检票口代码:");
		GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbCheckCode.setLayoutData(gridData);
		
		txtCheckCode = new Text(groupMain, SWT.BORDER);
		txtCheckCode.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtCheckCode.setLayoutData(gridData);
		
		KLabel lbCheckName = new KLabel(groupMain, SWT.RIGHT);
		lbCheckName.setFont(StyleFinal.SYS_FONT);
		lbCheckName.setText("检票口名称:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbCheckName.setLayoutData(gridData);
		
		txtCheckName = new Text(groupMain, SWT.BORDER);
		txtCheckName.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtCheckName.setLayoutData(gridData);
		
		Label lbRemark = new Label(groupMain, SWT.RIGHT);
		lbRemark.setFont(StyleFinal.SYS_FONT);
		lbRemark.setText("备注:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbRemark.setLayoutData(gridData);
		
		txtRemark = new Text(groupMain, SWT.BORDER);
		txtRemark.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtRemark.setLayoutData(gridData);
		
		initData();
		txtCheckCode.forceFocus();
		txtCheckCode.selectAll();

		callMethod.bindEnterEvent(this, txtCheckCode, txtCheckName, "");
		callMethod.bindEnterEvent(this, txtCheckName, txtRemark, "");
		callMethod.bindEnterEvent(this, txtRemark, null, "");
		return compMain;
	}
	
	private void initData(){
		if (CommFinal.OPER_TYPE_UPDATE.equals(operType)){
			EpdCheckgate epdCheckgate = (EpdCheckgate) gridView.getSelection();
			if (null != epdCheckgate && !"".equals(epdCheckgate.getCheckgateSeq())){
				txtCheckCode.setText(epdCheckgate.getCheckCode());
				txtCheckName.setText(epdCheckgate.getCheckName());
				txtRemark.setText(epdCheckgate.getRemark());
			}
		}
	}

	protected void buttonPressed(int buttonId){
		try {
			if(1 == buttonId){
				/**
				 * 确定
				 */
				EpdCheckgate epdCheckgate = validData();
				if (null != epdCheckgate){
					IEpdCheckgate iEpdCheckgate = new ImpEpdCheckgate();
					if (CommFinal.OPER_TYPE_ADD.equals(operType)){
						EpdCheckgate newCheckgate = iEpdCheckgate.insert(epdCheckgate,CommFinal.initConfig());
						gridView.addRow(newCheckgate);
						clearData();
					} else if(CommFinal.OPER_TYPE_UPDATE.equals(operType)){
						iEpdCheckgate.update(epdCheckgate,CommFinal.initConfig());
						gridView.updateRow(gridView.getSelectionIndex(), epdCheckgate);
						close();
					}
				}
			} else if (0 == buttonId) {
				/**
				 * 取消
				 */
				close();
			}
		} catch (Exception e) {
			LogUtil.operLog(e,"E",true,true);
		}
		
	}
	
	private EpdCheckgate validData(){
		String currTime = DateUtils.getNow(DateUtils.FORMAT_LONG);
		EpdCheckgate epdCheckgate = new EpdCheckgate();
		epdCheckgate.setOrganizeSeq(CommFinal.organize.getOrganizeSeq());
		if (CommFinal.OPER_TYPE_UPDATE.equals(operType)){
			epdCheckgate = (EpdCheckgate) gridView.getSelection();
		}
		epdCheckgate.setUpdateTime(currTime);
		if (null != txtCheckCode.getText() && txtCheckCode.getText().length()>0){
			epdCheckgate.setCheckCode(txtCheckCode.getText());
		}else{
			MsgBox.warning(getParentShell(),"请输入检票口代码！");
			txtCheckCode.forceFocus();
			return null;
		}
		
		if (null != txtCheckName.getText() && txtCheckName.getText().length()>0){
			epdCheckgate.setCheckName(txtCheckName.getText());
		}else{
			MsgBox.warning(getParentShell(),"请输入检票口名称！");
			txtCheckName.forceFocus();
			return null;
		}
		epdCheckgate.setRemark(txtRemark.getText());
		
		return epdCheckgate;
	}
	
	private void clearData(){
		txtCheckCode.setText("");
		txtCheckName.setText("");
		txtRemark.setText("");
		txtCheckCode.forceFocus();
	}
	
}
