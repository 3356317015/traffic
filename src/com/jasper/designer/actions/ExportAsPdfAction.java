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

import java.io.File;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.export.JRExportProgressMonitor;
import net.sf.jasperreports.engine.export.JRPdfExporter;

import org.eclipse.jface.resource.ImageDescriptor;

import com.jasper.designer.IReportViewer;

/**
 * PDF export action
 * 
 * @author Peter Severin (peter_p_s@users.sourceforge.net)
 */
public class ExportAsPdfAction extends AbstractExportAction {

	private static final ImageDescriptor ICON = ImageDescriptor.createFromFile(
			ExportAsPdfAction.class, "images/save.gif"); //$NON-NLS-1$

	private static final ImageDescriptor DISABLED_ICON = ImageDescriptor
			.createFromFile(ExportAsPdfAction.class, "images/saved.gif"); //$NON-NLS-1$

	/**
	 * @see AbstractExportAction#AbstractExportAction(IReportViewer)
	 */
	public ExportAsPdfAction(IReportViewer viewer) {
		super(viewer);

		setText(Messages.getString("ExportAsPdfAction.label")); //$NON-NLS-1$
		setToolTipText(Messages.getString("ExportAsPdfAction.tooltip")); //$NON-NLS-1$
		setImageDescriptor(ICON);
		setDisabledImageDescriptor(DISABLED_ICON);

		setFileExtensions(new String[] { "*.pdf" }); //$NON-NLS-1$
		setFilterNames(new String[] { Messages.getString("ExportAsPdfAction.filterName") }); //$NON-NLS-1$
		setDefaultFileExtension("pdf"); //$NON-NLS-1$
	}

	/**
	 * @see com.jasper.designer.actions.AbstractExportAction#exportWithProgress(java.io.File, net.sf.jasperreports.engine.export.JRExportProgressMonitor)
	 */
	protected void exportWithProgress(File file, JRExportProgressMonitor monitor)
			throws JRException {
		JRPdfExporter exporter = new JRPdfExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT,
				getReportViewer().getDocument());
		exporter.setParameter(JRExporterParameter.OUTPUT_FILE, file);
		exporter.setParameter(JRExporterParameter.PROGRESS_MONITOR, monitor);
		exporter.exportReport();
	}
}
