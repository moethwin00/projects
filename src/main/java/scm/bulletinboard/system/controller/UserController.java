package scm.bulletinboard.system.controller;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import scm.bulletinboard.system.form.user.UserCreateForm;
import scm.bulletinboard.system.form.user.UserForm;
import scm.bulletinboard.system.model.Post;
import scm.bulletinboard.system.model.User;
import scm.bulletinboard.system.service.UserService;

@Controller
public class UserController {
	public static final Integer INITIAL_OFFSET01 = 0;

	@Autowired
	UserService userService;

	@Autowired
	MessageSource messageSource;

	@RequestMapping(value = "/userlist", method = RequestMethod.GET)
	public ModelAndView showUsers(ModelAndView model, HttpSession session) {
		if (session.getAttribute("LOGIN_USER") == null) {
			return new ModelAndView("redirect:/login");
		}
		UserForm userForm = new UserForm();
		List<User> userList;
		int userCount;
		userList = userService.getAllUsers();
		userCount = userService.getUserCount();
		int paginationCount = userCount / 7;
		model.addObject("userLists", userList);
		model.addObject("userSearch", userForm);
		model.addObject("paginationCount", paginationCount);
		model.addObject("userCount", userCount);
		model.setViewName("userlist");
		return model;
	}

	@RequestMapping(value = "userlist/{pageId}", method = RequestMethod.GET)
	public ModelAndView showUsers(@PathVariable int pageId, ModelAndView model, HttpSession session) {
		if (session.getAttribute("LOGIN_USER") == null) {
			return new ModelAndView("redirect:/login");
		}
		UserForm userForm = new UserForm();
		int total = 7;
		if (pageId == 1) {

		} else {
			pageId = (pageId - 1) * total + 1;
		}

		List<User> userList = userService.getUserByPageId(pageId, total);
		int userCount = userService.getUserCount();
		int paginationCount = userCount / 7;
		model.addObject("userSearch", userForm);
		model.addObject("userLists", userList);
		model.addObject("paginationCount", paginationCount);
		model.addObject("userCount", userCount);
		model.setViewName("userlist");
		return model;
	}

	@RequestMapping(value = "userlist/searchUsers", method = { RequestMethod.POST })
	public ModelAndView searchUsers(@ModelAttribute("uerSearch") UserForm userForm, HttpSession session)
	        throws ParseException {
		if (session.getAttribute("LOGIN_USER") == null) {
			return new ModelAndView("redirect:/login");
		}
		ModelAndView searchUserView = new ModelAndView("userlist");
		doSearchUserProcess(searchUserView, INITIAL_OFFSET01, true, userForm);

		if (userForm.getName() == "" && userForm.getEmail() == "" && userForm.getCreatedFrom() == ""
		        && userForm.getCreatedTo() == "") {
			return new ModelAndView("redirect:/userlist/");
		} else {
			return searchUserView;
		}
	}

