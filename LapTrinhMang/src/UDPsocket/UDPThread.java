package UDPsocket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

import javax.script.ScriptException;

class UDPThread extends Thread {
	public int PORT = 9000;
	private DatagramSocket severSocket;
	private float rateVND_USD = 0.000043f;
	private float rateVND_JPY = 0.0048f;
	Scanner sc;

	public UDPThread(int portHost) throws ScriptException {
		this.PORT = portHost;
	}
	
	public void updateRate (float rateVND_USD, float rateVND_JPY ) {
		this.rateVND_USD = rateVND_USD;
		this.rateVND_JPY = rateVND_JPY;
	}
	
	public void run() {
		try {
			severSocket = new DatagramSocket(PORT);
			System.out.println("Server is running ...");
			byte[] receiveData = new byte[1024];
			byte[] sendData;
			while (true) {
				DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
				severSocket.receive(receivePacket);
				InetAddress IPAddress = receivePacket.getAddress();
				int port = receivePacket.getPort();
				String request = new String(receivePacket.getData(), 0, receivePacket.getLength());				
				String response = process(request);
				sendData = response.getBytes();
				DatagramPacket sendPacket = new DatagramPacket(sendData, response.length(), IPAddress, port);
				severSocket.send(sendPacket);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ScriptException e) {
			e.printStackTrace();
		}
	}
	
	public String process(String request) throws ScriptException {
		String response = "";
		float money = 0f;
		String[] reqs = request.split(",");
		if (reqs.length == 2) {
			switch (reqs[0]) {
			case "USD":
				money = Float.parseFloat(reqs[1]);
				response = "" + Float.toString((float)(money*rateVND_USD));
				break;
			case "YEN":
				money = Float.parseFloat(reqs[1]);
				response = "" + Float.toString((float)(money*rateVND_JPY));
				break;

			default:
				break;
			}
			return response;
		}
		return response;
	}
}
