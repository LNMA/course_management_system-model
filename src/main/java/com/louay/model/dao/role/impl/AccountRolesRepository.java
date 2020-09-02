package com.louay.model.dao.role.impl;

import com.louay.model.dao.CommonDaoImpl;
import com.louay.model.dao.role.AccountRolesDao;
import com.louay.model.entity.role.AccountsRoles;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class AccountRolesRepository extends CommonDaoImpl<AccountsRoles> implements AccountRolesDao {
    private static final long serialVersionUID = 3647710673075206869L;

    @Override
    public <S extends AccountsRoles> Boolean isExist(S entity) {
        return !getEntityManager().createQuery("SELECT r From AccountsRoles r WHERE r.roleID = :roleID")
                .setParameter("roleID", entity.getRoleID())
                .setMaxResults(1)
                .getResultList()
                .isEmpty();
    }

    @Override
    public <S extends AccountsRoles> S delete(S entity) {
        Class<? extends AccountsRoles> entityClass = entity.getClass();
        @SuppressWarnings("unchecked")
        S entityFound = (S) getEntityManager().getReference(entityClass, entity.getRoleID());
        getEntityManager().remove(entityFound);
        return entity;
    }

    @Override
    public <S extends AccountsRoles> Collection<S> deleteAll(Iterable<S> entities) {
        List<S> result = new ArrayList<>();
        for (S s : entities) {
            Class<? extends AccountsRoles> entityClass = s.getClass();
            @SuppressWarnings("unchecked")
            S entityFound = (S) getEntityManager().getReference(entityClass, s.getRoleID());
            getEntityManager().remove(entityFound);
            result.add(s);
        }
        return result;
    }

    @Override
    public <S extends AccountsRoles> S findOneById(S entity) {
        Class<? extends AccountsRoles> entityClass = entity.getClass();
        @SuppressWarnings("unchecked")
        S result = (S) getEntityManager().find(entityClass, entity.getRoleID());
        return result;
    }

    @Override
    public <S extends AccountsRoles> Collection<S> findAllById(Iterable<S> entities) {
        Set<S> result = new HashSet<>();
        for (S s : entities) {
            Class<? extends AccountsRoles> entityClass = s.getClass();
            @SuppressWarnings("unchecked")
            S entityFound = (S) getEntityManager().find(entityClass, s.getRoleID());
            result.add(entityFound);
        }
        return result;
    }
}
