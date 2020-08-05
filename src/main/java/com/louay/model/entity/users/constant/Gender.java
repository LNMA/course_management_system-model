package com.louay.model.entity.users.constant;

public enum Gender {
    MALE("MALE"), FEMALE("FEMALE");

    private final String gender;

    Gender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }
}
