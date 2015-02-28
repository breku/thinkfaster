package com.kcal.model;

import com.kcal.model.builder.UserProfileBuilder;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collections;
import java.util.Set;

/**
 * User: Breku
 * Date: 2014-09-15
 */
@Document(collection = "users")
public class User extends RootEntity implements UserDetails {

    private String password;
    private final String username;
    private String email;
    private Set<GrantedAuthority> authorities;
    private final boolean accountNonExpired;
    private final boolean accountNonLocked;
    private final boolean credentialsNonExpired;
    private final boolean enabled;

    private UserProfile userProfile;


    public User() {
        this(StringUtils.EMPTY, StringUtils.EMPTY, StringUtils.EMPTY, Collections.<GrantedAuthority>emptySet());
    }

    private User(String username, String email, String password, Set<GrantedAuthority> authorities) {
        this(username, email, password, true, true, true, true, authorities);
    }


    public User(String username, String email, String password, boolean accountNonExpired, boolean accountNonLocked, boolean credentialsNonExpired, boolean enabled, Set<GrantedAuthority> authorities) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.accountNonExpired = accountNonExpired;
        this.credentialsNonExpired = credentialsNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.authorities = authorities;
        this.userProfile = new UserProfileBuilder().defaultValues().build();
    }


    @Override
    public boolean equals(Object rhs) {
        if (rhs instanceof User) {
            return username.equals(((User) rhs).username);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return username.hashCode();
    }

    public String getEmail() {
        return email;
    }

    @Override
    public Set<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append(super.toString())
                .append("password", "[PROTECTED]")
                .append("username", username)
                .append("email", email)
                .append("authorities", authorities)
                .append("accountNonExpired", accountNonExpired)
                .append("accountNonLocked", accountNonLocked)
                .append("credentialsNonExpired", credentialsNonExpired)
                .append("enabled", enabled)
                .append("userProfile", userProfile)
                .toString();
    }
}
