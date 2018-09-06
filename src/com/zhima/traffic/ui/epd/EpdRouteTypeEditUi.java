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
import com.zhima.traffic.action.epd.IEpdRouteType;
import com.zhima.traffic.action.epd.impl.ImpEpdRouteType;
import com.zhima.traffic.model.EpdRoutetype;
import com.zhima.util.DateUtils;
import com.zhima.util.SWTResourceManager;
import com.zhima.util.log4j.LogUtil;
import com.zhima.widget.KLabel;
import com.zhima.widget.MsgBox;
import com.zhima.widget.grid.GridView;

public class EpdRouteTypeEditUi extends Dialog {
	private GridView gridView;
	private String operType;
	
	private Text txtRoutetypeCode;
	private Text txtRoutetypeName;
	private Text txtRemark;

	protected EpdRouteTypeEditUi(Shell shell,GridView gridView,String operType) {
		super(shell);
		this.gridView = gridView;
		this.operType = operType;
	}

	protected void configureShell(Shell shell){
		super.configureShell(shell);
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		shell.setBackground(StyleFinal.DIALOG_BACK_GROUND);
		shell.setText("线路类型设置-"+operType);
	}
	
	@Override
	protected Point getInitialLocation(Point initialSize) {
		Rectangle screenSize =  Display.getDefault().getClientArea();
		return new Point((screenSize.width - initialSize.x)/2, (screenSize.height - initialSize.y)/2);
	}
	
	@Override
	protected Point getInitialSize(){
        return new Point(315,225);
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
		groupMain.setText("线路类型信息");
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
		
		KLabel lbRoutetypeCode = new KLabel(groupMain, SWT.RIGHT);
		lbRoutetypeCode.setFont(StyleFinal.SYS_FONT);
		lbRoutetypeCode.setText("线路类型代码:");
		GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbRoutetypeCode.setLayoutData(gridData);
		
		txtRoutetypeCode = new Text(groupMain, SWT.BORDER);
		txtRoutetypeCode.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtRoutetypeCode.setLayoutData(gridData);
		
		KLabel lbRoutetypeName = new KLabel(groupMain, SWT.RIGHT);
		lbRoutetypeName.setFont(StyleFinal.SYS_FONT);
		lbRoutetypeName.setText("线路类型名称:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbRoutetypeName.setLayoutData(gridData);
		
		txtRoutetypeName = new Text(groupMain, SWT.BORDER);
		txtRoutetypeName.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtRoutetypeName.setLayoutData(gridData);
		
		Label lbRemark = new Label(groupMain, SWT.RIGHT);
		lbRemark.setFont(StyleFinal.SYS_FONT);
		lbRemark.setText("备注:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		lbRemark.setLayoutData(gridData);
		
		txtRemark = new Text(groupMain, SWT.BORDER|SWT.MULTI|SWT.WRAP);
		txtRemark.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT*3;
		txtRemark.setLayoutData(gridData);
		
		initData();
		txtRoutetypeCode.forceFocus();
		txtRoutetypeCode.selectAll();

		callMethod.bindEnterEvent(this, txtRoutetypeCode, txtRoutetypeName, "");
		callMethod.bindEnterEvent(this, txtRoutetypeName, txtRemark, "");
		callMethod.bindEnterEvent(this, txtRemark, null, "");
		return compMain;
	}
	
	private void initData(){
		if (CommFinal.OPER_TYPE_UPDATE.equals(operType)){
			EpdRoutetype epdRoutetype = (EpdRoutetype) gridView.getSelection();
			if (null != epdRoutetype && !"".equals(epdRoutetype.getRoutetypeSeq())){
				txtRoutetypeCode.setText(epdRoutetype.getRoutetypeCode());
				txtRoutetypeName.setText(epdRoutetype.getRoutetypeName());
				txtRemark.setText(epdRoutetype.getRemark());
			}
		}
	}

	protected void buttonPressed(int buttonId){
		try {
			if(1 == buttonId){
				/**
				 * 确定
				 */
				EpdRoutetype routetype = validData();
				if (null != routetype){
					IEpdRouteType iEpdRouteType = new ImpEpdRouteType();
					if (CommFinal.OPER_TYPE_ADD.equals(operType)){
						EpdRoutetype newRoutetype = iEpdRouteType.insert(routetype,CommFinal.initConfig());
						gridView.addRow(newRoutetype);
						clearData();
					} else if(CommFinal.OPER_TYPE_UPDATE.equals(operType)){
						iEpdRouteType.update(routetype,CommFinal.initConfig());
						gridView.updateRow(gridView.getSelectionIndex(), routetype);
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
	
	private EpdRoutetype validData(){
		String currTime = DateUtils.getNow(DateUtils.FORMAT_LONG);
		EpdRoutetype epdRoutetype = new EpdRoutetype();
		epdRoutetype.setOrganizeSeq(CommFinal.organize.getOrganizeSeq());
		if (CommFinal.OPER_TYPE_UPDATE.equals(operType)){
			epdRoutetype = (EpdRoutetype) gridView.getSelection();
		}
		epdRoutetype.setUpdateTime(currTime);
		if (null != txtRoutetypeCode.getText() && txtRoutetypeCode.getText().length()>0){
			epdRoutetype.setRoutetypeCode(txtRoutetypeCode.getText());
		}else{
			MsgBox.warning(getParentShell(),"请输入线路类型代码！");
			txtRoutetypeCode.forceFocus();
			return null;
		}
		
		if (null != txtRoutetypeName.getText() && txtRoutetypeName.getText().length()>0){
			epdRoutetype.setRoutetypeName(txtRoutetypeName.getText());
		}else{
			MsgBox.warning(getParentShell(),"请输入省(市)！");
			txtRoutetypeName.forceFocus();
			return null;
		}
		epdRoutetype.setRemark(txtRemark.getText());
		
		return epdRoutetype;
	}
	
	private void clearData(){

		txtRoutetypeCode.setText("");
		txtRoutetypeName.setText("");
		txtRemark.setText("");
		txtRoutetypeCode.forceFocus();
	}
	
}
