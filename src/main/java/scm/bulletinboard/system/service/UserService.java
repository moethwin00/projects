package scm.bulletinboard.system.service;

import java.util.List;

import scm.bulletinboard.system.model.User;

public interface UserService {
	public boolean isUserExist(String email);

	public User getUserById(int id);

	public User getUserByEmail(String email);

	public List<User> getAllUsers();

	public int getUserCount();
}
