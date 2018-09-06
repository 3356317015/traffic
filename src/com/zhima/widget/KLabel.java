package com.zhima.widget;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import com.zhima.util.SWTResourceManager;

/**
 * KLabel概要说明：标识必输项专用标签
 * @author lcy
 */
public class KLabel extends Composite {
	private Label mark;
	private Label label;
	
	public KLabel(Composite parent,int style) {
		super(parent, style);
		this.setLayout(new FormLayout());
		mark = new Label(this, SWT.NONE);
		mark.setText("*");
		mark.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));

		FormData formData = new FormData();
		formData.top = new FormAttachment(0);
		formData.left = new FormAttachment(0);
		formData.bottom = new FormAttachment(100);
		mark.setLayoutData(formData);
		label = new Label(this, style);
		formData = new FormData();
		formData.top = new FormAttachment(0);
		formData.left = new FormAttachment(mark);
		formData.bottom = new FormAttachment(100);
		label.setLayoutData(formData);
	}

	public void setText(String text){
		label.setText(text);
	}
	
	public void setFont(Font font){
		mark.setFont(font);
		label.setFont(font);
	}
	
	public void setForeground(Color color){
		label.setForeground(color);
	}
	
	public void setBackground(Color color){
		mark.setBackground(color);
		label.setBackground(color);

	}

}
