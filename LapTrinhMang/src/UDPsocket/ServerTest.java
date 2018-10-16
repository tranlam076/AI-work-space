package UDPsocket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.script.ScriptException;

public class ServerTest {
	public static final int PORT = 2808;
	private DatagramSocket severSocket;
	private static int count = 1000;

	public static void main(String[] args) throws ScriptException {
		new ServerTest(PORT);
	}

	public ServerTest(int portHost) throws ScriptException {
		try {
			severSocket = new DatagramSocket(portHost);
			System.out.println("Server is running ...");
			byte[] receiveData = new byte[1024];
			byte[] sendData;
			while (true) {
				DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
				severSocket.receive(receivePacket);
				InetAddress IPAddress = receivePacket.getAddress();
				int port = receivePacket.getPort();
				
				byte [] test = receivePacket.getData();
				System.out.println(test.length);
				for (int i =0; i < test.length; i++) {
					System.out.println("data " + test[i]);
				}
				
				String request = new String(test, 0, receivePacket.getLength());				
				System.out.println(request);
				String response = process(request);
				sendData = response.getBytes();
				DatagramPacket sendPacket = new DatagramPacket(sendData, response.length(), IPAddress, port);
				severSocket.send(sendPacket);
			}
		} catch (IOException ex) {
			Logger.getLogger(ServerTest.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public static String process(String request) throws ScriptException {
		String response = "";
		String[] reqs = request.split(",");
		if (reqs[0].equals("USD")) {
			int money = Integer.parseInt(reqs[1]);
			response = "" + (money + count);
		}

		if (reqs[0].equals("YEN")) {
			int money = Integer.parseInt(reqs[1]);
			response = "" + (money + count);
		}
		System.out.println("receive: "+Integer.parseInt(reqs[1]));
		count ++;
		return response;
	}
}
