package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Field;
import model.dao.FieldDAO;

/**
 * Servlet implementation class PaperSubmissionController
 */
public class ShowPaperSubmissionController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShowPaperSubmissionController() {
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
			FieldDAO field = new FieldDAO();
			ArrayList<Field> listFields = field.getItems();
			request.setAttribute("listFields", listFields);

			request.setAttribute("listFields", listFields);
			String message =(String) request.getAttribute("messsage");
			System.out.println("error here: " + message);
			
			String msg =(String) request.getParameter("msg");
			System.out.println("error here: " + msg);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/conferences/paper-submission.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

}
