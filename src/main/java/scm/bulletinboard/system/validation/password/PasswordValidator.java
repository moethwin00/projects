/**
 * 
 */
package scm.bulletinboard.system.validation.password;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<Password, String> {
	
	public void initialilze(Password paramA) {
		
	}

	public boolean isValid(String password, ConstraintValidatorContext context) {
		if(password == null) {
			return false;
		}
		else if (password.matches("^(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$")) {
			return true;
		}
		else return false;
	}

}
