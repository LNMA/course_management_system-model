package com.louay.model.dao.status.impl;

import com.louay.model.dao.CommonDaoImpl;
import com.louay.model.dao.status.UserAccountStatusDao;
import com.louay.model.entity.status.UserAccountStatus;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserAccountStatusRepository extends CommonDaoImpl<UserAccountStatus> implements UserAccountStatusDao {

    private static final long serialVersionUID = -7163964762588653278L;

    @Override
    public <S extends UserAccountStatus> Boolean isExist(S entity) {
        return !getEntityManager().createQuery("SELECT u From UserAccountStatus u WHERE u.users.email" +
                " = :email")
                .setParameter("email", entity.getUsers().getEmail())
                .setMaxResults(1)
                .getResultList()
                .isEmpty();
    }

    @Override
    public <S extends UserAccountStatus> S delete(S entity) {
        Class<? extends UserAccountStatus> entityClass = entity.getClass();
        @SuppressWarnings("unchecked")
        S entityFound = (S) getEntityManager().getReference(entityClass, entity.getUsers().getEmail());
        getEntityManager().remove(entityFound);
        return entity;
    }

    @Override
    public <S extends UserAccountStatus> Collection<S> deleteAll(Iterable<S> entities) {
        List<S> result = new ArrayList<>();
        for (S s : entities) {
            Class<? extends UserAccountStatus> entityClass = s.getClass();
            @SuppressWarnings("unchecked")
            S entityFound = (S) getEntityManager().getReference(entityClass, s.getUsers().getEmail());
            getEntityManager().remove(entityFound);
            result.add(s);
        }
        return result;
    }

    @Override
    public <S extends UserAccountStatus> S findOneById(S entity) {
        Class<? extends UserAccountStatus> entityClass = entity.getClass();
        @SuppressWarnings("unchecked")
        S result = (S) getEntityManager().find(entityClass, entity.getUsers().getEmail());
        return result;
    }

    @Override
    public <S extends UserAccountStatus> Collection<S> findAllById(Iterable<S> entities) {
        Set<S> result = new HashSet<>();
        for (S s : entities) {
            Class<? extends UserAccountStatus> entityClass = s.getClass();
            @SuppressWarnings("unchecked")
            S entityFound = (S) getEntityManager().find(entityClass, s.getUsers().getEmail());
            result.add(entityFound);
        }
        return result;
    }
}
