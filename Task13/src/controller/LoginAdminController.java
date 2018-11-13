package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import library.EncryptLibrary;
import model.DAO.UsersDAO;
import model.bean.User;

public class LoginAdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginAdminController() {
		super();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect(request.getContextPath() + "/login.jsp");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			EncryptLibrary encrypt = new EncryptLibrary();
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			if (username == "" || username == null || password == "" || password == null) {
				response.sendRedirect(request.getContextPath() + "/login.jsp");
			}
			UsersDAO usersDAO = new UsersDAO();
			User userChecking = usersDAO.checkLogin(username);
			if (userChecking != null) {
				if (encrypt.checkHash(password, userChecking.getPassword())) {
					HttpSession session = request.getSession();
					session.setAttribute("userInfo", userChecking);
					session.setMaxInactiveInterval(30 * 60);
					Cookie userName = new Cookie("user", userChecking.getFullname());
					userName.setMaxAge(30 * 60);
					response.addCookie(userName);
					response.sendRedirect(request.getContextPath() + "/admin/index");
				}
			} else {
				response.sendRedirect(request.getContextPath() + "/login.jsp?msg=error&message=wrong name or passowrd");
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}

	}
}