package com.zhima.frame.ui.sam;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
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
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import com.zhima.basic.CallMethod;
import com.zhima.basic.CommFinal;
import com.zhima.basic.StyleFinal;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.frame.action.sam.ISamModule;
import com.zhima.frame.action.sam.impl.ImpSamModule;
import com.zhima.frame.model.SamModule;
import com.zhima.util.DateUtils;
import com.zhima.util.ImageUtil;
import com.zhima.util.SWTResourceManager;
import com.zhima.util.log4j.LogUtil;
import com.zhima.widget.CImage;
import com.zhima.widget.KLabel;
import com.zhima.widget.MsgBox;
import com.zhima.widget.TextVerifyListener;

public class SamModuleEditUi extends Dialog {
	
	private Tree moduleTree;
	
	private String operType;
	
	/** 父项节点 */
	private CCombo comParent;
	
	/** 功能类型 */
	private CCombo comType;
	
	/** 图标 */
	private CImage lbModuleIcon;
	
	/** 功能名称 */
	private Text txtModuleName;
	
	/** 功能代码*/
	private Text txtModuleCode;
	
	/** 类名 */
	private Text txtModuleClass;
	
	/** 包名 */
	private Text txtPackName;
	
	/** 分组名 */
	private CCombo comGroupName;
	
	/** 序号 */
	private CCombo comSn;
	
	/** 描述 */
	private Text txtModuleDesc;
	
	/** 分隔线  */
	private Button separateYes;
	private Button separateNo;
	
	/** 状态 */
	private Button statusYes;
	private Button statusNo;
	
	/** 备注 */
	private Text txtRemark;
	
	protected SamModuleEditUi(Shell shell,Tree moduleTree,String operType) {
		super(shell);
		this.moduleTree=moduleTree;
		this.operType = operType;
	}

	protected void configureShell(Shell shell){
		super.configureShell(shell);
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		shell.setBackground(StyleFinal.DIALOG_BACK_GROUND);
		shell.setText("功能设置-"+operType);
	}
	
	@Override
	protected Point getInitialLocation(Point initialSize) {
		Rectangle screenSize =  Display.getDefault().getClientArea();
		return new Point((screenSize.width - initialSize.x)/2, (screenSize.height - initialSize.y)/2);
	}
	
