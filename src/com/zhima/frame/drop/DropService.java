package com.zhima.frame.drop;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;

import com.zhima.basic.CommFinal;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.frame.action.sam.ISamService;
import com.zhima.frame.action.sam.impl.ImpSamService;
import com.zhima.frame.model.SamService;
import com.zhima.util.log4j.LogUtil;
import com.zhima.widget.CDropBox;
import com.zhima.widget.grid.GridColumn;

/**
 * CButton概要说明：自定义用户选择
 * @author lcy
 */
public class DropService extends Composite {
	public CDropBox droptext;

	/**
	 * 构造函数:自定义按钮
	 * @param parent
	 * @param style
	 * @throws UserBusinessException 
	 */
	public DropService(Composite parent, int style) {
		super(parent, style);
		this.setLayout(new FillLayout());

		List<GridColumn> columns = new ArrayList<GridColumn>();
		columns.add(new GridColumn("乘车点代码", "serviceCode",120));
		columns.add(new GridColumn("乘车点名称", "serviceName", 200));
		droptext = new CDropBox(this, columns, null, "serviceSeq", "serviceName", SWT.NONE);
		droptext.create();
	}
	
	/**
	 * initType方法描述：根据类型加载客运站
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2018-5-10 下午05:49:15
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param type 1=除合作站外；2=合作站；3=车站+服务点
	 * @return void
	 */
	public void initType(String type) {
		try{
			ISamService iSamService = new ImpSamService();
			List<SamService> samServices = iSamService.queryByOrganizeAndSaleType(CommFinal.organize.getOrganizeSeq(),"1");
			droptext.setDataList(samServices);
		} catch (UserBusinessException e) {
			e.printStackTrace();
			LogUtil.operLog(e,"E",true,true);
		}
	}

}
