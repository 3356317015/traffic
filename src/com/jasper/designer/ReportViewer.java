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
package com.jasper.designer;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.event.EventListenerList;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRPrintXmlLoader;
import net.sf.jasperreports.view.JRHyperlinkListener;

import org.eclipse.jface.util.Assert;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

/**
 * SWT based report viewer implementation.
 * 
 * @author Peter Severin (peter_p_s@users.sourceforge.net)
 */
@SuppressWarnings({ "rawtypes", "deprecation" })
public class ReportViewer implements IReportViewer {

	private static final double[] DEFAULT_ZOOM_LEVELS = new double[] { 0.5f,
			0.75f, 1.0f, 1.25f, 1.50f, 1.75f, 2.0f };

	private EventListenerList listenerList = new EventListenerList();

	private JasperPrint document;

	private String reason;

	private double zoom = 1.0f;

	private double[] zoomLevels = DEFAULT_ZOOM_LEVELS;

	private int zoomMode = ZOOM_MODE_NONE;

	private int pageIndex;

	private String fileName;

	private boolean xml;

	private int style;

	private ViewerCanvas viewerComposite;

	private List hyperlinkListeners;

	/**
	 * Default constructor. The default style will be used for the SWT control
	 * associated to the viewer.
	 */
	public ReportViewer() {
		this(SWT.NONE);
	}

	/**
	 * Constructor that allows to specify a SWT control style. For possible styles
	 * see the {@link org.eclipse.swt.widgets.Canvas} class. Most frequently you
	 * will wont to specify the <code>SWT.NONE<code> style.
	 * @param style the style
	 */
	public ReportViewer(int style) {
		this.style = style;
	}

	/**
	 * @see com.jasper.designer.IReportViewer#setDocument(net.sf.jasperreports.engine.JasperPrint)
	 */
	public void setDocument(JasperPrint document) {
		Assert.isNotNull(document, Messages.getString("ReportViewer.documentNotNull")); //$NON-NLS-1$
		Assert.isNotNull(document.getPages(), Messages.getString("ReportViewer.documentNotEmpty")); //$NON-NLS-1$
		Assert.isTrue(!document.getPages().isEmpty(), Messages.getString("ReportViewer.documentNotEmpty")); //$NON-NLS-1$
		this.document = document;
		this.reason = null;
		this.pageIndex = Math.min(Math.max(0, pageIndex), getPageCount() - 1);
		setZoomInternal(computeZoom());
		fireViewerModelChanged();
	}

	/**
	 * @see com.jasper.designer.IReportViewer#getDocument()
	 */
	public JasperPrint getDocument() {
		return document;
	}

	/**
	 * @see com.jasper.designer.IReportViewer#hasDocument()
	 */
	public boolean hasDocument() {
		return getDocument() != null;
	}

	/**
	 * @see com.jasper.designer.IReportViewer#unsetDocument(java.lang.String)
	 */
	public void unsetDocument(String reason) {
		this.document = null;
		this.reason = reason;
		fireViewerModelChanged();
	}

	/**
	 * @see com.jasper.designer.IReportViewer#getReason()
	 */
	public String getReason() {
		return reason;
	}

	/**
	 * @see com.jasper.designer.IReportViewer#setZoom(double)
	 */
	public void setZoom(double zoom) {
		setZoom(zoom, false);
	}

	private void setZoom(double zoom, boolean keepMode) {
		if (!canChangeZoom())
			return;

		if (Math.abs(zoom - getZoom()) > 0.00001) {
			setZoomInternal(zoom);
			if (!keepMode)
				this.zoomMode = ZOOM_MODE_NONE;
			fireViewerModelChanged();
		}
	}

	/**
	 * @see com.jasper.designer.IReportViewer#canChangeZoom()
	 */
	public boolean canChangeZoom() {
		return hasDocument();
	}

	private void setZoomInternal(double zoom) {
		this.zoom = Math.min(Math.max(zoom, getMinZoom()), getMaxZoom());
	}

	/**
	 * @see com.jasper.designer.IReportViewer#getZoom()
	 */
	public double getZoom() {
		return zoom;
	}

	/**
	 * @see com.jasper.designer.IReportViewer#setZoomMode(int)
	 */
	public void setZoomMode(int zoomMode) {
		if (!canChangeZoom())
			return;

		if (zoomMode != getZoomMode()) {
			this.zoomMode = zoomMode;
			setZoomInternal(computeZoom());
			fireViewerModelChanged();
		}
	}

