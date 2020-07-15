package scm.bulletinboard.system.service;

import java.util.Date;
import java.util.List;
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
	
	public void addUser(User user);
	
	public User updateUser(User user);
}
