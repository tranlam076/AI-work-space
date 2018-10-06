package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.CatDAO;


/**
 * Servlet implementation class AddCatAdminController
 */
public class AddCatAdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCatAdminController() {
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
		String name = new String(request.getParameter("name").getBytes("ISO-8859-1"),"UTF-8");
		try {
			CatDAO cat = new CatDAO();
			int rs = cat.addItem(name);
			if(rs>0) {
				writer.println("success");
			}
			response.sendRedirect(request.getContextPath()+"/admin/index");
			return;
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath()+"/admin/index?msg=0");
			return;
		}
		
	}

}
