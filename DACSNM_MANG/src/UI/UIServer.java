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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class UIServer extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	JFrame jF = new JFrame();
	JPanel jpGraphic;
	String hello = "hello";
	static JList<Object> jListAccessFile;
	static JList<Object> jListLogs;
	int startPanel = 60;
	int endPanel = 600;
	String lastedDirPath = "C:\\Users\\tranl\\Desktop\\DACSNM\\server";
	HashMap <String, String> listAccessibleFile = new HashMap<>();
	JLabel readingLabel;
	JLabel writingLabel;
	JTextField textField;

	public UIServer() {
		jF.setTitle("TFTP server");
		jF.setSize(1000, 700);
		jF.setDefaultCloseOperation(EXIT_ON_CLOSE);

		JButton btnNewButton = new JButton("Chose File");
		btnNewButton.addActionListener(this);
		btnNewButton.setActionCommand("btnNewButton");
		btnNewButton.setBounds(430, startPanel, 116, 31);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		jF.add(btnNewButton);
		
		jListAccessFile = new JList<>();
		jListAccessFile.setModel(new DefaultListModel<Object>());
		jListAccessFile.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("unchecked")
			public void mouseClicked(MouseEvent evt) {
				JList<?> list = (JList<?>) evt.getSource();
				DefaultListModel<Object> model = (DefaultListModel<Object>) list.getModel();
				DefaultListModel<Object> dlm = model;
				if (evt.getClickCount() == 1) {
					int index = list.locationToIndex(evt.getPoint());
					String item = dlm.getElementAt(index).toString();
					String filePath = item.split("-")[1];
					textField.setText(filePath.split(" ")[1]);
				}
				if (evt.getClickCount() == 2) {
					int index = list.locationToIndex(evt.getPoint());
					String item = dlm.getElementAt(index).toString();
					String fileName = item.trim().split("-")[0];
					listAccessibleFile.remove(fileName);
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
					DefaultListModel<Object> model = (DefaultListModel<Object>) list.getModel();
					DefaultListModel<Object> dlm = model;
					dlm.removeElementAt(index);
				}
			}
		});
		JScrollPane jsLog = new JScrollPane(jListLogs);
		jsLog.setBounds(560, startPanel, 400, 510);
		jF.add(jsLog);
		
		readingLabel  = new JLabel("");
		readingLabel.setBounds(430, startPanel + 50, 116, 31);
		readingLabel.setFont(new Font(readingLabel.getName(), Font.PLAIN, 14));
		jF.add(readingLabel);
		
		writingLabel  = new JLabel("");
		writingLabel.setBounds(430, startPanel + 120, 116, 31);
		writingLabel.setFont(new Font(writingLabel.getName(), Font.PLAIN, 14));
		jF.add(writingLabel);
		
		
		textField = new JTextField();
		textField.setFont(new Font(writingLabel.getName(), Font.PLAIN, 14));
		textField.setBounds(150, endPanel, 700, 40);
		jF.add(textField);

		
		jpGraphic = new GPanel();
		jF.add(jpGraphic);
		jF.setVisible(true);
	}

	public static void main(String[] args) {
		new UIServer();
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
			gg = img.getGraphics();
			gg.setColor(Color.BLACK);
			gg.setFont(new Font("TimesRoman", Font.BOLD, 15));
			gg.drawString("List accessible files", 20, startPanel - 5);
			gg.drawString("List transferred file ", 560, startPanel - 5);
			gg.setFont(new Font("TimesRoman", Font.PLAIN, 15));
			gg.drawString("File path: ", 60, endPanel + 30);
			graphic.drawImage(img, 0, 0, null);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		DefaultListModel<Object> model = (DefaultListModel<Object>) jListAccessFile.getModel();
		DefaultListModel<Object> dlm = model;
		if (e.getActionCommand().equals("btnNewButton")) {
			String filePath = browseFolder();
			if (filePath.equals("")) {
				return;
			}
			String fileName = filePath.substring(filePath.lastIndexOf("\\") + 1, filePath.length());
			File file = new File(filePath);
			long fileSizeInBytes = file.length();
			long fileSizeInKB = fileSizeInBytes / 1024;
			long fileSizeInMB = fileSizeInKB / 1024;
			long fileSize = (fileSizeInMB > 0) ? fileSizeInMB : (fileSizeInKB > 0) ? fileSizeInKB : fileSizeInBytes;
			String ex = (fileSizeInMB > 0) ? "MB" : (fileSizeInKB > 0) ? "KB" : "B";
			String size = " (" + fileSize + " " + ex + ")";
			dlm.addElement((Object) " " + fileName + " - " + filePath + size);
			if (listAccessibleFile.get(fileName) == null) {
				listAccessibleFile.put(fileName, filePath);
			}
			jListAccessFile.ensureIndexIsVisible(jListAccessFile.getModel().getSize() - 1);
		}
		
		if (e.getActionCommand().equals("btnNewButton2")) {
			System.out.println("on check");
			updatePercent("tszljnssdknsknfdndk", 10);
			updateLogger("WRQ", "abc", "xyz", "!@#");
		}
	}

	private String browseFolder() {
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File(lastedDirPath));
		int r = chooser.showOpenDialog(this);
		if (r == JFileChooser.APPROVE_OPTION) {
			String fileName = chooser.getSelectedFile().getPath();
			lastedDirPath = fileName.substring(0, fileName.lastIndexOf("\\"));
			return fileName;
		}
		return "";
	}
	
	public String getFilePath (String fileName) {
		return listAccessibleFile.get(fileName);
	}
	
	public void updatePercent (String fileName, int percent) {
		if (fileName == null) {
			readingLabel.setText("");
			return;
		}
		readingLabel.setText(percent + "%" + " - " + fileName);
	}
	
	public void updateWritingFile (String fileName) {
		if (fileName == null) {
			writingLabel.setText("");
			return;
		}
		writingLabel.setText("Writing " + fileName);
	}
	
	public void updateLogger (String type, String IP, String fileName, String fileDir) {
		String filePath = fileDir + fileName;
		if (type.equals("WRQ")) {
			DefaultListModel<Object> model = (DefaultListModel<Object>) jListAccessFile.getModel();
			DefaultListModel<Object> dlm = model;
			File file = new File(filePath);
			long fileSizeInBytes = file.length();
			long fileSizeInKB = fileSizeInBytes / 1024;
			long fileSizeInMB = fileSizeInKB / 1024;
			long fileSize = (fileSizeInMB > 0) ? fileSizeInMB : (fileSizeInKB > 0) ? fileSizeInKB : fileSizeInBytes;
			String ex = (fileSizeInMB > 0) ? "MB" : (fileSizeInKB > 0) ? "KB" : "B";
			String size = " (" + fileSize + " " + ex + ")";
			dlm.addElement((Object) fileName + " - " + filePath + size);
			if (listAccessibleFile.get(fileName) == null) {
				listAccessibleFile.put(fileName, filePath);
			}
			jListAccessFile.ensureIndexIsVisible(jListAccessFile.getModel().getSize() - 1);
		}
			DefaultListModel<Object> model = (DefaultListModel<Object>) jListLogs.getModel();
			DefaultListModel<Object> dlm = model;
			String timeStamp = new SimpleDateFormat("yyyy/MM/dd - HH:mm:ss").format(Calendar.getInstance().getTime());
			dlm.addElement((Object) " " + type + " - " + IP.substring(1, IP.length()) + " - " + fileName + " (" + timeStamp + ")");
			jListLogs.ensureIndexIsVisible(jListLogs.getModel().getSize() - 1);
		}
	
}