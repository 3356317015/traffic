package com.zhima.frame.drop;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;

import com.zhima.basic.CommFinal;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.frame.action.sam.ISamModule;
import com.zhima.frame.action.sam.impl.ImpSamModule;
import com.zhima.frame.model.SamModule;
import com.zhima.widget.CDropBox;
import com.zhima.widget.grid.GridColumn;

/**
 * CButton概要说明：自定义用户选择
 * @author lcy
 */
public class DropModule extends Composite {
	public CDropBox droptext;

	/**
	 * 构造函数:自定义按钮
	 * @param parent
	 * @param style
	 */
	public DropModule(Composite parent, int style) {
		super(parent, style);
		this.setLayout(new FillLayout());
		try {
			List<GridColumn> columns = new ArrayList<GridColumn>();
			columns.add(new GridColumn("模块代码", "moduleClass",120));
			columns.add(new GridColumn("模块名称", "moduleName", 160));
			List<SamModule> samModules = new ArrayList<SamModule>();
			ISamModule iSamModule = new ImpSamModule();
			samModules = iSamModule.queryByType(CommFinal.organize.getOrganizeSeq(),"1");
			SamModule module = new SamModule();
			module.setModuleClass("SYSTEM");
			module.setModuleName("应用系统");
			samModules.add(0, module);
			droptext = new CDropBox(this, columns, samModules, "moduleClass", "moduleName", SWT.NONE);
			droptext.create();
		} catch (UserBusinessException e) {
			e.printStackTrace();
		}
	}
}
