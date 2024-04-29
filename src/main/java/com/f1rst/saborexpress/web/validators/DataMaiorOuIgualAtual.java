package com.f1rst.saborexpress.web.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DataIgualOuMaiorQueAtualValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface DataMaiorOuIgualAtual {
    String message() default "must be equal or greater than current date";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
