package UDPsocket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientTest {
	private InetAddress inetAdress;
	private DatagramSocket datagramSocket;
	public static final int PORT = 2808;
	public static  String VND = "1000";
	public static  String USD = "";
	public static  String YEN = "";
	Scanner sc= new Scanner(System.in);

	public static void main(String[] args) throws SocketException, UnknownHostException, InterruptedException {
		new ClientTest();
	}

	public ClientTest() throws SocketException, UnknownHostException, InterruptedException {
		datagramSocket = new DatagramSocket();
		inetAdress = InetAddress.getByName("localhost");
		Process();
	}

	private void Process() throws InterruptedException {
		System.out.println("Starting...");
		while (true) {
			VND = sc.nextLine();
			Exchange(VND);
		}
	}
	
	private String Exchange(String VND) {
		try {
			byte[] sendData = VND.getBytes();
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, inetAdress, PORT);
			datagramSocket.send(sendPacket);
			byte[] receiveData = new byte[1024];
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			datagramSocket.receive(receivePacket);
			String sReceive = new String(receivePacket.getData(), 0, receivePacket.getLength());
			return sReceive;
		} catch (IOException ex) {
			Logger.getLogger(ClientTest.class.getName()).log(Level.SEVERE, null, ex);
		}
		return VND;
	}
}
