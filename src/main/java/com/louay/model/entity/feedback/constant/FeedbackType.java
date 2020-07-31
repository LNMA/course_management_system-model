package com.louay.model.entity.feedback.constant;

public enum FeedbackType {
    FILE("FILE"), MESSAGE("MESSAGE");

    private final String feedbackType;

    FeedbackType(String feedbackType) {
        this.feedbackType = feedbackType;
    }

    public String getFeedbackType() {
        return feedbackType;
    }
}
