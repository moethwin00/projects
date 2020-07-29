package scm.bulletinboard.system.dao.impl;

import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import scm.bulletinboard.system.dao.PostDAO;
import scm.bulletinboard.system.model.Post;

/**
 * DAO Implementation for Post
 */
@Repository
public class PostDAOImpl implements PostDAO {

	/**
	 * <h2>${Session Factory}</h2>
	 * <p>
	 * ${Declare Session Factory For Database Query Creation}
	 * </p>
	 */
	@Autowired
	SessionFactory sessionFactory;

	/**
	 * <h2>${Get Query Concatination By Authorization}</h2>
	 * <p>
	 * Get Query Concatination for logined User or Guest
	 * </p>
	 *
	 * @return ${String}
	 */
	private String queryConcatByAuthorize(String queryStr, int loginUserId, String userRole) {
		if (userRole.equals("1")) {
			queryStr += " and p.user.id = " + loginUserId;
		}
		if (userRole.equals("")) {
			queryStr += " and p.status = " + 1;
		}
		return queryStr;
	}

	/**
	 * <h2>${Get Post List}</h2>
	 * <p>
	 * Get All Post List
	 * </p>
	 *
	 * @return ${List}
	 */
	@SuppressWarnings("unchecked")
	public List<Post> getAllPosts(int loginUserId, String userRole) {
		String queryStr = "from Post as p where p.deletedUserId = " + null + " and p.deletedAt = " + null;
		queryStr = queryConcatByAuthorize(queryStr, loginUserId, userRole);
		Query query = sessionFactory.getCurrentSession().createQuery(queryStr);
		query.setFirstResult(0);
		query.setMaxResults(7);
		return query.list();
	}

	/**
	 * <h2>${Get Post Count}</h2>
	 * <p>
	 * Get Number of Posts
	 * </p>
	 *
	 * @return ${int}
	 */
	public int getPostCount(int loginUserId, String userRole) {
		String queryStr = "Select COUNT(p.id) from Post as p where p.deletedUserId = " + null + " and p.deletedAt = "
		        + null;
		queryStr = queryConcatByAuthorize(queryStr, loginUserId, userRole);
		Query query = sessionFactory.getCurrentSession().createQuery(queryStr);
		if (query.list().get(0) == null) {
			return 1;
		} else {
			@SuppressWarnings("rawtypes")
			List result = query.list();
			int i = Integer.parseInt(result.get(0).toString());
			return i;
		}
	}

	/**
	 * <h2>${Get Post List By PageId for Pagination}</h2>
	 * <p>
	 * Get Post List By Paginated Page Id
	 * </p>
	 *
	 * @return ${List}
	 */
	@SuppressWarnings("unchecked")
	public List<Post> getPostsByPageId(int pageId, int total, int loginUserId, String userRole) {
		String queryStr = "from Post as p where p.deletedUserId = " + null + " and p.deletedAt = " + null;
		queryStr = queryConcatByAuthorize(queryStr, loginUserId, userRole);
		Query query = sessionFactory.getCurrentSession().createQuery(queryStr);
		query.setFirstResult(pageId - 1);
		query.setMaxResults(total);
		return query.list();
	}

	/**
	 * <h2>${Get Post List By Search Keys}</h2>
	 * <p>
	 * Get Post List that are searched with Post Title Or Post Description by User
	 * </p>
	 *
	 * @return ${List}
	 */
	@SuppressWarnings("unchecked")
	public List<Post> getPostsBySearchkey(String searchKey, int loginUserId, String userRole) {
		String queryStr = "from Post as p where (p.title LIKE '%" + searchKey + "%' or p.description LIKE '%"
		        + searchKey + "%') and p.deletedUserId = " + null + " and p.deletedAt = " + null;
		queryStr = queryConcatByAuthorize(queryStr, loginUserId, userRole);
		Query query = sessionFactory.getCurrentSession().createQuery(queryStr);
		return query.list();
	}

	/**
	 * <h2>${Insert Post}</h2>
	 * <p>
	 * Insert Data of a Post
	 * </p>
	 */
	public void addPost(Post post) {
		sessionFactory.getCurrentSession().saveOrUpdate(post);
	}

	/**
	 * <h2>${Get Post By Page Id}</h2>
	 * <p>
	 * Get Post By Title to Check Title Exits or Not
	 * </p>
	 * 
	 * @return ${Object}
	 */
	public Post getPostsByTitle(String title) {
		Query query = sessionFactory.getCurrentSession().createQuery("from Post as p where p.title = :title");
		query.setParameter("title", title);
		return (Post) query.uniqueResult();
	}

	/**
	 * <h2>${Update Post}</h2>
	 * <p>
	 * Update Data of a Post
	 * </p>
	 * 
	 * @return ${Object}
	 */
	public Post updatePost(Post post) {
		sessionFactory.getCurrentSession().update(post);
		return post;
	}

	/**
	 * <h2>${Delete Post}</h2>
	 * <p>
	 * Soft Delete Data of a Post
	 * </p>
	 */
	public void softDelete(int id, int userId, Date deletedDate) {
		Query query = sessionFactory.getCurrentSession().createQuery(
		        "update Post as p set p.deletedUserId = :userId, p.deletedAt = :deletedDate where p.id = :id");
		query.setParameter("userId", userId);
		query.setParameter("deletedDate", deletedDate);
		query.setParameter("id", id);
		query.executeUpdate();
	}

	/**
	 * <h2>${Update Post}</h2>
	 * <p>
	 * Update Data of a Post
	 * </p>
	 * 
	 * @return ${Object}
	 */
	public Post getPostById(int postId) {
		return (Post) sessionFactory.getCurrentSession().get(Post.class, postId);
	}

	/**
	 * <h2>${Post Title Duplicate or Not}</h2>
	 * <p>
	 * Post Title Duplicate or Not
	 * </p>
	 * 
	 * @return ${Object}
	 */
	@Override
	public Post isDuplicateTitleExist(String title) {
		String postHqlQuery = "SELECT p FROM Post p where p.title = :title";
		Query queryPostByTitle = sessionFactory.getCurrentSession().createQuery(postHqlQuery);
		queryPostByTitle.setParameter("title", title);
		Post resultPost = (Post) queryPostByTitle.uniqueResult();

		return resultPost;
	}

	/**
	 * <h2>${Save Post Data with Upload File}</h2>
	 * <p>
	 * Save Post Data with Upload File
	 * </p>
	 */
	@Override
	public void postUploadData(Post postData) {
		sessionFactory.getCurrentSession().save(postData);
	}
}
