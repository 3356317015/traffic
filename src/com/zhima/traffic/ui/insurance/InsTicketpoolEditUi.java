package com.zhima.traffic.ui.insurance;

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
import com.zhima.traffic.action.insurance.IInsTicketpool;
import com.zhima.traffic.action.insurance.impl.ImpInsTicketpool;
import com.zhima.traffic.comm.TraffFinal;
import com.zhima.traffic.drop.DropInsurancetype;
import com.zhima.traffic.model.InsTicketpool;
import com.zhima.traffic.model.InsTickettype;
import com.zhima.util.DateUtils;
import com.zhima.util.SWTResourceManager;
import com.zhima.util.StringUtils;
import com.zhima.util.log4j.LogUtil;
import com.zhima.widget.KLabel;
import com.zhima.widget.MsgBox;
import com.zhima.widget.grid.GridView;

public class InsTicketpoolEditUi extends Dialog {
	private GridView gridView;
	private String operType;
	
	private DropInsurancetype dropInsurancetype;
	private DropUser dropUser;
	private CCombo cboPoolStatus;
	private Text txtInsuranceStart;
	private Text txtInsuranceLimit;
	private Text txtInsuranceCurrent;

	private Text txtTotalNum;
	private Text txtUseNum;
	private Text txtUnuseNum;
	
	private Text txtRemark;
	
	private InsTicketpool insTicketpool;

	protected InsTicketpoolEditUi(Shell shell,GridView gridView,String operType) {
		super(shell);
		this.gridView = gridView;
		this.operType = operType;
	}

	protected void configureShell(Shell shell){
		super.configureShell(shell);
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		shell.setBackground(StyleFinal.DIALOG_BACK_GROUND);
		shell.setText("保险票管理-"+operType);
	}
	
	@Override
	protected Point getInitialLocation(Point initialSize) {
		Rectangle screenSize =  Display.getDefault().getClientArea();
		return new Point((screenSize.width - initialSize.x)/2, (screenSize.height - initialSize.y)/2);
	}
	
