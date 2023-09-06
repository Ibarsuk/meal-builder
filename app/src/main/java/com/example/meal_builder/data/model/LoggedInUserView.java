package com.example.meal_builder.data.model;

/**
 * Class exposing authenticated user details to the UI.
 */
public class LoggedInUserView {
    private String displayName;
    //... other data fields that may be accessible to the UI
    private String role;

    public LoggedInUserView(String displayName, String role) {
        this.displayName = displayName;
        this.role = role;
    }

    public String getDisplayName() {
        return displayName;
    }
    public String getRole() {return  role;}
}