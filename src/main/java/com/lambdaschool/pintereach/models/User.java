package com.lambdaschool.pintereach.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User
{
    @Id
    @GeneratedValue
    private long userId;

    private String username;

    private String password;



    public User(String username, String password) {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
