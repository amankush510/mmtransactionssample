package com.example.sample.validators.annotations;

import com.example.sample.validators.impl.DateValidatorImpl;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/***
 * This annotation can be used to validate date format
 * It can be applied at the field and paramter level
 */
@Target( {ElementType.FIELD, ElementType.PARAMETER})
@Retention( RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DateValidatorImpl.class)
public @interface ValidDate {
    String message() default "Date should be provided in mm/dd/yyyy format";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
