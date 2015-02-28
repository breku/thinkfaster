package com.horse.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by brekol on 23.11.14.
 */

@Entity
@Table(name = "USER_ROLES")
public class UserRole extends RootEntity {

    public UserRole() {
    }

    public UserRole(String roleName) {
        this.roleName = roleName;
    }

    @Column
    private String roleName;


    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }


}
