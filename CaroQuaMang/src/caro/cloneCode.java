//package caro;
//
//import java.io.DataInputStream;
//import java.net.ServerSocket;
//import java.net.Socket;
//import java.util.ArrayList;
//
//public class cloneCode {
//	int so = 10;
//	ArrayList<Point> ddist = new ArrayList<>();
//	ServerSocket server;
//	int numP = 0;
//
//	public cloneCode() {
//		try {
//			server = new ServerSocket(3000);
//			Socket p1 = server.accept();
//			p1.new Xuly(p1, this);
//			numP++;
//			Socket p2 = server.accept();
//			p2.new Xuly(p2, this);
//			numP++;
//			System.out.println("bat dau choi");
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//	}
//}
//
//class Xuly extends Thread {
//	cloneCode server;
//	Socket soc;
//
//	public Xuly(Socket soc, cloneCode server) {
//		this.server = server;
//		this.soc = soc;
//	}
//
//	@Override
//	public void run() {
//
//		while (true) {
//			try {
//				DataInputStream dis = new DataInputStream(soc.getInputStream());
//				int x = Integer.parseInt(dis.readUTF());
//				int y = Integer.parseInt(dis.readUTF());
//				
//			} catch (Exception e) {
//				// TODO: handle exception
//			}
//		}
//	}
//}
