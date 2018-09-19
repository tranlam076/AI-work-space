package BaiTH1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Bai1_client {
	private static Socket soc;
	private static DataInputStream dis;
	private static DataOutputStream dos;

	public static void main(String[] args) {
		boolean done = false;
		try {
			soc = new Socket("localhost", 2000);
			dos = new DataOutputStream(soc.getOutputStream());
			dis = new DataInputStream(soc.getInputStream());
			dos.writeUTF("client connected");
			dos.flush();
			while (!done) {
				String recieve = dis.readUTF();
				if (recieve.length()>0) done = true;
				System.out.println(recieve);
				System.out.println(dis.readUTF());
				System.out.println(dis.readUTF());
			}
		} catch (Exception e) {
			e.getMessage();
		}
	}
}
