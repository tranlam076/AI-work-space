package Aircraft;

import java.awt.Point;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerAircraft {
	private static ServerSocket server;

	public static void main(String[] args) throws IOException {
		new ServerAircraft();
	}

	public boolean allow = false;
	public static String airCraftFirst;
	public static String airCraftSecond;
	public static SocketThread thread1;
	public static SocketThread thread2;

	public static ArrayList<Point> FirstAircraft = new ArrayList<>();
	public static ArrayList<Point> SecondAircraft = new ArrayList<>();
	public static ArrayList<Point> FirstPlayerAttach = new ArrayList<>();
	public static ArrayList<Point> SecondPlayerAttach = new ArrayList<>();
	public static int firstPlayCount = 0;
	public static int secondPlayCount = 0;

	public ServerAircraft() {
		try {
			server = new ServerSocket(2000);
			System.out.println("created server");
			while (true) {
				Socket player1 = server.accept();
				SocketThread st1 = new SocketThread(player1);
				st1.start();
				st1.setPlayFirst(true);
				thread1 = st1;
				System.out.println("player---1");
				Socket player2 = server.accept();
				SocketThread st2 = new SocketThread(player2);
				st2.start();
				st2.setPlayFirst(false);
				thread2 = st2;
				System.out.println("player---2");
				System.out.println("waiting for setup aircraft");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void notifyClient(String str1, String msg) throws IOException {
		if (str1 == "st1")
			thread1.notifyClient(msg);
		if (str1 == "st2")
			thread2.notifyClient(msg);
		if (str1 == "end-game") {
			thread1.notifyClient(msg);
			thread2.notifyClient(msg);
		}
	}
}

class SocketThread extends Thread {
	boolean isPlayFirst;

	public boolean isPlayFirst() {
		return isPlayFirst;
	}

	public void setPlayFirst(boolean isPlayFirst) {
		this.isPlayFirst = isPlayFirst;
	}

	Socket soc;
	DataOutputStream dos;
	DataInputStream dis;

	public SocketThread(Socket soc) throws IOException {
		this.soc = soc;
		this.dos = new DataOutputStream(soc.getOutputStream());
		this.dis = new DataInputStream(soc.getInputStream());
	}

	public void run() {
		while (true) {
			try {
				String recievedValues = dis.readUTF();
				control(dos, recievedValues, isPlayFirst);
			} catch (Exception e) {
				e.getLocalizedMessage();
			}
		}
	}

	public static void control(DataOutputStream dos, String recievedValues, boolean isPlayFirst) throws IOException {
		if (recievedValues.length() > 0) {
			if (recievedValues.contains("setup,")) {
				String getPosition[] = recievedValues.split(",");
				dos.writeUTF("starting");
				dos.flush();
				if (isPlayFirst == true) {
					ServerAircraft.airCraftFirst = recievedValues;
					for (int i = 1; i < getPosition.length; i++) {
						ServerAircraft.FirstAircraft.add(new Point(Integer.parseInt(getPosition[i].split("-")[0]),
								Integer.parseInt(getPosition[i].split("-")[1])));
					}
					dos.writeUTF("play-first");
					dos.flush();
				}
				if (isPlayFirst == false) {
					ServerAircraft.airCraftSecond = recievedValues;
					for (int i = 1; i < getPosition.length; i++) {
						ServerAircraft.SecondAircraft.add(new Point(Integer.parseInt(getPosition[i].split("-")[0]),
								Integer.parseInt(getPosition[i].split("-")[1])));
					}
					dos.writeUTF("play-second");
					dos.flush();
				}
			}

			if (recievedValues.contains("reset")) {
				ServerAircraft.airCraftFirst = " ";
				ServerAircraft.airCraftSecond = " ";
				ServerAircraft.FirstAircraft.clear();
				ServerAircraft.SecondAircraft.clear();
				ServerAircraft.FirstAircraft.clear();
				ServerAircraft.FirstPlayerAttach.clear();
				ServerAircraft.SecondPlayerAttach.clear();
				ServerAircraft.firstPlayCount = 0;
				ServerAircraft.secondPlayCount = 0;
				ServerAircraft.notifyClient("st1", "reset");
				ServerAircraft.notifyClient("st2", "reset");
			}

			if (recievedValues.contains("play")) {
				String[] position = recievedValues.split(",");
				int x = Integer.parseInt(position[2].split("-")[0]);
				int y = Integer.parseInt(position[2].split("-")[1]);
				if (position[1].contains("first")) {
					ServerAircraft.FirstPlayerAttach.add(new Point(x, y));
					if (ServerAircraft.SecondAircraft.contains(new Point(x, y))) {
						ServerAircraft.firstPlayCount++;
						if (ServerAircraft.firstPlayCount > 6) {
							ServerAircraft.notifyClient("end-game", null);
						}
					}
					ServerAircraft.notifyClient("st2", x + "-" + y);
					if (ServerAircraft.SecondAircraft.contains(new Point(x, y))) {
						ServerAircraft.notifyClient("st1", "correct," + x + "-" + y);
					}
				}
				if (position[1].contains("second")) {
					ServerAircraft.SecondPlayerAttach.add(new Point(x, y));
					if (ServerAircraft.FirstAircraft.contains(new Point(x, y))) {
						ServerAircraft.secondPlayCount++;
						if (ServerAircraft.secondPlayCount > 6) {
							ServerAircraft.notifyClient("end-game", null);
						}
					}
					ServerAircraft.notifyClient("st1", x + "-" + y);
					if (ServerAircraft.FirstAircraft.contains(new Point(x, y))) {
						ServerAircraft.notifyClient("st2", "correct," + x + "-" + y);
					}
				}
			}
		}
	}

	public void notifyClient(String msg) throws IOException {
		if (ServerAircraft.firstPlayCount > 6) {
			this.dos.writeUTF("first-win");
			if (isPlayFirst) {
				this.dos.writeUTF(ServerAircraft.airCraftSecond);
			} else {
				this.dos.writeUTF(ServerAircraft.airCraftFirst);
			}
			this.dos.flush();
		}
		if (ServerAircraft.secondPlayCount > 6) {
			this.dos.writeUTF("second-win");
			if (!isPlayFirst) {
				this.dos.writeUTF(ServerAircraft.airCraftFirst);
			} else {
				this.dos.writeUTF(ServerAircraft.airCraftSecond);
			}
			this.dos.flush();
		}
		if (msg != null) {
			if (msg == "reset") {
				this.dos.writeUTF("starting");
				this.dos.writeUTF("reset");
				this.dos.flush();
			} else {
				if (!msg.contains("correct")) {
					this.dos.writeUTF("play-now");
				}
				this.dos.writeUTF("enemyAttach," + msg);
				this.dos.flush();
			}

		}
		System.out.println("check cout:" + ServerAircraft.firstPlayCount + "_" + ServerAircraft.secondPlayCount);
	}
}
