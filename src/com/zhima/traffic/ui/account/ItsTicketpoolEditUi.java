package com.zhima.traffic.ui.account;

import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
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
import com.zhima.frame.drop.DropUser;
import com.zhima.traffic.action.account.IItsTicketpool;
import com.zhima.traffic.action.account.impl.ImpItsTicketpool;
import com.zhima.traffic.comm.TraffFinal;
import com.zhima.traffic.drop.DropTickettype;
import com.zhima.traffic.model.EpdTickettype;
import com.zhima.traffic.model.ItsTicketpool;
import com.zhima.util.DateUtils;
import com.zhima.util.SWTResourceManager;
import com.zhima.util.StringUtils;
import com.zhima.util.log4j.LogUtil;
import com.zhima.widget.KLabel;
import com.zhima.widget.MsgBox;
import com.zhima.widget.grid.GridView;

public class ItsTicketpoolEditUi extends Dialog {
	private GridView gridView;
	private String operType;
	
	private DropTickettype dropTickettype;
	private DropUser dropUser;
	private CCombo cboPoolStatus;
	private Text txtInvoiceCode;
	private Text txtTaxcodeStart;
	private Text txtTaxcodeLimit;
	private Text txtTicketStart;
	private Text txtTicketLimit;
	private Text txtTicketCurrent;

	private Text txtTotalNum;
	private Text txtUseNum;
	private Text txtUnuseNum;
	
	private Text txtRemark;
	
	private ItsTicketpool itsTicketpool;

	protected ItsTicketpoolEditUi(Shell shell,GridView gridView,String operType) {
		super(shell);
		this.gridView = gridView;
		this.operType = operType;
	}

	protected void configureShell(Shell shell){
		super.configureShell(shell);
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		shell.setBackground(StyleFinal.DIALOG_BACK_GROUND);
		shell.setText("车票管理-"+operType);
	}
	
	@Override
	protected Point getInitialLocation(Point initialSize) {
		Rectangle screenSize =  Display.getDefault().getClientArea();
		return new Point((screenSize.width - initialSize.x)/2, (screenSize.height - initialSize.y)/2);
	}
	
