package com.louay.model.dao.feedback.impl;

import com.louay.model.dao.CommonDaoImpl;
import com.louay.model.dao.feedback.FeedbackDao;
import com.louay.model.entity.feedback.FeedbackContent;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class FeedbackRepository extends CommonDaoImpl<FeedbackContent> implements FeedbackDao {
    private static final long serialVersionUID = -6073860615605037324L;

    @Override
    public <S extends FeedbackContent> Boolean isExist(S entity) {
        return !getEntityManager().createQuery("SELECT f From FeedbackContent f WHERE " +
                "f.courseFeedback.feedbackID = :feedbackID")
                .setParameter("feedbackID", entity.getCourseFeedback().getFeedbackID())
                .setMaxResults(1)
                .getResultList()
                .isEmpty();
    }

    @Override
    public <S extends FeedbackContent> S delete(S entity) {
        Class<? extends FeedbackContent> entityClass = entity.getClass();
        @SuppressWarnings("unchecked")
        S entityFound = (S) getEntityManager().getReference(entityClass, entity.getCourseFeedback().getFeedbackID());
        getEntityManager().remove(entityFound);
        return entity;
    }

    @Override
    public <S extends FeedbackContent> Collection<S> deleteAll(Iterable<S> entities) {
        List<S> result = new ArrayList<>();
        for (S s : entities) {
            Class<? extends FeedbackContent> entityClass = s.getClass();
            @SuppressWarnings("unchecked")
            S entityFound = (S) getEntityManager().getReference(entityClass, s.getCourseFeedback().getFeedbackID());
            getEntityManager().remove(entityFound);
            result.add(s);
        }
        return result;
    }

    @Override
    public <S extends FeedbackContent> S findOneById(S entity) {
        Class<? extends FeedbackContent> entityClass = entity.getClass();
        @SuppressWarnings("unchecked")
        S result = (S) getEntityManager().find(entityClass, entity.getCourseFeedback().getFeedbackID());
        return result;
    }

    @Override
    public <S extends FeedbackContent> Collection<S> findAllById(Iterable<S> entities) {
        Set<S> result = new HashSet<>();
        for (S s : entities) {
            Class<? extends FeedbackContent> entityClass = s.getClass();
            @SuppressWarnings("unchecked")
            S entityFound = (S) getEntityManager().find(entityClass, s.getCourseFeedback().getFeedbackID());
            result.add(entityFound);
        }
        return result;
    }
}
