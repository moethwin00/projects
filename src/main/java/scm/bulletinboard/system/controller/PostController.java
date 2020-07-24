package scm.bulletinboard.system.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.gembox.spreadsheet.SaveOptions;
import com.gembox.spreadsheet.SpreadsheetInfo;
import com.google.common.io.Files;

import scm.bulletinboard.system.form.post.PostCreateForm;
import scm.bulletinboard.system.form.post.PostForm;
import scm.bulletinboard.system.model.Post;
import scm.bulletinboard.system.service.DownloadService;
import scm.bulletinboard.system.service.PostService;
import scm.bulletinboard.system.service.UserService;

/**
 * Controller for Login
 */
@Controller
public class PostController {

	public static final Integer INITIAL_OFFSET = 0;

	/**
	 * <h2>${Post Service}</h2>
	 * <p>
	 * ${Declare Post Service For Using Service Methods}
	 * </p>
	 */
	@Autowired
	PostService postService;

	/**
	 * <h2>${User Service}</h2>
	 * <p>
	 * ${Declare User Service For Using Service Methods}
	 * </p>
	 */
	@Autowired
	UserService userService;

	/**
	 * <h2>${Post Service Error Message}</h2>
	 * <p>
	 * ${Declare MessageSource For Accessing Messages From Property Resource File}
	 * </p>
	 */
	@Autowired
	MessageSource messageSource;

	/**
	 * <h2>${Download Service}</h2>
	 * <p>
	 * ${Declare Download Service For Using Service Methods}
	 * </p>
	 */
	@Autowired
	DownloadService downloadService;

	/**
	 * <h2>${Get Post List}</h2>
	 * <p>
	 * ${Go To postlist Route, Show Post List Page}
	 * </p>
	 */
	@RequestMapping(value = "postlist", method = RequestMethod.GET)
	public ModelAndView showPosts(ModelAndView model, HttpServletResponse response, HttpServletRequest request,
	        HttpSession session) {
		PostForm postForm = new PostForm();
		List<Post> postList;
		int postCount;
		if (request.getParameter("page") != null) {
			int pageId = Integer.parseInt(request.getParameter("page"));
			int total = 7;
			if (pageId == 1) {

			} else {
				pageId = (pageId - 1) * total + 1;
			}

			postList = postService.getPostsByPageId(pageId, total);
		} else {
			postList = postService.getAllPosts();
		}
		postCount = userService.getUserCount();
		int paginationCount = postCount / 7;
		if (paginationCount != 0) {
			++paginationCount;
		}
		model.addObject("postLists", postList);
		model.addObject("title", postForm.getTitle());
		model.addObject("postSearch", postForm);
		model.addObject("paginationCount", paginationCount);
		model.addObject("postCount", postCount);
		model.setViewName("postlist");
		return model;
	}

