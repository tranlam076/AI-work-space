package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DAO.SubmissionDAO;

/**
 * Servlet implementation class DeleteSubmissionAdminController
 */
@WebServlet("/admin/delete-submission")
public class DeleteSubmissionAdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DeleteSubmissionAdminController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			PrintWriter out = response.getWriter();
			Enumeration<String> paramNames = request.getParameterNames();
			ArrayList<String> listIdSubmission = new ArrayList<>();
			SubmissionDAO submissionDAO = new SubmissionDAO();

			while (paramNames.hasMoreElements()) {
				String paramName = (String) paramNames.nextElement();
				String paramValues = "";
				if (paramName.contains("checkbox")) {
					paramValues = paramName.substring(8, paramName.length());
					listIdSubmission.add(paramValues);
				}
			}
			if (listIdSubmission.size() > 0) {
				submissionDAO.delItems(listIdSubmission);
				response.sendRedirect(request.getContextPath() + "/admin/submissions?msg=success");
			}
			out.println("list id null");
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath() + "/admin/submissions?msg=error&message=" + e.getMessage());
		}
	}

}
