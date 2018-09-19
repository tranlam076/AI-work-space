package BaiTH1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Bai2_client {
	private static Socket soc;
	private static DataInputStream dis;
	private static DataOutputStream dos;
	private static Scanner sc;

	public static void main(String[] args) throws IOException {
		boolean done = false;
		boolean sent = false;
		try {
			soc = new Socket("localhost", 2000);
			dos = new DataOutputStream(soc.getOutputStream());
			dis = new DataInputStream(soc.getInputStream());
			sc = new Scanner (System.in);
			
			while (true) {
				if (!sent) {
					System.out.println("Nhap phep tinh: ");
					String request = sc.nextLine();
					dos.writeUTF(request);
					dos.flush();
					sent = true;
					done = false;
				}
				if (!done) {
					String recieve = dis.readUTF();
					System.out.println(recieve);
					done = true;
					sent = false;
				}
			}
		} catch (Exception e) {
			e.getMessage();
		}
	}
}
