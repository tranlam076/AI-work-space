package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.bean.User;
import model.dao.UsersDAO;

/**
 * Servlet implementation class LoginAdminController
 */
public class LoginAdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginAdminController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect(request.getContextPath() + "/login.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if (username == "" || username == null || password == "" || password == null) {
			response.sendRedirect(request.getContextPath() + "/login.jsp");
		}
		UsersDAO usersDAO = new UsersDAO();
		User userChecking = usersDAO.checkLogin(username, password);
		if (userChecking != null) {
			if (username.equals(userChecking.getUsername()) && password.equals(userChecking.getPassword())) {
				HttpSession session = request.getSession();
				session.setAttribute("userInfo", userChecking);
				session.setMaxInactiveInterval(30*60);
				Cookie userName = new Cookie("user", userChecking.getFullname());
				userName.setMaxAge(30 * 60);
				response.addCookie(userName);
				response.sendRedirect(request.getContextPath() + "/admin/index");
			}
		} else {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
	}
}