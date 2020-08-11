package scm.bulletinboard.system.validation.password;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * <h2>Interface for PasswordValidator</h2>
 * <p>
 * Interface for PasswordValidator
 * </p>
 */
@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Password {
    String message() default "*Password must be more than 8 characters long, must contain at least 1 Uppercase and 1 Numeric.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}