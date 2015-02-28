package com.kcal.validator;

import com.kcal.model.Registration;
import com.kcal.model.User;
import com.kcal.service.database.security.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

/**
 * User: Breku
 * Date: 2014-09-17
 */
@Component
public class RegisterUserValidator implements Validator {


    private UserService userService;

    static final String USERNAME_NOT_NULL_ERROR_KEY = "RegisterUserValidator.error.notnull.username";
    static final String PASSWORD_NOT_NULL_ERROR_KEY = "RegisterUserValidator.error.notnull.password";
    static final String EMAIL_NOT_NULL_ERROR_KEY = "RegisterUserValidator.error.notnull.email";

    static final String USERNAME_SYNTAX_ERROR_KEY = "RegisterUserValidator.error.syntax.username";
    static final String PASSWORD_SYNTAX_ERROR_KEY = "RegisterUserValidator.error.syntax.password";
    static final String EMAIL_SYNTAX_ERROR_KEY = "RegisterUserValidator.error.syntax.email";

    static final String IDENTICAL_PASSWORD_ERROR_KEY = "RegisterUserValidator.error.identical.password";

    static final String USER_EXISTS_ERROR_KEY = "RegisterUserValidator.error.userexists";


    static final String FIELD_USERNAME = "username";
    static final String FIELD_EMAIL = "email";
    static final String FIELD_PASSWORD = "password";
    static final String FIELD_CONFIRM_PASSWORD = "confirmPassword";

    private static final String USERNAME_REGEX_PATTERN = "^[a-zA-Z0-9_-]{6,30}$";
    private static final String EMAIL_REGEX_PATTERN = "^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,4})$";
    private static final String PASSWORD_REGEX_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,20})";


    @Autowired
    public RegisterUserValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Registration.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Registration registration = (Registration) target;
        checkNotNull(errors, registration);
        checkSyntax(errors, registration);
    }


    private boolean syntaxCorrect(String string, String regex) {
        if (string != null) {
            Pattern pattern = Pattern.compile(regex);
            return pattern.matcher(string).matches();
        }
        return false;
    }

    private void checkSyntax(Errors errors, Registration registration) {
        if (!syntaxCorrect(registration.getPassword(), PASSWORD_REGEX_PATTERN)) {
            errors.rejectValue(FIELD_PASSWORD, PASSWORD_SYNTAX_ERROR_KEY);
        }
        if (!syntaxCorrect(registration.getConfirmPassword(), PASSWORD_REGEX_PATTERN)) {
            errors.rejectValue(FIELD_CONFIRM_PASSWORD, PASSWORD_SYNTAX_ERROR_KEY);
        }
        if (!syntaxCorrect(registration.getUsername(), USERNAME_REGEX_PATTERN)) {
            errors.rejectValue(FIELD_USERNAME, USERNAME_SYNTAX_ERROR_KEY);
        }
        if (!syntaxCorrect(registration.getEmail(), EMAIL_REGEX_PATTERN)) {
            errors.rejectValue(FIELD_EMAIL, EMAIL_SYNTAX_ERROR_KEY);
        }
        if (registration.getPassword() == null || registration.getConfirmPassword() == null || !registration.getPassword().equals(registration.getConfirmPassword())) {
            errors.rejectValue(FIELD_PASSWORD, IDENTICAL_PASSWORD_ERROR_KEY);
            errors.rejectValue(FIELD_CONFIRM_PASSWORD, IDENTICAL_PASSWORD_ERROR_KEY);
        }

        User user = userService.findByName(registration.getUsername());
        if (user != null) {
            errors.rejectValue(FIELD_USERNAME, USER_EXISTS_ERROR_KEY);
        }

    }

    private void checkNotNull(Errors errors, Registration registration) {
        if (StringUtils.isBlank(registration.getUsername())) {
            errors.rejectValue(FIELD_USERNAME, USERNAME_NOT_NULL_ERROR_KEY);
        }
        if (StringUtils.isBlank(registration.getEmail())) {
            errors.rejectValue(FIELD_EMAIL, EMAIL_NOT_NULL_ERROR_KEY);
        }
        if (StringUtils.isBlank(registration.getPassword())) {
            errors.rejectValue(FIELD_PASSWORD, PASSWORD_NOT_NULL_ERROR_KEY);
        }
        if (StringUtils.isBlank(registration.getConfirmPassword())) {
            errors.rejectValue(FIELD_CONFIRM_PASSWORD, PASSWORD_NOT_NULL_ERROR_KEY);
        }

    }
}