	private void doSearchUserProcess(ModelAndView view, int offset, Boolean resultSearch, UserForm userForm)
	        throws ParseException {
		String searchName = userForm.getName();
		String searchEmail = userForm.getEmail();
		String searchCreatedFrom = userForm.getCreatedFrom().toString();
		String searchCreatedTo = userForm.getCreatedTo().toString();
		int count = this.userService.getUsersBySearchkeys(searchName, searchEmail, searchCreatedFrom, searchCreatedTo)
		        .size();
		List<User> userList = this.userService.getUsersBySearchkeys(searchName, searchEmail, searchCreatedFrom,
		        searchCreatedTo);
		System.out.println(userList);
		if (resultSearch == false && userList.size() == 0) {
			view.addObject("alertMsg", "There is no search result.");
		}
		view.addObject("userSearch", new UserForm());
		view.addObject("searchName", searchName);
		view.addObject("searchEmail", searchEmail);
		view.addObject("searchCreatedFrom", searchCreatedFrom);
		view.addObject("searchCreatedTo", searchCreatedTo);
		view.addObject("offset", offset);
		view.addObject("userLists", userList);
		view.addObject("userCount", count);
	}

//
	@RequestMapping(value = "userlist/createuser", method = RequestMethod.GET)
	public ModelAndView createuser(ModelAndView model, HttpSession session, HttpServletRequest request) {
		if (session.getAttribute("LOGIN_USER") == null) {
			return new ModelAndView("redirect:/login");
		}
		UserCreateForm userCreateForm = new UserCreateForm();
		if (request.getParameter("userEmail") != null) {
			User existUser = userService.getUserByEmail(request.getParameter("userEmail"));
			userCreateForm.setId(existUser.getId());
			userCreateForm.setName(existUser.getName());
			userCreateForm.setEmail(existUser.getEmail());
			userCreateForm.setPassword(existUser.getPassword());
			userCreateForm.setType(Integer.parseInt(existUser.getType()));
			userCreateForm.setPhone(existUser.getPhone());
			userCreateForm.setDob(existUser.getDob());
			userCreateForm.setAddress(existUser.getAddress());
			userCreateForm.setProfile(existUser.getProfile());
		}
		model.addObject("userForm", userCreateForm);
		model.addObject("pageTitle", "Create User");
		model.setViewName("createuser");
		return model;
	}

//
	@RequestMapping(value = "/userlist/confirmuser", method = RequestMethod.POST)
	public ModelAndView saveUser(@Validated @ModelAttribute(value = "userForm") UserCreateForm userCreateForm,
	        BindingResult result, HttpSession session, HttpServletRequest request, HttpServletResponse response,
	        @RequestParam("imageData") String imageData) {
		if (session.getAttribute("LOGIN_USER") == null) {
			return new ModelAndView("redirect:/login");
		}
		if (result.hasErrors() || !userCreateForm.getPassword().equals(userCreateForm.getConfirmPassword())) {
			ModelAndView model = new ModelAndView("createuser");
			if (!userCreateForm.getPassword().equals(userCreateForm.getConfirmPassword())) {
				model.addObject("passwordMismatchError", messageSource.getMessage("MSG_0005", null, null));
			}
			if (userCreateForm.getId() == null) {
				model.addObject("pageTitle", "Create User");
			} else {
				model.addObject("pageTitle", "Update User");
			}
			return model;
		} else {
			Integer loginUserId = (Integer) request.getSession().getAttribute("loginUserId");
			Date date = userService.getDateData();
			User user = addNewUser(userCreateForm, loginUserId, date);
			user.setProfile(imageData);
			ModelAndView model = new ModelAndView("/confirmuser");
			model.addObject("user", user);
			return model;

//			String path=session.getServletContext().getRealPath("/");  
//		    String filename=file.getOriginalFilename();  
//		    System.out.println(path+" "+filename);  
//		    try{  
//		        byte barr[]=file.getBytes();  
//		          
//		        BufferedOutputStream bout=new BufferedOutputStream(  
//		                 new FileOutputStream(path+"/"+filename));  
//		        bout.write(barr);  
//		        bout.flush();  
//		        bout.close();  
//		          
//		        }catch(Exception e){System.out.println(e);}  
//			return new ModelAndView();
		}
	}

	private User addNewUser(UserCreateForm userCreateForm, Integer loginUserId, Date date) {
		User user = new User();
		if (userCreateForm.getId() != null) {
			User oldUser = userService.getUserById(userCreateForm.getId());
			user.setId(userCreateForm.getId());
			user.setCreatedAt(oldUser.getCreatedAt());
			user.setCreateUserId(oldUser.getCreateUserId());
			user.setUpdatedUserId(loginUserId);
			user.setUpdatedAt(date);
		} else {
			user.setCreatedAt(date);
			user.setUpdatedAt(date);
			user.setUpdatedUserId(loginUserId);
			user.setCreateUserId(loginUserId);
		}
		user.setName(userCreateForm.getName());
		user.setEmail(userCreateForm.getEmail());
		user.setPassword(userCreateForm.getPassword());
		user.setType(userCreateForm.getType() + "");
		user.setPhone(userCreateForm.getPhone());
		user.setAddress(userCreateForm.getAddress());
		user.setDob(userCreateForm.getDob());
//		user.setProfile(userCreateForm.getProfile());
		return user;
	}

	@RequestMapping(value = "/userlist/confirmuser", method = RequestMethod.GET)
	public ModelAndView confirmUser(@Validated @ModelAttribute(value = "user") User user, HttpServletRequest request,
	        HttpSession session) {
		ModelAndView model = new ModelAndView();
		if (request.getParameter("errorMsg") != null) {
			model.addObject("errorMsg", messageSource.getMessage("MSG_0002", null, null));
			User existUser = userService.getUserByEmail(request.getParameter("email"));
			model.addObject("user", existUser);
		}

		model.addObject("pageTitle", "Create Post Confirmation");
		model.addObject("btnText", "Create");
		model.addObject("user", user);
		model.setViewName("confirmpost");
		return model;
	}

