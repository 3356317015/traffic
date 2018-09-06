package com.zhima.traffic.drop;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;

import com.zhima.basic.CommFinal;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.action.epd.IEpdCheckgate;
import com.zhima.traffic.action.epd.impl.ImpEpdCheckgate;
import com.zhima.traffic.model.EpdCheckgate;
import com.zhima.widget.CDropBox;
import com.zhima.widget.grid.GridColumn;

public class DropCheckgate extends Composite {
	public CDropBox droptext;

	public DropCheckgate(Composite parent, int style) {
		super(parent, style);
		this.setLayout(new FillLayout());
		try {
		List<GridColumn> columns = new ArrayList<GridColumn>();
		columns.add(new GridColumn("发车位代码", "checkCode",120));
		columns.add(new GridColumn("发车位名称", "checkName",120));
		IEpdCheckgate iEpdCheckgate = new ImpEpdCheckgate();
		List<EpdCheckgate> checkgates = iEpdCheckgate.queryByOrganizeSeq(CommFinal.organize.getOrganizeSeq());
		droptext = new CDropBox(this, columns, checkgates, "checkgateSeq", "checkName", SWT.NONE);
		droptext.create();
		} catch (UserBusinessException e) {
			e.printStackTrace();
		}
	}
}
