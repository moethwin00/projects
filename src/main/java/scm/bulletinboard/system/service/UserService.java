package scm.bulletinboard.system.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import scm.bulletinboard.system.form.user.UserCreateForm;
import scm.bulletinboard.system.model.User;

public interface UserService {
	public boolean isUserExist(String email);

	public User getUserById(int id);

	public User getUserByEmail(String email);

	public List<User> getAllUsers();

	public int getUserCount();

	public List<User> getUserByPageId(int pageId, int total);

	public List<User> getUsersBySearchkeys(String searchName, String searchEmail, String searchCreatedFrom,
	        String searchCreatedTo);
	
	public Date getDateData();
	
	public void insertUser(UserCreateForm userForm, int loginUserId, String profilePath) throws ParseException, IOException;
	
	public User updateUser(User user);
}
