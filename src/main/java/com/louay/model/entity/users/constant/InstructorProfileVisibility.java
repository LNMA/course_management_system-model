package com.louay.model.entity.users.constant;

public enum InstructorProfileVisibility {
    PUBLIC("PUBLIC"), PRIVATE("PRIVATE");

    private final String profileVisibility;

    InstructorProfileVisibility(String profileVisibility) {
        this.profileVisibility = profileVisibility;
    }

    public String getProfileVisibility() {
        return profileVisibility;
    }
}
