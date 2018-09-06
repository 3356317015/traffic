package com.zhima.frame.ui.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabFolder2Adapter;
import org.eclipse.swt.custom.CTabFolderEvent;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.MouseTrackAdapter;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.forms.events.ExpansionAdapter;
import org.eclipse.ui.forms.events.ExpansionEvent;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;

import com.zhima.basic.CommFinal;
import com.zhima.basic.StyleFinal;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.frame.action.sam.ISamUserToolbar;
import com.zhima.frame.action.sam.impl.ImpSamUserToolbar;
import com.zhima.frame.model.SamModule;
import com.zhima.frame.model.SamUserToolbar;
import com.zhima.util.ImageUtil;
import com.zhima.util.ImageZoom;
import com.zhima.util.SWTResourceManager;
import com.zhima.widget.MsgBox;

public class PanelUi extends Composite {
	public static CTabFolder folder;
	public static ScrolledComposite scrolledComposite;
	public static Composite desktop;
	private List<SamModule> modules;
	private static Text txtPath;
	private static Text txtFind;
	/**
	 * 构造函数:主视图类的构造函数，创建Foler
	 * @param parent
	 * @param CButton 
	 */
	public PanelUi(final Composite parent,int style,List<SamModule> modules) {
		super(parent, style);
		this.setLayout(new FormLayout());
		this.modules = modules;
		//创建自定义选项卡对象
		folder = new CTabFolder(this,SWT.BORDER);
		folder.addCTabFolder2Listener(new CTabFolder2Adapter() {
			@Override
			public void close(CTabFolderEvent arg0) {
				SamModule module = (SamModule) arg0.item.getData();
				if (null != module){
					try {
						CommFinal.insertOperLog(module, "close", "关闭", "1", "");
					} catch (UserBusinessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		folder.setSelectionBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		folder.addMouseMoveListener(new MouseMoveListener() {
			public void mouseMove(MouseEvent arg0) {
				if (Display.getDefault().getActiveShell() != null) {
					if (Display.getDefault().getActiveShell().getCursor() != null) 
						Display.getDefault().getActiveShell().setCursor(null);
				}
			}
		});
		//设置复杂的选项卡，也就是带有圆角的选项卡标签
		folder.setSimple(false);
		folder.setTabHeight(21);
		//设置未选中标签，图标和关闭按钮的状态
		folder.setUnselectedImageVisible(true);
		folder.setUnselectedCloseVisible(true);
		//显示“最大化”和最小化按钮
		folder.setMaximizeVisible(true);
		folder.setBorderVisible(true);
		folder.setFont(StyleFinal.FOLDER_FONT);
		folder.setBackground(SWTResourceManager.getColor(213,230,244));//200,217,240//210,227,242
		//设置选项卡右键菜单
		Menu tabMenu = new Menu(folder);
		MenuItem closeCurrent = new MenuItem(tabMenu,SWT.CASCADE);
		closeCurrent.setText("关闭当前选项卡");
		new MenuItem(tabMenu, SWT.SEPARATOR);
		MenuItem closeOther = new MenuItem(tabMenu, SWT.CASCADE);
		closeOther.setText("关闭其他选项卡");
		MenuItem closeAll = new MenuItem(tabMenu, SWT.CASCADE);
		closeAll.setText("关闭所有选项卡");
		folder.setMenu(tabMenu);
		//关闭当前选项卡
		closeCurrent.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent ev) {
				CTabItem currItem = folder.getSelection();
				if(currItem.getStyle() == SWT.CLOSE){
					SamModule module = (SamModule) currItem.getData();
					if (null != module){
						try {
							CommFinal.insertOperLog(module, "close", "关闭", "1", "");
						} catch (UserBusinessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					currItem.dispose();
				}
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
		//关闭其他选项卡
		closeOther.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent ev) {
				//得到当前选项卡
				CTabItem currItem = folder.getSelection();
				//得到所有标签
				CTabItem[] allItem = folder.getItems();
				for(int i=0;i<allItem.length;i++){
					if(allItem[i] != currItem && allItem[i].getStyle() == SWT.CLOSE){
						SamModule module = (SamModule) allItem[i].getData();
						if (null != module){
							try {
								CommFinal.insertOperLog(module, "close", "关闭", "1", "");
							} catch (UserBusinessException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						allItem[i].dispose();
					}
				}
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
		//关闭所有选项卡
		closeAll.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				//得到所有选项卡
				CTabItem[] allItem = folder.getItems();
				for(int i=0;i<allItem.length;i++){
					if(allItem[i].getStyle() == SWT.CLOSE){
						SamModule module = (SamModule) allItem[i].getData();
						if (null != module){
							try {
								CommFinal.insertOperLog(module, "close", "关闭", "1", "");
							} catch (UserBusinessException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						allItem[i].dispose();
					}
				}
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
		
		//设置该右边这个选项卡的位置
		FormData formData = new FormData();
		formData.top = new FormAttachment(0);
		formData.left = new FormAttachment(0);
		formData.right = new FormAttachment(100);
		formData.bottom = new FormAttachment(100);
		folder.setLayoutData(formData);
		
		//创建首页选项卡标签对象
		CTabItem item = new CTabItem(folder,SWT.NONE);
		item.setText("系统首页");
		Composite index = new Composite(folder, SWT.NONE);
		index.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		FormLayout formLayout = new FormLayout();
		index.setLayout(formLayout);
		item.setControl(index);
		item.setImage(SWTResourceManager.getImage("images/main_page.png"));
		createMain(index);
		//默认选中第一个选项卡
		folder.setSelection(0);

	}
	
	/**
	 * createMain方法描述：创建首页信息
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-4-12 下午05:09:30
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param item void
	 */
	private void createMain(Composite index){
		Composite compSection = new Composite(index, SWT.NONE);
		compSection.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_BLUE));
		compSection.setLayout(new FillLayout());
		
		final List<Section> sections = new ArrayList<Section>();
		
		FormData data = new FormData();
		data.top = new FormAttachment(0);
		data.left = new FormAttachment(0);
		data.bottom = new FormAttachment(100);
		data.width = 220;
		compSection.setLayoutData(data);
		
		FormToolkit toolkit = new FormToolkit(compSection.getDisplay());
        //通过表单工具对象创建可滚动的表单对象
        final ScrolledForm scrolledForm = toolkit.createScrolledForm(compSection);
        scrolledForm.setLayout(new FillLayout());
        //form表单body元素布局
        TableWrapLayout formLayout = new TableWrapLayout();
        formLayout.makeColumnsEqualWidth = true;
        //formLayout.horizontalSpacing = 10;
        //formLayout.verticalSpacing = 8;
        scrolledForm.getBody().setLayout(formLayout);
        scrolledForm.setBackground(SWTResourceManager.getColor(0,128,64));//0,64,128//SWTResourceManager.getColor(84, 131, 178)
       
    	/*if (null != modules && modules.size()>0){
			for (SamModule samModule : modules) {
				if ("".equals(samModule.getParentSeq())
						|| "0".equals(samModule.getParentSeq())){
					if (!"1".equals(samModule.getStatus())){
						continue;
					}
					initSection(toolkit, scrolledForm, sections, samModule);
				}
			}
    	}*/
    	
    	SamModule samModule = new SamModule();
    	samModule.setModuleName("系统桌面");
    	Composite compDesktop = new Composite(index, SWT.NONE);
    	compDesktop.setLayout(new FormLayout());
    	FormData formData = new FormData();
    	formData.top = new FormAttachment(0);
		formData.left = new FormAttachment(compSection);
		formData.right = new FormAttachment(100);
		formData.bottom = new FormAttachment(100);
		compDesktop.setLayoutData(formData);
		compDesktop.setData(samModule);
    	createDesktop(compDesktop);
    	initSection(toolkit, scrolledForm, sections, samModule, compDesktop);

    	samModule = new SamModule();
    	samModule.setModuleName("我的收藏");
    	Composite compWork = new Composite(index, SWT.NONE);
    	compWork.setLayout(new FormLayout());
    	compWork.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
    	formData = new FormData();
    	formData.top = new FormAttachment(0);
		formData.left = new FormAttachment(compSection);
		formData.right = new FormAttachment(100);
		formData.bottom = new FormAttachment(100);
		compWork.setLayoutData(formData);
		compWork.setData(samModule);
		compWork.setVisible(false);
    	initSection(toolkit, scrolledForm, sections, samModule, compWork);

    	samModule = new SamModule();
    	samModule.setModuleName("我的消息");
    	Composite compMessage = new Composite(index, SWT.NONE);
    	compMessage.setLayout(new FormLayout());
    	formData = new FormData();
    	formData.top = new FormAttachment(0);
		formData.left = new FormAttachment(compSection);
		formData.right = new FormAttachment(100);
		formData.bottom = new FormAttachment(100);
		compMessage.setLayoutData(formData);
		compMessage.setData(samModule);
		compMessage.setVisible(false);
    	initSection(toolkit, scrolledForm, sections, samModule, compMessage);
    	sections.get(0).setExpanded(true);
    	
    	samModule = new SamModule();
    	samModule.setModuleName("我的记事");
    	initSection(toolkit, scrolledForm, sections, samModule, compMessage);
    	sections.get(0).setExpanded(true);
    	
    	samModule = new SamModule();
    	samModule.setModuleName("系统帮助");
    	initSection(toolkit, scrolledForm, sections, samModule, compMessage);
    	sections.get(0).setExpanded(true);
	}
	
	protected void initSection(FormToolkit toolkit,ScrolledForm scrolledForm,
			final List<Section> sections,SamModule samModule,Composite compDesktop){
		final Section section = toolkit.createSection(scrolledForm.getBody(), Section.TWISTIE | Section.TITLE_BAR);
		sections.add(section);
		toolkit.paintBordersFor(section);
		//顶级折叠菜单布局
		TableWrapData wrapData = new TableWrapData(TableWrapData.FILL, TableWrapData.TOP, 1, 1);
		wrapData.grabHorizontal = true;
		section.setLayoutData(wrapData);
		String space = "　　　　　　   　　　　　　";
		section.setText(samModule.getModuleName()+space.substring(0, space.length()-samModule.getModuleName().length()));
		section.setData(compDesktop);
		//st.setSize(0, 30);
		Font font = section.getFont();
		section.setFont(SWTResourceManager.getFont(font.getFontData()[0].getName(),10, SWT.NONE));
		section.marginHeight=2;
		//箭头距左边偏移距离
		section.titleBarTextMarginWidth = 0;
		//设置箭头颜色
		section.setToggleColor(SWTResourceManager.getColor(21,66,139));
		//设置文字颜色
		section.setTitleBarForeground(SWTResourceManager.getColor(21,66,139));
		//设置鼠标移上文字颜色
		section.setActiveToggleColor(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		//设置背景色
		//SWTResourceManager.getColor(213,230,244)
		//84, 131, 178
		section.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		//设置边框颜色
		section.setTitleBarBorderColor(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		section.setTitleBarBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		section.addExpansionListener(new ExpansionAdapter(){
            public void expansionStateChanged(ExpansionEvent e ){
            	Composite composite = (Composite) section.getData();
            	if (null != sections && sections.size()>0){
            		for (int i = 0; i < sections.size(); i++) {
            			if (!sections.get(i).getText().equals(section.getText())){
            				Composite compother = (Composite) sections.get(i).getData();
            				compother.setVisible(false);
            				if (sections.get(i).isExpanded()){
            					sections.get(i).setExpanded(false);
            				}
            			}
					}
            	}
            	composite.setVisible(true);
            	section.setExpanded(true);
            	//SamModule module = (SamModule) composite.getData();
            	//initDesktop(module);
            }
	    });
		

		//子级菜单面板布局
        Composite firstComp = new Composite(section, SWT.NONE);
 		RowLayout rowLayout = new RowLayout();
 		//rowLayout.spacing = 5;
 		//rowLayout.pack = false;
 		//rowLayout.type = SWT.VERTICAL;
 		//rowLayout.justify = true;
 		rowLayout.fill = true;
 		firstComp.setLayout(new FormLayout());
 		//将面板绑定到折叠菜单中
 		section.setClient(firstComp);
 		//int h = 0;
 		final Tree tree = new Tree (firstComp, SWT.NONE|SWT.FULL_SELECTION);
 		tree.setFont(SWTResourceManager.getFont(font.getFontData()[0].getName(),10, SWT.NONE));
 		tree.setForeground(SWTResourceManager.getColor(21,66,139));
 		if ("0".equals(samModule.getModuleType())){
			if (null != modules && modules.size()>0){
				for (int i = 0; i < modules.size(); i++) {
					SamModule secondModules = modules.get(i);
					if (secondModules.getParentSeq().equals(samModule.getModuleSeq())){
						if ("0".equals(secondModules.getModuleType())){
							//h += 1;
							TreeItem secondItem = new TreeItem(tree,SWT.NONE);
							secondItem.setData(secondModules);
							secondItem.setText(modules.get(i).getModuleName());
							if (null != modules && modules.size()>0){
								for (int j = 0; j < modules.size(); j++) {
									SamModule thirdModules = modules.get(j);
									if (thirdModules.getParentSeq().equals(secondModules.getModuleSeq())){
										if ("0".equals(thirdModules.getModuleType())){
											//h += 1;
											TreeItem thirdItem = new TreeItem(secondItem,SWT.NONE);
											thirdItem.setData(thirdModules);
											thirdItem.setText(modules.get(j).getModuleName());
										}
									}
								}
							}
						}
					}
				}
			}
		}
 		
 		tree.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				TreeItem ti=(TreeItem)arg0.item;
				SamModule module = (SamModule) ti.getData();
				initDesktop(module,"1");
			}
		});
		FormData formData = new FormData();
		formData.left = new FormAttachment(0);
		formData.top = new FormAttachment(0);
		
		Rectangle screenSize = Display.getDefault().getClientArea();
		formData.height=screenSize.height-200-5*38;
		formData.right = new FormAttachment(100);
		tree.setLayoutData(formData);
	}
	
	protected void createDesktop(Composite compDesktop) {
    	//桌面工具栏
    	Composite compToolbar = new Composite(compDesktop, SWT.NONE);
    	//compToolbar.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
    	compToolbar.setLayout(new FormLayout());
    	FormData formData = new FormData();
    	formData = new FormData();
    	formData.top = new FormAttachment(0);
		formData.left = new FormAttachment(0);
		formData.height = 30;
		formData.right = new FormAttachment(100);
		compToolbar.setLayoutData(formData);
		Label lbMain = new Label(compToolbar, SWT.NONE);
		//lbMain.setText("主页");
		lbMain.setImage(SWTResourceManager.getImage("images/main_index.png"));
		formData = new FormData();
    	formData.top = new FormAttachment(0,5);
		formData.left = new FormAttachment(0,5);
		lbMain.setLayoutData(formData);
		
		Label lbBack = new Label(compToolbar, SWT.NONE);
		lbBack.setImage(SWTResourceManager.getImage("images/main_back.png"));
		formData = new FormData();
    	formData.top = new FormAttachment(0,5);
		formData.left = new FormAttachment(lbMain,10);
		lbBack.setLayoutData(formData);
		
		Label lbRefresh  = new Label(compToolbar, SWT.NONE);
		lbRefresh.setImage(SWTResourceManager.getImage("images/main_refresh.png"));
		formData = new FormData();
    	formData.top = new FormAttachment(0,8);
		formData.left = new FormAttachment(100,-288);
		lbRefresh.setLayoutData(formData);
		
		txtPath = new Text(compToolbar, SWT.BORDER|SWT.READ_ONLY);
		formData = new FormData();
    	formData.top = new FormAttachment(0,6);
		formData.left = new FormAttachment(lbBack,5);
		formData.right = new FormAttachment(100,-265);
		txtPath.setLayoutData(formData);
    	
		Label lbFind  = new Label(compToolbar, SWT.NONE);
		lbFind.setImage(SWTResourceManager.getImage("images/main_find.png"));
		formData = new FormData();
    	formData.top = new FormAttachment(0,8);
		formData.left = new FormAttachment(100,-28);
		lbFind.setLayoutData(formData);
    	
		txtFind = new Text(compToolbar, SWT.BORDER);
		formData = new FormData();
    	formData.top = new FormAttachment(0,6);
		formData.left = new FormAttachment(100,-260);
		formData.right = new FormAttachment(100,-5);
		txtFind.setLayoutData(formData);
		txtFind.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent arg0) {
				// TODO Auto-generated method stub
				SamModule module = new SamModule();
				module.setModuleSeq("0");
				module.setModuleName("查找功能");
				initDesktop(module, "2");
			}
		});

		//系统桌面
		scrolledComposite = new ScrolledComposite(compDesktop, SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setExpandVertical(true);
		scrolledComposite.setExpandHorizontal(true);
		formData = new FormData();
		formData.top = new FormAttachment(compToolbar,5);
		formData.left = new FormAttachment(0);
		formData.bottom = new FormAttachment(100);
		formData.right = new FormAttachment(100);
		scrolledComposite.setLayoutData(formData);
		initMenu();
		lbMain.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseUp(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseDown(MouseEvent arg0) {
				// TODO Auto-generated method stub
				initMenu();
			}
			
			@Override
			public void mouseDoubleClick(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		lbBack.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseUp(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseDown(MouseEvent arg0) {
				// TODO Auto-generated method stub
				SamModule module = (SamModule) txtPath.getData();
				System.out.println(module.getModuleName());
				if ("0".equals(module.getParentSeq())){
					initMenu();
				}else{
					if (null != modules && modules.size()>0){
						for (SamModule samModule : modules) {
							if (module.getParentSeq().equals(samModule.getModuleSeq())){
								System.out.println(samModule.getModuleName());
								if ("0".equals(samModule.getParentSeq())){
									initMenu();
									break;
								}else{
									for (int i = 0; i < modules.size(); i++) {
										if (samModule.getParentSeq().equals(modules.get(i).getModuleSeq())){
											initDesktop(modules.get(i),"1");
											break;
										}
									}
								}
								break;
							}
						}
					}
				}
				
			}
			
			@Override
			public void mouseDoubleClick(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	protected void initDesktop(SamModule module,String initType) {
		if (null != desktop){
    		desktop.dispose();
    	}
    	desktop = new Composite(scrolledComposite, SWT.NONE);
		desktop.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		GridLayout gridLayout = new GridLayout(5, true);
		gridLayout.marginTop = 1;
		gridLayout.marginLeft = 2;
		gridLayout.verticalSpacing = 5;
		gridLayout.horizontalSpacing=5;
		desktop.setLayout(gridLayout);
		
		txtPath.setData(module);
		txtPath.setText(module.getModuleName());
		initPath(module);
		if (null != modules && modules.size()>0){
			Image dehauleImage = SWTResourceManager.getImage("images/module.ico");
			dehauleImage =ImageZoom.getImage(dehauleImage,36,36);;
			for (int i = 0; i < modules.size(); i++) {
				if ("1".equals(initType)){
					//分级菜单
					if (modules.get(i).getParentSeq().equals(module.getModuleSeq())){//&& "1".equals(modules.get(i).getModuleType())
						initModule(dehauleImage,modules.get(i));
					}
				}else{
					//默糊查询
					if (modules.get(i).getModuleName().toLowerCase().indexOf(txtFind.getText().toLowerCase())!=-1){
						initModule(dehauleImage,modules.get(i));
					}
				}

			}
		}
    	scrolledComposite.setContent(desktop);
		scrolledComposite.setMinSize(desktop.computeSize(SWT.DEFAULT, SWT.DEFAULT));
	}


	private void initModule(Image dehauleImage, SamModule samModule) {
		Composite composite = new Composite(desktop, SWT.NONE);
		GridData gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.verticalAlignment = SWT.FILL;
		gridData.heightHint=69;
		composite.setLayoutData(gridData);
		composite.setBackground(produceColor());
		composite.setBackgroundMode(SWT.INHERIT_FORCE);
		composite.setLayout(new FormLayout());
		Label lbIcon = new Label(composite, SWT.NONE);
		FormData data = new FormData();
		data.top = new FormAttachment(5);
		data.left = new FormAttachment(5);
		data.width=38;
		data.height=38;
		lbIcon.setLayoutData(data);
		Image barImage =  ImageZoom.getImage(ImageUtil.Base64ToBlob(samModule.getModuleIcon()),36,36);
		if (null == barImage){
			lbIcon.setImage(dehauleImage);
		}else{
			lbIcon.setImage(barImage);
		}
		
		final Label lbName = new Label(composite, SWT.NONE);
		data = new FormData();
		data.top = new FormAttachment(lbIcon,2);
		data.left = new FormAttachment(5);
		lbName.setLayoutData(data);
		lbName.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lbName.setData(samModule);
		lbName.setText(samModule.getModuleName());
		
		CLabel lbInsert = new CLabel(composite, SWT.CENTER);
		lbInsert.setToolTipText("添加到收藏夹/我的工作");
		//lbInsert.setImage(ImageZoom.getImage(SWTResourceManager.getImage("images/bar_add.png"),16,16));
		lbInsert.setText("＋");
		lbInsert.setFont(StyleFinal.SYS_FONT);
		lbInsert.setForeground(SWTResourceManager.getColor(255,255,255));
		lbInsert.setBackground(SWTResourceManager.getColor(0,162,232));
		data = new FormData();
		data.top = new FormAttachment(0,1);
		data.left = new FormAttachment(100,-40);
		data.bottom = new FormAttachment(50);
		data.right = new FormAttachment(100,-1);
		lbInsert.setLayoutData(data);
		lbInsert.setVisible(false);
		
		CLabel lbRemove = new CLabel(composite, SWT.CENTER);
		lbRemove.setToolTipText("从收藏夹/我的工作移除");
		//lbRemove.setImage(SWTResourceManager.getImage("images/bar_del.png"));
		lbRemove.setText("－");
		lbRemove.setFont(StyleFinal.SYS_FONT);
		lbRemove.setForeground(SWTResourceManager.getColor(255,255,255));
		lbRemove.setBackground(SWTResourceManager.getColor(181,230,29));
		data = new FormData();
		data.top = new FormAttachment(lbInsert,1);
		data.left = new FormAttachment(100,-40);
		data.bottom = new FormAttachment(100,-1);
		data.right = new FormAttachment(100,-1);
		lbRemove.setLayoutData(data);
		lbRemove.setVisible(false);
		
		composite.addMouseTrackListener(changeMode(lbName,lbInsert,lbRemove));
		composite.addMouseListener(openModule(composite, samModule));
		
		lbIcon.addMouseTrackListener(changeMode(lbName,lbInsert,lbRemove));
		lbIcon.addMouseListener(openModule(lbIcon, samModule));
		
		lbName.addMouseTrackListener(changeMode(lbName,lbInsert,lbRemove));
		lbName.addMouseListener(openModule(lbName, samModule));
		
		lbInsert.addMouseListener(insertToolbar(lbInsert,lbRemove,samModule));
		lbRemove.addMouseListener(removeToolbar(samModule));
		
		lbInsert.addMouseTrackListener(changeMenu(composite, lbIcon, lbName, lbInsert,lbRemove,samModule));
		lbRemove.addMouseTrackListener(changeMenu(composite, lbIcon, lbName, lbInsert,lbRemove,samModule));
	}

	private Color produceColor() {  
        Random random = new Random();  
        int red = random.nextInt(255);  
        int green = random.nextInt(255);  
        int blue = random.nextInt(255);   
        RGB rgb = new RGB(red, green, blue);  
        Color color = new Color(Display.getDefault(), rgb);  
        return color;  
	}
	
	private MouseTrackAdapter changeMode(final Control control,final CLabel lbInsert,final CLabel lbRemove){
		MouseTrackAdapter mouseTrackAdapter = new MouseTrackAdapter() {
			@Override
			public void mouseEnter(MouseEvent arg0) {
				control.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
				lbInsert.setVisible(true);
				lbRemove.setVisible(true);
			}
			@Override
			public void mouseExit(MouseEvent arg0) {
				lbInsert.setVisible(false);
				lbRemove.setVisible(false);
				control.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
			}
		};
		return mouseTrackAdapter;
	}
	
	private MouseTrackAdapter changeMenu(final Composite composite,final Label lbIcon, final Label lbName,
			final CLabel lbInsert,final CLabel lbRemove,final SamModule module){
		MouseTrackAdapter mouseTrackAdapter = new MouseTrackAdapter() {
			@Override
			public void mouseEnter(MouseEvent arg0) {
				lbInsert.setVisible(true);
				lbRemove.setVisible(true);
			}
			@Override
			public void mouseExit(MouseEvent arg0) {
				if (composite.isFocusControl()==true
						|| lbIcon.isFocusControl()==true
						|| lbName.isFocusControl()==true
						|| lbInsert.isFocusControl()==true
						|| lbRemove.isFocusControl()==true){
					
				}else{
					lbInsert.setVisible(false);
					lbRemove.setVisible(false);
				}
				
			}
		};
		return mouseTrackAdapter;
	}

	private MouseAdapter insertToolbar(final CLabel lbInsert,final CLabel lbRemove,final SamModule module){
		MouseAdapter mouseAdapter = new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent arg0) {}
			@Override
    		public void mouseDown(MouseEvent arg0) {
				try {
					ISamUserToolbar iSamUserToolbar = new ImpSamUserToolbar();
					SamUserToolbar userToolbar = new SamUserToolbar();
					userToolbar.setUserSeq(CommFinal.user.getUserSeq());
					userToolbar.setModuleSeq(module.getModuleSeq());
					iSamUserToolbar.insert(CommFinal.user.getUserSeq(),userToolbar,CommFinal.initConfig());
					lbInsert.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
				} catch (UserBusinessException e) {
					MsgBox.warning(getShell(),e.getMessage());
				}
    		}
		};
		return mouseAdapter;
	}
	
	private MouseAdapter removeToolbar(final SamModule module){
		MouseAdapter mouseAdapter = new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent arg0) {}
			@Override
    		public void mouseDown(MouseEvent arg0) {
				try {
					ISamUserToolbar iSamUserToolbar = new ImpSamUserToolbar();
					iSamUserToolbar.deleteByUserSeq(CommFinal.user.getUserSeq(),module.getModuleSeq());
				} catch (UserBusinessException e) {
					MsgBox.warning(getShell(),e.getMessage());
				}
    		}
		};
		return mouseAdapter;
	}
	
	private MouseAdapter openModule(Control control,final SamModule module){
		MouseAdapter mouseAdapter = new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent arg0) {}
			@Override
    		public void mouseDown(MouseEvent arg0) {
				if ("0".equals(module.getModuleType())){
					initDesktop(module,"1");
				}else{
					txtPath.setData(module);
					MainUi.getModuleTab(module);
				}
				txtPath.setText(module.getModuleName());
				initPath(module);
    		}
		};
		return mouseAdapter;
	}
	
	private void initMenu(){
		SamModule module = new SamModule();
		module.setModuleSeq("0");
		module.setModuleName("菜单顶级");
		initDesktop(module,"1");
	}
	
	private void initPath(SamModule module){
		if ("0".equals(module.getParentSeq())){
			txtPath.setText("菜单顶级" + "\\"+txtPath.getText());
		}else{
			if (null != modules && modules.size()>0){
				for (SamModule samModule : modules) {
					if (module.getParentSeq().equals(samModule.getModuleSeq())){
						txtPath.setText(samModule.getModuleName()+"\\"+txtPath.getText());
						if (!"0".equals(samModule.getParentSeq())){
							initPath(samModule);
						}else{
							txtPath.setText("菜单顶级" + "\\"+txtPath.getText());
						}
						break;
					}
				}
			}
		}
	}
}
