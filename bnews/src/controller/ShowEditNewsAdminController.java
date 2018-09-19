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
import model.bean.News;
import model.bean.User;
import model.dao.CatDAO;
import model.dao.NewsDAO;
import model.dao.UsersDAO;

/**
 * Servlet implementation class ShowEditCatController
 */
public class ShowEditNewsAdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowEditNewsAdminController() {
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
		
		int idNews = Integer.parseInt(request.getParameter("nid"));
		NewsDAO newsDAO = new NewsDAO();
		News objNews = newsDAO.getItem(idNews);
		request.setAttribute("objNews", objNews);
		
		CatDAO catDAO = new CatDAO();
		ArrayList<Category> listCat = catDAO.getItems();
		request.setAttribute("listCat", listCat);
		
		RequestDispatcher rd = request.getRequestDispatcher("/admin/editNews.jsp");
		rd.forward(request, response);
	}

}
