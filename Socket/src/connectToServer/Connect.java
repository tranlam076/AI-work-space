package connectToServer;

import java.io.BufferedReader;
import java.io.Console;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Connect {

	public static void main(String[] args) {
		try {
			Socket soc = new Socket("google.com", 80);
			System.out.println(soc.getInetAddress());
			
			DataOutputStream dos = new DataOutputStream(soc.getOutputStream());
			DataInputStream dis = new DataInputStream(soc.getInputStream());
			PrintWriter pw = new PrintWriter(soc.getOutputStream());
			
			pw.println("GET/ HTTP/ 1.1");
			pw.println();
			pw.flush();
			
			BufferedReader br = new BufferedReader(new InputStreamReader(soc.getInputStream()));
			System.out.println(br.readLine());

			br.close();
			System.out.println("success");
			soc.close();
		} catch (Exception e) {
			
		}
	}

}
