package scm.bulletinboard.system.dao.impl;

import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import scm.bulletinboard.system.dao.UserDAO;
import scm.bulletinboard.system.model.User;

/**
 * DAO Implementation for User
 */
@Repository
public class UserDAOImpl implements UserDAO {

	/**
	 * <h2>${Session Factory}</h2>
	 * <p>
	 * ${Declare Session Factory For Database Query Creation}
	 * </p>
	 */
	@Autowired
	SessionFactory sessionFactory;

	/**
	 * <h2>${Get a User}</h2>
	 * <p>
	 * Get a Record of User By User Email
	 * </p>
	 *
	 * @return ${Object}
	 */
	public User getUserByEmail(String email) {
		Query userHqlQuery = sessionFactory.getCurrentSession().createQuery("from User as u where u.email = :email");
		userHqlQuery.setParameter("email", email);
		User resultUser = (User) userHqlQuery.uniqueResult();
		return resultUser;
	}

	/**
	 * <h2>${Get a User}</h2>
	 * <p>
	 * Get a Record of User By User Id
	 * </p>
	 *
	 * @return ${Object}
	 */
	public User getUserById(int id) {
		return (User) sessionFactory.getCurrentSession().get(User.class, id);
	}

	/**
	 * <h2>${Get All User List}</h2>
	 * <p>
	 * Get All User List
	 * </p>
	 *
	 * @return ${List}
	 */
	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() {
		Query query = sessionFactory.getCurrentSession()
		        .createQuery("from User as u where u.deletedUserId = " + null + " and u.deletedAt = " + null);
		query.setFirstResult(0);
		query.setMaxResults(7);
		return query.list();
	}

	/**
	 * <h2>${Get User Count}</h2>
	 * <p>
	 * Get Number of Users
	 * </p>
	 *
	 * @return ${int}
	 */
	public int getUserCount() {
		Query query = sessionFactory.getCurrentSession().createQuery(
		        "Select COUNT(u.id) from User as u where u.deletedUserId = " + null + " and u.deletedAt = " + null);
		if (query.list().get(0) == null) {
			return 1;
		} else {
			@SuppressWarnings("rawtypes")
			List result = query.list();
			int i = Integer.parseInt(result.get(0).toString());
			return i;
		}
	}

	/**
	 * <h2>${Get User By PageId}</h2>
	 * <p>
	 * Get User By PageId
	 * </p>
	 * 
	 * @param ${pageId, total}
	 * @return ${List}
	 */
	@SuppressWarnings("unchecked")
	public List<User> getUsersByPageId(int pageId, int total) {
		Query query = sessionFactory.getCurrentSession()
		        .createQuery("from User as u where u.deletedUserId = " + null + " and u.deletedAt = " + null);
		query.setFirstResult(pageId - 1);
		query.setMaxResults(total);
		return query.list();
	}

	/**
	 * <h2>${Get User List By Search Keys}</h2>
	 * <p>
	 * Get User List that are searched with Name, Email, Created From, Created To by
	 * User
	 * </p>
	 *
	 * @param ${searchName, searchEmail, searchCreatedFrom, searchCreatedTo}
	 * @return ${List}
	 */
	@SuppressWarnings("unchecked")
	public List<User> getUsersBySearchkeys(String searchName, String searchEmail, String searchCreatedFrom,
	        String searchCreatedTo) {
		String hql = "from User as u where u.name LIKE '%" + searchName + "%' ";
		if (searchEmail != "") {
			hql += "and u.email LIKE '%" + searchEmail + "%'";
		}
		if (searchCreatedFrom != "" && searchCreatedTo == "") {
			hql += "and u.createdAt >= '" + searchCreatedFrom + "'";
		} else if (searchCreatedFrom == "" && searchCreatedTo != "") {
			hql += "and u.createdAt <= '" + searchCreatedTo + "'";
		} else if (searchCreatedFrom != "" && searchCreatedTo != "") {
			hql += "and u.createdAt between '" + searchCreatedFrom + "' and '" + searchCreatedTo + "'";
		}
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	/**
	 * <h2>${Insert User}</h2>
	 * <p>
	 * Insert Data of a User
	 * </p>
	 */
	public void addUser(User user) {
		sessionFactory.getCurrentSession().saveOrUpdate(user);
	}

	/**
	 * <h2>${Update User}</h2>
	 * <p>
	 * Update Data of a User
	 * </p>
	 */
	@Override
	public void editedUser(int id, String name, String email, String type, String phone, Date currentDate,
	        String address, String profile, int loginUserId) {
		String hql = "update User as u set u.name = :name, u.email = :email, u.type = :type, u.phone = :phone, u.updatedAt = :currentDate, u.address = :address, u.updatedUserId = :currentUser";
		if (profile != null) {
			hql += ", u.profile = profile";
		}
		hql += " where u.id = :id";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setInteger("id", id);
		query.setInteger("currentUser", loginUserId);
		query.setString("name", name);
		query.setString("email", email);
		query.setString("type", type);
		query.setString("phone", phone);
		query.setString("address", address);
		query.setDate("currentDate", currentDate);
		query.executeUpdate();
	}

	/**
	 * <h2>${Update Password}</h2>
	 * <p>
	 * Update Data of Password for Password Changing
	 * </p>
	 */
	@Override
	public void updatePassword(int id, String newPassword) {
		String hql = "update User as u set u.password = :password where u.id = :id";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString("password", newPassword);
		query.setInteger("id", id);
		query.executeUpdate();
	}

	/**
	 * <h2>${Delete User}</h2>
	 * <p>
	 * Soft Delete Data of a User
	 * </p>
	 */
	@Override
	public void softDelete(int id, int userId, Date deletedDate) {
		Query query = sessionFactory.getCurrentSession().createQuery(
		        "update User as u set u.deletedUserId = :userId, u.deletedAt = :deletedDate where u.id = :id");
		query.setParameter("userId", userId);
		query.setParameter("deletedDate", deletedDate);
		query.setParameter("id", id);
		query.executeUpdate();
	}

}
