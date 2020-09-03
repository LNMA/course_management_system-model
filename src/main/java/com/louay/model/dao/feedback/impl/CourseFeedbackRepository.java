package com.louay.model.dao.feedback.impl;

import com.louay.model.dao.CommonDaoImpl;
import com.louay.model.dao.feedback.CourseFeedbackDao;
import com.louay.model.entity.feedback.CourseFeedback;
import com.louay.model.entity.wrapper.GeneralSearch;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class CourseFeedbackRepository extends CommonDaoImpl<CourseFeedback> implements CourseFeedbackDao {
    private static final long serialVersionUID = 7489676737444211990L;

    @Override
    public <S extends CourseFeedback> Boolean isExist(S entity) {
        return !getEntityManager().createQuery("SELECT c From CourseFeedback c WHERE " +
                "c.feedbackID = :feedbackID")
                .setParameter("feedbackID", entity.getFeedbackID())
                .setMaxResults(1)
                .getResultList()
                .isEmpty();
    }

    @Override
    public <S extends CourseFeedback> S delete(S entity) {
        Class<? extends CourseFeedback> entityClass = entity.getClass();
        @SuppressWarnings("unchecked")
        S entityFound = (S) getEntityManager().getReference(entityClass, entity.getFeedbackID());
        getEntityManager().remove(entityFound);
        return entity;
    }

    @Override
    public <S extends CourseFeedback> Collection<S> deleteAll(Iterable<S> entities) {
        List<S> result = new ArrayList<>();
        for (S s : entities) {
            Class<? extends CourseFeedback> entityClass = s.getClass();
            @SuppressWarnings("unchecked")
            S entityFound = (S) getEntityManager().getReference(entityClass, s.getFeedbackID());
            getEntityManager().remove(entityFound);
            result.add(s);
        }
        return result;
    }

    @Override
    public <S extends CourseFeedback> S findOneById(S entity) {
        Class<? extends CourseFeedback> entityClass = entity.getClass();
        @SuppressWarnings("unchecked")
        S result = (S) getEntityManager().find(entityClass, entity.getFeedbackID());
        return result;
    }

    @Override
    public <S extends CourseFeedback> Collection<S> findAllById(Iterable<S> entities) {
        Set<S> result = new HashSet<>();
        for (S s : entities) {
            Class<? extends CourseFeedback> entityClass = s.getClass();
            @SuppressWarnings("unchecked")
            S entityFound = (S) getEntityManager().find(entityClass, s.getFeedbackID());
            result.add(entityFound);
        }
        return result;
    }

    @Override
    public Set<CourseFeedback> findCourseFeedbackByCourseId(CourseFeedback courseFeedback) {
        List<CourseFeedback> courseFeedbackList = getEntityManager().createQuery("SELECT cf FROM " +
                "CourseFeedback cf WHERE cf.course.courseID = :CourseId", CourseFeedback.class)
                .setParameter("CourseId", courseFeedback.getCourse().getCourseID())
                .getResultList();
        return new HashSet<>(courseFeedbackList);
    }

    @Override
    public Set<CourseFeedback> findCourseFeedbackAndCommentByCourseId(CourseFeedback courseFeedback) {
        List<CourseFeedback> courseFeedbackList = getEntityManager().createQuery("SELECT cf FROM " +
                "CourseFeedback cf LEFT JOIN FETCH cf.comment c WHERE " +
                "cf.course.courseID = :CourseId", CourseFeedback.class)
                .setParameter("CourseId", courseFeedback.getCourse().getCourseID())
                .getResultList();
        return new HashSet<>(courseFeedbackList);
    }

    @Override
    public Set<CourseFeedback> findCourseFeedbackLikePagination(GeneralSearch generalSearch) {
        String key = generalSearch.getKey()+"%";
        int firstResultValue = (generalSearch.getPageNumber() - 1) * generalSearch.getPageSize();
        List<CourseFeedback> courseFeedbackList = getEntityManager().createQuery("SELECT cf FROM CourseFeedback cf WHERE " +
                "cf.user.email LIKE :email", CourseFeedback.class)
                .setParameter("email", key)
                .setFirstResult(firstResultValue)
                .setMaxResults(generalSearch.getPageSize())
                .getResultList();

        return new HashSet<>(courseFeedbackList);
    }

    @Override
    public Long getCountCourseFeedbackLikePagination(GeneralSearch generalSearch) {
        String key = generalSearch.getKey()+"%";
        return getEntityManager().createQuery("SELECT COUNT(cf) FROM CourseFeedback cf WHERE " +
                "cf.user.email LIKE :email", Long.class)
                .setParameter("email", key)
                .setMaxResults(1)
                .getResultList()
                .get(0);
    }
}
