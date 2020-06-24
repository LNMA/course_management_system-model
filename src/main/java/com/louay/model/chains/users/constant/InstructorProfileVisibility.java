package com.louay.model.chains.users.constant;

public enum InstructorProfileVisibility {
    PUBLIC("PUBLIC"), PRIVATE("PRIVATE");

    private String profileVisibility;

    InstructorProfileVisibility(String profileVisibility) {
        this.profileVisibility = profileVisibility;
    }

    public String getProfileVisibility() {
        return profileVisibility;
    }
}
