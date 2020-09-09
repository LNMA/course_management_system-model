package com.louay.model.util.constant;

public enum ClassName {
    COURSES("Courses"),ACCOUNTS("Account"),COURSE_FEEDBACK("CourseFeedback"),COURSE_MATERIAL("CourseMaterial");

    private final String className;

    ClassName(String className) {
        this.className = className;
    }

    public String getClassName() {
        return className;
    }
}
