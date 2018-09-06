package com.zhima.traffic.ui.operate;

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
import com.zhima.util.SWTResourceManager;
import com.zhima.widget.CCalendar;
import com.zhima.widget.KLabel;
import com.zhima.widget.grid.GridView;

public class ItsLinerMergeUi extends Dialog {
	@SuppressWarnings("unused")
	private GridView gridView;
	private String operType;

	protected ItsLinerMergeUi(Shell shell,GridView gridView,String operType) {
		super(shell);
		this.gridView = gridView;
		this.operType = operType;
	}

	protected void configureShell(Shell shell){
		super.configureShell(shell);
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		shell.setBackground(StyleFinal.DIALOG_BACK_GROUND);
		shell.setText("班次并班-"+operType);
	}
	
	@Override
	protected Point getInitialLocation(Point initialSize) {
		Rectangle screenSize =  Display.getDefault().getClientArea();
		return new Point((screenSize.width - initialSize.x)/2, (screenSize.height - initialSize.y)/2);
	}
	
	@Override
	protected Point getInitialSize(){
        return new Point(605,460);
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
		
		Group groupLeft = new Group(compMain,SWT.NONE);
		groupLeft.setText("合并班次信息");
		groupLeft.setFont(StyleFinal.SYS_FONT);
		GridLayout gridLayout = new GridLayout(2,false);
		gridLayout.verticalSpacing=StyleFinal.VERTICAL_SPACING;
		groupLeft.setLayout(gridLayout);
		FormData data = new FormData();
		data.top = new FormAttachment(0,5);
		data.left = new FormAttachment(0,5);
		data.right = new FormAttachment(50,-5);
		data.bottom = new FormAttachment(100);
		groupLeft.setLayoutData(data);
		createLeft(groupLeft);
		
		Group groupRight = new Group(compMain,SWT.NONE);
		groupRight.setText("目标班次信息");
		groupRight.setFont(StyleFinal.SYS_FONT);
		gridLayout = new GridLayout(2,false);
		gridLayout.verticalSpacing=StyleFinal.VERTICAL_SPACING;
		groupRight.setLayout(gridLayout);
		data = new FormData();
		data.top = new FormAttachment(0,5);
		data.left = new FormAttachment(groupLeft,5);
		data.right = new FormAttachment(100,-5);
		data.bottom = new FormAttachment(100);
		groupRight.setLayoutData(data);
		createRight(groupRight);
		
		return compMain;
	}

