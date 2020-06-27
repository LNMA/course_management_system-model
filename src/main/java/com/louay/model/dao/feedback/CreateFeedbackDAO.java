package com.louay.model.dao.feedback;

import com.louay.model.chains.feedback.Feedback;
import com.louay.model.chains.feedback.FileFeedback;
import com.louay.model.chains.feedback.MessageFeedback;
import com.louay.model.chains.feedback.comment.Comment;

public interface CreateFeedbackDAO {

    Long createCoursesFeedback(Feedback feedback);

    int createFeedbackFiles(FileFeedback fileFeedback);

    int createFeedbackMessages(MessageFeedback messageFeedback);

    Long createFeedbackComments(Comment comment);
}
