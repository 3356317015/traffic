package com.zhima.traffic.drop;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;

import com.zhima.basic.CommFinal;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.action.epd.IEpdRegion;
import com.zhima.traffic.action.epd.impl.ImpEpdRegion;
import com.zhima.traffic.model.EpdRegion;
import com.zhima.widget.CDropBox;
import com.zhima.widget.grid.GridColumn;

public class DropCity extends Composite {
	public CDropBox droptext;

	public DropCity(Composite parent, int style) {
		super(parent, style);
		this.setLayout(new FillLayout());
		try {
			List<GridColumn> columns = new ArrayList<GridColumn>();
			columns.add(new GridColumn("省(市)", "city",120));
			List<EpdRegion> epdRegions = new ArrayList<EpdRegion>();
			IEpdRegion iEpdRegion = new ImpEpdRegion();
			epdRegions = iEpdRegion.queryGroupCity(CommFinal.organize.getOrganizeSeq());
			droptext = new CDropBox(this, columns, epdRegions, "city", "city", SWT.NONE);
			droptext.create();
			droptext.txtShow.setMessage("省(市)");
		} catch (UserBusinessException e) {
			e.printStackTrace();
		}
	}
}
