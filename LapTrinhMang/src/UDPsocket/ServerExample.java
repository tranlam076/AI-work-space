package UDPsocket;

import java.io.IOException;
import java.util.Scanner;

import javax.script.ScriptException;

public class ServerExample implements Runnable {
//	private static float rateVND_USD = 0.000043f;
//	private static float rateVND_JPY = 0.0048f;
	private static final int PORT = 9000; 
	Scanner sc = new Scanner(System.in);
	static UDPThread udpThread;


	public static void main(String[] args) throws IOException, ScriptException {
		System.out.println("started the main server");
		UDPThread serverThread = new UDPThread(PORT);
		serverThread.start();
		udpThread = serverThread;
		new Thread (new ServerExample()).start();
		
	}

	@Override
	public void run() {
		while (true) {
			System.out.println("rate of VND - USD....");
			String vnd_usd = sc.nextLine();
			System.out.println("rate of VND - JPY....");
			String vnd_jpy = sc.nextLine();
			udpThread.updateRate(Float.parseFloat(vnd_usd), Float.parseFloat(vnd_jpy));
		}	
	}
}
