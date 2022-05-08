package com.bwagih.automaaticirrigateplot.model;

import java.util.Collection;

import org.springframework.security.core.userdetails.User;

/**
 * @author bwagih
 */

public class CustomUser extends User {
    private static final long serialVersionUID = 1L;

    private final String firstName;
    private final String lastName;
    private String fullName;
    private String pass;
    private String email;

    public CustomUser(String username, String password, boolean enabled,
                      boolean accountNonExpired, boolean credentialsNonExpired,
                      boolean accountNonLocked,
                      Collection authorities,
                      String firstName, String lastName , String email) {

        super(username, password, enabled, accountNonExpired,
                credentialsNonExpired, accountNonLocked, authorities);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }


    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }


    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
