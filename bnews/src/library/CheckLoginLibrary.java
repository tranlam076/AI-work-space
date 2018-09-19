package library;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CheckLoginLibrary {
	public static boolean checkLogin(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		if(session.getAttribute("userInfo")==null){
			try {
				response.sendRedirect(request.getContextPath()+"/admin/show-login");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
		}
		return true;
	}

}
