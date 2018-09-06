package com.zhima.widget.seatBar;

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
public class SeatPage extends Composite {
	//每页记录行数的下拉列表框
	private Combo cbLimit;
	//跳转到某页
	private Text txtToPage;
	//总页数
	private Label lbSumPage;
	
	//总记录行数的文本框
	private Label lbTotal;

	//首页按钮
	private Label lbFirst;
	//上一页按钮
	private Label lbBack;
	//下一页按钮
	private Label lbForword;
	//尾页按钮
	private Label lbEnd;
	//刷新按钮
	private Label lbRefresh;
	
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
	public SeatPage(Composite parent, int style) {
		super(parent, style);
		setLayout(new FormLayout());
		Label label1 = new Label(this, SWT.NONE);
		label1.setText("每页");
		label1.setFont(StyleFinal.GRID_FONT);
		FormData data = new FormData();
		data.top = new FormAttachment(0, 9);
		data.left = new FormAttachment(0, 5);
		label1.setLayoutData(data);

		//每页记录条数
		cbLimit = new Combo(this, SWT.NONE);
		cbLimit.setFont(StyleFinal.GRID_FONT);
		cbLimit.setItems(new String[] {"50", "100", "200", "500", "1000"});
		cbLimit.setText("100");
		data = new FormData();
		data.top = new FormAttachment(0,5);
		data.left = new FormAttachment(label1, 5, SWT.RIGHT);
		data.width=50;
		cbLimit.setLayoutData(data);
		cbLimit.addVerifyListener(new TextVerifyListener(1));
		cbLimit.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				//改变每页行数时需要重新计算总页数
				String userNum = cbLimit.getText();
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
		
		Label label2 = new Label(this, SWT.NONE);
		label2.setText("条  第");
		label2.setFont(StyleFinal.GRID_FONT);
		data = new FormData();
		data.top = new FormAttachment(label1, 0, SWT.TOP);
		data.left = new FormAttachment(cbLimit, 5 , SWT.RIGHT);
		label2.setLayoutData(data);

		//跳转到某页
		txtToPage = new Text(this, SWT.BORDER | SWT.CENTER);
		txtToPage.setText("1");
		txtToPage.setFont(StyleFinal.GRID_FONT);
		data = new FormData();
		data.top = new FormAttachment(0,5);
		data.left = new FormAttachment(label2, 5 , SWT.RIGHT);
		data.width = 40;
		txtToPage.setLayoutData(data);
		txtToPage.addVerifyListener(new TextVerifyListener(1));
		txtToPage.addModifyListener(new ModifyListener() {
			//调转到某页的方法
			public void modifyText(ModifyEvent arg0) {
				if(Validator.IsNumber(txtToPage.getText())==false){
					txtToPage.setText(String.valueOf(getPageNo()));
					return ;
				}
				String userPage = txtToPage.getText();
				if (null != userPage && userPage.length()>0){
					int toPage = Integer.valueOf(userPage);
					if (toPage != getPageNo()){
						if(toPage < 1){
							setPageNo(1,true);
							txtToPage.selectAll();
						}else if(toPage > totalPage){
							setPageNo(totalPage,true);
							txtToPage.selectAll();
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
		Label label3 = new Label(this, SWT.NONE);
		label3.setText("页");
		label3.setFont(StyleFinal.GRID_FONT);
		data = new FormData();
		data.top = new FormAttachment(label1, 0, SWT.TOP);
		data.left = new FormAttachment(txtToPage, 5, SWT.RIGHT);
		label3.setLayoutData(data);
		
		//第一页
		lbFirst = new Label(this, SWT.CENTER);
		lbFirst.setToolTipText("第一页");
		lbFirst.setImage(SWTResourceManager.getImage("images/grid/begin_dis.gif"));
		data = new FormData();
		data.top = new FormAttachment(label1, 0, SWT.TOP);
		data.left = new FormAttachment(label3, 10, SWT.RIGHT);
		data.width=30;
		lbFirst.setLayoutData(data);
		lbFirst.addMouseListener(new MouseAdapter() {
			//第一页按钮的单击方法
			public void mouseDown(MouseEvent e) {
				setPageNo(1,true);
				if(mouseAdpater!=null){
					mouseAdpater.mouseDown(e);
				}
			}
		});
		lbFirst.addMouseTrackListener(new MouseTrackAdapter() {
			@Override
			public void mouseEnter(MouseEvent arg0) {
				lbFirst.setData(lbFirst.getImage());
				lbFirst.setImage(SWTResourceManager.getImage("images/grid/begin_tmp.gif"));
			}
			@Override
			public void mouseExit(MouseEvent arg0) {
				lbFirst.setImage((Image)lbFirst.getData());
			}
		});
		
		lbBack = new Label(this, SWT.CENTER);
		lbBack.setToolTipText("上一页");
		lbBack.setImage(SWTResourceManager.getImage("images/grid/back_dis.gif"));
		data = new FormData();
		data.top = new FormAttachment(label1, 0, SWT.TOP);
		data.left = new FormAttachment(lbFirst, 5, SWT.RIGHT);
		data.width=30;
		lbBack.setLayoutData(data);
		lbBack.addMouseListener(new MouseAdapter() {
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
		lbBack.addMouseTrackListener(new MouseTrackAdapter() {
			@Override
			public void mouseEnter(MouseEvent arg0) {
				lbBack.setData(lbBack.getImage());
				lbBack.setImage(SWTResourceManager.getImage("images/grid/back_tmp.gif"));
			}
			@Override
			public void mouseExit(MouseEvent arg0) {
				lbBack.setImage((Image)lbBack.getData());
			}
		});
		
		lbForword = new Label(this, SWT.CENTER);
		lbForword.setToolTipText("下一页");
		lbForword.setImage(SWTResourceManager.getImage("images/grid/forward_dis.gif"));
		data = new FormData();
		data.top = new FormAttachment(label1, 0, SWT.TOP);
		data.left = new FormAttachment(lbBack, 5, SWT.RIGHT);
		data.width=30;
		lbForword.setLayoutData(data);
		lbForword.addMouseListener(new MouseAdapter() {
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
		lbForword.addMouseTrackListener(new MouseTrackAdapter() {
			@Override
			public void mouseEnter(MouseEvent arg0) {
				lbForword.setData(lbForword.getImage());
				lbForword.setImage(SWTResourceManager.getImage("images/grid/forward_tmp.gif"));
			}
			@Override
			public void mouseExit(MouseEvent arg0) {
				lbForword.setImage((Image)lbForword.getData());
			}
		});
		//最后一页
		lbEnd = new Label(this, SWT.CENTER);
		lbEnd.setToolTipText("最后一页");
		lbEnd.setImage(SWTResourceManager.getImage("images/grid/end_dis.gif"));
		data = new FormData();
		data.top = new FormAttachment(label1, 0, SWT.TOP);
		data.left = new FormAttachment(lbForword, 5, SWT.RIGHT);
		data.width=30;
		lbEnd.setLayoutData(data);
		lbEnd.addMouseListener(new MouseAdapter() {
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
		lbEnd.addMouseTrackListener(new MouseTrackAdapter() {
			@Override
			public void mouseEnter(MouseEvent arg0) {
				lbEnd.setData(lbEnd.getImage());
				lbEnd.setImage(SWTResourceManager.getImage("images/grid/end_tmp.gif"));
			}
			@Override
			public void mouseExit(MouseEvent arg0) {
				lbEnd.setImage((Image)lbEnd.getData());
			}
		});
		//刷新按钮
		lbRefresh = new Label(this, SWT.NONE);
		lbRefresh.setAlignment(SWT.CENTER);
		lbRefresh.setToolTipText("刷新");
		lbRefresh.setImage(SWTResourceManager.getImage("images/grid/refresh.gif"));
		data = new FormData();
		data.top = new FormAttachment(label1, 0, SWT.TOP);
		data.left = new FormAttachment(lbEnd, 5, SWT.RIGHT);
		data.width=30;
		lbRefresh.setLayoutData(data);
		if(mouseAdpater !=null){
			//刷新按钮的单击方法
			lbRefresh.addMouseListener(mouseAdpater);
		}
		lbRefresh.addMouseTrackListener(new MouseTrackAdapter() {
			@Override
			public void mouseEnter(MouseEvent arg0) {
				lbRefresh.setImage(SWTResourceManager.getImage("images/grid/refresh_tmp.gif"));
			}
			@Override
			public void mouseExit(MouseEvent arg0) {
				lbRefresh.setImage(SWTResourceManager.getImage("images/grid/refresh.gif"));
			}
		});
		
		Label label4 = new Label(this, SWT.NONE);
		label4.setText("共");
		label4.setFont(StyleFinal.GRID_FONT);
		data = new FormData();
		data.top = new FormAttachment(label1, 0, SWT.TOP);
		data.left = new FormAttachment(100, -180);
		label4.setLayoutData(data);
		//总页数
		lbSumPage = new Label(this, SWT.CENTER);
		lbSumPage.setText("0");
		lbSumPage.setFont(StyleFinal.GRID_FONT);
		lbSumPage.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		data = new FormData();
		data.top = new FormAttachment(label1, 0, SWT.TOP);
		data.left = new FormAttachment(label4, 5, SWT.RIGHT);
		data.width = 35;
		lbSumPage.setLayoutData(data);
		
		//共
		Label label5 = new Label(this, SWT.NONE);
		label5.setText("页");
		label5.setFont(StyleFinal.GRID_FONT);
		data = new FormData();
		data.top = new FormAttachment(label1, 0, SWT.TOP);
		data.left = new FormAttachment(lbSumPage,5);
		label5.setLayoutData(data);
		//总记录条数
		lbTotal = new Label(this, SWT.CENTER);
		lbTotal.setText("0");
		lbTotal.setFont(StyleFinal.GRID_FONT);
		lbTotal.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		data = new FormData();
		data.top = new FormAttachment(label1, 0, SWT.TOP);
		data.left = new FormAttachment(label5, 10, SWT.RIGHT);
		data.width = 50;
		lbTotal.setLayoutData(data);
		
		//条记录  当前第
		Label label6 = new Label(this, SWT.RIGHT);
		data = new FormData();
		label6.setText("座位数");
		label6.setFont(StyleFinal.GRID_FONT);
		data.top = new FormAttachment(label1, 0, SWT.TOP);
		data.left = new FormAttachment(lbTotal, 5, SWT.RIGHT);
		data.right = new FormAttachment(100,-5);
		label6.setLayoutData(data);
		
		this.lbFirst.setEnabled(false);
		this.lbBack.setEnabled(false);
		this.lbForword.setEnabled(false);
		this.lbEnd.setEnabled(false);
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
		lbTotal.setText(String.valueOf(totalCount));
		//设置总行数时得到总页数
		totalPage = (int)Math.ceil(Double.valueOf(totalCount)/Double.valueOf(limit)); 
		lbSumPage.setText(String.valueOf(totalPage));
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
			this.txtToPage.setText(String.valueOf(pageNo));
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
		this.lbSumPage.setText(String.valueOf(totalPage));
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
		lbRefresh.addMouseListener(mouseAdpater);
	}
	
	/**
	 * setPageButton方法描述：分页工具按钮方法
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2012-10-18 上午12:17:23
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间) void
	 */
	public void setPageButton(){
		lbRefresh.setImage(SWTResourceManager.getImage("images/grid/wait.gif"));
		if(pageNo == 1){
			this.lbFirst.setImage(SWTResourceManager.getImage("images/grid/begin_dis.gif"));
			this.lbFirst.setEnabled(false);
			this.lbBack.setImage(SWTResourceManager.getImage("images/grid/back_dis.gif"));
			this.lbBack.setEnabled(false);
		}else{
			this.lbFirst.setImage(SWTResourceManager.getImage("images/grid/begin.gif"));
			this.lbFirst.setEnabled(true);
			this.lbBack.setImage(SWTResourceManager.getImage("images/grid/back.gif"));
			this.lbBack.setEnabled(true);
		}
		
		if(pageNo > totalPage){
			this.lbForword.setImage(SWTResourceManager.getImage("images/grid/forward_dis.gif"));
			this.lbForword.setEnabled(false);
			this.lbEnd.setImage(SWTResourceManager.getImage("images/grid/end_dis.gif"));
			this.lbEnd.setEnabled(false);
		}else if(pageNo == totalPage){
			this.lbForword.setImage(SWTResourceManager.getImage("images/grid/forward.gif"));
			this.lbForword.setEnabled(false);
			this.lbEnd.setImage(SWTResourceManager.getImage("images/grid/end.gif"));
			this.lbEnd.setEnabled(false);
		}else{
			this.lbForword.setImage(SWTResourceManager.getImage("images/grid/forward.gif"));
			this.lbForword.setEnabled(true);
			this.lbEnd.setImage(SWTResourceManager.getImage("images/grid/end.gif"));
			this.lbEnd.setEnabled(true);
		}
		lbRefresh.setImage(SWTResourceManager.getImage("images/grid/refresh.gif"));
	}
	
}
