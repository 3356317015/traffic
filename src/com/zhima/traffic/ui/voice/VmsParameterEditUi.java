package com.zhima.traffic.ui.voice;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
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
import org.eclipse.swt.widgets.Scale;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.zhima.basic.CallMethod;
import com.zhima.basic.CommFinal;
import com.zhima.basic.StyleFinal;
import com.zhima.traffic.action.voice.IVmsParameter;
import com.zhima.traffic.action.voice.impl.ImpVmsParameter;
import com.zhima.traffic.model.VmsParameter;
import com.zhima.util.DateUtils;
import com.zhima.util.SWTResourceManager;
import com.zhima.util.log4j.LogUtil;
import com.zhima.widget.KLabel;
import com.zhima.widget.MsgBox;
import com.zhima.widget.TextVerifyListener;
import com.zhima.widget.grid.GridView;

public class VmsParameterEditUi extends Dialog {
	private GridView gridView;
	private String operType;
	
	private Text txtParameterName;
	private CCombo cboParameterValue;
	private Text txtParameterValue;
	private Text txtValue;
	private Scale scaleParameterValue;
	String[] ARR_CODE_PAGE = new String[]{
			"936-CODEPAGE_GB",
			"950-CODEPAGE_BIG5",
			"932-CODEPAGE_SHIFTJIS",
			"1252-CODEPAGE_ISO8859_1",
			"1200-CODEPAGE_UNICODE",
			"1201-CP_UNICODE_BIGE",
			"65001-CODEPAGE_UTF8",};
	String[] ARR_DOMAIN = new String[]{
			"0-通用领域",
			"6-汽运 ",
			"30-一般定制领域"};
	String[] ARR_VOICE_AUTO = new String[]{"否","是"};
	String[] ARR_VOICE_ENGLISH = new String[]{"否","是"};
	String[] ARR_VOICE_PUNCMODE = new String[]{
			"0-不合成标点,自动判断回车符是否断句",
			"1-合成标点,自动判断回车符是否断句",
			"2-不合成标点, 强制认为回车符是断句标记",
			"3-合成标点,强制认为回车符是断句标记"};
	String[] ARR_VOICE_DIGITMODE = new String[]{
			"0-自动判断，无法判断的按数目方式合成",
			"1-按电报方式合成",
			"2-按数目方式合成 ",
			"3-自动判断，无法判断的按电报方式播放"
	};
	String[] ARR_VOICE_ENGMODE = new String[]{
			"0-自动",
			"1-不能按单字母方式合成的强制按SAPI合成",
			"2-强制按单字母方式合成",
			"3-强制按单字母或自录音词汇方式合成"
	};
	String[] ARR_VOICE_TAGMODE = new String[]{
			"0-同时检测JTTS标记和SSML标记",
			"1-只检测JTTS标记",
			"2-只检测SSML标记",
			"3-不检测任何标记，标记将被读出",
			"4-只检测S3ML标记"
	};
	
	

	protected VmsParameterEditUi(Shell shell,GridView gridView,String operType) {
		super(shell);
		this.gridView = gridView;
		this.operType = operType;
	}

	protected void configureShell(Shell shell){
		super.configureShell(shell);
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		shell.setBackground(StyleFinal.DIALOG_BACK_GROUND);
		shell.setText("播音设置-"+operType);
	}
	
	@Override
	protected Point getInitialLocation(Point initialSize) {
		Rectangle screenSize =  Display.getDefault().getClientArea();
		return new Point((screenSize.width - initialSize.x)/2, (screenSize.height - initialSize.y)/2);
	}
	
