package scm.bulletinboard.system.form.login;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import scm.bulletinboard.system.model.User;

public class LoginForm {
	int id;
	char type;
	
	@NotEmpty(message = "*Email Address is required")
	@Email(message = "*Should be email addresss")
	private String email;
	
	@NotEmpty(message = "*Password is required")
	private String password;
	
	public LoginForm() {
		
	}
	public LoginForm(User user) {
		this.email = user.getEmail();
		this.password = user.getPassword();
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public char getType() {
		return type;
	}
	public void setType(char type) {
		this.type = type;
	}
	
}
