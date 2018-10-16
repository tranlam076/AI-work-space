package UDPsocket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class ThreadUDPTest {
	public static final int PORT = 2808;
	private DatagramSocket severSocket;
	

	public static void main(String[] args) throws IOException {
		new ThreadUDPTest();
	}

	public static UDPThread Client1;

	public ThreadUDPTest() throws IOException {
		UDPThread thread = new UDPThread(severSocket, PORT);
		UDPThread thread1 = new UDPThread(severSocket, PORT);
		thread.start();
		Client1 = thread;
		System.out.println("Created server...");
	}

	public static void notifyClient(String msg) throws IOException {
		System.out.println("noti");
		Client1.notifyClient(msg);
	}
}

class UDPThread extends Thread {

	DatagramSocket severSocket;
	int portHost;

	public UDPThread(DatagramSocket severSocket, int portHost) throws IOException {
		this.severSocket = severSocket;
		this.portHost = portHost;
	}

	public void run() {
		while (true) {
			try {
				severSocket = new DatagramSocket(portHost);
				System.out.println("Server is running ...");
				byte[] receiveData = new byte[1024];
				byte[] senData;
				while (true) {
					DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
					severSocket.receive(receivePacket);
					InetAddress IPAddress = receivePacket.getAddress();
					int port = receivePacket.getPort();
					String request = new String(receivePacket.getData(), 0, receivePacket.getLength());
					String response = doCals(request).toString();
					senData = response.getBytes();
					DatagramPacket sendPacket = new DatagramPacket(senData, response.length(), IPAddress, port);
					severSocket.send(sendPacket);
				}
			} catch (IOException ex) {
				Logger.getLogger(ServerExample.class.getName()).log(Level.SEVERE, null, ex);
			} catch (ScriptException e) {
				e.printStackTrace();
			}
		}
	}

	public static Object doCals(String str) throws ScriptException {
		ScriptEngineManager mgr = new ScriptEngineManager();
		ScriptEngine engine = mgr.getEngineByName("js");
		return engine.eval(str);
	}

	public void notifyClient(String msg) throws IOException {
		
	}
}
