package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Example
 */
@WebServlet("/Example")
public class Example extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Example() {
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.append("<h1>hello</h1>");
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.append("<h1>Ket qua hoc tap</h1>");
		String param1 = request.getParameter("param1");
		String param2 = request.getParameter("param2");
		String param3 = request.getParameter("param3");
		
		float bt = Float.parseFloat(param1);
		float gk = Float.parseFloat(param2);
		float ck = Float.parseFloat(param3);
		
		
		float diem = bt*20/100 + gk*20/100 + ck*60/100;  
		
		System.out.println(bt + "-" + gk + "-" + ck + "-" + diem );

		out.print("Ket qua: ");
		if (diem <4.0) {
			out.print("F");
		}
		
		if (diem >= 4.0 && diem < 5.5 ) {
			out.print("D");
		}
		
		if (diem >= 5.5  && diem < 7.0) {
			out.print("C");
		}
		
		if (diem >= 7.0  && diem < 8.5) {
			out.print("B");
		}
		
		if (diem >= 8.5  && diem <= 10) {
			out.print("A");
		}
		
		
	}

}
