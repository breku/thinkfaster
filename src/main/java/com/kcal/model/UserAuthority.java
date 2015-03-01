package com.kcal.model;

import com.kcal.utils.security.RoleName;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.Assert;

/**
 * Created by brekol on 01.03.15.
 */
public class UserAuthority implements GrantedAuthority {

    public UserAuthority() {
        this(RoleName.ROLE_USER.name());
    }

    private final String role;

    public UserAuthority(String role) {
        Assert.hasText(role, "A granted authority textual representation is required");
        this.role = role;
    }

    public String getAuthority() {
        return role;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof UserAuthority) {
            return role.equals(((UserAuthority) obj).role);
        }

        return false;
    }

    public int hashCode() {
        return this.role.hashCode();
    }

    public String toString() {
        return this.role;
    }
}
