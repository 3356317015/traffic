package com.zhima.widget;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseTrackAdapter;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;

import com.zhima.frame.ui.main.PanelUi;
import com.zhima.util.SWTResourceManager;

/**
 * PanelNormal概要说明：基类混合面板
 * @author lcy
 */
public class BasicPanel extends Composite {
	private Composite parent;
	private int style;
	//工具栏
	public Composite toolbar;
	private boolean isToolbar = true;
	private boolean showBack = true;
	//输入区
	public Composite input;
	private boolean isInput = true;
	//细部
	public Composite detail;
	private boolean isDetail = true;
	
	private boolean isForce = true;
	
	/**
	 * 
	 * 构造函数: 基类混合面板
	 * @param parent
	 * @param style
	 */
	public BasicPanel(Composite parent, int style) {
		super(parent, style);
		this.parent = parent;
		this.style=style;
		//parent.setBackground(SWTResourceManager.getColor(SWT.COLOR_DARK_MAGENTA));
		setLayout(new FormLayout());
	}
	
	/**
	 * createPanel方法描述：创建面板
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-17 下午02:26:15
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间) void
	 */
	public void createPanel(){
		if (isForce==true){
			parent.setBackgroundMode(SWT.INHERIT_FORCE);
		}
		createToolbar(this,style);
		createInput(this,style);
		createDetail(this,style);
	}
	
	
	/**
	 * createToolbar方法描述：创建工具栏
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-17 上午11:26:39
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param parent void
	 */
	private void createToolbar(Composite parent, int style){
		if (isToolbar == true){
			toolbar = new Composite(parent, style);
			FormData formData = new FormData();
			formData.top = new FormAttachment(0);
			formData.left = new FormAttachment(0);
			if (showBack == true){
				formData.right = new FormAttachment(100,-16);
			}else{
				formData.right = new FormAttachment(100);
			}
			
			toolbar.setLayoutData(formData);
			toolbar.addPaintListener(new PaintListener() {
				private Image image_info_bg;
				@Override
				public void paintControl(PaintEvent arg0) {
					Point point = toolbar.getSize();
					if(image_info_bg == null || image_info_bg.getBounds().height != point.y){
						if(image_info_bg!=null&&!image_info_bg.isDisposed())
							image_info_bg.dispose();
							image_info_bg = new Image(toolbar.getDisplay(),1,point.y);
					}
					GC gc_main = new GC(image_info_bg);
					gc_main.setForeground(SWTResourceManager.getColor(202, 202, 202));//111, 111, 202
					gc_main.setBackground(SWTResourceManager.getColor(250, 250, 250));//220, 212, 180//236, 255, 236
					gc_main.fillGradientRectangle(toolbar.getClientArea().x, toolbar.getClientArea().y,
							toolbar.getClientArea().width,toolbar.getClientArea().height,true);
					gc_main.dispose();
					toolbar.setBackgroundImage(image_info_bg);
				}
			});
			if (showBack == true){
				final Composite composite = new Composite(parent, SWT.BORDER);
				formData = new FormData();
				formData.top = new FormAttachment(0);
				formData.left = new FormAttachment(toolbar,-1,SWT.RIGHT);
				formData.right = new FormAttachment(100);
				formData.bottom= new FormAttachment(toolbar,0,SWT.BOTTOM);
				composite.setLayoutData(formData);
				composite.setToolTipText("返回导航");
				composite.setBackground(SWTResourceManager.getColor(213,230,244));
				composite.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseDown(MouseEvent arg0) {
						PanelUi.folder.setSelection(0);
					}
				});
				composite.addMouseTrackListener(new MouseTrackAdapter() {
					@Override
					public void mouseEnter(MouseEvent arg0) {
	        			composite.setBackground(SWTResourceManager.getColor(239, 228, 176));
					}
					@Override
					public void mouseExit(MouseEvent arg0) {
						composite.setBackground(SWTResourceManager.getColor(213,230,244));
					}
				});
				/*final CLabel label = new CLabel(toolbar, SWT.NONE);
				
				Image barImage = SWTResourceManager.getImage("images/back_exit.ico");
				barImage =ImageZoom.getImage(barImage,26,26);
				label.setImage(barImage);
				label.setLayout(new RowLayout());
				label.addMouseTrackListener(new MouseTrackAdapter() {
					@Override
					public void mouseEnter(MouseEvent arg0) {
						Image barImage = SWTResourceManager.getImage("images/back_enter.ico");
	        			barImage =ImageZoom.getImage(barImage,26,26);
	        			label.setImage(barImage);
					}
					@Override
					public void mouseExit(MouseEvent arg0) {
						Image barImage = SWTResourceManager.getImage("images/back_exit.ico");
						barImage =ImageZoom.getImage(barImage,26,26);
	        			label.setImage(barImage);
					}
				});
				label.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseDown(MouseEvent arg0) {
						PanelUi.folder.setSelection(0);
					}
				});*/
			}
		}
	}
	
	/**
	 * createInput方法描述：创建输入区域
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-17 上午11:31:32
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param parent void
	 */
	private void createInput(Composite parent, int style){
		if (isInput == true){
			input = new Composite(parent, style);
			FormData formData = new FormData();
			if (isToolbar == true){
				formData.top = new FormAttachment(toolbar,-1);
			}else{
				formData.top = new FormAttachment(0);
			}
			formData.left = new FormAttachment(0);
			formData.right = new FormAttachment(100);
			input.setLayoutData(formData);
		}
	}
	
	/**
	 * createDetail方法描述：创建细部
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-6-17 上午11:33:31
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param parent void
	 */
	private void createDetail(Composite parent, int style){
		if (isDetail == true){
			detail = new Composite(parent, style);
			FormData formData = new FormData();
			if (isInput == true){
				formData.top = new FormAttachment(input,-1);
			}else if (isToolbar == true){
				formData.top = new FormAttachment(toolbar,-1);
			}else {
				formData.top = new FormAttachment(0);
			}
			formData.left = new FormAttachment(0);
			formData.right = new FormAttachment(100);
			formData.bottom = new FormAttachment(100);
			detail.setLayoutData(formData);
		}
	}


	public boolean isToolbar() {
		return isToolbar;
	}


	public void setToolbar(boolean isToolbar) {
		this.isToolbar = isToolbar;
	}
	
	public boolean isShowBack() {
		return showBack;
	}

	public void setShowBack(boolean showBack) {
		this.showBack = showBack;
	}

	public boolean isInput() {
		return isInput;
	}


	public void setInput(boolean isInput) {
		this.isInput = isInput;
	}


	public boolean isDetail() {
		return isDetail;
	}


	public void setDetail(boolean isDetail) {
		this.isDetail = isDetail;
	}

	public boolean isForce() {
		return isForce;
	}

	public void setForce(boolean isForce) {
		this.isForce = isForce;
	}
	
	
	
}
