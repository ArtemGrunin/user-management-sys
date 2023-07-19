package ua.com.usermanagementsystem.lib;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import ua.com.usermanagementsystem.model.Status;

public class StatusValidator implements ConstraintValidator<ValidStatus, String> {
    @Override
    public boolean isValid(String status, ConstraintValidatorContext constraintValidatorContext) {
        if (status == null) {
            return false;
        }
        try {
            Status.valueOf(status);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }
}
