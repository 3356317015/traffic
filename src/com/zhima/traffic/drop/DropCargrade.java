package com.zhima.traffic.drop;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;

import com.zhima.basic.CommFinal;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.action.epd.IEpdCargrade;
import com.zhima.traffic.action.epd.impl.ImpEpdCargrade;
import com.zhima.traffic.model.EpdCargrade;
import com.zhima.widget.CDropBox;
import com.zhima.widget.grid.GridColumn;

public class DropCargrade extends Composite {
	public CDropBox droptext;

	public DropCargrade(Composite parent, int style) {
		super(parent, style);
		this.setLayout(new FillLayout());
		List<GridColumn> columns = new ArrayList<GridColumn>();
		columns.add(new GridColumn("车型代码", "cargradeCode",100));
		columns.add(new GridColumn("车型名称", "cargradeName",100));
		droptext = new CDropBox(this, columns, null, "cargradeSeq", "cargradeName", SWT.NONE);
		droptext.create();
	}
	
	public void initAll(){
		try {
			IEpdCargrade iEpdCargrade = new ImpEpdCargrade();
			List<EpdCargrade> cargrades = iEpdCargrade.queryByOrganizeSeq(CommFinal.organize.getOrganizeSeq());
			droptext.setDataList(cargrades);
		} catch (UserBusinessException e) {
			e.printStackTrace();
		}
	}
	
	public void initByRouteSeq(String routeSeq) {
		try {
			IEpdCargrade iEpdCargrade = new ImpEpdCargrade();
			List<EpdCargrade> cargrades = iEpdCargrade.queryByRouteSeq(routeSeq);
			droptext.setDataList(cargrades);
		} catch (UserBusinessException e) {
			e.printStackTrace();
		}
	}
}
