package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.User;
import model.dao.UsersDAO;

/**
 * Servlet implementation class AddNewAdminController
 */
public class AddUserAdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddUserAdminController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		String username = new String(request.getParameter("username").getBytes("ISO-8859-1"),"UTF-8");
		String password = new String(request.getParameter("password").getBytes("ISO-8859-1"),"UTF-8");
		String fullname = new String(request.getParameter("fullname").getBytes("ISO-8859-1"),"UTF-8");
		try {
			User objUser = new User("", username, password, fullname);
			UsersDAO u = new UsersDAO();
			int rs = u.addItem(objUser);
			if(rs>0)
			writer.println("hello" + username + password+ fullname);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
