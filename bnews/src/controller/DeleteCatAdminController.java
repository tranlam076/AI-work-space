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
import model.dao.CatDAO;

/**
 * Servlet implementation class DeleteCatAdminController
 */
public class DeleteCatAdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteCatAdminController() {
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
		
		CatDAO catDAO = new CatDAO();
		int idCat = Integer.parseInt(request.getParameter("cid"));
		ArrayList<Category> listCat = catDAO.getItems();
		request.setAttribute("listCat", listCat);
		if(catDAO.delItem(idCat)>0){
			response.sendRedirect(request.getContextPath()+"/admin/indexCat?msg=3");
			return;
		} else {
			response.sendRedirect(request.getContextPath()+"/admin/indexCat?msg=0");
			return;
		}
	}

}
