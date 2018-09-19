package connectToServer;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Vector;

public class DateTimeServer {
	private static ServerSocket server;

	public static void main(String[] args) {
		new DateTimeServer();
	}

	Vector<MyThread> listThread = new Vector<>();
	int maxRun = 10;
	int countC = 0;

	public DateTimeServer() {
		try {
			server = new ServerSocket(2000);
			System.out.println("created server");
			while (true) {
				Socket soc = server.accept();
				System.out.println("co ket noi " + countC++);
				MyThread mt = new MyThread(soc, this);
				listThread.add(mt);
				if (listThread.size() <= maxRun) {
					mt.start();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void isFinished(MyThread myThread) {
		listThread.remove(myThread);
		int count = 0;
		for (MyThread myThread2 : listThread) {
			if (myThread2.isRunning == true)
				count++;
			if (!myThread2.isRunning && count <= maxRun) {
				myThread2.start();
			}
		}
	}
}

class MyThread extends Thread {
	Socket soc;
	DateTimeServer dts;
	boolean isRunning = false;

	public MyThread(Socket soc, DateTimeServer dts) {
		this.soc = soc;
		this.dts = dts;
	}

	public void run() {
		isRunning = true;
		DataOutputStream dos;
		try {
			dos = new DataOutputStream(soc.getOutputStream());
			Thread.sleep(5000);
			dos.writeUTF(new Date().toString());
			dts.isFinished(this);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
