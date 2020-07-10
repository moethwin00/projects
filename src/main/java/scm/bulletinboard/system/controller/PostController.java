package scm.bulletinboard.system.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import org.springframework.web.servlet.ModelAndView;
import scm.bulletinboard.system.form.post.PostCreateForm;
import scm.bulletinboard.system.form.post.PostForm;
import scm.bulletinboard.system.model.Post;
import scm.bulletinboard.system.model.User;
import scm.bulletinboard.system.service.PostService;
import scm.bulletinboard.system.service.UserService;

@Controller
public class PostController {

	public static final Integer INITIAL_OFFSET = 0;

	@Autowired
	PostService postService;

	@Autowired
	UserService userService;

	@Autowired
	MessageSource messageSource;

	@RequestMapping(value = "postlist", method = RequestMethod.GET)
	public ModelAndView showPosts(ModelAndView model) {
//		System.out.println(request.getParameter("q"));
		PostForm postForm = new PostForm();
		List<Post> postList;
		int postCount;
		postList = postService.getAllPosts();
		postCount = postService.getPostCount();
		int paginationCount = postCount / 7;
		model.addObject("postLists", postList);
		model.addObject("postSearch", postForm);
		model.addObject("paginationCount", paginationCount);
		model.addObject("postCount", postCount);
		model.setViewName("postlist");
		return model;
	}

	@RequestMapping(value = "postlist/{pageId}", method = RequestMethod.GET)
	public ModelAndView showPosts(@PathVariable int pageId, ModelAndView model) {
		PostForm postForm = new PostForm();
		int total = 7;
		if (pageId == 1) {

		} else {
			pageId = (pageId - 1) * total + 1;
		}

		List<Post> postList = postService.getPostsByPageId(pageId, total);
		int postCount = postService.getPostCount();
		int paginationCount = postCount / 7;
		model.addObject("postSearch", postForm);
		model.addObject("postLists", postList);
		model.addObject("paginationCount", paginationCount);
		model.addObject("postCount", postCount);
		model.setViewName("postlist");
		return model;
	}

	@RequestMapping(value = "postlist/searchPosts", method = { RequestMethod.POST })
	public ModelAndView searchPosts(@ModelAttribute("postSearch") PostForm postForm, HttpSession session)
	        throws ParseException {
		ModelAndView searchPostView = new ModelAndView("postlist");
		doSearchProcess(searchPostView, INITIAL_OFFSET, true, postForm);

		if (postForm.getTitle() == "") {
			return new ModelAndView("redirect:/postlist/");
		} else {
			return searchPostView;
		}
//	        session.setAttribute("SEARCH_POST_FORM", postForm);
	}

	private void doSearchProcess(ModelAndView view, int offset, Boolean resultSearch, PostForm postForm)
	        throws ParseException {
		String search = postForm.getTitle();
		int count = this.postService.getPostsBySearchkey(search).size();
		List<Post> postList = this.postService.getPostsBySearchkey(search);
		System.out.println(postList);
		if (resultSearch == false && postList.size() == 0) {
			view.addObject("alertMsg", "There is no search result.");
		}
		view.addObject("postSearch", new PostForm());
		view.addObject("offset", offset);
		view.addObject("postLists", postList);
		view.addObject("count", count);
	}

	@RequestMapping(value = "postlist/createpost", method = RequestMethod.GET)
	public ModelAndView createpost(ModelAndView model) {
		PostCreateForm postCreateForm = new PostCreateForm();
		model.addObject("postForm", postCreateForm);
		model.addObject("pageTitle", "Create Post");
		model.setViewName("createpost");
		return model;
	}

	@RequestMapping(value = "/postlist/confirmpost", method = RequestMethod.POST)
	public ModelAndView savePost(@Validated @ModelAttribute(value = "postForm") PostCreateForm postCreateForm,
	        BindingResult result, HttpSession httpSession, HttpServletRequest request, HttpServletResponse response) {
		if (result.hasErrors()) {
			ModelAndView model = new ModelAndView("createpost");
			return model;
		} else {
			Integer loginUserId = (Integer) request.getSession().getAttribute("loginUserId");
			Date date = getDate();
			Post post = addNewPost(postCreateForm, loginUserId, date);
			ModelAndView model = new ModelAndView("/confirmpost");
			model.addObject("post", post);
			return model;
		}
	}

	private Date getDate() {
		DateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		dateFormatter.format(date);
		return date;
	}

