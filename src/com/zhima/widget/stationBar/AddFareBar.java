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

public class AddFareBar extends Composite {
	//控件面板
	private Composite composite;
	private int addWidth;
	//线路站点
	private List<AddFareItem> fareItems = new ArrayList<AddFareItem>();
	public AddFareBar(Composite parent, List<Station> routeStations,int addWidth,int style) {
		super(parent, SWT.BORDER);
		this.addWidth = addWidth;
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
        data.left = new FormAttachment(lbIndex,40+addWidth/2);
        lbStationName.setLayoutData(data);

		Label lbIfSale = new Label(this, SWT.NONE);
		lbIfSale.setText("可售");
		data = new FormData();
        data.top = new FormAttachment(0,5);
        data.left = new FormAttachment(lbStationName,25+addWidth);
        lbIfSale.setLayoutData(data);
        
		Label lbStationNum = new Label(this, SWT.NONE);
		lbStationNum.setText("数量");
		data = new FormData();
        data.top = new FormAttachment(0,5);
        data.left = new FormAttachment(lbIfSale,20);
        lbStationNum.setLayoutData(data);
        
		Label lbFullFare = new Label(this, SWT.NONE);
		lbFullFare.setText("总价");
		data = new FormData();
        data.top = new FormAttachment(0,5);
        data.left = new FormAttachment(lbStationNum,15+addWidth);
        lbFullFare.setLayoutData(data);
        
		Label lbBaseFare = new Label(this, SWT.NONE);
		lbBaseFare.setText("基础价");
		data = new FormData();
        data.top = new FormAttachment(0,5);
        data.left = new FormAttachment(lbFullFare,50+addWidth);
        lbBaseFare.setLayoutData(data);
        
		Label lbStationFare = new Label(this, SWT.NONE);
		lbStationFare.setText("站务费");
		data = new FormData();
        data.top = new FormAttachment(0,5);
        data.left = new FormAttachment(lbBaseFare,35+addWidth);
        lbStationFare.setLayoutData(data);
        
		Label lbFuelFare = new Label(this, SWT.NONE);
		lbFuelFare.setText("燃油附加费");
		data = new FormData();
        data.top = new FormAttachment(0,5);
        data.left = new FormAttachment(lbStationFare,35+addWidth);
        lbFuelFare.setLayoutData(data);
        
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

	private void createContent(List<Station> stations){
		if(null != stations && stations.size()>0){
			for(int i=0;i<stations.size();i++){
				AddFareItem fareItem = new AddFareItem(composite,i+1,addWidth,stations.get(i));
				fareItems.add(fareItem);
			}
			composite.layout(true);
		}
	}
	
	@SuppressWarnings("rawtypes")
	public List getData(){
		List<Station> stations = new ArrayList<Station>();
		if (null != fareItems && fareItems.size()>0){
			for(int i=0;i<fareItems.size();i++){
				AddFareItem fareItem =  fareItems.get(i);
				if(null!=fareItem.getStation()){
					stations.add(fareItem.getStation());
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
		if (null != fareItems && fareItems.size()>0){
			for(int i=0;i<fareItems.size();i++){
				fareItems.get(i).setStation(null);
				fareItems.get(i).dispose();
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
		new AddFareBar(shell, routeStations, 0, SWT.BORDER);

		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
}
