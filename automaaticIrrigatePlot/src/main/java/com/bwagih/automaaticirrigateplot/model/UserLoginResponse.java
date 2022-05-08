package com.bwagih.automaaticirrigateplot.model;

public class UserLoginResponse {

    private String token;
    private String fullName;

    public UserLoginResponse(String token) {
        this.token = token;
    }

    public UserLoginResponse(String token, String fullName) {
        this.token = token;
        this.fullName = fullName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "UserLoginResponse{" +
                "token='" + token + '\'' +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}
