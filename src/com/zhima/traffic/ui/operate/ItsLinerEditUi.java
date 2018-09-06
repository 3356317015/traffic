package com.zhima.traffic.ui.operate;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.nebula.widgets.formattedtext.DateTimeFormatter;
import org.eclipse.nebula.widgets.formattedtext.FormattedText;
import org.eclipse.nebula.widgets.formattedtext.ITextFormatter;
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
import com.zhima.traffic.action.operate.IItsLiner;
import com.zhima.traffic.action.operate.impl.ImpItsLiner;
import com.zhima.traffic.model.ItsLiner;
import com.zhima.util.DateUtils;
import com.zhima.util.SWTResourceManager;
import com.zhima.util.log4j.LogUtil;
import com.zhima.widget.KLabel;
import com.zhima.widget.MsgBox;
import com.zhima.widget.grid.GridView;

public class ItsLinerEditUi extends Dialog {
	private GridView gridView;
	private String operType;
	
	private Text txtRouteName;
	private Text txtLinerDate;
	private Text txtLinerId;
	private Text txtLinerTime;
	private Button btnYes;
	private Button btnNo;
	private Button btnOne;
	private Button btnTwo;
	private Button btnThree;
	private Button btnFour;
	

	protected ItsLinerEditUi(Shell shell,GridView gridView,String operType) {
		super(shell);
		this.gridView = gridView;
		this.operType = operType;
	}

	protected void configureShell(Shell shell){
		super.configureShell(shell);
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		shell.setBackground(StyleFinal.DIALOG_BACK_GROUND);
		shell.setText("班次设置-"+operType);
	}
	
	@Override
	protected Point getInitialLocation(Point initialSize) {
		Rectangle screenSize =  Display.getDefault().getClientArea();
		return new Point((screenSize.width - initialSize.x)/2, (screenSize.height - initialSize.y)/2);
	}
	
	@Override
	protected Point getInitialSize(){
        return new Point(315,315);
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
		groupMain.setText("班次信息");
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
		
		Label lbLineName = new Label(groupMain, SWT.NONE);
		lbLineName.setText("线路名称:");
		lbLineName.setFont(StyleFinal.SYS_FONT);
		GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbLineName.setLayoutData(gridData);
		
		txtRouteName = new Text(groupMain, SWT.BORDER|SWT.READ_ONLY);
		txtRouteName.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtRouteName.setLayoutData(gridData);
		
		Label lbLinerDate = new Label(groupMain, SWT.NONE);
		lbLinerDate.setText("班次日期:");
		lbLinerDate.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbLinerDate.setLayoutData(gridData);
		
		txtLinerDate = new Text(groupMain, SWT.BORDER|SWT.READ_ONLY);
		txtLinerDate.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtLinerDate.setLayoutData(gridData);
		
		Label lbLinerId = new Label(groupMain, SWT.NONE);
		lbLinerId.setText("班次号:");
		lbLinerId.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbLinerId.setLayoutData(gridData);
		
		txtLinerId = new Text(groupMain, SWT.BORDER|SWT.READ_ONLY);
		txtLinerId.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtLinerId.setLayoutData(gridData);
		
		KLabel lbLinerTime = new KLabel(groupMain, SWT.NONE);
		lbLinerTime.setText("发车时间:");
		lbLinerTime.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbLinerTime.setLayoutData(gridData);
		
		FormattedText formattedLinerTime = new FormattedText(groupMain, SWT.BORDER);
		ITextFormatter formatterLinerTime = new DateTimeFormatter("HH:mm");
		formattedLinerTime.setFormatter(formatterLinerTime);
		txtLinerTime = formattedLinerTime.getControl();
		txtLinerTime.setFont(StyleFinal.SYS_FONT);
		SimpleDateFormat formatFirstTime = new SimpleDateFormat("HH:mm");
		Calendar calendarLinerTime = Calendar.getInstance();
		txtLinerTime.setText(formatFirstTime.format(calendarLinerTime.getTime()));
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtLinerTime.setLayoutData(gridData);
		
		KLabel lbIfNetsale = new KLabel(groupMain, SWT.NONE);
		lbIfNetsale.setText("是否网售:");
		lbIfNetsale.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbIfNetsale.setLayoutData(gridData);
		
		Composite compNetsale = new Composite(groupMain, SWT.NONE);
		gridLayout = new GridLayout(2,false);
		gridLayout.verticalSpacing=StyleFinal.VERTICAL_SPACING;
		compNetsale.setLayout(gridLayout);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		compNetsale.setLayoutData(gridData);
		
		btnYes = new Button(compNetsale, SWT.RADIO|SWT.RIGHT);
		btnYes.setText("是(&Y)");
		btnYes.setFont(StyleFinal.SYS_FONT);
		
		btnNo = new Button(compNetsale, SWT.RADIO|SWT.RIGHT);
		btnNo.setText("否(&N)");
		btnNo.setFont(StyleFinal.SYS_FONT);
		
		KLabel lbLinerState = new KLabel(groupMain, SWT.NONE);
		lbLinerState.setText("班次状态:");
		lbLinerState.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		gridData.verticalSpan=2;
		lbLinerState.setLayoutData(gridData);
		
		Composite compState = new Composite(groupMain, SWT.NONE);
		gridLayout = new GridLayout(2,false);
		gridLayout.verticalSpacing=StyleFinal.VERTICAL_SPACING;
		compState.setLayout(gridLayout);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.verticalSpan=2;
		compState.setLayoutData(gridData);
		
		btnOne = new Button(compState, SWT.RADIO|SWT.RIGHT);
		btnOne.setText("销售(&S)");
		btnOne.setFont(StyleFinal.SYS_FONT);
		
		btnTwo = new Button(compState, SWT.RADIO|SWT.RIGHT);
		btnTwo.setText("停班(&Z)");
		btnTwo.setFont(StyleFinal.SYS_FONT);
		
		btnThree = new Button(compState, SWT.RADIO|SWT.RIGHT);
		btnThree.setText("撤班(&D)");
		btnThree.setFont(StyleFinal.SYS_FONT);
		
		btnFour = new Button(compState, SWT.RADIO|SWT.RIGHT);
		btnFour.setText("废班(&X)");
		btnFour.setFont(StyleFinal.SYS_FONT);

		initData();

		callMethod.bindEnterEvent(this, txtRouteName, txtLinerTime, "");
		callMethod.bindEnterEvent(this, txtLinerDate, txtLinerTime, "");
		callMethod.bindEnterEvent(this, txtLinerId, txtLinerTime, "");
		callMethod.bindEnterEvent(this, txtLinerTime, btnYes, "");
		callMethod.bindEnterEvent(this, btnYes, btnNo, "");
		callMethod.bindEnterEvent(this, btnNo, btnOne, "");
		callMethod.bindEnterEvent(this, btnOne, btnTwo, "");
		callMethod.bindEnterEvent(this, btnTwo, btnThree, "");
		callMethod.bindEnterEvent(this, btnThree, btnFour, "");
		callMethod.bindEnterEvent(this, btnFour, null, "");
		return compMain;
	}
	
