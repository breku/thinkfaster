package com.kcal.utils;

import com.google.common.collect.Sets;
import com.kcal.model.User;
import com.kcal.model.UserAuthority;
import com.kcal.model.json.XEditableForm;
import com.kcal.utils.security.RoleName;

/**
 * User: Breku
 * Date: 2014-10-28
 */
public class BuilderTest {


    public static XEditableForm createXEditableForm(String name, String value) {
        XEditableForm form = new XEditableForm();
        form.setName(name);
        form.setValue(value);
        return form;
    }

    public static User createUser(String name) {
        User user = new User(name, "aaa@aaa.pl", "zaq1@WSX", true, true, true, true, Sets.<UserAuthority>newHashSet(new UserAuthority(RoleName.ROLE_ADMIN.name())));
        return user;
    }


    public static User createUser(long id, String name) {
        User user = new User(name, "aaa@aaa.pl", "zaq1@WSX", true, true, true, true, Sets.<UserAuthority>newHashSet(new UserAuthority(RoleName.ROLE_ADMIN.name())));
        user.setId(id);
        return user;
    }

}
