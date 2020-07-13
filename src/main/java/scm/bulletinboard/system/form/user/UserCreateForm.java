package scm.bulletinboard.system.form.user;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import scm.bulletinboard.system.validation.password.Confirm;
import scm.bulletinboard.system.validation.password.Password;
import scm.bulletinboard.system.validation.phone.Phone;


public class UserCreateForm implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Integer id;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@NotEmpty(message = "*Name is required")
	String name;

	@NotEmpty(message = "*Email address is required")
	@Email(message = "*Should be email format")
	String email;
	
	@Password
	String password;
	String confirmPassword;
	int type;

	@NotEmpty(message = "*Phone is required")
	@Phone
	String phone;

	@DateTimeFormat(iso = ISO.DATE)
	@Past(message = "*Date Of Birth should be past")
	Date dob;
	
	String address;
	String profile;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

}
