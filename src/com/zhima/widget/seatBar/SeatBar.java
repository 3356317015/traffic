package com.zhima.widget.seatBar;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.zhima.basic.CommFinal;
import com.zhima.traffic.comm.TraffFinal;
import com.zhima.util.ImageZoom;
import com.zhima.util.SWTResourceManager;
import com.zhima.util.log4j.LogUtil;

public class SeatBar extends Composite {
	/**
	 * 滚动条
	 * */
	private ScrolledComposite scrolledComposite;
	/**
	 * 主面板
	 * */
	public Composite compSeat;
	
	private SeatPage seatPage;
	/**
	 * 席位集合
	 * **/
	private List<Composite> composites = new ArrayList<Composite>();
	
	/**
	 * 打开座位图片
	 * */
	private Image openImage = ImageZoom.getImage(SWTResourceManager.getImage("images/seat/open.png"), 56, 50);
	/**
	 * 关闭座位图片
	 * */
	private Image closeImage = ImageZoom.getImage(SWTResourceManager.getImage("images/seat/close.png"), 56, 50);
	/**
	 * 预订座位图片
	 */
	private Image lockImage = ImageZoom.getImage(SWTResourceManager.getImage("images/seat/lock.png"), 56, 50);
	/**
	 * 配载座位图片
	 */
	private Image assortImage = ImageZoom.getImage(SWTResourceManager.getImage("images/seat/deal.png"), 56, 50);
	/**
	 * 未检座位图片
	 */
	private Image uncheckImage = ImageZoom.getImage(SWTResourceManager.getImage("images/seat/uncheck.png"), 56, 50);

	/**
	 * 已检座位图片
	 */
	private Image checkImage = ImageZoom.getImage(SWTResourceManager.getImage("images/seat/check.png"), 56, 50);

	/**
	 * 已选中
	 */
	private Color selectColor = SWTResourceManager.getColor(149, 195, 236);
	/**
	 * 未选中
	 */
	private Color unselectColor = SWTResourceManager.getColor(245,245,245);

	/**
	 * 对象集合
	 */
	private List<Seat> seatItems = new ArrayList<Seat>();
	
	private boolean showMore;
	/**
	 * 起点坐标
	 */
	private int startX;
	private int startY;
	/**
	 * 终点坐标
	 */
	private int endX;
	private int endY;


