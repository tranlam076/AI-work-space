package UDPsocket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.script.ScriptException;

public class ServerExample {
	public static final int PORT = 2808;
	private DatagramSocket severSocket;
	private static int count = 1000;

	public static void main(String[] args) throws ScriptException {
		new ServerExample(PORT);
	}

	public ServerExample(int portHost) throws ScriptException {
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
				String request = new String(receivePacket.getData(), 0, receivePacket.getLength());				
				System.out.println(request);
				String response = process(request);
				sendData = response.getBytes();
				DatagramPacket sendPacket = new DatagramPacket(sendData, response.length(), IPAddress, port);
				severSocket.send(sendPacket);
			}
		} catch (IOException ex) {
			Logger.getLogger(ServerExample.class.getName()).log(Level.SEVERE, null, ex);
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
