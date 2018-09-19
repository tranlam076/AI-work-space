package tranlam.listeners;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class WebsiteSession implements HttpSessionListener {
//manage the numbers of client connect to websile
	private static int numberOfSession = 0;

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		se.getSession().getServletContext().log("A new session is created " + ++numberOfSession);
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		se.getSession().getServletContext().log("A new session is destroyed " + numberOfSession);

	}

}
