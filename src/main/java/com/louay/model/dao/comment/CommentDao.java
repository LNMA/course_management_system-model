package com.louay.model.dao.comment;

import com.louay.model.dao.CommonDao;
import com.louay.model.dao.GenericDao;
import com.louay.model.entity.feedback.comment.Comment;

import java.util.Set;

public interface CommentDao extends CommonDao<Comment>, GenericDao<Comment> {
    Set<Comment> findCommentByFeedbackId(Comment comment);
}
