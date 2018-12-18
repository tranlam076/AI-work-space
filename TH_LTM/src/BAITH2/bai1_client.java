package BAITH2;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class bai1_client {
    private static Scanner sc;
	private static DatagramSocket moi;

	public static void main(String args[])
    {
    	   sc = new Scanner(System.in);
    	   byte senddata[];
    	   byte receivedata[];
    	  try {
    		  InetAddress inet=InetAddress.getByName("localhost");
			  moi = new DatagramSocket();
			  System.out.println("moi ban nhap vao chuoi muon gui:");
			  String goi=sc.nextLine();
			  senddata=new byte[goi.length()];
			  receivedata=new byte[goi.length()];
			  senddata=goi.getBytes();
			 DatagramPacket send=new DatagramPacket(senddata, senddata.length,inet,8892);
			 moi.send(send);
			 for(int i=0;i<3;i++)
			 {
		     DatagramPacket receive=new DatagramPacket(receivedata, receivedata.length);
			 moi.receive(receive);
			 String nhan=new String(receive.getData());
			 if(i==0)System.out.println("chuoi hoa server gui:"+nhan);
			 else if(i==1)System.out.println("chuoi thuong server gui:"+nhan);
			 else System.out.println("chuoi lon xon server gui:"+nhan);
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
