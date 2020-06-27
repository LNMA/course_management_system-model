package com.louay.model.dao.feedback;

import com.louay.model.chains.feedback.Feedback;
import com.louay.model.chains.feedback.FileFeedback;
import com.louay.model.chains.feedback.MessageFeedback;
import com.louay.model.chains.feedback.comment.Comment;

public interface UpdateFeedbackDAO {

    int updateCoursesFeedbackByFeedbackID(Feedback feedback);

    int updateFeedbackFilesByFeedbackID(FileFeedback fileFeedback);

    int updateFeedbackMessagesByFeedbackID(MessageFeedback messageFeedback);

    int updateFeedbackCommentsByCommentID(Comment comment);

}
