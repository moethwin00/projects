package scm.bulletinboard.system.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import scm.bulletinboard.system.common.CommonConstant;

@Component
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if (request.getSession().getAttribute(CommonConstant.LOGIN_USER) == null) {
            response.sendRedirect(request.getContextPath() + "/postlist");
            return false;
        } else {
            return true;
        }
    }
}
