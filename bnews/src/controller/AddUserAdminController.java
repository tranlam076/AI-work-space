package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.CheckLoginLibrary;
import library.StringLibrary;
import model.bean.User;
import model.dao.UsersDAO;

/**
 * Servlet implementation class AddUserAdminController
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
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!CheckLoginLibrary.checkLogin(request, response)){
			return;
		}
		
		String username = new String(request.getParameter("username").getBytes("ISO-8859-1"),"UTF-8");
		String password = StringLibrary.md5(new String(request.getParameter("password").getBytes("ISO-8859-1"),"UTF-8"));
		String fullname = new String(request.getParameter("fullname").getBytes("ISO-8859-1"),"UTF-8");
		User objUser = new User(0, username, password, fullname);
		request.setAttribute("objUser", objUser);
		UsersDAO userDAO = new UsersDAO();
		if(userDAO.checkUser(username)!=null){
			response.sendRedirect(request.getContextPath()+"/admin/show-addUser?msg=0");
			return;
		} else {
			if(userDAO.addItem(objUser)>0){
				response.sendRedirect(request.getContextPath()+"/admin/indexUsers?msg=1");
				return;
			} else {
				response.sendRedirect(request.getContextPath()+"/admin/indexUsers?msg=0");
				return;
			}
		}
		
		
	}

}
