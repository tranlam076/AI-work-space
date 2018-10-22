package UDPsocket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientExample {
	private static InetAddress inetAdress;
	private static DatagramSocket datagramSocket;
	public static final int PORT = 9000;
	public static final String HOST = "localhost";
	public static  String VND = "1000";
	public static  String USD = "";
	public static  String YEN = "";
	Scanner sc= new Scanner(System.in);

	public static void main(String[] args) throws SocketException, UnknownHostException, InterruptedException {
		new ClientExample();
	}

	public ClientExample() throws SocketException, UnknownHostException, InterruptedException {
		datagramSocket = new DatagramSocket();
		inetAdress = InetAddress.getByName(HOST);
		Process();
	}

	private void Process() throws InterruptedException {
		System.out.println("Starting...");
		PrintThread print = new PrintThread();
		print.start();
		while (true) {
			Thread.sleep(1500);
			System.out.println("Enter VND");
			VND = sc.nextLine();
		}
	}
	
	static String Exchange(String value) {
		try {
			byte[] sendData = value.getBytes();
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, inetAdress, PORT);
			datagramSocket.send(sendPacket);
			byte[] receiveData = new byte[1024];
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			datagramSocket.setSoTimeout(3000);
			datagramSocket.receive(receivePacket);
			String sReceive = new String(receivePacket.getData(), 0, receivePacket.getLength());
			return sReceive;
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return value;
	}
}

class PrintThread extends Thread {
	String VND = "";
	String USD = "";
	String YEN = "";
	public PrintThread() {
		super();
	}
 	
	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(1500);
				this.VND = Float.toString(Float.parseFloat(ClientExample.VND));
				this.USD = ClientExample.Exchange("USD,"+ClientExample.VND);
				this.YEN = ClientExample.Exchange("YEN,"+ClientExample.VND);
				System.out.println(VND + " - " + USD + " - " + YEN);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}