	@RequestMapping(value = "userlist/editUser")
	public ModelAndView editPost(HttpServletRequest request) {
		int userId = Integer.parseInt(request.getParameter("id"));
		User user = userService.getUserById(userId);
		UserCreateForm userCreateForm = new UserCreateForm();
		userCreateForm.setId(user.getId());
		userCreateForm.setName(user.getName());
		userCreateForm.setEmail(user.getEmail());
		userCreateForm.setPassword(user.getPassword());
		userCreateForm.setType(Integer.parseInt(user.getType()));
		userCreateForm.setPhone(user.getPhone());
		userCreateForm.setDob(user.getDob());
		userCreateForm.setAddress(user.getAddress());
		userCreateForm.setProfile(user.getProfile());
		ModelAndView model = new ModelAndView();
		model.addObject("userForm", userCreateForm);
		model.addObject("user", user);
		model.addObject("pageTitle", "Update Post");
		model.setViewName("createpost");
		return model;
	}

	@RequestMapping(value = "userlist/saveUser/{id}/{name}/{email}/{password}/{profile}/{type}/{phone}/{dob}/{address}", method = RequestMethod.GET)
	public ModelAndView savePost(@PathVariable("id") int id, @PathVariable("name") String name,
	        @PathVariable("email") String email, @PathVariable("password") String password,
	        @PathVariable("profile") String profile, @PathVariable("type") String type,
	        @PathVariable("phone") String phone, @PathVariable("dob") Date dob, @PathVariable("address") String address,
	        HttpSession session, HttpServletRequest request, @RequestParam CommonsMultipartFile file) {
		UserCreateForm userCreateForm = new UserCreateForm();
		if (id != 0) {
			userCreateForm.setId(id);
		}

		String path = session.getServletContext().getRealPath("/profiles");
		String filename = file.getOriginalFilename();

		System.out.println(path + " " + filename);
		try {
			byte barr[] = file.getBytes();

			BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(path + "/" + filename));
			bout.write(barr);
			bout.flush();
			bout.close();

		} catch (Exception e) {
			System.out.println(e);
		}

		userCreateForm.setName(name);
		userCreateForm.setEmail(email);
		userCreateForm.setPassword(password);
		userCreateForm.setType(Integer.parseInt(type));
		userCreateForm.setPhone(phone);
		userCreateForm.setDob(dob);
		userCreateForm.setAddress(address);
		userCreateForm.setProfile(profile);
		Integer loginUserId = (Integer) request.getSession().getAttribute("loginUserId");
		Date date = userService.getDateData();
		User user = addNewUser(userCreateForm, loginUserId, date);
		ModelAndView model = new ModelAndView();
		if (id == 0) {
			User userForCheck = userService.getUserByEmail(email);
			if (userForCheck != null) {
				model = redirectErrorView(user);

			} else {
				userService.addUser(user);
				redirectUserList(model);

			}
		} else {
			User userForCheck = userService.getUserByEmail(email);
			if (userForCheck != null && userForCheck.getId() != user.getId()) {
				if (userForCheck.getId() != user.getId()) {
					model = redirectErrorView(user);
				} else {
					userService.updateUser(user);
					redirectUserList(model);
				}

			} else {
				userService.updateUser(user);
				redirectUserList(model);
			}
		}
		return model;
	}

	private void redirectUserList(ModelAndView model) {
		UserForm postForm = new UserForm();
		model.addObject("postSearch", postForm);
		model.setViewName("redirect:/postlist/");
	}

	private ModelAndView redirectErrorView(User user) {
		ModelAndView model = new ModelAndView();
		model.addObject("errorMsg", messageSource.getMessage("MSG_0002", null, null));
		if (user.getId() == 0) {
			model.setViewName("redirect:/userlist/createuser");
			model.addObject("user", user);
		} else {
			model.setViewName("redirect:/userlist/editUser?id=" + user.getId());
			model.addObject("user", user);
		}
		return model;
	}
}

//	@RequestMapping(value = "postlist/deletePost")
//	public ModelAndView softDelete(ModelAndView model, HttpServletRequest request, HttpSession session) {
//		int id = Integer.parseInt(request.getParameter("id"));
//		int userId = (Integer) request.getSession().getAttribute("loginUserId");
//		Date deletedDate = getDate();
//		postService.softDelete(Integer.parseInt(request.getParameter("id")), userId, deletedDate);
//		redirectPostList(model);
//		return model;
//	}
//	
