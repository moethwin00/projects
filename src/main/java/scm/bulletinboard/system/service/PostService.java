package scm.bulletinboard.system.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import scm.bulletinboard.system.form.post.PostCreateForm;
import scm.bulletinboard.system.model.Post;

/**
 * <h2>Interface for ${Post Service Implementation}</h2>
 * <p>
 * Interface for ${Post Service Implementation}
 * </p>
 */
public interface PostService {
	public void addPost(Post post);

	public List<Post> getAllPosts();

	public int getPostCount();

	public List<Post> getPostsByPageId(int pageId, int total);

	public List<Post> getPostsBySearchkey(String searchKey);

	public Post getPostsByTitle(String title);
	
	public Post updatePost(Post post);
	
	public Post getPostById(int postId);
	
	public void softDelete(int id, int userId, Date deletedDate);

	public Post addNewPost(PostCreateForm postCreateForm, Integer loginUserId, Date date);

	public PostCreateForm setDataToPostCreateForm(Post post, HttpServletRequest request);

	public List<String> uploadCSV(MultipartFile uploadFile, int loginUserId) throws IOException;

	public PostCreateForm addToSavePost(int id, String title, String description, int status);
	

}
