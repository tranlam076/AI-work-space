package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Category;
import model.bean.News;
import model.dao.CatDAO;
import model.dao.NewsDAO;

/**
 * Servlet implementation class ShowIndexController
 */
public class IndexPublicController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public IndexPublicController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			NewsDAO news = new NewsDAO();
			ArrayList<News> listNews = news.getItems();
			request.setAttribute("listNews", listNews);
			CatDAO cat = new CatDAO();
			ArrayList<Category> listCats = cat.getItems();
			request.setAttribute("listCats", listCats);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			// TODO: handle exception
		}
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
