package com.zhima.traffic.drop;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;

import com.zhima.basic.CommFinal;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.action.epd.IEpdCar;
import com.zhima.traffic.action.epd.impl.ImpEpdCar;
import com.zhima.traffic.comm.TraffFinal;
import com.zhima.traffic.model.EpdCar;
import com.zhima.widget.CDropBox;
import com.zhima.widget.grid.GridColumn;

/**
 * CButton概要说明：自定义用户选择
 * @author lcy
 */
public class DropCar extends Composite {
	public CDropBox droptext;

	/**
	 * 构造函数:自定义按钮
	 * @param parent
	 * @param style
	 */
	public DropCar(Composite parent, int style) {
		super(parent, style);
		try {
			this.setLayout(new FillLayout());
			List<GridColumn> columns = new ArrayList<GridColumn>();
			columns.add(new GridColumn("车牌号", "carNumber",100));
			columns.add(new GridColumn("状态","carStatus",TraffFinal.ARR_CAR_STATUS,100));
			columns.add(new GridColumn("车属公司","carCompanyname",300));
			IEpdCar iEpdCar = new ImpEpdCar();
			List<EpdCar> epdCars = iEpdCar.queryByOrganizeSeq(CommFinal.organize.getOrganizeSeq());
			droptext = new CDropBox(this, columns, epdCars, "carNumber", "carNumber", SWT.NONE);
			droptext.create();
		} catch (UserBusinessException e) {
			e.printStackTrace();
		}
	}

}
