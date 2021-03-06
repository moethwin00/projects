package scm.bulletinboard.system.controller;

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import scm.bulletinboard.system.common.CommonConstant;
import scm.bulletinboard.system.form.login.LoginForm;
import scm.bulletinboard.system.form.post.PostForm;
import scm.bulletinboard.system.model.User;
import scm.bulletinboard.system.service.UserService;

/**
 * Controller for Login
 */
@Controller
public class LoginController {

    /**
     * <h2>Login Service</h2>
     * <p>
     * Declare User Service For Using Service Methods
     * </p>
     */
    @Autowired
    private UserService userService;

    /**
     * <h2>Login Service Error Message</h2>
     * <p>
     * Declare MessageSource For Accessing Messages From Property Resource File
     * </p>
     */
    @Autowired
    private MessageSource messageSource;

    /**
     * <h2>Start Route Calling</h2>
     * <p>
     * $Go To login Route, Show Login Page
     * </p>
     */
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public ModelAndView showLoginForm(ModelAndView model) {
        LoginForm loginForm = new LoginForm();
        model.addObject("loginForm", loginForm);
        model.setViewName("login");
        return model;
    }

    /**
     * <h2>Get Login Data(Email, Password)</h2>
     * <p>
     * Get Login Data(Email, Password) And Go To Post List
     * </p>
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ModelAndView checkLogin(@Validated @ModelAttribute LoginForm loginForm, BindingResult result,
            HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ParseException {
        String email = loginForm.getEmail();
        if (result.hasErrors()) {
            ModelAndView model = new ModelAndView("login");
            return model;
        } else if (userService.isUserExist(email)) {
            User user = userService.getUserByEmail(email);
            String password = loginForm.getPassword();
            if (BCrypt.checkpw(password, user.getPassword())) {
                session.setAttribute(CommonConstant.LOGIN_USER, user);
                ModelAndView model = new ModelAndView();
                model.addObject("postSearch", new PostForm());
                model.setViewName("redirect:/postlist");
                return model;
            } else {
                ModelAndView model = new ModelAndView();
                model.setViewName("login");
                model.addObject("errorMsg", messageSource.getMessage(CommonConstant.MSG_0001, null, null));
                return model;
            }
        } else {
            ModelAndView model = new ModelAndView();
            model.setViewName("login");
            model.addObject("errorMsg", messageSource.getMessage(CommonConstant.MSG_0001, null, null));
            return model;
        }
    }

    /**
     * <h2>User Account Logout</h2>
     * <p>
     * User Account Logging Out And Go To Login Page
     * </p>
     */
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public ModelAndView logout(Model model, HttpSession session, HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        req.getSession().removeAttribute(CommonConstant.LOGIN_USER);
        req.getSession().invalidate();
        ModelAndView loginView = new ModelAndView();
        loginView.setViewName("redirect:postlist");
        return loginView;
    }
}
