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
import model.dao.CatDAO;

/**
 * Servlet implementation class ShowEditCatController
 */
public class ShowEditAdminCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowEditAdminCatController() {
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
		
		int idCat = Integer.parseInt(request.getParameter("cid"));
		CatDAO catDAO = new CatDAO();
		Category objCat = catDAO.getItem(idCat);
		request.setAttribute("objCat", objCat);
		
		RequestDispatcher rd = request.getRequestDispatcher("/admin/editCat.jsp");
		rd.forward(request, response);
	}

}
