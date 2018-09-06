package com.zhima.traffic.drop;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;

import com.zhima.basic.CommFinal;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.action.epd.IEpdTickettype;
import com.zhima.traffic.action.epd.impl.ImpEpdTickettype;
import com.zhima.traffic.model.EpdTickettype;
import com.zhima.widget.CDropBox;
import com.zhima.widget.grid.GridColumn;

public class DropTickettype extends Composite {
	public CDropBox droptext;

	public DropTickettype(Composite parent, int style) {
		super(parent, style);
		this.setLayout(new FillLayout());
		List<GridColumn> columns = new ArrayList<GridColumn>();
		columns.add(new GridColumn("票据名称", "ticketName",100));
		columns.add(new GridColumn("批次数量", "ticketNum",100));
		columns.add(new GridColumn("联单页数", "ticketPages",100));
		droptext = new CDropBox(this, columns, null, "tickettypeSeq", "ticketName", SWT.NONE);
		droptext.create();
	}
	
	public void initCategory(String category){
		try {
			IEpdTickettype iEpdTickettype = new ImpEpdTickettype();
			List<EpdTickettype> tickettypes = iEpdTickettype.queryAllByCustom(CommFinal.organize.getOrganizeSeq(), category);
			droptext.setDataList(tickettypes);
		} catch (UserBusinessException e) {
			e.printStackTrace();
		}
	}

}
