package com.kcal.model;

import com.google.appengine.repackaged.com.google.api.client.util.Sets;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Index;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.HashSet;

/**
 * User: Breku
 * Date: 2014-09-15
 */
@Entity
public class User extends RootEntity implements UserDetails, Serializable {

//    private static final long serialVersionUID = 1L;


    private String password;

    @Index
    private String username;
    private String email;
    private HashSet<UserAuthority> authorities = new HashSet<>();
    private final boolean accountNonExpired;
    private final boolean accountNonLocked;
    private final boolean credentialsNonExpired;
    private final boolean enabled;


    public User() {
        this(StringUtils.EMPTY, StringUtils.EMPTY, StringUtils.EMPTY, Sets.<UserAuthority>newHashSet());
    }

    private User(String username, String email, String password, HashSet<UserAuthority> authorities) {
        this(username, email, password, true, true, true, true, authorities);
    }


    public User(String username, String email, String password, boolean accountNonExpired, boolean accountNonLocked, boolean credentialsNonExpired, boolean enabled, HashSet<UserAuthority> authorities) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.accountNonExpired = accountNonExpired;
        this.credentialsNonExpired = credentialsNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.authorities = authorities;
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
    public HashSet<UserAuthority> getAuthorities() {
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
                .toString();
    }
}
