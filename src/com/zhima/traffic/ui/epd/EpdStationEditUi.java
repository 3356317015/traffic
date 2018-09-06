package com.zhima.traffic.ui.epd;

import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.zhima.basic.CallMethod;
import com.zhima.basic.CommFinal;
import com.zhima.basic.StyleFinal;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.traffic.action.epd.IEpdRegion;
import com.zhima.traffic.action.epd.IEpdStation;
import com.zhima.traffic.action.epd.impl.ImpEpdRegion;
import com.zhima.traffic.action.epd.impl.ImpEpdStation;
import com.zhima.traffic.comm.TraffFinal;
import com.zhima.traffic.drop.DropCity;
import com.zhima.traffic.drop.DropCounty;
import com.zhima.traffic.drop.DropTowns;
import com.zhima.traffic.model.EpdRegion;
import com.zhima.traffic.model.EpdStation;
import com.zhima.util.DateUtils;
import com.zhima.util.PinyinUtil;
import com.zhima.util.SWTResourceManager;
import com.zhima.util.log4j.LogUtil;
import com.zhima.widget.KLabel;
import com.zhima.widget.MsgBox;
import com.zhima.widget.TextVerifyListener;
import com.zhima.widget.grid.GridView;

public class EpdStationEditUi extends Dialog {
	private GridView gridView;
	private String operType;
	
	private Text txtStationCode;
	
	private Text txtStationSpell;
	
	private Text txtStationName;
	
	private Text txtStationMileage;
	
	private DropCity dropCity;
	
	private DropCounty dropCounty;
	
	private DropTowns dropTowns;
	
	private CCombo comStatus;

	private Text txtRemark;

	protected EpdStationEditUi(Shell shell,GridView gridView,String operType) {
		super(shell);
		this.gridView = gridView;
		this.operType = operType;
	}

	protected void configureShell(Shell shell){
		super.configureShell(shell);
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		shell.setBackground(StyleFinal.DIALOG_BACK_GROUND);
		shell.setText("站点设置-"+operType);
	}
	
	@Override
	protected Point getInitialLocation(Point initialSize) {
		Rectangle screenSize =  Display.getDefault().getClientArea();
		return new Point((screenSize.width - initialSize.x)/2, (screenSize.height - initialSize.y)/2);
	}
	
	@Override
	protected Point getInitialSize(){
        return new Point(385,320);
    }
	
	protected void createButtonsForButtonBar(Composite parent){
		GridData btnData = new GridData(StyleFinal.DIALOGBAR_ALIGNMENT);
		parent.setLayoutData(btnData);
		Button confirm = createButton(parent, 1, "确定(&O)", false);
		confirm.setFont(StyleFinal.SYS_FONT);
		Button cancel = createButton(parent, 0, "取消(&C)", false);
		cancel.setFont(StyleFinal.SYS_FONT);
		if (StyleFinal.BTN_SHOWIMAGE == true){
			confirm.setImage(SWTResourceManager.getImage(CommFinal.IMAGE_BUTTON_OK));
			cancel.setImage(SWTResourceManager.getImage(CommFinal.IMAGE_BUTTON_CANCEL));
		}
	}
	
