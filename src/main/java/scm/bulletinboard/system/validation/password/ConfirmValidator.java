package scm.bulletinboard.system.validation.password;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import scm.bulletinboard.system.form.user.UserCreateForm;

public class ConfirmValidator  implements ConstraintValidator<Confirm, String>  {
	public void initialilze(Confirm paramA) {
		
	}

	public boolean isValid(String password, ConstraintValidatorContext context) {
		UserCreateForm userCreateForm = new UserCreateForm();
		return userCreateForm.getPassword().equals(userCreateForm.getConfirmPassword());
	}

}
