package library;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.User;

@WebFilter("/check-jwt.jsp")
public class JwtAuthenticationFilter implements Filter {

	private static final java.util.logging.Logger LOG = Logger.getLogger(JwtAuthenticationFilter.class.getName());

	private static final String AUTH_HEADER_KEY = "Authorization";
	private static final String AUTH_HEADER_VALUE_PREFIX = "Bearer "; // with trailing space to separate token

	private static final int STATUS_CODE_UNAUTHORIZED = 401;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		LOG.info("JwtAuthenticationFilter initialized");
	}

	@Override
	public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		try {
			String token = getBearerToken(req);
			if (token != null && !token.isEmpty()) {
				User user = new JwtLibrary().verifyToken(token);
				if (user != null) {
					LOG.info("Logged in using JWT" + user.getId());
					req.setAttribute("userId", user.getId());
					req.setAttribute("username", user.getUsername());
					filterChain.doFilter(request, response);
				} else {
					LOG.info("can not verify token, go on unauthenticated");
					res.sendRedirect(req.getContextPath() + "/login-with-jwt.jsp");
				}
			} else {
				LOG.info("No JWT provided, go on unauthenticated");
				res.sendRedirect(req.getContextPath() + "/login-with-jwt.jsp");
			}
		} catch (final Exception e) {
			LOG.log(Level.WARNING, "Failed logging in with security token", e);
			HttpServletResponse httpResponse = (HttpServletResponse) response;
			httpResponse.setContentLength(0);
			httpResponse.setStatus(STATUS_CODE_UNAUTHORIZED);
		}
	}

	@Override
	public void destroy() {
		LOG.info("JwtAuthenticationFilter destroyed");
	}

	private String getBearerToken(HttpServletRequest request) {
		String authHeader = request.getHeader(AUTH_HEADER_KEY);
		if (authHeader != null && authHeader.startsWith(AUTH_HEADER_VALUE_PREFIX)) {
			return authHeader.substring(AUTH_HEADER_VALUE_PREFIX.length());
		}
		if (request.getParameter("token") != null) {
			return request.getParameter("token");
		}
		return null;
	}
}