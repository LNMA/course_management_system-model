package com.louay.model.chains.feedback.factory;

import com.louay.model.chains.feedback.constant.FeedbackType;
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

    public AbstractFactoryFeedback getFactory(FeedbackType feedbackType) {
        if (FeedbackType.MESSAGE_FEEDBACK.compareTo(feedbackType) == 0) {
            return this.getMessageFeedbackFactoryFactory();
        } else if (FeedbackType.FILE_FEEDBACK.compareTo(feedbackType) == 0 ){
            return this.getFileFeedbackFactoryFactory();
        }else {
            throw new UnsupportedOperationException("FeedbackType may be MESSAGE_FEEDBACK or FILE_FEEDBACK only.");
        }
    }
}
