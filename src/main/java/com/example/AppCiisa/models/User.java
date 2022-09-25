package com.example.AppCiisa.models;

import java.util.Date;

// User model, stores stuff about the user

public class User implements IUser {
    private long id;
    private String username;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private double height;
    private String email;
    private String password;

    public User(String username, String firstName, String lastName, Date dateOfBirth, double height, String email) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.height = height;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public double getHeight() {
        return height;
    }

    public String getHeightAsString() {
        return Double.toString(height);
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
