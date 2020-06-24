package com.louay.model.dao.feedback;

import com.louay.model.chains.feedback.Feedback;
import com.louay.model.chains.feedback.FileFeedback;
import com.louay.model.chains.feedback.MessageFeedback;
import com.louay.model.chains.feedback.comment.Comment;

public interface UpdateFeedbackDAO {

    int updateCoursesFeedback(Feedback feedback);

    int updateFeedbackFiles(FileFeedback fileFeedback);

    int updateFeedbackMessages(MessageFeedback messageFeedback);

    int updateFeedbackComments(Comment comment);

}
