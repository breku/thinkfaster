package com.kcal.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * User: Breku
 * Date: 2014-09-17
 */
public class Registration {

    private String username;

    private String password;

    private String confirmPassword;

    private String email;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("username", username)
                .append("password", "[PROTECTED]")
                .append("confirmPassword", "[PROTECTED]")
                .append("email", email)
                .toString();
    }
}
