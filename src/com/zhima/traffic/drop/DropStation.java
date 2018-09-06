package com.zhima.traffic.drop;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;

import com.zhima.basic.CommFinal;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.action.epd.IEpdStation;
import com.zhima.traffic.action.epd.impl.ImpEpdStation;
import com.zhima.traffic.model.EpdStation;
import com.zhima.widget.CDropBox;
import com.zhima.widget.grid.GridColumn;

public class DropStation extends Composite {
	public CDropBox droptext;

	public DropStation(Composite parent, int style) {
		super(parent, style);
		this.setLayout(new FillLayout());
		try {
		List<GridColumn> columns = new ArrayList<GridColumn>();
		columns.add(new GridColumn("站点名称", "stationName",110));
		columns.add(new GridColumn("站点代码", "stationCode",80));
		columns.add(new GridColumn("拼音代码", "stationSpell",80));
		columns.add(new GridColumn("里程", "stationMileage",60));
		columns.add(new GridColumn("行政区域", "regionName",200));
		IEpdStation iEpdStation = new ImpEpdStation();
		List<EpdStation> stations = iEpdStation.queryByOrganizeSeq(CommFinal.organize.getOrganizeSeq());
		droptext = new CDropBox(this, columns, stations, "stationSeq", "stationName", SWT.NONE);
		droptext.create();
		} catch (UserBusinessException e) {
			e.printStackTrace();
		}
	}
}
