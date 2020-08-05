package com.louay.model.dao.feedback.impl;

import com.louay.model.dao.CommonDaoImpl;
import com.louay.model.dao.feedback.FeedbackDao;
import com.louay.model.entity.feedback.FeedbackContent;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
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
            getEntityManager().flush();
            result.add(s);
            getEntityManager().clear();
        }
        return result;
    }

    @Override
    public <S extends FeedbackContent> S findOneById(S entity) {
        Class<? extends FeedbackContent> entityClass = entity.getClass();
        @SuppressWarnings("unchecked")
        S result = (S) getEntityManager().find(entityClass, entity.getCourseFeedback().getFeedbackID(), LockModeType.PESSIMISTIC_READ);
        return result;
    }

    @Override
    public <S extends FeedbackContent> Collection<S> findAllById(Iterable<S> entities) {
        Set<S> result = new HashSet<>();
        for (S s : entities) {
            Class<? extends FeedbackContent> entityClass = s.getClass();
            @SuppressWarnings("unchecked")
            S entityFound = (S) getEntityManager().find(entityClass, s.getCourseFeedback().getFeedbackID(), LockModeType.PESSIMISTIC_READ);
            result.add(entityFound);
            getEntityManager().flush();
            getEntityManager().clear();
        }
        return result;
    }
}