	private void createLeft(Group group) {
		// TODO Auto-generated method stub
		GridLayout gridLayout = new GridLayout(2,false);
		gridLayout.verticalSpacing=StyleFinal.VERTICAL_SPACING;
		group.setLayout(gridLayout);
		
		KLabel lbLinerDate = new KLabel(group, SWT.NONE);
		lbLinerDate.setText("班次日期:");
		lbLinerDate.setFont(StyleFinal.SYS_FONT);
		GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbLinerDate.setLayoutData(gridData);
		
		CCalendar dLinerDate = new CCalendar(group, SWT.BORDER);
		dLinerDate.txtDate.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		dLinerDate.setLayoutData(gridData);
		
		KLabel lbLinerId = new KLabel(group, SWT.NONE);
		lbLinerId.setText("班次号:");
		lbLinerId.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbLinerId.setLayoutData(gridData);
		
		Text txtLinerId = new Text(group, SWT.BORDER);
		txtLinerId.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtLinerId.setLayoutData(gridData);
		
		Label lbLinerTime = new Label(group, SWT.NONE);
		lbLinerTime.setText("发车时间:");
		lbLinerTime.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbLinerTime.setLayoutData(gridData);
		
		Text txtLinerTime = new Text(group, SWT.BORDER|SWT.READ_ONLY);
		txtLinerTime.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtLinerTime.setLayoutData(gridData);
		
		Label lbLinerType = new Label(group, SWT.NONE);
		lbLinerType.setText("班次类型:");
		lbLinerType.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbLinerType.setLayoutData(gridData);
		
		Text txtLinerType = new Text(group, SWT.BORDER|SWT.READ_ONLY);
		txtLinerType.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtLinerType.setLayoutData(gridData);
		
		Label lbLinerSeat = new Label(group, SWT.NONE);
		lbLinerSeat.setText("座位数:");
		lbLinerSeat.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		lbLinerSeat.setLayoutData(gridData);
		
		Text txtLinerSeat = new Text(group, SWT.BORDER|SWT.READ_ONLY);
		txtLinerSeat.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		txtLinerSeat.setLayoutData(gridData);
		
		Label lbSaleNum = new Label(group, SWT.NONE);
		lbSaleNum.setText("售票数:");
		lbSaleNum.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		lbSaleNum.setLayoutData(gridData);
		
		Text txtSaleNum = new Text(group, SWT.BORDER|SWT.READ_ONLY);
		txtLinerSeat.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		txtSaleNum.setLayoutData(gridData);
		
		Label lbHalfNum = new Label(group, SWT.NONE);
		lbHalfNum.setText("半票数:");
		lbHalfNum.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		lbHalfNum.setLayoutData(gridData);
		
		Text txtHalfNum = new Text(group, SWT.BORDER|SWT.READ_ONLY);
		txtLinerSeat.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		txtHalfNum.setLayoutData(gridData);
		
		Label lbFreeNum = new Label(group, SWT.NONE);
		lbFreeNum.setText("免票数:");
		lbFreeNum.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		lbFreeNum.setLayoutData(gridData);
		
		Text txtFreeNum = new Text(group, SWT.BORDER|SWT.READ_ONLY);
		txtFreeNum.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		txtFreeNum.setLayoutData(gridData);
		
		Label lbCheckName = new Label(group, SWT.NONE);
		lbCheckName.setText("检票口:");
		lbCheckName.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbCheckName.setLayoutData(gridData);
		
		Text txtCheckNum = new Text(group, SWT.BORDER|SWT.READ_ONLY);
		txtCheckNum.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtCheckNum.setLayoutData(gridData);
		
		Label lbIfDeal = new Label(group, SWT.NONE);
		lbIfDeal.setText("是否配载:");
		lbIfDeal.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbIfDeal.setLayoutData(gridData);
		
		Text txtIfDeal = new Text(group, SWT.BORDER|SWT.READ_ONLY);
		txtIfDeal.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtIfDeal.setLayoutData(gridData);
		
		Label lbIfMain = new Label(group, SWT.NONE);
		lbIfMain.setText("始发站:");
		lbIfMain.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbIfMain.setLayoutData(gridData);
		
		Text txtIfMain = new Text(group, SWT.BORDER|SWT.READ_ONLY);
		txtIfMain.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtIfMain.setLayoutData(gridData);
		
		Label lbIfNetsale = new Label(group, SWT.RIGHT);
		lbIfNetsale.setText("是否网售:");
		lbIfNetsale.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		gridData.widthHint=80;
		lbIfNetsale.setLayoutData(gridData);
		
		Text txtIfNetsale = new Text(group, SWT.BORDER|SWT.READ_ONLY);
		txtIfNetsale.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtIfNetsale.setLayoutData(gridData);
	}
	private void createRight(Group group) {
		// TODO Auto-generated method stub
		GridLayout gridLayout = new GridLayout(2,false);
		gridLayout.verticalSpacing=StyleFinal.VERTICAL_SPACING;
		group.setLayout(gridLayout);
		
		KLabel lbLinerDate = new KLabel(group, SWT.NONE);
		lbLinerDate.setText("班次日期:");
		lbLinerDate.setFont(StyleFinal.SYS_FONT);
		GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbLinerDate.setLayoutData(gridData);
		
		CCalendar dLinerDate = new CCalendar(group, SWT.BORDER);
		dLinerDate.txtDate.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		dLinerDate.setLayoutData(gridData);
		
		KLabel lbLinerId = new KLabel(group, SWT.NONE);
		lbLinerId.setText("班次号:");
		lbLinerId.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbLinerId.setLayoutData(gridData);
		
		Text txtLinerId = new Text(group, SWT.BORDER);
		txtLinerId.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtLinerId.setLayoutData(gridData);
		
		Label lbLinerTime = new Label(group, SWT.NONE);
		lbLinerTime.setText("发车时间:");
		lbLinerTime.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbLinerTime.setLayoutData(gridData);
		
		Text txtLinerTime = new Text(group, SWT.BORDER|SWT.READ_ONLY);
		txtLinerTime.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtLinerTime.setLayoutData(gridData);
		
		Label lbLinerType = new Label(group, SWT.NONE);
		lbLinerType.setText("班次类型:");
		lbLinerType.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbLinerType.setLayoutData(gridData);
		
		Text txtLinerType = new Text(group, SWT.BORDER|SWT.READ_ONLY);
		txtLinerType.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtLinerType.setLayoutData(gridData);
		
		Label lbLinerSeat = new Label(group, SWT.NONE);
		lbLinerSeat.setText("座位数:");
		lbLinerSeat.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		lbLinerSeat.setLayoutData(gridData);
		
		Text txtLinerSeat = new Text(group, SWT.BORDER|SWT.READ_ONLY);
		txtLinerSeat.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		txtLinerSeat.setLayoutData(gridData);
		
		Label lbSaleNum = new Label(group, SWT.NONE);
		lbSaleNum.setText("售票数:");
		lbSaleNum.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		lbSaleNum.setLayoutData(gridData);
		
		Text txtSaleNum = new Text(group, SWT.BORDER|SWT.READ_ONLY);
		txtLinerSeat.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		txtSaleNum.setLayoutData(gridData);
		
		Label lbHalfNum = new Label(group, SWT.NONE);
		lbHalfNum.setText("半票数:");
		lbHalfNum.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		lbHalfNum.setLayoutData(gridData);
		
		Text txtHalfNum = new Text(group, SWT.BORDER|SWT.READ_ONLY);
		txtLinerSeat.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		txtHalfNum.setLayoutData(gridData);
		
		Label lbFreeNum = new Label(group, SWT.NONE);
		lbFreeNum.setText("免票数:");
		lbFreeNum.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		lbFreeNum.setLayoutData(gridData);
		
		Text txtFreeNum = new Text(group, SWT.BORDER|SWT.READ_ONLY);
		txtFreeNum.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		txtFreeNum.setLayoutData(gridData);
		
		Label lbCheckName = new Label(group, SWT.NONE);
		lbCheckName.setText("检票口:");
		lbCheckName.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbCheckName.setLayoutData(gridData);
		
		Text txtCheckNum = new Text(group, SWT.BORDER|SWT.READ_ONLY);
		txtCheckNum.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtCheckNum.setLayoutData(gridData);
		
		Label lbIfDeal = new Label(group, SWT.NONE);
		lbIfDeal.setText("是否配载:");
		lbIfDeal.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbIfDeal.setLayoutData(gridData);
		
		Text txtIfDeal = new Text(group, SWT.BORDER|SWT.READ_ONLY);
		txtIfDeal.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtIfDeal.setLayoutData(gridData);
		
		Label lbIfMain = new Label(group, SWT.NONE);
		lbIfMain.setText("始发站:");
		lbIfMain.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbIfMain.setLayoutData(gridData);
		
		Text txtIfMain = new Text(group, SWT.BORDER|SWT.READ_ONLY);
		txtIfMain.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtIfMain.setLayoutData(gridData);
		
		Label lbIfNetsale = new Label(group, SWT.RIGHT);
		lbIfNetsale.setText("是否网售:");
		lbIfNetsale.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		gridData.widthHint=80;
		lbIfNetsale.setLayoutData(gridData);
		
		Text txtIfNetsale = new Text(group, SWT.BORDER|SWT.READ_ONLY);
		txtIfNetsale.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtIfNetsale.setLayoutData(gridData);
	}
	
}
