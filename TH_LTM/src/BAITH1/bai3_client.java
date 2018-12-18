package BAITH1;
import java.net.*;
import java.io.*;
import java.util.*;
import java.awt.*;

class bai3_client extends Frame implements Runnable
{
    private static final long serialVersionUID = 1L;
	Socket soc;    
    TextField tf;
    TextArea ta;
    Button btnSend,btnClose;
    
    String LoginName;
    Thread t=null;
    DataOutputStream dout;
    DataInputStream din;
	private static Scanner sc;
    bai3_client(String LoginName) throws Exception
    {
        super(LoginName);
        this.LoginName=LoginName;
        tf=new TextField(50);
        ta=new TextArea(50,50);
        btnSend=new Button("Send");
        btnClose=new Button("Close");
        soc=new Socket("127.0.0.1",5217);

        din=new DataInputStream(soc.getInputStream()); 
        dout=new DataOutputStream(soc.getOutputStream());        
        dout.writeUTF(LoginName);

        t=new Thread(this);
        t.start();

    }
    void setup()
    {
        setSize(600,400);
        setLayout(new GridLayout(2,1));

        add(ta);
        Panel p=new Panel();
        
        p.add(tf);
        p.add(btnSend);
        p.add(btnClose);
        add(p);
        show();        
    }
    public boolean action(Event e,Object o)
    {
        if(e.arg.equals("Send"))
        {
            try
            {
                dout.writeUTF(LoginName+": "+ tf.getText().toString());            
                ta.append("\n" + LoginName +":"+ tf.getText().toString());    
                tf.setText("");
            }
            catch(Exception ex)
            {
            }    
        }
        else if(e.arg.equals("Close"))
        {
            try
            {
                dout.writeUTF(LoginName + " LOGOUT");
                System.exit(1);
            }
            catch(Exception ex)
            {
            }
            
        }
        
        return super.action(e,o);
    }
    public static void main(String args[]) throws Exception
    {
    	sc = new Scanner(System.in);
    	System.out.print("Nhập Tên Login:");
    	String LoginName=sc.nextLine();
    	
    	bai3_client Client1=new bai3_client(LoginName);
        Client1.setup();                
    }    
    public void run()
    {        
        while(true)
        {
            try
            {
                ta.append( "\n" +din.readUTF());
                
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
        }
    }
}

