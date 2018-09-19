package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.CheckLoginLibrary;
import model.bean.User;
import model.dao.NewsDAO;
import model.dao.UsersDAO;

/**
 * Servlet implementation class DeleteNewsAdminController
 */
public class DeleteNewsAdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteNewsAdminController() {
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
		
		NewsDAO newsDAO = new NewsDAO();
		int idNews = Integer.parseInt(request.getParameter("nid"));
		
		response.setContentType("text/html;charset=UTF-8");
		final String path = request.getServletContext().getRealPath("files");
		String picture_old = newsDAO.getItem(idNews).getPicture();
		if(!"".equals(picture_old)){
			String urlDelFile = path+File.separator+picture_old;
			File delFile = new File(urlDelFile);
			delFile.delete();
		}
		
		if(newsDAO.delItem(idNews)>0){
			response.sendRedirect(request.getContextPath()+"/admin/indexNews?msg=3");
			return;
		} else {
			response.sendRedirect(request.getContextPath()+"/admin/indexNews?msg=0");
			return;
		}
	}

}
