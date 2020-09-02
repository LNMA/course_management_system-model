package com.louay.model.dao.course.impl;

import com.louay.model.dao.CommonDaoImpl;
import com.louay.model.dao.course.CourseDao;
import com.louay.model.entity.courses.Courses;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.*;

@Repository
public class CourseRepository extends CommonDaoImpl<Courses> implements CourseDao {
    private static final long serialVersionUID = -8297092976480516498L;

    @Override
    public <S extends Courses> Boolean isExist(S entity) {
        return !getEntityManager().createQuery("SELECT c From Courses c WHERE c.courseID = :courseID")
                .setParameter("courseID", entity.getCourseID())
                .setMaxResults(1)
                .getResultList()
                .isEmpty();
    }

    @Override
    public <S extends Courses> S delete(S entity) {
        Class<? extends Courses> entityClass = entity.getClass();
        @SuppressWarnings("unchecked")
        S entityFound = (S) getEntityManager().getReference(entityClass, entity.getCourseID());
        getEntityManager().remove(entityFound);
        return entity;
    }

    @Override
    public <S extends Courses> Collection<S> deleteAll(Iterable<S> entities) {
        List<S> result = new ArrayList<>();
        for (S s : entities) {
            Class<? extends Courses> entityClass = s.getClass();
            @SuppressWarnings("unchecked")
            S entityFound = (S) getEntityManager().getReference(entityClass, s.getCourseID());
            getEntityManager().remove(entityFound);
            result.add(s);
        }
        return result;
    }

    @Override
    public <S extends Courses> S findOneById(S entity) {
        Class<? extends Courses> entityClass = entity.getClass();
        @SuppressWarnings("unchecked")
        S result = (S) getEntityManager().find(entityClass, entity.getCourseID());
        return result;
    }

    @Override
    public <S extends Courses> Collection<S> findAllById(Iterable<S> entities) {
        Set<S> result = new HashSet<>();
        for (S s : entities) {
            Class<? extends Courses> entityClass = s.getClass();
            @SuppressWarnings("unchecked")
            S entityFound = (S) getEntityManager().find(entityClass, s.getCourseID());
            result.add(entityFound);
        }
        return result;
    }

    @Override
    public List<Courses> findAllCourse(int pageNumber, int pageSize) {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();

        CriteriaQuery<Courses> criteriaQuery = criteriaBuilder.createQuery(Courses.class);
        Root<Courses> from = criteriaQuery.from(Courses.class);
        CriteriaQuery<Courses> select = criteriaQuery.select(from);

        TypedQuery<Courses> typedQuery = getEntityManager().createQuery(select);
        int firstResultValue = (pageNumber - 1) * pageSize;
        typedQuery.setFirstResult(firstResultValue);
        typedQuery.setMaxResults(pageSize);

        return typedQuery.getResultList();
    }

    @Override
    public Long getCountRow() {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();

        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        countQuery.select(criteriaBuilder.count(countQuery.from(Courses.class)));

        return getEntityManager().createQuery(countQuery).getSingleResult();
    }
}
