package scm.bulletinboard.system.validation.phone;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * PhoneValidator For Phone Number Format Checking
 */
public class PhoneValidator implements ConstraintValidator<Phone, String> {
	
	public void initialize(Phone paramA) {
		
	}

	/**
	 * <h2>${Checking Phone Format Valid or Not}</h2>
	 * <p>
	 * Checking Phone Format Validation 
	 * </p>
	 * 
	 * @param ${phoneNo, context}
	 * @return ${boolean}
	 */
	public boolean isValid(String phoneNo, ConstraintValidatorContext context) {
		if(phoneNo == null) {
			return false;
		}
		//validate phone numbers of format "1234567890"
        if (phoneNo.matches("\\d{11}")) return true;
        //validating phone number with -, . or spaces
        else if(phoneNo.matches("\\d{3}[-\\.\\s]\\d{4}[-\\.\\s]\\d{4}")) return true;
        //validating phone number with extension length from 3 to 5
        else if(phoneNo.matches("\\d{3}-\\d{4}-\\d{4}\\s(x|(ext))\\d{3,5}")) return true;
        //validating phone number where area code is in braces ()
        else if(phoneNo.matches("\\(\\d{3}\\)-\\d{4}-\\d{4}")) return true;
        //return false if nothing matches the input
        else return false;
	}
}

