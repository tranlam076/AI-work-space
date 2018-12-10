package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.BO.UserBO;
import model.bean.User;

@WebServlet("/CheckLogin")
public class CheckLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public CheckLogin() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		RequestDispatcher dispatcher;
		HttpSession session = request.getSession();
		String id = request.getParameter("id");
		String password = request.getParameter("pass"); 
		System.out.println(id +  "-" + password);
		UserBO userBO = new UserBO();
		User userLogin = userBO.getUser(id, password);
		if (userLogin != null) {
			out.println("hello :" + userLogin.getId());
			out.println(userLogin.getInfo());
			request.setAttribute("user", userLogin);
			session.setAttribute("user", userLogin);
			if (userLogin.getRole().equals("admin")) {
//				dispatcher = request.getRequestDispatcher("AdminController.jsp");
//				dispatcher.forward(request, response);
				response.sendRedirect(request.getContextPath() + "/AdminController");
			} else {
				dispatcher  = request.getRequestDispatcher("Info.jsp");
				dispatcher.forward(request, response);
			}
		} else {
			out.println("user not found");
			response.sendRedirect(request.getContextPath() + "/LoginServlet");
		}
	}

}
