package scm.bulletinboard.system.validation.password;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import scm.bulletinboard.system.form.user.UserCreateForm;

public class ConfirmValidator  implements ConstraintValidator<Confirm, Object>  {
	public void initialilze(Confirm paramA) {
		
	}

	public boolean isValid(Object obj, ConstraintValidatorContext context) {
		UserCreateForm userCreateForm = (UserCreateForm) obj;
		return userCreateForm.getPassword().equals(userCreateForm.getConfirmPassword());
	}

}
