package scm.bulletinboard.system.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	        throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String url = req.getRequestURI();

		resp.setHeader("pragma", "no-cache");
		resp.setHeader("Cache-control", "no-cache, no-store, must-revalidate");
		resp.setHeader("Expires", "0");
		HttpSession session = req.getSession(false);

		// !url.contains("login.html") check if the requested page is login page or not.
		// you can do it a numerous way.
		// but for simpplicity here i do that
		if (session == null && !url.contains("login")) {
			resp.sendRedirect(req.getContextPath() + "/login"); // here goto http://yourdoamin/login.html
			resp.setHeader("message", "Session Timeout."); // you can set your preffered message.
			return; // break filter chain, requested JSP/servlet will not be executed
		}

		chain.doFilter(request, response);

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
