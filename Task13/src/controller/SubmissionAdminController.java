package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Category;
import model.bean.Submission;
import model.dao.CatDAO;
import model.dao.SubmissionDAO;

/**
 * Servlet implementation class Admin
 */
public class SubmissionAdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubmissionAdminController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Enumeration<String> paramNames = request.getParameterNames();
			if (paramNames.hasMoreElements()) {
				String idSub = request.getParameter("idSubmission");
				SubmissionDAO SubDAO = new SubmissionDAO();
				Submission sub = SubDAO.getItem(idSub);
				request.setAttribute("submission", sub);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/submission-detail.jsp");
				dispatcher.forward(request, response);
			} else {
				SubmissionDAO SubDAO = new SubmissionDAO();
				ArrayList<Submission> listSubs = SubDAO.getItems();
				request.setAttribute("listSubs", listSubs);
				int count  = SubDAO.countItems();
				int pages =count/15;
				if (count%15 >0) {
					pages +=1;
				}
				request.setAttribute("totalPages", pages);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/submission-manager.jsp");
				dispatcher.forward(request, response);
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.sendRedirect(request.getContextPath() + "/admin/index");
	}
}
