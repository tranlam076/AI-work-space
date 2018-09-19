package BaiTH1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Bai2_server {
	private static ServerSocket server;
	private static DataOutputStream dos;
	private static DataInputStream dis;

	public static void main(String[] args) throws IOException {
		try {
			server = new ServerSocket(2000);
			System.out.println("created server");
			while(true) {
				Socket soc = server.accept(); 
				dos = new DataOutputStream(soc.getOutputStream());
				dis = new DataInputStream(soc.getInputStream());
				while (true) {
					String recieve = dis.readUTF();
					if (recieve.length()>0) {
						System.out.println("Chuoi da nhan: " + recieve);
						String result = null;
						try {
							result = doCals(recieve).toString();
						} catch (Exception e) {
							dos.writeUTF("Error: " + e.getMessage());
							dos.flush();
							continue;
						}
						dos.writeUTF("Result: " + result);
						dos.flush();
					}	
				}	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Object doCals (String str) throws ScriptException {
		ScriptEngineManager mgr = new ScriptEngineManager();
		ScriptEngine engine = mgr.getEngineByName("js");
		return engine.eval(str);
	}
}
