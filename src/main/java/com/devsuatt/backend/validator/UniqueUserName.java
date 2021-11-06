package com.devsuatt.backend.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = {UniqueUserNameValidator.class})
public @interface UniqueUserName {
    String message() default "{backend.constraints.UniqueUserName.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};


}
