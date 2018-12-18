package UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class test extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	JFrame jF = new JFrame();
	JPanel jp;
	String hello = "hello";
	static JList<Object> jListAccessFile;
	static JList<Object> jListLogs;
	int startPanel = 60;
	String lastedDirPath = "D:\\";

	public test() {
		jF.setTitle("TFTP server");
		jF.setSize(1000, 700);
		jF.setDefaultCloseOperation(EXIT_ON_CLOSE);

		JButton btnNewButton = new JButton("Chose File");
		btnNewButton.addActionListener(this);
		btnNewButton.setActionCommand("btnNewButton");
		btnNewButton.setBounds(430, startPanel, 116, 31);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		jF.add(btnNewButton);

//		JButton btnNewButton2 = new JButton("check2");
//		btnNewButton2.addActionListener(this);
//		btnNewButton2.setActionCommand("btnNewButton2");
//		btnNewButton2.setBounds(799, 55, 116, 31);
//		btnNewButton2.setFont(new Font("Tahoma", Font.BOLD, 15));
//		jF.add(btnNewButton2);

		jListAccessFile = new JList<>();
		jListAccessFile.setModel(new DefaultListModel<Object>());
		jListAccessFile.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("unchecked")
			public void mouseClicked(MouseEvent evt) {
				JList<?> list = (JList<?>) evt.getSource();
				if (evt.getClickCount() == 2) {
					int index = list.locationToIndex(evt.getPoint());
					System.out.println(index);
					DefaultListModel<Object> model = (DefaultListModel<Object>) list.getModel();
					DefaultListModel<Object> dlm = model;
					dlm.removeElementAt(index);
				}
			}
		});
		JScrollPane jsAccess = new JScrollPane(jListAccessFile);
		jsAccess.setBounds(20, startPanel, 400, 510);
		jF.add(jsAccess);
		
		
		jListLogs = new JList<>();
		jListLogs.setModel(new DefaultListModel<Object>());
		jListLogs.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("unchecked")
			public void mouseClicked(MouseEvent evt) {
				JList<?> list = (JList<?>) evt.getSource();
				if (evt.getClickCount() == 2) {
					int index = list.locationToIndex(evt.getPoint());
					System.out.println(index);
					DefaultListModel<Object> model = (DefaultListModel<Object>) list.getModel();
					DefaultListModel<Object> dlm = model;
					dlm.removeElementAt(index);
				}
			}
		});
		JScrollPane jsLog = new JScrollPane(jListLogs);
		jsLog.setBounds(550, startPanel, 400, 510);
		jF.add(jsLog);

		jp = new GPanel();
		jF.add(jp);
		jF.setVisible(true);
	}

	public static void main(String[] args) {
		new test();
	}

	class GPanel extends JPanel {
		private static final long serialVersionUID = 1L;
		Image img;
		Graphics gg;

		public GPanel() {
			jF.setPreferredSize(new Dimension(1000, 700));
		}

		@Override
		public void paint(Graphics graphic) {
			img = this.createImage(this.getWidth(), this.getHeight());
			System.out.println(this.getHeight());
			System.out.println(this.getWidth());
			gg = img.getGraphics();
//			gg.drawRect(550, 450, 400, 75);
//			gg.setColor(Color.WHITE);
//			gg.fillRect(551, 451, 399, 74);
			gg.setColor(Color.BLACK);
//			gg.fillRoundRect(420, 40, 100, 100, 80, 80);
			gg.setFont(new Font("TimesRoman", Font.BOLD, 15));
			gg.drawString("List accessible files", 20, startPanel - 5);
			gg.drawString("List transferred file ", 550, startPanel - 5);
			graphic.drawImage(img, 0, 0, null);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		DefaultListModel<Object> model = (DefaultListModel<Object>) jListAccessFile.getModel();
		DefaultListModel<Object> dlm = model;

		System.out.println(e.getActionCommand());
		if (e.getActionCommand().equals("btnNewButton")) {
			String filePath = browseFolder();
			System.out.println(filePath);
			if (filePath.equals("")) {
				return;
			}
			String fileName = filePath.substring(filePath.lastIndexOf("\\") + 1, filePath.length());
			dlm.addElement((Object) " " + fileName + " (" + filePath + ")");
			jListAccessFile.ensureIndexIsVisible(jListAccessFile.getModel().getSize() - 1);
		}
//			dlm.clear();
	}

	private String browseFolder() {
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File(lastedDirPath));
		int r = chooser.showOpenDialog(this);
		if (r == JFileChooser.APPROVE_OPTION) {
			String fileName = chooser.getSelectedFile().getPath();
			System.out.println(fileName);
			lastedDirPath = fileName.substring(0, fileName.lastIndexOf("\\"));
			return fileName;
		}
		return "";
	}

}