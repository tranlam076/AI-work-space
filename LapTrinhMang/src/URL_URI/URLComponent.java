package URL_URI;

import java.net.URL;

public class URLComponent {
	public static void main(String[] args) {
		try {
//			URL u = new URL(args[0]);
			URL u = new URL("http://a.com/1/0");
			System.out.println("URL is "+u);
			System.out.println("The protocol part is "+u.getProtocol());
			System.out.println("The host part is "+u.getHost());
			System.out.println("The port part is "+u.getPort());
			System.out.println("The file part is "+u.getFile());
			System.out.println("The reference part is "+u.getRef());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
