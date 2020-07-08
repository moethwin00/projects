package scm.bulletinboard.system.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import scm.bulletinboard.system.dao.PostDAO;
import scm.bulletinboard.system.form.post.PostCreateForm;
import scm.bulletinboard.system.model.Post;
import scm.bulletinboard.system.service.PostService;

@Service
@Transactional
public class PostServiceImpl implements PostService {

	@Autowired
	PostDAO postDAO;
	
	@Transactional
	public List<Post> getAllPosts() {
		return postDAO.getAllPosts();
	}

	public int getPostCount() {
		return postDAO.getPostCount();
	}

	public List<Post> getPostsByPageId(int pageId, int total) {
		return postDAO.getPostsByPageId(pageId, total);
	}

	public List<Post> getPostsBySearchkey(String searchKey) {
		return postDAO.getPostsBySearchkey(searchKey);
	}

	public void addPost(Post post) {
		postDAO.addPost(post);
	}

	public Post getPostsByTitle(String title) {
		return postDAO.getPostsByTitle(title);
	}

	public Post updatePost(Post post) {
		return postDAO.updatePost(post);
	}

	public Post getPostById(int postId) {
		return postDAO.getPostById(postId);
	}
	

}
