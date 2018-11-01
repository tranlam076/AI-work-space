package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/httpHeader")
public class httpHeader extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public httpHeader() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		System.out.println(request.getMethod());
		System.out.println(request.getRequestURI());
		System.out.println(request.getProtocol());
		
		out.print("<!DOCTYPE html>\r\n" + 
				"<html lang=\"en\">\r\n" + 
				"<head>\r\n" + 
				"	<meta charset=\"UTF-8\">\r\n" + 
				"	<title>http header</title>\r\n" + 
				"</head>\r\n" + 
				"<body>\r\n" +
				"	<b>Request Method: </b>\r\n" + 
				"\r\n" + request.getMethod() +
				"	<br/>\r\n" + 
				"	<b>Request URI: </b>\r\n" + 
				"\r\n" + request.getRequestURI() +
				"	<br/>\r\n" + 
				"	<b>Request Protocol: </b>\r\n" + 
				"\r\n" + request.getProtocol() + 
				"	<br/>\r\n" + 
				"\r\n" + 
				"<table border=1 align =\"center\">");
		
		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String headerName = headerNames.nextElement();
			out.println("<tr> <td>" + headerName + "</td>");
			out.println("<td>" + request.getHeader(headerName) + "</td>");
			out.println("<tr>");
		}		
		out.println(		
				"</table>" +
				"</body>\r\n" + 
				"</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
