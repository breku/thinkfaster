package com.horse.service.database.security;

import com.horse.model.Registration;
import com.horse.model.User;
import com.horse.service.RootService;
import com.horse.utils.security.RoleName;

/**
 * User: Breku
 * Date: 2014-09-23
 */
public interface UserService extends RootService<User> {

    void registerNewUser(Registration registration);

    void grantAuthorityToUser(User user, RoleName roleName);

    public void removeAuthorityFromUser(User user, RoleName roleName);

    public User getCurrentUser();

    User findByName(String name);
}