	@Override
	protected Point getInitialSize(){
        return new Point(488,390);
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
		groupMain.setText("功能信息");
		groupMain.setFont(StyleFinal.SYS_FONT);
		GridLayout gridLayout = new GridLayout(4,false);
		gridLayout.verticalSpacing=StyleFinal.VERTICAL_SPACING;
		groupMain.setLayout(gridLayout);
		
		FormData data = new FormData();
		data.top = new FormAttachment(0,5);
		data.left = new FormAttachment(0,5);
		data.right = new FormAttachment(100,-5);
		data.bottom = new FormAttachment(100);
		groupMain.setLayoutData(data);
		
		KLabel lbParent = new KLabel(groupMain, SWT.RIGHT);
		lbParent.setFont(StyleFinal.SYS_FONT);
		lbParent.setText("父项节点:");
		GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbParent.setLayoutData(gridData);
		
		comParent = new CCombo(groupMain, SWT.BORDER|SWT.READ_ONLY);
		comParent.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.horizontalSpan=3;
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		comParent.setLayoutData(gridData);
		comParent.add("顶层节点");
		comParent.addSelectionListener(new SelectionAdapter() {
			@SuppressWarnings({ "unchecked" })
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				String parentSeq="0";
				if (comParent.getSelectionIndex()!=0){
					List<SamModule> modules = (List<SamModule>) comParent.getData();
					parentSeq=modules.get(comParent.getSelectionIndex()-1).getModuleSeq();
				}
				getGroupAndSn(parentSeq);
			}
		});
		
		
		KLabel lbType = new KLabel(groupMain, SWT.RIGHT);
		lbType.setFont(StyleFinal.SYS_FONT);
		lbType.setText("类型:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbType.setLayoutData(gridData);
		
		comType = new CCombo(groupMain, SWT.BORDER|SWT.READ_ONLY);
		comType.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if (comType.getText().equals("功能")){
					txtModuleClass.setEditable(true);
					txtPackName.setEditable(true);
				}else{
					txtModuleClass.setEditable(false);
					txtPackName.setEditable(false);
				}
			}
		});
		comType.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		comType.setLayoutData(gridData);
		comType.setItems(new String[] {"节点", "功能"});
		
		Label lbImage = new Label(groupMain, SWT.RIGHT);
		lbImage.setFont(StyleFinal.SYS_FONT);
		lbImage.setText("图标:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbImage.setLayoutData(gridData);
		
		lbModuleIcon = new CImage(groupMain, SWT.RIGHT|SWT.BORDER);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_CENTER);
		gridData.widthHint=32;
		gridData.heightHint=32;
		lbModuleIcon.setLayoutData(gridData);
		
		KLabel lbModuleName = new KLabel(groupMain, SWT.RIGHT);
		lbModuleName.setFont(StyleFinal.SYS_FONT);
		lbModuleName.setText("功能名称:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbModuleName.setLayoutData(gridData);
		
		txtModuleName = new Text(groupMain, SWT.BORDER);
		txtModuleName.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.widthHint = 150;
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtModuleName.setLayoutData(gridData);
		
		KLabel lbModuleCode = new KLabel(groupMain, SWT.RIGHT);
		lbModuleCode.setFont(StyleFinal.SYS_FONT);
		lbModuleCode.setText("功能代码:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbModuleCode.setLayoutData(gridData);
		
		txtModuleCode = new Text(groupMain, SWT.BORDER);
		txtModuleCode.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtModuleCode.setLayoutData(gridData);
		
		Label lbModuleClass = new Label(groupMain, SWT.RIGHT);
		lbModuleClass.setFont(StyleFinal.SYS_FONT);
		lbModuleClass.setText("类名:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbModuleClass.setLayoutData(gridData);
		
		txtModuleClass = new Text(groupMain, SWT.BORDER);
		txtModuleClass.setFont(StyleFinal.SYS_FONT);
		txtModuleClass.setEditable(false);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtModuleClass.setLayoutData(gridData);
		
		Label label = new Label(groupMain, SWT.RIGHT);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.horizontalSpan=2;
		label.setLayoutData(gridData);

		Label lbPackName = new Label(groupMain, SWT.RIGHT);
		lbPackName.setFont(StyleFinal.SYS_FONT);
		lbPackName.setText("包:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbPackName.setLayoutData(gridData);
		
		txtPackName = new Text(groupMain, SWT.BORDER);
		txtPackName.setFont(StyleFinal.SYS_FONT);
		txtPackName.setEditable(false);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.horizontalSpan = 3;
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtPackName.setLayoutData(gridData);
		
		Label lbGroupName = new Label(groupMain, SWT.RIGHT);
		lbGroupName.setFont(StyleFinal.SYS_FONT);
		lbGroupName.setText("组:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbGroupName.setLayoutData(gridData);
		
		comGroupName = new CCombo(groupMain, SWT.BORDER);
		comGroupName.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		comGroupName.setLayoutData(gridData);
		
		Label lbSn = new Label(groupMain, SWT.RIGHT);
		lbSn.setFont(StyleFinal.SYS_FONT);
		lbSn.setText("序号:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbSn.setLayoutData(gridData);
		
		comSn = new CCombo(groupMain, SWT.BORDER);
		comSn.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = StyleFinal.DROP_HEIGHT;
		comSn.setLayoutData(gridData);
		comSn.addVerifyListener(new TextVerifyListener(1));
		
		Label lbModuleDesc = new Label(groupMain, SWT.RIGHT);
		lbModuleDesc.setFont(StyleFinal.SYS_FONT);
		lbModuleDesc.setText("描述:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbModuleDesc.setLayoutData(gridData);
		
		txtModuleDesc = new Text(groupMain, SWT.BORDER);
		txtModuleDesc.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.horizontalSpan = 3;
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtModuleDesc.setLayoutData(gridData);
		
		KLabel lbSeparate = new KLabel(groupMain, SWT.RIGHT);
		lbSeparate.setFont(StyleFinal.SYS_FONT);
		lbSeparate.setText("分隔线:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbSeparate.setLayoutData(gridData);
		
		Composite compSeparate = new Composite(groupMain, SWT.NONE);
		compSeparate.setLayout(new GridLayout(2,true));
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		compSeparate.setLayoutData(gridData);
		
		separateYes = new Button(compSeparate, SWT.RADIO);
		separateYes.setFont(StyleFinal.SYS_FONT);
		separateYes.setText("有");
		gridData = new GridData(GridData.FILL_BOTH);
		gridData.horizontalAlignment = SWT.LEFT;
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		separateYes.setLayoutData(gridData);
		
		separateNo = new Button(compSeparate, SWT.RADIO|SWT.CENTER);
		separateNo.setSelection(true);
		separateNo.setFont(StyleFinal.SYS_FONT);
		separateNo.setText("无");
		gridData = new GridData(GridData.FILL_BOTH);
		gridData.horizontalAlignment = SWT.LEFT;
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		separateNo.setLayoutData(gridData);
		
		KLabel lbStatus = new KLabel(groupMain, SWT.RIGHT);
		lbStatus.setFont(StyleFinal.SYS_FONT);
		lbStatus.setText("状态:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbStatus.setLayoutData(gridData);
		
		Composite compStatus = new Composite(groupMain, SWT.NONE);
		compStatus.setLayout(new GridLayout(2,true));
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		compStatus.setLayoutData(gridData);
		
		statusYes = new Button(compStatus, SWT.RADIO);
		statusYes.setSelection(true);
		statusYes.setFont(StyleFinal.SYS_FONT);
		statusYes.setText("有效");
		gridData = new GridData(GridData.FILL_BOTH);
		gridData.horizontalAlignment = SWT.LEFT;
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		statusYes.setLayoutData(gridData);
		
		statusNo = new Button(compStatus, SWT.RADIO|SWT.CENTER);
		statusNo.setFont(StyleFinal.SYS_FONT);
		statusNo.setText("无效");
		gridData = new GridData(GridData.FILL_BOTH);
		gridData.horizontalAlignment = SWT.LEFT;
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		statusNo.setLayoutData(gridData);
		
		Label lbRemark = new Label(groupMain, SWT.RIGHT);
		lbRemark.setFont(StyleFinal.SYS_FONT);
		lbRemark.setText("备注:");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		lbRemark.setLayoutData(gridData);
		
		txtRemark = new Text(groupMain, SWT.BORDER);
		txtRemark.setFont(StyleFinal.SYS_FONT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.horizontalSpan = 3;
		gridData.heightHint = StyleFinal.TEXT_HEIGHT;
		txtRemark.setLayoutData(gridData);
		initData();
		callMethod.bindEnterEvent(this, comParent, comType, "");
		callMethod.bindEnterEvent(this, comType, txtModuleName, "");
		callMethod.bindEnterEvent(this, txtModuleName, txtModuleCode, "");
		callMethod.bindEnterEvent(this, txtModuleCode, txtModuleClass, "");
		callMethod.bindEnterEvent(this, txtModuleClass, txtPackName, "");
		callMethod.bindEnterEvent(this, txtPackName, comGroupName, "");
		callMethod.bindEnterEvent(this, comGroupName, comSn, "");
		callMethod.bindEnterEvent(this, comSn, txtModuleDesc, "");
		callMethod.bindEnterEvent(this, txtModuleDesc, separateYes, "");
		callMethod.bindEnterEvent(this, separateYes, statusYes, "");
		callMethod.bindEnterEvent(this, separateNo, statusYes, "");
		callMethod.bindEnterEvent(this, statusYes, txtRemark, "");
		callMethod.bindEnterEvent(this, statusNo, txtRemark, "");
		callMethod.bindEnterEvent(this, txtRemark, null, "");
		return compMain;
	}
	
	private void initData(){
		try {
			ISamModule iSamModule = new ImpSamModule();
			List<SamModule> samModules = iSamModule.queryModuleParent(CommFinal.organize.getOrganizeSeq());
			TreeItem[] treeItem = moduleTree.getSelection();
			if (null != samModules && samModules.size()>0){
				comParent.setData(samModules);
				for (int i = 0; i < samModules.size(); i++) {
					comParent.add(samModules.get(i).getModuleName());
					treeItem = moduleTree.getSelection();
					if (treeItem.length>0){
						SamModule samModule = (SamModule) treeItem[0].getData();
						getGroupAndSn(samModule.getParentSeq());
						if ("0".equals(samModule.getParentSeq())){
							comParent.select(0);
						}
						else if(samModule.getParentSeq().equals(samModules.get(i).getModuleSeq())){
							comParent.select(i+1);
						}
					}
				}
			}
			if (CommFinal.OPER_TYPE_UPDATE.equals(operType)
					||CommFinal.OPER_TYPE_COPY.equals(operType)){
				if (treeItem.length>0){
					SamModule samModule = (SamModule) treeItem[0].getData();
					getGroupAndSn(samModule.getParentSeq());
					txtModuleName.setText(samModule.getModuleName());
					txtModuleCode.setText(samModule.getModuleCode());
					txtModuleClass.setText(samModule.getModuleClass());
					txtPackName.setText(samModule.getPackName());
					comType.select(Integer.valueOf(samModule.getModuleType()));
					if ("0".equals(samModule.getModuleType())){
						txtModuleClass.setEditable(false);
						txtPackName.setEditable(false);
					}else{
						txtModuleClass.setEditable(true);
						txtPackName.setEditable(true);
					}
					lbModuleIcon.setImage(ImageUtil.Base64ToBlob(samModule.getModuleIcon()),32,32);
					txtModuleDesc.setText(samModule.getModuleDesc());
					comGroupName.setText(samModule.getGroupName());
					comSn.setText(samModule.getSn().toString());
					if ("1".equals(samModule.getSeparate())){
						separateYes.setSelection(true);
						separateNo.setSelection(false);
					} else{
						separateYes.setSelection(false);
						separateNo.setSelection(true);
					}
					if ("1".equals(samModule.getStatus())){
						statusYes.setSelection(true);
						statusNo.setSelection(false);
					} else{
						statusYes.setSelection(false);
						statusNo.setSelection(true);
					}
					txtRemark.setText(samModule.getRemark());
				}
			}
		} catch (UserBusinessException e) {
			LogUtil.operLog(e,"E",true,true);
		}
	}
	

	protected void buttonPressed(int buttonId){
		try {
			if(1 == buttonId){
				/**
				 * 确定
				 */
				SamModule samModule = validData();
				if (null != samModule){
					ISamModule iSamModule = new ImpSamModule();
					if (CommFinal.OPER_TYPE_ADD.equals(operType)
							|| CommFinal.OPER_TYPE_COPY.equals(operType)){
						String copyModuleSeq = "";
						if (CommFinal.OPER_TYPE_COPY.equals(operType)){
							TreeItem[] treeItem = moduleTree.getSelection();
							SamModule module = (SamModule) treeItem[0].getData();
							copyModuleSeq = module.getModuleSeq();
						}
						SamModule newModule = iSamModule.insert(samModule,copyModuleSeq,CommFinal.initConfig());
						TreeItem treeItem = findTreeItem();
						if (null != treeItem){
							TreeItem newItem = new TreeItem(treeItem,SWT.NONE);
							newItem.setData(newModule);
							newItem.setText(showText(samModule));
						}else{
							TreeItem topItem = new TreeItem(moduleTree,SWT.NONE);
							topItem.setData(newModule);
							topItem.setText(showText(samModule));
						}
					} else if(CommFinal.OPER_TYPE_UPDATE.equals(operType)){
						iSamModule.update(samModule,CommFinal.initConfig());
						TreeItem[] treeItem = moduleTree.getSelection();
						if (null != treeItem && treeItem.length>0){
							TreeItem item = treeItem[0];
							item.setData(samModule);
							item.setText(showText(samModule));
						}
					}
					close();
				}
			} else if (0 == buttonId) {
				/**
				 * 取消
				 */
				close();
			}
		} catch (UserBusinessException e) {
			LogUtil.operLog(e,"E",true,true);
		}
	}
	
	@SuppressWarnings("unchecked")
	private SamModule validData(){
		String currTime = DateUtils.getNow(DateUtils.FORMAT_LONG);
		SamModule samModule = new SamModule();
		samModule.setOrganizeSeq(CommFinal.organize.getOrganizeSeq());
		TreeItem[] treeItem = moduleTree.getSelection();
		if (CommFinal.OPER_TYPE_ADD.equals(operType)
				||CommFinal.OPER_TYPE_COPY.equals(operType)){
			samModule.setCreateTime(currTime);
		} else if (CommFinal.OPER_TYPE_UPDATE.equals(operType)){
			if (treeItem.length>0){
				samModule = (SamModule) treeItem[0].getData();
			}
		}
		samModule.setUpdateTime(currTime);
		if (comParent.getSelectionIndex() != -1){
			samModule.setParentSeq("0");
			samModule.setParentName("");
			if (comParent.getSelectionIndex()!=0){
				List<SamModule> samModules = (List<SamModule>) comParent.getData();
				samModule.setParentSeq(samModules.get(comParent.getSelectionIndex()-1).getModuleSeq());
				samModule.setParentName(samModules.get(comParent.getSelectionIndex()-1).getModuleName());
			}
		}else{
			MsgBox.warning(getParentShell(),"请选择父项节点。");
			comParent.forceFocus();
			return null;
		}
		
		if (comType.getSelectionIndex() != -1){
			samModule.setModuleType(String.valueOf(comType.getSelectionIndex()));
		}else{
			MsgBox.warning(getParentShell(),"请选择功能类型。");
			comType.forceFocus();
			return null;
		}

		samModule.setModuleIcon(ImageUtil.blobToBase64(lbModuleIcon.getBlob()));
	
		if (null != txtModuleName.getText() && !"".equals(txtModuleName.getText())){
			samModule.setModuleName(txtModuleName.getText());
		}else{
			MsgBox.warning(getParentShell(),"请输入功能名称。");
			txtModuleName.forceFocus();
			return null;
		}
		
		if (null != txtModuleCode.getText() && !"".equals(txtModuleCode.getText())){
			samModule.setModuleCode(txtModuleCode.getText());
		}else{
			MsgBox.warning(getParentShell(),"请输入功能代码。");
			txtModuleCode.forceFocus();
			return null;
		}
		
		if ("1".equals(samModule.getModuleType())){
			if (null != txtModuleClass.getText() && !"".equals(txtModuleClass.getText())){
				
			}else{
				MsgBox.warning(getParentShell(),"请输入类名。");
				txtModuleClass.forceFocus();
				return null;
			}
			
			if (null != txtPackName.getText() && !"".equals(txtPackName.getText())){
				
			}else{
				MsgBox.warning(getParentShell(),"请输入包路径。");
				txtPackName.forceFocus();
				return null;
			}
		}
		samModule.setModuleClass(txtModuleClass.getText());
		samModule.setPackName(txtPackName.getText());
		
		samModule.setGroupName(comGroupName.getText());
		if (!"".equals(comSn.getText())){
			samModule.setSn(Integer.valueOf(comSn.getText()));
		}else{
			samModule.setSn(0);
		}
		
		samModule.setModuleDesc(txtModuleDesc.getText());
		if (separateYes.getSelection() == true){
			samModule.setSeparate("1");
		}else{
			samModule.setSeparate("0");
		}
		if (statusYes.getSelection() == true){
			samModule.setStatus("1");
		}else{
			samModule.setStatus("0");
		}
		samModule.setRemark(txtRemark.getText());
		return samModule;
	}
	
	@SuppressWarnings({ "unchecked" })
	private TreeItem findTreeItem(){
		String parentSeq="0";
		if (comParent.getSelectionIndex()!=0){
			List<SamModule> modules = (List<SamModule>) comParent.getData();
			parentSeq=modules.get(comParent.getSelectionIndex()-1).getModuleSeq();
		}
		if ("0".equals(parentSeq)){
			return null;
		}else{
			TreeItem parentItem = null;
			for (TreeItem firstItem : moduleTree.getItems()) {
				SamModule firstModule = (SamModule) firstItem.getData();
				if (parentSeq.equals(firstModule.getModuleSeq())){
					parentItem = firstItem;
					break;
				} else{
					for (TreeItem secondItem : firstItem.getItems()) {
						SamModule secondModule = (SamModule) secondItem.getData();
						if (parentSeq.equals(secondModule.getModuleSeq())){
							parentItem = secondItem;
							break;
						}else{
							for (TreeItem thirdItem : secondItem.getItems()) {
								SamModule thirdModule = (SamModule) thirdItem.getData();
								if (parentSeq.equals(thirdModule.getModuleSeq())){
									parentItem = thirdItem;
									break;
								}else{
									for (TreeItem fourthItem : thirdItem.getItems()) {
										SamModule fourthModule = (SamModule) fourthItem.getData();
										if (parentSeq.equals(fourthModule.getModuleSeq())){
											parentItem = fourthItem;
											break;
										}
									}
								}
							}
						}
					}
				}
			}
			return parentItem;
		}
	}
	
	private String[] showText(SamModule samModule){
		String moduleType ="功能";
		if ("0".equals(samModule.getModuleType())){
			 moduleType ="节点";
		}
		String separate = "有";
		if ("0".equals(samModule.getSeparate())){
			separate ="无";
		}
		String status ="有效";
		if ("0".equals(samModule.getStatus())){
			status ="无效";
		}
		return new String[]{samModule.getModuleName(),samModule.getModuleClass(),samModule.getPackName(),
				moduleType,separate,samModule.getGroupName(),samModule.getSn().toString(),status,
				samModule.getModuleDesc(),samModule.getRemark(),samModule.getCreateTime(),samModule.getUpdateTime()};
		
	}
	
	private void getGroupAndSn(String parentSeq){
		try {
			comSn.removeAll();
			comGroupName.removeAll();
			ISamModule iSamModule = new ImpSamModule();
			List<SamModule> samModules;
			samModules = iSamModule.querySubAll(parentSeq);
			if (null != samModules && samModules.size()>0){
				List<SamModule> sns = new ArrayList<SamModule>();
				List<SamModule> groupNames = new ArrayList<SamModule>();
				boolean addSn = true;
				boolean addGroup = true;
				for (int i = 0; i < samModules.size(); i++) {
					addSn = true;
					addGroup = true;
					if (null != samModules.get(i).getSn()
							&& !"".equals(samModules.get(i).getSn())){
						if (null != sns && sns.size()>0){
							for (int j = 0; j < sns.size(); j++) {
								if (samModules.get(i).getSn()==sns.get(j).getSn()){
									addSn = false;
									break;
								}
							}
							if (addSn == true){
								sns.add(samModules.get(i));
							}
						}else{
							sns.add(samModules.get(i));
						}
					}
					
					if (null != samModules.get(i).getGroupName()
							&& !"".equals(samModules.get(i).getGroupName())){
						if (null != groupNames && groupNames.size()>0){
							for (int j = 0; j < groupNames.size(); j++) {
								if (samModules.get(i).getGroupName().equals(groupNames.get(j).getGroupName())){
									addGroup = false;
									break;
								}
							}
							if (addGroup == true){
								groupNames.add(samModules.get(i));
							}
						}else{
							groupNames.add(samModules.get(i));
						}
					}
				}
				if (null != sns && sns.size()>0){
					for (int i = 0; i < sns.size(); i++) {
						comSn.add(samModules.get(i).getSn().toString());
					}
				}
				if (null != groupNames && groupNames.size()>0){
					for (int i = 0; i < groupNames.size(); i++) {
						comGroupName.add(groupNames.get(i).getGroupName());
					}
				}
			}
		} catch (UserBusinessException e) {
			LogUtil.operLog(e,"E",true,true);
		}
	}
	
}
