package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LongServer
 */
@WebServlet("/LongServer")
public class LongServer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LongServer() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Change the definition of "out" depending on whether
		// or not gzip is supported.
		PrintWriter out;
		if (GzipUtilities.isGzipSupported(request) && !GzipUtilities.isGzipDisabled(request)) {
			out = GzipUtilities.getGzipWriter(response);
			response.setHeader("Content-Encoding", "gzip");
		} else {
			out = response.getWriter();
		}
		String docType = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " + "Transitional//EN\">\n";
		String title = "Long Page";
		out.println(docType + "<HTML>\n" + "<HEAD><TITLE>" + title + "</TITLE></HEAD>\n"
				+ "<BODY BGCOLOR=\"#FDF5E6\">\n" + "<H1 ALIGN=\"CENTER\">" + title + "</H1>\n");
		String line = "Blah, blah, blah, blah, blah. " + "Yadda, yadda, yadda, yadda.";
		for (int i = 0; i < 10000; i++)
			out.println(line);
		out.println("</BODY></HTML>");
		out.close(); // Needed for gzip; optional otherwise.
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
