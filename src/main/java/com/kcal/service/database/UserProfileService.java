package com.kcal.service.database;

import com.kcal.model.User;
import com.kcal.model.json.UserProfileSliderJson;
import com.kcal.model.json.XEditableForm;

/**
 * User: Breku
 * Date: 2014-10-04
 */
public interface UserProfileService {

    void updateProfile(User user, UserProfileSliderJson json);

    void updateProfile(User user, XEditableForm form);

}
