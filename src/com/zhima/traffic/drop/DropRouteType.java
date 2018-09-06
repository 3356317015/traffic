package com.zhima.traffic.drop;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;

import com.zhima.basic.CommFinal;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.action.epd.IEpdRouteType;
import com.zhima.traffic.action.epd.impl.ImpEpdRouteType;
import com.zhima.traffic.model.EpdRoutetype;
import com.zhima.widget.CDropBox;
import com.zhima.widget.grid.GridColumn;

public class DropRouteType extends Composite {
	public CDropBox droptext;

	public DropRouteType(Composite parent, int style) {
		super(parent, style);
		this.setLayout(new FillLayout());
		try {
		List<GridColumn> columns = new ArrayList<GridColumn>();
		columns.add(new GridColumn("线路类型代码", "routetypeCode",120));
		columns.add(new GridColumn("线路类型名称", "routetypeName",120));
		IEpdRouteType iEpdRouteType = new ImpEpdRouteType();
		List<EpdRoutetype> routetypes = iEpdRouteType.queryByOrganizeSeq(CommFinal.organize.getOrganizeSeq());
		droptext = new CDropBox(this, columns, routetypes, "routetypeSeq", "routetypeName", SWT.NONE);
		droptext.create();
		} catch (UserBusinessException e) {
			e.printStackTrace();
		}
	}
}
