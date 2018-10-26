package KMeans;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class XyLyAnh extends JFrame {
	public static void main(String[] args) {
		new XyLyAnh();
	}
	BufferedImage img = null;

	public XyLyAnh () {
		try {
			img = ImageIO.read(new File("C:\\Users\\tranl\\Desktop\\test.jpg"));
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		List<int []> ld = new ArrayList<>();
		
		int[][] data = new int [img.getWidth()*img.getHeight()/25][];
		int id = 0;
		for (int y = 0; y < img.getHeight(); y++) {
			for (int x = 0; x < img.getWidth(); x++) {
				int color = img.getRGB(x, y);
				int b = color & 0xff;
				int g = (color >> 8) & 0xff;
				int r = (color >> 16) & 0xff;
				
//				data[id]= new int [] {r,g,b};
				ld.add(new int [] {r,g,b});
				id ++;
			}
		}
		
		
		k_means km = new k_means(data, 8);
		int i = 0;
		for (int y = 0; y < img.getHeight(); y+=5) {
			for (int x = 0; x < img.getWidth(); x+=5) {
				int color = img.getRGB(x, y);
				int b = color & 0xff;
				int g = (color >> 8) & 0xff;
				int r = (color >> 16) & 0xff;
				
				
				int index = km.Chia(new int[]{r,g,b});
				int nr = km.c[km.id[i]][0];
				int ng = km.c[km.id[i]][1];
				int nb = km.c[km.id[i]][2];
				int ncolor = nb+(ng<<8) + (nr<<16);
				img.setRGB(x, y, ncolor);
				i ++;
			}
		}
		
		
		
		this.setTitle("Xy Ly Anh");
		this.setSize(img.getWidth(), img.getHeight());
		
		this.setDefaultCloseOperation(3);
		this.setVisible(true);
	}
	
	public void paint (Graphics g) {
		g.setColor(Color.white);
		g.drawImage(img, 0, 0, null );
	}
}
