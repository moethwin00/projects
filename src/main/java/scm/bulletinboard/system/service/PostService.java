package scm.bulletinboard.system.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import scm.bulletinboard.system.form.post.PostCreateForm;
import scm.bulletinboard.system.model.Post;

/**
 * <h2>Interface for Post Service Implementation</h2>
 * <p>
 * Interface for Post Service Implementation
 * </p>
 */
public interface PostService {

    public void addPost(Post post);

    /**
     * <h2>Get All Posts</h2>
     * <p>
     * Get All Posts
     * </p>
     * 
     * @return List
     */
    public List<Post> getAllPosts(int loginUserId, String userRole);

    /**
     * <h2>Get Number Of Posts</h2>
     * <p>
     * Get Number Of Posts
     * </p>
     * 
     * @return int
     */
    public int getPostCount(int loginUserId, String userRole);

    /**
     * <h2>Get Posts By Pagination Number</h2>
     * <p>
     * Get Posts By Pagination Number
     * </p>
     * 
     * @param pageId, total
     * @return List
     */
    public List<Post> getPostsByPageId(int pageId, int total, int loginUserId, String userRole);

    /**
     * <h2>Get Posts By Search Keys</h2>
     * <p>
     * Get Posts By Search Keys
     * </p>
     * 
     * @param searchKey
     * @return List
     */
    public List<Post> getPostsBySearchkey(String searchKey, int loginUserId, String userRole);

    /**
     * <h2>Get Post By Title</h2>
     * <p>
     * Get Post By Title
     * </p>
     * 
     * @param title
     * @return List
     */
    public Post getPostsByTitle(String title);

    /**
     * <h2>Update Post</h2>
     * <p>
     * Update Post
     * </p>
     * 
     * @param post
     * @return Object
     */
    public Post updatePost(Post post);

    /**
     * <h2>Get Post By Id</h2>
     * <p>
     * Get Post By Id
     * </p>
     * 
     * @param postId
     * @return Object
     */
    public Post getPostById(int postId);

    /**
     * <h2>Soft Delete Post</h2>
     * <p>
     * Soft Delete Post
     * </p>
     * 
     * @param id, userId, deletedDate
     */
    public void softDelete(int id, int userId, Date deletedDate);

    /**
     * <h2>Add New Post Method</h2>
     * <p>
     * Add New Post
     * </p>
     * 
     * @param postCreateForm, loginUserId, date
     * @return Object
     */
    public Post addNewPost(PostCreateForm postCreateForm, Integer loginUserId, Date date);

    /**
     * <h2>Data Setting into PostCreateForm</h2>
     * <p>
     * Data Setting from Post to PostCreateForm
     * </p>
     * 
     * @param post, request
     * @return Object
     */
    public PostCreateForm setDataToPostCreateForm(Post post, HttpServletRequest request);

    /**
     * <h2>Uploading CSV File</h2>
     * <p>
     * CSV file uploading
     * </p>
     * 
     * @param uploadFile, loginUserId
     * @return List
     * @throws IOException
     */
    public List<String> uploadCSV(MultipartFile uploadFile, int loginUserId) throws IOException;

    /**
     * <h2>Data Setting into PostCreateForm to Save Posts</h2>
     * <p>
     * Data Setting into PostCreateForm to Save Post
     * </p>
     * 
     * @param id, title, description, status
     */
    public PostCreateForm addToSavePost(int id, String title, String description, int status);
}