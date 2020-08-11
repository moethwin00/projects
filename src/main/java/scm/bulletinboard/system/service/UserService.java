package scm.bulletinboard.system.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import scm.bulletinboard.system.form.user.UserCreateForm;
import scm.bulletinboard.system.model.User;

/**
 * <h2>Interface for User Service Implementation</h2>
 * <p>
 * Interface for User Service Implementation
 * </p>
 */
public interface UserService {

    /**
     * <h2>User Exist Or Not</h2>
     * <p>
     * User Exist Or Not
     * </p>
     * 
     * @param email
     * @return Boolean
     */
    public boolean isUserExist(String email);

    /**
     * <h2>Get User By Id</h2>
     * <p>
     * Get User By Id
     * </p>
     * 
     * @param id
     * @return Object
     */
    public User getUserById(int id);

    /**
     * <h2>Get User By Email</h2>
     * <p>
     * Get User By Email
     * </p>
     * 
     * @param email
     * @return Object
     */
    public User getUserByEmail(String email);

    /**
     * <h2>Get All Users</h2>
     * <p>
     * Get All Users
     * </p>
     * 
     * @return List
     */
    public List<User> getAllUsers();

    /**
     * <h2>Get Number Of Users</h2>
     * <p>
     * Get Number Of Users
     * </p>
     *
     * @return int
     */
    public int getUserCount();

    /**
     * <h2>Get User By PageId</h2>
     * <p>
     * Get User By PageId
     * </p>
     * 
     * @param pageId, total
     * @return List
     */
    public List<User> getUserByPageId(int pageId, int total);

    /**
     * <h2>Get User By Search Key</h2>
     * <p>
     * Get User By Search Key
     * </p>
     * 
     * @param searchName, searchEmail, searchCreatedFrom, searchCreatedTo
     * @return List
     */
    public List<User> getUsersBySearchkeys(String searchName, String searchEmail, String searchCreatedFrom,
            String searchCreatedTo);

    /**
     * <h2>Get Current Date</h2>
     * <p>
     * Get Current Date
     * </p>
     * 
     * @return Date
     */
    public Date getDateData();

    /**
     * <h2>Save User</h2>
     * <p>
     * Save User
     * </p>
     * 
     * @param userForm, loginUserId, userProfilePath
     */
    public void insertUser(UserCreateForm userForm, int loginUserId, String profilePath)
            throws ParseException, IOException;

    /**
     * <h2>Updating User</h2>
     * <p>
     * Update User
     * </p>
     * 
     * @param userCreateForm, loginUserId, userProfilePath
     * @throws ParseException
     */
    public void updateUser(UserCreateForm userCreateForm, User user, String userProfilePath)
            throws IOException, ParseException;

    /**
     * <h2>Add New User Method</h2>
     * <p>
     * Add New User
     * </p>
     * 
     * @param userCreateForm, loginUserId, date
     * @return Object
     */
    public User addNewUser(UserCreateForm userCreateForm, User currentUser, Date date) throws ParseException;

    /**
     * <h2>Data Setting into UserCreateForm</h2>
     * <p>
     * Data Setting from User to UserCreateForm
     * </p>
     * 
     * @param user
     * @return Object
     */
    public UserCreateForm setDataToUserCreateForm(User user);

    /**
     * <h2>Change Password</h2>
     * <p>
     * Change Password
     * </p>
     * 
     * @param id, newPassword
     */
    public void updatePassword(int id, String newPassword);

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
     * <h2>Set Data To User Method for Update</h2>
     * <p>
     * Set Data To User Object
     * </p>
     * 
     * @param userToUpdate, user, currentDate, loginUserId
     */
    public void setDataToUser(User userToUpdate, User user, Date currentDate, int loginUserId);
}