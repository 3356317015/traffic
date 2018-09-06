package com.zhima.widget.stationBar;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.zhima.util.SWTResourceManager;
import com.zhima.widget.TextVerifyListener;

public class PlanStationItem extends Composite {
	//正确的图形
	private Image rightImage = SWTResourceManager.getImage("images/station/check.png");
	//错误图形
	private Image wrongImage = SWTResourceManager.getImage("images/station/uncheck.png");
	//线路站点类
	private Station station;
	
	private int index;

	public PlanStationItem(Composite parent,int index,Station station) {
		super(parent, SWT.BORDER);
		this.index = index;
		this.station = station;
		//setBackground(SWTResourceManager.getColor(SWT.COLOR_CYAN));
		createContent();
	}
	
	private void createContent(){
		setLayout(new FormLayout());
		
		Label lbStationNo = new Label(this, SWT.BORDER|SWT.CENTER);
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
		
		final Label lbIfSale = new Label(this, SWT.BORDER|SWT.CENTER);
		lbIfSale.setToolTipText("鼠标点击设置站点是否允许售票。");
		formData = new FormData();
		formData.top = new FormAttachment(0,2);
		formData.left = new FormAttachment(lbStationNo,5);
		formData.bottom = new FormAttachment(100,-2);
		formData.width = 32;
		lbIfSale.setLayoutData(formData);
		
		//图标的单击事件
		lbIfSale.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				if (null != station){
					if(0==station.getIfSale()){
						station.setIfSale(1);
						lbIfSale.setImage(rightImage);
					}else if(1==station.getIfSale()){
						station.setIfSale(0);
						lbIfSale.setImage(wrongImage);
					}
				}

			}
		});
		
		//是否显示站点售票数量对话框
		final Text txtStationNum = new Text(this, SWT.BORDER|SWT.CENTER);
		txtStationNum.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		//txtStationNum.setForeground(SWTResourceManager.getColor(SWT.COLOR_YELLOW));
		txtStationNum.setToolTipText("限制站点售票数量，0为不限制。");
		txtStationNum.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.NORMAL));
		txtStationNum.addVerifyListener(new TextVerifyListener(1));
		formData = new FormData();
		formData.top = new FormAttachment(0,2);
		formData.left = new FormAttachment(lbIfSale,5);
		formData.bottom = new FormAttachment(100,-2);
		formData.width = 30;
		txtStationNum.setLayoutData(formData);
		//文本框得到焦点事件
		txtStationNum.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtStationNum.selectAll();
			}
		});
		//文本框的回车变为tab
		txtStationNum.addTraverseListener(new TraverseListener() {
			public void keyTraversed(TraverseEvent e) {
				if (e.detail == SWT.TRAVERSE_RETURN){
					e.detail = SWT.TRAVERSE_TAB_NEXT;
				}
			}
		});
		//文本框的修改事件
		txtStationNum.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				if(txtStationNum.getText()==""){
					station.setStationNum(0);
				}else{
					station.setStationNum(Integer.valueOf(txtStationNum.getText().trim()));
				}
			}
		});

		Text txtStationName = new Text(this, SWT.BORDER|SWT.CENTER);
		txtStationName.setToolTipText("售票站点名称。");
		//lbStationName.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		txtStationName.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.NORMAL));
		formData = new FormData();
		formData.top = new FormAttachment(0,2);
		formData.left = new FormAttachment(txtStationNum,2);
		formData.bottom = new FormAttachment(100,-2);
		formData.width =85;
		txtStationName.setLayoutData(formData);
		txtStationName.setEnabled(false);
		
		Label label = new Label(this, SWT.NONE|SWT.CENTER);
		formData = new FormData();
		formData.top = new FormAttachment(0,2);
		formData.left = new FormAttachment(txtStationName,0);
		formData.bottom = new FormAttachment(100,-2);
		formData.width = 3;
		label.setLayoutData(formData);
		
		if (null != station){
			lbStationNo.setText(String.valueOf(index));
			if(0==station.getIfSale()){
				lbIfSale.setImage(wrongImage);
			}else if(1==station.getIfSale()){
				lbIfSale.setImage(rightImage);
			}
			if(null != station.getStationName()){
				txtStationName.setText(station.getStationName());
			}
			if(station.getStationNum()>=0){
				txtStationNum.setText(String.valueOf(station.getStationNum()));
			}
		}
	}

	public Station getStation(){
		return station;
	}

	public void setStation(Station station){
		this.station=station;
	}
}
