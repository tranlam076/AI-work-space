package ThiGK;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server2 {
	public static void main(String[] args) throws IOException {
		new Server2();
	}

	private static ServerSocket server;
	private static Socket soc;
	DataOutputStream dosS;
	DataInputStream disS;
	public static SocketThread2 thread2;
	public static ArrayList<Person> listPersonMZ = new ArrayList<>();
	public Server2() throws IOException {
		listPersonMZ.add(new Person("M", 13));
		listPersonMZ.add(new Person("N", 15));
		soc = new Socket("localhost", 2000);
		dosS = new DataOutputStream(soc.getOutputStream());
		disS = new DataInputStream(soc.getInputStream());
		dosS.writeUTF("getArr");
		dosS.flush();
		String ArrAL = "";
		ArrAL = disS.readUTF();
		String Arr[] = ArrAL.split(",");
		if (Arr[0].contains("here")) {
			for (int i =1; i<Arr.length; i++) {
				System.out.println(Arr[i]);
				listPersonMZ.add(new Person(Arr[i].split("-")[0], Integer.parseInt(Arr[i].split("-")[1])));
			}
		}
		
		server = new ServerSocket(2020);
		System.out.println("Created server");
		while (true) {
			Socket client = server.accept();
			SocketThread2 st2 = new SocketThread2(client);
			st2.start();
			thread2 = st2;
			System.out.println("Client---connect");
		}
	}
	
	public static void notifyClient(String msg) throws IOException {
			thread2.notifyClient(msg);
	}
}

class SocketThread2 extends Thread {

	Socket soc;
	DataOutputStream dos;
	DataInputStream dis;

	public SocketThread2(Socket soc) throws IOException {
		this.soc = soc;
		this.dos = new DataOutputStream(soc.getOutputStream());
		this.dis = new DataInputStream(soc.getInputStream());
	}

	public void run() {
		while (true) {
			try {
				String recievedValues = dis.readUTF();
				control(dos, recievedValues);
			} catch (Exception e) {
				e.getLocalizedMessage();
			}
		}
	}

	public static void control(DataOutputStream dos, String recievedValues) throws IOException {
		System.out.println(recievedValues);
		if (recievedValues.length() > 0) {
			for (int i = 0; i < Server2.listPersonMZ.size(); i++) {
				if (recievedValues.contains(Server2.listPersonMZ.get(i).getName())) {
					Server2.notifyClient("" + Server2.listPersonMZ.get(i).getAge());
				}
			}
		}
	}
	
	public void notifyClient(String msg) throws IOException {
		this.dos.writeUTF("" + msg);
		this.dos.flush();
	}
}
