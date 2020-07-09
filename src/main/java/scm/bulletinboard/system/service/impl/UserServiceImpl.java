package scm.bulletinboard.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import scm.bulletinboard.system.dao.UserDAO;
import scm.bulletinboard.system.model.User;
import scm.bulletinboard.system.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDAO userDAO;
	
	public boolean isUserExist(String email) {
		boolean userExist = false;
		User user = userDAO.getUserByEmail(email);
		System.out.println(user);
		if(user != null) {
			userExist = true;
		}
		return userExist;
	}

	public User getUserByEmail(String email) {
		return userDAO.getUserByEmail(email);
	}

	public User getUserById(int id) {
		return userDAO.getUserById(id);
	}

	public List<User> getAllUsers() {
		return userDAO.getAllUsers();
	}

	public int getUserCount() {
		return userDAO.getUserCount();
	}

}
