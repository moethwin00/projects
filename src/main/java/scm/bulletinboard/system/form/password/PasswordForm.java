package scm.bulletinboard.system.form.password;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import scm.bulletinboard.system.validation.password.Password;

public class PasswordForm implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Integer id;
	
	@NotEmpty(message = "*Password is required")
	@NotBlank(message = "*Password is required")
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
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getId() {
		return id;
	}
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getConfirmNewPassword() {
		return confirmNewPassword;
	}
	public void setConfirmNewPassword(String confirmNewPassword) {
		this.confirmNewPassword = confirmNewPassword;
	}
	
	
}
