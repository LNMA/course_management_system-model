package com.louay.model.dao.userpic.impl;

import com.louay.model.dao.CommonDaoImpl;
import com.louay.model.dao.userpic.AccountPictureDao;
import com.louay.model.entity.users.picute.AccountPicture;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.*;

@Repository
public class AccountPictureRepository extends CommonDaoImpl<AccountPicture> implements AccountPictureDao {
    private static final long serialVersionUID = -8078667522346426810L;

    @Override
    public <S extends AccountPicture> Boolean isExist(S entity) {
        return !getEntityManager().createQuery("SELECT r From AccountPicture r WHERE r.admin.email = :roleID")
                .setParameter("roleID", entity.getAdmin().getEmail())
                .setMaxResults(1)
                .getResultList()
                .isEmpty();
    }

    @Override
    public <S extends AccountPicture> S delete(S entity) {
        Class<? extends AccountPicture> entityClass = entity.getClass();
        @SuppressWarnings("unchecked")
        S entityFound = (S) getEntityManager().find(entityClass, entity.getAdmin().getEmail());
        getEntityManager().remove(entityFound);
        return entity;
    }

    @Override
    public <S extends AccountPicture> Collection<S> deleteAll(Iterable<S> entities) {
        List<S> result = new ArrayList<>();
        for (S s : entities) {
            Class<? extends AccountPicture> entityClass = s.getClass();
            @SuppressWarnings("unchecked")
            S entityFound = (S) getEntityManager().find(entityClass, s.getAdmin().getEmail());
            getEntityManager().remove(entityFound);
            getEntityManager().flush();
            result.add(s);
            getEntityManager().clear();
        }
        return result;
    }

    @Override
    public <S extends AccountPicture> S findOneById(S entity) {
        Class<? extends AccountPicture> entityClass = entity.getClass();
        @SuppressWarnings("unchecked")
        S result = (S) getEntityManager().find(entityClass, entity.getAdmin().getEmail(), LockModeType.PESSIMISTIC_READ);
        return result;
    }

    @Override
    public <S extends AccountPicture> Collection<S> findAllById(Iterable<S> entities) {
        Set<S> result = new HashSet<>();
        for (S s : entities) {
            Class<? extends AccountPicture> entityClass = s.getClass();
            @SuppressWarnings("unchecked")
            S entityFound = (S) getEntityManager().find(entityClass, s.getAdmin().getEmail(), LockModeType.PESSIMISTIC_READ);
            result.add(entityFound);
            getEntityManager().flush();
            getEntityManager().clear();
        }
        return result;
    }
}
