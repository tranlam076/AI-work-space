package connectToServer;

import java.io.DataInputStream;
import java.net.Socket;

public class DateTimeClient {
	public static void main (String [] args) {
		try {
			Socket soc = new Socket("localhost", 2000);
			DataInputStream dis = new DataInputStream(soc.getInputStream());
			System.out.println(dis.readUTF());
		} catch (Exception e) {
				
		}
	}
}
