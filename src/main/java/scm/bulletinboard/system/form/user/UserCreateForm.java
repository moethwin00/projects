package scm.bulletinboard.system.form.user;

import java.io.Serializable;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import scm.bulletinboard.system.validation.password.Password;
import scm.bulletinboard.system.validation.phone.Phone;

/**
 * Form For User Creation
 */
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

	String dob;

	String address;
	String profile;

	/**
	 * <h2>${profile}</h2>
	 * <p>
	 * ${Getter Method For User Profile}
	 * </p>
	 * 
	 * @return ${String}
	 */
	public String getProfile() {
		return profile;
	}

	/**
	 * <h2>${profile}</h2>
	 * <p>
	 * ${Setter Method For User Profile}
	 * </p>
	 * 
	 * @param ${profile}
	 */
	public void setProfile(String profile) {
		this.profile = profile;
	}

	/**
	 * <h2>${phone}</h2>
	 * <p>
	 * ${Getter Method For User Phone}
	 * </p>
	 * 
	 * @return ${String}
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * <h2>${phone}</h2>
	 * <p>
	 * ${Setter Method For User Phone}
	 * </p>
	 * 
	 * @param ${phone}
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * <h2>${id}</h2>
	 * <p>
	 * ${Getter Method For User Id}
	 * </p>
	 * 
	 * @return ${Integer}
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * <h2>${id}</h2>
	 * <p>
	 * ${Setter Method For Post Id}
	 * </p>
	 * 
	 * @param ${id}
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * <h2>${name}</h2>
	 * <p>
	 * ${Getter Method For User Name}
	 * </p>
	 * 
	 * @return ${String}
	 */
	public String getName() {
		return name;
	}

	/**
	 * <h2>${name}</h2>
	 * <p>
	 * ${Setter Method For Post Name}
	 * </p>
	 * 
	 * @param ${name}
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * <h2>${email}</h2>
	 * <p>
	 * ${Getter Method For User Email}
	 * </p>
	 * 
	 * @return ${String}
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * <h2>${email}</h2>
	 * <p>
	 * ${Setter Method For Post Email}
	 * </p>
	 * 
	 * @param ${email}
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * <h2>${password}</h2>
	 * <p>
	 * ${Getter Method For User Password}
	 * </p>
	 * 
	 * @return ${String}
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * <h2>${password}</h2>
	 * <p>
	 * ${Setter Method For User Password}
	 * </p>
	 * 
	 * @param ${password}
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * <h2>${confirmPassword}</h2>
	 * <p>
	 * ${Getter Method For Confirm Password}
	 * </p>
	 * 
	 * @return ${String}
	 */
	public String getConfirmPassword() {
		return confirmPassword;
	}

	/**
	 * <h2>${confirmPassword}</h2>
	 * <p>
	 * ${Setter Method For Confirm Password}
	 * </p>
	 * 
	 * @param ${confirmPassword}
	 */
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	/**
	 * <h2>${type}</h2>
	 * <p>
	 * ${Getter Method For User Type}
	 * </p>
	 * 
	 * @return ${int}
	 */
	public int getType() {
		return type;
	}

	/**
	 * <h2>${type}</h2>
	 * <p>
	 * ${Setter Method For User Type}
	 * </p>
	 * 
	 * @param ${type}
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * <h2>${dob}</h2>
	 * <p>
	 * ${Getter Method For User Date Of Birth}
	 * </p>
	 * 
	 * @return ${String}
	 */
	public String getDob() {
		return dob;
	}

	/**
	 * <h2>${dob}</h2>
	 * <p>
	 * ${Setter Method For User Date Of Birth}
	 * </p>
	 * 
	 * @param ${dob}
	 */
	public void setDob(String dob) {
		this.dob = dob;
	}

	/**
	 * <h2>${address}</h2>
	 * <p>
	 * ${Getter Method For User Address}
	 * </p>
	 * 
	 * @return ${String}
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * <h2>${address}</h2>
	 * <p>
	 * ${Setter Method For User Address}
	 * </p>
	 * 
	 * @param ${id}
	 */
	public void setAddress(String address) {
		this.address = address;
	}
}
