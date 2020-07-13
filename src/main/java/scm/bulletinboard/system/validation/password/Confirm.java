package scm.bulletinboard.system.validation.password;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ConfirmValidator.class)
public @interface Confirm {
	String message();

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
