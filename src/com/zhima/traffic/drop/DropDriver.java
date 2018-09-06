package com.zhima.traffic.drop;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;

import com.zhima.basic.CommFinal;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.action.epd.IEpdDriver;
import com.zhima.traffic.action.epd.impl.ImpEpdDriver;
import com.zhima.traffic.comm.TraffFinal;
import com.zhima.traffic.model.EpdDriver;
import com.zhima.widget.CDropBox;
import com.zhima.widget.grid.GridColumn;

public class DropDriver extends Composite {
	public CDropBox droptext;

	public DropDriver(Composite parent, int style) {
		super(parent, style);
		this.setLayout(new FillLayout());
		try {
		List<GridColumn> columns = new ArrayList<GridColumn>();
		columns.add(new GridColumn("驾驶员","driverName",100));
		columns.add(new GridColumn("性别","sex",TraffFinal.ARR_SEX,60));
		columns.add(new GridColumn("联系电话","telephone",110));
		columns.add(new GridColumn("身份证","idNumber",160));
		IEpdDriver iEpdDriver = new ImpEpdDriver();
		List<EpdDriver> drivers = iEpdDriver.queryByOrganizeSeq(CommFinal.organize.getOrganizeSeq());
		droptext = new CDropBox(this, columns, drivers, "driverSeq", "driverName", SWT.NONE);
		droptext.create();
		} catch (UserBusinessException e) {
			e.printStackTrace();
		}
	}
}
