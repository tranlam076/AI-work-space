package BAITH1;

import java.net.*;
import java.util.*;
import java.io.*;

class bai3_server {
	static Vector ClientSockets;
	static Vector LoginNames;

	bai3_server() throws Exception {
		ServerSocket soc = new ServerSocket(5217);
		ClientSockets = new Vector();
		LoginNames = new Vector();

		while (true) {
			Socket CSoc = soc.accept();
			AcceptClient obClient = new AcceptClient(CSoc);
		}
	}

	public static void main(String args[]) throws Exception {

		bai3_server ob = new bai3_server();
	}

	class AcceptClient extends Thread {
		Socket ClientSocket;
		DataInputStream din;
		DataOutputStream dout;
		String LoginName;

		AcceptClient(Socket CSoc) throws Exception {
			ClientSocket = CSoc;

			din = new DataInputStream(ClientSocket.getInputStream());
			dout = new DataOutputStream(ClientSocket.getOutputStream());

			LoginName = din.readUTF();

			System.out.println("User Logged In :" + LoginName);
			LoginNames.add(LoginName);
			ClientSockets.add(ClientSocket);
			start();
		}

		public void run() {
			while (true) {

				try {
					String msgFromClient = new String();
					msgFromClient = din.readUTF();
					StringTokenizer st = new StringTokenizer(msgFromClient);

					String MsgType = "";
					if (st.hasMoreTokens()) {
						MsgType = st.nextToken();
					}

					int iCount = 0;

					if (MsgType.equals("LOGOUT")) {
						for (iCount = 0; iCount < LoginNames.size(); iCount++) {
							if (LoginNames.elementAt(iCount).equals(LoginName)) {
								LoginNames.removeElementAt(iCount);
								ClientSockets.removeElementAt(iCount);
								System.out.println("User " + LoginName + " Logged Out ...");
								break;
							}
						}

					} else {
						String msg = "";
						while (st.hasMoreTokens()) {
							msg = msg + " " + st.nextToken();
						}
						for (iCount = 0; iCount < LoginNames.size(); iCount++) {
							if (!LoginNames.elementAt(iCount).equals(LoginName)) {
								Socket tSoc = (Socket) ClientSockets.elementAt(iCount);
								DataOutputStream tdout = new DataOutputStream(tSoc.getOutputStream());
								tdout.writeUTF(LoginName + ":" + msg);
								break;
							}
						}
					}
					if (MsgType.equals("LOGOUT")) {
						break;
					}

				} catch (Exception ex) {
					ex.printStackTrace();
				}

			}
		}
	}
}