	private Post addNewPost(PostCreateForm postCreateForm, Integer loginUserId, Date date) {
		Post post = new Post();
		if (postCreateForm.getId() != null) {
			Post oldPost = postService.getPostById(postCreateForm.getId());
			post.setId(postCreateForm.getId());
			post.setCreatedAt(oldPost.getCreatedAt());
			User oldUser = oldPost.getUser();
			post.setUser(oldUser);
			User user = userService.getUserById(loginUserId);
			post.setUser2(user);
			if (!postCreateForm.isActive()) {
				System.out.println(postCreateForm.isActive() + "********");
				post.setStatus(0);
			}
			if (postCreateForm.isActive()) {
				System.out.println(postCreateForm.isActive());
				post.setStatus(1);
			}
			post.setUpdatedAt(date);
		} else {
			post.setCreatedAt(date);
			post.setUpdatedAt(date);
			User user = userService.getUserById(loginUserId);
			post.setUser(user);
			post.setUser2(user);
			post.setStatus(1);
		}
		post.setTitle(postCreateForm.getTitle());
		post.setDescription(postCreateForm.getDescription());
		return post;
	}

	@RequestMapping(value = "/postlist/confirmpost", method = RequestMethod.GET)
	public ModelAndView confirmPost(@Validated @ModelAttribute(value = "post") Post post, HttpServletRequest request,
	        HttpSession session) {
		ModelAndView model = new ModelAndView();
		if (request.getParameter("errorMsg") != null) {
			model.addObject("errorMsg", messageSource.getMessage("MSG_0002", null, null));
			model.addObject("title", request.getParameter("title"));
			model.addObject("description", request.getParameter("description"));
			model.addObject("active", request.getParameter("active"));
			
		}

		model.addObject("pageTitle", "Create Post Confirmation");
		model.addObject("btnText", "Create");
		model.addObject("post", post);
		model.setViewName("confirmpost");
		return model;
	}

	@RequestMapping(value = "postlist/editPost")
	public ModelAndView editPost(HttpServletRequest request) {
		int postId = Integer.parseInt(request.getParameter("id"));
		Post post = postService.getPostById(postId);
		PostCreateForm postCreateForm = new PostCreateForm();
		postCreateForm.setId(post.getId());
		postCreateForm.setTitle(post.getTitle());
		postCreateForm.setDescription(post.getDescription());
		boolean active = (post.getStatus() == 1) ? true : false;
		postCreateForm.setActive(active);
		ModelAndView model = new ModelAndView();
		model.addObject("postForm", postCreateForm);
		model.addObject("post", post);
		model.addObject("pageTitle", "Update Post");
		model.setViewName("createpost");
		return model;
	}

	@RequestMapping(value = "postlist/savePost/{id}/{title}/{description}/{status}", method = RequestMethod.GET)
	public ModelAndView savePost(@PathVariable("id") int id, @PathVariable("title") String title,
	        @PathVariable("description") String description, @PathVariable("status") int status,
	        HttpSession session, HttpServletRequest request) {
		PostCreateForm postCreateForm = new PostCreateForm();
		if (id != 0) {
			postCreateForm.setId(id);
		}
		postCreateForm.setTitle(title);
		postCreateForm.setDescription(description);
		boolean active = (status==1)?true:false;
		postCreateForm.setActive(active);
		Integer loginUserId = (Integer) request.getSession().getAttribute("loginUserId");
		Date date = getDate();
		Post post = addNewPost(postCreateForm, loginUserId, date);
		ModelAndView model = new ModelAndView();
		if (id == 0) {
			Post postForCheck = postService.getPostsByTitle(title);
			if (postForCheck != null) {
				model = redirectErrorView(post);

			} else {
				postService.addPost(post);
				redirectPostList(model);

			}
		} else {
			Post postForCheck = postService.getPostsByTitle(title);
			if (postForCheck != null && postForCheck.getId() != post.getId()) {
				if (postForCheck.getId() != post.getId()) {
					model = redirectErrorView(post);
				} else {
					postService.updatePost(post);
					redirectPostList(model);
				}

			} else {
				postService.updatePost(post);
				redirectPostList(model);
			}
		}
		return model;

	}

	@RequestMapping(value = "postlist/deletePost")
	public ModelAndView softDelete(ModelAndView model, HttpServletRequest request, HttpSession session) {
		int userId = (Integer) request.getSession().getAttribute("loginUserId");
		Date deletedDate = getDate();
		postService.softDelete(Integer.parseInt(request.getParameter("id")), userId, deletedDate);
		redirectPostList(model);
		return model;
	}

	private void redirectPostList(ModelAndView model) {
		PostForm postForm = new PostForm();
		model.addObject("postSearch", postForm);
		model.setViewName("redirect:/postlist/");
	}

	private ModelAndView redirectErrorView(Post post) {
		ModelAndView model = new ModelAndView();
		model.addObject("errorMsg", messageSource.getMessage("MSG_0002", null, null));
		model.setViewName("redirect:/postlist/confirmpost?id=" + post.getId() + "&title=" + post.getTitle()
		        + "&description=" + post.getDescription());
		return model;
	}

}