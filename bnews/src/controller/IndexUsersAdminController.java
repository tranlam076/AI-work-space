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
import model.bean.Category;
import model.bean.User;
import model.dao.CatDAO;
import model.dao.UsersDAO;

/**
 * Servlet implementation class IndexCatAdminController
 */

public class IndexUsersAdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexUsersAdminController() {
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
		
		UsersDAO usersDAO = new UsersDAO();
		ArrayList<User> listUsers = usersDAO.getItems();
		request.setAttribute("listUsers", listUsers);
		
		RequestDispatcher rd = request.getRequestDispatcher("/admin/indexUsers.jsp");
		rd.forward(request, response);
	}

}