	private double computeZoom() {
		switch (zoomMode) {
		case ZOOM_MODE_ACTUAL_SIZE:
			return 1.0;
		case ZOOM_MODE_FIT_WIDTH: {
			double ratio = ratio(viewerComposite.getFitSize().x, document
					.getPageWidth());
			return ratio(viewerComposite.getFitSize((int) (document
					.getPageWidth() * ratio),
					(int) (document.getPageHeight() * ratio)).x, document
					.getPageWidth());
		}
		case ZOOM_MODE_FIT_HEIGHT: {
			double ratio = ratio(viewerComposite.getFitSize().y, document
					.getPageHeight());
			return ratio(viewerComposite.getFitSize((int) (document
					.getPageWidth() * ratio),
					(int) (document.getPageHeight() * ratio)).y, document
					.getPageHeight());
		}
		case ZOOM_MODE_FIT_PAGE:
			Point fitSize = viewerComposite.getFitSize();
			return Math.min(ratio(fitSize.x, document.getPageWidth()), ratio(
					fitSize.y, document.getPageHeight()));
		}

		return zoom;
	}

	private double ratio(int a, int b) {
		return (a * 100 / b) / 100.0;
	}

	/**
	 * @see com.jasper.designer.IReportViewer#getZoomMode()
	 */
	public int getZoomMode() {
		return zoomMode;
	}

	private int getPageCount() {
		return document == null ? 0 : document.getPages().size();
	}

	/**
	 * @see com.jasper.designer.IReportViewer#getPageIndex()
	 */
	public int getPageIndex() {
		return pageIndex;
	}

	/**
	 * @see com.jasper.designer.IReportViewer#setPageIndex(int)
	 */
	public void setPageIndex(int pageIndex) {
		if (pageIndex != getPageIndex()) {
			this.pageIndex = Math.min(Math.max(0, pageIndex),
					getPageCount() - 1);
			fireViewerModelChanged();
		}
	}

	/**
	 * @see com.jasper.designer.IReportViewer#canGotoFirstPage()
	 */
	public boolean canGotoFirstPage() {
		return hasDocument() && pageIndex > 0;
	}

	/**
	 * @see com.jasper.designer.IReportViewer#gotoFirstPage()
	 */
	public void gotoFirstPage() {
		if (canGotoFirstPage()) {
			setPageIndex(0);
		}
	}

	/**
	 * @see com.jasper.designer.IReportViewer#canGotoLastPage()
	 */
	public boolean canGotoLastPage() {
		return hasDocument() && pageIndex < getPageCount() - 1;
	}

	/**
	 * @see com.jasper.designer.IReportViewer#gotoLastPage()
	 */
	public void gotoLastPage() {
		if (canGotoLastPage()) {
			setPageIndex(getPageCount() - 1);
		}
	}

	/**
	 * @see com.jasper.designer.IReportViewer#canGotoNextPage()
	 */
	public boolean canGotoNextPage() {
		return hasDocument() && pageIndex < getPageCount() - 1;
	}

	/**
	 * @see com.jasper.designer.IReportViewer#gotoNextPage()
	 */
	public void gotoNextPage() {
		if (canGotoNextPage()) {
			setPageIndex(pageIndex + 1);
		}
	}

	/**
	 * @see com.jasper.designer.IReportViewer#canGotoPreviousPage()
	 */
	public boolean canGotoPreviousPage() {
		return hasDocument() && pageIndex > 0;
	}

	/**
	 * @see com.jasper.designer.IReportViewer#gotoPreviousPage()
	 */
	public void gotoPreviousPage() {
		if (canGotoPreviousPage()) {
			setPageIndex(pageIndex - 1);
		}
	}

	/**
	 * @see com.jasper.designer.IReportViewer#addReportViewerListener(com.jasper.designer.IReportViewerListener)
	 */
	public void addReportViewerListener(IReportViewerListener listener) {
		listenerList.add(IReportViewerListener.class, listener);
	}

	/**
	 * @see com.jasper.designer.IReportViewer#removeReportViewerListener(com.jasper.designer.IReportViewerListener)
	 */
	public void removeReportViewerListener(IReportViewerListener listener) {
		listenerList.remove(IReportViewerListener.class, listener);
	}

	private void fireViewerModelChanged() {
		Object[] listeners = listenerList.getListenerList();
		ReportViewerEvent e = null;

		for (int i = listeners.length - 2; i >= 0; i -= 2) {
			if (listeners[i] == IReportViewerListener.class) {
				if (e == null) {
					e = new ReportViewerEvent(this);
				}
				((IReportViewerListener) listeners[i + 1])
						.viewerStateChanged(e);
			}
		}
	}

	/**
	 * @see com.jasper.designer.IReportViewer#reload()
	 */
	public void reload() {
		try {
			pageIndex = 0;
			//			zoom = 1.0f;
			//			zoomMode = ZOOM_MODE_NONE;
			setDocument(load());
		} catch (JRException e) {
			unsetDocument(e.toString());
		}
	}

