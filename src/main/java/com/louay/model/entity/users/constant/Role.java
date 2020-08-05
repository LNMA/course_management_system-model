package com.louay.model.entity.users.constant;

public enum Role {
    ADMIN("ADMIN"), INSTRUCTOR("INSTRUCTOR"), STUDENT("STUDENT"), USER("USER");

    private final String role;

    Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
