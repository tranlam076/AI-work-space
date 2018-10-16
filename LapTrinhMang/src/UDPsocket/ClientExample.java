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

public class ClientExample {
	private InetAddress inetAdress;
	private DatagramSocket datagramSocket;
	public static final int PORT = 2808;
	public static  String VND = "1000";
	public static  String USD = "";
	public static  String YEN = "";
	Scanner sc= new Scanner(System.in);

	public static void main(String[] args) throws SocketException, UnknownHostException, InterruptedException {
		new ClientExample();
	}

	public ClientExample() throws SocketException, UnknownHostException, InterruptedException {
		datagramSocket = new DatagramSocket();
		inetAdress = InetAddress.getByName("localhost");
		Process();
	}

	private void Process() throws InterruptedException {
		System.out.println("Starting...");
		PrintThread print = new PrintThread();
		print.start();
		while (true) {
			VND = sc.nextLine();
			Thread.sleep(1000);
			print.VND = VND;
			print.USD = Exchange("USD,"+VND);
			print.YEN = Exchange("YEN,"+VND);
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
			Logger.getLogger(ClientExample.class.getName()).log(Level.SEVERE, null, ex);
		}
		return VND;
	}
}

class PrintThread extends Thread {
	String VND = "";
	String USD = "";
	String YEN = "";
	public PrintThread(String VND, String USD, String YEN) {
		this.VND = VND;
		this.USD = USD;
		this.YEN = YEN;
	}
	
	public PrintThread() {
		super();
	}
 	
	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(1000);
				System.out.println(VND + " - " + USD + " - " + YEN);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}