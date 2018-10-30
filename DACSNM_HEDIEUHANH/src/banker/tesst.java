package banker;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

import javax.swing.JFrame;

public class tesst extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Image img;
	Ball b[];
	Graphics gg;
	
	public tesst() {
		this.setTitle("Ball");
		this.setSize(600, 400);
		this.setDefaultCloseOperation(3);
		
		b = new Ball[1000];
		for (int i = 0; i < b.length; i++) {
			b[i] = new Ball();
			b[i].start();
		}
		
		img = this.createImage(this.getWidth(), this.getHeight());
		gg = img.getGraphics();
		
		this.setVisible(true);
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		for (int i = 0; i < b.length; i++) {
			g.setColor(Color.blue);
			g.fillOval(b[i].x - b[i].r, b[i].y - b[i].r, 2*b[i].r, 2*b[i].r);
			g.setColor(Color.red);
			g.drawOval(b[i].x - b[i].r, b[i].y - b[i].r, 2*b[i].r, 2*b[i].r);
		}
		
		
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		this.repaint();

	}
	
	public static void main(String[] args) {		
		new tesst();
	}

}

class Ball extends Thread {
	Random rd = new Random();
	int x, y, dx, dy, r;

	public Ball() {
		x = rd.nextInt(600) + 100;
		y = rd.nextInt(400) + 100;
		r = rd.nextInt(20) + 20;
		dx = rd.nextInt(10) - 5;
		dy = rd.nextInt(10) - 5;
	}

	public void run() {
		while(true) {
			x += dx;
			y += dy;
			if (x < r || x + r > 600)
				dx *= -1;
			if (y < r || y + r > 400)
				dy *= -1;
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}

	}
}


