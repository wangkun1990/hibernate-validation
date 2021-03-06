package com.hibernate.custom;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SingleNullValidator implements ConstraintValidator<SingleNull, Object> {

    private String firstField;

    private String secondField;

    @Override
    public void initialize(SingleNull constraintAnnotation) {
        this.firstField = constraintAnnotation.firstField();
        this.secondField = constraintAnnotation.secondField();
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext context) {
        if (object == null) {
            return false;
        }
        try {
            String firstValue = BeanUtils.getProperty(object, firstField);
            String secondValue = BeanUtils.getProperty(object, secondField);
            return StringUtils.isNotBlank(firstValue) || StringUtils.isNotBlank(secondValue);
        } catch (Exception e) {
            return false;
        }
    }
}
