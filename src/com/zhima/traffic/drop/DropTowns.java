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

public class DropTowns extends Composite {
	public CDropBox droptext;

	public DropTowns(Composite parent, int style) {
		super(parent, style);
		this.setLayout(new FillLayout());
		List<GridColumn> columns = new ArrayList<GridColumn>();
		columns.add(new GridColumn("乡(镇)", "towns",120));
		droptext = new CDropBox(this, columns, null, "towns", "towns", SWT.NONE);
		droptext.create();
		droptext.txtShow.setMessage("乡(镇)");
	}
	
	public void initDrop(String city,String county) {
		try {
			List<EpdRegion> epdRegions = new ArrayList<EpdRegion>();
			IEpdRegion iEpdRegion = new ImpEpdRegion();
			epdRegions = iEpdRegion.queryGroupTowns(CommFinal.organize.getOrganizeSeq(),city, county);
			droptext.setDataList(epdRegions);
		} catch (UserBusinessException e) {
			e.printStackTrace();
		}
	}
}
