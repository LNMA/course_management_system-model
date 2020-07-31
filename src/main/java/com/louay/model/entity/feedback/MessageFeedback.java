package com.louay.model.entity.feedback;

import com.louay.model.entity.feedback.constant.FeedbackType;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Entity
@Table(name = "feedback_content")
@DiscriminatorValue("2")
public class MessageFeedback extends FeedbackContent {
    private static final long serialVersionUID = 6615876469099013237L;
    @Column(name = "post_message", length = 1000)
    private StringBuilder postMessage;

    public StringBuilder getPostMessage() {
        return postMessage;
    }

    public void setPostMessage(StringBuilder postMessage) {
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
        return "MessageFeedback{" +
                "postMessage=" + postMessage +
                '}';
    }
}
