package com.louay.model.dao.comment.impl;

import com.louay.model.dao.CommonDaoImpl;
import com.louay.model.dao.comment.CommentDao;
import com.louay.model.entity.feedback.comment.Comment;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class CommentRepository extends CommonDaoImpl<Comment> implements CommentDao {
    private static final long serialVersionUID = 6697761201933412695L;

    @Override
    public <S extends Comment> Boolean isExist(S entity) {
        return !getEntityManager().createQuery("SELECT c From Comment c WHERE c.commentID = :commentID")
                .setParameter("commentID", entity.getCommentID())
                .setMaxResults(1)
                .getResultList()
                .isEmpty();
    }

    @Override
    public <S extends Comment> S delete(S entity) {
        Class<? extends Comment> entityClass = entity.getClass();
        @SuppressWarnings("unchecked")
        S entityFound = (S) getEntityManager().getReference(entityClass, entity.getCommentID());
        getEntityManager().remove(entityFound);
        return entity;
    }

    @Override
    public <S extends Comment> Collection<S> deleteAll(Iterable<S> entities) {
        List<S> result = new ArrayList<>();
        for (S s : entities) {
            Class<? extends Comment> entityClass = s.getClass();
            @SuppressWarnings("unchecked")
            S entityFound = (S) getEntityManager().getReference(entityClass, s.getCommentID());
            getEntityManager().remove(entityFound);
            result.add(s);
        }
        return result;
    }

    @Override
    public <S extends Comment> S findOneById(S entity) {
        Class<? extends Comment> entityClass = entity.getClass();
        @SuppressWarnings("unchecked")
        S result = (S) getEntityManager().find(entityClass, entity.getCommentID());
        return result;
    }

    @Override
    public <S extends Comment> Collection<S> findAllById(Iterable<S> entities) {
        Set<S> result = new HashSet<>();
        for (S s : entities) {
            Class<? extends Comment> entityClass = s.getClass();
            @SuppressWarnings("unchecked")
            S entityFound = (S) getEntityManager().find(entityClass, s.getCommentID());
            result.add(entityFound);
        }
        return result;
    }

    @Override
    public Set<Comment> findCommentByFeedbackId(Comment comment) {
        List<Comment> commentList = getEntityManager().createQuery("SELECT c FROM Comment c WHERE " +
                "c.courseFeedback.feedbackID = :feedbackID", Comment.class)
                .setParameter("feedbackID", comment.getCourseFeedback().getFeedbackID())
                .getResultList();
        return new HashSet<>(commentList);
    }
}
