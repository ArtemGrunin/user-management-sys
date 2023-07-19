package ua.com.usermanagementsystem.lib;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {
    private static final String PASSWORD_REGEX = "[a-zA-Z0-9]+$";

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        if (password == null
                || password.length() < 3
                || password.length() > 16
                || password.matches(PASSWORD_REGEX)) {
            return false;
        }
        boolean hasOneCharacter = false;
        boolean hasOneNumber = false;
        for (char ch : password.toCharArray()) {
            if (Character.isLetter(ch)) {
                hasOneCharacter = true;
            }
            if (Character.isDigit(ch)) {
                hasOneNumber = true;
            }
        }
        return hasOneCharacter && hasOneNumber;
    }
}
