package com.example.sample.validators.impl;

import com.example.sample.validators.annotations.ValidDate;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/***
 * This class is used to check the format of the date
 */
public class DateValidatorImpl implements ConstraintValidator<ValidDate, String> {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    @Override
    public void initialize(ValidDate validDate) {

    }

    /***
     * This method checks whether the date is in required format or not
     * @param date input date
     * @param constraint ConstraintValidatorContext instance
     * @return true - if date is in valid format otherwise false
     */
    @Override
    public boolean isValid(String date, ConstraintValidatorContext constraint) {
        try {
            if(!StringUtils.isEmpty(date)) {
                formatter.parse(date);
                return true;
            }
        } catch (DateTimeParseException e) {

        }
        return false;
    }
}
