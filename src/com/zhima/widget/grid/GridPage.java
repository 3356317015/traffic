package com.zhima.widget.grid;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseTrackAdapter;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.zhima.basic.StyleFinal;
import com.zhima.util.SWTResourceManager;
import com.zhima.util.Validator;
import com.zhima.widget.TextVerifyListener;
/**
 * BarPage概要说明：自定义表格分页
 * @author lcy
 */
public class GridPage extends Composite {
	//每页记录行数的下拉列表框
	private Combo cb_limit;
	//跳转到某页
	private Text txt_toPage;
	//总页数
	private Label lb_sumPage;
	
	//总记录行数的文本框
	private Label lb_total;

	//首页按钮
	private Label lb_first;
	//上一页按钮
	private Label lb_back;
	//下一页按钮
	private Label lb_forword;
	//尾页按钮
	private Label lb_end;
	//刷新按钮
	private Label lb_refresh;
	
	//每页记录数
	private int limit=100;	
	//当前页数
	private int pageNo=1;
	//总页数
	private int totalPage=0;
	//总记录数
	private int subtotal=0 ;

	//刷新事件
	private MouseAdapter mouseAdpater;

	/**
	 * 构造函数:初始化分页栏
	 * @param parent
	 * @param style
	 */
	public GridPage(Composite parent, int style) {
		super(parent, style);
		setLayout(new FormLayout());
		Label label_1 = new Label(this, SWT.NONE);
		label_1.setText("每页");
		label_1.setFont(StyleFinal.GRID_FONT);
		FormData data = new FormData();
		data.top = new FormAttachment(0, 4);
		data.left = new FormAttachment(0);
		label_1.setLayoutData(data);

		//每页记录条数
		cb_limit = new Combo(this, SWT.NONE);
		cb_limit.setFont(StyleFinal.GRID_FONT);
		cb_limit.setItems(new String[] {"50", "100", "200", "500", "1000", "2000", "5000", "10000"});
		cb_limit.setText("100");
		data = new FormData();
		data.top = new FormAttachment(0,0);
		data.left = new FormAttachment(label_1, 5, SWT.RIGHT);
		data.width=50;
		cb_limit.setLayoutData(data);
		cb_limit.addVerifyListener(new TextVerifyListener(1));
		cb_limit.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				//改变每页行数时需要重新计算总页数
				String userNum = cb_limit.getText();
				if (null != userNum && userNum.length()>0){
					setLimit(Integer.valueOf(userNum));
					double db_page = Math.ceil(Double.valueOf(subtotal)/Double.valueOf(getLimit()));
					setTotalPage((int) db_page);
					if(mouseAdpater!=null){
						mouseAdpater.mouseDown(null);
					}
				}
			}
		});
		
		Label label_2 = new Label(this, SWT.NONE);
		label_2.setText("条  第");
		label_2.setFont(StyleFinal.GRID_FONT);
		data = new FormData();
		data.top = new FormAttachment(label_1, 0, SWT.TOP);
		data.left = new FormAttachment(cb_limit, 5 , SWT.RIGHT);
		label_2.setLayoutData(data);

		//跳转到某页
		txt_toPage = new Text(this, SWT.BORDER | SWT.CENTER);
		txt_toPage.setText("1");
		txt_toPage.setFont(StyleFinal.GRID_FONT);
		data = new FormData();
		data.top = new FormAttachment(0, 1);
		data.left = new FormAttachment(label_2, 5 , SWT.RIGHT);
		data.width = 40;
		txt_toPage.setLayoutData(data);
		txt_toPage.addVerifyListener(new TextVerifyListener(1));
		txt_toPage.addModifyListener(new ModifyListener() {
			//调转到某页的方法
			public void modifyText(ModifyEvent arg0) {
				if(Validator.IsNumber(txt_toPage.getText())==false){
					txt_toPage.setText(String.valueOf(getPageNo()));
					return ;
				}
				String userPage = txt_toPage.getText();
				if (null != userPage && userPage.length()>0){
					int toPage = Integer.valueOf(userPage);
					if (toPage != getPageNo()){
						if(toPage < 1){
							setPageNo(1,true);
							txt_toPage.selectAll();
						}else if(toPage > totalPage){
							setPageNo(totalPage,true);
							txt_toPage.selectAll();
						}else{
							setPageNo(toPage,false);
						}
						if(mouseAdpater !=null){
							mouseAdpater.mouseDown(null);
						}
					}
				}
			}
		});
		Label label_3 = new Label(this, SWT.NONE);
		label_3.setText("页");
		label_3.setFont(StyleFinal.GRID_FONT);
		data = new FormData();
		data.top = new FormAttachment(label_1, 0, SWT.TOP);
		data.left = new FormAttachment(txt_toPage, 5, SWT.RIGHT);
		label_3.setLayoutData(data);
		
		//第一页
		lb_first = new Label(this, SWT.CENTER);
		lb_first.setToolTipText("第一页");
		lb_first.setImage(SWTResourceManager.getImage("images/grid/begin_dis.gif"));
		data = new FormData();
		data.top = new FormAttachment(label_1, 0, SWT.TOP);
		data.left = new FormAttachment(label_3, 10, SWT.RIGHT);
		data.width=30;
		lb_first.setLayoutData(data);
		lb_first.addMouseListener(new MouseAdapter() {
			//第一页按钮的单击方法
			public void mouseDown(MouseEvent e) {
				setPageNo(1,true);
				if(mouseAdpater!=null){
					mouseAdpater.mouseDown(e);
				}
			}
		});
		lb_first.addMouseTrackListener(new MouseTrackAdapter() {
			@Override
			public void mouseEnter(MouseEvent arg0) {
				lb_first.setData(lb_first.getImage());
				lb_first.setImage(SWTResourceManager.getImage("images/grid/begin_tmp.gif"));
			}
			@Override
			public void mouseExit(MouseEvent arg0) {
				lb_first.setImage((Image)lb_first.getData());
			}
		});
		
		lb_back = new Label(this, SWT.CENTER);
		lb_back.setToolTipText("上一页");
		lb_back.setImage(SWTResourceManager.getImage("images/grid/back_dis.gif"));
		data = new FormData();
		data.top = new FormAttachment(label_1, 0, SWT.TOP);
		data.left = new FormAttachment(lb_first, 5, SWT.RIGHT);
		data.width=30;
		lb_back.setLayoutData(data);
		lb_back.addMouseListener(new MouseAdapter() {
			//上一页按钮的单击方法
			public void mouseDown(MouseEvent e) {
				if(getPageNo() > 1){
					setPageNo(getPageNo() -1,true);
				}
				if(mouseAdpater!=null){
					mouseAdpater.mouseDown(e);
				}
			}
		});
		lb_back.addMouseTrackListener(new MouseTrackAdapter() {
			@Override
			public void mouseEnter(MouseEvent arg0) {
				lb_back.setData(lb_back.getImage());
				lb_back.setImage(SWTResourceManager.getImage("images/grid/back_tmp.gif"));
			}
			@Override
			public void mouseExit(MouseEvent arg0) {
				lb_back.setImage((Image)lb_back.getData());
			}
		});
		
		lb_forword = new Label(this, SWT.CENTER);
		lb_forword.setToolTipText("下一页");
		lb_forword.setImage(SWTResourceManager.getImage("images/grid/forward_dis.gif"));
		data = new FormData();
		data.top = new FormAttachment(label_1, 0, SWT.TOP);
		data.left = new FormAttachment(lb_back, 5, SWT.RIGHT);
		data.width=30;
		lb_forword.setLayoutData(data);
		lb_forword.addMouseListener(new MouseAdapter() {
			//下一页按钮的单击方法
			public void mouseDown(MouseEvent e) {
				if(getPageNo() <= totalPage){
					setPageNo(getPageNo() +1,true);
				}
				if(mouseAdpater!=null){
					mouseAdpater.mouseDown(e);
				}
			}
		});
		lb_forword.addMouseTrackListener(new MouseTrackAdapter() {
			@Override
			public void mouseEnter(MouseEvent arg0) {
				lb_forword.setData(lb_forword.getImage());
				lb_forword.setImage(SWTResourceManager.getImage("images/grid/forward_tmp.gif"));
			}
			@Override
			public void mouseExit(MouseEvent arg0) {
				lb_forword.setImage((Image)lb_forword.getData());
			}
		});
		//最后一页
		lb_end = new Label(this, SWT.CENTER);
		lb_end.setToolTipText("最后一页");
		lb_end.setImage(SWTResourceManager.getImage("images/grid/end_dis.gif"));
		data = new FormData();
		data.top = new FormAttachment(label_1, 0, SWT.TOP);
		data.left = new FormAttachment(lb_forword, 5, SWT.RIGHT);
		data.width=30;
		lb_end.setLayoutData(data);
		lb_end.addMouseListener(new MouseAdapter() {
			//最后一页按钮的单击方法
			public void mouseDown(MouseEvent e) {
				if(getPageNo() <= totalPage){
					setPageNo(totalPage,true);
				}
				if(mouseAdpater!=null){
					mouseAdpater.mouseDown(e);
				}
			}
		});
		lb_end.addMouseTrackListener(new MouseTrackAdapter() {
			@Override
			public void mouseEnter(MouseEvent arg0) {
				lb_end.setData(lb_end.getImage());
				lb_end.setImage(SWTResourceManager.getImage("images/grid/end_tmp.gif"));
			}
			@Override
			public void mouseExit(MouseEvent arg0) {
				lb_end.setImage((Image)lb_end.getData());
			}
		});
		//刷新按钮
		lb_refresh = new Label(this, SWT.NONE);
		lb_refresh.setAlignment(SWT.CENTER);
		lb_refresh.setToolTipText("刷新");
		lb_refresh.setImage(SWTResourceManager.getImage("images/grid/refresh.gif"));
		data = new FormData();
		data.top = new FormAttachment(label_1, 0, SWT.TOP);
		data.left = new FormAttachment(lb_end, 5, SWT.RIGHT);
		data.width=30;
		lb_refresh.setLayoutData(data);
		if(mouseAdpater !=null){
			//刷新按钮的单击方法
			lb_refresh.addMouseListener(mouseAdpater);
		}
		lb_refresh.addMouseTrackListener(new MouseTrackAdapter() {
			@Override
			public void mouseEnter(MouseEvent arg0) {
				lb_refresh.setImage(SWTResourceManager.getImage("images/grid/refresh_tmp.gif"));
			}
			@Override
			public void mouseExit(MouseEvent arg0) {
				lb_refresh.setImage(SWTResourceManager.getImage("images/grid/refresh.gif"));
			}
		});
		
		Label label_4 = new Label(this, SWT.NONE);
		label_4.setText("共");
		label_4.setFont(StyleFinal.GRID_FONT);
		data = new FormData();
		data.top = new FormAttachment(label_1, 0, SWT.TOP);
		data.left = new FormAttachment(100, -215);
		label_4.setLayoutData(data);
		//总页数
		lb_sumPage = new Label(this, SWT.CENTER);
		lb_sumPage.setText("0");
		lb_sumPage.setFont(StyleFinal.GRID_FONT);
		lb_sumPage.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		data = new FormData();
		data.top = new FormAttachment(label_1, 0, SWT.TOP);
		data.left = new FormAttachment(label_4, 5, SWT.RIGHT);
		data.width = 50;
		lb_sumPage.setLayoutData(data);
		
		//共
		Label label_5 = new Label(this, SWT.NONE);
		label_5.setText("页");
		label_5.setFont(StyleFinal.GRID_FONT);
		data = new FormData();
		data.top = new FormAttachment(label_1, 0, SWT.TOP);
		data.left = new FormAttachment(lb_sumPage,5);
		label_5.setLayoutData(data);
		//总记录条数
		lb_total = new Label(this, SWT.CENTER);
		lb_total.setText("0");
		lb_total.setFont(StyleFinal.GRID_FONT);
		lb_total.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		data = new FormData();
		data.top = new FormAttachment(label_1, 0, SWT.TOP);
		data.left = new FormAttachment(label_5, 5, SWT.RIGHT);
		data.width = 60;
		lb_total.setLayoutData(data);
		
		//条记录  当前第
		Label label_6 = new Label(this, SWT.RIGHT);
		data = new FormData();
		label_6.setText("条记录");
		label_6.setFont(StyleFinal.GRID_FONT);
		data.top = new FormAttachment(label_1, 0, SWT.TOP);
		data.left = new FormAttachment(lb_total, 5, SWT.RIGHT);
		data.right = new FormAttachment(100);
		label_6.setLayoutData(data);
		this.lb_first.setEnabled(false);
		this.lb_back.setEnabled(false);
		this.lb_forword.setEnabled(false);
		this.lb_end.setEnabled(false);
	}

	/**
	 * getSubtotal方法描述：得到数据总行数
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2012-10-18 上午12:10:46
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @return int 数据总行数
	 */
	public int getSubtotal() {
		return subtotal;
	}

	/**
	 * setSubtotal方法描述：设置数据总行数
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2012-10-18 上午12:11:18
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param totalCount 数据总行数
	 */
	public void setSubtotal(int totalCount) {
		this.subtotal = totalCount;
		lb_total.setText(String.valueOf(totalCount));
		//设置总行数时得到总页数
		totalPage = (int)Math.ceil(Double.valueOf(totalCount)/Double.valueOf(limit)); 
		lb_sumPage.setText(String.valueOf(totalPage));
		//设置页功能按钮
		this.setPageButton();
	}

	/**
	 * getPageNo方法描述：得到表格当前页
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2012-10-18 上午12:11:45
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @return int 表格当前页
	 */
	public int getPageNo() {
		return pageNo;
	}
	
	/**
	 * setPageNo方法描述：设置表格当前页
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2012-10-18 上午12:12:27
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param pageNo 表格当前页
	 */
	public void setPageNo(int pageNo,boolean isEdit) {
		this.pageNo = pageNo;
		if (isEdit==true){
			this.txt_toPage.setText(String.valueOf(pageNo));
		}
		this.setPageButton();
	}

	/**
	 * getLimit方法描述：得到每页记录数
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2012-10-18 上午12:13:04
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @return int 每页记录数
	 */
	public int getLimit() {
		return limit;
	}

	/**
	 * setLimit方法描述：设置每页记录数
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2012-10-18 上午12:13:34
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param limit 每页记录数
	 */
	public void setLimit(int limit) {
		this.limit = limit;
	}
	
	/**
	 * setTotalPage方法描述：设置表格总页数
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2012-10-18 上午12:15:07
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param totalPage 表格总页数
	 */
	public void setTotalPage(int totalPage){
		this.totalPage = totalPage;
		this.lb_sumPage.setText(String.valueOf(totalPage));
		setPageButton();
	}
	
	/**
	 * setMouseAdpater方法描述：刷新数据方法
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2012-10-18 上午12:15:34
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param mouseAdpater void
	 */
	public void refreshData(MouseAdapter mouseAdpater) {
		this.mouseAdpater = mouseAdpater;
		lb_refresh.addMouseListener(mouseAdpater);
	}
	
	/**
	 * setPageButton方法描述：分页工具按钮方法
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2012-10-18 上午12:17:23
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间) void
	 */
	public void setPageButton(){
		lb_refresh.setImage(SWTResourceManager.getImage("images/grid/wait.gif"));
		if(pageNo == 1){
			this.lb_first.setImage(SWTResourceManager.getImage("images/grid/begin_dis.gif"));
			this.lb_first.setEnabled(false);
			this.lb_back.setImage(SWTResourceManager.getImage("images/grid/back_dis.gif"));
			this.lb_back.setEnabled(false);
		}else{
			this.lb_first.setImage(SWTResourceManager.getImage("images/grid/begin.gif"));
			this.lb_first.setEnabled(true);
			this.lb_back.setImage(SWTResourceManager.getImage("images/grid/back.gif"));
			this.lb_back.setEnabled(true);
		}
		
		if(pageNo > totalPage){
			this.lb_forword.setImage(SWTResourceManager.getImage("images/grid/forward_dis.gif"));
			this.lb_forword.setEnabled(false);
			this.lb_end.setImage(SWTResourceManager.getImage("images/grid/end_dis.gif"));
			this.lb_end.setEnabled(false);
		}else if(pageNo == totalPage){
			this.lb_forword.setImage(SWTResourceManager.getImage("images/grid/forward.gif"));
			this.lb_forword.setEnabled(false);
			this.lb_end.setImage(SWTResourceManager.getImage("images/grid/end.gif"));
			this.lb_end.setEnabled(false);
		}else{
			this.lb_forword.setImage(SWTResourceManager.getImage("images/grid/forward.gif"));
			this.lb_forword.setEnabled(true);
			this.lb_end.setImage(SWTResourceManager.getImage("images/grid/end.gif"));
			this.lb_end.setEnabled(true);
		}
		lb_refresh.setImage(SWTResourceManager.getImage("images/grid/refresh.gif"));
	}
	
}
