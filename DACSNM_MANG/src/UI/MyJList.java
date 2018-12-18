package UI;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

class MyJList extends JPanel {
	  JList list;

	  public MyJList() {
	    setLayout(new BorderLayout());
	    list = new JList();
	    add(new JScrollPane(list));
	    }

	  public Dimension getPreferredSize() {
	    return new Dimension(150, 250);
	    }
	}
