package com.kcal.validator;

import com.kcal.model.UserProfile;
import com.kcal.model.json.UserProfileSliderJson;
import com.kcal.model.json.XEditableForm;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * User: Breku
 * Date: 2014-10-05
 */
@Component
public class UserProfileValidator implements Validator {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserProfileValidator.class);

    private static final String INCORRECT_VALUES = "Incorrect values";
    private static final String PROPERTY_WEIGHT = "weight";
    private static final String PROPERTY_HEIGHT = "height";
    private static final String PROPERTY_KCAL_PER_DAY = "kcalPerDay";
    private static final String PROPERTY_MEALS_PER_DAY = "mealsPerDay";

    private static final int MIN_MEALS_PER_DAY = 0;
    private static final int MAX_MEALS_PER_DAY = 6;

    private static final int MAX_WEIGHT = 500;
    private static final int MAX_HEIGHT = 500;
    private static final int MAX_KCAL_PER_DAY = 20000;

    @Override
    public boolean supports(Class<?> clazz) {
        return UserProfileSliderJson.class.isAssignableFrom(clazz) || UserProfile.class.isAssignableFrom(clazz)
                || XEditableForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (UserProfileSliderJson.class.isAssignableFrom(target.getClass())) {
            validateSliderJson(target, errors);
        } else if (XEditableForm.class.isAssignableFrom(target.getClass())) {
            validateXEditableForm(target, errors);
        }

    }


    private void validateXEditableForm(Object target, Errors errors) {

        XEditableForm xForm = (XEditableForm) target;
        String value = xForm.getValue();
        String property = xForm.getName();

        Double parsedValue = NumberUtils.toDouble(value, -1);


        if (PROPERTY_KCAL_PER_DAY.equals(property)) {
            checkParsedValue(parsedValue, MAX_KCAL_PER_DAY, errors);
        } else if (PROPERTY_HEIGHT.equals(property)) {
            checkParsedValue(parsedValue, MAX_HEIGHT, errors);
        } else if (PROPERTY_WEIGHT.equals(property)) {
            checkParsedValue(parsedValue, MAX_WEIGHT, errors);
        } else if (PROPERTY_MEALS_PER_DAY.equals(property)) {
            checkParsedValue(parsedValue, MIN_MEALS_PER_DAY, MAX_MEALS_PER_DAY, errors);
        } else {
            LOGGER.warn("User tried to modified property={} with value={}", property, value);
            rejectValidation(errors);
        }

    }

    private void checkParsedValue(Double parsedValue, int maxValue, Errors errors) {
        checkParsedValue(parsedValue, 0, maxValue, errors);
    }

    private void checkParsedValue(Double parsedValue, int minValue, int maxValue, Errors errors) {
        if (parsedValue <= minValue || parsedValue > maxValue) {
            rejectValidation(errors);
        }
    }

    private void validateSliderJson(Object target, Errors errors) {
        UserProfileSliderJson json = (UserProfileSliderJson) target;

        if (json.getCarbohydratePercent() < 0 || json.getFatPercent() < 0 || json.getProteinPercent() < 0 ||
                ((int) json.getCarbohydratePercent() + (int) json.getProteinPercent() + (int) json.getFatPercent() != 100)) {
            rejectValidation(errors);
        }
    }

    private void rejectValidation(Errors errors) {
        errors.reject(INCORRECT_VALUES);
    }
}