	@Override
	protected Point getInitialSize(){
        return new Point(535,370);
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
		groupMain.setText("票据信息");
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
		
		KLabel lbTickettype = new KLabel(groupMain, SWT.RIGHT);
		lbTickettype.setFont(StyleFinal.SYS_FONT);
		lbTickettype.setText("票据名称:");
		GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbTickettype.setLayoutData(gridData);
		
		dropTickettype = new DropTickettype(groupMain, SWT.BORDER);
		dropTickettype.droptext.txtShow.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		dropTickettype.setLayoutData(gridData);
		dropTickettype.droptext.txtShow.addKeyListener(new KeyListener() {
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				if(arg0.keyCode == 13 || arg0.keyCode==SWT.KEYPAD_CR){
					try {
						if ("发放".equals(operType)){
							IItsTicketpool iItsTicketpool = new ImpItsTicketpool();
							List<ItsTicketpool> ticketpools =iItsTicketpool.queryAllByCustom(CommFinal.organize.getOrganizeSeq(),
									dropTickettype.droptext.getValue(), "1", null, "2");
							if (null == ticketpools || ticketpools.size()<=0){
								ticketpools =iItsTicketpool.queryAllByCustom(CommFinal.organize.getOrganizeSeq(),
										dropTickettype.droptext.getValue(), "1", null, "1");
							}
							if (null != ticketpools && ticketpools.size()>0){
								itsTicketpool = ticketpools.get(0);
								txtInvoiceCode.setText(itsTicketpool.getInvoiceCode());
								txtTicketStart.setText(itsTicketpool.getTicketCurrent());
								txtTicketCurrent.setText(itsTicketpool.getTicketCurrent());
								
								EpdTickettype tickettype = (EpdTickettype) dropTickettype.droptext.getObject();
								//票据张数
								String ticketNum = String.valueOf(tickettype.getTicketNum());
								String useNum =  String.valueOf(Long.valueOf(StringUtils.getNumbers(itsTicketpool.getTicketCurrent()))
										-Long.valueOf(StringUtils.getNumbers(itsTicketpool.getTicketStart())));
								int unuseNum = (int) (Long.valueOf(StringUtils.getNumbers(itsTicketpool.getTicketLimit()))
										-Long.valueOf(StringUtils.getNumbers(itsTicketpool.getTicketCurrent())));
								
								//开始发票号
								String taxcodeNumber = itsTicketpool.getTaxcodeStart();
								int taxcodeLength = taxcodeNumber.length();
								txtTaxcodeStart.setText(String.format("%0" + String.valueOf(taxcodeLength) +"d", 
										Long.parseLong(taxcodeNumber) + Long.parseLong(useNum)));
								
								if (unuseNum>=tickettype.getTicketNum()){
									//入库票据状态
									itsTicketpool.setPoolStatus("2");
									//结束发票号
									taxcodeNumber = txtTaxcodeStart.getText();
									taxcodeLength = taxcodeNumber.length();
									txtTaxcodeLimit.setText(String.format("%0" + String.valueOf(taxcodeLength) +"d", 
											Long.parseLong(taxcodeNumber) + Long.parseLong(ticketNum)-1));
									//结束车票号
									String ticketNumber = txtTicketStart.getText();
									int ticketLength = ticketNumber.length();
									txtTicketLimit.setText(String.format("%0" + String.valueOf(ticketLength) +"d", 
											Long.parseLong(ticketNumber) + Long.parseLong(ticketNum)-1));
								}else{
									//入库票据状态
									itsTicketpool.setPoolStatus("3");
									//结束发票号
									txtTaxcodeLimit.setText(itsTicketpool.getTaxcodeLimit());
									//结束车票号
									txtTicketLimit.setText(itsTicketpool.getTicketLimit());
								}
								if ("3".equals(itsTicketpool.getPoolStatus())){
									itsTicketpool.setTicketCurrent(itsTicketpool.getTicketLimit());
								}else{
									String ticketNumber = txtTicketLimit.getText();
									int ticketLength = ticketNumber.length();
									itsTicketpool.setTicketCurrent(String.format("%0" + String.valueOf(ticketLength) +"d", 
											Long.parseLong(ticketNumber) + 1));
								}
								
								
							}
						}
					} catch (UserBusinessException e) {
						LogUtil.operLog(e,"E",true,true);
					}
				}
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		KLabel lbUser = new KLabel(groupMain, SWT.RIGHT);
		lbUser.setFont(StyleFinal.SYS_FONT);
		lbUser.setText("用户名称:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbUser.setLayoutData(gridData);
		
		dropUser = new DropUser(groupMain, SWT.BORDER);
		dropUser.droptext.txtShow.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		dropUser.setLayoutData(gridData);
		
		KLabel lbPoolStatus = new KLabel(groupMain, SWT.RIGHT);
		lbPoolStatus.setFont(StyleFinal.SYS_FONT);
		lbPoolStatus.setText("票据状态:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbPoolStatus.setLayoutData(gridData);
		
		cboPoolStatus = new CCombo(groupMain, SWT.BORDER);
		cboPoolStatus.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		cboPoolStatus.setLayoutData(gridData);
		
		Label label = new Label(groupMain, SWT.NONE);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.horizontalSpan=2;
		label.setLayoutData(gridData);
		
		KLabel lbInvoiceCode = new KLabel(groupMain, SWT.RIGHT);
		lbInvoiceCode.setFont(StyleFinal.SYS_FONT);
		lbInvoiceCode.setText("发票代码:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbInvoiceCode.setLayoutData(gridData);
		
		txtInvoiceCode = new Text(groupMain, SWT.BORDER);
		txtInvoiceCode.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtInvoiceCode.setLayoutData(gridData);

		label = new Label(groupMain, SWT.NONE);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.horizontalSpan=2;
		label.setLayoutData(gridData);
		
		KLabel lbTaxcodeStart = new KLabel(groupMain, SWT.RIGHT);
		lbTaxcodeStart.setFont(StyleFinal.SYS_FONT);
		lbTaxcodeStart.setText("开始发票号:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbTaxcodeStart.setLayoutData(gridData);
		
		txtTaxcodeStart = new Text(groupMain, SWT.BORDER);
		txtTaxcodeStart.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtTaxcodeStart.setLayoutData(gridData);
		
		KLabel lbTaxcodeLimit = new KLabel(groupMain, SWT.RIGHT);
		lbTaxcodeLimit.setFont(StyleFinal.SYS_FONT);
		lbTaxcodeLimit.setText("结束发票号:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbTaxcodeLimit.setLayoutData(gridData);
		
		txtTaxcodeLimit = new Text(groupMain, SWT.BORDER);
		txtTaxcodeLimit.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtTaxcodeLimit.setLayoutData(gridData);
		
		KLabel lbTicketStart = new KLabel(groupMain, SWT.RIGHT);
		lbTicketStart.setFont(StyleFinal.SYS_FONT);
		lbTicketStart.setText("开始车票号:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbTicketStart.setLayoutData(gridData);
		
		txtTicketStart = new Text(groupMain, SWT.BORDER);
		txtTicketStart.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtTicketStart.setLayoutData(gridData);
		txtTicketStart.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				if (!CommFinal.OPER_TYPE_UPDATE.equals(operType)){
					txtTicketCurrent.setText(txtTicketStart.getText());
				}
				clacInit();
			}
		});
		
		KLabel lbTicketLimit = new KLabel(groupMain, SWT.RIGHT);
		lbTicketLimit.setFont(StyleFinal.SYS_FONT);
		lbTicketLimit.setText("结束车票号:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbTicketLimit.setLayoutData(gridData);
		
		txtTicketLimit = new Text(groupMain, SWT.BORDER);
		txtTicketLimit.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtTicketLimit.setLayoutData(gridData);
		txtTicketLimit.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				clacInit();
			}
		});
		
		KLabel lbTicketCurrent = new KLabel(groupMain, SWT.RIGHT);
		lbTicketCurrent.setFont(StyleFinal.SYS_FONT);
		lbTicketCurrent.setText("当前车票号:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbTicketCurrent.setLayoutData(gridData);
		
		txtTicketCurrent = new Text(groupMain, SWT.BORDER);
		txtTicketCurrent.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtTicketCurrent.setLayoutData(gridData);
		if (CommFinal.OPER_TYPE_UPDATE.equals(operType)){
			txtTicketCurrent.setEditable(true);
			txtTicketCurrent.addModifyListener(new ModifyListener() {
				public void modifyText(ModifyEvent arg0) {
					clacInit();
				}
			});
		}else{
			txtTicketCurrent.setEditable(false);
		}
		label = new Label(groupMain, SWT.NONE);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.horizontalSpan=2;
		label.setLayoutData(gridData);
		
		Label lbRemark = new Label(groupMain, SWT.RIGHT);
		lbRemark.setFont(StyleFinal.SYS_FONT);
		lbRemark.setText("备注:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbRemark.setLayoutData(gridData);
		
		txtRemark = new Text(groupMain, SWT.BORDER);
		txtRemark.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.horizontalSpan=3;
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtRemark.setLayoutData(gridData);
		
		Label lbTotalNum = new Label(groupMain, SWT.RIGHT);
		lbTotalNum.setFont(StyleFinal.SYS_FONT);
		lbTotalNum.setText("总数量:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbTotalNum.setLayoutData(gridData);
		
		txtTotalNum = new Text(groupMain, SWT.BORDER);
		txtTotalNum.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtTotalNum.setLayoutData(gridData);
		txtTotalNum.setEnabled(false);
		
		label = new Label(groupMain, SWT.NONE);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.horizontalSpan=2;
		label.setLayoutData(gridData);
		
		Label lbUseNum = new Label(groupMain, SWT.RIGHT);
		lbUseNum.setFont(StyleFinal.SYS_FONT);
		lbUseNum.setText("使用数量:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbUseNum.setLayoutData(gridData);
		
		txtUseNum = new Text(groupMain, SWT.BORDER);
		txtUseNum.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtUseNum.setLayoutData(gridData);
		txtUseNum.setEnabled(false);
		
		Label lbUnuseNum = new Label(groupMain, SWT.RIGHT);
		lbUnuseNum.setFont(StyleFinal.SYS_FONT);
		lbUnuseNum.setText("未用数量:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbUnuseNum.setLayoutData(gridData);
		
		txtUnuseNum = new Text(groupMain, SWT.BORDER);
		txtUnuseNum.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtUnuseNum.setLayoutData(gridData);
		txtUnuseNum.setEnabled(false);
		
		initData();

		callMethod.bindEnterEvent(this, dropTickettype.droptext.txtShow, dropUser.droptext.txtShow, "");
		callMethod.bindEnterEvent(this, dropUser.droptext.txtShow, cboPoolStatus, "");
		callMethod.bindEnterEvent(this, cboPoolStatus, txtInvoiceCode, "");
		callMethod.bindEnterEvent(this, txtInvoiceCode, txtTaxcodeStart, "");
		callMethod.bindEnterEvent(this, txtTaxcodeStart, txtTaxcodeLimit, "");
		callMethod.bindEnterEvent(this, txtTaxcodeLimit, txtTicketStart, "");
		callMethod.bindEnterEvent(this, txtTicketStart, txtTicketLimit, "");
		callMethod.bindEnterEvent(this, txtTicketLimit, txtTicketCurrent, "");
		callMethod.bindEnterEvent(this, txtTicketCurrent, txtRemark, "");
		callMethod.bindEnterEvent(this, txtRemark, null, "");
		return compMain;
	}
	
	private void initData(){
		dropTickettype.initCategory("2");
		dropUser.init(CommFinal.organize.getOrganizeSeq());
		cboPoolStatus.setItems(CommFinal.getAllName(TraffFinal.ARR_POOL_STATUS));
		if (!CommFinal.OPER_TYPE_ADD.equals(operType)){
			if(CommFinal.OPER_TYPE_UPDATE.equals(operType)){
				ItsTicketpool itsTicketpool = (ItsTicketpool) gridView.getSelection();
				dropTickettype.droptext.setEnabled(false);
				txtInvoiceCode.setEditable(false);
				txtTaxcodeStart.setEditable(false);
				txtTaxcodeLimit.setEditable(false);
				txtTicketStart.setEditable(false);
				txtTicketLimit.setEditable(false);
				if (null != itsTicketpool && !"".equals(itsTicketpool.getTicketpoolSeq())){
					dropTickettype.droptext.setValue(itsTicketpool.getTickettypeSeq());
					dropUser.droptext.setValue(itsTicketpool.getUserCode());
					cboPoolStatus.setText(CommFinal.getItemName(TraffFinal.ARR_POOL_STATUS,itsTicketpool.getPoolStatus()));
					txtInvoiceCode.setText(itsTicketpool.getInvoiceCode());
					txtTaxcodeStart.setText(itsTicketpool.getTaxcodeStart());
					txtTaxcodeLimit.setText(itsTicketpool.getTaxcodeLimit());
					txtTicketStart.setText(itsTicketpool.getTicketStart());
					txtTicketLimit.setText(itsTicketpool.getTicketLimit());
					txtTicketCurrent.setText(itsTicketpool.getTicketCurrent());
					txtRemark.setText(itsTicketpool.getRemark());
				}
				if ("1".equals(itsTicketpool.getOperType())){
					dropUser.droptext.setEnabled(false);
				}else {
					dropUser.droptext.txtShow.forceFocus();
					dropUser.droptext.txtShow.selectAll();
				}
			}else{
				dropTickettype.droptext.setEditable(false);
				txtInvoiceCode.setEditable(false);
				txtTaxcodeStart.setEditable(false);
				txtTaxcodeLimit.setEditable(false);
				txtTicketStart.setEditable(false);
				txtTicketLimit.setEditable(false);
			}
		}else{
			dropTickettype.droptext.setEditable(false);
			dropUser.droptext.setValue(CommFinal.user.getUserCode());
			dropTickettype.droptext.txtShow.forceFocus();
			dropTickettype.droptext.txtShow.selectAll();
		}
	}

	protected void buttonPressed(int buttonId){
		try {
			if(1 == buttonId){
				/**
				 * 确定
				 */
				ItsTicketpool ticketpool = validData();
				if (null != ticketpool){
					IItsTicketpool iItsTicketpool = new ImpItsTicketpool();
					if (CommFinal.OPER_TYPE_ADD.equals(operType)){
						ItsTicketpool newItsTicketpool = iItsTicketpool.insert(ticketpool,CommFinal.initConfig());
						gridView.addRow(newItsTicketpool);
						clearData();
					} else if(CommFinal.OPER_TYPE_UPDATE.equals(operType)){
						iItsTicketpool.update(ticketpool,CommFinal.initConfig());
						gridView.updateRow(gridView.getSelectionIndex(), ticketpool);
						close();
					} else if("发放".equals(operType)){
						ItsTicketpool newItsTicketpool = iItsTicketpool.send(itsTicketpool,
								ticketpool,CommFinal.initConfig());
						gridView.addRow(newItsTicketpool);
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
	
	private ItsTicketpool validData(){
		String currTime = DateUtils.getNow(DateUtils.FORMAT_LONG);
		ItsTicketpool itsTicketpool = new ItsTicketpool();
		itsTicketpool.setOrganizeSeq(CommFinal.organize.getOrganizeSeq());
		if (CommFinal.OPER_TYPE_UPDATE.equals(operType)){
			itsTicketpool = (ItsTicketpool) gridView.getSelection();
		}
		itsTicketpool.setUpdateTime(currTime);
		if (null != dropTickettype.droptext.getValue() && dropTickettype.droptext.getValue().length()>0){
			itsTicketpool.setTickettypeSeq(dropTickettype.droptext.getValue());
			itsTicketpool.setTicketName(dropTickettype.droptext.getText());
		}else{
			MsgBox.warning(getParentShell(),"请输入或选择票据类型！");
			dropTickettype.droptext.txtShow.forceFocus();
			return null;
		}
		if (null != dropUser.droptext.getValue() && dropUser.droptext.getValue().length()>0){
			itsTicketpool.setUserCode(dropUser.droptext.getValue());
			itsTicketpool.setUserName(dropUser.droptext.getText());
		}else{
			MsgBox.warning(getParentShell(),"请输入或选择用户！");
			dropUser.droptext.txtShow.forceFocus();
			return null;
		}
		
		if (null != cboPoolStatus.getText() && cboPoolStatus.getText().length()>0){
			itsTicketpool.setPoolStatus(CommFinal.getItemValue(TraffFinal.ARR_POOL_STATUS, cboPoolStatus.getText()));
		}else{
			MsgBox.warning(getParentShell(),"请选择票据状态！");
			cboPoolStatus.forceFocus();
			return null;
		}
		if (null != txtInvoiceCode.getText() && txtInvoiceCode.getText().length()>0){
			itsTicketpool.setInvoiceCode(txtInvoiceCode.getText());
		}else{
			MsgBox.warning(getParentShell(),"请输入发票代码！");
			txtInvoiceCode.forceFocus();
			return null;
		}
		if (null != txtTaxcodeStart.getText() && txtTaxcodeStart.getText().length()>0){
			itsTicketpool.setTaxcodeStart(txtTaxcodeStart.getText());
		}else{
			MsgBox.warning(getParentShell(),"请输入开始发票号码！");
			txtTaxcodeStart.forceFocus();
			return null;
		}
		if (null != txtTaxcodeLimit.getText() && txtTaxcodeLimit.getText().length()>0){
			itsTicketpool.setTaxcodeLimit(txtTaxcodeLimit.getText());
		}else{
			MsgBox.warning(getParentShell(),"请输入结束发票号码！");
			txtTaxcodeLimit.forceFocus();
			return null;
		}
		if (null != txtTicketStart.getText() && txtTicketStart.getText().length()>0){
			itsTicketpool.setTicketStart(txtTicketStart.getText());
		}else{
			MsgBox.warning(getParentShell(),"请输入开始车票号码！");
			txtTicketStart.forceFocus();
			return null;
		}
		if (null != txtTicketLimit.getText() && txtTicketLimit.getText().length()>0){
			itsTicketpool.setTicketLimit(txtTicketLimit.getText());
		}else{
			MsgBox.warning(getParentShell(),"请输入结束车票号码！");
			txtTicketLimit.forceFocus();
			return null;
		}
		if (null != txtTicketCurrent.getText() && txtTicketCurrent.getText().length()>0){
			itsTicketpool.setTicketCurrent(txtTicketCurrent.getText());
		}else{
			MsgBox.warning(getParentShell(),"请输入结束车票号码！");
			txtTicketCurrent.forceFocus();
			return null;
		}
		if (CommFinal.OPER_TYPE_ADD.equals(operType)){
			itsTicketpool.setOperType("1");
		} else if ("发放".equals(operType)){
			itsTicketpool.setOperType("2");
		}
		//验证票号
		int taxcodeNum = (int) (Long.valueOf(StringUtils.getNumbers(txtTaxcodeLimit.getText()))
				-Long.valueOf(StringUtils.getNumbers(txtTaxcodeStart.getText()))+1);
		int ticketNum = (int) (Long.valueOf(StringUtils.getNumbers(txtTicketLimit.getText()))
				-Long.valueOf(StringUtils.getNumbers(txtTicketStart.getText()))+1);
		if (taxcodeNum != ticketNum){
			MsgBox.warning(getParentShell(),"发票号码数("+ taxcodeNum +")与车票号码数("+ticketNum+")不一致，请核对！");
			txtTaxcodeStart.forceFocus();
			txtTaxcodeStart.selectAll();
			return null;
		}
		
		itsTicketpool.setTotalNum((int) (Long.valueOf(StringUtils.getNumbers(itsTicketpool.getTicketLimit()))
				-Long.valueOf(StringUtils.getNumbers(itsTicketpool.getTicketStart())))+1);
		itsTicketpool.setUseNum((int) (Long.valueOf(StringUtils.getNumbers(itsTicketpool.getTicketCurrent()))
				-Long.valueOf(StringUtils.getNumbers(itsTicketpool.getTicketStart()))));
		itsTicketpool.setUnuseNum(itsTicketpool.getTotalNum()-itsTicketpool.getUseNum());
				
		itsTicketpool.setOperUser(CommFinal.user.getUserCode());
		itsTicketpool.setOperName(CommFinal.user.getUserName());
		itsTicketpool.setOperTime(currTime);
		itsTicketpool.setRemark(txtRemark.getText());
		
		return itsTicketpool;
	}
	
	private void clearData(){
		dropTickettype.droptext.setValue("");
		dropUser.droptext.setValue("");
		cboPoolStatus.setText("");
		txtInvoiceCode.setText("");
		txtTaxcodeStart.setText("");
		txtTaxcodeLimit.setText("");
		txtTicketStart.setText("");
		txtTicketLimit.setText("");
		txtTicketCurrent.setText("");
		txtRemark.setText("");
		dropTickettype.droptext.txtShow.forceFocus();
		dropTickettype.droptext.txtShow.selectAll();
	}
	
	private void clacInit(){
		String startNo = txtTicketStart.getText();
		String endNo = txtTicketLimit.getText();
		String currentNo = txtTicketCurrent.getText();
		
		if (null == startNo || startNo.length()<=0){
			startNo = "0";
		}
		if (null == endNo || endNo.length()<=0){
			endNo = "0";
		}
		if (null == currentNo || currentNo.length()<=0){
			currentNo = "0";
		}
		txtTotalNum.setText(String.valueOf(Long.valueOf(StringUtils.getNumbers(endNo))
				-Long.valueOf(StringUtils.getNumbers(startNo))+1));
		txtUseNum.setText(String.valueOf(Long.valueOf(StringUtils.getNumbers(currentNo))
				-Long.valueOf(StringUtils.getNumbers(startNo))));
		txtUnuseNum.setText(String.valueOf(Long.valueOf(txtTotalNum.getText())-Long.valueOf(txtUseNum.getText())));
	}
}
