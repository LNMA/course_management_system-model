package com.louay.model.chains.feedback;

import com.louay.model.chains.courses.Courses;
import com.louay.model.chains.users.factory.UserFactoryProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class MessageFeedback extends Feedback {
    private StringBuilder postMessage;

    @Autowired
    public MessageFeedback(Courses course, UserFactoryProducer userFactoryProducer) {
        super(course, userFactoryProducer);
    }

    public StringBuilder getPostMessage() {
        return postMessage;
    }

    public void setPostMessage(StringBuilder postMessage) {
        this.postMessage = postMessage;
    }

    public void setPostMessageAuxiliary(String postMessage) {
        this.postMessage = new StringBuilder(postMessage);
    }

    @Override
    public String toString() {
        return super.toString() + ", MessageFeedback{" +
                "postMessage=" + postMessage +
                '}';
    }
}
