package scm.bulletinboard.system.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import scm.bulletinboard.system.model.User;
@PropertySource("classpath:authority.properties")
@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

	  @Autowired
	    private Environment environment;

	    private static final long MAX_INACTIVE_SESSION_TIME = 50 * 10000;

	    public boolean postHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
	            throws Exception {

	        User loginUser = (User) request.getSession().getAttribute("LOGIN_USER");

	        if ((loginUser == null)) {
	        	response.sendRedirect(request.getContextPath() + "/postlist");
	            return true;
	        }

	        String loginUserType = loginUser.getType();
	        if (loginUserType == "0") {
	            
	            return true;
	        }
	        
	        long time = System.currentTimeMillis() - request.getSession().getLastAccessedTime();
	        if (time > MAX_INACTIVE_SESSION_TIME) {
	            response.sendRedirect(request.getContextPath()+ "/logout");
	        }
	        
	        String pathsKey = new String("authorized.path.").concat(loginUserType.toString());
	        String paths = environment.getProperty(pathsKey);
	        String servletPath = request.getServletPath();
	        if (paths.contains(servletPath)) {
	            
	            return true;
	        } else {
	            response.sendRedirect(request.getContextPath() + "/err401");
	            
	            return false;
	        }
	    }
}