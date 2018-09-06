package com.zhima.widget;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import com.zhima.basic.CallMethod;
import com.zhima.basic.StyleFinal;
import com.zhima.frame.model.SamModuleRight;
import com.zhima.util.ImageUtil;
import com.zhima.util.ImageZoom;

/**
 * CButton概要说明：自定义按钮
 * @author lcy
 */
public class CButton extends Composite {
	private static Button btControl;
	public SelectionAdapter adapters;

	/**
	 * 构造函数:自定义按钮
	 * @param parent
	 * @param style
	 */
	public CButton(Composite parent, int style) {
		super(parent, style);
	}

	/**
	 * createButton方法描述：创建按钮
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2012-12-14 下午05:16:42
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param text 按钮名称
	 * @return Composite 按钮面板
	 */
	public final Composite createButton(SamModuleRight samModuleRight){
		Composite comp_btn = new Composite(this, SWT.NONE);
		comp_btn.setLayout(new RowLayout());
		btControl = new Button(this, SWT.NONE);
		String btnName = samModuleRight.getRightName();
		RowData data = new RowData();
		data.height= StyleFinal.BTN_HEIHGT;
		if (samModuleRight.getRightName().length()<=3){
			if((StyleFinal.BTN_SHOWIMAGE == true && null == samModuleRight.getRightIcon())
					|| StyleFinal.BTN_SHOWIMAGE == false){
				data.width = StyleFinal.BTN_WIDTH;
			}
		}
		btControl.setLayoutData(data);
		if (null != samModuleRight.getShortcutKey() && !"".equals(samModuleRight.getShortcutKey())){
			btnName += "(&" + samModuleRight.getShortcutKey() + ")";
		}
		if (StyleFinal.BTN_SHOWIMAGE==true){
			btControl.setImage(ImageZoom.getImage(ImageUtil.Base64ToBlob(samModuleRight.getRightIcon()), 18, 18));
		}
		btControl.setText(btnName);
		btControl.setToolTipText(samModuleRight.getRightDesc());
		btControl.setFont(StyleFinal.BTN_FONT);
		return comp_btn;
	}

	/**
	 * createButton方法描述：自定义宽度创建按钮
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2012-12-14 下午05:15:38
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param text 按钮名称
	 * @param width 宽度
	 * @return Composite 按钮面板
	 */
	public Composite createButton(String text,int width){
		Composite comp_btn = new Composite(this, SWT.NONE);
		comp_btn.setLayout(new RowLayout());
		RowData data = new RowData();
		btControl = new Button(this, SWT.NONE);
		btControl.setText(text);
		btControl.setFont(StyleFinal.BTN_FONT);
		data.height= StyleFinal.BTN_HEIHGT;
		data.width = width;
		btControl.setLayoutData(data);
		return comp_btn;
	}
	
	/**
	 * setText方法描述：设置按钮文本
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2012-12-14 下午05:15:17
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param text void
	 */
	public void setText(String text){
		btControl.setText(text);
	}
	
	/**
	 * bindSelectButton方法描述：绑定按钮方法
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2012-12-14 下午05:18:14
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param obj
	 * @param funName void
	 */
	public void bindSelectButton(final Object obj, final SamModuleRight moduleRight){
		SelectionAdapter adapter = new SelectionAdapter(){
			public void widgetSelected(SelectionEvent arg0) {
				CallMethod callMethod = new CallMethod();
				callMethod.bindSelectMenu(obj, moduleRight);
			}
		};
		SelectionAdapter(adapter);
	}
	
	/**
	 * SelectionAdapter方法描述：按钮选事件
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2012-12-14 下午05:17:37
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param adapter void
	 */
	public void SelectionAdapter(SelectionAdapter adapter) {
		this.adapters = adapter;
		btControl.addSelectionListener(adapter);
	}

}
