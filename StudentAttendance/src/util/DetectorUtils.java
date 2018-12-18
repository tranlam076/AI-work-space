package util;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class DetectorUtils {
	
	public static final String SERVER_HOST = "localhost";
	public static final int SERVER_PORT = 6969;
	
	public static String train() {
		String result = null;
		try {
			Socket socket = new Socket(SERVER_HOST, SERVER_PORT);
			DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
			DataInputStream din = new DataInputStream(socket.getInputStream());
			dout.writeUTF("train");
			dout.flush();
			result = din.readUTF();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	public static String detect(String path) {
		String result = null;
		try {
			Socket socket = new Socket(SERVER_HOST, SERVER_PORT);
			DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
			DataInputStream din = new DataInputStream(socket.getInputStream());
			System.out.println("detect;" + path);
			dout.writeUTF("detect;" + path);
			dout.flush();
			result = din.readUTF();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static String retrain(String path, String studentCode) {
		String result = null;
		try {
			Socket socket = new Socket(SERVER_HOST, SERVER_PORT);
			DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
			DataInputStream din = new DataInputStream(socket.getInputStream());
			dout.writeUTF("retrain;" + path + ";" + studentCode);
			dout.flush();
			result = din.readUTF();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
}
