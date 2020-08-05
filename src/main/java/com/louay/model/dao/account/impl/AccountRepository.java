package com.louay.model.dao.account.impl;

import com.louay.model.dao.CommonDaoImpl;
import com.louay.model.dao.account.AccountDao;
import com.louay.model.entity.users.Accounts;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.*;

@Repository
public class AccountRepository extends CommonDaoImpl<Accounts> implements AccountDao {
    private static final long serialVersionUID = -4047258463169659635L;

    @Override
    public <S extends Accounts> Boolean isExist(S entity) {
        return !getEntityManager().createQuery("SELECT a From Admin a WHERE a.email = :email")
                .setParameter("email", entity.getEmail())
                .setMaxResults(1)
                .getResultList()
                .isEmpty();
    }

    @Override
    public <S extends Accounts> S delete(S entity) {
        Class<? extends Accounts> entityClass = entity.getClass();
        @SuppressWarnings("unchecked")
        S entityFound = (S) getEntityManager().getReference(entityClass, entity.getEmail());
        getEntityManager().remove(entityFound);
        return entity;
    }

    @Override
    public <S extends Accounts> Collection<S> deleteAll(Iterable<S> entities) {
        List<S> result = new ArrayList<>();
        for (S s : entities) {
            Class<? extends Accounts> entityClass = s.getClass();
            @SuppressWarnings("unchecked")
            S entityFound = (S) getEntityManager().getReference(entityClass, s.getEmail());
            getEntityManager().remove(entityFound);
            getEntityManager().flush();
            result.add(s);
            getEntityManager().clear();
        }
        return result;
    }

    @Override
    public <S extends Accounts> S findOneById(S entity) {
        Class<? extends Accounts> entityClass = entity.getClass();
        @SuppressWarnings("unchecked")
        S result = (S) getEntityManager().find(entityClass, entity.getEmail(), LockModeType.PESSIMISTIC_READ);
        return result;
    }

    @Override
    public <S extends Accounts> Collection<S> findAllById(Iterable<S> entities) {
        Set<S> result = new HashSet<>();
        for (S s : entities) {
            Class<? extends Accounts> entityClass = s.getClass();
            @SuppressWarnings("unchecked")
            S entityFound = (S) getEntityManager().find(entityClass, s.getEmail(), LockModeType.PESSIMISTIC_READ);
            result.add(entityFound);
            getEntityManager().flush();
            getEntityManager().clear();
        }
        return result;
    }
}
