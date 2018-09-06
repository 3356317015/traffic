package com.zhima.traffic.drop;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;

import com.zhima.basic.CommFinal;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.action.epd.IEpdFaresuit;
import com.zhima.traffic.action.epd.impl.ImpEpdFaresuit;
import com.zhima.traffic.model.EpdFaresuit;
import com.zhima.widget.CDropBox;
import com.zhima.widget.grid.GridColumn;

public class DropFaresuit extends Composite {
	public CDropBox droptext;

	public DropFaresuit(Composite parent, int style) {
		super(parent, style);
		this.setLayout(new FillLayout());
		try {
		List<GridColumn> columns = new ArrayList<GridColumn>();
		columns.add(new GridColumn("价套代码", "faresuitCode",100));
		columns.add(new GridColumn("价套名称", "faresuitName",100));
		IEpdFaresuit iEpdFaresuit = new ImpEpdFaresuit();
		List<EpdFaresuit> faresuits = iEpdFaresuit.queryByOrganizeSeq(CommFinal.organize.getOrganizeSeq());
		droptext = new CDropBox(this, columns, faresuits, "faresuitSeq", "faresuitName", SWT.NONE);
		droptext.create();
		} catch (UserBusinessException e) {
			e.printStackTrace();
		}
	}
}
