package scm.bulletinboard.system.dao;

import java.util.Date;
import java.util.List;

import scm.bulletinboard.system.form.post.PostCreateForm;
import scm.bulletinboard.system.model.Post;

public interface PostDAO {
	public void addPost(Post post);
	public  List<Post> getAllPosts();
	public int getPostCount();
	public List<Post> getPostsByPageId(int pageId, int total);
	public List<Post> getPostsBySearchkey(String searchKey);
}
