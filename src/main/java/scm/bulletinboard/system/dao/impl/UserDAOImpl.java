package scm.bulletinboard.system.dao.impl;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import scm.bulletinboard.system.dao.UserDAO;
import scm.bulletinboard.system.model.User;

@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	SessionFactory sessionFactory;

	public User getUserByEmail(String email) {
		Query userHqlQuery = sessionFactory.getCurrentSession()
		        .createQuery("Select u from User u where u.email = :email");
		userHqlQuery.setParameter("email", email);
		User resultUser = (User) userHqlQuery.uniqueResult();
		return resultUser;
	}

	public User getUserById(int id) {
		return (User) sessionFactory.getCurrentSession().get(User.class, id);
	}
}
