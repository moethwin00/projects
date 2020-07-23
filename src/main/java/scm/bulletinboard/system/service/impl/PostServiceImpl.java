package scm.bulletinboard.system.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import scm.bulletinboard.system.dao.PostDAO;
import scm.bulletinboard.system.dao.UserDAO;
import scm.bulletinboard.system.form.post.PostCreateForm;
import scm.bulletinboard.system.model.Post;
import scm.bulletinboard.system.model.User;
import scm.bulletinboard.system.service.PostService;
import scm.bulletinboard.system.service.UserService;

/**
 * Service Implementation For Post
 */
@Service
@Transactional
public class PostServiceImpl implements PostService {

	/**
	 * <h2>${Post DAO}</h2>
	 * <p>
	 * ${Declare Post DAO For Using DAO Methods}
	 * </p>
	 */
	@Autowired
	PostDAO postDAO;

	/**
	 * <h2>${User DAO}</h2>
	 * <p>
	 * ${Declare User DAO For Using DAO Methods}
	 * </p>
	 */
	@Autowired
	UserDAO userDAO;

	/**
	 * <h2>${User Service}</h2>
	 * <p>
	 * ${Declare User Service For Using Services Methods}
	 * </p>
	 */
	@Autowired
	UserService userService;

	/**
	 * <h2>${Get All Posts}</h2>
	 * <p>
	 * Get All Posts
	 * </p>
	 * 
	 * @return ${List}
	 */
	@Transactional
	public List<Post> getAllPosts() {
		return postDAO.getAllPosts();
	}

	/**
	 * <h2>${Get Number Of Posts}</h2>
	 * <p>
	 * Get Number Of Posts
	 * </p>
	 * 
	 * @return ${int}
	 */
	public int getPostCount() {
		return postDAO.getPostCount();
	}

	/**
	 * <h2>${Get Posts By Pagination Number}</h2>
	 * <p>
	 * Get Posts By Pagination Number
	 * </p>
	 * 
	 * @param ${pageId, total}
	 * @return ${List}
	 */
	public List<Post> getPostsByPageId(int pageId, int total) {
		return postDAO.getPostsByPageId(pageId, total);
	}

	/**
	 * <h2>${Get Posts By Search Keys}</h2>
	 * <p>
	 * Get Posts By Search Keys
	 * </p>
	 * 
	 * @param ${searchKey}
	 * @return ${List}
	 */
	public List<Post> getPostsBySearchkey(String searchKey) {
		return postDAO.getPostsBySearchkey(searchKey);
	}

	/**
	 * <h2>${Add Post}</h2>
	 * <p>
	 * Add Post
	 * </p>
	 * 
	 * @param ${post}
	 */
	public void addPost(Post post) {
		postDAO.addPost(post);
	}

	/**
	 * <h2>${Get Post By Title}</h2>
	 * <p>
	 * Get Post By Title
	 * </p>
	 * 
	 * @param ${title}
	 * @return ${List}
	 */
	public Post getPostsByTitle(String title) {
		return postDAO.getPostsByTitle(title);
	}

	/**
	 * <h2>${Update Post}</h2>
	 * <p>
	 * Update Post
	 * </p>
	 * 
	 * @param ${post}
	 * @return ${Object}
	 */
	public Post updatePost(Post post) {
		return postDAO.updatePost(post);
	}

	/**
	 * <h2>${Get Post By Id}</h2>
	 * <p>
	 * Get Post By Id
	 * </p>
	 * 
	 * @param ${postId}
	 * @return ${Object}
	 */
	public Post getPostById(int postId) {
		return postDAO.getPostById(postId);
	}

	/**
	 * <h2>${Soft Delete Post}</h2>
	 * <p>
	 * Soft Delete Post
	 * </p>
	 * 
	 * @param ${id, userId, deletedDate}
	 */
	public void softDelete(int id, int userId, Date deletedDate) {
		postDAO.softDelete(id, userId, deletedDate);
	}

