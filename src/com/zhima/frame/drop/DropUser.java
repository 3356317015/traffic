package com.zhima.frame.drop;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;

import com.zhima.basic.exception.UserBusinessException;
import com.zhima.frame.action.sam.ISamUser;
import com.zhima.frame.action.sam.impl.ImpSamUser;
import com.zhima.widget.CDropBox;
import com.zhima.widget.grid.GridColumn;

/**
 * CButton概要说明：自定义用户选择
 * @author lcy
 */
public class DropUser extends Composite {
	public CDropBox droptext;

	/**
	 * 构造函数:自定义按钮
	 * @param parent
	 * @param style
	 */
	public DropUser(Composite parent, int style) {
		super(parent, style);
		this.setLayout(new FillLayout());

		List<GridColumn> columns = new ArrayList<GridColumn>();
		columns.add(new GridColumn("用户代码", "userCode",100));
		columns.add(new GridColumn("用户名称", "userName", 100));
		columns.add(new GridColumn("状态", "status", new String[]{"0-无效","1-有效"}, 80));
	
		droptext = new CDropBox(this, columns, null, "userCode", "userName", SWT.NONE);
		droptext.create();
		
	}
	
	public void init(String organizeSeq){
		try {
			ISamUser iSamUser = new ImpSamUser();
			droptext.setDataList(iSamUser.queryByOrganize(organizeSeq));
		} catch (UserBusinessException e) {
			e.printStackTrace();
		}
	}
}
