package scm.bulletinboard.system.dao.impl;

import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import scm.bulletinboard.system.dao.PostDAO;
import scm.bulletinboard.system.model.Post;

@Repository
public class PostDAOImpl implements PostDAO {

	@Autowired
	SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public List<Post> getAllPosts() {
		Query query = sessionFactory.getCurrentSession().createQuery("from Post as p where p.deletedUserId = "+null+" and p.deletedAt = "+null);
		query.setFirstResult(0);
		query.setMaxResults(7);
		return query.list();
	}

	public int getPostCount() {
		Query query = sessionFactory.getCurrentSession().createQuery("Select COUNT(p.id) from Post as p where p.deletedUserId = "+null+" and p.deletedAt = "+null);
		if (query.list().get(0) == null) {
			return 1;
		} else {
			@SuppressWarnings("rawtypes")
			List result = query.list();
			int i = Integer.parseInt(result.get(0).toString());
			return i;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Post> getPostsByPageId(int pageId, int total) {
		Query query = sessionFactory.getCurrentSession().createQuery("from Post as p where p.deletedUserId = "+null+" and p.deletedAt = "+null);
		query.setFirstResult(pageId - 1);
		query.setMaxResults(total);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<Post> getPostsBySearchkey(String searchKey) {
		Query query = sessionFactory.getCurrentSession().createQuery(
		        "from Post as p where (p.title LIKE '%" + searchKey + "%' or p.description LIKE '%" + searchKey + "%') and p.deletedUserId = "+null+" and p.deletedAt = "+null);
		return query.list();
	}

	public void addPost(Post post) {
		sessionFactory.getCurrentSession().saveOrUpdate(post);
	}

	public Post getPostsByTitle(String title) {
		Query query = sessionFactory.getCurrentSession().createQuery("from Post as p where p.title = :title");
		query.setParameter("title", title);
		return (Post) query.uniqueResult();
	}

	public Post updatePost(Post post) {
		sessionFactory.getCurrentSession().update(post);
		return post;
	}
	
	public void softDelete(int id, int userId, Date deletedDate) {
		Query query = sessionFactory.getCurrentSession().createQuery("update Post as p set p.deletedUserId = :userId, p.deletedAt = :deletedDate where p.id = :id");
		query.setParameter("userId", userId);
		query.setParameter("deletedDate", deletedDate);
		query.setParameter("id", id);
		query.executeUpdate();
	}

	public Post getPostById(int postId) {
		return (Post) sessionFactory.getCurrentSession().get(Post.class, postId);
	}
}
