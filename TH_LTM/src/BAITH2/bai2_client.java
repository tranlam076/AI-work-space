package BAITH2;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.SocketException;
import java.util.Scanner;

public class bai2_client {
    private static Scanner sc;
	private static DatagramSocket socket;

	public static void main(String args[])
    {
    	byte[] senddata;
    	byte[] receivedata=new byte[1024];
    	sc = new Scanner(System.in);
    	try {
			socket = new DatagramSocket();
			InetAddress inet=InetAddress.getByName("localhost");
			System.out.println("moi ban nhap vao chuoi muon goi:");
			String goi=sc.nextLine();
			senddata=new byte[goi.length()];
			senddata=goi.getBytes();
			DatagramPacket send=new DatagramPacket(senddata, senddata.length, inet, 8899); 
			socket.send(send);
			DatagramPacket receive=new DatagramPacket(receivedata, receivedata.length);
			socket.receive(receive);
			String nhan=new String(receive.getData());
			System.out.println("gia tri tinh duoc la:"+nhan);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
}
