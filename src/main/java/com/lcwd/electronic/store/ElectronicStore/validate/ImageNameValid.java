package com.lcwd.electronic.store.ElectronicStore.validate;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ImageNameValidator.class)
public @interface ImageNameValid {
//    Error message
    String message() default "Invalid image name";
//    Represent group of constraint
    Class<?>[] groups() default {};
//    Represents additional information about annotations
    Class<? extends Payload>[] payload() default {};
}
