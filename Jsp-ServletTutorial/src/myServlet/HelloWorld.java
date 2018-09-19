package myServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HelloWorld
 */
public class HelloWorld extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public HelloWorld() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		loadParams(request, response, " byGet");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		loadParams(request, response, " byPost");
	}

	private void loadParams(HttpServletRequest request, HttpServletResponse response, String method)
			throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		writer.println("Hello World" + method);

		ServletContext context =  this.getServletContext();
		Enumeration<String> listContext = context.getInitParameterNames();
		writer.println("List all context:");
		while (listContext.hasMoreElements()) {
			String nextContext = listContext.nextElement();
			writer.println("name: "+ nextContext);
			writer.println("value: "+ context.getInitParameter(nextContext));
		}

//		config can not get value outside servlet, see web.xml, note context and config - same 
		ServletConfig config = this.getServletConfig();
		Enumeration<String> listConfig = config.getInitParameterNames();
		writer.println("\nList all config:");
		while (listConfig.hasMoreElements()) {
			String nextConfig = listConfig.nextElement();
			writer.println("name: "+ nextConfig);
			writer.println("value: "+ config.getInitParameter(nextConfig));
		}
	}
}
