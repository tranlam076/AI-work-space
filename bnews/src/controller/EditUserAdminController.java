package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.CheckLoginLibrary;
import library.StringLibrary;
import model.bean.Category;
import model.bean.User;
import model.dao.CatDAO;
import model.dao.UsersDAO;

/**
 * Servlet implementation class EditCatAdminController
 */
public class EditUserAdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditUserAdminController() {
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
		
		int idUser = Integer.parseInt(request.getParameter("uid"));
		UsersDAO userDAO = new UsersDAO();
		String password = new String(request.getParameter("password").getBytes("ISO-8859-1"),"UTF-8");
		String fullname = new String(request.getParameter("fullname").getBytes("ISO-8859-1"),"UTF-8");
		
		if("".equals(password)){
			password = userDAO.getItem(idUser).getPassword();
		} else {
			password = StringLibrary.md5(new String(request.getParameter("password").getBytes("ISO-8859-1"),"UTF-8"));
			
		}
		User objUser = new User(idUser, null, password, fullname);
		//request.setAttribute("objUser", objUser);
		if(userDAO.editItem(objUser)>0){
			response.sendRedirect(request.getContextPath()+"/admin/indexUsers?msg=2");
			return;
		} else {
			response.sendRedirect(request.getContextPath()+"/admin/indexUsers?msg=0");
			return;
		}
		
	}

}
