package ThiGK;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server1 {
	public static void main(String[] args) throws IOException {
		new Server1();
	}

	private static ServerSocket server;
	public static SocketThread1 thread1;
	public static ArrayList<Person> listPersonAL = new ArrayList<>();

	public Server1() throws IOException {
		listPersonAL.add(new Person("A", 12));
		listPersonAL.add(new Person("B", 14));
		server = new ServerSocket(2000);
		System.out.println("Created server");
		while (true) {
			Socket client1 = server.accept();
			SocketThread1 st1 = new SocketThread1(client1);
			st1.start();
			thread1 = st1;
			System.out.println("Client---connect");
		}
	}
	
	public static void notifyClient(String msg) throws IOException {
		System.out.println("noti");
			thread1.notifyClient(msg);
	}
}

class SocketThread1 extends Thread {

	Socket soc;
	DataOutputStream dos;
	DataInputStream dis;

	public SocketThread1(Socket soc) throws IOException {
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
			if (recievedValues.contains("getArr")) {
				String st = "here,";
				for (int i = 0; i < Server1.listPersonAL.size(); i++) {
					st += Server1.listPersonAL.get(i).getName() + "-"+ Server1.listPersonAL.get(i).getAge() +"," ; 
				}
				Server1.notifyClient(st);
			}
			for (int i = 0; i < Server1.listPersonAL.size(); i++) {
				if (recievedValues.contains(Server1.listPersonAL.get(i).getName())) {
					Server1.notifyClient("" + Server1.listPersonAL.get(i).getAge());
				}
			}
		}
	}
	
	public void notifyClient(String msg) throws IOException {
		this.dos.writeUTF("" + msg);
		this.dos.flush();
	}
}
