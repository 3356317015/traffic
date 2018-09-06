package com.zhima.widget;

import java.util.Random;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;

import com.zhima.basic.CallMethod;
import com.zhima.basic.StyleFinal;
import com.zhima.frame.model.SamModuleRight;
import com.zhima.util.ImageUtil;
import com.zhima.util.ImageZoom;
import com.zhima.util.SWTResourceManager;

/**
 * CButton概要说明：自定义按钮
 * @author lcy
 */
public class FButton extends Composite {
	private static Button btShort;
	private static CLabel btControl;
	@SuppressWarnings("unused")
	private MouseListener mouseListener;
	@SuppressWarnings("unused")
	private SelectionAdapter selectionAdapter;

	/**
	 * 构造函数:自定义按钮
	 * @param parent
	 * @param style
	 */
	public FButton(Composite parent, int style) {
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
	public final void createButton(SamModuleRight samModuleRight){
		Composite composite = new Composite(this, SWT.NONE);
		composite.setBackground(produceColor());
		composite.setLayout(new FormLayout());
		RowData data = new RowData();
		data.height= StyleFinal.BTN_HEIHGT;
		if (samModuleRight.getRightName().length()<=3){
			if((StyleFinal.BTN_SHOWIMAGE == true && null == samModuleRight.getRightIcon())
					|| StyleFinal.BTN_SHOWIMAGE == false){
				data.width = StyleFinal.BTN_WIDTH;
			}
		}
		composite.setLayoutData(data);
		
			
		btControl = new CLabel(composite, SWT.NONE|SWT.CENTER);
		btShort = new Button(composite, SWT.NONE|SWT.CENTER);
		if (StyleFinal.BTN_SHOWIMAGE==true){
			if (null != samModuleRight.getRightIcon()&&samModuleRight.getRightIcon().length()>0){
				btControl.setImage(ImageZoom.getImage(ImageUtil.Base64ToBlob(samModuleRight.getRightIcon()), 18, 18));
			}
		}
		String btnName = samModuleRight.getRightName();
		if (null != samModuleRight.getShortcutKey() && !"".equals(samModuleRight.getShortcutKey())){
			btnName += "(" + samModuleRight.getShortcutKey() + ")";
			btShort.setText("&" + samModuleRight.getShortcutKey());
		}
		btControl.setText(btnName);
		btControl.setToolTipText(samModuleRight.getRightDesc());
		btControl.setFont(StyleFinal.BTN_FONT);
		FormData formData = new FormData();
		formData.left = new FormAttachment(0,5);
		formData.top = new FormAttachment(0);
		formData.bottom = new FormAttachment(100);
		btControl.setLayoutData(formData);
		btControl.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		formData = new FormData();
		formData.left = new FormAttachment(0,15);
		formData.top = new FormAttachment(0);
		formData.bottom = new FormAttachment(100);
		btShort.setLayoutData(formData);
		
		Label lbSplit = new Label(composite, SWT.NONE);
		formData = new FormData();
		formData.left = new FormAttachment(btControl,0);
		formData.top = new FormAttachment(0);
		formData.bottom = new FormAttachment(100);
		formData.width=10;
		lbSplit.setLayoutData(formData);
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
	public void createButton(String strIcon, String text, String shortcutKey, int width){
		Composite composite = new Composite(this, SWT.NONE);
		composite.setBackground(produceColor());
		composite.setLayout(new FormLayout());
		RowData data = new RowData();
		data.height= StyleFinal.BTN_HEIHGT;
		data.width = width;
		composite.setLayoutData(data);
		btControl = new CLabel(composite, SWT.NONE|SWT.CENTER);
		btShort = new Button(composite, SWT.NONE|SWT.CENTER);
		
		if (StyleFinal.BTN_SHOWIMAGE==true){
			if (null != strIcon&&strIcon.length()>0){
				btControl.setImage(ImageZoom.getImage(ImageUtil.Base64ToBlob(strIcon), 18, 18));
			}
		}
		String btnName = text;
		if (null != shortcutKey && !"".equals(shortcutKey)){
			btnName += "(" + shortcutKey + ")";
			btShort.setText("&" + shortcutKey);
		}
		btControl.setText(btnName);
		btControl.setFont(StyleFinal.BTN_FONT);
		FormData formData = new FormData();
		formData.left = new FormAttachment(0,5);
		formData.top = new FormAttachment(0);
		formData.bottom = new FormAttachment(100);
		formData.right = new FormAttachment(100,-10);
		btControl.setLayoutData(formData);
		btControl.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		formData = new FormData();
		formData.left = new FormAttachment(0,15);
		formData.top = new FormAttachment(0);
		formData.bottom = new FormAttachment(100);
		btShort.setLayoutData(formData);
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
		btShort.setText(text);
	}
	
    private static Color produceColor() {  
        Random random = new Random();  
        int red = random.nextInt(255);  
        int green = random.nextInt(255);  
        int blue = random.nextInt(255);   
        RGB rgb = new RGB(red, green, blue);  
        Color color = new Color(Display.getDefault(), rgb);  
        return color;  
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
	public void bindMouseListener(final Object obj, final SamModuleRight moduleRight){
	
		MouseListener mouseListener = new MouseListener() {
			@Override
			public void mouseDown(MouseEvent arg0) {
				// TODO Auto-generated method stub
				CallMethod callMethod = new CallMethod();
				callMethod.bindSelectMenu(obj, moduleRight);
			}

			@Override
			public void mouseDoubleClick(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseUp(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		};
		MouseAdapter(mouseListener);
	}
	

	
	public void bindMouseListener(final Object obj, final String methodName) {
		MouseListener mouseListener = new MouseListener() {
			@Override
			public void mouseDown(MouseEvent arg0) {
				// TODO Auto-generated method stub
				CallMethod callMethod = new CallMethod();
				callMethod.bindSelectMenu(obj, methodName);
			}

			@Override
			public void mouseDoubleClick(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseUp(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		};
		MouseAdapter(mouseListener);
		
	}
	
	/**
	 * SelectionAdapter方法描述：按钮选事件
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2012-12-14 下午05:17:37
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param adapter void
	 */
	public void MouseAdapter(MouseListener mouseListener) {
		this.mouseListener = mouseListener;
		btControl.addMouseListener(mouseListener);
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
	public void SelectionAdapter(SelectionAdapter selectionAdapter) {
		this.selectionAdapter = selectionAdapter;
		btShort.addSelectionListener(selectionAdapter);
	}

}
