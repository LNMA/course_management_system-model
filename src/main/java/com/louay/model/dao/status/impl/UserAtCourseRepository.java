package com.louay.model.dao.status.impl;

import com.louay.model.dao.CommonDaoImpl;
import com.louay.model.dao.status.UserAtCourseDao;
import com.louay.model.entity.status.UserAtCourse;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserAtCourseRepository extends CommonDaoImpl<UserAtCourse> implements UserAtCourseDao {
    private static final long serialVersionUID = -8327756498058565209L;

    @Override
    public <S extends UserAtCourse> Boolean isExist(S entity) {
        return !getEntityManager().createQuery("SELECT u From UserAtCourse u WHERE u.users.email = :email")
                .setParameter("email", entity.getUsers().getEmail())
                .setMaxResults(1)
                .getResultList()
                .isEmpty();
    }

    @Override
    public <S extends UserAtCourse> S delete(S entity) {
        Class<? extends UserAtCourse> entityClass = entity.getClass();
        @SuppressWarnings("unchecked")
        S entityFound = (S) getEntityManager().getReference(entityClass, entity.getUsers().getEmail());
        getEntityManager().remove(entityFound);
        return entity;
    }

    @Override
    public <S extends UserAtCourse> Collection<S> deleteAll(Iterable<S> entities) {
        List<S> result = new ArrayList<>();
        for (S s : entities) {
            Class<? extends UserAtCourse> entityClass = s.getClass();
            @SuppressWarnings("unchecked")
            S entityFound = (S) getEntityManager().getReference(entityClass, s.getUsers().getEmail());
            getEntityManager().remove(entityFound);
            result.add(s);
        }
        return result;
    }

    @Override
    public <S extends UserAtCourse> S findOneById(S entity) {
        Class<? extends UserAtCourse> entityClass = entity.getClass();
        @SuppressWarnings("unchecked")
        S result = (S) getEntityManager().find(entityClass, entity.getUsers().getEmail());
        return result;
    }

    @Override
    public <S extends UserAtCourse> Collection<S> findAllById(Iterable<S> entities) {
        Set<S> result = new HashSet<>();
        for (S s : entities) {
            Class<? extends UserAtCourse> entityClass = s.getClass();
            @SuppressWarnings("unchecked")
            S entityFound = (S) getEntityManager().find(entityClass, s.getUsers().getEmail());
            result.add(entityFound);
        }
        return result;
    }
}
