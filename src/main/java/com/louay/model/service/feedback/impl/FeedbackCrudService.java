package com.louay.model.service.feedback.impl;

import com.louay.model.dao.feedback.CourseFeedbackDao;
import com.louay.model.dao.feedback.FeedbackDao;
import com.louay.model.entity.feedback.CourseFeedback;
import com.louay.model.entity.feedback.FileFeedback;
import com.louay.model.entity.feedback.FileMessageFeedback;
import com.louay.model.entity.feedback.MessageFeedback;
import com.louay.model.entity.wrapper.GeneralSearch;
import com.louay.model.service.feedback.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Set;

@Service
public class FeedbackCrudService implements FeedbackService, Serializable {
    private static final long serialVersionUID = 8904792140268539111L;
    private final FeedbackDao feedbackDao;
    private final CourseFeedbackDao courseFeedbackDao;

    @Autowired
    public FeedbackCrudService(FeedbackDao feedbackDao, CourseFeedbackDao courseFeedbackDao) {
        if (courseFeedbackDao == null || feedbackDao == null) {
            throw new IllegalArgumentException("DAO cannot be null at FeedbackCrudService.class");
        }
        this.feedbackDao = feedbackDao;
        this.courseFeedbackDao = courseFeedbackDao;
    }

    private FeedbackDao getFeedbackDao() {
        return feedbackDao;
    }

    private CourseFeedbackDao getCourseFeedbackDao() {
        return courseFeedbackDao;
    }

    @Transactional
    @Override
    public CourseFeedback createCourseFeedback(CourseFeedback courseFeedback) {
        return getCourseFeedbackDao().save(courseFeedback);
    }

    @Transactional
    @Override
    public CourseFeedback deleteCourseFeedbackByFeedbackId(CourseFeedback courseFeedback) {
        return getCourseFeedbackDao().delete(courseFeedback);
    }

    @Transactional
    @Override
    public CourseFeedback updateCourseFeedback(CourseFeedback courseFeedback) {
        return getCourseFeedbackDao().update(courseFeedback);
    }

    @Transactional
    @Override
    public CourseFeedback findCourseFeedbackByFeedbackId(CourseFeedback courseFeedback) {
        return getCourseFeedbackDao().findOneById(courseFeedback);
    }

    @Transactional
    @Override
    public FileFeedback createFileFeedback(FileFeedback fileFeedback) {
        return getFeedbackDao().save(fileFeedback);
    }

    @Transactional
    @Override
    public FileFeedback deleteFileFeedbackByFeedbackId(FileFeedback fileFeedback) {
        return getFeedbackDao().delete(fileFeedback);
    }

    @Transactional
    @Override
    public FileFeedback updateFileFeedback(FileFeedback fileFeedback) {
        return getFeedbackDao().update(fileFeedback);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    @Override
    public FileFeedback findFileFeedbackByFeedbackId(FileFeedback fileFeedback) {
        return getFeedbackDao().findOneById(fileFeedback);
    }

    @Transactional
    @Override
    public MessageFeedback createMessageFeedback(MessageFeedback messageFeedback) {
        return getFeedbackDao().save(messageFeedback);
    }

    @Transactional
    @Override
    public MessageFeedback deleteMessageFeedbackByFeedbackId(MessageFeedback messageFeedback) {
        return getFeedbackDao().delete(messageFeedback);
    }

    @Transactional
    @Override
    public MessageFeedback updateMessageFeedback(MessageFeedback messageFeedback) {
        return getFeedbackDao().update(messageFeedback);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    @Override
    public MessageFeedback findMessageFeedbackByFeedbackId(MessageFeedback messageFeedback) {
        return getFeedbackDao().findOneById(messageFeedback);
    }

    @Transactional
    @Override
    public FileMessageFeedback createFileMessageFeedback(FileMessageFeedback fileMessageFeedback) {
        return getFeedbackDao().save(fileMessageFeedback);
    }

    @Transactional
    @Override
    public FileMessageFeedback deleteFileMessageFeedbackByFeedbackId(FileMessageFeedback fileMessageFeedback) {
        return getFeedbackDao().delete(fileMessageFeedback);
    }

    @Transactional
    @Override
    public FileMessageFeedback updateFileMessageFeedback(FileMessageFeedback fileMessageFeedback) {
        return getFeedbackDao().update(fileMessageFeedback);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    @Override
    public FileMessageFeedback findFileMessageFeedbackByFeedbackId(FileMessageFeedback fileMessageFeedback) {
        return getFeedbackDao().findOneById(fileMessageFeedback);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    @Override
    public Set<CourseFeedback> findCourseFeedbackByCourseId(CourseFeedback courseFeedback) {
        return getCourseFeedbackDao().findCourseFeedbackByCourseId(courseFeedback);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    @Override
    public Set<CourseFeedback> findCourseFeedbackAndCommentByCourseId(CourseFeedback courseFeedback) {
        return getCourseFeedbackDao().findCourseFeedbackAndCommentByCourseId(courseFeedback);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    @Override
    public Set<CourseFeedback> findCourseFeedbackLikeForSearch(GeneralSearch generalSearch) {
        return getCourseFeedbackDao().findCourseFeedbackLikePagination(generalSearch);
    }

    @Transactional
    @Override
    public Long getCountCourseFeedbackLikeForSearch(GeneralSearch generalSearch) {
        return getCourseFeedbackDao().getCountCourseFeedbackLikePagination(generalSearch);
    }
}
