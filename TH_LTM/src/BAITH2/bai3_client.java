package BAITH2;
import java.net.*;
import java.io.*;
import java.util.*;
import java.awt.*;

class bai3_client extends Frame implements Runnable
{
    DatagramSocket soc;    
    TextField tf;
    TextArea ta;
    Button btnSend,btnClose;
    
    String LoginName;
    Thread t=null;
    DatagramPacket send;
    DatagramPacket receive;
    private byte[] senddata;
    private byte[] receivedata;
    InetAddress inet;
    bai3_client(String LoginName) throws Exception
    {
        super(LoginName);
        this.LoginName=LoginName;
        senddata=new byte[LoginName.length()];
        tf=new TextField(50);
        ta=new TextArea(50,50);
        btnSend=new Button("Send");
        btnClose=new Button("Close");
        soc=new DatagramSocket();
        
        inet=InetAddress.getByName("localhost");
        senddata=LoginName.getBytes();
        send=new DatagramPacket(senddata, senddata.length, inet, 8899);
        soc.send(send);
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
            	senddata=(LoginName+": "+ tf.getText().toString()).getBytes();
            	send=new DatagramPacket(senddata, senddata.length, inet, 8899);
            	soc.send(send);
                //dout.writeUTF(LoginName+": "+ tf.getText().toString());            
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
                senddata=(LoginName + " LOGOUT").getBytes();
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
    	Scanner sc=new Scanner(System.in);
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
                
                receivedata=new byte[1024];
                receive=new DatagramPacket(receivedata, receivedata.length);
                soc.receive(receive);
                ta.append( "\n" +new String(receive.getData()));
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
        }
    }
}
