package scm.bulletinboard.system.controller;

import java.text.ParseException;
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
import org.springframework.web.servlet.ModelAndView;

import scm.bulletinboard.system.form.user.UserCreateForm;
import scm.bulletinboard.system.form.user.UserForm;
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
		if(session.getAttribute("LOGIN_USER") == null) {
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
		if(session.getAttribute("LOGIN_USER") == null) {
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
		if(session.getAttribute("LOGIN_USER") == null) {
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
		view.addObject("offset", offset);
		view.addObject("userLists", userList);
		view.addObject("userCount", count);
	}
//
	@RequestMapping(value = "userlist/createuser", method = RequestMethod.GET)
	public ModelAndView createuser(ModelAndView model, HttpSession session, HttpServletRequest request) {
		if(session.getAttribute("LOGIN_USER") == null) {
			return new ModelAndView("redirect:/login");
		}
		UserCreateForm userCreateForm = new UserCreateForm();
		if (request.getParameter("userEmail") != null) {
			userCreateForm.setName(request.getParameter("userName"));
		}
		model.addObject("userForm", userCreateForm);
		model.addObject("pageTitle", "Create User");
		model.setViewName("createuser");
		return model;
	}
//
	@RequestMapping(value = "/userlist/confirmuser", method = RequestMethod.POST)
	public ModelAndView saveUser(@Validated @ModelAttribute(value = "userForm") UserCreateForm userCreateForm,
	        BindingResult result, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		if(session.getAttribute("LOGIN_USER") == null) {
			return new ModelAndView("redirect:/login");
		}
		if (result.hasErrors() || !userCreateForm.getPassword().equals(userCreateForm.getConfirmPassword())) {
			ModelAndView model = new ModelAndView("createuser");
			if(!userCreateForm.getPassword().equals(userCreateForm.getConfirmPassword())) {
				model.addObject("passwordMismatchError", messageSource.getMessage("MSG_0005", null, null));
			}
			return model;
		}
		return new ModelAndView();
	}
}
//		else {
//			Integer loginUserId = (Integer) request.getSession().getAttribute("loginUserId");
//			Date date = getDate();
//			Post post = addNewPost(postCreateForm, loginUserId, date);
//			ModelAndView model = new ModelAndView("/confirmpost");
//			model.addObject("post", post);
//			return model;
//		}
//
//	private Date getDate() {
//		DateFormat dateFormatter = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
//		Date date = new Date();
//		dateFormatter.format(date);
//		return date;
//	}
//
//	private Post addNewPost(PostCreateForm postCreateForm, Integer loginUserId, Date date) {
//		Post post = new Post();
//		if(postCreateForm.getId() != null) {
//			Post oldPost = postService.getPostById(postCreateForm.getId());
//			post.setId(postCreateForm.getId());
//			post.setCreatedAt(oldPost.getCreatedAt());
//			User oldUser = oldPost.getUser();
//			post.setUser(oldUser);
//			User user = userService.getUserById(loginUserId);
//			post.setUser2(user);
//			post.setUpdatedAt(date);
//		}
//		else {
//			post.setCreatedAt(date);
//			post.setUpdatedAt(date);
//			User user = userService.getUserById(loginUserId);
//			post.setUser(user);
//			post.setUser2(user);
//		}
//		post.setTitle(postCreateForm.getTitle());
//		post.setDescription(postCreateForm.getDescription());
//		if(postCreateForm.isActive()) {
//			post.setStatus(1);
//		}
//		else {
//			post.setStatus(0);
//		}
//		return post;
//	}
//
//	@RequestMapping(value = "/postlist/confirmpost", method = RequestMethod.GET)
//	public ModelAndView confirmPost(@Validated @ModelAttribute(value = "post") Post post, HttpServletRequest request,
//	        HttpSession session) {
//		ModelAndView model = new ModelAndView();
//		if (request.getParameter("errorMsg") != null) {
//			model.addObject("errorMsg", messageSource.getMessage("MSG_0002", null, null));
//			model.addObject("title", request.getParameter("title"));
//			model.addObject("description", request.getParameter("description"));
//		}
//		
//		model.addObject("pageTitle", "Create Post Confirmation");
//		model.addObject("btnText", "Create");
//		model.addObject("post", post);
//		model.setViewName("confirmpost");
//		return model;
//	}
//	
//	@RequestMapping(value = "postlist/editPost") 
//	public ModelAndView editPost(HttpServletRequest request) {
//		int postId = Integer.parseInt(request.getParameter("id"));
//		Post post = postService.getPostById(postId);
//		PostCreateForm postCreateForm = new PostCreateForm();
//		postCreateForm.setId(post.getId());
//		postCreateForm.setTitle(post.getTitle());
//		postCreateForm.setDescription(post.getDescription());
//		boolean active = (post.getStatus() == 1)?true:false;
//		postCreateForm.setActive(active);
//		ModelAndView model = new ModelAndView();
//		model.addObject("postForm", postCreateForm);
//		model.addObject("post", post);
//		model.addObject("pageTitle", "Update Post");
//		model.setViewName("createpost");
//		return model;
//	}
//
//	@RequestMapping(value = "postlist/savePost/{id}/{title}/{description}", method = RequestMethod.GET)
//	public ModelAndView savePost(@PathVariable("id") int id, @PathVariable("title") String title, @PathVariable("description") String description,
//	        HttpSession session, HttpServletRequest request) {
//		PostCreateForm postCreateForm = new PostCreateForm();
//		if(id != 0) {
//			postCreateForm.setId(id);
//		}
//		postCreateForm.setTitle(title);
//		postCreateForm.setDescription(description);
//		Integer loginUserId = (Integer) request.getSession().getAttribute("loginUserId");
//		Date date = getDate();
//		Post post = addNewPost(postCreateForm, loginUserId, date);
//		ModelAndView model = new ModelAndView();
//		if(id == 0) {
//			Post postForCheck = postService.getPostsByTitle(title);
//			if (postForCheck != null) {
//				model = redirectErrorView(post);
//				
//			} else {
//				postService.addPost(post);
//				redirectPostList(model);
//			
//			}
//		}
//		else {
//			Post postForCheck = postService.getPostsByTitle(title);
//			if (postForCheck != null && postForCheck.getId() != post.getId()) {
//				if(postForCheck.getId() != post.getId()) {
//					model = redirectErrorView(post);
//				}
//				else {
//					postService.updatePost(post);
//					redirectPostList(model);
//				}
//				
//			} 
//			else {
//				postService.updatePost(post);
//				redirectPostList(model);
//			}
//		}
//		return model;
//		
//	}
//
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
//	private void redirectPostList(ModelAndView model) {
//		PostForm postForm = new PostForm();
//		model.addObject("postSearch", postForm);
//		model.setViewName("redirect:/postlist/");
//	}
//
//	private ModelAndView redirectErrorView(Post post) {
//		ModelAndView model = new ModelAndView();
//		model.addObject("errorMsg", messageSource.getMessage("MSG_0002", null, null));
//		model.setViewName("redirect:/postlist/confirmpost?id="+post.getId()+"&title="+post.getTitle()+"&description="+post.getDescription());
//		return model;
//	}