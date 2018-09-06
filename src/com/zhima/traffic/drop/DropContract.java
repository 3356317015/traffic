package com.zhima.traffic.drop;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;

import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.action.epd.IEpdContract;
import com.zhima.traffic.action.epd.impl.ImpEpdContract;
import com.zhima.traffic.model.EpdContract;
import com.zhima.widget.CDropBox;
import com.zhima.widget.grid.GridColumn;

public class DropContract extends Composite {
	public CDropBox droptext;
	
	public DropContract(Composite parent, int style) {
		super(parent, style);
		this.setLayout(new FillLayout());
		try {
			List<GridColumn> columns = new ArrayList<GridColumn>();
			columns.add(new GridColumn("合同代码", "contractCode",100));
			columns.add(new GridColumn("合同名称", "contractName",100));
			IEpdContract iEpdContract = new ImpEpdContract();
			List<EpdContract> contracts = iEpdContract.queryByAll();
			droptext = new CDropBox(this, columns, contracts, "contractSeq", "contractName", SWT.NONE);
			droptext.create();
		} catch (UserBusinessException e) {
			e.printStackTrace();
		}
	}

}
