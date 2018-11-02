package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Category;
import model.bean.News;
import model.dao.CatDAO;
import model.dao.NewsDAO;

/**
 * Servlet implementation class AddNewsAdminController
 */
public class AddNewsAdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddNewsAdminController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @throws IOException
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			PrintWriter writer = response.getWriter();
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String id_cat = request.getParameter("idCat");
			String nameCat = "";
			CatDAO cat = new CatDAO();
			Category category = cat.getItem(id_cat);
			nameCat = category.getName();

			News objNews = new News("", title, content, id_cat, nameCat);
			NewsDAO news = new NewsDAO();
			int rs = news.addItem(objNews);
			if (rs > 0)
				writer.println("success");
			response.sendRedirect(request.getContextPath() + "/admin/index?msg=1");
			return;
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/admin/index?msg=0");
			return;
		}
	}
}
