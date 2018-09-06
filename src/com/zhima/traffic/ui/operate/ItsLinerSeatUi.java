package com.zhima.traffic.ui.operate;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
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

import com.zhima.basic.CallMethod;
import com.zhima.basic.CommFinal;
import com.zhima.basic.StyleFinal;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.action.operate.IItsLiner;
import com.zhima.traffic.action.operate.IItsLinerseat;
import com.zhima.traffic.action.operate.impl.ImpItsLiner;
import com.zhima.traffic.action.operate.impl.ImpItsLinerseat;
import com.zhima.traffic.model.ItsLiner;
import com.zhima.traffic.model.ItsLinerseat;
import com.zhima.util.DateUtils;
import com.zhima.util.SWTResourceManager;
import com.zhima.util.log4j.LogUtil;
import com.zhima.widget.grid.GridView;
import com.zhima.widget.seatBar.Seat;
import com.zhima.widget.seatBar.SeatBar;

public class ItsLinerSeatUi extends Dialog {
	private Object obj;
	private GridView gridView;
	private String operType;
	
	private SeatBar seatBar;
	private Text txtAddNum;
	private Text txtHalfNum;
	private Text txtFreeNum;
	private Button btnPrintSeat;
	private List<Seat> updateSeats = new ArrayList<Seat>();
	List<Seat> seats = new ArrayList<Seat>();
	

	protected ItsLinerSeatUi(Shell shell,GridView gridView,String operType) {
		super(shell);
		this.obj = this;
		this.gridView = gridView;
		this.operType = operType;
	}

	protected void configureShell(Shell shell){
		super.configureShell(shell);
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		shell.setBackground(StyleFinal.DIALOG_BACK_GROUND);
		shell.setText("席位设置-"+operType);
	}
	
	@Override
	protected Point getInitialLocation(Point initialSize) {
		Rectangle screenSize =  Display.getDefault().getClientArea();
		return new Point((screenSize.width - initialSize.x)/2, (screenSize.height - initialSize.y)/2);
	}
	
