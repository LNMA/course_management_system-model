package com.louay.model.chains.users.constant;

public enum UserRole {
    ADMIN("Admin"), INSTRUCTOR("Instructor"), STUDENT("Student");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