	/**
	 * <h2>${Calling Post List Route By Search Keys}</h2>
	 * <p>
	 * ${Go To postlist Route, Show Posts List Page By Search Keys}
	 * </p>
	 */
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
	}

	/**
	 * <h2>${Process For Post Searching}</h2>
	 * <p>
	 * ${Process For Post Searching}
	 * </p>
	 */
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
		view.addObject("title", search);
		view.addObject("offset", offset);
		view.addObject("postLists", postList);
		view.addObject("count", count);
	}

	/**
	 * <h2>${Create Post}</h2>
	 * <p>
	 * ${Go To createpost Route, Show Post Creation Form Page}
	 * </p>
	 */
	@RequestMapping(value = "postlist/createpost", method = RequestMethod.GET)
	public ModelAndView createpost(ModelAndView model, HttpSession session, HttpServletRequest request) {
		if (session.getAttribute("LOGIN_USER") == null) {
			return new ModelAndView("redirect:/login");
		}
		PostCreateForm postCreateForm = new PostCreateForm();
		if (request.getParameter("postTitle") != null) {
			postCreateForm.setTitle(request.getParameter("postTitle"));
			postCreateForm.setDescription(request.getParameter("postDescription"));
			postCreateForm.setStatus(Integer.parseInt(request.getParameter("postStatus")));
		}
		model.addObject("postForm", postCreateForm);
		model.addObject("errorMsg", request.getParameter("errorMsg"));
		model.addObject("pageTitle", "Create Post");
		model.setViewName("createpost");
		return model;
	}

	/**
	 * <h2>${Go To Post Create Confirmation}</h2>
	 * <p>
	 * ${Go To confirmpost Route And Show Confirm Post Page}
	 * </p>
	 */
	@RequestMapping(value = "/postlist/confirmpost", method = RequestMethod.POST)
	public ModelAndView savePost(@Validated @ModelAttribute(value = "postForm") PostCreateForm postCreateForm,
	        BindingResult result, HttpSession httpSession, HttpServletRequest request, HttpServletResponse response) {
		if (result.hasErrors()) {
			ModelAndView model = new ModelAndView("createpost");
			if (postCreateForm.getId() == null) {
				model.addObject("pageTitle", "Create Post");
			} else {
				model.addObject("pageTitle", "Update Post");
			}
			return model;
		} else {
			Integer loginUserId = (Integer) request.getSession().getAttribute("loginUserId");
			Date date = userService.getDateData();
			Post post = postService.addNewPost(postCreateForm, loginUserId, date);
			ModelAndView model = new ModelAndView("/confirmpost");
			model.addObject("post", post);
			return model;
		}
	}

	/**
	 * <h2>${Confirm Post}</h2>
	 * <p>
	 * ${Go To confirmpost Route and Create Posts}
	 * </p>
	 */
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

	/**
	 * <h2>${Editing Post}</h2>
	 * <p>
	 * ${Go To editPost Route, Show Post Creation Form Page To Edit}
	 * </p>
	 */
	@RequestMapping(value = "postlist/editPost")
	public ModelAndView editPost(HttpServletRequest request) {
		int postId = Integer.parseInt(request.getParameter("id"));
		Post post = postService.getPostById(postId);
		ModelAndView model = new ModelAndView();
		PostCreateForm postCreateForm = postService.setDataToPostCreateForm(post, request);
		model.addObject("errorMsg", request.getParameter("errorMsg"));
		model.addObject("postForm", postCreateForm);
		model.addObject("post", post);
		model.addObject("pageTitle", "Update Post");
		model.setViewName("createpost");
		return model;
	}

	/**
	 * <h2>${Save Post By Editing Or Creation}</h2>
	 * <p>
	 * ${Go To savePost Route And Post Save to Database And Go To Post List Page}
	 * </p>
	 */
	@RequestMapping(value = "postlist/savePost/{id}/{title}/{description}/{status}", method = RequestMethod.GET)
	public ModelAndView savePost(@PathVariable("id") int id, @PathVariable("title") String title,
	        @PathVariable("description") String description, @PathVariable("status") int status, HttpSession session,
	        HttpServletRequest request) {
		PostCreateForm postCreateForm = postService.addToSavePost(id, title, description, status);
		Integer loginUserId = (Integer) request.getSession().getAttribute("loginUserId");
		Date date = userService.getDateData();
		Post post = postService.addNewPost(postCreateForm, loginUserId, date);
		ModelAndView model = new ModelAndView();
		if (id == 0) {
			Post postForCheck = postService.getPostsByTitle(title);
			if (postForCheck != null) {
				model = redirectErrorView(post);

			} else {
				postService.addPost(post);
				PostForm postForm = new PostForm();
				model.addObject("postSearch", postForm);
				model.setViewName("redirect:/postlist/");

			}
		} else {
			Post postForCheck = postService.getPostsByTitle(title);
			if (postForCheck != null && postForCheck.getId() != post.getId()) {
				if (postForCheck.getId() != post.getId()) {
					model = redirectErrorView(post);
				} else {
					postService.updatePost(post);
					PostForm postForm = new PostForm();
					model.addObject("postSearch", postForm);
					model.setViewName("redirect:/postlist/");
				}

			} else {
				postService.updatePost(post);
				PostForm postForm = new PostForm();
				model.addObject("postSearch", postForm);
				model.setViewName("redirect:/postlist/");
			}
		}
		return model;

	}

	/**
	 * <h2>${Soft Deleting Post}</h2>
	 * <p>
	 * ${Go To deletePost Route}
	 * </p>
	 */
	@RequestMapping(value = "postlist/deletePost")
	public ModelAndView softDelete(ModelAndView model, HttpServletRequest request, HttpSession session) {
		int userId = (Integer) request.getSession().getAttribute("loginUserId");
		Date deletedDate = userService.getDateData();
		postService.softDelete(Integer.parseInt(request.getParameter("id")), userId, deletedDate);
		redirectPostList(model, request);
		return model;
	}

	/**
	 * <h2>${Search and Delete Posts}</h2>
	 * <p>
	 * ${Go To searchDeleteRoute Route}
	 * </p>
	 */
	@RequestMapping(value = "postlist/searchDeletePost")
	public ModelAndView softSearchDelete(ModelAndView model, HttpServletRequest request, HttpSession session)
	        throws ParseException {
		int userId = (Integer) request.getSession().getAttribute("loginUserId");
		Date deletedDate = userService.getDateData();
		postService.softDelete(Integer.parseInt(request.getParameter("id")), userId, deletedDate);
		PostForm postForm = new PostForm();
		model.addObject("postSearch", postForm);
		model.setViewName("redirect:/postlist/");
		return model;
	}

	/**
	 * <h2>${Redirecting Post List Page Method}</h2>
	 * <p>
	 * ${Redirecting Post List}
	 * </p>
	 * 
	 * @param${model, request}
	 */
	private void redirectPostList(ModelAndView model, HttpServletRequest request) {
		PostForm postForm = new PostForm();
		model.addObject("postSearch", postForm);
		String referer = request.getHeader("Referer");
		System.out.println(referer);
		model.setViewName("redirect:" + referer);
	}

	/**
	 * <h2>${Redirecting Post Create Page Method}</h2>
	 * <p>
	 * ${Redirecting Post Create Page with Error Message}
	 * </p>
	 * 
	 * @param${model, request}
	 */
	private ModelAndView redirectErrorView(Post post) {
		ModelAndView model = new ModelAndView();
		model.addObject("errorMsg", messageSource.getMessage("MSG_0002", null, null));
		if (post.getId() == 0) {
			model.setViewName("redirect:/postlist/createpost");
			model.addObject("postTitle", post.getTitle());
			model.addObject("postDescription", post.getDescription());
			model.addObject("postStatus", post.getStatus());
		} else {
			model.setViewName("redirect:/postlist/editPost?id=" + post.getId());
			model.addObject("postTitle", post.getTitle());
			model.addObject("postDescription", post.getDescription());
			model.addObject("postStatus", post.getStatus());
		}
		return model;
	}

	/**
	 * <h2>${CSV Upload Form}</h2>
	 * <p>
	 * ${Go To uploadcsv Route, Show CSV Upload Form Page}
	 * </p>
	 */
	@RequestMapping(value = "postlist/uploadcsv", method = RequestMethod.GET)
	public ModelAndView showUploadCSV(ModelAndView model) {
		model.setViewName("uploadcsv");
		return model;
	}

	/**
	 * <h2>${CSV Uploading}</h2>
	 * <p>
	 * ${Go To uploadCSVFile Route And Do Uploading Process}
	 * </p>
	 */
	@RequestMapping(value = "postlist/uploadCSVFile", method = RequestMethod.POST)
	public ModelAndView uploadCSV(@RequestParam(value = "csvfile") MultipartFile uploadFile, HttpServletRequest request)
	        throws IOException {
		ModelAndView uploadView = new ModelAndView("uploadcsv");
		if (!Files.getFileExtension(uploadFile.getOriginalFilename()).toString().equals("csv")) {
			uploadView.addObject("csvError", messageSource.getMessage("MSG_0008", null, null));
			return uploadView;
		} else {
			int loginUserId = (int) request.getSession().getAttribute("loginUserId");
			List<String> uploadErrors = this.postService.uploadCSV(uploadFile, loginUserId);
			if (uploadErrors.size() != 0) {
				uploadView = new ModelAndView("uploadcsv");
				uploadView.addObject("uploadErrorMsg", uploadErrors);
			} else {
				uploadView = new ModelAndView("redirect:/postlist/");
			}
			return uploadView;
		}
	}

	/**
	 * <h2>${Downloading Posts As Excel Format}</h2>
	 * <p>
	 * ${Go To download Route And Do Downloading Process}
	 * </p>
	 * 
	 * @throws IOException
	 */
	@RequestMapping(value = "postlist/download")
	public HttpEntity<byte[]> download(HttpServletRequest request, HttpServletResponse response) throws IOException {
		SpreadsheetInfo.setLicense("FREE-LIMITED-KEY");
		List<Post> postList = postService.getAllPosts();
		byte[] bytes = downloadService.generateDownload(postList);
		HttpHeaders header = new HttpHeaders();
		header.set(HttpHeaders.CONTENT_TYPE, SaveOptions.getCsvDefault().getContentType());
		header.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=PostList.csv");
		header.setContentLength(bytes.length);
		return new HttpEntity<>(bytes, header);
	}

}