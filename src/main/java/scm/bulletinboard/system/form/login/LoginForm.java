package scm.bulletinboard.system.form.login;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import scm.bulletinboard.system.model.User;

/**
 * Form for Login
 */
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
	 * ${Setter Method For User Email}
	 * </p>
	 * 
	 * @param ${email}
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
     * <h2> ${password}</h2>
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
     * <h2> ${password}</h2>
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
     * <h2> ${id}</h2>
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
     * <h2> ${id}</h2>
     * <p>
     * ${Setter Method For User id}
     * </p>
     * 
     * @param ${id}
     */
	public void setId(int id) {
		this.id = id;
	}

	/**
     * <h2> ${type}</h2>
     * <p>
     * ${Getter Method For User Type}
     * </p>
     * 
     * @return ${char}
     */
	public char getType() {
		return type;
	}

	/**
     * <h2> ${type}</h2>
     * <p>
     * ${Setter Method For User Type}
     * </p>
     * 
     * @param ${type}
     */
	public void setType(char type) {
		this.type = type;
	}

}
