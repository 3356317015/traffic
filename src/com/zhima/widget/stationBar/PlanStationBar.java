package com.zhima.widget.stationBar;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.zhima.util.SWTResourceManager;

public class PlanStationBar extends Composite {
	//控件面板
	private Composite composite;
	//线路站点
	private List<PlanStationItem> stationItems = new ArrayList<PlanStationItem>();
	public PlanStationBar(Composite parent, List<Station> routeStations,int style) {
		super(parent, SWT.BORDER);
		setLayout(new FillLayout());
		final ScrolledComposite scrolledComposite = new ScrolledComposite(this,SWT.H_SCROLL|SWT.V_SCROLL);
		composite = new Composite(scrolledComposite, SWT.NONE);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		RowLayout rowLayout = new RowLayout();
		rowLayout.pack = false;
		rowLayout.type = SWT.HORIZONTAL;
		rowLayout.spacing = 3;
		rowLayout.marginTop = 3;
		rowLayout.marginBottom = 3;
		rowLayout.marginLeft = 3;
		rowLayout.marginRight = 3;
		composite.setLayout(rowLayout);
		scrolledComposite.setContent(composite);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
        scrolledComposite.setMinWidth(300);
        scrolledComposite.setMinHeight(200);
        //将滚动面板的大小设置为父面板大小
        scrolledComposite.addControlListener(new ControlAdapter() {
			public void controlResized(ControlEvent e) {
				Rectangle rectangle = scrolledComposite.getClientArea();
				scrolledComposite.setMinSize(composite.computeSize(rectangle.width, SWT.DEFAULT));
			}
        });
		createContent(routeStations);
	}

	private void createContent(List<Station> routeStations){
		if(null != routeStations && routeStations.size()>0){
			for(int i=0;i<routeStations.size();i++){
				PlanStationItem stationItem = new PlanStationItem(composite,i+1,routeStations.get(i));
				stationItems.add(stationItem);
			}
			composite.layout(true);
		}
	}
	
	@SuppressWarnings("rawtypes")
	public List getData(){
		List<Station> stations = new ArrayList<Station>();
		if (null != stationItems && stationItems.size()>0){
			for(int i=0;i<stationItems.size();i++){
				PlanStationItem stationItem =  stationItems.get(i);
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
	public static void main(String[] args) {
		Display display = Display.getDefault();
		Shell shell = new Shell();
		shell.setSize(835, 600);
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
		new PlanStationBar(shell, routeStations, SWT.BORDER);
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
}