	/**
	 * @see com.jasper.designer.IReportViewer#canReload()
	 */
	public boolean canReload() {
		return fileName != null;
	}
	
	/**
	 * @see com.jasper.designer.IReportViewer#loadDocument(java.lang.String,
	 *      boolean)
	 */
	public void loadDocument(String fileName, boolean xml) {
		this.fileName = fileName;
		this.xml = xml;
		reload();
	}
	
	public void loadDocument(JasperPrint jasperPrint, boolean xml) {
		pageIndex = 0;
		setDocument(jasperPrint);
		
	}
	
	@Override
	public void clearDocument() {
		try {
			pageIndex = 0;
			URL url = getClass().getResource("emptyreport.jrprint");
			JasperPrint jasperPrint = (JasperPrint) JRLoader.loadObject(url);
			setDocument(jasperPrint);
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	private JasperPrint load() throws JRException {
		JasperPrint jasperPrint = null;

		if (xml) {
			jasperPrint = JRPrintXmlLoader.load(fileName);
		} else {
			jasperPrint = (JasperPrint) JRLoader.loadObject(fileName);
		}

		return jasperPrint;
	}

	/**
	 * Creates the SWT control for the report viewer. Later calls to this method
	 * will return the same instance of the control.
	 * 
	 * @param parent
	 *            the parent
	 * @return the created control
	 */
	public Control createControl(Composite parent) {
		if (viewerComposite == null) {
			viewerComposite = new ViewerCanvas(parent, style) {
				/**
				 * @see com.jasper.designer.ViewerCanvas#resize()
				 */
				protected void resize() {
					setZoom(computeZoom(), true);
					super.resize();
				}
			};
			viewerComposite.setReportViewer(this);
		}

		return viewerComposite;
	}

	/**
	 * @see com.jasper.designer.IReportViewer#getZoomLevels()
	 */
	public double[] getZoomLevels() {
		return zoomLevels;
	}

	/**
	 * @see com.jasper.designer.IReportViewer#setZoomLevels(double[])
	 */
	public void setZoomLevels(double[] levels) {
		Assert.isNotNull(levels);
		Assert.isTrue(levels.length > 0);
		this.zoomLevels = levels;
	}

	/**
	 * @see com.jasper.designer.IReportViewer#zoomIn()
	 */
	public void zoomIn() {
		if (canZoomIn())
			setZoom(getNextZoom());
	}

	/**
	 * @see com.jasper.designer.IReportViewer#canZoomIn()
	 */
	public boolean canZoomIn() {
		return hasDocument() && getZoom() < getMaxZoom();
	}

	/**
	 * @see com.jasper.designer.IReportViewer#zoomOut()
	 */
	public void zoomOut() {
		if (canZoomOut())
			setZoom(getPreviousZoom());
	}

	/**
	 * @see com.jasper.designer.IReportViewer#canZoomOut()
	 */
	public boolean canZoomOut() {
		return hasDocument() && getZoom() > getMinZoom();
	}

	private double getMinZoom() {
		return zoomLevels[0];
	}

	private double getMaxZoom() {
		return zoomLevels[zoomLevels.length - 1];
	}

	private double getNextZoom() {
		for (int i = 0; i < zoomLevels.length; i++) {
			if (zoom < zoomLevels[i])
				return zoomLevels[i];
		}

		return getMaxZoom();
	}

	private double getPreviousZoom() {
		for (int i = zoomLevels.length - 1; i >= 0; i--) {
			if (zoom > zoomLevels[i])
				return zoomLevels[i];
		}

		return getMinZoom();
	}

	/**
	 * @see com.jasper.designer.IReportViewer#addHyperlinkListener(net.sf.jasperreports.view.JRHyperlinkListener)
	 */
	@SuppressWarnings("unchecked")
	public void addHyperlinkListener(JRHyperlinkListener listener) {
		if (hyperlinkListeners == null) {
			hyperlinkListeners = new ArrayList();
		} else {
			hyperlinkListeners.remove(listener); // add once
		}

		hyperlinkListeners.add(listener);
	}

	/**
	 * @see com.jasper.designer.IReportViewer#removeHyperlinkListener(net.sf.jasperreports.view.JRHyperlinkListener)
	 */
	public void removeHyperlinkListener(JRHyperlinkListener listener) {
		if (hyperlinkListeners != null)
			hyperlinkListeners.remove(listener);
	}

	/**
	 * @see com.jasper.designer.IReportViewer#getHyperlinkListeners()
	 */
	@SuppressWarnings("unchecked")
	public JRHyperlinkListener[] getHyperlinkListeners() {
		return hyperlinkListeners == null ? new JRHyperlinkListener[0]
				: (JRHyperlinkListener[]) hyperlinkListeners
						.toArray(new JRHyperlinkListener[hyperlinkListeners
								.size()]);
	}

}
