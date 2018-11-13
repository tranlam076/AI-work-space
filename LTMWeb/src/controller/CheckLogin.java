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
		RequestDispatcher dispatcher = request.getRequestDispatcher("Info.jsp");
		HttpSession session = request.getSession();
		String id = request.getParameter("id");
		String password = request.getParameter("pass"); 
		System.out.println(id +  "-" + password);
		UserBO userBO = new UserBO();
		User user = userBO.getUser(id, password);
		if (user != null) {
			out.println("hello :" + user.getId());
			out.println(user.getInfo());
			request.setAttribute("user", user);
			session.setAttribute("user", user);
			dispatcher.forward(request, response);
		} else {
			out.println("user not found");
		}
	}

}
