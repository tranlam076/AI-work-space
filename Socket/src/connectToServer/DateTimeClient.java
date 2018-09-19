package connectToServer;

import java.io.DataInputStream;
import java.net.Socket;

public class DateTimeClient {
	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			new LuongRequest().start();
		}
	}
}

class LuongRequest extends Thread {
	
	private Socket soc;

	public void run() {
		try {
			soc = new Socket("localhost", 2000);
			DataInputStream dis = new DataInputStream(soc.getInputStream());
			System.out.println(dis.readUTF());
		} catch (Exception e) {

		}
	}
}
