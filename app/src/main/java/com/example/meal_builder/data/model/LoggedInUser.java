package com.example.meal_builder.data.model;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class LoggedInUser {

    private int userId;
    private String displayName;
    private String role;

    public LoggedInUser(int userId, String displayName, String role) {
        this.userId = userId;
        this.displayName = displayName;
        this.role = role;
    }

    public int getUserId() {
        return userId;
    }

    public String getDisplayName() {
        return displayName;
    }
    public String getRole() {return  role;}
}