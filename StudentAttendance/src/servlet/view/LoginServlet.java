package servlet.view;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.service.StudentService;
import data.service.TeacherService;
import data.service.UserService;
import model.Student;
import model.Teacher;
import model.User;
import util.Constants;
import util.CryptoUtils;

@WebServlet(urlPatterns = { "", "/login" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		request.setAttribute("title", "Dang nhap");
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/login.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		UserService userService = new UserService();
		User user = userService.find(username, password);
		System.out.println("User : " + user);
		if (user != null) {
			String token = CryptoUtils.md5(username) + CryptoUtils.hmacSHA256(password);
			Cookie cookie = new Cookie("token", token);
			response.addCookie(cookie);
			session.setAttribute("user", user);
			if (user.getRoleId() != null) {
				switch (user.getRoleId()) {
				case "teacher":
					TeacherService teacherService = new TeacherService();
					Teacher teacher = teacherService.getTeacherByUserId(user.getId());
					try {
						response.addCookie(new Cookie("userLoginId", teacher.getId()));
						response.sendRedirect(Constants.url.TEACHER);
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
				case "student":
					StudentService studentService = new StudentService();
					Student student = studentService.getStudentByUserId(user.getId());
					try {
						response.addCookie(new Cookie("userLoginId", student.getId()));
						response.sendRedirect(Constants.url.STUDENT);
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
				case "admin":
					response.sendRedirect(Constants.url.ADMIN);
					break;
				}
			}
		} else {
			response.sendRedirect(request.getRequestURI());
		}
	}
}
