package scm.bulletinboard.system.service;

import scm.bulletinboard.system.model.User;

public interface UserService {
	public boolean isUserExist(String email);
	public User getUserById(int id);
	public User getUserByEmail(String email);
}
