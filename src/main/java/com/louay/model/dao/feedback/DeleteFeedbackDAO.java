package com.louay.model.dao.feedback;

import com.louay.model.chains.feedback.Feedback;
import com.louay.model.chains.feedback.FileFeedback;
import com.louay.model.chains.feedback.MessageFeedback;
import com.louay.model.chains.feedback.comment.Comment;

public interface DeleteFeedbackDAO {

    int deleteCoursesFeedbackByFeedbackID(Feedback feedback);

    int deleteFeedbackFilesByFeedbackID(FileFeedback fileFeedback);

    int deleteFeedbackMessagesByFeedbackID(MessageFeedback messageFeedback);

    int deleteFeedbackCommentsByCommentID(Comment comment);

}
