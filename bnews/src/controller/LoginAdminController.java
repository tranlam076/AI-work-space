package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import library.StringLibrary;
import model.bean.Category;
import model.dao.CatDAO;
import model.dao.UsersDAO;

/**
 * Servlet implementation class AddCatAdminController
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = new String(request.getParameter("username").getBytes("ISO-8859-1"), "UTF-8");
		String password = StringLibrary.md5(new String(request.getParameter("password").getBytes("ISO-8859-1"), "UTF-8"));
		UsersDAO usersDAO = new UsersDAO();
		
		if(usersDAO.checkLogin(username,password)!=null ){
			HttpSession session = request.getSession();
			session.setAttribute("userInfo", usersDAO.checkLogin(username,password));
			response.sendRedirect(request.getContextPath()+"/admin/trang-chu?msg=1");
			return;
		} else {
			response.sendRedirect(request.getContextPath()+"/admin/show-login?msg=1");
			return;
		}
		
	}

}