	@Override
	protected Point getInitialSize(){
        return new Point(535,315);
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
		
		dropInsurancetype = new DropInsurancetype(groupMain, SWT.BORDER);
		dropInsurancetype.droptext.txtShow.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		dropInsurancetype.setLayoutData(gridData);
		dropInsurancetype.droptext.txtShow.addKeyListener(new KeyListener() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				if(arg0.keyCode == 13 || arg0.keyCode==SWT.KEYPAD_CR){
					try {
						if ("发放".equals(operType)){
							IInsTicketpool iInsTicketpool = new ImpInsTicketpool();
							List<InsTicketpool> ticketpools =iInsTicketpool.queryValid(CommFinal.organize.getOrganizeSeq(),
									dropInsurancetype.droptext.getValue(),"1",null);
							if (null != ticketpools && ticketpools.size()>0){
								insTicketpool = ticketpools.get(0);
								txtInsuranceStart.setText(insTicketpool.getInsuranceStart());
								txtInsuranceCurrent.setText(insTicketpool.getInsuranceCurrent());
								
								InsTickettype tickettype = (InsTickettype) dropInsurancetype.droptext.getObject();
								//票据张数
								String ticketNum = String.valueOf(tickettype.getTicketNum());

								int unuseNum = (int) (Long.valueOf(StringUtils.getNumbers(insTicketpool.getInsuranceLimit()))
										-Long.valueOf(StringUtils.getNumbers(insTicketpool.getInsuranceCurrent())));
								if (unuseNum>=tickettype.getTicketNum()){
									//入库票据状态
									insTicketpool.setPoolStatus("2");
									
									//结束车票号
									String ticketNumber = txtInsuranceStart.getText();
									int ticketLength = ticketNumber.length();
									txtInsuranceLimit.setText(String.format("%0" + String.valueOf(ticketLength) +"d", 
											Long.parseLong(ticketNumber) + Long.parseLong(ticketNum)-1));
								}else{
									//入库票据状态
									insTicketpool.setPoolStatus("3");
									//结束车票号
									txtInsuranceLimit.setText(insTicketpool.getInsuranceLimit());
								}
								if ("3".equals(insTicketpool.getPoolStatus())){
									insTicketpool.setInsuranceCurrent(insTicketpool.getInsuranceLimit());
								}else{
									String ticketNumber = txtInsuranceLimit.getText();
									int ticketLength = ticketNumber.length();
									insTicketpool.setInsuranceCurrent(String.format("%0" + String.valueOf(ticketLength) +"d", 
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
		
		KLabel lbInsuranceStart = new KLabel(groupMain, SWT.RIGHT);
		lbInsuranceStart.setFont(StyleFinal.SYS_FONT);
		lbInsuranceStart.setText("开始保单号:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbInsuranceStart.setLayoutData(gridData);
		
		txtInsuranceStart = new Text(groupMain, SWT.BORDER);
		txtInsuranceStart.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtInsuranceStart.setLayoutData(gridData);
		txtInsuranceStart.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				if (!CommFinal.OPER_TYPE_UPDATE.equals(operType)){
					txtInsuranceCurrent.setText(txtInsuranceStart.getText());
				}
				clacInit();
			}
		});
		
		KLabel lbInsuranceLimit = new KLabel(groupMain, SWT.RIGHT);
		lbInsuranceLimit.setFont(StyleFinal.SYS_FONT);
		lbInsuranceLimit.setText("结束保单号:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbInsuranceLimit.setLayoutData(gridData);
		
		txtInsuranceLimit = new Text(groupMain, SWT.BORDER);
		txtInsuranceLimit.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtInsuranceLimit.setLayoutData(gridData);
		txtInsuranceLimit.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				clacInit();
			}
		});
		
		KLabel lbInsuranceCurrent = new KLabel(groupMain, SWT.RIGHT);
		lbInsuranceCurrent.setFont(StyleFinal.SYS_FONT);
		lbInsuranceCurrent.setText("当前保单号:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbInsuranceCurrent.setLayoutData(gridData);
		
		txtInsuranceCurrent = new Text(groupMain, SWT.BORDER);
		txtInsuranceCurrent.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtInsuranceCurrent.setLayoutData(gridData);
		if (CommFinal.OPER_TYPE_UPDATE.equals(operType)){
			txtInsuranceCurrent.setEditable(true);
			txtInsuranceCurrent.addModifyListener(new ModifyListener() {
				public void modifyText(ModifyEvent arg0) {
					clacInit();
				}
			});
		}else{
			txtInsuranceCurrent.setEditable(false);
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

		callMethod.bindEnterEvent(this, dropInsurancetype.droptext.txtShow, dropUser.droptext.txtShow, "");
		callMethod.bindEnterEvent(this, dropUser.droptext.txtShow, cboPoolStatus, "");
		callMethod.bindEnterEvent(this, cboPoolStatus, txtInsuranceStart, "");
		callMethod.bindEnterEvent(this, txtInsuranceStart, txtInsuranceLimit, "");
		callMethod.bindEnterEvent(this, txtInsuranceLimit, txtInsuranceCurrent, "");
		callMethod.bindEnterEvent(this, txtInsuranceCurrent, txtRemark, "");
		callMethod.bindEnterEvent(this, txtRemark, null, "");
		return compMain;
	}
	
	private void initData(){
		dropUser.init(CommFinal.organize.getOrganizeSeq());
		cboPoolStatus.setItems(CommFinal.getAllName(TraffFinal.ARR_POOL_STATUS));
		if (!CommFinal.OPER_TYPE_ADD.equals(operType)){
			if(CommFinal.OPER_TYPE_UPDATE.equals(operType)){
				InsTicketpool insTicketpool = (InsTicketpool) gridView.getSelection();
				dropInsurancetype.droptext.setEnabled(false);
				txtInsuranceStart.setEditable(false);
				txtInsuranceLimit.setEditable(false);
				if (null != insTicketpool && !"".equals(insTicketpool.getTicketpoolSeq())){
					dropInsurancetype.droptext.setValue(insTicketpool.getTickettypeSeq());
					dropUser.droptext.setValue(insTicketpool.getUserCode());
					cboPoolStatus.setText(CommFinal.getItemName(TraffFinal.ARR_POOL_STATUS,insTicketpool.getPoolStatus()));
					txtInsuranceStart.setText(insTicketpool.getInsuranceStart());
					txtInsuranceLimit.setText(insTicketpool.getInsuranceLimit());
					txtInsuranceCurrent.setText(insTicketpool.getInsuranceCurrent());
					txtRemark.setText(insTicketpool.getRemark());
				}
				if ("1".equals(insTicketpool.getOperType())){
					dropUser.droptext.setEnabled(false);
				}else{
					dropUser.droptext.txtShow.forceFocus();
					dropUser.droptext.txtShow.selectAll();
				}
			}else{
				dropInsurancetype.droptext.setEditable(false);
				txtInsuranceStart.setEditable(false);
				txtInsuranceLimit.setEditable(false);
			}
		}else{
			dropInsurancetype.droptext.setEditable(false);
			dropUser.droptext.setValue(CommFinal.user.getUserCode());
			dropInsurancetype.droptext.txtShow.forceFocus();
			dropInsurancetype.droptext.txtShow.selectAll();
		}
	}

	protected void buttonPressed(int buttonId){
		try {
			if(1 == buttonId){
				/**
				 * 确定
				 */
				InsTicketpool ticketpool = validData();
				if (null != ticketpool){
					IInsTicketpool iInsTicketpool = new ImpInsTicketpool();
					if (CommFinal.OPER_TYPE_ADD.equals(operType)){
						InsTicketpool newInsTicketpool = iInsTicketpool.insert(ticketpool,CommFinal.initConfig());
						gridView.addRow(newInsTicketpool);
						clearData();
					} else if(CommFinal.OPER_TYPE_UPDATE.equals(operType)){
						iInsTicketpool.update(ticketpool,CommFinal.initConfig());
						gridView.updateRow(gridView.getSelectionIndex(), ticketpool);
						close();
					} else if("发放".equals(operType)){
						InsTicketpool newInsTicketpool = iInsTicketpool.send(insTicketpool,
								ticketpool,CommFinal.initConfig());
						gridView.addRow(newInsTicketpool);
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
	
	private InsTicketpool validData(){
		String currTime = DateUtils.getNow(DateUtils.FORMAT_LONG);
		InsTicketpool insTicketpool = new InsTicketpool();
		insTicketpool.setOrganizeSeq(CommFinal.organize.getOrganizeSeq());
		if (CommFinal.OPER_TYPE_UPDATE.equals(operType)){
			insTicketpool = (InsTicketpool) gridView.getSelection();
		}
		insTicketpool.setUpdateTime(currTime);
		if (null != dropInsurancetype.droptext.getValue() && dropInsurancetype.droptext.getValue().length()>0){
			insTicketpool.setTickettypeSeq(dropInsurancetype.droptext.getValue());
			insTicketpool.setTicketName(dropInsurancetype.droptext.getText());
			InsTickettype insTickettype = (InsTickettype) dropInsurancetype.droptext.getObject();
			insTicketpool.setCompanySeq(insTickettype.getCompanySeq());
			insTicketpool.setInsuranceCode(insTickettype.getInsuranceCode());
			insTicketpool.setInsuranceName(insTickettype.getInsuranceName());
		}else{
			MsgBox.warning(getParentShell(),"请输入或选择票据类型！");
			dropInsurancetype.droptext.txtShow.forceFocus();
			return null;
		}
		if (null != dropUser.droptext.getValue() && dropUser.droptext.getValue().length()>0){
			insTicketpool.setUserCode(dropUser.droptext.getValue());
			insTicketpool.setUserName(dropUser.droptext.getText());
		}else{
			MsgBox.warning(getParentShell(),"请输入或选择用户！");
			dropUser.droptext.txtShow.forceFocus();
			return null;
		}
		
		if (null != cboPoolStatus.getText() && cboPoolStatus.getText().length()>0){
			insTicketpool.setPoolStatus(CommFinal.getItemValue(TraffFinal.ARR_POOL_STATUS, cboPoolStatus.getText()));
		}else{
			MsgBox.warning(getParentShell(),"请选择票据状态！");
			cboPoolStatus.forceFocus();
			return null;
		}
		if (null != txtInsuranceStart.getText() && txtInsuranceStart.getText().length()>0){
			insTicketpool.setInsuranceStart(txtInsuranceStart.getText());
		}else{
			MsgBox.warning(getParentShell(),"请输入开始车票号码！");
			txtInsuranceStart.forceFocus();
			return null;
		}
		if (null != txtInsuranceLimit.getText() && txtInsuranceLimit.getText().length()>0){
			insTicketpool.setInsuranceLimit(txtInsuranceLimit.getText());
		}else{
			MsgBox.warning(getParentShell(),"请输入结束车票号码！");
			txtInsuranceLimit.forceFocus();
			return null;
		}
		if (null != txtInsuranceCurrent.getText() && txtInsuranceCurrent.getText().length()>0){
			insTicketpool.setInsuranceCurrent(txtInsuranceCurrent.getText());
		}else{
			MsgBox.warning(getParentShell(),"请输入结束车票号码！");
			txtInsuranceCurrent.forceFocus();
			return null;
		}
		if (CommFinal.OPER_TYPE_ADD.equals(operType)){
			insTicketpool.setOperType("1");
		} else if ("发放".equals(operType)){
			insTicketpool.setOperType("2");
		}
		
		insTicketpool.setTotalNum((int) (Long.valueOf(StringUtils.getNumbers(insTicketpool.getInsuranceLimit()))
				-Long.valueOf(StringUtils.getNumbers(insTicketpool.getInsuranceStart())))+1);
		insTicketpool.setUseNum((int) (Long.valueOf(StringUtils.getNumbers(insTicketpool.getInsuranceCurrent()))
				-Long.valueOf(StringUtils.getNumbers(insTicketpool.getInsuranceStart()))));
		insTicketpool.setUnuseNum(insTicketpool.getTotalNum()-insTicketpool.getUseNum());
				
		insTicketpool.setOperUser(CommFinal.user.getUserCode());
		insTicketpool.setOperName(CommFinal.user.getUserName());
		insTicketpool.setOperTime(currTime);
		insTicketpool.setRemark(txtRemark.getText());
		
		return insTicketpool;
	}
	
	private void clearData(){
		dropInsurancetype.droptext.setValue("");
		dropUser.droptext.setValue("");
		cboPoolStatus.setText("");
		txtInsuranceStart.setText("");
		txtInsuranceLimit.setText("");
		txtInsuranceCurrent.setText("");
		txtRemark.setText("");
		dropInsurancetype.droptext.txtShow.forceFocus();
		dropInsurancetype.droptext.txtShow.selectAll();
	}
	
	private void clacInit(){
		String startNo = txtInsuranceStart.getText();
		String endNo = txtInsuranceLimit.getText();
		String currentNo = txtInsuranceCurrent.getText();
		
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