	public SeatBar(Composite parent, boolean showMore, int style) {
		super(parent, style);
		this.showMore = showMore;
		this.setLayout(new FormLayout());
		Composite compMain = new Composite(this, SWT.NONE);
		FormData formData = new FormData();
		formData.top = new FormAttachment(0);
		formData.left = new FormAttachment(0);
		formData.right = new FormAttachment(100);
		formData.bottom = new FormAttachment(100, -30);
		compMain.setLayoutData(formData);
		FillLayout fillLayout = new FillLayout();
		fillLayout.marginHeight = 0;
		fillLayout.marginWidth = 0;
		fillLayout.type = SWT.HORIZONTAL;
		compMain.setLayout(fillLayout);

		scrolledComposite = new ScrolledComposite(compMain, SWT.H_SCROLL | SWT.V_SCROLL);
		compSeat = new Composite(scrolledComposite, SWT.NONE);
		RowLayout rowLayout = new RowLayout();
		rowLayout.pack = false;
		rowLayout.type = SWT.HORIZONTAL;
		rowLayout.spacing = 8;
		rowLayout.marginTop = 8;
		rowLayout.marginBottom = 8;
		rowLayout.marginLeft = 8;
		rowLayout.marginRight = 8;
		compSeat.setLayout(rowLayout);
		//mainComp.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		compSeat.addMouseMoveListener(new MouseMoveListener() {
			public void mouseMove(MouseEvent me) {
				endX = me.x;
				endY = me.y;
				if (startX != 0) {
					compSeat.redraw();
					compSeat.update();
					GC gc = new GC(compSeat);
					gc.setForeground(selectColor);
					gc.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.NORMAL));
					gc.drawRectangle(startX, startY, endX - startX, endY- startY);
					gc.dispose();
					for (int i = 0; i < composites.size(); i++) {
						int compOx = composites.get(i).getBounds().x;
						int compOy = composites.get(i).getBounds().y;
						int compTx = composites.get(i).getBounds().x
								+ composites.get(i).getBounds().width;
						int compTy = composites.get(i).getBounds().y;
						int compThx = composites.get(i).getBounds().x;
						int compThy = composites.get(i).getBounds().y
								+ composites.get(i).getBounds().height;
						int compFx = composites.get(i).getBounds().x
								+ composites.get(i).getBounds().width;
						int compFy = composites.get(i).getBounds().y
								+ composites.get(i).getBounds().height;

						int minX = Math.min(startX, endX);
						int minY = Math.min(startY, endY);

						int maxX = Math.max(startX, endX);
						int maxY = Math.max(startY, endY);
						// 是否选中
						boolean isxz = false;
						if (compOx >= minX && compOx <= maxX && compOy >= minY
								&& compOy <= maxY) {
							composites.get(i).setBackground(selectColor);
							isxz = true;
						}
						if (compTx >= minX && compTx <= maxX && compTy >= minY
								&& compTy <= maxY) {
							composites.get(i).setBackground(selectColor);
							isxz = true;
						}
						if (compThx >= minX && compThx <= maxX
								&& compThy >= minY && compThy <= maxY) {
							composites.get(i).setBackground(selectColor);
							isxz = true;
						}
						if (compFx >= minX && compFx <= maxX && compFy >= minY
								&& compFy <= maxY) {
							composites.get(i).setBackground(selectColor);
							isxz = true;
						}
						if (isxz == false) {
							composites.get(i).setBackground(unselectColor);
						}
					}
				}
			}
		});
		compSeat.addMouseListener(new MouseAdapter() {
			public void mouseDown(MouseEvent e) {
				for (int i = 0; i < composites.size(); i++) {
					composites.get(i).setBackground(unselectColor);
				}
				startX = e.x;
				startY = e.y;
			}

			public void mouseUp(MouseEvent e) {
				compSeat.redraw();
				startX = 0;
				startY = 0;
			};
		});

		scrolledComposite.setContent(compSeat);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		scrolledComposite.setMinWidth(230);
		scrolledComposite.setMinHeight(10000);
		// 将滚动面板的大小设置为父面板大小
		scrolledComposite.addControlListener(new ControlAdapter() {
			public void controlResized(ControlEvent e) {
				Rectangle r = scrolledComposite.getClientArea();
				scrolledComposite.setMinSize(compSeat.computeSize(r.width,
						SWT.DEFAULT));
			}
		});
		
		seatPage = new SeatPage(this, SWT.NONE);
		formData = new FormData();
		formData.top = new FormAttachment(compMain,0);
		formData.left = new FormAttachment(0);
		formData.right = new FormAttachment(100);
		formData.bottom = new FormAttachment(100);
		seatPage.setLayoutData(formData);
	}

	public void createSeats(List<Seat> seats) {
		// 清除单个席位
		clearSeat();
		seatItems = seats;
		if (null != seats && seats.size()>0){
			for (Seat seat : seats) {
				Composite seatComp = createSeat(compSeat, seat);
				seatComp.setLayoutData(new RowData(62, 55));
				composites.add(seatComp);
			}
		}

		compSeat.layout(true);
		scrolledComposite.setContent(compSeat);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		scrolledComposite.setMinWidth(230);
		scrolledComposite.setMinHeight(((composites.size() / 10) * 78));
		// 将滚动面板的大小设置为父面板大小
		scrolledComposite.addControlListener(new ControlAdapter() {
			public void controlResized(ControlEvent e) {
				Rectangle r = scrolledComposite.getClientArea();
				scrolledComposite.setMinSize(compSeat.computeSize(r.width,
						SWT.DEFAULT));
			}
		});
	}

	/***
	 * createSeat方法慨述:设置单个席位 创 建 人：黄辉勇 创建时间：2011-4-10 下午05:39:02 修 改 人：黄辉勇
	 * 修改日期：(请填上修改该文件时的日期)
	 * 
	 * @param planseatState
	 * @param ticketState
	 * @return Composite
	 * @throws
	 */
	public Composite createSeat(Composite parent, Seat seat) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.addMouseListener(createListener(composite));
		composite.setBackgroundMode(SWT.INHERIT_FORCE);
		composite.setLayout(new FormLayout());
		composite.setBackground(unselectColor);
		CLabel cLabel = new CLabel(composite, SWT.NONE);
		cLabel.addMouseListener(createListener(composite));
		setSeatInfo(cLabel,seat);
		return composite;
	}
	
	private void setSeatInfo(CLabel cLabel,final Seat seat){
		cLabel.addPaintListener(new PaintListener() {		
			@Override
			public void paintControl(PaintEvent paintevent) {
				//paintevent.gc.setFont(ConfigureUi.USER_FONT_STYLE);
				//paintevent.gc.setForeground(ConfigureUi.USER_FONT_COLOR);
				Font font = paintevent.gc.getFont();
				Font useFont = new Font(paintevent.gc.getDevice(), font.getFontData()[0].getName(), 8, font.getFontData()[0].getStyle());
				if (seat.getSeatId().length()==1){
					paintevent.gc.drawText(String.valueOf(seat.getSeatId()), 27, 1, true);	
				}else if (seat.getSeatId().length()==2){
					paintevent.gc.drawText(String.valueOf(seat.getSeatId()), 24, 1, true);
				}else if (seat.getSeatId().length()==3){
					paintevent.gc.drawText(String.valueOf(seat.getSeatId()), 21, 1, true);
				}else if (seat.getSeatId().length()==4){
					paintevent.gc.drawText(String.valueOf(seat.getSeatId()), 17, 1, true);
				}
				if (seat.getSeatState()==2){
					paintevent.gc.setFont(useFont);
					if (null != seat.getSaleType() && seat.getSaleType().length()>0){
						if ("1".equals(seat.getSaleType())){
							paintevent.gc.drawText("本站售", 14, 13, true);
						}else if("2".equals(seat.getSaleType())){
							paintevent.gc.drawText("自助售", 14, 13, true);
						}else if("3".equals(seat.getSaleType())){
							paintevent.gc.drawText("异站售", 14, 13, true);
						}else if("4".equals(seat.getSaleType())){
							paintevent.gc.drawText("代售", 19, 13, true);
						}else if("5".equals(seat.getSaleType())){
							paintevent.gc.drawText("网售", 19, 13, true);
						}else if("6".equals(seat.getSaleType())){
							paintevent.gc.drawText("手机售", 14, 13, true);
						}else if("7".equals(seat.getSaleType())){
							paintevent.gc.drawText("微信售", 14, 13, true);
						}
					}
					if (null != seat.getFaretypeName() && seat.getFaretypeName().length()>0){
						if (seat.getFaretypeName().length()==2){
							paintevent.gc.drawText(String.valueOf(seat.getFaretypeName()), 20, 23, true);
						}else if(seat.getFaretypeName().length()==3){
							paintevent.gc.drawText(String.valueOf(seat.getFaretypeName()), 14, 23, true);
						}else{
							paintevent.gc.drawText(String.valueOf(seat.getFaretypeName()), 20, 23, true);
						}
					}
					paintevent.gc.setFont(font);
					if (null != seat.getStationName() && seat.getStationName().length()>0){
						if (seat.getStationName().length()==1){
							paintevent.gc.drawText(String.valueOf(seat.getStationName()), 27, 36, true);
						}else if (seat.getStationName().length()==2){
							paintevent.gc.drawText(String.valueOf(seat.getStationName()), 19, 36, true);
						}else if(seat.getStationName().length()==3){
							paintevent.gc.drawText(String.valueOf(seat.getStationName()), 15, 36, true);
						}else if(seat.getStationName().length()==4){
							paintevent.gc.drawText(String.valueOf(seat.getStationName()), 8, 36, true);
						}else if(seat.getStationName().length()==5){
							paintevent.gc.drawText(String.valueOf(seat.getStationName()), 2, 36, true);
						}else{
							paintevent.gc.drawText(String.valueOf(seat.getStationName()), 0, 36, true);
						}
					}
				}
			}
		});

		
		if (seat.getSeatState() == 2) {
			cLabel.setImage(uncheckImage);
			if (seat.getTicketStates() == 2) {
				cLabel.setImage(checkImage);
			}
			String str = "座位号:" + String.valueOf(seat.getSeatId()+"("
					+CommFinal.getItemName(TraffFinal.ARR_SEAT_STATE, String.valueOf(seat.getSeatState()))+")\n");
			str += "目的地:" + seat.getStationName() + "\n";
			str += "票型:" + seat.getFaretypeName()+"($"+seat.getTicketFare()+")\n";
			if(showMore==true) {
				str += "票据号:" + seat.getTicketId() + "\n";
				str += "客户姓名:" + seat.getCustomer() + "\n";
				str += "联系电话:" + seat.getTelephone() + "\n";
				str += "售票员:" + seat.getSaleUsername() + "\n";
				str += "售票时间:" + seat.getSaleTime() + "\n";
			}
			str += "售票方式:" +CommFinal.getItemName(TraffFinal.ARR_SALE_TYPE, String.valueOf(seat.getSaleType()))+"\n";
			str += "售票机构:" + seat.getSaleOrganizename() + "\n";
			if (seat.getTicketStates() == 2) {
				str += "检票员:" + seat.getCheckUsername() + "\n";
				str += "检票时间:" + seat.getCheckTime() + "\n";
			}
			cLabel.setToolTipText(str);
		}else{
			// 禁售
			if (seat.getSeatState() == 0) {
				cLabel.setImage(closeImage);
			}
			// 可售
			if (seat.getSeatState() == 1) {
				cLabel.setImage(openImage);
			}
			// 预订
			if (seat.getSeatState() == 3) {
				cLabel.setImage(lockImage);
			}
			// 配载
			if (seat.getSeatState() == 4) {
				cLabel.setImage(assortImage);
			}
		}
	}


	public void clearSeat() {
		for (int i = (composites.size() - 1); i >= 0; i--) {
			clear(composites.get(i));
			composites.remove(i);
			seatItems.remove(i);
		}
	}

	private void clear(Composite comp) {
		dispose(comp);
		Control[] cons = comp.getChildren();
		for (int i = 0; i < cons.length; i++) {
			dispose(cons[i]);
		}
		comp.dispose();
		comp = null;
	}

	private void dispose(Control conrol) {
		// 释放字体颜色
		if (null != conrol.getForeground()
				&& !conrol.getForeground().isDisposed()) {
			conrol.getForeground().dispose();
		}
		/*
		 * //释放字体 if(null != conrol.getFont() &&
		 * !conrol.getFont().isDisposed()){ conrol.getFont().dispose(); }
		 */
		// 释放背景图片
		if (null != conrol.getBackgroundImage()
				&& !conrol.getBackgroundImage().isDisposed()) {
			conrol.getBackgroundImage().dispose();
		}
		// 释放背景颜色
		if (null != conrol.getBackground()
				&& !conrol.getBackground().isDisposed()) {
			conrol.getBackground().dispose();
		}
		if (null != conrol.getCursor() && !conrol.getCursor().isDisposed()) {
			// 释放光标
			conrol.getCursor().dispose();
		}
		if (null != conrol.getAccessible()) {
			conrol.getAccessible().dispose();
		}
		if (null != conrol.getMenu() && !conrol.getMenu().isDisposed()) {
			// 释放菜单
			conrol.getMenu().dispose();
		}
		if (null != conrol.getRegion() && !conrol.getRegion().isDisposed()) {
			conrol.getRegion().dispose();
		}
	}

	private MouseListener createListener(final Composite seatComp) {
		MouseListener listener = new MouseListener() {
			public void mouseDown(MouseEvent e) {
				if (seatComp.getBackground().equals(selectColor)) {
					seatComp.setBackground(unselectColor);
				} else {
					seatComp.setBackground(selectColor);
				}
			}
			public void mouseDoubleClick(MouseEvent e) {
			}
			public void mouseUp(MouseEvent e) {
			}
		};
		return listener;
	}

	public Seat getSelectionSeat() {
		Seat seat = null;
		if (null != composites && composites.size() > 0) {
			for (int i = 0; i < composites.size(); i++) {
				if (composites.get(i).getBackground().equals(selectColor)) {
					if (null != seatItems.get(i) && seatItems.size() > 0) {
						seat = seatItems.get(i);
					}
				}
			}
		}
		return seat;
	}

	public Composite getSelectionComp() {
		Composite composite = null;
		if (null != composites && composites.size() > 0) {
			for (int i = 0; i < composites.size(); i++) {
				if (composites.get(i).getBackground().equals(selectColor)) {
					composite = composites.get(i);
				}
			}
		}
		return composite;
	}

	public List<Seat> getSelectionSeats() {
		List<Seat> seats = new ArrayList<Seat>();
		if (null != composites && composites.size() > 0) {
			for (int i = 0; i < composites.size(); i++) {
				if (composites.get(i).getBackground().equals(selectColor)) {
					if (null != seatItems.get(i) && seatItems.size() > 0) {
						seats.add(seatItems.get(i));
					}
				}
			}
		}
		return seats;
	}
	
	public List<Composite> getSelectionComps() {
		List<Composite> comps = new ArrayList<Composite>();
		if (null != composites && composites.size() > 0) {
			for (int i = 0; i < composites.size(); i++) {
				if (composites.get(i).getBackground().equals(selectColor)) {
					comps.add(composites.get(i));
				}
			}
		}
		return comps;
	}
	
	public int getSelectionIndex() {
		int index = 0;
		if (null != composites && composites.size() > 0) {
			for (int i = 0; i < composites.size(); i++) {
				if (composites.get(i).getBackground().equals(selectColor)) {
					index = i;
				}
			}
		}
		return index;
	}

	public int[] getSelectionIndexs() {
		int[] indexs = new int[getSelectionComps().size()];
		if (null != composites && composites.size() > 0) {
			int j = 0;
			for (int i = 0; i < composites.size(); i++) {
				if (composites.get(i).getBackground().equals(selectColor)) {
					indexs[j] = i;
					j++;
				}
			}
		}
		return indexs;
	}

	public List<Seat> getDataList() {
		return seatItems;
	}

	public void addSeat(Seat seat) {
		Composite composite = createSeat(compSeat, seat);
		composite.setLayoutData(new RowData(62, 55));
		composites.add(composite);
		seatItems.add(seat);
		compSeat.layout(true);

		scrolledComposite.setContent(compSeat);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		scrolledComposite.setMinWidth(230);
		scrolledComposite.setMinHeight(((composites.size() * 60) + 20));
		// 将滚动面板的大小设置为父面板大小
		scrolledComposite.addControlListener(new ControlAdapter() {
			public void controlResized(ControlEvent e) {
				Rectangle r = scrolledComposite.getClientArea();
				scrolledComposite.setMinSize(compSeat.computeSize(r.width,
						SWT.DEFAULT));
			}
		});
	}

	public void addSeats(List<Seat> seats) {
		if (null == seatItems){
			seatItems = new ArrayList<Seat>();
		}
		for (Seat seatModel : seats) {
			Composite composite = createSeat(compSeat, seatModel);
			composite.setLayoutData(new RowData(62, 55));
			composites.add(composite);
			seatItems.add(seatModel);
		}
		compSeat.layout(true);
		scrolledComposite.setContent(compSeat);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		scrolledComposite.setMinWidth(230);
		scrolledComposite.setMinHeight(((composites.size() * 60) + 20));
		// 将滚动面板的大小设置为父面板大小
		scrolledComposite.addControlListener(new ControlAdapter() {
			public void controlResized(ControlEvent e) {
				Rectangle r = scrolledComposite.getClientArea();
				scrolledComposite.setMinSize(compSeat.computeSize(r.width,
						SWT.DEFAULT));
			}
		});
	}
	
	private void upSeat(Seat seat, int index) {
		Seat seatEnty = seatItems.get(index);
		seatEnty.setFaretypeName(seat.getFaretypeName());
		seatEnty.setSaleOrganizename(seat.getSaleOrganizename());
		seatEnty.setSeatId(seat.getSeatId());
		seatEnty.setSeatState(seat.getSeatState());
		seatEnty.setStationName(seat.getStationName());
		seatEnty.setTicketStates(seat.getTicketStates());
	}
	
	public void upSeat(Composite composite, final Seat seat, int index) {
		CLabel cLabel = (CLabel) composite.getChildren()[0];
		cLabel.dispose();
		cLabel = new CLabel(composite, SWT.NONE);
		cLabel.addMouseListener(createListener(composite));
		setSeatInfo(cLabel, seat);
		composite.layout(true);
		// 修改数据对象
		upSeat(seat, index);
	}

	public void upSeats(List<Seat> seats,int[] indexs,List<Composite> comps) {
		if (null != comps && comps.size() > 0) {
			for (int i = 0; i < comps.size(); i++) {
				final Seat seat = seats.get(i);
				Composite composite = comps.get(i);
				CLabel cLabel = (CLabel) composite.getChildren()[0];
				cLabel.dispose();
				cLabel = new CLabel(composite, SWT.NONE);
				cLabel.addMouseListener(createListener(composite));
				setSeatInfo(cLabel,seat);
				composite.layout(true);
				// 修改数据对象
				upSeat(seat, indexs[i]);
			}
		}
	}
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		Display display = Display.getDefault();
		Shell shell = new Shell();
		shell.setSize(835, 600);
		shell.setText("SWT Application");
		shell.setLayout(new FillLayout());
		List<Seat> seats = new ArrayList<Seat>();
		for (int i = 1; i < 50; i++) {
			Seat seat = new Seat();
			seat.setSeatSeq(String.valueOf(i));
			seat.setSeatId(String.valueOf(i));
			
			if (i<=20){
				seat.setSeatState(2);
				seat.setSaleType("1");
				
				if (i<=15){
					seat.setFaretypeName("全票");
					seat.setTicketStates(1);
				}else{
					seat.setFaretypeName("军残免");
					seat.setTicketStates(2);
				}
			}else if(i>=21 && i<=23){
				seat.setSeatState(1);
			}else if (i>=24 && i<=28){
				seat.setSeatState(0);
			}else if (i>=29 && i<=40){
				seat.setSeatState(3);
			}else if (i>=41 && i<=46){
				seat.setSeatState(4);
			}else{
				seat.setSeatState(1);
			}
			seat.setStationName("成都");
			seats.add(seat);
		}
		SeatBar seatBar = new SeatBar(shell, true, SWT.BORDER);
		seatBar.createSeats(seats);
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
	
	/**
	 * getStart方法描述：得到起始行
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-22 下午04:26:49
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @return int 起始行
	 */
	public int getStart() {
		int start =(this.seatPage.getPageNo() -1) * this.seatPage.getLimit();
		return start;
	}

	/**
	 * getEnd方法描述：得到结束行
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-22 下午04:27:12
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @return int 结束行
	 */
	public int getEnd() {
		int end = this.seatPage.getPageNo() * this.seatPage.getLimit();
		return end;
	}
	
	/**
	 * getPageNo方法描述：得到当前页号
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-22 下午04:31:00
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @return int 当前页号
	 */
	public int getPageNo(){
		if (seatPage != null){
			return this.seatPage.getPageNo();
		}else{
			return 0;
		}
	}

	/**
	 * getLimit方法描述：得到当前显示页行数
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-22 下午04:30:29
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @return int 显示页行数
	 */
	public int getLimit(){
		if (seatPage != null){
			return this.seatPage.getLimit();
		}else{
			return 0;
		}
	}
	
	/**
	 * setTotalCount方法描述：设置分页栏总记录数
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-22 下午03:36:35
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param totalCount 总记录数
	 */
	public void setTotalCount(int totalCount){
		if(seatPage != null){
			this.seatPage.setSubtotal(totalCount);
		}
	}
	
	public int getTotalCount(){
		if(seatPage != null){
			return this.seatPage.getSubtotal();
		}else{
			return 0;
		}
	}
	
	/**
	 * bindRefresh方法描述：绑定刷新
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-22 下午04:35:01
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param obj 对象
	 * @param funName 执行方法
	 */
	public void bindRefresh(final Object obj,final String funName){
		if (seatPage != null){
			MouseAdapter mouseAdpater = new MouseAdapter(){
				@SuppressWarnings({ "rawtypes", "unchecked" })
				public void mouseDown(MouseEvent e) {
					try {
						Class cls = obj.getClass();
						cls.getDeclaredMethod(funName).invoke(obj);
					} catch (Exception ex) {
						//throw new UserSystemException(ex.getMessage());
						LogUtil.operLog(ex,"E",true,false);
					}
				}
			};
			this.seatPage.refreshData(mouseAdpater);
		}
	}

}
