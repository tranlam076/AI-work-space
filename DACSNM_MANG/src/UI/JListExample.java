package UI;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

public class JListExample extends JPanel {
  static MyJList mj;  
  public static void main(String s[]) {
    JListExample ex = new JListExample();
    JFrame frame = new JFrame("JList Scrolling Display");
    JButton button = new JButton("Insert");
    ex.mj = new MyJList();
    ex.mj.list.setModel (new DefaultListModel());

    frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    frame.getContentPane().setLayout(new FlowLayout());
    frame.getContentPane().add(ex.mj);
    frame.getContentPane().add(button);
    button.addActionListener( 
      new ActionListener() {
        public void actionPerformed(ActionEvent ae) {
            DefaultListModel dlm = 
               (DefaultListModel)JListExample.mj.list.getModel();
            dlm.addElement
               ((Object) new Long(System.currentTimeMillis()));
            JListExample.mj.list.ensureIndexIsVisible
               (JListExample.mj.list.getModel().getSize() - 1);
            dlm.clear();
            }
       
        });
 
   frame.setSize(300, 300);
   frame.setVisible(true);
    }
}
