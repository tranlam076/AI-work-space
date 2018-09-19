package controller;

import java.io.IOException;

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
 * Servlet implementation class ShowEditCatController
 */
public class ShowEditUserAdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowEditUserAdminController() {
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
		User objUser = userDAO.getItem(idUser);
		request.setAttribute("objUser", objUser);
		
		RequestDispatcher rd = request.getRequestDispatcher("/admin/editUser.jsp");
		rd.forward(request, response);
	}

}
