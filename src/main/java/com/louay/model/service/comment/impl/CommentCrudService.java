package com.louay.model.service.comment.impl;

import com.louay.model.dao.comment.CommentDao;
import com.louay.model.entity.feedback.comment.Comment;
import com.louay.model.service.comment.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Set;

@Service
public class CommentCrudService implements CommentService, Serializable {
    private static final long serialVersionUID = -6581959930717201076L;
    private final CommentDao commentDao;

    @Autowired
    public CommentCrudService(CommentDao commentDao) {
        if (commentDao == null){
            throw new IllegalArgumentException("DAO cannot be null at CommentCrudService.class");
        }
        this.commentDao = commentDao;
    }

    private CommentDao getCommentDao() {
        return commentDao;
    }

    @Transactional
    @Override
    public Comment createComment(Comment comment) {
        return getCommentDao().save(comment);
    }

    @Transactional
    @Override
    public Comment deleteCommentByCommentId(Comment comment) {
        return getCommentDao().delete(comment);
    }

    @Transactional
    @Override
    public Comment updateComment(Comment comment) {
        return getCommentDao().update(comment);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    @Override
    public Comment findCommentByCommentId(Comment comment) {
        return getCommentDao().findOneById(comment);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    @Override
    public Set<Comment> findCommentByFeedbackId(Comment comment) {
        return getCommentDao().findCommentByFeedbackId(comment);
    }
}
