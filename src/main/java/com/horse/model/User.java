package com.horse.model;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * User: Breku
 * Date: 2014-09-15
 */
@Entity
@Table(name = "USERS")
public class User extends RootEntity implements UserDetails {

    @Column
    private final String username;
    @Column
    private final boolean accountNonExpired;
    @Column
    private final boolean accountNonLocked;
    @Column
    private final boolean credentialsNonExpired;
    @Column
    private final boolean enabled;
    @Column
    private String password;
    @Column
    private String email;
    @JoinColumn(name = "USER_ID")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Horse> horses;

    @JoinColumn(name = "USER_ID")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<UserRole> userRoles;


    public User() {
        this(StringUtils.EMPTY, StringUtils.EMPTY, StringUtils.EMPTY, Collections.<UserRole>emptySet());
    }

    private User(String username, String email, String password, Set<UserRole> userRoles) {
        this(username, email, password, true, true, true, true, userRoles);
    }


    public User(String username, String email, String password, boolean accountNonExpired, boolean accountNonLocked, boolean credentialsNonExpired, boolean enabled, Set<UserRole> userRoles) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.accountNonExpired = accountNonExpired;
        this.credentialsNonExpired = credentialsNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.userRoles = userRoles;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("username", username)
                .append("accountNonExpired", accountNonExpired)
                .append("accountNonLocked", accountNonLocked)
                .append("credentialsNonExpired", credentialsNonExpired)
                .append("enabled", enabled)
                .append("password", "[PROTECTED]")
                .append("email", email)
                .append("horses", horses)
                .append("userRoles", userRoles)
                .toString();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public Set<GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        for (UserRole userRole : userRoles) {
            authorities.add(new SimpleGrantedAuthority(userRole.getRoleName()));
        }
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

    public Set<Horse> getHorses() {
        return horses;
    }

    public void setHorses(Set<Horse> horses) {
        this.horses = horses;
    }

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }
}
