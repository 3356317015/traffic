package com.zhima.traffic.ui.voice;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
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
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.zhima.basic.CommFinal;
import com.zhima.basic.StyleFinal;
import com.zhima.traffic.action.voice.IVmsTemplate;
import com.zhima.traffic.action.voice.impl.ImpVmsTemplate;
import com.zhima.traffic.model.VmsLiner;
import com.zhima.traffic.model.VmsTemplate;
import com.zhima.util.SWTResourceManager;
import com.zhima.util.log4j.LogUtil;
import com.zhima.widget.MsgBox;
import com.zhima.widget.grid.GridColumn;
import com.zhima.widget.grid.GridConfig;
import com.zhima.widget.grid.GridView;

public class VmsPlayCauseUi extends Dialog {
	private GridView gridView;
	private String operType;
	
	private VmsTemplate vmsCause;
	
	private Text txtLinerDate;
	private Text txtLinerId;
	private Text txtLinerTime;
	private Text txtRouteName;
	private GridView gridCause;

	protected VmsPlayCauseUi(Shell shell,GridView gridView,String operType) {
		super(shell);
		this.gridView = gridView;
		this.operType = operType;
	}

	protected void configureShell(Shell shell){
		super.configureShell(shell);
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		shell.setBackground(StyleFinal.DIALOG_BACK_GROUND);
		shell.setText("播放设置-"+operType);
	}
	
	@Override
	protected Point getInitialLocation(Point initialSize) {
		Rectangle screenSize =  Display.getDefault().getClientArea();
		return new Point((screenSize.width - initialSize.x)/2, (screenSize.height - initialSize.y)/2);
	}
	
