package com.kcal.service.database.security;

import com.kcal.model.Registration;
import com.kcal.model.User;
import com.kcal.model.UserProfile;
import com.kcal.model.json.UserProfileSliderJson;
import com.kcal.model.json.XEditableForm;
import com.kcal.service.database.RootService;
import com.kcal.utils.security.RoleName;

/**
 * User: Breku
 * Date: 2014-09-23bierzesz cos poza ciuchami normalnymi, w sensie jeansy etc
 */
public interface UserService extends RootService<User> {

    void registerNewUser(Registration registration);

    void grantAuthorityToUser(User user, RoleName roleName);

    public void removeAuthorityFromUser(User user, RoleName roleName);

    public User getUser(long userId);

    public User getCurrentUser();

    void updateProfile(UserProfileSliderJson json);

    void updateProfile(XEditableForm form);

    UserProfile getUserProfile();

    User findByName(String name);
}
