package com.louay.model.dao.course.impl;

import com.louay.model.dao.CommonDaoImpl;
import com.louay.model.dao.course.CourseDao;
import com.louay.model.entity.courses.Courses;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class CourseRepository extends CommonDaoImpl<Courses> implements CourseDao {
    private static final long serialVersionUID = 5218830081792631594L;

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
            getEntityManager().flush();
            result.add(s);
            getEntityManager().clear();
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
            getEntityManager().flush();
            getEntityManager().clear();
        }
        return result;
    }
}
