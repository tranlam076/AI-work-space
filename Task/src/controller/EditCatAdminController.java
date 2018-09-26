package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Category;
import model.dao.CatDAO;


/**
 * Servlet implementation class AddCatAdminController
 */
public class EditCatAdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditCatAdminController() {
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
		CatDAO catDAO = new CatDAO();
		String name = request.getParameter("name");
		String id_cat = request.getParameter("id_cat");
		ArrayList<Category> listCat = catDAO.getItems();
		request.setAttribute("listCat", listCat);
		if(catDAO.editItem(id_cat, name)>0){
			writer.println("success");
			response.sendRedirect(request.getContextPath()+"/admin/index?msg=1");
			return;
		}
		else {
			writer.println("fail");
			response.sendRedirect(request.getContextPath()+"/admin/index?msg=0");
			return;
		}
		
	}

}
