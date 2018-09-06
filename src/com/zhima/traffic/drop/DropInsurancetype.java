package com.zhima.traffic.drop;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;

import com.zhima.basic.CommFinal;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.action.insurance.IInsTickettype;
import com.zhima.traffic.action.insurance.impl.ImpInsTickettype;
import com.zhima.traffic.model.InsTickettype;
import com.zhima.widget.CDropBox;
import com.zhima.widget.grid.GridColumn;

public class DropInsurancetype extends Composite {
	public CDropBox droptext;

	public DropInsurancetype(Composite parent, int style) {
		super(parent, style);
		this.setLayout(new FillLayout());
		try {
			
			List<GridColumn> columns = new ArrayList<GridColumn>();
			columns.add(new GridColumn("票据名称", "ticketName",150));
			columns.add(new GridColumn("保险名称", "insuranceName",150));
			IInsTickettype iInsTickettype = new ImpInsTickettype();
			List<InsTickettype> tickettypes = iInsTickettype.queryByOrganizeSeq(CommFinal.organize.getOrganizeSeq());
			droptext = new CDropBox(this, columns, tickettypes, "tickettypeSeq", "ticketName", SWT.NONE);
			droptext.create();
		} catch (UserBusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
