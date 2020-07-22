package scm.bulletinboard.system.form.password;

import java.io.Serializable;
import javax.validation.constraints.NotEmpty;
import scm.bulletinboard.system.validation.password.Password;

/**
 * Form For Password Changing
 */
public class PasswordForm implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	int id;

	@NotEmpty(message = "*Password is required")
	String oldPassword;

	@Password
	String newPassword;

	String confirmNewPassword;

	public PasswordForm(int id, String oldPassword, String newPassword, String confirmNewPassword) {
		this.id = id;
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
		this.confirmNewPassword = confirmNewPassword;
	}

	public PasswordForm() {
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * <h2>${id}</h2>
	 * <p>
	 * ${Setter Method For User Id}
	 * </p>
	 * 
	 * @param ${id}
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * <h2>${id}</h2>
	 * <p>
	 * ${Getter Method For User Id}
	 * </p>
	 * 
	 * @return ${int}
	 */
	public int getId() {
		return id;
	}

	/**
	 * <h2>${oldPassword}</h2>
	 * <p>
	 * ${Getter Method For User's Old Password}
	 * </p>
	 * 
	 * @return ${String}
	 */
	public String getOldPassword() {
		return oldPassword;
	}

	/**
	 * <h2>${oldPassword}</h2>
	 * <p>
	 * ${Setter Method For User's Old Password}
	 * </p>
	 * 
	 * @param ${oldPassword}
	 */
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	/**
	 * <h2>${newPassword}</h2>
	 * <p>
	 * ${Getter Method For User's New Password}
	 * </p>
	 * 
	 * @return ${String}
	 */
	public String getNewPassword() {
		return newPassword;
	}

	/**
	 * <h2>${newPassword}</h2>
	 * <p>
	 * ${Setter Method For User's New Password}
	 * </p>
	 * 
	 * @param ${newPassword}
	 */
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	/**
	 * <h2>${confirmPassword}</h2>
	 * <p>
	 * ${Getter Method For Confirm Password}
	 * </p>
	 * 
	 * @return ${String}
	 */
	public String getConfirmNewPassword() {
		return confirmNewPassword;
	}

	/**
	 * <h2>${confirmNewPassword}</h2>
	 * <p>
	 * ${Setter Method For User's Confirm New Password}
	 * </p>
	 * 
	 * @param ${confirmNewPassword}
	 */
	public void setConfirmNewPassword(String confirmNewPassword) {
		this.confirmNewPassword = confirmNewPassword;
	}

}
