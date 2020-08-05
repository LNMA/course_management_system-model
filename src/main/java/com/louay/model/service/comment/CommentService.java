package com.louay.model.service.comment;

import com.louay.model.entity.feedback.comment.Comment;

public interface CommentService {
    Comment createComment(Comment comment);

    Comment deleteCommentByCommentId(Comment comment);

    Comment updateComment(Comment comment);

    Comment findCommentByCommentId(Comment comment);
}
