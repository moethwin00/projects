package scm.bulletinboard.system.dao;

import scm.bulletinboard.system.model.User;

public interface UserDAO {
	public User getUserByEmail(String email);
	public User getUserById(int id);
}
