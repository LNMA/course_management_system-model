package com.louay.model.service.feedback;

import com.louay.model.entity.feedback.CourseFeedback;
import com.louay.model.entity.feedback.FileFeedback;
import com.louay.model.entity.feedback.FileMessageFeedback;
import com.louay.model.entity.feedback.MessageFeedback;

import java.util.Set;

public interface FeedbackService {
    CourseFeedback createCourseFeedback(CourseFeedback courseFeedback);

    CourseFeedback deleteCourseFeedbackByFeedbackId(CourseFeedback courseFeedback);

    CourseFeedback updateCourseFeedback(CourseFeedback courseFeedback);

    CourseFeedback findCourseFeedbackByFeedbackId(CourseFeedback courseFeedback);

    FileFeedback createFileFeedback(FileFeedback fileFeedback);

    FileFeedback deleteFileFeedbackByFeedbackId(FileFeedback fileFeedback);

    FileFeedback updateFileFeedback(FileFeedback fileFeedback);

    FileFeedback findFileFeedbackByFeedbackId(FileFeedback fileFeedback);

    MessageFeedback createMessageFeedback(MessageFeedback messageFeedback);

    MessageFeedback deleteMessageFeedbackByFeedbackId(MessageFeedback messageFeedback);

    MessageFeedback updateMessageFeedback(MessageFeedback messageFeedback);

    MessageFeedback findMessageFeedbackByFeedbackId(MessageFeedback messageFeedback);

    FileMessageFeedback createFileMessageFeedback(FileMessageFeedback fileMessageFeedback);

    FileMessageFeedback deleteFileMessageFeedbackByFeedbackId(FileMessageFeedback fileMessageFeedback);

    FileMessageFeedback updateFileMessageFeedback(FileMessageFeedback fileMessageFeedback);

    FileMessageFeedback findFileMessageFeedbackByFeedbackId(FileMessageFeedback fileMessageFeedback);

    Set<CourseFeedback> findCourseFeedbackByCourseId(CourseFeedback courseFeedback);

    Set<CourseFeedback> findCourseFeedbackAndCommentByCourseId(CourseFeedback courseFeedback);
}
