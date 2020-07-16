package scm.bulletinboard.system.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import scm.bulletinboard.system.dao.UserDAO;
import scm.bulletinboard.system.form.user.UserCreateForm;
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

	public List<User> getUserByPageId(int pageId, int total) {
		return userDAO.getPostsByPageId(pageId, total);
	}
	
	@Transactional
	public List<User> getUsersBySearchkeys(String searchName, String searchEmail, String searchCreatedFrom,
	        String searchCreatedTo) {
		return userDAO.getUsersBySearchkeys(searchName, searchEmail, searchCreatedFrom,
		        searchCreatedTo);
	}

	public Date getDateData() {
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		dateFormatter.format(date);
		return date;
	}

	public User updateUser(User user) {
		return userDAO.updateUser(user);
	}

	public void insertUser(UserCreateForm userForm, int loginUserId, String userProfilePath) throws ParseException, IOException {
		 	Date currentDate = getDateData();
	        String imageBase64 = userForm.getProfile();
	        if(!imageBase64.isEmpty() && !imageBase64.equals("") && !imageBase64.equals(null)) {
	            
	            String[] block = imageBase64.split(",");
	            String realData = block[1];
	            
	            byte[] data = Base64.decodeBase64(realData);
	            File imgFile=new File(userProfilePath, userForm.getEmail()+".jpg");
	            System.out.println(imgFile.getAbsolutePath());
	            System.out.println(imgFile.exists());
	            if(!imgFile.exists()){
	            imgFile.createNewFile();
	            }
	            try (FileOutputStream stream = new FileOutputStream(imgFile)) {
	                stream.write(data);
	                System.out.println(data+"*****");
	            }
	            userForm.setProfile(userProfilePath);
	        }
	     
	        String  name = userForm.getName();
	        String email = userForm.getEmail();
	        String password = userForm.getPassword();
	        System.out.println(password+"*****");
	        String profile = userForm.getProfile();
	        int type = userForm.getType();
	        String phone = userForm.getPhone();
	        String address = userForm.getAddress();
	        String dob = userForm.getDob();
	        Date createdAt = currentDate;
	        Date updatedAt = currentDate;
	        int createUserId = loginUserId;
	        int updatedUserId = loginUserId;
	        User user = new User(name, email, password, profile, type+"", phone, address, dob, createUserId, updatedUserId, createdAt, updatedAt);
	        userDAO.addUser(user);
	}

}
