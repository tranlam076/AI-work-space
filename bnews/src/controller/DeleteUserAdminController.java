package controller;

import java.io.IOException;
import java.util.ArrayList;

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
 * Servlet implementation class DeleteCatAdminController
 */
public class DeleteUserAdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteUserAdminController() {
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
		
		UsersDAO userDAO = new UsersDAO();
		int idUser = Integer.parseInt(request.getParameter("uid"));
		ArrayList<User> listUser = userDAO.getItems();
		request.setAttribute("listUser", listUser);
		User objUser = userDAO.getItem(idUser);
		if("admin".equals(objUser.getUsername())){
			response.sendRedirect(request.getContextPath()+"/admin/indexUsers?msg=4");
			return;
		} else {
			if(userDAO.delItem(idUser)>0){
				response.sendRedirect(request.getContextPath()+"/admin/indexUsers?msg=3");
				return;
			} else {
				response.sendRedirect(request.getContextPath()+"/admin/indexUsers?msg=0");
				return;
			}
		}
		
	}

}
