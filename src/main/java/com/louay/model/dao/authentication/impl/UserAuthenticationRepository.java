package com.louay.model.dao.authentication.impl;

import com.louay.model.dao.CommonDaoImpl;
import com.louay.model.dao.authentication.UserAuthenticationDao;
import com.louay.model.entity.authentication.UsersAuthentication;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserAuthenticationRepository extends CommonDaoImpl<UsersAuthentication> implements UserAuthenticationDao {
    private static final long serialVersionUID = -2535159341508753199L;

    @Override
    public <S extends UsersAuthentication> Boolean isExist(S entity) {
        return !getEntityManager().createQuery("SELECT u From UsersAuthentication u WHERE u.userId = :userId")
                .setParameter("userId", entity.getUserId())
                .setMaxResults(1)
                .getResultList()
                .isEmpty();
    }

    @Override
    public <S extends UsersAuthentication> S delete(S entity) {
        Class<? extends UsersAuthentication> entityClass = entity.getClass();
        @SuppressWarnings("unchecked")
        S entityFound = (S) getEntityManager().getReference(entityClass, entity.getUserId());
        getEntityManager().remove(entityFound);
        return entity;
    }

    @Override
    public <S extends UsersAuthentication> Collection<S> deleteAll(Iterable<S> entities) {
        List<S> result = new ArrayList<>();
        for (S s : entities) {
            Class<? extends UsersAuthentication> entityClass = s.getClass();
            @SuppressWarnings("unchecked")
            S entityFound = (S) getEntityManager().getReference(entityClass, s.getUserId());
            getEntityManager().remove(entityFound);
            result.add(s);
        }
        return result;
    }

    @Override
    public <S extends UsersAuthentication> S findOneById(S entity) {
        Class<? extends UsersAuthentication> entityClass = entity.getClass();
        @SuppressWarnings("unchecked")
        S result = (S) getEntityManager().find(entityClass, entity.getUserId());
        return result;
    }

    @Override
    public <S extends UsersAuthentication> Collection<S> findAllById(Iterable<S> entities) {
        Set<S> result = new HashSet<>();
        for (S s : entities) {
            Class<? extends UsersAuthentication> entityClass = s.getClass();
            @SuppressWarnings("unchecked")
            S entityFound = (S) getEntityManager().find(entityClass, s.getUserId());
            result.add(entityFound);
        }
        return result;
    }
}
