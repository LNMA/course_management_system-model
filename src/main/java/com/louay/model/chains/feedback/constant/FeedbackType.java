package com.louay.model.chains.feedback.constant;

public enum FeedbackType {
    FILE_FEEDBACK("fileFeedback"), MESSAGE_FEEDBACK("messageFeedback");

    private final String feedbackType;

    FeedbackType(String feedbackType) {
        this.feedbackType = feedbackType;
    }

    public String getFeedbackType() {
        return feedbackType;
    }
}
