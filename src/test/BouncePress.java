
package test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 多线程，小球演示. 打开Windows任务管理器，可看到线程变化。 可搜索到，run()方法/.start()
 * 
 * du: 程序技巧体会： 所谓产生一个小球，即是 new 其类对象，其属性携带画小球的 坐标、颜色、所在容器 等参数。
 * 
 * 一个类，属性用来作为参数容器用， 方法....完成功能。
 * */
// 运行类
public class BouncePress {
	//
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		JFrame frame = new BouncePressFrame(); // 生成窗口。执行构造。-----业务逻辑。
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // similar to
																// window
																// listener
		frame.show();
	}
}

@SuppressWarnings("serial")
class BouncePressFrame extends JFrame {
	private BallPressCanvas canvas;

	public BouncePressFrame() {
		setSize(600, 500); // 窗口大小
		setTitle("Bounce Ball");
		Container contentPane = getContentPane(); // Swing的窗口不能直接放入东西，只能在其上的ContentPane上放。
		canvas = new BallPressCanvas(); // 生成一个新面板。-----canvas
		contentPane.add(canvas, BorderLayout.CENTER); // 窗口中心 加入该面板。
		JPanel buttonPanel = new JPanel(); // 再生成一个新面板。----buttonPanel

		// 调用本类方法addButton。
		addButton(buttonPanel, "Start", // 生成一个按钮"Start"---加入面板buttonPanel
				new ActionListener() { // |------>按钮绑上 action监听器。
					public void actionPerformed(ActionEvent evt) { // | 小球容器对象的
						addBall(Thread.NORM_PRIORITY - 4, Color.black); // 事件处理时，执行---addBall()方法。--->产生小球(参数对象)--->加入List中--->开始画球。
					}
				}); // 按一次，addBall()一次--->产生一个新小球--->加入List中--->开始画此新小球。
					// --->画球线程BallPressThread的run()--->小球(参数对象).move()--->每次画时，先移动，再判断，再画。
					// --->BallPressCanvas类的canvas对象.paint()--->自动调BallPressCanvas类的paintComponent(Graphics
					// g)方法。
					// --->该方法，从List中循环取出所有小球，第i个球，--->调该小球BallPress类
					// .draw()方法--->调Graphics2D方法画出小球。--使用color/
		addButton(buttonPanel, "Express", new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				addBall(Thread.NORM_PRIORITY + 2, Color.red);
			}
		});

		addButton(buttonPanel, "Close", new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				System.exit(0);
			}
		});

		contentPane.add(buttonPanel, BorderLayout.SOUTH);
	}

	public void addButton(Container c, String title, ActionListener listener) {
		JButton button = new JButton(title); // 生成一个按钮。
		c.add(button); // 加入容器中。
		button.addActionListener(listener); // 按钮绑上 action监听器。
	}

	/** 主要业务方法。 */
	public void addBall(int priority, Color color) {
		// 生成 小球(参数对象)
		BallPress b = new BallPress(canvas, color); // 生成BallPress对象，携带、初始化
													// 画Ball形小球，所需参数：所在容器组件，所需color--black/red.
		// 小球加入 List中。
		canvas.add(b); // 面板canvas 的ArrayList中 加入BallPress对象。

		BallPressThread thread = new BallPressThread(b); // 生成画小球的线程类BallPressThread对象。传入BallPress对象(携带了画球所需
															// 容器、color参数)。
		thread.setPriority(priority);
		thread.start(); // call run(), ball start to move
		// 画球线程开始。--->BallPressThread的run()--->小球(参数对象).move()--->先移动，再画。canvas.paint--->BallPressCanvas类的
	}
}

// 画球的线程类。
class BallPressThread extends Thread {
	private BallPress b;

	public BallPressThread(BallPress aBall) {
		b = aBall;
	}

	// 画球开始。
	public void run() {
		try {
			for (int i = 1; i <= 1000; i++) { // 画1000次。
				b.move(); // 每次画时，先移动，再判断，再画。
				sleep(5); // 所以移动比Bounce.java的球慢。
			}
		} catch (InterruptedException e) {
		}
	}
}

// swing面板类.
// 作用1） 本类面板对象.paint()方法---->自动绘制面板，且自动调paintComponent(Graphics
// g)方法，--->重写该方法，绘制面板(及其上组件)。
// 作用2） 该类对象 属性ArrayList balls---兼作小球(参数对象)的容器。
@SuppressWarnings("serial")
class BallPressCanvas extends JPanel {
	@SuppressWarnings("rawtypes")
	private ArrayList balls = new ArrayList();

	@SuppressWarnings("unchecked")
	public void add(BallPress b) {
		balls.add(b); // 向ArrayList中添加球。当按下按钮，添加多个球时，都保存在这个List中。
	}

	// 重写了 javax.swing.JComponent的 paintComponent()方法。
	// paint()方法自动调用该方法。
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		for (int i = 0; i < balls.size(); i++) { // 循环
			BallPress b = (BallPress) balls.get(i); // 从List中取出第i个球，
			b.draw(g2); // 画此球。
		}
	}
}

/**
 * 画出球。
 * 
 * 在 canvas上画出，color色的小球图形。
 * 
 * 属性，可用于携带画小球所需参数。
 * 
 * 
 * 
 * @author ducongan
 * 
 */
class BallPress {
	private Component canvas;
	private Color color;
	private int x = 0;
	private int y = 0;
	private int dx = 2;
	private int dy = 2;

	// 构造 初始化 容器 颜色 参数。
	public BallPress(Component c, Color aColor) {
		canvas = c;
		color = aColor;
	}

	// 制定位置，画出小球。
	public void draw(Graphics2D g2) {
		g2.setColor(color);
		g2.fill(new Ellipse2D.Double(x, y, 15, 15)); // ellipse:椭圆形
	}

	// 移动小球。
	// 每次画时，先移动，再判断，再画。
	// 该方法每次执行，画小球的起点坐标 (x,y), 每次各自+2, 即斜向右下运动。
	public void move() {
		x += dx; // x=x+dx; 画小球的起点坐标 (x,y), 每次各自+2, 即斜向右下运动。
		y += dy; // y=y+dy;
		if (x < 0) { // 小球已到左边框。保证，从左边框开始画。
			x = 0;
			dx = -dx; // 小球横坐标变化值取反。开始反向运动。
		}
		if (x + 15 >= canvas.getWidth()) { // 小球右边已经到画板右边。
			x = canvas.getWidth() - 15;
			dx = -dx; // 开始反向运动。
		}
		if (y < 0) { // 保证，从顶框开始画。
			y = 0;
			dy = -dy;
		}
		if (y + 15 >= canvas.getHeight()) { // 小球已到画板顶。
			y = canvas.getHeight() - 15;
			dy = -dy;
		}
		canvas.paint(canvas.getGraphics()); // 画出面板对象canvas----（及其上所有组件）
											// //.paint()方法，自动调用
	}
}

