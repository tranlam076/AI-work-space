

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class test extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	JFrame jF = new JFrame();
	JPanel jp;
	String hello = "hello";

	public test() {
		jF.setTitle("Simple Drawing");
		jF.setSize(1000, 600);
		jF.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JButton btnNewButton = new JButton("check");
		btnNewButton.addActionListener(this);
		btnNewButton.setActionCommand("btnNewButton");
		btnNewButton.setBounds(799, 15, 116, 31);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		jF.add(btnNewButton);
		
		jp = new GPanel();
		jF.add(jp);
		jF.setVisible(true);
	}

	public static void main(String[] args) {
		new test();
	}

	class GPanel extends JPanel implements MouseListener {
		private static final long serialVersionUID = 1L;
		Image img;
		Graphics gg;
		
		public GPanel() {
			jF.setPreferredSize(new Dimension(1000, 600));
			this.addMouseListener(this);
		}
		
		@Override
		public void paint(Graphics graphic) {	
			img = this.createImage(this.getWidth(), this.getHeight());
			System.out.println(this.getHeight());
			System.out.println(this.getWidth());
			gg = img.getGraphics();
			gg.drawRect(10, 10, 240, 240);
			gg.setColor(Color.WHITE);
			gg.fillRect(11, 11, 239, 239);
			gg.setColor(Color.GREEN);
			gg.fillRoundRect(100, 100, 100, 100, 80, 80);
			gg.drawString(hello, 10, 10);
			graphic.drawImage(img, 0, 0, null);
		}

		@Override
		public void mouseClicked(java.awt.event.MouseEvent e) {
			System.out.println(e.getX() + " - " + e.getY());
			System.out.println("on mouse clicked");
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

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getActionCommand());
	}
}