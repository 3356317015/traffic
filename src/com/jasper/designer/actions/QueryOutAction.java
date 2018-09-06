/*
 * SWTJasperViewer - Free SWT/JFace report viewer for JasperReports.
 * Copyright (C) 2004  Peter Severin (peter_p_s@users.sourceforge.net)
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307, USA.
 */
package com.jasper.designer.actions;

import org.eclipse.jface.resource.ImageDescriptor;

import com.jasper.designer.IReportViewer;
import com.zhima.basic.CommFinal;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.frame.model.SamModuleRight;
import com.zhima.util.log4j.LogUtil;

/**
 * Zoom out action
 * 
 * @author Peter Severin (peter_p_s@users.sourceforge.net)
 */
public class QueryOutAction extends AbstractReportViewerAction {

	private static final ImageDescriptor ICON	=	
		ImageDescriptor.createFromFile(QueryOutAction.class, "images/find.gif"); //$NON-NLS-1$
	private static final ImageDescriptor DISABLED_ICON	=	
		ImageDescriptor.createFromFile(QueryOutAction.class, "images/find.gif"); //$NON-NLS-1$
	private Object obj;
	private SamModuleRight right;
	
	/**
	 * @see AbstractReportViewerAction#AbstractReportViewerAction(IReportViewer)
	 */
	public QueryOutAction(IReportViewer viewer, Object obj, SamModuleRight right) {
		super(viewer);
		this.obj = obj;
		this.right = right;
		setText(Messages.getString("FindOutAction.label")); //$NON-NLS-1$
		setToolTipText(Messages.getString("FindOutAction.tooltip")); //$NON-NLS-1$
		setImageDescriptor(ICON);
		setDisabledImageDescriptor(DISABLED_ICON);
		setEnabled(true);
	}
	
	/**
	 * @see com.jasper.designer.actions.AbstractReportViewerAction#runBusy()
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected void runBusy() {
		try {
			CommFinal.insertOperLog(right,"1","");
			Class cls = obj.getClass();
			cls.getDeclaredMethod(right.getRightMethod()).invoke(obj);
		} catch (Exception e) {
			try {
				CommFinal.updateOperLog(right,"0","运行失败或方法设置不正确");
			} catch (UserBusinessException e1) {
				LogUtil.operLog(e, "E", true, "[" + right.getRightName() + "]运行失败或方法设置不正确,\r\n请与管理员联系。");
			}
			LogUtil.operLog(e, "E", true, "[" + right.getRightName() + "]运行失败或方法设置不正确,\r\n请与管理员联系。");
		} 
	}

	/**
	 * @see com.jasper.designer.actions.AbstractReportViewerAction#calculateEnabled()
	 */
	protected boolean calculateEnabled() {
		return true;
		//return getReportViewer().canZoomOut();
	}
}
