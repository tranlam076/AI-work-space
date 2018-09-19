package caroAI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;

public class caroAI extends JFrame implements MouseListener {
	
	public static void main(String[] args) {
		new caroAI();
	}
	private static final long serialVersionUID = 1L;
	int so = 3;
	int s = 100;
	int of = 50;
	
	ArrayList<Point> checked = new ArrayList<>();	
	
	public caroAI() {
		this.setTitle("Caro Game");
		this.setSize(so*s+of*2, so*s+of*2);
		this.setDefaultCloseOperation(3);
		this.addMouseListener(this);
		
		this.setVisible(true);
	}
	
	public void paint(Graphics graphic) {
		/*graphic.setColor(Color.RED);
		graphic.fillRect(50, 50, 100, 60);
		graphic.fillOval(50, 150, 100, 60);
		
		graphic.setColor(Color.GREEN);
		graphic.drawRect(50, 50, 100, 60);
		graphic.drawOval(50, 150, 100, 60);*/
	
		graphic.setColor(Color.white);
		graphic.fillRect(0, 0, this.getWidth(), this.getHeight());
		graphic.setColor(Color.green);
		
		for (int i = 0; i <= so; i++) {
			graphic.drawLine(of, of+i*s, of+so*s, of+i*s);
			graphic.drawLine(of+i*s, of, of+i*s, of+so*s);
		}
		graphic.setFont(new Font("arial", Font.BOLD, s));
		for (int i = 0; i < checked.size(); i++) {
			Color c = Color.green;
			String str = "x";
			if (i%2 == 0) {
				c = Color.red;
				str = "o";
			}
			graphic.setColor(c);
			graphic.drawString(str, of + s * checked.get(i).x + s*1/4, of + s * checked.get(i).y + s*3/4);
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {

		int x = arg0.getX();
		int y = arg0.getY();
		
		if (x < of || x >= of+so*s) return;
		if (y < of || y >= of+so*s) return;
		
		int ix = (x - of)/s;
		int iy = (y - of)/s;

		if (checked.contains(new Point(ix, iy))) return;
		
		checked.add(new Point(ix, iy));
		this.repaint();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
