package com.louay.model.dao.status.impl;

import com.louay.model.dao.CommonDaoImpl;
import com.louay.model.dao.status.UserSignInDao;
import com.louay.model.entity.status.UserSignIn;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserSignInRepository extends CommonDaoImpl<UserSignIn> implements UserSignInDao {
    private static final long serialVersionUID = -5571898535200015129L;

    @Override
    public <S extends UserSignIn> Boolean isExist(S entity) {
        return !getEntityManager().createQuery("SELECT u From UserSignIn u WHERE u.userSignInId = " +
                ":userSignInId")
                .setParameter("userSignInId", entity.getUserSignInId())
                .setMaxResults(1)
                .getResultList()
                .isEmpty();
    }

    @Override
    public <S extends UserSignIn> S delete(S entity) {
        Class<? extends UserSignIn> entityClass = entity.getClass();
        @SuppressWarnings("unchecked")
        S entityFound = (S) getEntityManager().getReference(entityClass, entity.getUserSignInId());
        getEntityManager().remove(entityFound);
        return entity;
    }

    @Override
    public <S extends UserSignIn> Collection<S> deleteAll(Iterable<S> entities) {
        List<S> result = new ArrayList<>();
        for (S s : entities) {
            Class<? extends UserSignIn> entityClass = s.getClass();
            @SuppressWarnings("unchecked")
            S entityFound = (S) getEntityManager().getReference(entityClass, s.getUserSignInId());
            getEntityManager().remove(entityFound);
            getEntityManager().flush();
            result.add(s);
            getEntityManager().clear();
        }
        return result;
    }

    @Override
    public <S extends UserSignIn> S findOneById(S entity) {
        Class<? extends UserSignIn> entityClass = entity.getClass();
        @SuppressWarnings("unchecked")
        S result = (S) getEntityManager().find(entityClass, entity.getUserSignInId());
        return result;
    }

    @Override
    public <S extends UserSignIn> Collection<S> findAllById(Iterable<S> entities) {
        Set<S> result = new HashSet<>();
        for (S s : entities) {
            Class<? extends UserSignIn> entityClass = s.getClass();
            @SuppressWarnings("unchecked")
            S entityFound = (S) getEntityManager().find(entityClass, s.getUserSignInId());
            result.add(entityFound);
            getEntityManager().flush();
            getEntityManager().clear();
        }
        return result;
    }
}
