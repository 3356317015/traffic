package com.zhima.widget.stationBar;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.zhima.util.SWTResourceManager;
import com.zhima.widget.TextVerifyListener;

public class LinerFareItem extends Composite {
	private Label lbStationNo;
	private Text txtStationName;
	private Text txtFullFare;
	public Text txtBaseFare;
	private Text txtStationFare;
	private Text txtFuelFare;
	private int addWidth;
	//线路站点类
	private Station station;

	public LinerFareItem(Composite parent,int index,int addWidth,Station station) {
		super(parent, SWT.NONE);
		this.addWidth = addWidth;
		this.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		this.station = station;
		//setBackground(SWTResourceManager.getColor(SWT.COLOR_CYAN));
		createContent();
		if (null != station){
			lbStationNo.setText(String.valueOf(index));
			if(null != station.getStationName()){
				txtStationName.setText(station.getStationName());
			}
			txtFullFare.setText(String.valueOf(station.getFullFare()));
			txtBaseFare.setText(String.valueOf(station.getBaseFare()));
			txtStationFare.setText(String.valueOf(station.getStationFare()));
			txtFuelFare.setText(String.valueOf(station.getFuelFare()));
		}
	}
	
	private void createContent(){
		setLayout(new FormLayout());
		
		lbStationNo = new Label(this, SWT.BORDER|SWT.CENTER);
		lbStationNo.setToolTipText("站点序号");
		lbStationNo.setBackground(SWTResourceManager.getColor(SWT.COLOR_DARK_BLUE));
		lbStationNo.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.NORMAL));
		lbStationNo.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		FormData formData = new FormData();
		formData.top = new FormAttachment(0,2);
		formData.left = new FormAttachment(0,5);
		formData.bottom = new FormAttachment(100,-2);
		formData.width =35;
		lbStationNo.setLayoutData(formData);
		
		txtStationName = new Text(this, SWT.BORDER|SWT.CENTER|SWT.READ_ONLY);
		txtStationName.setToolTipText("站点名称");
		//lbStationName.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		txtStationName.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.NORMAL));
		txtStationName.setEnabled(false);
		formData = new FormData();
		formData.top = new FormAttachment(0,2);
		formData.left = new FormAttachment(lbStationNo,5);
		formData.bottom = new FormAttachment(100,-2);
		formData.width =130+addWidth;
		txtStationName.setLayoutData(formData);
		
		//总价
		txtFullFare = new Text(this, SWT.BORDER|SWT.CENTER);
		txtFullFare.setEnabled(false);
		//txtFullFare.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		//txtStationNum.setForeground(SWTResourceManager.getColor(SWT.COLOR_YELLOW));
		txtFullFare.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.NORMAL));
		//txtFullFare.addVerifyListener(new TextVerifyListener(1));
		formData = new FormData();
		formData.top = new FormAttachment(0,2);
		formData.left = new FormAttachment(txtStationName,5);
		formData.bottom = new FormAttachment(100,-2);
		formData.width = 70+addWidth;
		txtFullFare.setLayoutData(formData);
		//文本框得到焦点事件
		txtFullFare.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtFullFare.selectAll();
			}
		});
		//文本框的回车变为tab
		txtFullFare.addTraverseListener(new TraverseListener() {
			public void keyTraversed(TraverseEvent e) {
				if (e.detail == SWT.TRAVERSE_RETURN){
					e.detail = SWT.TRAVERSE_TAB_NEXT;
				}
			}
		});
		//文本框的修改事件
		txtFullFare.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				if(txtFullFare.getText()==""){
					station.setFullFare(0.00);
				}else{
					station.setFullFare(Double.valueOf(txtFullFare.getText().trim()));
				}
			}
		});
		
		//基础价
		txtBaseFare = new Text(this, SWT.BORDER|SWT.CENTER);
		txtBaseFare.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		//txtStationNum.setForeground(SWTResourceManager.getColor(SWT.COLOR_YELLOW));
		txtBaseFare.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.NORMAL));
		txtBaseFare.addVerifyListener(new TextVerifyListener(1));
		formData = new FormData();
		formData.top = new FormAttachment(0,2);
		formData.left = new FormAttachment(txtFullFare,5);
		formData.bottom = new FormAttachment(100,-2);
		formData.width = 70+addWidth;
		txtBaseFare.setLayoutData(formData);
		//文本框得到焦点事件
		txtBaseFare.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtBaseFare.selectAll();
			}
		});
		//文本框的回车变为tab
		txtBaseFare.addTraverseListener(new TraverseListener() {
			public void keyTraversed(TraverseEvent e) {
				if (e.detail == SWT.TRAVERSE_RETURN){
					e.detail = SWT.TRAVERSE_TAB_NEXT;
				}
			}
		});
		//文本框的修改事件
		txtBaseFare.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				if(txtBaseFare.getText()==""){
					station.setBaseFare(0.00);
				}else{
					station.setBaseFare(Double.valueOf(txtBaseFare.getText().trim()));
				}
				txtFullFare.setText(String.valueOf(
						station.getBaseFare()
						+station.getStationFare()
						+station.getFuelFare()
						+station.getOtherOne()
						+station.getOtherTwo()
						+station.getOtherThree()
						+station.getOtherFour()
						+station.getOtherFive()));
			}
		});
		
		//站务费
		txtStationFare = new Text(this, SWT.BORDER|SWT.CENTER);
		txtStationFare.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		//txtStationNum.setForeground(SWTResourceManager.getColor(SWT.COLOR_YELLOW));
		txtStationFare.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.NORMAL));
		txtStationFare.addVerifyListener(new TextVerifyListener(1));
		formData = new FormData();
		formData.top = new FormAttachment(0,2);
		formData.left = new FormAttachment(txtBaseFare,5);
		formData.bottom = new FormAttachment(100,-2);
		formData.width = 70+addWidth;
		txtStationFare.setLayoutData(formData);
		//文本框得到焦点事件
		txtStationFare.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtStationFare.selectAll();
			}
		});
		//文本框的回车变为tab
		txtStationFare.addTraverseListener(new TraverseListener() {
			public void keyTraversed(TraverseEvent e) {
				if (e.detail == SWT.TRAVERSE_RETURN){
					e.detail = SWT.TRAVERSE_TAB_NEXT;
				}
			}
		});
		//文本框的修改事件
		txtStationFare.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				if(txtStationFare.getText()==""){
					station.setStationFare(0.00);
				}else{
					station.setStationFare(Double.valueOf(txtStationFare.getText().trim()));
				}
				txtFullFare.setText(String.valueOf(
						station.getBaseFare()
						+station.getStationFare()
						+station.getFuelFare()
						+station.getOtherOne()
						+station.getOtherTwo()
						+station.getOtherThree()
						+station.getOtherFour()
						+station.getOtherFive()));
			}
		});
		
		txtFuelFare = new Text(this, SWT.BORDER|SWT.CENTER);
		txtFuelFare.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		//txtStationNum.setForeground(SWTResourceManager.getColor(SWT.COLOR_YELLOW));
		txtFuelFare.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.NORMAL));
		txtFuelFare.addVerifyListener(new TextVerifyListener(1));
		formData = new FormData();
		formData.top = new FormAttachment(0,2);
		formData.left = new FormAttachment(txtStationFare,5);
		formData.bottom = new FormAttachment(100,-2);
		formData.width = 90+addWidth;
		txtFuelFare.setLayoutData(formData);
		//文本框得到焦点事件
		txtFuelFare.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtFuelFare.selectAll();
			}
		});
		//文本框的回车变为tab
		txtFuelFare.addTraverseListener(new TraverseListener() {
			public void keyTraversed(TraverseEvent e) {
				if (e.detail == SWT.TRAVERSE_RETURN){
					e.detail = SWT.TRAVERSE_TAB_NEXT;
				}
			}
		});
		//文本框的修改事件
		txtFuelFare.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				if(txtStationFare.getText()==""){
					station.setFuelFare(0.00);
				}else{
					station.setFuelFare(Double.valueOf(txtFuelFare.getText().trim()));
				}
				txtFullFare.setText(String.valueOf(
						station.getBaseFare()
						+station.getStationFare()
						+station.getFuelFare()
						+station.getOtherOne()
						+station.getOtherTwo()
						+station.getOtherThree()
						+station.getOtherFour()
						+station.getOtherFive()));
			}
		});
	}
	
	public Station getStation(){
		return station;
	}

	public void setStation(Station station){
		this.station=station;
	}
}
