package scm.bulletinboard.system.dao;

import java.util.Date;
import java.util.List;
import scm.bulletinboard.system.model.Post;

/**
 * <h2>Interface for ${Post DAO Implementation}</h2>
 * <p>
 * Interface for ${Post DAO Implementation}
 * </p>
 * ${tags}
 */
public interface PostDAO {
	public void addPost(Post post);

	public List<Post> getAllPosts(int loginUserId, String userRole);

	public int getPostCount(int loginUserId, String userRole);

	public List<Post> getPostsByPageId(int pageId, int total, int loginUserId, String userRole);

	public List<Post> getPostsBySearchkey(String searchKey, int loginUserId, String userRole);

	public Post getPostsByTitle(String title);

	public Post updatePost(Post post);

	public Post getPostById(int postId);

	public void softDelete(int id, int userId, Date deletedDate);

	public void postUploadData(Post postData);

}
