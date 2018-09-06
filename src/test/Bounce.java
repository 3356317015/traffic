
package test;

import java.awt.BorderLayout;
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
 * 单线程，小球演示 搜索不到，run()方法/.start()
 */
public class Bounce {
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		JFrame frame = new BounceFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // similar to
																// window
																// listener
		frame.show();
	}
}

@SuppressWarnings("serial")
class BounceFrame extends JFrame {
	private BallCanvas canvas;

	public BounceFrame() {
		setSize(600, 500);
		setTitle("Bounce Ball");
		Container contentPane = getContentPane();
		canvas = new BallCanvas();
		contentPane.add(canvas, BorderLayout.CENTER); // add canvas to teh
														// container of frame
		JPanel buttonPanel = new JPanel();

		addButton(buttonPanel, "Start", new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				addBall(); // call method
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
		JButton button = new JButton(title);
		c.add(button); // add button to panel
		button.addActionListener(listener);
	}

	@SuppressWarnings("static-access")
	public void addBall() {
		// try{
		Ball b = new Ball(canvas);
		canvas.add(b); // add ball to canvas
		for (int i = 1; i <= 1000; i++) {
			b.move();
				try {
					Thread.currentThread().sleep(5);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}    //main()也是一个线程。一样可以用线程操控的招。
			
			// 还可以：Timer（），用定时器，每次xxx毫秒后，触发事件处理器，事件处理方法中.move();
		}
		// }
		// catch (InterruptedException e) {}
	}
}

@SuppressWarnings("serial")
class BallCanvas extends JPanel {
	@SuppressWarnings("rawtypes")
	private ArrayList balls = new ArrayList();

	@SuppressWarnings("unchecked")
	public void add(Ball b) {
		balls.add(b); // add to list
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		for (int i = 0; i < balls.size(); i++) {
			Ball b = (Ball) balls.get(i);
			b.draw(g2);
		}
	}

}

class Ball {
	private Component canvas;

	private int x = 0;

	private int y = 0;

	private int dx = 2; // 小球每次移动步长。循环----调用.move()----x=x+dx;

	private int dy = 2; // 因为是单线程，独占cpu，所以跑得快。

	public Ball(Component c) {
		canvas = c;
	}

	public void draw(Graphics2D g2) {
		g2.fill(new Ellipse2D.Double(x, y, 15, 15));
	}

	public void move() {
		x += dx;
		y += dy;
		if (x < 0) {
			x = 0;
			dx = -dx;
		}
		if (x + 15 >= canvas.getWidth()) {
			x = canvas.getWidth() - 15;
			dx = -dx;
		}
		if (y < 0) {
			y = 0;
			dy = -dy;
		}
		if (y + 15 >= canvas.getHeight()) {
			y = canvas.getHeight() - 15;
			dy = -dy;
		}
		canvas.paint(canvas.getGraphics());
	}

}
