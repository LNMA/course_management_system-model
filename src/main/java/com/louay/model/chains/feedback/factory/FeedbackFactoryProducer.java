package com.louay.model.chains.feedback.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class FeedbackFactoryProducer {
    private MessageFeedbackFactoryFactory messageFeedbackFactoryFactory;
    private FileFeedbackFactoryFactory fileFeedbackFactoryFactory;

    public FeedbackFactoryProducer() {
    }

    public MessageFeedbackFactoryFactory getMessageFeedbackFactoryFactory() {
        return messageFeedbackFactoryFactory;
    }

    @Autowired
    public void setMessageFeedbackFactoryFactory(MessageFeedbackFactoryFactory messageFeedbackFactoryFactory) {
        this.messageFeedbackFactoryFactory = messageFeedbackFactoryFactory;
    }

    public FileFeedbackFactoryFactory getFileFeedbackFactoryFactory() {
        return fileFeedbackFactoryFactory;
    }

    @Autowired
    public void setFileFeedbackFactoryFactory(FileFeedbackFactoryFactory fileFeedbackFactoryFactory) {
        this.fileFeedbackFactoryFactory = fileFeedbackFactoryFactory;
    }

    public AbstractFactoryFeedback getFactory(boolean message) {
        if (message) {
            return this.getMessageFeedbackFactoryFactory();
        } else {
            return this.getFileFeedbackFactoryFactory();
        }
    }
}
