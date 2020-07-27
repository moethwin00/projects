package scm.bulletinboard.system.dto;

import scm.bulletinboard.system.form.user.UserCreateForm;

public class UserDTO {
	String errorMsg;
	UserCreateForm userCreateForm;
	
	public UserDTO() {
	}

	public UserDTO(String errorMsg, UserCreateForm userCreateForm) {
		this.errorMsg = errorMsg;
		this.userCreateForm = userCreateForm;
	}
	
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public UserCreateForm getUserCreateForm() {
		return userCreateForm;
	}
	public void setUserCreateForm(UserCreateForm userCreateForm) {
		this.userCreateForm = userCreateForm;
	}

}
