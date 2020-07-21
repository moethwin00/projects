package scm.bulletinboard.system.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import scm.bulletinboard.system.form.password.PasswordForm;
import scm.bulletinboard.system.service.UserService;

@Controller
public class PasswordController {

	@Autowired
	UserService userService;

	@Autowired
	MessageSource messageSource;

	@RequestMapping(value = "userlist/changePassword", method = RequestMethod.GET)
	public ModelAndView showPasswordForm(ModelAndView model, HttpServletRequest request) {
		PasswordForm passwordForm = new PasswordForm();
		passwordForm.setId(Integer.parseInt(request.getParameter("id")));
		model.addObject("password", passwordForm);
		model.setViewName("changepassword");
		return model;
	}

	@RequestMapping(value = "userlist/checkPassword", method = RequestMethod.POST)
	public ModelAndView changePassword(@Validated @ModelAttribute("password") PasswordForm passwordForm,
	        BindingResult result, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		int id = passwordForm.getId();
		if (result.hasErrors()) {
			ModelAndView model = new ModelAndView("userlist/changePassword?id="+id);
			return model;
		}
//		System.out.println()
		else {
			return new ModelAndView("../changePassword");
		}
	}
}
