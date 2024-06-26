package com.f1rst.saborexpress.web.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;


public class DataIgualOuMaiorQueAtualValidator implements ConstraintValidator<DataMaiorOuIgualAtual, LocalDate> {

    @Override
    public void initialize(DataMaiorOuIgualAtual constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(LocalDate date, ConstraintValidatorContext constraintValidatorContext) {
        if(date == null) {
            return false;
        }

        return date.isAfter(LocalDate.now()) || date.isEqual(LocalDate.now());
    }
}
