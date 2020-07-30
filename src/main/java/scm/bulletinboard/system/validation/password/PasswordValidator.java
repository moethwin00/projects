/**
 * 
 */
package scm.bulletinboard.system.validation.password;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * PasswordValidator For Password Format Checking
 */
public class PasswordValidator implements ConstraintValidator<Password, String> {
	
	public void initialilze(Password paramA) {
		
	}

	/**
	 * <h2>${Checking Password Format Valid or Not}</h2>
	 * <p>
	 * Checking Password Format Validation 
	 * </p>
	 * 
	 * @param ${password, context}
	 * @return ${boolean}
	 */
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
