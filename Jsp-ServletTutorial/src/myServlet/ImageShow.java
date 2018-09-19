package myServlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tranlam.models.Student;

/**
 * Servlet implementation class ImageShow
 */
@WebServlet("/ImageShow")
public class ImageShow extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImageShow() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doCheck(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doCheck(request, response);
	}

	private void doCheck(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
//		session.invalidate(); //destroy session
		if (username == null) {
			RequestDispatcher dispatcher = request .getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);	
		} else {
			for (Cookie c: request.getCookies()) {
				System.out.println(c.getName());
				System.out.println(c.getValue());
			}
			
			this.getServletContext().setAttribute("otherScope", "Applycation scope");
			
			session.setAttribute("otherScope", "session scope"); // applycation
			request.setAttribute("otherScope", "request scope"); //request 
			Student student = new Student();
			student.setName("Lam");
			request.setAttribute("student", student);
			
			List<String> languages = new ArrayList<>();
			languages.add("java");
			languages.add("js");
			languages.add("c");
			request.setAttribute("languages", languages);
			
			RequestDispatcher dispatcher = request .getRequestDispatcher("somethings.jsp");
			request.setAttribute("imageName", "changed");
			dispatcher.forward(request, response);	
		}
	}

}
