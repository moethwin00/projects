package scm.bulletinboard.system.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

import scm.bulletinboard.system.form.login.LoginForm;
import scm.bulletinboard.system.form.post.PostForm;
import scm.bulletinboard.system.model.User;
import scm.bulletinboard.system.service.UserService;

@Controller
public class LoginController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	private MessageSource messageSource;
	
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public ModelAndView showLoginForm(ModelAndView model) {
		LoginForm loginForm = new LoginForm();
		model.addObject("loginForm", loginForm);
		model.setViewName("login");
		return model;
	}
	
	@RequestMapping(value = "checkLogin", method = RequestMethod.POST)
	public ModelAndView checkLogin(@Validated @ModelAttribute LoginForm loginForm, BindingResult result, HttpSession session) {
		String email = loginForm.getEmail();
		System.out.println(userService.isUserExist(email));
		if(result.hasErrors()) {
			ModelAndView model = new ModelAndView("login");
			return model;
		}
		else if(userService.isUserExist(email)) {
				User user = userService.getUserByEmail(email);
				if(user.getPassword().equals(loginForm.getPassword())) {
					session.setAttribute("LOGIN_USER", user);
					session.setAttribute("loginUserId", user.getId());
					session.setAttribute("loginUserName", user.getName());
					ModelAndView model = new ModelAndView();
					model.addObject("postSearch", new PostForm());
					model.setViewName("redirect:/postlist/");
					return model;
					}
				else {
					ModelAndView model = new ModelAndView();
					model.setViewName("login");
					model.addObject("errorMsg", messageSource.getMessage("MSG_0001", null, null));
					return model;
				}
			}
			else {
				System.out.println("Email or Password is incorrect");
				ModelAndView model = new ModelAndView();
				model.setViewName("login");
				model.addObject("errorMsg", messageSource.getMessage("MSG_0001", null, null));
				return model;
			}
	}
	
	 @RequestMapping(value = "logout", method = RequestMethod.GET)
	    public ModelAndView logout(Model model, HttpSession session) {
	        session.removeAttribute("LOGIN_USER");
	        ModelAndView loginView = new ModelAndView("redirect:/login");

	        return loginView;
	    }
}
