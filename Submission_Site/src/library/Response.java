package library;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class Response {
	private static PrintWriter out;

	public static void returnSuccess (HttpServletResponse response ,String message) {
		try {
			out = response.getWriter();
			out.println("Page not found");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
