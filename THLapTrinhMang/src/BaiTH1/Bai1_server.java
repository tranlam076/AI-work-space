package BaiTH1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Bai1_server {
	private static ServerSocket server;
	private static DataOutputStream dos;
	private static DataInputStream dis;

	public static void main(String[] args) {
		try {
			server = new ServerSocket(2000);
			System.out.println("created server");
			while(true) {
				Socket soc = server.accept(); 
				dos = new DataOutputStream(soc.getOutputStream());
				dis = new DataInputStream(soc.getInputStream());
				String recieve = dis.readUTF();
				String count[] = recieve.split("[ ,.]");
				if (recieve.length()>0) {
					dos.writeUTF("Chuoi toUpperCase: " + recieve.toUpperCase());
					dos.writeUTF("Chuoi da gui: " + recieve);
					dos.writeUTF("So luong chuoi: " + count.length);
					dos.flush();
				}
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