	/**
	 * <h2>${Add New Post Method}</h2>
	 * <p>
	 * Add New Post
	 * </p>
	 * 
	 * @param ${postCreateForm, loginUserId, date}
	 * @return ${Object}
	 */
	@Override
	public Post addNewPost(PostCreateForm postCreateForm, Integer loginUserId, Date date) {
		Post post = new Post();
		if (postCreateForm.getId() != null) {
			Post oldPost = this.getPostById(postCreateForm.getId());
			post.setId(postCreateForm.getId());
			post.setCreatedAt(oldPost.getCreatedAt());
			User oldUser = oldPost.getUser();
			post.setUser(oldUser);
			User user = userService.getUserById(loginUserId);
			post.setUser2(user);
			if (!postCreateForm.isActive()) {
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

	/**
	 * <h2>${Data Setting into PostCreateForm}</h2>
	 * <p>
	 * Data Setting from Post to PostCreateForm
	 * </p>
	 * 
	 * @param ${post, request}
	 * @return ${Object}
	 */
	@Override
	public PostCreateForm setDataToPostCreateForm(Post post, HttpServletRequest request) {
		PostCreateForm postCreateForm = new PostCreateForm();
		postCreateForm.setId(post.getId());
		postCreateForm.setTitle(post.getTitle());
		postCreateForm.setDescription(post.getDescription());
		boolean active = (post.getStatus() == 1) ? true : false;
		postCreateForm.setActive(active);

		if (request.getParameter("postTitle") != null) {
			postCreateForm.setTitle(request.getParameter("postTitle"));
			postCreateForm.setDescription(request.getParameter("postDescription"));
			postCreateForm.setStatus(Integer.parseInt(request.getParameter("postStatus")));
		}
		return postCreateForm;
	}

	/**
	 * <h2>${Uploading CSV File}</h2>
	 * <p>
	 * CSV file uploading
	 * </p>
	 * 
	 * @param ${uploadFile, loginUserId}
	 * @return ${List}
	 * @throws IOException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<String> uploadCSV(MultipartFile uploadFile, int currentUserId) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(uploadFile.getInputStream()));
		List<String> uploadErrors = new ArrayList<>();
		uploadErrors.add("");
		int lineNumber = 1;
		String[] attributes;
		List<Post> postList = new ArrayList();
		String line = reader.readLine();
		while (line != null) {
			Post post = new Post();
			Date currentDate = new Date();
			attributes = line.split(",");
			if (attributes != null) {
				if (attributes.length == 3) {
					Post resultPostByTitle = this.postDAO.isDuplicateTitleExist(attributes[0]);
					if (!attributes[0].equals("") && !attributes[0].equals(null) && !attributes[0].isEmpty()
					        && resultPostByTitle == null && attributes[2].length() == 1) {
						post.setTitle(attributes[0]);
						post.setDescription(attributes[1]);
						post.setStatus(Integer.parseInt(attributes[2]) != 0 && Integer.parseInt(attributes[2]) != 1 ? 1
						        : Integer.parseInt(attributes[2]));
						post.setUser(userDAO.getUserById(currentUserId));
						post.setUser2(userDAO.getUserById(currentUserId));
						post.setCreatedAt(currentDate);
						post.setUpdatedAt(currentDate);
						postList.add(post);
						System.out.println(post);
					} else {
						if (resultPostByTitle != null) {
							uploadErrors.remove(0);
							uploadErrors.add("Post Title Data always Exist! Error At Row " + lineNumber);
						}
						if (attributes[0].equals(null) || attributes[0].equals("") || attributes[0].isEmpty()) {
							uploadErrors.remove(0);
							uploadErrors.add("Post Title Data is Empty! Error At Row " + lineNumber);
						}
					}
				} else if (attributes.length == 2 || attributes.length == 1 || attributes.length == 0) {
					uploadErrors.remove(0);
					uploadErrors.add("Post Status Data is Empty! Error At Row " + lineNumber);
					if (attributes.length == 0) {
						uploadErrors.remove(0);
						uploadErrors.add("Post Title Data is Empty! Error At Row " + lineNumber);
					}
				}
				line = reader.readLine();
			}
			++lineNumber;
		}
		for (int i = 0; i < postList.size(); i++) {
			postDAO.postUploadData(postList.get(i));
		}
		return uploadErrors;
	}

	/**
	 * <h2>${Data Setting into PostCreateForm to Save Posts}</h2>
	 * <p>
	 * Data Setting into PostCreateForm to Save Post
	 * </p>
	 * 
	 * @param ${id, title, description, status}
	 */
	@Override
	public PostCreateForm addToSavePost(int id, String title, String description, int status) {
		PostCreateForm postCreateForm = new PostCreateForm();
		if (id != 0) {
			postCreateForm.setId(id);
		}
		postCreateForm.setTitle(title);
		postCreateForm.setDescription(description);
		boolean active = (status == 1) ? true : false;
		postCreateForm.setActive(active);
		return postCreateForm;

	}
}
