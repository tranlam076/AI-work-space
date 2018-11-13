package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.EncryptLibrary;
import library.JwtLibrary;
import model.DAO.UsersDAO;
import model.bean.User;

@WebServlet("/login-jwt")
public class LoginAdminControllerJwt extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginAdminControllerJwt() {
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
				response.sendRedirect(request.getContextPath() + "/login-with-jsp.jsp");
			}
			UsersDAO usersDAO = new UsersDAO();
			User userLogin = usersDAO.checkLogin(username);
			if (userLogin.getId() != null) {
				if (encrypt.checkHash(password, userLogin.getPassword())) {
					String token = new JwtLibrary().getToken(userLogin.getId(), userLogin.getUsername());
					System.out.println("created token: " + token);
					response.addHeader("Authorization","Bearer " + token);
					response.sendRedirect(request.getContextPath() + "/check-jwt.jsp");
				}
			} else {
				response.sendRedirect(request.getContextPath() + "/login-with-jwt.jsp?msg=error&message=wrong name or passowrd");
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}
}