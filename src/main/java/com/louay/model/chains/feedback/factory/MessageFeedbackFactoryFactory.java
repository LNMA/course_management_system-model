package com.louay.model.chains.feedback.factory;

import com.louay.model.chains.feedback.MessageFeedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class MessageFeedbackFactoryFactory extends AbstractFactoryFeedback {

    @Autowired
    public MessageFeedbackFactoryFactory(MessageFeedback feedback) {
        super(feedback);
    }
}