	protected Control createDialogArea(Composite parent) {
		Composite compMain = (Composite) super.createDialogArea(parent);
		compMain.setLayout(new FormLayout());
		CallMethod callMethod = new CallMethod();

		Group groupMain = new Group(compMain,SWT.NONE);
		groupMain.setText("站点信息");
		groupMain.setFont(StyleFinal.SYS_FONT);
		GridLayout gridLayout = new GridLayout(2,false);
		gridLayout.verticalSpacing=StyleFinal.VERTICAL_SPACING;
		groupMain.setLayout(gridLayout);
		
		
		FormData data = new FormData();
		data.top = new FormAttachment(0,5);
		data.left = new FormAttachment(0,5);
		data.right = new FormAttachment(100,-5);
		data.bottom = new FormAttachment(100);
		groupMain.setLayoutData(data);
		
		KLabel lbStationName = new KLabel(groupMain, SWT.RIGHT);
		lbStationName.setFont(StyleFinal.SYS_FONT);
		lbStationName.setText("站点名称:");
		GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbStationName.setLayoutData(gridData);
		
		txtStationName = new Text(groupMain, SWT.BORDER);
		txtStationName.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtStationName.setLayoutData(gridData);

		KLabel lbStationCode = new KLabel(groupMain, SWT.RIGHT);
		lbStationCode.setFont(StyleFinal.SYS_FONT);
		lbStationCode.setText("站点代码:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		lbStationCode.setLayoutData(gridData);
		
		txtStationCode = new Text(groupMain, SWT.BORDER);
		txtStationCode.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtStationCode.setLayoutData(gridData);

		Label lbStationSpell = new Label(groupMain, SWT.RIGHT);
		lbStationSpell.setFont(StyleFinal.SYS_FONT);
		lbStationSpell.setText("拼音代码:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		lbStationSpell.setLayoutData(gridData);
		
		txtStationSpell = new Text(groupMain, SWT.BORDER);
		txtStationSpell.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtStationSpell.setLayoutData(gridData);
		
		KLabel lbStationMileage = new KLabel(groupMain, SWT.RIGHT);
		lbStationMileage.setFont(StyleFinal.SYS_FONT);
		lbStationMileage.setText("里程(km):");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbStationMileage.setLayoutData(gridData);
		
		txtStationMileage = new Text(groupMain, SWT.BORDER);
		txtStationMileage.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtStationMileage.setLayoutData(gridData);
		txtStationMileage.addVerifyListener(new TextVerifyListener(1));
		
		Label lbCityArea = new Label(groupMain, SWT.RIGHT);
		lbCityArea.setFont(StyleFinal.SYS_FONT);
		lbCityArea.setText("行政区域:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		lbCityArea.setLayoutData(gridData);
		
		Composite compRegion = new Composite(groupMain, SWT.NONE);
		gridLayout = new GridLayout(3,false);
		gridLayout.marginLeft=-5;
		gridLayout.verticalSpacing=StyleFinal.VERTICAL_SPACING;
		compRegion.setLayout(gridLayout);

		dropCity = new DropCity(compRegion, SWT.BORDER);
		dropCity.droptext.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		gridData.widthHint=80;
		dropCity.setLayoutData(gridData);
		
		dropCounty = new DropCounty(compRegion, SWT.BORDER);
		dropCounty.droptext.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		gridData.widthHint=80;
		dropCounty.setLayoutData(gridData);
		
		dropTowns = new DropTowns(compRegion, SWT.BORDER);
		dropTowns.droptext.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		gridData.widthHint=80;
		dropTowns.setLayoutData(gridData);
		
		KLabel lbStatus = new KLabel(groupMain, SWT.RIGHT);
		lbStatus.setFont(StyleFinal.SYS_FONT);
		lbStatus.setText("站点状态:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbStatus.setLayoutData(gridData);
		
		comStatus = new CCombo(groupMain, SWT.BORDER);
		comStatus.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		comStatus.setLayoutData(gridData);
		comStatus.setItems(CommFinal.getAllName(TraffFinal.ARR_STATION_STATUS));

		Label lbRemark = new Label(groupMain, SWT.RIGHT);
		lbRemark.setFont(StyleFinal.SYS_FONT);
		lbRemark.setText("备注:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		lbRemark.setLayoutData(gridData);
		
		txtRemark = new Text(groupMain, SWT.BORDER);
		txtRemark.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtRemark.setLayoutData(gridData);
		
		initData();
		txtStationName.forceFocus();
		txtStationName.selectAll();
		callMethod.bindEnterEvent(this, txtStationName, txtStationCode, "setSpell");
		callMethod.bindEnterEvent(this, txtStationCode, txtStationSpell, "");
		callMethod.bindEnterEvent(this, txtStationSpell, txtStationMileage, "");
		callMethod.bindEnterEvent(this, txtStationMileage, dropCity.droptext.txtShow, "");
		callMethod.bindEnterEvent(this, dropCity.droptext.txtShow, dropCounty.droptext.txtShow, "initCounty");
		callMethod.bindEnterEvent(this, dropCounty.droptext.txtShow, dropTowns.droptext.txtShow, "initTowns");
		callMethod.bindEnterEvent(this, dropTowns.droptext.txtShow, comStatus, "");
		callMethod.bindEnterEvent(this, comStatus, txtRemark, "");
		callMethod.bindEnterEvent(this, txtRemark, null, "");
		return compMain;
	}
	
	private void initData(){
		if (CommFinal.OPER_TYPE_UPDATE.equals(operType)) {
			try {
				EpdStation station = (EpdStation) gridView.getSelection();
				if (null != station && !"".equals(station.getStationSeq())) {
					txtStationCode.setText(station.getStationCode());
					txtStationSpell.setText(station.getStationSpell());
					txtStationName.setText(station.getStationName());
					txtStationMileage.setText(String.valueOf(station.getStationMileage()));
					if (null != station.getRegionSeq() && station.getRegionSeq().length() > 0) {
						IEpdRegion iEpdRegion = new ImpEpdRegion();
						List<EpdRegion> regions = iEpdRegion.queryByPK(station.getRegionSeq());
						if (null != regions && regions.size()>0){
							dropCity.droptext.setText(regions.get(0).getCity());
							dropCounty.droptext.setText(regions.get(0).getCounty());
							dropTowns.droptext.setText(regions.get(0).getTowns());
						}
					}
					comStatus.setText(CommFinal.getItemName(TraffFinal.ARR_STATION_STATUS,station.getStationStatus()));
					txtRemark.setText(station.getRemark());
				}
			} catch (UserBusinessException e) {
				e.printStackTrace();
			}
		}
	}

	protected void buttonPressed(int buttonId){
		try {
			if(1 == buttonId){
				/**
				 * 确定
				 */
				EpdStation epdStation = validData();
				if (null != epdStation){
					IEpdStation iEpdStation = new ImpEpdStation();
					if (CommFinal.OPER_TYPE_ADD.equals(operType)){
						EpdStation newEpdStation = iEpdStation.insert(epdStation,CommFinal.initConfig());
						newEpdStation.setRegionName(epdStation.getCity()+epdStation.getCounty()+epdStation.getTowns());
						gridView.addRow(newEpdStation);
						clearData();
					} else if(CommFinal.OPER_TYPE_UPDATE.equals(operType)){
						iEpdStation.update(epdStation,CommFinal.initConfig());
						epdStation.setRegionName(epdStation.getCity()+epdStation.getCounty()+epdStation.getTowns());
						gridView.updateRow(gridView.getSelectionIndex(), epdStation);
						close();
					}
				}
			} else if (0 == buttonId) {
				/**
				 * 取消
				 */
				close();
			}
		} catch (Exception e) {
			LogUtil.operLog(e,"E",true,true);
		}
	}
	
	private EpdStation validData(){
		String currTime = DateUtils.getNow(DateUtils.FORMAT_LONG);
		EpdStation epdStation = new EpdStation();
		epdStation.setOrganizeSeq(CommFinal.organize.getOrganizeSeq());
		if (CommFinal.OPER_TYPE_UPDATE.equals(operType)){
			epdStation = (EpdStation) gridView.getSelection();
		}
		epdStation.setUpdateTime(currTime);
		if (null != txtStationCode.getText() && !"".equals(txtStationCode.getText())){
			epdStation.setStationCode(txtStationCode.getText());
		}else{
			MsgBox.warning(getParentShell(),"请输入站点代码！");
			txtStationCode.forceFocus();
			return null;
		}
		if (null != txtStationName.getText() && !"".equals(txtStationName.getText())){
			epdStation.setStationName(txtStationName.getText());
		}else{
			MsgBox.warning(getParentShell(),"请输入站点名称！");
			txtStationName.forceFocus();
			return null;
		}
		if (null != dropCity.droptext.getValue() && !"".equals(dropCity.droptext.getText())){
			epdStation.setRegionSeq(dropCity.droptext.getValue());
			epdStation.setRegionName(dropCity.droptext.getText());
		}else{
			MsgBox.warning(getParentShell(),"请输入或选择城市区域！");
			dropCity.droptext.txtShow.forceFocus();
			return null;
		}
		epdStation.setStationSpell(txtStationSpell.getText());
		epdStation.setStationMileage(Double.valueOf(txtStationMileage.getText()));
		epdStation.setStationStatus(CommFinal.getItemValue(TraffFinal.ARR_STATION_STATUS, comStatus.getText()));
		epdStation.setCity(dropCity.droptext.getText());
		epdStation.setCounty(dropCounty.droptext.getText());
		epdStation.setTowns(dropTowns.droptext.getText());
		epdStation.setRemark(txtRemark.getText());
		return epdStation;
	}
	
	private void clearData(){
		txtStationCode.setText("");
		txtStationSpell.setText("");
		txtStationName.setText("");
		txtStationMileage.setText("");
		dropCity.droptext.setValue("");
		dropCounty.droptext.setValue("");
		dropTowns.droptext.setValue("");
		comStatus.clearSelection();
		txtRemark.setText("");
		txtStationName.forceFocus();
	}
	
	public void setSpell(){
		PinyinUtil pinyinUtil = new PinyinUtil();  
		txtStationSpell.setText(pinyinUtil.String2Alpha(txtStationName.getText().toLowerCase()));
	}
	
	/**
	 * initCounty方法描述：
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2015-10-24 上午10:54:03
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @return void
	 */
	public void initCounty(){
		String city = dropCity.droptext.getText();
		dropCounty.initDrop(city);
		
	}
	
	/**
	 * initTowns方法描述：
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2015-10-24 上午10:54:06
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @return void
	 */
	public void initTowns(){
		String city = dropCity.droptext.getText();
		String county = dropCounty.droptext.getText();
		dropTowns.initDrop(city,county);
	}
	
}
