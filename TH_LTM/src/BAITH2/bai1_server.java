package BAITH2;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
public class bai1_server{
   public static void main(String args[])
   {
	   byte senddata[];
	   byte receivedata[]=new byte[1024];
	   try
	   {
		   @SuppressWarnings("resource")
		   DatagramSocket server=new DatagramSocket(8892);

		   DatagramPacket receive=new DatagramPacket(receivedata, receivedata.length);
		   server.receive(receive);
		   InetAddress inet=receive.getAddress();
		   int port=receive.getPort();
		   String nhan=new String(receive.getData());
		   senddata=new byte[nhan.length()];
		   String chuoihoa=nhan.toUpperCase();
		   senddata=chuoihoa.getBytes();
		   DatagramPacket send=new DatagramPacket(senddata, senddata.length, inet, port);
		   server.send(send);
		   String chuoithuong=nhan.toLowerCase();
		   senddata=chuoithuong.getBytes();
		   send=new DatagramPacket(senddata, senddata.length, inet, port);
		   server.send(send);
		   String s="";
		   char b;
		   for(int i=0;i<nhan.length();i++)
		   {
			   if('a'<=nhan.charAt(i)&&nhan.charAt(i)<='z')
				     {
				     b=(char) (nhan.charAt(i)-32);
				     s+=b;
				     }
			   else if('A'<=nhan.charAt(i)&&nhan.charAt(i)<='Z')
			   {
				   b=(char) (nhan.charAt(i)+32);
				     s+=b;
			   }
			   else s+=nhan.charAt(i);
		   }
		   
		   senddata=s.getBytes();
		   send=new DatagramPacket(senddata, senddata.length, inet, port);
		   server.send(send);
	   }catch(Exception e)
	   {
		   
	   }
   }

}