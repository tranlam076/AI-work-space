package myServlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tranlam.models.DatabaseManagement;
import tranlam.models.Image;

/**
 * Servlet implementation class Authentication
 */
public class Authentication extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Authentication() {
		super();
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
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		DatabaseManagement dm = new DatabaseManagement();
		try {
			if (dm.checkUser(username, password)) {
				
				Cookie cookie = new Cookie("location", "vietnam");
				response.addCookie(cookie);
				HttpSession session = request.getSession();
				session.isNew();
				session.setMaxInactiveInterval(900); //using millisecond
				session.setAttribute("username", username);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/ImageShow");
				Image image = new Image("img", 200, 300);
 				request.setAttribute("imagename", image); 
 				
 				session.setAttribute("check", new Image("Testing image", 200, 300));
 				session.removeAttribute("check");
 				
				dispatcher.forward(request, response);
			} else {
				response.sendRedirect("login.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
