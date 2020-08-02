package com.louay.model.service.comment.impl;

import com.louay.model.dao.comment.CommentDao;
import com.louay.model.entity.feedback.comment.Comment;
import com.louay.model.service.comment.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@Service
public class CommentCrudService implements CommentService, Serializable {
    private static final long serialVersionUID = -8871520160623685053L;
    private CommentDao commentDao;

    public CommentDao getCommentDao() {
        return commentDao;
    }

    @Autowired
    public void setCommentDao(CommentDao commentDao) {
        this.commentDao = commentDao;
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
}
