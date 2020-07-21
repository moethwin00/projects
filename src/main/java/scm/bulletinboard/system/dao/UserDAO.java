package scm.bulletinboard.system.dao;

import java.util.Date;
import java.util.List;

import scm.bulletinboard.system.model.User;

public interface UserDAO {
	public User getUserByEmail(String email);

	public User getUserById(int id);

	public List<User> getAllUsers();

	public int getUserCount();

	public List<User> getPostsByPageId(int pageId, int total);

	public List<User> getUsersBySearchkeys(String searchName, String searchEmail, String searchCreatedFrom,
	        String searchCreatedTo);

	public void addUser(User user);

	public void editedUser(int id, String name, String email, String type, String phone, Date currentDate, String address,
	        String profile, int loginUserId);
}
