package com.bwagih.automaaticirrigateplot.dto;

import com.bwagih.automaaticirrigateplot.model.ResponseModel;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author bwagih
 */
public class UserLoginDto extends ResponseModel {
    private String userName;
    private String plainPassword;
    private String email;
    private String firstName;
    private String lastName;
    private int enabled;
    private String token;

    public UserLoginDto(String userName, String plainPassword, String email, String firstName, String lastName, int enabled) {
        this.userName = userName;
        this.plainPassword = plainPassword;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.enabled = enabled;
    }

    public UserLoginDto(){

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPlainPassword() {
        return plainPassword;
    }

    public void setPlainPassword(String plainPassword) {
        this.plainPassword = plainPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