	private void initData(){
		if (CommFinal.OPER_TYPE_UPDATE.equals(operType)){
			ItsLiner itsLiner = (ItsLiner) gridView.getSelection();
			if (null != itsLiner && !"".equals(itsLiner.getLinerSeq())){
				txtRouteName.setText(itsLiner.getRouteName());
				txtLinerDate.setText(itsLiner.getLinerDate());
				txtLinerId.setText(itsLiner.getLinerId());
				txtLinerTime.setText(itsLiner.getLinerTime());
				if (itsLiner.getIfNetsale()==1){
					btnYes.setSelection(true);
				}else{
					btnNo.setSelection(true);
				}
				if ("销售".equals(itsLiner.getLinerStatus())){
					btnOne.setSelection(true);
				}else if ("停班".equals(itsLiner.getLinerStatus())){
					btnTwo.setSelection(true);
				}else if ("撤班".equals(itsLiner.getLinerStatus())){
					btnThree.setSelection(true);
				}else if ("废班".equals(itsLiner.getLinerStatus())){
					btnFour.setSelection(true);
				}
			}
		}
		txtLinerTime.forceFocus();
		txtLinerTime.selectAll();
	}

	protected void buttonPressed(int buttonId){
		try {
			if(1 == buttonId){
				/**
				 * 确定
				 */
				ItsLiner itsLiner = validData();
				if (null != itsLiner){
					IItsLiner iItsLiner = new ImpItsLiner();
					if(CommFinal.OPER_TYPE_UPDATE.equals(operType)){
						iItsLiner.updateAttribute(itsLiner,CommFinal.initConfig());
						gridView.updateRow(gridView.getSelectionIndex(), itsLiner);
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
	
	private ItsLiner validData(){
		String currTime = DateUtils.getNow(DateUtils.FORMAT_LONG);
		ItsLiner itsLiner = new ItsLiner();
		if (CommFinal.OPER_TYPE_UPDATE.equals(operType)){
			itsLiner = (ItsLiner) gridView.getSelection();
		}
		itsLiner.setUpdateTime(currTime);
		if (txtLinerTime.getText().trim().length()!=5){
			MsgBox.warning(getShell(), "班次发车时间不合法！");
			txtLinerTime.forceFocus();
			return null;
		}
		itsLiner.setLinerTime(txtLinerTime.getText());
		if (btnYes.getSelection()){
			itsLiner.setIfNetsale(1);
		}else{
			itsLiner.setIfNetsale(0);
		}
		if (btnOne.getSelection()){
			itsLiner.setLinerStatus("销售");
		}else if (btnTwo.getSelection()){
			itsLiner.setLinerStatus("停班");
		}else if (btnThree.getSelection()){
			itsLiner.setLinerStatus("撤班");
		}else if (btnFour.getSelection()){
			itsLiner.setLinerStatus("废班");
		}
		
		return itsLiner;
	}
	
}
