package com.zhima.traffic.drop;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;

import com.zhima.basic.CommFinal;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.action.epd.IEpdParking;
import com.zhima.traffic.action.epd.impl.ImpEpdParking;
import com.zhima.traffic.model.EpdCheckgate;
import com.zhima.traffic.model.EpdParking;
import com.zhima.widget.CDropBox;
import com.zhima.widget.grid.GridColumn;

public class DropParking extends Composite {
	public CDropBox droptext;

	public DropParking(Composite parent, int style) {
		super(parent, style);
		this.setLayout(new FillLayout());
		List<GridColumn> columns = new ArrayList<GridColumn>();
		columns.add(new GridColumn("发车位代码", "parkingCode",160));
		columns.add(new GridColumn("发车位名称", "parkingName",160));
		columns.add(new GridColumn("允许座位数", "seatNum",160));
		columns.add(new GridColumn("检票口名称", "checkName",160));
		droptext = new CDropBox(this, columns, null, "parkingSeq", "parkingName", SWT.NONE);
		droptext.create();
	}
	
	public void initAll(){
		try {
			IEpdParking iEpdParking = new ImpEpdParking();
			List<EpdParking> parkings = iEpdParking.queryByOrganizeSeq(CommFinal.organize.getOrganizeSeq());
			droptext.setDataList(parkings);
		} catch (UserBusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void initByCheckCode(List<EpdCheckgate> checkgates){
		try {
			if (null != checkgates && checkgates.size()>0){
				IEpdParking iEpdParking = new ImpEpdParking();
				List<EpdParking> parkings = iEpdParking.queryByOrganizeAndCheckSeq(
						CommFinal.organize.getOrganizeSeq(),checkgates);
				droptext.setDataList(parkings);
			}else{
				droptext.setDataList(null);
			}
		} catch (UserBusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
