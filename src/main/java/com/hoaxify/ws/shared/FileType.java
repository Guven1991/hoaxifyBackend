package com.hoaxify.ws.shared;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;

@Target({FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { FileTypeValidator.class})
public @interface FileType {

    String message() default "{hoaxify.constraint.FileType.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
    String[] types();
}
