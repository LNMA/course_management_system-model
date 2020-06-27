package com.louay.model.chains.users.constant;

public enum UserRole {
    ADMIN("ADMIN"), INSTRUCTOR("INSTRUCTOR"), STUDENT("STUDENT");

    private final String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
