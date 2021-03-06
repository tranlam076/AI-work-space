package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DAO.NewsDAO;
import model.bean.News;

/**
 * Servlet implementation class AddNewsAdminController
 */
public class EditNewsAdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditNewsAdminController() {
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		String title = new String(request.getParameter("title").getBytes("ISO-8859-1"), "UTF-8");
		String content = new String(request.getParameter("password").getBytes("ISO-8859-1"), "UTF-8");
		String id_cat = new String(request.getParameter("id_cat").getBytes("ISO-8859-1"), "UTF-8");
		String nameCat = new String(request.getParameter("nameCat").getBytes("ISO-8859-1"), "UTF-8");
		try {
			News objNews = new News("", title, content, id_cat, nameCat);
			NewsDAO news = new NewsDAO();
			int rs = news.editItem(objNews);
			if (rs > 0) {
				writer.println("success");
				response.sendRedirect(request.getContextPath() + "/admin/index?msg=1");
				return;
			} else {
				writer.println("fail");
				response.sendRedirect(request.getContextPath() + "/admin/index?msg=0");
				return;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
