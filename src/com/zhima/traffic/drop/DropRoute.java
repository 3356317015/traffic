package com.zhima.traffic.drop;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;

import com.zhima.basic.CommFinal;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.action.epd.IEpdRoute;
import com.zhima.traffic.action.epd.impl.ImpEpdRoute;
import com.zhima.traffic.model.EpdRoute;
import com.zhima.widget.CDropBox;
import com.zhima.widget.grid.GridColumn;

public class DropRoute extends Composite {
	public CDropBox droptext;
	
	public DropRoute(Composite parent, int style) {
		super(parent, style);
		this.setLayout(new FillLayout());
		try {
		List<GridColumn> columns = new ArrayList<GridColumn>();
		columns.add(new GridColumn("线路代码", "routeCode",100));
		columns.add(new GridColumn("拼音代码", "routeSpell",100));
		columns.add(new GridColumn("线路名称", "routeName",100));
		IEpdRoute iEpdRoute = new ImpEpdRoute();
		List<EpdRoute> routes = iEpdRoute.queryByOrganizeSeq(CommFinal.organize.getOrganizeSeq());
		droptext = new CDropBox(this, columns, routes, "routeSeq", "routeName", SWT.NONE);
		droptext.create();
		} catch (UserBusinessException e) {
			e.printStackTrace();
		}
	}
	
	public void init(boolean initAll) {
		try {
			IEpdRoute iEpdRoute = new ImpEpdRoute();
			if (initAll==true){
				List<EpdRoute> routes = iEpdRoute.queryByOrganizeSeq(CommFinal.organize.getOrganizeSeq());
				droptext.setDataList(routes);
			}else{
				List<EpdRoute> routes = iEpdRoute.queryByNoFare(CommFinal.organize.getOrganizeSeq());
				droptext.setDataList(routes);
			}
		} catch (UserBusinessException e) {
			e.printStackTrace();
		}
	}
	
	public DropRoute(Composite parent, Object obj, String methodName,String queryType, int style) {
		super(parent, style);
		this.setLayout(new FillLayout());
		try {
		List<GridColumn> columns = new ArrayList<GridColumn>();
		columns.add(new GridColumn("线路代码", "routeCode",100));
		columns.add(new GridColumn("拼音代码", "routeSpell",100));
		columns.add(new GridColumn("线路名称", "routeName",100));
		IEpdRoute iEpdRoute = new ImpEpdRoute();
		if ("1".equals(queryType)){
			List<EpdRoute> routes = iEpdRoute.queryByOrganizeSeq(CommFinal.organize.getOrganizeSeq());
			droptext = new CDropBox(this, columns, routes, "routeSeq", "routeName", SWT.NONE);
		}else{
			List<EpdRoute> routes = iEpdRoute.queryByNoFare(CommFinal.organize.getOrganizeSeq());
			droptext = new CDropBox(this, columns, routes, "routeSeq", "routeName", SWT.NONE);
		}
		droptext.setObj(obj);
		droptext.setMethodName(methodName);
		droptext.create();
		} catch (UserBusinessException e) {
			e.printStackTrace();
		}
	}
	
}
