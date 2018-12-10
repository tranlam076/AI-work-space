package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.User;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public LoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		User userLogin = new User();
		if (session!=null) {
			userLogin = (User) session.getAttribute("user");
		}
		if (session != null && userLogin!= null) {
			request.setAttribute("user", userLogin);
			if (userLogin.getRole().equals("admin")) {
				response.sendRedirect(request.getContextPath() + "/AdminController");
			} else {
				RequestDispatcher rd = request.getRequestDispatcher("Info.jsp");
				rd.forward(request, response);
			}
		} else {
			response.sendRedirect("Login.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
