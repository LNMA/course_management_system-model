package com.louay.model.entity.feedback;

import com.louay.model.entity.feedback.constant.FeedbackType;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Entity
@DiscriminatorValue("1")
public class MessageFeedback extends FeedbackContent {
    private static final long serialVersionUID = 8005447294025026606L;
    @Column(name = "post_message", length = 1000, columnDefinition = "VARCHAR(1000)")
    private String postMessage;

    public String getPostMessage() {
        return postMessage;
    }

    public void setPostMessage(String postMessage) {
        this.postMessage = postMessage;
    }

    @Transient
    @Override
    public FeedbackType getFeedbackType() {
        return FeedbackType.MESSAGE;
    }

    @Transient
    @Override
    public String toString() {
        return super.toString() + ", MessageFeedback{" +
                "postMessage='" + postMessage + '\'' +
                '}';
    }
}


