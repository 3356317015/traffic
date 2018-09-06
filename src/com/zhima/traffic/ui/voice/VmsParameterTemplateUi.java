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
import org.eclipse.swt.layout.GridLayout;
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
import com.zhima.traffic.model.VmsTemplate;
import com.zhima.util.DateUtils;
import com.zhima.util.SWTResourceManager;
import com.zhima.util.log4j.LogUtil;
import com.zhima.widget.grid.GridView;

public class VmsParameterTemplateUi extends Dialog {
	private GridView gridView;
	private String operType;
	
	private Text txtTemplateName;
	private Text txtVariable;
	
	private Text txtTemplateCn;
	private Text txtTemplateEn;

	protected VmsParameterTemplateUi(Shell shell,GridView gridView,String operType) {
		super(shell);
		this.gridView = gridView;
		this.operType = operType;
	}

	protected void configureShell(Shell shell){
		super.configureShell(shell);
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		shell.setBackground(StyleFinal.DIALOG_BACK_GROUND);
		shell.setText("模板设置-"+operType);
	}
	
	@Override
	protected Point getInitialLocation(Point initialSize) {
		Rectangle screenSize =  Display.getDefault().getClientArea();
		return new Point((screenSize.width - initialSize.x)/2, (screenSize.height - initialSize.y)/2);
	}
	
	@Override
	protected Point getInitialSize(){
        return new Point(625,500);
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
		groupMain.setText("模板信息");
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
		
		Label lbTemplateName = new Label(groupMain, SWT.RIGHT);
		lbTemplateName.setFont(StyleFinal.SYS_FONT);
		lbTemplateName.setText("模板名称:");
		GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbTemplateName.setLayoutData(gridData);
		
		txtTemplateName = new Text(groupMain, SWT.BORDER);
		txtTemplateName.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		txtTemplateName.setLayoutData(gridData);
		
		Label lbVariable = new Label(groupMain, SWT.RIGHT);
		lbVariable.setFont(StyleFinal.SYS_FONT);
		lbVariable.setText("播音变量:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbTemplateName.setLayoutData(gridData);
		
		txtVariable = new Text(groupMain, SWT.BORDER|SWT.MULTI|SWT.WRAP);
		txtVariable.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = 30;
		txtVariable.setLayoutData(gridData);

		Label lbTemplateCn = new Label(groupMain, SWT.RIGHT);
		lbTemplateCn.setFont(StyleFinal.SYS_FONT);
		lbTemplateCn.setText("中文播音:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbTemplateCn.setLayoutData(gridData);
		
		txtTemplateCn = new Text(groupMain, SWT.BORDER|SWT.MULTI|SWT.WRAP);
		txtTemplateCn.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = 145;
		txtTemplateCn.setLayoutData(gridData);
		
		Label lbTemplateEn = new Label(groupMain, SWT.RIGHT);
		lbTemplateEn.setFont(StyleFinal.SYS_FONT);
		lbTemplateEn.setText("英文播音:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbTemplateEn.setLayoutData(gridData);
		
		txtTemplateEn = new Text(groupMain, SWT.BORDER|SWT.MULTI|SWT.WRAP);
		txtTemplateEn.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = 145;
		txtTemplateEn.setLayoutData(gridData);
		
		initData();
		txtTemplateCn.forceFocus();
		txtTemplateCn.selectAll();
		
		/*callMethod.bindEnterEvent(this, txtTemplateCn, txtTemplateEn, "");
		callMethod.bindEnterEvent(this, txtTemplateEn, null, "");*/
		return compMain;
	}
	
	private void initData(){
		if (CommFinal.OPER_TYPE_UPDATE.equals(operType)){
			txtTemplateName.setEnabled(false);
			txtVariable.setEditable(false);
			txtVariable.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
			txtVariable.setText("[route_name]=线路名称;[liner_id]=班次号;[liner_time]=班次时间;" +
					"\r\n[cargrade_name]=车型;[check_code]=检票口;[parking_name]=发车位");
			VmsTemplate vmsTemplate = (VmsTemplate) gridView.getSelection();
			if (null != vmsTemplate && !"".equals(vmsTemplate.getTemplateSeq())){
				txtTemplateName.setText(vmsTemplate.getTemplateName());
				txtTemplateCn.setText(vmsTemplate.getTemplateCn());
				txtTemplateEn.setText(vmsTemplate.getTemplateEn());
			}
		}
	}

	protected void buttonPressed(int buttonId){
		try {
			if(1 == buttonId){
				/**
				 * 确定
				 */
				VmsTemplate vmsTemplate = validData();
				if (null != vmsTemplate){
					if(CommFinal.OPER_TYPE_UPDATE.equals(operType)){
						IVmsTemplate iVmsTemplate = new ImpVmsTemplate();
						List<VmsTemplate> vmsTemplates = new ArrayList<VmsTemplate>();
						vmsTemplates.add(vmsTemplate);
						iVmsTemplate.update(vmsTemplates, CommFinal.initConfig());
						gridView.updateRow(gridView.getSelectionIndex(), vmsTemplate);
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
	
	private VmsTemplate validData(){
		String currTime = DateUtils.getNow(DateUtils.FORMAT_LONG);
		VmsTemplate vmsTemplate = new VmsTemplate();
		vmsTemplate.setOrganizeSeq(CommFinal.organize.getOrganizeSeq());
		if (CommFinal.OPER_TYPE_UPDATE.equals(operType)){
			vmsTemplate = (VmsTemplate) gridView.getSelection();
		}
		vmsTemplate.setUpdateTime(currTime);
		vmsTemplate.setTemplateCn(txtTemplateCn.getText());
		vmsTemplate.setTemplateEn(txtTemplateEn.getText());
		return vmsTemplate;
	}
	
}
