package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import library.CheckLoginLibrary;
import model.dao.NewsDAO;

/**
 * Servlet implementation class IndexNewsAdminController
 */
public class IndexNewsAdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexNewsAdminController() {
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
		int page = 1;
		NewsDAO newsDAO = new NewsDAO();
		//tổng số tin
		int countNews = newsDAO.countItems();
		int row_count = 5;
		int sumPage = (int)Math.ceil((float)countNews/row_count);
		request.setAttribute("sumPage", sumPage);
		//lấy trang hiện tại
		if(request.getParameter("current_page")!=null){
			page = Integer.parseInt(request.getParameter("current_page"));
		}
		request.setAttribute("page", page);
		//tính offset
		int offset = (page-1)*row_count;
		
		request.setAttribute("listNews", newsDAO.getItemsPagition(offset,row_count));
		
		RequestDispatcher rd = request.getRequestDispatcher("/admin/indexNews.jsp");
		rd.forward(request, response);
	}

}
