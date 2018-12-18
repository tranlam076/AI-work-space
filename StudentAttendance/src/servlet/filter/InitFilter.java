package servlet.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.service.UserService;

@WebFilter(urlPatterns = { "/teacher/*", "/admin/*", "/student/*" })
public class InitFilter implements Filter {

	public InitFilter() {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String token = extractCookie(req, "token");
		if (token == null) {
			res.sendRedirect(req.getContextPath() + "/login");
			return;
		}
		String url = req.getRequestURL().toString();
		UserService userservice = new UserService();
		String role = userservice.check(token);
		if (role == null) {
			res.sendRedirect(req.getContextPath() + "/login");
			return;
		} else {
			if (url.contains(role)) {
				chain.doFilter(req, res);
			} else {
				res.sendRedirect(req.getContextPath() + "/login");
				return;
			}
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

	private String extractCookie(HttpServletRequest req, String cookieName) {
		try {
			for (Cookie c : req.getCookies()) {
				if (c.getName().equals(cookieName))
					return c.getValue();
			}
			return null;
		} catch (Exception e) {
			return null;
		}
	}
}