	@Override
	protected Point getInitialSize(){
        return new Point(525,355);
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
		
		Group groupMain = new Group(compMain,SWT.NONE);
		groupMain.setText("原因信息");
		groupMain.setFont(StyleFinal.SYS_FONT);
		groupMain.setLayout(new FormLayout());
		FormData data = new FormData();
		data.top = new FormAttachment(0,5);
		data.left = new FormAttachment(0,5);
		data.right = new FormAttachment(100,-5);
		data.bottom = new FormAttachment(100);
		groupMain.setLayoutData(data);
		
		
		Label lbLinerDate = new Label(groupMain, SWT.RIGHT);
		lbLinerDate.setFont(StyleFinal.SYS_FONT);
		lbLinerDate.setText("班次日期:");
		data = new FormData();
		data.top = new FormAttachment(0, 8);
		data.left = new FormAttachment(0, 5);
		lbLinerDate.setLayoutData(data);
		
		txtLinerDate = new Text(groupMain,SWT.BORDER);
		txtLinerDate.setFont(StyleFinal.SYS_FONT);
		data = new FormData();
		data.top = new FormAttachment(0, 5);
		data.left = new FormAttachment(lbLinerDate, 5);
		data.right = new FormAttachment(50);
		data.height = StyleFinal.TEXT_HEIGHT;
		txtLinerDate.setLayoutData(data);
		
		Label lbLinerId = new Label(groupMain, SWT.RIGHT);
		lbLinerId.setFont(StyleFinal.SYS_FONT);
		lbLinerId.setText("班次号:");
		data = new FormData();
		data.top = new FormAttachment(0, 8);
		data.left = new FormAttachment(txtLinerDate, 10);
		lbLinerId.setLayoutData(data);
		
		txtLinerId = new Text(groupMain,SWT.BORDER);
		txtLinerId.setFont(StyleFinal.SYS_FONT);
		data = new FormData();
		data.top = new FormAttachment(0, 5);
		data.left = new FormAttachment(lbLinerId, 5);
		data.right = new FormAttachment(100, -5);
		data.height = StyleFinal.TEXT_HEIGHT;
		txtLinerId.setLayoutData(data);
		
		Label lbLinerTime = new Label(groupMain, SWT.RIGHT);
		lbLinerTime.setFont(StyleFinal.SYS_FONT);
		lbLinerTime.setText("班次时间:");
		data = new FormData();
		data.top = new FormAttachment(lbLinerDate, 10);
		data.right = new FormAttachment(lbLinerDate, 0, SWT.RIGHT);
		lbLinerTime.setLayoutData(data);
		
		txtLinerTime = new Text(groupMain,SWT.BORDER);
		txtLinerTime.setFont(StyleFinal.SYS_FONT);
		data = new FormData();
		data.top = new FormAttachment(lbLinerDate, 8);
		data.left = new FormAttachment(lbLinerTime, 5);
		data.right = new FormAttachment(50);
		data.height = StyleFinal.TEXT_HEIGHT;
		txtLinerTime.setLayoutData(data);
		
		Label lbRouteName = new Label(groupMain, SWT.RIGHT);
		lbRouteName.setFont(StyleFinal.SYS_FONT);
		lbRouteName.setText("线路:");
		data = new FormData();
		data.top = new FormAttachment(lbLinerId, 10);
		data.right = new FormAttachment(lbLinerId, 0, SWT.RIGHT);
		lbRouteName.setLayoutData(data);
		
		txtRouteName = new Text(groupMain,SWT.BORDER);
		txtRouteName.setFont(StyleFinal.SYS_FONT);
		data = new FormData();
		data.top = new FormAttachment(lbLinerId, 8);
		data.left = new FormAttachment(lbRouteName, 5);
		data.right = new FormAttachment(100, -5);
		data.height = StyleFinal.TEXT_HEIGHT;
		txtRouteName.setLayoutData(data);
		
		List<GridColumn> columns=new ArrayList<GridColumn>();
		columns.add(new GridColumn("中文原因", "templateCn",205));
		columns.add(new GridColumn("英文原因", "templateEn",205));
		GridConfig gridConfig = new GridConfig();
		gridConfig.setCheck(false);
		gridConfig.setShowPage(false);
		gridConfig.setColumns(columns);
		
		gridCause = new GridView(groupMain, SWT.BORDER);
		gridCause.CreateTabel(gridConfig);
		data = new FormData();
		data.top = new FormAttachment(lbLinerTime,10);
		data.left = new FormAttachment(0,5);
		data.bottom = new FormAttachment(100,-5);
		data.right = new FormAttachment(100,-5);
		gridCause.setLayoutData(data);
		initData();
		return compMain;
	}
	
	
	private void initData(){
		try {
			VmsLiner vmsLiner = (VmsLiner) gridView.getSelection();
			txtLinerDate.setText(vmsLiner.getLinerDate());
			txtLinerId.setText(vmsLiner.getLinerId());
			txtLinerTime.setText(vmsLiner.getLinerTime());
			txtRouteName.setText(vmsLiner.getRouteName());
			
			txtLinerDate.setEditable(false);
			txtLinerId.setEditable(false);
			txtLinerTime.setEditable(false);
			txtRouteName.setEditable(false);
			
			
			IVmsTemplate iVmsTemplate = new ImpVmsTemplate();
			List<VmsTemplate> vmsTemplates = iVmsTemplate.queryByOrganizeAndType(
					CommFinal.organize.getOrganizeSeq(), "2");
			gridCause.setDataList(vmsTemplates);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void buttonPressed(int buttonId){
		try {
			if(1 == buttonId){
				/**
				 * 确定
				 */
				int index =  gridCause.getSelectionIndex();
				if (index>=0){
					vmsCause = (VmsTemplate) gridCause.getSelection();
				}else{
					MsgBox.warning(getShell(), "请选择原因");
					return;
				}
				close();
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

	public VmsTemplate getVmsCause() {
		return vmsCause;
	}

	public void setVmsCause(VmsTemplate vmsCause) {
		this.vmsCause = vmsCause;
	}
	
}
