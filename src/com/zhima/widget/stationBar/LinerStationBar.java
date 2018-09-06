package com.zhima.widget.stationBar;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import com.zhima.util.SWTResourceManager;

public class LinerStationBar extends Composite {
	//控件面板
	private Composite composite;
	//线路站点
	private List<LinerStationItem> stationItems = new ArrayList<LinerStationItem>();
	public LinerStationBar(Composite parent, List<Station> routeStations,int style) {
		super(parent, SWT.BORDER);
		//this.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		setLayout(new FormLayout());
		
		Label lbIndex = new Label(this, SWT.NONE);
		lbIndex.setText("序号");
		FormData data = new FormData();
        data.top = new FormAttachment(0,5);
        data.left = new FormAttachment(0,15);
        lbIndex.setLayoutData(data);
        
		Label lbStationName = new Label(this, SWT.NONE);
		lbStationName.setText("站点名称");
		data = new FormData();
        data.top = new FormAttachment(0,5);
        data.left = new FormAttachment(lbIndex,50);
        lbStationName.setLayoutData(data);
        
		Label lbIfSale = new Label(this, SWT.NONE);
		lbIfSale.setText("可售");
		data = new FormData();
        data.top = new FormAttachment(0,5);
        data.left = new FormAttachment(lbStationName,60);
        lbIfSale.setLayoutData(data);
        
		Label lbSaleNum = new Label(this, SWT.NONE);
		lbSaleNum.setText("可售数");
		data = new FormData();
        data.top = new FormAttachment(0,5);
        data.left = new FormAttachment(lbIfSale,30);
        lbSaleNum.setLayoutData(data);
        
		Label lbTicketNum = new Label(this, SWT.NONE);
		lbTicketNum.setText("已售数");
		data = new FormData();
        data.top = new FormAttachment(0,5);
        data.left = new FormAttachment(lbSaleNum,30);
        lbTicketNum.setLayoutData(data);
        
		Label lbCheckNum = new Label(this, SWT.NONE);
		lbCheckNum.setText("检票数");
		data = new FormData();
        data.top = new FormAttachment(0,5);
        data.left = new FormAttachment(lbTicketNum,40);
        lbCheckNum.setLayoutData(data);
        
		Label lbMileageNum = new Label(this, SWT.NONE);
		lbMileageNum.setText("里程数");
		data = new FormData();
        data.top = new FormAttachment(0,5);
        data.left = new FormAttachment(lbCheckNum,50);
        lbMileageNum.setLayoutData(data);
		
		final ScrolledComposite scrolledComposite = new ScrolledComposite(this,SWT.H_SCROLL|SWT.V_SCROLL);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
        scrolledComposite.setMinWidth(100);
        scrolledComposite.setMinHeight(100);
        //将滚动面板的大小设置为父面板大小
        scrolledComposite.addControlListener(new ControlAdapter() {
			public void controlResized(ControlEvent e) {
				Rectangle rectangle = scrolledComposite.getClientArea();
				scrolledComposite.setMinSize(composite.computeSize(rectangle.width, SWT.DEFAULT));
			}
        });
        data = new FormData();
        data.top = new FormAttachment(0,25);
        data.left = new FormAttachment(0);
        data.right = new FormAttachment(100);
        data.bottom = new FormAttachment(100);
        scrolledComposite.setLayoutData(data);
        
        composite = new Composite(scrolledComposite, SWT.NONE);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		RowLayout rowLayout = new RowLayout();
		rowLayout.pack = false;
		rowLayout.type = SWT.HORIZONTAL;
		rowLayout.spacing = 0;
		rowLayout.marginTop = 2;
		rowLayout.marginBottom = 2;
		rowLayout.marginLeft = 2;
		rowLayout.marginRight = 2;
		composite.setLayout(rowLayout);
		scrolledComposite.setContent(composite);
		createContent(routeStations);
	}

	private void createContent(List<Station> routeStations){
		if(null != routeStations && routeStations.size()>0){
			for(int i=0;i<routeStations.size();i++){
				LinerStationItem stationItem = new LinerStationItem(composite,i+1,routeStations.get(i));
				stationItems.add(stationItem);
				if (i==0){
					stationItem.txtStationNum.forceFocus();
					stationItem.txtStationNum.selectAll();
				}
			}
			composite.layout(true);
		}
	}
	
	@SuppressWarnings("rawtypes")
	public List getData(){
		List<Station> stations = new ArrayList<Station>();
		if (null != stationItems && stationItems.size()>0){
			for(int i=0;i<stationItems.size();i++){
				LinerStationItem stationItem =  stationItems.get(i);
				if(null!=stationItem.getStation()){
					stations.add(stationItem.getStation());
				}
			}
		}
		return stations;
	}
	
	public void setData(List<Station> stations){
		clear();
		createContent(stations);
	}
	
	public void clear(){
		if (null != stationItems && stationItems.size()>0){
			for(int i=0;i<stationItems.size();i++){
				stationItems.get(i).setStation(null);
				stationItems.get(i).dispose();
			}
		}
	}
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		Display display = Display.getDefault();
		Shell shell = new Shell();
		shell.setSize(535, 500);
		shell.setText("SWT Application");
		shell.setLayout(new FillLayout());
		List<Station> routeStations = new ArrayList<Station>();
		for (int i = 0; i < 18; i++) {
			Station routeStation = new Station();
			routeStation.setStationSeq(String.valueOf(i));
			routeStation.setStationName("乌鲁木齐/高速");
			routeStation.setIfSale(1);
			routeStation.setStationNum(3);
			routeStation.setSaleNum(1);
			routeStation.setStationOrder(i);
			routeStations.add(routeStation);
		}
		new LinerStationBar(shell, routeStations, SWT.BORDER);
		
		
		
		

		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
}
