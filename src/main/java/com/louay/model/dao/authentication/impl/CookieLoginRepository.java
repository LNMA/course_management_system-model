package com.louay.model.dao.authentication.impl;

import com.louay.model.dao.CommonDaoImpl;
import com.louay.model.dao.authentication.CookieLoginDao;
import com.louay.model.entity.authentication.CookieLogin;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.*;

@Repository
public class CookieLoginRepository extends CommonDaoImpl<CookieLogin> implements CookieLoginDao {
    private static final long serialVersionUID = 7102139416337757502L;

    @Override
    public <S extends CookieLogin> Boolean isExist(S entity) {
        return !getEntityManager().createQuery("SELECT c From CookieLogin c WHERE c.admin.email = :email")
                .setParameter("email", entity.getAdmin().getEmail())
                .setMaxResults(1)
                .getResultList()
                .isEmpty();
    }

    @Override
    public <S extends CookieLogin> S delete(S entity) {
        Class<? extends CookieLogin> entityClass = entity.getClass();
        @SuppressWarnings("unchecked")
        S entityFound = (S) getEntityManager().getReference(entityClass, entity.getAdmin().getEmail());
        getEntityManager().remove(entityFound);
        return entity;
    }

    @Override
    public <S extends CookieLogin> Collection<S> deleteAll(Iterable<S> entities) {
        List<S> result = new ArrayList<>();
        for (S s : entities) {
            Class<? extends CookieLogin> entityClass = s.getClass();
            @SuppressWarnings("unchecked")
            S entityFound = (S) getEntityManager().getReference(entityClass, s.getAdmin().getEmail());
            getEntityManager().remove(entityFound);
            getEntityManager().flush();
            result.add(s);
            getEntityManager().clear();
        }
        return result;
    }

    @Override
    public <S extends CookieLogin> S findOneById(S entity) {
        Class<? extends CookieLogin> entityClass = entity.getClass();
        @SuppressWarnings("unchecked")
        S result = (S) getEntityManager().find(entityClass, entity.getAdmin().getEmail(), LockModeType.PESSIMISTIC_READ);
        return result;
    }

    @Override
    public <S extends CookieLogin> Collection<S> findAllById(Iterable<S> entities) {
        Set<S> result = new HashSet<>();
        for (S s : entities) {
            Class<? extends CookieLogin> entityClass = s.getClass();
            @SuppressWarnings("unchecked")
            S entityFound = (S) getEntityManager().find(entityClass, s.getAdmin().getEmail(), LockModeType.PESSIMISTIC_READ);
            result.add(entityFound);
            getEntityManager().flush();
            getEntityManager().clear();
        }
        return result;
    }
}
