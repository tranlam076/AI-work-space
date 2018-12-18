package BAITH2;
import java.net.*;
import java.util.*;
import java.io.*;

class bai3_server
{
    static Vector portclient;
    static Vector LoginNames;
   
    String LoginName;
    
    bai3_server() throws Exception
    {
    	byte[] sendata;
        byte[] receivedata;
        DatagramPacket send;
        DatagramPacket receive;
        portclient=new Vector();
        LoginNames=new Vector();
        DatagramSocket soc;
        soc=new DatagramSocket(8899);
        while(true)
        {   
            
        	receivedata=new byte[1024];
            receive=new DatagramPacket(receivedata, receivedata.length);
            soc.receive(receive);
           
            LoginName=new String(receive.getData());
            InetAddress inet=receive.getAddress();
            int port=receive.getPort();
            int i=0;
            for(;i<portclient.size();i++)
            {
            	object ob= portclient.elementAt(i);
            	if(inet==ob.getInet()&&port==ob.getPort())
            	{
            		break;
            	}
            }
            if(i==portclient.size())
            { AcceptClient obClient=new AcceptClient(inet,port,soc);}
        }
    }
    public static void main(String args[]) throws Exception
    {
        
    	bai3_server ob=new bai3_server();
    }

class AcceptClient extends Thread
{
    InetAddress inet;
    String msgFromClient;
    byte[] sendata1;
    byte[] receivedata1;
    DatagramPacket send1;
    DatagramPacket receive1;
    private InetAddress inet2;
    private int port;
    private DatagramSocket soc;
    AcceptClient (InetAddress inet,int port,DatagramSocket soc) throws Exception
    {
    	sendata1=new byte[1024];
    	receivedata1=new byte[1024];
    	this.soc=soc;
        this.inet=inet;
        this.port=port;
        System.out.println("User Logged In :" + LoginName);
        LoginNames.add(LoginName);
        object dt=new object(port, inet);
        portclient.add(dt);    
        start();
    }

    public void run()
    {
        while(true)
        {
            
            try
            {
            	System.out.println("cai lon gi the");
            	receive1=new DatagramPacket(receivedata1, receivedata1.length);
                soc.receive(receive1);
                msgFromClient=new String(receive1.getData());
                StringTokenizer st=new StringTokenizer(msgFromClient);
                String MsgType="";
                if(st.hasMoreTokens())
                { MsgType=st.nextToken();}
                int iCount=0;
                if(MsgType.equals("LOGOUT"))
                {
                    for(iCount=0;iCount<LoginNames.size();iCount++)
                    {
                        if(LoginNames.elementAt(iCount).equals(LoginName))
                        {
                            LoginNames.removeElementAt(iCount);
                            portclient.removeElementAt(iCount);
                            System.out.println("User " + LoginName +" Logged Out ...");
                            break;
                        }
                    }
    
                }
                else
                {
                    String msg="";
                    while(st.hasMoreTokens())
                    {
                        msg=msg+" "+st.nextToken();
                    }
                    for(iCount=0;iCount<LoginNames.size();iCount++)
                    {
                        if(!LoginNames.elementAt(iCount).equals(LoginName))
                        {    
                           object ob=(object) portclient.elementAt(iCount);
                           InetAddress inet=ob.getInet();
                           int port=ob.getPort();
                            sendata1=(LoginName+":"+msg).getBytes();  
                            System.out.println("tau goi roi do");
                            DatagramPacket goi=new DatagramPacket(sendata1,sendata1.length,inet,port);
                            soc.send(goi);                           
                            break;
                        }
                    }
                }
                if(MsgType.equals("LOGOUT"))
                {
                    break;
                }

            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
        }        
    }
}
}
