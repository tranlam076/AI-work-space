package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Author;
import model.bean.Submission;
import model.dao.AuthorDAO;
import model.dao.SubmissionDAO;

/**
 * Servlet implementation class Admin
 */
public class SubmissionAdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int ROW_COUNT = 10;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SubmissionAdminController() {
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

			String idSub = request.getParameter("idSubmission");
			String requestPage = request.getParameter("requestPage");

			SubmissionDAO subDAO = new SubmissionDAO();
			int count = subDAO.countItems();
			int pages = count / ROW_COUNT;
			if (count % ROW_COUNT > 0) {
				pages += 1;
			}
			request.setAttribute("totalPage", pages);
			if (idSub != null) {
				Submission sub = subDAO.getItem(idSub);
				request.setAttribute("submission", sub);
				
				AuthorDAO auDAO = new AuthorDAO();
				ArrayList<Author> listAuthors = auDAO.getItems(sub.getIdSubmission());
				request.setAttribute("listAuthors", listAuthors);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/submission-detail.jsp");
				dispatcher.forward(request, response);
				
			} else if (requestPage != null) {
				int reqPage = Integer.parseInt(requestPage);
				ArrayList<Submission> listSubs = subDAO.getItemsPagition((reqPage - 1) * ROW_COUNT, ROW_COUNT);
				request.setAttribute("listSubs", listSubs);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("/admin/submission-manager.jsp?currentPage=" + reqPage);
				dispatcher.forward(request, response);
				return;
			} else {
				ArrayList<Submission> listSubs = subDAO.getItems();
				request.setAttribute("listSubs", listSubs);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("/admin/submission-manager.jsp?currentPage=1");
				dispatcher.forward(request, response);
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.sendRedirect(request.getContextPath() + "/admin/index");
		return;
	}
}
