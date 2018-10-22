package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Submission;
import model.dao.SubmissionDAO;

/**
 * Servlet implementation class test
 */
@WebServlet("/test")
public class test extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public test() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter writer = response.getWriter();
//		String name = new String(request.getParameter("name").getBytes("ISO-8859-1"),"UTF-8");
		try {
//			FieldDAO fieldDAO = new FieldDAO();
//			Field f = new Field("","Computational Science");
//			int rs = fieldDAO.addItem(f);
//			if(rs>0) {
//				writer.println("success");
//			}
//			writer.println("fail");
			
			SubmissionDAO submissionDAO = new SubmissionDAO();
			Submission sm = new Submission("","22291d81-c88d-11e8-a0ee-0026b9f396c1","title","discription","keywords","fileNameUpload");
			int rs = submissionDAO.addItem(sm);
			System.out.println("check: ");
			System.out.println(rs);

			writer.println("fail");
						
//			AuthorDAO authorDAO = new AuthorDAO();
//			Author au = new Author("","ed7bfd16-c88d-11e8-a0ee-0026b9f396c1","name","email","country","organization","webPage",true);
//			int rs = authorDAO.addItem(au);
//			if(rs>0) {
//				writer.println("success");
//			}
//			writer.println("fail");
			
//			AuthorDAO author = new AuthorDAO();
//			int rs = author.addItem();
//			if(rs>0) {
//				writer.println("success");
//			}
			return;
		} catch (Exception e) {
			// TODO: handle exception
		}		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
