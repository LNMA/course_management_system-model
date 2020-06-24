package com.louay.model.dao.feedback;

import com.louay.model.chains.feedback.Feedback;
import com.louay.model.chains.feedback.FileFeedback;
import com.louay.model.chains.feedback.MessageFeedback;
import com.louay.model.chains.feedback.comment.Comment;

public interface DeleteFeedbackDAO {

    int deleteCoursesFeedback(Feedback feedback);

    int deleteFeedbackFiles(FileFeedback fileFeedback);

    int deleteFeedbackMessages(MessageFeedback messageFeedback);

    int deleteFeedbackComments(Comment comment);

}