	@Override
	protected Point getInitialSize(){
        return new Point(755,555);
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
		groupMain.setText("席位信息");
		groupMain.setFont(StyleFinal.SYS_FONT);
		groupMain.setLayout(new FormLayout());
		FormData data = new FormData();
		data.top = new FormAttachment(0,5);
		data.left = new FormAttachment(0,5);
		data.right = new FormAttachment(100,-5);
		data.bottom = new FormAttachment(100);
		groupMain.setLayoutData(data);
		
		seatBar = new SeatBar(groupMain, true, SWT.BORDER);
		FormData formData = new FormData();
		formData.top = new FormAttachment(0);
		formData.left = new FormAttachment(0);
		formData.right = new FormAttachment(100);
		formData.bottom = new FormAttachment(100, -80);
		seatBar.setLayoutData(formData);
		seatBar.bindRefresh(obj, "pageMethod");
		
		Composite composite = new Composite(groupMain, SWT.BORDER);
		composite.setLayout(new FormLayout());
		formData = new FormData();
		formData.top = new FormAttachment(seatBar,-1);
		formData.left = new FormAttachment(0);
		formData.right = new FormAttachment(100);
		formData.bottom = new FormAttachment(100, -40);
		composite.setLayoutData(formData);
		
		Composite compSeat = new Composite(composite, SWT.NONE);
		compSeat.setLayout(new FormLayout());
		formData = new FormData();
		formData.top = new FormAttachment(0);
		formData.left = new FormAttachment(0);
		formData.right = new FormAttachment(100,-200);
		compSeat.setLayoutData(formData);
		
		Label lbHalfNum = new Label(compSeat, SWT.NONE);
		lbHalfNum.setText("半票数量:");
		lbHalfNum.setFont(StyleFinal.SYS_FONT);
		formData = new FormData();
		formData.top = new FormAttachment(0,12);
		formData.left  = new FormAttachment(0, 5);
		lbHalfNum.setLayoutData(formData);
		
		txtHalfNum = new Text(compSeat, SWT.BORDER);
		txtHalfNum.setFont(StyleFinal.SYS_FONT);
		formData = new FormData();
		formData.top = new FormAttachment(0,10);
		formData.left  = new FormAttachment(lbHalfNum, 5);
		formData.width=60;
		txtHalfNum.setLayoutData(formData);
		
		Label lbFreeNum = new Label(compSeat, SWT.NONE);
		lbFreeNum.setText("免票数量:");
		lbFreeNum.setFont(StyleFinal.SYS_FONT);
		formData = new FormData();
		formData.top = new FormAttachment(0,12);
		formData.left  = new FormAttachment(txtHalfNum, 10);
		lbFreeNum.setLayoutData(formData);
		
		txtFreeNum = new Text(compSeat, SWT.BORDER);
		txtFreeNum.setFont(StyleFinal.SYS_FONT);
		formData = new FormData();
		formData.top = new FormAttachment(0,10);
		formData.left  = new FormAttachment(lbFreeNum, 5);
		formData.width=60;
		txtFreeNum.setLayoutData(formData);
		
		Label lbAddNum = new Label(compSeat, SWT.NONE);
		lbAddNum.setText("添加座位:");
		lbAddNum.setFont(StyleFinal.SYS_FONT);
		formData = new FormData();
		formData.top = new FormAttachment(0,12);
		formData.left  = new FormAttachment(txtFreeNum, 10);
		lbAddNum.setLayoutData(formData);
		
		txtAddNum = new Text(compSeat, SWT.BORDER);
		txtAddNum.setFont(StyleFinal.SYS_FONT);
		formData = new FormData();
		formData.top = new FormAttachment(0,10);
		formData.left  = new FormAttachment(lbAddNum, 5);
		formData.width=60;
		txtAddNum.setLayoutData(formData);
		txtAddNum.addKeyListener(new KeyListener() {
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				if(arg0.keyCode == 13 || arg0.keyCode==SWT.KEYPAD_CR
						|| arg0.keyCode==16777227){
					int addCount =  Integer.valueOf(txtAddNum.getText());
					if (addCount>0){
						/*List<Seat> newSeats = new ArrayList<Seat>();
						
						List<Seat> seats = seatBar.getDataList();
						for (int i = 0; i < seats.size(); i++) {
							if (null != seats.get(i).getSeatSeq() && seats.get(i).getSeatSeq().length()>0){
								newSeats.add(seats.get(i));
							}
						}
						int seatCount = seatBar.getTotalCount();*/
						for (int i = 0; i < addCount; i++) {
							Seat seat = new Seat();
							seat.setSeatId(String.valueOf(seats.size()+1));
							seat.setSeatState(1);
							seats.add(seat);
							updateSeats.add(seat);
						}
						seatBar.setTotalCount(seats.size());
						List<Seat> barSeats = new ArrayList<Seat>();
						if (null != seats && seats.size()>0){
							for (int i = seats.size()-addCount; i < seats.size(); i++) {
								if (i<seats.size()){
									barSeats.add(seats.get(i));
								}
							}
						}
						seatBar.createSeats(barSeats);
					}
					
					txtAddNum.selectAll();
				}
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		btnPrintSeat = new Button(composite, SWT.CHECK);
		btnPrintSeat.setText("打印座位号(&P)");
		btnPrintSeat.setFont(StyleFinal.SYS_FONT);
		formData = new FormData();
		formData = new FormData();
		formData.top = new FormAttachment(compSeat,10,SWT.TOP);
		formData.right = new FormAttachment(100);
		btnPrintSeat.setLayoutData(formData);
		
		Button btnOpen = new Button(groupMain, SWT.NONE);
		btnOpen.setText("可售(&S)");
		btnOpen.setFont(StyleFinal.SYS_FONT);
		formData = new FormData();
		formData.top = new FormAttachment(seatBar,50);
		formData.left = new FormAttachment(30);
		formData.width=80;
		btnOpen.setLayoutData(formData);
		btnOpen.addMouseListener(new MouseListener() {
			@Override
			public void mouseUp(MouseEvent arg0) {
			}
			@Override
			public void mouseDown(MouseEvent arg0) {
				updateSeats(1);
			}
			@Override
			public void mouseDoubleClick(MouseEvent arg0) {
			}
		});
		
		Button btnClose = new Button(groupMain, SWT.NONE);
		btnClose.setText("禁售(&C)");
		btnClose.setFont(StyleFinal.SYS_FONT);
		formData = new FormData();
		formData.top = new FormAttachment(seatBar,50);
		formData.left = new FormAttachment(btnOpen,15);
		formData.width=80;
		btnClose.setLayoutData(formData);
		btnClose.addMouseListener(new MouseListener() {
			@Override
			public void mouseUp(MouseEvent arg0) {
			}
			
			@Override
			public void mouseDown(MouseEvent arg0) {
				updateSeats(0);
			}
			
			@Override
			public void mouseDoubleClick(MouseEvent arg0) {
			}
		});
		
		Button btnDeal = new Button(groupMain, SWT.NONE);
		btnDeal.setText("配载(&D)");
		btnDeal.setFont(StyleFinal.SYS_FONT);
		formData = new FormData();
		formData.top = new FormAttachment(seatBar,50);
		formData.left = new FormAttachment(btnClose,15);
		formData.width=80;
		btnDeal.setLayoutData(formData);
		btnDeal.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseUp(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseDown(MouseEvent arg0) {
				// TODO Auto-generated method stub
				updateSeats(4);
				
			}
			
			@Override
			public void mouseDoubleClick(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		Label lbLegend = new Label(groupMain, SWT.NONE);
		lbLegend.setText("图\r\n例");
		lbLegend.setFont(StyleFinal.SYS_FONT);
		lbLegend.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		formData = new FormData();
		formData.top = new FormAttachment(composite,8);
		formData.left = new FormAttachment(0,5);
		lbLegend.setLayoutData(formData);
		
		Label lbText = new Label(groupMain, SWT.NONE);
		lbText.setText("可售  已售  已检  禁售  配载");
		lbText.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		formData = new FormData();
		formData.top = new FormAttachment(lbLegend,3,SWT.TOP);
		formData.left = new FormAttachment(lbLegend,10);
		lbText.setLayoutData(formData);
		
		initData();
		
		return compMain;
	}
	
	private void initData(){
		try {
			CallMethod callMethod = new CallMethod();
			callMethod.bindEnterEvent(this, txtHalfNum, txtFreeNum, "");
			callMethod.bindEnterEvent(this, txtFreeNum, txtAddNum, "");
			callMethod.bindEnterEvent(this, txtAddNum, null, "");
			if (CommFinal.OPER_TYPE_UPDATE.equals(operType)){
				ItsLiner itsLiner = (ItsLiner) gridView.getSelection();
				if (null != itsLiner && !"".equals(itsLiner.getLinerSeq())){
					IItsLiner iItsLiner = new ImpItsLiner();
					IItsLinerseat iItsLinerseat = new ImpItsLinerseat();
					List<ItsLiner> itsLiners = iItsLiner.queryByPK(itsLiner.getLinerSeq());
					if (null != itsLiners && itsLiners.size()>0){
						txtHalfNum.setText(String.valueOf(itsLiners.get(0).getHalfNum()));
						txtFreeNum.setText(String.valueOf(itsLiners.get(0).getFreeNum()));
						if (1== itsLiners.get(0).getIfPrintseat()){
							btnPrintSeat.setSelection(true);
						}
						List<ItsLinerseat> itsLinerseats = iItsLinerseat.queryByLinerSeq(itsLiners.get(0).getLinerSeq());
						if(null != itsLinerseats && itsLinerseats.size()>0){
							for (int i = 0; i < itsLinerseats.size(); i++) {
								Seat seat = new Seat();
								seat.setSeatSeq(itsLinerseats.get(i).getLinerseatSeq());
								seat.setSeatId(String.valueOf(itsLinerseats.get(i).getSeatId()));
								seat.setSeatState(itsLinerseats.get(i).getSeatStatus());
								seats.add(seat);
							}
							pageMethod();
						}
					}
				}
			}
			txtHalfNum.forceFocus();
			txtHalfNum.selectAll();
		} catch (UserBusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void updateSeats(int seatState){
		List<Seat> seats  = seatBar.getSelectionSeats();
		if(null != seats && seats.size() > 0){
			int[] indexs = seatBar.getSelectionIndexs();
			List<Composite> comps = seatBar.getSelectionComps();
			boolean isAdd = true;
			for(int i=0;i<seats.size();i++){
				if(2==seats.get(i).getSeatState()){
					continue;
				}
				seats.get(i).setSeatState(seatState);
				
				if (null != updateSeats && updateSeats.size()>0){
					for (int j = 0; j < updateSeats.size(); j++) {
						if (updateSeats.get(j).getSeatId().equals(seats.get(i).getSeatId())){
							isAdd =false;
							updateSeats.get(j).setSeatState(seats.get(i).getSeatState());
							break;
						}
					}
				}
				if (isAdd == true){
					updateSeats.add(seats.get(i));
				}
				
			}
			seatBar.upSeats(seats, indexs, comps);
		}
	}
	
	protected void buttonPressed(int buttonId){
		try {
			if(1 == buttonId){
				/**
				 * 确定
				 */
				ItsLiner itsLiner = validData();
				if (null != itsLiner){
					IItsLinerseat iItsLinerseat = new ImpItsLinerseat();
					if(CommFinal.OPER_TYPE_UPDATE.equals(operType)){
						List<ItsLinerseat> linerseats = new ArrayList<ItsLinerseat>();
						if (null !=  updateSeats && updateSeats.size()>0){
							for (int i = 0; i < updateSeats.size(); i++) {
								ItsLinerseat linerseat = new ItsLinerseat();
								linerseat.setLinerSeq(itsLiner.getLinerSeq());
								linerseat.setLinerDate(itsLiner.getLinerDate());
								linerseat.setLinerId(itsLiner.getLinerId());
								linerseat.setLinerseatSeq(updateSeats.get(i).getSeatSeq());
								linerseat.setSeatType(1);
								linerseat.setSeatId(Integer.valueOf(updateSeats.get(i).getSeatId()));
								linerseat.setSeatStatus(updateSeats.get(i).getSeatState());
								linerseat.setUpdateTime(itsLiner.getUpdateTime());
								linerseats.add(linerseat);
							}
						}
						iItsLinerseat.updateAttribute(itsLiner,linerseats,CommFinal.initConfig());
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
		itsLiner.setHalfNum(Integer.valueOf(txtHalfNum.getText()));
		itsLiner.setFreeNum(Integer.valueOf(txtFreeNum.getText()));
		itsLiner.setSeatNum(seats.size());
		if (btnPrintSeat.getSelection()){
			itsLiner.setIfPrintseat(1);
		}else{
			itsLiner.setIfPrintseat(0);
		}
		return itsLiner;
	}
	
	public void pageMethod(){
		int start = seatBar.getStart();
		int limit = seatBar.getLimit();
		List<Seat> barSeats = new ArrayList<Seat>();
		if (null != seats && seats.size()>0){
			for (int i = start; i < start+limit; i++) {
				if (i<seats.size()){
					barSeats.add(seats.get(i));
				}
			}
		}
		seatBar.createSeats(barSeats);
		seatBar.setTotalCount(seats.size());
	}
	
}
