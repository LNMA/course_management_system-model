package com.louay.model.chains.feedback.factory;

import com.louay.model.chains.feedback.Feedback;

public abstract class AbstractFactoryFeedback {
    private Feedback feedback;

    public AbstractFactoryFeedback(Feedback feedback) {
        this.feedback = feedback;
    }

    public Feedback getFeedback() {
        return feedback;
    }
}
