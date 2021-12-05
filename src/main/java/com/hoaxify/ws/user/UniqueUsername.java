package com.hoaxify.ws.user;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Size;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.FIELD;

@Target({FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { UniqueUsernameValidator.class})
public @interface UniqueUsername {

    String message() default "{hoaxify.constraint.username.UniqueUsername.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
