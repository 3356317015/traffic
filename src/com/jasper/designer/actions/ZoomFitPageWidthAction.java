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
import com.jasper.designer.ReportViewerEvent;

/**
 * Fit page width zoom action
 * @author Peter Severin (peter_p_s@users.sourceforge.net)
 */
public class ZoomFitPageWidthAction extends AbstractReportViewerAction {

	private static final ImageDescriptor ICON	=	
		ImageDescriptor.createFromFile(ZoomFitPageWidthAction.class, "images/zoomfitwidth.gif"); //$NON-NLS-1$
	private static final ImageDescriptor DISABLED_ICON	=	
		ImageDescriptor.createFromFile(ZoomFitPageWidthAction.class, "images/zoomfitwidthd.gif"); //$NON-NLS-1$
	
	/**
	 * @see AbstractReportViewerAction#AbstractReportViewerAction(IReportViewer)
	 */
	public ZoomFitPageWidthAction(IReportViewer viewer) {
		super(viewer);
		
		setText(Messages.getString("ZoomFitPageWidthAction.label")); //$NON-NLS-1$
		setToolTipText(Messages.getString("ZoomFitPageWidthAction.tooltip")); //$NON-NLS-1$
		setImageDescriptor(ICON);
		setDisabledImageDescriptor(DISABLED_ICON);
		update();
	}

	private void update() {
		setChecked(getReportViewer().getZoomMode() == IReportViewer.ZOOM_MODE_FIT_WIDTH);
	}
	
	/**
	 * @see com.jasper.designer.actions.AbstractReportViewerAction#runBusy()
	 */
	protected void runBusy() {
		getReportViewer().setZoomMode(IReportViewer.ZOOM_MODE_FIT_WIDTH);
		update();
	}

	/**
	 * @see com.jasper.designer.actions.AbstractReportViewerAction#calculateEnabled()
	 */
	protected boolean calculateEnabled() {
		return getReportViewer().canChangeZoom();
	}
	
	/**
	 * @see com.jasper.designer.actions.AbstractReportViewerAction#viewerStateChanged(com.jasper.designer.ReportViewerEvent)
	 */
	public void viewerStateChanged(ReportViewerEvent evt) {
		update();
		super.viewerStateChanged(evt);
	}
}
