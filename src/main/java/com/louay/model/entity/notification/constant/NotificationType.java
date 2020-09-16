package com.louay.model.entity.notification.constant;

public enum NotificationType {
    MATERIAL("MATERIAL"), FEEDBACK("FEEDBACK");

    private final String type;

    NotificationType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
