package com.zhima.traffic.drop;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;

import com.zhima.basic.CommFinal;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.action.insurance.IInsCompany;
import com.zhima.traffic.action.insurance.impl.ImpInsCompany;
import com.zhima.traffic.model.InsCompany;
import com.zhima.widget.CDropBox;
import com.zhima.widget.grid.GridColumn;

public class DropInsurance extends Composite {
	public CDropBox droptext;

	public DropInsurance(Composite parent, int style) {
		super(parent, style);
		this.setLayout(new FillLayout());
		try {
			List<GridColumn> columns = new ArrayList<GridColumn>();
			columns.add(new GridColumn("保险代码", "insuranceCode",120));
			columns.add(new GridColumn("保险名称", "insuranceName",150));
			List<InsCompany> insCompanies = new ArrayList<InsCompany>();
			IInsCompany iInsCompany = new ImpInsCompany();
			insCompanies = iInsCompany.queryByOrganizeSeq(CommFinal.organize.getOrganizeSeq());
			droptext = new CDropBox(this, columns, insCompanies, "companySeq", "insuranceName", SWT.NONE);
			droptext.create();
		} catch (UserBusinessException e) {
			e.printStackTrace();
		}
	}
}
