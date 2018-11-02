package library;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class Response {
	public static void returnSuccess (HttpServletResponse response ,String message) {
		try {
			PrintWriter out = response.getWriter();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
