package com.zhima.widget.stationBar;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.zhima.basic.StyleFinal;

/**
 * KLabel概要说明：标识必输项专用标签
 * @author lcy
 */
public class LinerBar extends Composite {
	public Text txtLinerDate;
	public Text txtLinerId;
	public Text txtRouteName;
	
	public LinerBar(Composite parent,int style) {
		super(parent, style);
		this.setLayout(new FormLayout());
		
		Label lbLinerDate = new Label(this, SWT.NONE);
		lbLinerDate.setText("班次日期:");
		lbLinerDate.setFont(StyleFinal.SYS_FONT);
		FormData formData = new FormData();
		formData.top = new FormAttachment(0,2);
		formData.left = new FormAttachment(0,5);
		lbLinerDate.setLayoutData(formData);
		
		txtLinerDate = new Text(this, SWT.BORDER|SWT.READ_ONLY);
		txtLinerDate.setFont(StyleFinal.SYS_FONT);
		formData = new FormData();
		formData.top = new FormAttachment(0);
		formData.left = new FormAttachment(lbLinerDate,5);
		formData.width=100;
		formData.height=StyleFinal.TEXT_HEIGHT;
		txtLinerDate.setLayoutData(formData);
		txtLinerDate.setText("2018-04-15");
		
		Label lbLinerId = new Label(this, SWT.NONE);
		lbLinerId.setText("班次号:");
		lbLinerId.setFont(StyleFinal.SYS_FONT);
		formData = new FormData();
		formData.top = new FormAttachment(0,2);
		formData.left = new FormAttachment(txtLinerDate,10);
		lbLinerId.setLayoutData(formData);
		
		txtLinerId = new Text(this, SWT.BORDER|SWT.READ_ONLY);
		txtLinerId.setFont(StyleFinal.SYS_FONT);
		formData = new FormData();
		formData.top = new FormAttachment(0);
		formData.left = new FormAttachment(lbLinerId,5);
		formData.width=100;
		formData.height=StyleFinal.TEXT_HEIGHT;
		txtLinerId.setLayoutData(formData);
		
		Label lbRouteName = new Label(this, SWT.NONE);
		lbRouteName.setText("线路名称:");
		lbRouteName.setFont(StyleFinal.SYS_FONT);
		formData = new FormData();
		formData.top = new FormAttachment(0,2);
		formData.left = new FormAttachment(txtLinerId,5);
		lbRouteName.setLayoutData(formData);
		
		txtRouteName = new Text(this, SWT.BORDER|SWT.READ_ONLY);
		txtRouteName.setFont(StyleFinal.SYS_FONT);
		formData = new FormData();
		formData.top = new FormAttachment(0);
		formData.left = new FormAttachment(lbRouteName,5);
		formData.right= new FormAttachment(100, -2);
		formData.height=StyleFinal.TEXT_HEIGHT;
		txtRouteName.setLayoutData(formData);
	}
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		Display display = Display.getDefault();
		Shell shell = new Shell();
		shell.setSize(600, 500);
		shell.setText("SWT Application");
		shell.setLayout(new FormLayout());
		/*List<Station> routeStations = new ArrayList<Station>();
		for (int i = 0; i < 12; i++) {
			Station routeStation = new Station();
			routeStation.setStationSeq(String.valueOf(i));
			routeStation.setStationName("乌鲁木齐/高速");
			routeStation.setIfSale(1);
			routeStation.setStationNum(3);
			routeStation.setSaleNum(1);
			routeStation.setStationOrder(i);
			routeStations.add(routeStation);
		}*/
		LinerBar linerBar = new LinerBar(shell, SWT.BORDER);
		FormData formData = new FormData();
		formData.top = new FormAttachment(0);
		formData.left =new FormAttachment(0);
		formData.right = new FormAttachment(100);
		linerBar.setLayoutData(formData);
		
		
		
		

		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

}
