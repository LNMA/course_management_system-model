package com.louay.model.dao.userpic.impl;

import com.louay.model.dao.CommonDaoImpl;
import com.louay.model.dao.userpic.AccountPictureDao;
import com.louay.model.entity.users.picute.AccountPicture;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class AccountPictureRepository extends CommonDaoImpl<AccountPicture> implements AccountPictureDao {

    private static final long serialVersionUID = -8078667522346426810L;

    @Override
    public <S extends AccountPicture> Boolean isExist(S entity) {
        return !getEntityManager().createQuery("SELECT p From AccountPicture p WHERE p.users.email = :email")
                .setParameter("email", entity.getUsers().getEmail())
                .setMaxResults(1)
                .getResultList()
                .isEmpty();
    }

    @Override
    public <S extends AccountPicture> S delete(S entity) {
        Class<? extends AccountPicture> entityClass = entity.getClass();
        @SuppressWarnings("unchecked")
        S entityFound = (S) getEntityManager().getReference(entityClass, entity.getUsers().getEmail());
        getEntityManager().remove(entityFound);
        return entity;
    }

    @Override
    public <S extends AccountPicture> Collection<S> deleteAll(Iterable<S> entities) {
        List<S> result = new ArrayList<>();
        for (S s : entities) {
            Class<? extends AccountPicture> entityClass = s.getClass();
            @SuppressWarnings("unchecked")
            S entityFound = (S) getEntityManager().getReference(entityClass, s.getUsers().getEmail());
            getEntityManager().remove(entityFound);
            result.add(s);
        }
        return result;
    }

    @Override
    public <S extends AccountPicture> S findOneById(S entity) {
        Class<? extends AccountPicture> entityClass = entity.getClass();
        @SuppressWarnings("unchecked")
        S result = (S) getEntityManager().find(entityClass, entity.getUsers().getEmail());
        return result;
    }

    @Override
    public <S extends AccountPicture> Collection<S> findAllById(Iterable<S> entities) {
        Set<S> result = new HashSet<>();
        for (S s : entities) {
            Class<? extends AccountPicture> entityClass = s.getClass();
            @SuppressWarnings("unchecked")
            S entityFound = (S) getEntityManager().find(entityClass, s.getUsers().getEmail());
            result.add(entityFound);
        }
        return result;
    }
}