	@Override
	protected Point getInitialSize(){
        return new Point(325,190);
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
		groupMain.setText("参数信息");
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
		
		Label lbParameterName = new Label(groupMain, SWT.RIGHT);
		lbParameterName.setFont(StyleFinal.SYS_FONT);
		lbParameterName.setText("参数名称:");
		GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbParameterName.setLayoutData(gridData);
		
		txtParameterName = new Text(groupMain, SWT.BORDER);
		txtParameterName.setEnabled(false);
		txtParameterName.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtParameterName.setLayoutData(gridData);

		KLabel lbParameterValue = new KLabel(groupMain, SWT.RIGHT);
		lbParameterValue.setFont(StyleFinal.SYS_FONT);
		lbParameterValue.setText("参数值:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbParameterValue.setLayoutData(gridData);
		
		Composite composite = new Composite(groupMain, SWT.NONE);
		composite.setLayout(new FormLayout());
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		
		composite.setLayoutData(gridData);
		
		cboParameterValue = new CCombo(composite, SWT.BORDER|SWT.READ_ONLY);
		cboParameterValue.setFont(StyleFinal.SYS_FONT);
		FormData formData = new FormData();
		formData.left = new FormAttachment(0);
		formData.top = new FormAttachment(0,9);
		formData.right = new FormAttachment(100);
		formData.height = StyleFinal.DROP_HEIGHT;
		cboParameterValue.setLayoutData(formData);
		cboParameterValue.setVisibleItemCount(15);
		
		txtParameterValue = new Text(composite, SWT.BORDER);
		txtParameterValue.setFont(StyleFinal.SYS_FONT);
		formData = new FormData();
		formData.left = new FormAttachment(0);
		formData.top = new FormAttachment(0,9);
		formData.right = new FormAttachment(100);
		formData.height = StyleFinal.TEXT_HEIGHT;
		txtParameterValue.setLayoutData(formData);
		
		txtValue = new Text(composite, SWT.BORDER|SWT.CENTER);
		txtValue.setEnabled(false);
		formData = new FormData();
		formData.left = new FormAttachment(0);
		formData.top = new FormAttachment(0,9);
		formData.width = 30;
		txtValue.setLayoutData(formData);
		
		scaleParameterValue = new Scale(composite, SWT.NONE);
		formData = new FormData();
		formData.left = new FormAttachment(txtValue,2);
		formData.top = new FormAttachment(0);
		formData.right = new FormAttachment(100);
		formData.height = StyleFinal.TEXT_HEIGHT+25;
		scaleParameterValue.setLayoutData(formData);
		scaleParameterValue.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				txtValue.setText(String.valueOf(scaleParameterValue.getSelection()));
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		initData();
		
		callMethod.bindEnterEvent(this, txtParameterName, cboParameterValue, "");
		callMethod.bindEnterEvent(this, cboParameterValue, null, "");
		return compMain;
	}
	
	private void initData(){
		if (CommFinal.OPER_TYPE_UPDATE.equals(operType)){
			VmsParameter vmsParameter = (VmsParameter) gridView.getSelection();
			if (null != vmsParameter && !"".equals(vmsParameter.getParameterSeq())){
				txtParameterName.setText(vmsParameter.getParameterName());
				if ("voice_domain".equals(vmsParameter.getParameterCode())
						|| "voice_code".equals(vmsParameter.getParameterCode())
						|| "voice_puncmode".equals(vmsParameter.getParameterCode())
						|| "voice_digitmode".equals(vmsParameter.getParameterCode())
						|| "voice_engmode".equals(vmsParameter.getParameterCode())
						|| "voice_tagmode".equals(vmsParameter.getParameterCode())
						|| "voice_auto".equals(vmsParameter.getParameterCode())
						|| "liner_english".equals(vmsParameter.getParameterCode())){
					if ("voice_domain".equals(vmsParameter.getParameterCode())){
						cboParameterValue.setItems(CommFinal.getAllName(ARR_DOMAIN));
						cboParameterValue.setText(CommFinal.getItemName(ARR_DOMAIN, vmsParameter.getParameterValue()));
					}else if("voice_code".equals(vmsParameter.getParameterCode())){
						cboParameterValue.setItems(CommFinal.getAllName(ARR_CODE_PAGE));
						cboParameterValue.setText(CommFinal.getItemName(ARR_CODE_PAGE, vmsParameter.getParameterValue()));
					}else if("voice_puncmode".equals(vmsParameter.getParameterCode())){
						cboParameterValue.setItems(CommFinal.getAllName(ARR_VOICE_PUNCMODE));
						cboParameterValue.setText(CommFinal.getItemName(ARR_VOICE_PUNCMODE,vmsParameter.getParameterValue()));
					}else if("voice_digitmode".equals(vmsParameter.getParameterCode())){
						cboParameterValue.setItems(CommFinal.getAllName(ARR_VOICE_DIGITMODE));
						cboParameterValue.setText(CommFinal.getItemName(ARR_VOICE_DIGITMODE,vmsParameter.getParameterValue()));
					}else if("voice_engmode".equals(vmsParameter.getParameterCode())){
						cboParameterValue.setItems(CommFinal.getAllName(ARR_VOICE_ENGMODE));
						cboParameterValue.setText(CommFinal.getItemName(ARR_VOICE_ENGMODE,vmsParameter.getParameterValue()));
					}else if("voice_tagmode".equals(vmsParameter.getParameterCode())){
						cboParameterValue.setItems(CommFinal.getAllName(ARR_VOICE_TAGMODE));
						cboParameterValue.setText(CommFinal.getItemName(ARR_VOICE_TAGMODE,vmsParameter.getParameterValue()));
					}else if("voice_auto".equals(vmsParameter.getParameterCode())){
						cboParameterValue.setItems(ARR_VOICE_AUTO);
						cboParameterValue.setText(vmsParameter.getParameterValue());
					}else if("liner_english".equals(vmsParameter.getParameterCode())){
						cboParameterValue.setItems(ARR_VOICE_ENGLISH);
						cboParameterValue.setText(vmsParameter.getParameterValue());
					}
					cboParameterValue.setVisible(true);
					txtParameterValue.setVisible(false);
					txtValue.setVisible(false);
					scaleParameterValue.setVisible(false);
					cboParameterValue.forceFocus();
				}else if("voice_pitch".equals(vmsParameter.getParameterCode())
						|| "voice_volume".equals(vmsParameter.getParameterCode())
						|| "voice_speed".equals(vmsParameter.getParameterCode())
						|| "voice_sound".equals(vmsParameter.getParameterCode())){
					cboParameterValue.setVisible(false);
					txtParameterValue.setVisible(false);
					txtValue.setVisible(true);
					scaleParameterValue.setVisible(true);
					scaleParameterValue.setMinimum(0);
					scaleParameterValue.setMaximum(9);
					if ("voice_sound".equals(vmsParameter.getParameterCode())){
						scaleParameterValue.setMaximum(86);
					}
					if (null != vmsParameter.getParameterValue() && vmsParameter.getParameterValue().length()>0){
						scaleParameterValue.setSelection(Integer.valueOf(vmsParameter.getParameterValue()));
						txtValue.setText(String.valueOf(vmsParameter.getParameterValue()));
					}
				}else{
					if ("liner_rate".equals(vmsParameter.getParameterCode())
							|| "liner_number".equals(vmsParameter.getParameterCode())
							|| "liner_grade".equals(vmsParameter.getParameterCode())
							|| "text_grade".equals(vmsParameter.getParameterCode())
							|| "voice_grade".equals(vmsParameter.getParameterCode())){
						txtParameterValue.addVerifyListener(new TextVerifyListener(1));
					}
					
					cboParameterValue.setVisible(false);
					txtParameterValue.setVisible(true);
					txtValue.setVisible(false);
					scaleParameterValue.setVisible(false);
					txtParameterValue.setText(vmsParameter.getParameterValue());
					txtParameterValue.setFocus();
					txtParameterValue.selectAll();
				}
			}
		}
	}

	protected void buttonPressed(int buttonId){
		try {
			if(1 == buttonId){
				/**
				 * 确定
				 */
				VmsParameter vmsParameter = validData();
				if (null != vmsParameter){
					if(CommFinal.OPER_TYPE_UPDATE.equals(operType)){
						IVmsParameter iVmsParameter = new ImpVmsParameter();
						List<VmsParameter> vmsParameters = new ArrayList<VmsParameter>();
						vmsParameters.add(vmsParameter);
						iVmsParameter.update(vmsParameters, CommFinal.initConfig());
						gridView.updateRow(gridView.getSelectionIndex(), vmsParameter);
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
	
	private VmsParameter validData(){
		String currTime = DateUtils.getNow(DateUtils.FORMAT_LONG);
		VmsParameter vmsParameter = new VmsParameter();
		vmsParameter.setOrganizeSeq(CommFinal.organize.getOrganizeSeq());
		if (CommFinal.OPER_TYPE_UPDATE.equals(operType)){
			vmsParameter = (VmsParameter) gridView.getSelection();
		}
		vmsParameter.setUpdateTime(currTime);
		if ("voice_domain".equals(vmsParameter.getParameterCode())
				|| "voice_code".equals(vmsParameter.getParameterCode())
				|| "voice_puncmode".equals(vmsParameter.getParameterCode())
				|| "voice_digitmode".equals(vmsParameter.getParameterCode())
				|| "voice_engmode".equals(vmsParameter.getParameterCode())
				|| "voice_tagmode".equals(vmsParameter.getParameterCode())
				|| "voice_auto".equals(vmsParameter.getParameterCode())
				|| "liner_english".equals(vmsParameter.getParameterCode())){
			if (null != cboParameterValue.getText() && !"".equals(cboParameterValue.getText())){
				if ("voice_domain".equals(vmsParameter.getParameterCode())){
					vmsParameter.setParameterValue(CommFinal.getItemValue(ARR_DOMAIN, cboParameterValue.getText()));
				}else if("voice_code".equals(vmsParameter.getParameterCode())){
					vmsParameter.setParameterValue(CommFinal.getItemValue(ARR_CODE_PAGE, cboParameterValue.getText()));
				}else if("voice_puncmode".equals(vmsParameter.getParameterCode())){
					vmsParameter.setParameterValue(CommFinal.getItemValue(ARR_VOICE_PUNCMODE, cboParameterValue.getText()));
				}else if("voice_digitmode".equals(vmsParameter.getParameterCode())){
					vmsParameter.setParameterValue(CommFinal.getItemValue(ARR_VOICE_DIGITMODE, cboParameterValue.getText()));
				}else if("voice_engmode".equals(vmsParameter.getParameterCode())){
					vmsParameter.setParameterValue(CommFinal.getItemValue(ARR_VOICE_ENGMODE, cboParameterValue.getText()));
				}else if("voice_tagmode".equals(vmsParameter.getParameterCode())){
					vmsParameter.setParameterValue(CommFinal.getItemValue(ARR_VOICE_TAGMODE, cboParameterValue.getText()));
				}else if("voice_auto".equals(vmsParameter.getParameterCode())){
					vmsParameter.setParameterValue(cboParameterValue.getText());
				}else if("liner_english".equals(vmsParameter.getParameterCode())){
					vmsParameter.setParameterValue(cboParameterValue.getText());
				}
				vmsParameter.setParameterDesc(cboParameterValue.getText());
			}else{
				MsgBox.warning(getParentShell(),"请设置参数值！");
				cboParameterValue.forceFocus();
				return null;
			}
		}else if("voice_pitch".equals(vmsParameter.getParameterCode())
					|| "voice_volume".equals(vmsParameter.getParameterCode())
					|| "voice_speed".equals(vmsParameter.getParameterCode())
					|| "voice_sound".equals(vmsParameter.getParameterCode())){
			vmsParameter.setParameterValue(String.valueOf(scaleParameterValue.getSelection()));
			vmsParameter.setParameterDesc(String.valueOf(scaleParameterValue.getSelection()));
		}else{
			if (null != txtParameterValue.getText() && !"".equals(txtParameterValue.getText())){
				vmsParameter.setParameterValue(txtParameterValue.getText());
				vmsParameter.setParameterDesc(txtParameterValue.getText());
			}else{
				MsgBox.warning(getParentShell(),"请设置参数值！");
				txtParameterValue.forceFocus();
				return null;
			}
		}
		return vmsParameter;
	}
	
}
