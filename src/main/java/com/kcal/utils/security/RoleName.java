package com.kcal.utils.security;

/**
 * User: Breku
 * Date: 2014-09-16
 */
public enum RoleName {
    ROLE_ADMIN("ADMIN"),
    ROLE_USER("USER");

    private String simpleName;

    RoleName(String simpleName) {
        this.simpleName = simpleName;
    }


    /**
     * Getter
     *
     * @return the role name without ROLE_ prefix
     */
    public String getSimpleName() {
        return simpleName;
    }

}
