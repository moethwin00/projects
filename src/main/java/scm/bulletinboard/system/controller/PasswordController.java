package scm.bulletinboard.system.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
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
import scm.bulletinboard.system.form.user.UserForm;
import scm.bulletinboard.system.model.User;
import scm.bulletinboard.system.service.UserService;

@Controller
public class PasswordController {

	@Autowired
	UserService userService;

	@Autowired
	MessageSource messageSource;

	@RequestMapping(value = "userlist/changepassword", method = RequestMethod.GET)
	public ModelAndView showPasswordForm(ModelAndView model, HttpServletRequest request) {
		PasswordForm passwordForm = new PasswordForm();
		passwordForm.setId(Integer.parseInt(request.getParameter("id")));
		model.addObject("password", passwordForm);
		model.setViewName("changepassword");
		return model;
	}

	@RequestMapping(value = "userlist/checkpassword", method = RequestMethod.POST)
	public ModelAndView changePassword(@Validated @ModelAttribute(value = "password") PasswordForm passwordForm,
	        BindingResult result, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		User user = userService.getUserById(passwordForm.getId());
		String password = user.getPassword();
		System.out.println(passwordForm.getOldPassword());
		if (result.hasErrors() || !passwordForm.getNewPassword().equals(passwordForm.getConfirmNewPassword()) || !BCrypt.checkpw(passwordForm.getOldPassword(), password)) {
			ModelAndView model = new ModelAndView();
			if(!passwordForm.getNewPassword().equals(passwordForm.getConfirmNewPassword())) {
				model.addObject("passwordMismatchError", messageSource.getMessage("MSG_0005", null, null));
			}
			if(!BCrypt.checkpw(passwordForm.getOldPassword(), password)) {
				model.addObject("invalidPassword", messageSource.getMessage("MSG_0007", null, null));
			}
			model.setViewName("changepassword");
			return model;
		}
		else {
			ModelAndView model = new ModelAndView();
			userService.updatePassword(passwordForm.getId(), passwordForm.getNewPassword());
			UserForm userForm = new UserForm();
			model.addObject("userSearch", userForm);
			model.setViewName("redirect:/userlist/");
			return model;
		}
	}
}
