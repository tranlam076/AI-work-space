package DiachiIp;

import java.net.InetAddress;

public class DiachiIp {
	public static void main(String[] args) {
		try {
//			InetAddress ip = InetAddress.getByName("google.com");
//			System.out.println(ip.getHostAddress());
//			System.out.println(ip.getLocalHost());
			
			InetAddress ip [] = InetAddress.getAllByName("dut.udn.vn");
			for (InetAddress inetAddress : ip) {
				System.out.println(inetAddress.getHostAddress());
			}
		} catch (Exception e) {

		}
	}
}
