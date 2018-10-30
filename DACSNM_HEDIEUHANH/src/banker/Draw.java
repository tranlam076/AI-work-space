package banker;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

public class Draw extends JFrame implements Runnable, MouseListener {

	private static final long serialVersionUID = 1L;
	Image img;
//	Graphics gg;
	int X = 1000;
	int Y = 700;
	int size = 10;
	int offset = 40;
	int mapSizeX = (X - offset * 2) / size;
	int mapSizeY = (Y - offset * 2) / size;

//	create pannel
	int divX = 170; // 46
	int divY = 209; // 75

	public Draw() {
		this.setTitle("Banker Algorithm");
		this.setSize(X, Y);
		this.setDefaultCloseOperation(3);
		this.addMouseListener(this);
		this.setVisible(true);
//		img = this.createImage(1000, 700);
//		gg=img.getGraphics();
	}

	public void paint(Graphics g) { 
		g.setColor(Color.white);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(Color.black);
		g.drawRect(offset, offset, mapSizeX * size, mapSizeY * size);
//		for (int i = 0; i <= mapSizeY; i++) {
//			g.drawLine(offset, offset + i * size, offset + mapSizeX * size , offset + i * size);
//		}
//		for (int i = 0; i <= mapSizeX; i++) {
//			g.drawLine(offset + i * size, offset, offset + i * size, offset + mapSizeY * size );
//		}

//		draw x
		g.drawLine(offset, offset + divY / 4, offset + mapSizeX * size, offset + divY / 4);
//		draw y
		g.drawLine(offset + divX / 2, offset, offset + divX / 2, offset + mapSizeY * size);
		for (int i = divX/2 + divX; i < mapSizeX * size; i += divX) {
			g.drawLine(offset + i, offset, offset + i, offset + mapSizeY * size);
		}
//		g.drawImage(img, 0, 0, null);
	}

	public static void main(String[] args) {
		new Draw();
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
			this.repaint();
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

}