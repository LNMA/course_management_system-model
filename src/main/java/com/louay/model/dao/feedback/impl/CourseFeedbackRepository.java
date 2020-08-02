package com.louay.model.dao.feedback.impl;

import com.louay.model.dao.CommonDaoImpl;
import com.louay.model.dao.feedback.CourseFeedbackDao;
import com.louay.model.entity.feedback.CourseFeedback;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.*;

@Repository
public class CourseFeedbackRepository extends CommonDaoImpl<CourseFeedback> implements CourseFeedbackDao {
    private static final long serialVersionUID = -910124764053502575L;

    @Override
    public <S extends CourseFeedback> Boolean isExist(S entity) {
        return !getEntityManager().createQuery("SELECT c From CourseFeedback c WHERE " +
                "c.feedbackID = :feedbackID")
                .setParameter("feedbackID", entity.getFeedbackID())
                .setMaxResults(1)
                .getResultList()
                .isEmpty();    }

    @Override
    public <S extends CourseFeedback> S delete(S entity) {
        Class<? extends CourseFeedback> entityClass = entity.getClass();
        @SuppressWarnings("unchecked")
        S entityFound = (S) getEntityManager().find(entityClass, entity.getFeedbackID());
        getEntityManager().remove(entityFound);
        return entity;
    }

    @Override
    public <S extends CourseFeedback> Collection<S> deleteAll(Iterable<S> entities) {
        List<S> result = new ArrayList<>();
        for (S s : entities) {
            Class<? extends CourseFeedback> entityClass = s.getClass();
            @SuppressWarnings("unchecked")
            S entityFound = (S) getEntityManager().find(entityClass, s.getFeedbackID());
            getEntityManager().remove(entityFound);
            getEntityManager().flush();
            result.add(s);
            getEntityManager().clear();
        }
        return result;
    }

    @Override
    public <S extends CourseFeedback> S findOneById(S entity) {
        Class<? extends CourseFeedback> entityClass = entity.getClass();
        @SuppressWarnings("unchecked")
        S result = (S) getEntityManager().find(entityClass, entity.getFeedbackID(), LockModeType.PESSIMISTIC_READ);
        return result;
    }

    @Override
    public <S extends CourseFeedback> Collection<S> findAllById(Iterable<S> entities) {
        Set<S> result = new HashSet<>();
        for (S s : entities) {
            Class<? extends CourseFeedback> entityClass = s.getClass();
            @SuppressWarnings("unchecked")
            S entityFound = (S) getEntityManager().find(entityClass, s.getFeedbackID(), LockModeType.PESSIMISTIC_READ);
            result.add(entityFound);
            getEntityManager().flush();
            getEntityManager().clear();
        }
        return result;
    }
}
