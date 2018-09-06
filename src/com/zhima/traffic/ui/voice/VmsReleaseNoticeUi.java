package com.zhima.traffic.ui.voice;

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
import com.zhima.traffic.action.voice.IVmsNotice;
import com.zhima.traffic.action.voice.impl.ImpVmsNotice;
import com.zhima.traffic.model.VmsNotice;
import com.zhima.util.DateUtils;
import com.zhima.util.SWTResourceManager;
import com.zhima.util.log4j.LogUtil;
import com.zhima.widget.grid.GridView;

public class VmsReleaseNoticeUi extends Dialog {
	private GridView gridView;
	private String operType;
	
	private Text txtNoticeName;

	private Text txtNoticeContent;

	protected VmsReleaseNoticeUi(Shell shell,GridView gridView,String operType) {
		super(shell);
		this.gridView = gridView;
		this.operType = operType;
	}

	protected void configureShell(Shell shell){
		super.configureShell(shell);
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		shell.setBackground(StyleFinal.DIALOG_BACK_GROUND);
		shell.setText("文字公告-"+operType);
	}
	
	@Override
	protected Point getInitialLocation(Point initialSize) {
		Rectangle screenSize =  Display.getDefault().getClientArea();
		return new Point((screenSize.width - initialSize.x)/2, (screenSize.height - initialSize.y)/2);
	}
	
	@Override
	protected Point getInitialSize(){
        return new Point(600,355);
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
		groupMain.setText("公告信息");
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
		
		Label lbNoticeName = new Label(groupMain, SWT.RIGHT);
		lbNoticeName.setFont(StyleFinal.SYS_FONT);
		lbNoticeName.setText("公告名称:");
		GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbNoticeName.setLayoutData(gridData);
		
		txtNoticeName = new Text(groupMain, SWT.BORDER);
		txtNoticeName.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		txtNoticeName.setLayoutData(gridData);

		Label lbNoticeContent = new Label(groupMain, SWT.RIGHT);
		lbNoticeContent.setFont(StyleFinal.SYS_FONT);
		lbNoticeContent.setText("公告内容:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbNoticeContent.setLayoutData(gridData);
		
		txtNoticeContent = new Text(groupMain, SWT.BORDER|SWT.MULTI|SWT.WRAP);
		txtNoticeContent.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = 200;
		txtNoticeContent.setLayoutData(gridData);
		
		initData();
		txtNoticeName.forceFocus();
		txtNoticeName.selectAll();
		
		callMethod.bindEnterEvent(this, txtNoticeName, txtNoticeContent, "");
		callMethod.bindEnterEvent(this, txtNoticeContent, null, "");
		return compMain;
	}
	
	private void initData(){
		if (CommFinal.OPER_TYPE_UPDATE.equals(operType)){
			VmsNotice vmsNotice = (VmsNotice) gridView.getSelection();
			if (null != vmsNotice && !"".equals(vmsNotice.getNoticeSeq())){
				txtNoticeName.setText(vmsNotice.getNoticeName());
				txtNoticeContent.setText(vmsNotice.getNoticeContent());
			}
		}
	}

	protected void buttonPressed(int buttonId){
		try {
			if(1 == buttonId){
				/**
				 * 确定
				 */
				VmsNotice vmsNotice = validData();
				if (null != vmsNotice){
					IVmsNotice iVmsNotice = new ImpVmsNotice();
					if (CommFinal.OPER_TYPE_ADD.equals(operType)){
						VmsNotice newNotice = iVmsNotice.insert(vmsNotice,CommFinal.initConfig());
						gridView.addRow(newNotice);
						clearData();
					} else if(CommFinal.OPER_TYPE_UPDATE.equals(operType)){
						iVmsNotice.update(vmsNotice,CommFinal.initConfig());
						gridView.updateRow(gridView.getSelectionIndex(), vmsNotice);
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
	
	private VmsNotice validData(){
		String currTime = DateUtils.getNow(DateUtils.FORMAT_LONG);
		VmsNotice vmsNotice = new VmsNotice();
		vmsNotice.setOrganizeSeq(CommFinal.organize.getOrganizeSeq());
		if (CommFinal.OPER_TYPE_UPDATE.equals(operType)){
			vmsNotice = (VmsNotice) gridView.getSelection();
		}
		vmsNotice.setUpdateTime(currTime);
		vmsNotice.setNoticeName(txtNoticeName.getText());
		vmsNotice.setNoticeContent(txtNoticeContent.getText());
		
		vmsNotice.setNoticeStatus("1");
		return vmsNotice;
	}
	
	private void clearData(){
		txtNoticeName.setText("");
		txtNoticeContent.setText("");
		txtNoticeName.forceFocus();
	}
	
}
