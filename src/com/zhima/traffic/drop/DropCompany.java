package com.zhima.traffic.drop;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;

import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.action.epd.IEpdCompany;
import com.zhima.traffic.action.epd.impl.ImpEpdCompany;
import com.zhima.traffic.model.EpdCompany;
import com.zhima.widget.CDropBox;
import com.zhima.widget.grid.GridColumn;

/**
 * CButton概要说明：自定义用户选择
 * @author lcy
 */
public class DropCompany extends Composite {
	public CDropBox droptext;

	/**
	 * 构造函数:自定义按钮
	 * @param parent
	 * @param style
	 */
	public DropCompany(Composite parent, int style) {
		super(parent, style);
		this.setLayout(new FillLayout());
		try {
			List<GridColumn> columns = new ArrayList<GridColumn>();
			columns.add(new GridColumn("公司代码", "companyCode",120));
			columns.add(new GridColumn("公司名称", "companyName", 200));
			List<EpdCompany> epdCompanies = new ArrayList<EpdCompany>();
			IEpdCompany iEpdCompany = new ImpEpdCompany();
			epdCompanies = iEpdCompany.queryByAll();
			droptext = new CDropBox(this, columns, epdCompanies, "companySeq", "companyName", SWT.NONE);
			droptext.create();
		} catch (UserBusinessException e) {
			e.printStackTrace();
		}
	}
}
