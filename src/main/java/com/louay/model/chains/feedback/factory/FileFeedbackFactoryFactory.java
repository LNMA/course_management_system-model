package com.louay.model.chains.feedback.factory;

import com.louay.model.chains.feedback.FileFeedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class FileFeedbackFactoryFactory extends AbstractFactoryFeedback {

    @Autowired
    public FileFeedbackFactoryFactory(FileFeedback feedback) {
        super(feedback);
    }
}
