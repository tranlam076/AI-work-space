package ThiGK;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client2 {
	private static Socket soc;
	private static DataInputStream dis;
	private static DataOutputStream dos;
	private Scanner sc;
	public static void main(String[] args) {
		try {
			soc = new Socket("localhost", 2020);
			dos = new DataOutputStream(soc.getOutputStream());
			dis = new DataInputStream(soc.getInputStream());
			new Client2();
		} catch (Exception e) {
			e.getMessage();
		}
	}
	
	public Client2 () throws IOException {
		while (true) {
			System.out.println("Enter name");
			sc = new Scanner(System.in);
			String Sending = sc.nextLine();
			dos.writeUTF(Sending);
			dos.flush();
			System.out.println("sent");
			String recieve = dis.readUTF();
			System.out.println("recieve");
			if (recieve.length()>0) {
				System.out.println(recieve);
			}
		}
	}
}
