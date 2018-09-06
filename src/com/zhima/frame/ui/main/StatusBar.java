package com.zhima.frame.ui.main;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;

import com.zhima.basic.StyleFinal;
import com.zhima.util.SWTResourceManager;

public class StatusBar extends Composite {

	public CLabel lbOrganize;
	
	public CLabel lbUser;
	
	public CLabel lbTime;
	
	public StatusBar(Composite parent,int style) {
		super(parent, style);
		setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		setLayout(new FormLayout());
		
		lbOrganize = new CLabel(this, SWT.NONE|SWT.SHADOW_NONE);
		lbOrganize.setFont(StyleFinal.SYS_FONT);
		//lbOrganize.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		FormData data = new FormData();
		data.top = new FormAttachment(0,2);
		data.bottom = new FormAttachment(100,-2);
		data.left = new FormAttachment(0,2);
		data.right = new FormAttachment(40);
		lbOrganize.setLayoutData(data);
		
		lbUser = new CLabel(this, SWT.NONE|SWT.SHADOW_NONE);
		lbUser.setFont(StyleFinal.SYS_FONT);
		//lbUser.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		data = new FormData();
		data.top = new FormAttachment(lbOrganize, 0, SWT.TOP);
		data.bottom = new FormAttachment(lbOrganize,0,SWT.BOTTOM);
		data.left = new FormAttachment(lbOrganize,2);
		data.right = new FormAttachment(100, -300);
		lbUser.setLayoutData(data);
		
		lbTime = new CLabel(this, SWT.NONE|SWT.SHADOW_NONE);
		lbTime.setFont(StyleFinal.SYS_FONT);
		//lbTime.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		lbTime.setAlignment(SWT.RIGHT);
		data = new FormData();
		data.top = new FormAttachment(lbOrganize, 0, SWT.TOP);
		data.bottom = new FormAttachment(lbOrganize,0,SWT.BOTTOM);
		data.left = new FormAttachment(lbUser, 2, SWT.RIGHT);
		data.right = new FormAttachment(100,-2);
		lbTime.setLayoutData(data);
	}
}
