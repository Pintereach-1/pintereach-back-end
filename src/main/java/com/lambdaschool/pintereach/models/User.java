package com.lambdaschool.pintereach.models;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class User
{
    @Id
    @GeneratedValue
    private long userId;

    private String username;

    private char password;

    public User(String username, char password) {
        this.username = username;
        this.password = password;
    }

    public User()
    {
        //default
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public char getPassword() {
        return password;
    }

    public void setPassword(char password) {
        this.password = password;
    }
}
