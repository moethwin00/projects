package scm.bulletinboard.system.dao;

import java.util.List;

import scm.bulletinboard.system.model.User;

public interface UserDAO {
	public User getUserByEmail(String email);

	public User getUserById(int id);

	public List<User> getAllUsers();

	public int getUserCount();
}
