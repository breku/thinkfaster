package com.kcal.service.database;

import com.kcal.model.User;
import com.kcal.model.UserProfile;
import com.kcal.model.builder.UserProfileBuilder;
import com.kcal.model.json.UserProfileSliderJson;
import com.kcal.model.json.XEditableForm;
import org.springframework.stereotype.Service;

/**
 * User: Breku
 * Date: 2014-10-04
 */
@Service
public class UserProfileServiceImpl implements UserProfileService {

    @Override
    public void updateProfile(User user, UserProfileSliderJson json) {

        UserProfile userProfile = new UserProfileBuilder(user.getUserProfile())
                .proteinPercent(json.getProteinPercent())
                .carbohydratePercent(json.getCarbohydratePercent())
                .fatPercent(json.getFatPercent()).build();
        user.setUserProfile(userProfile);
    }

    @Override
    public void updateProfile(User user, XEditableForm form) {
        if (form.getName().equals("weight")) {
            user.setUserProfile(new UserProfileBuilder(user.getUserProfile()).weight(Double.parseDouble(form.getValue())).build());
        } else if (form.getName().equals("height")) {
            user.setUserProfile(new UserProfileBuilder(user.getUserProfile()).height(Double.parseDouble(form.getValue())).build());
        } else if (form.getName().equals("kcalPerDay")) {
            user.setUserProfile(new UserProfileBuilder(user.getUserProfile()).kcalPerDay(Double.parseDouble(form.getValue())).build());
        } else if (form.getName().equals("mealsPerDay")) {
            user.setUserProfile(new UserProfileBuilder(user.getUserProfile()).mealsPerDay(Integer.parseInt(form.getValue())).build());
        }
    }

}
