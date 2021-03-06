package scm.bulletinboard.system.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.tomcat.util.codec.binary.Base64;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import scm.bulletinboard.system.common.CommonConstant;
import scm.bulletinboard.system.common.StringUtil;
import scm.bulletinboard.system.dao.UserDAO;
import scm.bulletinboard.system.form.user.UserCreateForm;
import scm.bulletinboard.system.model.User;
import scm.bulletinboard.system.service.UserService;

/**
 * Service Implementation For User
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    /**
     * <h2>User DAO</h2>
     * <p>
     * Declare User DAO For Using DAO Methods
     * </p>
     */
    @Autowired
    UserDAO userDAO;

    /**
     * <h2>User Exist Or Not</h2>
     * <p>
     * User Exist Or Not
     * </p>
     * 
     * @param email
     * @return Boolean
     */
    public boolean isUserExist(String email) {
        boolean userExist = false;
        User user = userDAO.getUserByEmail(email);
        if (user != null) {
            userExist = true;
        }
        return userExist;
    }

    /**
     * <h2>Get User By Email</h2>
     * <p>
     * Get User By Email
     * </p>
     * 
     * @param email
     * @return Object
     */
    public User getUserByEmail(String email) {
        return userDAO.getUserByEmail(email);
    }

    /**
     * <h2>Get User By Id</h2>
     * <p>
     * Get User By Id
     * </p>
     * 
     * @param id
     * @return Object
     */
    public User getUserById(int id) {
        return userDAO.getUserById(id);
    }

    /**
     * <h2>Get All Users</h2>
     * <p>
     * Get All Users
     * </p>
     * 
     * @return List
     */
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    /**
     * <h2>Get Number Of Users</h2>
     * <p>
     * Get Number Of Users
     * </p>
     *
     * @return int
     */
    public int getUserCount() {
        return userDAO.getUserCount();
    }

    /**
     * <h2>Get User By PageId</h2>
     * <p>
     * Get User By PageId
     * </p>
     * 
     * @param pageId, total
     * @return List
     */
    public List<User> getUserByPageId(int pageId, int total) {
        return userDAO.getUsersByPageId(pageId, total);
    }

    /**
     * <h2>Get User By Search Key</h2>
     * <p>
     * Get User By Search Key
     * </p>
     * 
     * @param searchName, searchEmail, searchCreatedFrom, searchCreatedTo
     * @return List
     */
    @Transactional
    public List<User> getUsersBySearchkeys(String searchName, String searchEmail, String searchCreatedFrom,
            String searchCreatedTo) {
        return userDAO.getUsersBySearchkeys(searchName, searchEmail, searchCreatedFrom, searchCreatedTo);
    }

    /**
     * <h2>Get Current Date</h2>
     * <p>
     * Get Current Date
     * </p>
     * 
     * @return Date
     */
    public Date getDateData() {
        DateFormat dateFormatter = new SimpleDateFormat(CommonConstant.DATE_FORMAT);
        Date date = new Date();
        dateFormatter.format(date);
        return date;
    }

    /**
     * <h2>Profile Uploading</h2>
     * <p>
     * To Upload Profile
     * </p>
     * 
     * @param userForm, loginUserId, userProfilePath
     */
    public void uploadProfile(UserCreateForm userForm, int loginUserId, String userProfilePath) throws IOException {
        String imageBase64 = null;
        try {
            imageBase64 = userForm.getProfile();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        if (StringUtil.isNotEmpty(imageBase64)) {

            String[] block = imageBase64.split(",");
            String realData = block[1];

            byte[] data = Base64.decodeBase64(realData);
            File imgFile = new File(userProfilePath, userForm.getEmail() + "profile.jpg");
            if (imgFile.exists()) {
                imgFile.delete();
            }
            if (!imgFile.exists()) {
                imgFile.createNewFile();
            }
            try (FileOutputStream stream = new FileOutputStream(imgFile)) {
                stream.write(data);
            }
            userForm.setProfile("resources/profiles/" + userForm.getEmail() + "profile.jpg");

        }

    }

    /**
     * <h2>Save User</h2>
     * <p>
     * Save User
     * </p>
     * 
     * @param userForm, loginUserId, userProfilePath
     */
    public void insertUser(UserCreateForm userForm, int loginUserId, String userProfilePath)
            throws ParseException, IOException {
        Date currentDate = getDateData();

        uploadProfile(userForm, loginUserId, userProfilePath);
        String name = userForm.getName();
        String email = userForm.getEmail();
        String password = userForm.getPassword();
        String profile = userForm.getProfile();
        int type = userForm.getType();
        String phone = userForm.getPhone();
        String address = userForm.getAddress();
        String dob = userForm.getDob();
        Date createdAt = currentDate;
        Date updatedAt = currentDate;
        int createUserId = loginUserId;
        int updatedUserId = loginUserId;
        User user = new User(name, email, password, profile, type + "", phone, address, dob, createUserId,
                updatedUserId, createdAt, updatedAt);
        userDAO.addUser(user);
    }

    /**
     * <h2>Updating User</h2>
     * <p>
     * Update User
     * </p>
     * 
     * @param userCreateForm, loginUserId, userProfilePath
     * @throws ParseException
     */
    @Override
    public void updateUser(UserCreateForm userCreateForm, User currentUser, String userProfilePath)
            throws IOException, ParseException {
        Date currentDate = getDateData();
        int loginUserId = currentUser.getId();
        uploadProfile(userCreateForm, loginUserId, userProfilePath);
        User userToAdd = addNewUser(userCreateForm, currentUser, currentDate);
        userDAO.editedUser(userToAdd, currentDate, loginUserId);
    }

    /**
     * <h2>Add New User Method</h2>
     * <p>
     * Add New User
     * </p>
     * 
     * @param userCreateForm, loginUserId, date
     * @return Object
     */
    @Override
    public User addNewUser(UserCreateForm userCreateForm, User currentUser, Date date) throws ParseException {
        User user = new User();
        if (userCreateForm.getId() != null) {
            User oldUser = this.getUserById(userCreateForm.getId());
            user.setId(userCreateForm.getId());
            user.setCreatedAt(oldUser.getCreatedAt());
            user.setCreateUserId(oldUser.getCreateUserId());
            user.setUpdatedUserId(currentUser.getId());
            user.setUpdatedAt(date);
        } else {
            user.setCreatedAt(date);
            user.setUpdatedAt(date);
            user.setUpdatedUserId(currentUser.getId());
            user.setCreateUserId(currentUser.getId());
        }
        user.setName(userCreateForm.getName());
        user.setEmail(userCreateForm.getEmail());
        String hashPass = BCrypt.hashpw(userCreateForm.getPassword(), BCrypt.gensalt(12));
        user.setPassword(hashPass);
        user.setType(userCreateForm.getType() + "");
        user.setPhone(userCreateForm.getPhone());
        user.setAddress(userCreateForm.getAddress());
        user.setDob(userCreateForm.getDob());
        return user;
    }

    /**
     * <h2>Data Setting into UserCreateForm</h2>
     * <p>
     * Data Setting from User to UserCreateForm
     * </p>
     * 
     * @param user
     * @return Object
     */
    @Override
    public UserCreateForm setDataToUserCreateForm(User user) {
        UserCreateForm userCreateForm = new UserCreateForm();
        userCreateForm.setId(user.getId());
        userCreateForm.setName(user.getName());
        userCreateForm.setEmail(user.getEmail());
        userCreateForm.setType(Integer.parseInt(user.getType()));
        userCreateForm.setPhone(user.getPhone());
        userCreateForm.setDob(user.getDob());
        userCreateForm.setAddress(user.getAddress());
        userCreateForm.setProfile(user.getProfile());
        return userCreateForm;
    }

    /**
     * <h2>Change Password</h2>
     * <p>
     * Change Password
     * </p>
     * 
     * @param id, newPassword
     */
    @Override
    public void updatePassword(int id, String newPassword) {
        String hashPass = BCrypt.hashpw(newPassword, BCrypt.gensalt(12));
        userDAO.updatePassword(id, hashPass);
    }

    /**
     * <h2>Soft Delete Post</h2>
     * <p>
     * Soft Delete Post
     * </p>
     * 
     * @param id, userId, deletedDate
     */
    @Override
    public void softDelete(int id, int userId, Date deletedDate) {
        userDAO.softDelete(id, userId, deletedDate);

    }

    /**
     * <h2>Set Data To User Method for Update</h2>
     * <p>
     * Set Data To User Object
     * </p>
     * 
     * @param userToUpdate, user, currentDate, loginUserId
     */
    @Override
    public void setDataToUser(User userToUpdate, User user, Date currentDate, int loginUserId) {
        userToUpdate.setName(user.getName());
        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setPassword(user.getPassword());
        userToUpdate.setProfile(user.getProfile());
        userToUpdate.setType(user.getType());
        userToUpdate.setAddress(user.getAddress());
        userToUpdate.setDob(user.getDob());
        userToUpdate.setCreateUserId(user.getCreateUserId());
        userToUpdate.setUpdatedUserId(loginUserId);
        userToUpdate.setCreatedAt(user.getCreatedAt());
        userToUpdate.setUpdatedAt(currentDate);
    }
}