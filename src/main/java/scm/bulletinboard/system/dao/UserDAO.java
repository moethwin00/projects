package scm.bulletinboard.system.dao;

import java.util.Date;
import java.util.List;

import scm.bulletinboard.system.model.User;

/**
 * <h2>Interface for User DAO Implementation</h2>
 * <p>
 * Interface for User DAO Implementation
 * </p>
 */
public interface UserDAO {

    /**
     * <h2>Get a User</h2>
     * <p>
     * Get a Record of User By User Email
     * </p>
     *
     * @return Object
     */
    public User getUserByEmail(String email);

    /**
     * <h2>Get a User</h2>
     * <p>
     * Get a Record of User By User Id
     * </p>
     *
     * @return Object
     */
    public User getUserById(int id);

    /**
     * <h2>Get All User List</h2>
     * <p>
     * Get All User List
     * </p>
     *
     * @return List
     */
    public List<User> getAllUsers();

    /**
     * <h2>Get User Count</h2>
     * <p>
     * Get Number of Users
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
    public List<User> getUsersByPageId(int pageId, int total);

    /**
     * <h2>Get User List By Search Keys</h2>
     * <p>
     * Get User List that are searched with Name, Email, Created From, Created To by
     * User
     * </p>
     *
     * @param searchName, searchEmail, searchCreatedFrom, searchCreatedTo
     * @return List
     */
    public List<User> getUsersBySearchkeys(String searchName, String searchEmail, String searchCreatedFrom,
            String searchCreatedTo);

    /**
     * <h2>Insert User</h2>
     * <p>
     * Insert Data of a User
     * </p>
     */
    public void addUser(User user);

    /**
     * <h2>Update User</h2>
     * <p>
     * Update Data of a User
     * </p>
     */
    public void editedUser(User user, Date currentDate, int loginUserId);

    /**
     * <h2>Update Password</h2>
     * <p>
     * Update Data of Password for Password Changing
     * </p>
     */
    public void updatePassword(int id, String newPassword);

    /**
     * <h2>Delete User</h2>
     * <p>
     * Soft Delete Data of a User
     * </p>
     */
    public void softDelete(int id, int userId, Date deletedDate);
}
