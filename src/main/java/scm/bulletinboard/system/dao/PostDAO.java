package scm.bulletinboard.system.dao;

import java.util.Date;
import java.util.List;
import scm.bulletinboard.system.model.Post;

public interface PostDAO {
	public void addPost(Post post);

	public List<Post> getAllPosts();

	public int getPostCount();

	public List<Post> getPostsByPageId(int pageId, int total);

	public List<Post> getPostsBySearchkey(String searchKey);

	public Post getPostsByTitle(String title);

	public Post updatePost(Post post);

	public Post getPostById(int postId);
	
	public void softDelete(int id, int userId, Date deletedDate);
	
	public Post isDuplicateTitleExist(String title);

	public void postUploadData(Post postData);
	
}
