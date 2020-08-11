package scm.bulletinboard.system.dao;

import java.util.Date;
import java.util.List;

import scm.bulletinboard.system.model.Post;

/**
 * <h2>Interface for Post DAO Implementation</h2>
 * <p>
 * Interface for Post DAO Implementation
 * </p>
 */
public interface PostDAO {

    /**
     * <h2>Insert Post</h2>
     * <p>
     * Insert Data of a Post
     * </p>
     */
    public void addPost(Post post);

    /**
     * <h2>Get Post List</h2>
     * <p>
     * Get All Post List
     * </p>
     * 
     * @return List
     */
    public List<Post> getAllPosts(int loginUserId, String userRole);

    /**
     * <h2>Get Post Count</h2>
     * <p>
     * Get Number of Posts
     * </p>
     *
     * @return int
     */
    public int getPostCount(int loginUserId, String userRole);

    /**
     * <h2>Get Post List By PageId for Pagination</h2>
     * <p>
     * Get Post List By Paginated Page Id
     * </p>
     *
     * @return List
     */
    public List<Post> getPostsByPageId(int pageId, int total, int loginUserId, String userRole);

    /**
     * <h2>Get Post List By Search Keys</h2>
     * <p>
     * Get Post List that are searched with Post Title Or Post Description by User
     * </p>
     *
     * @return List
     */
    public List<Post> getPostsBySearchkey(String searchKey, int loginUserId, String userRole);

    /**
     * <h2>Get Post By Page Id</h2>
     * <p>
     * Get Post By Title to Check Title Exits or Not
     * </p>
     * 
     * @return Object
     */
    public Post getPostsByTitle(String title);

    /**
     * <h2>Update Post</h2>
     * <p>
     * Update Data of a Post
     * </p>
     * 
     * @return Object
     */
    public Post updatePost(Post post);

    /**
     * <h2>Update Post</h2>
     * <p>
     * Update Data of a Post
     * </p>
     * 
     * @return Object
     */
    public Post getPostById(int postId);

    /**
     * <h2>Delete Post</h2>
     * <p>
     * Soft Delete Data of a Post
     * </p>
     */
    public void softDelete(int id, int userId, Date deletedDate);

    /**
     * <h2>Save Post Data with Upload File</h2>
     * <p>
     * Save Post Data with Upload File
     * </p>
     */
    public void postUploadData(Post postData);

}
