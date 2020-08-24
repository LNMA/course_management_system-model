package com.louay.model.dao.role.impl;

import com.louay.model.dao.CommonDaoImpl;
import com.louay.model.dao.role.UsersRolesDao;
import com.louay.model.entity.role.UsersRoles;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UsersRolesRepository extends CommonDaoImpl<UsersRoles> implements UsersRolesDao {
    private static final long serialVersionUID = 970518634702550032L;

    @Override
    public <S extends UsersRoles> Boolean isExist(S entity) {
        return !getEntityManager().createQuery("SELECT r From UsersRoles r WHERE r.users.email = :email")
                .setParameter("email", entity.getUsers().getEmail())
                .setMaxResults(1)
                .getResultList()
                .isEmpty();
    }

    @Override
    public <S extends UsersRoles> S delete(S entity) {
        Class<? extends UsersRoles> entityClass = entity.getClass();
        @SuppressWarnings("unchecked")
        S entityFound = (S) getEntityManager().getReference(entityClass, entity.getUsers().getEmail());
        getEntityManager().remove(entityFound);
        return entity;
    }

    @Override
    public <S extends UsersRoles> Collection<S> deleteAll(Iterable<S> entities) {
        List<S> result = new ArrayList<>();
        for (S s : entities) {
            Class<? extends UsersRoles> entityClass = s.getClass();
            @SuppressWarnings("unchecked")
            S entityFound = (S) getEntityManager().getReference(entityClass, s.getUsers().getEmail());
            getEntityManager().remove(entityFound);
            getEntityManager().flush();
            result.add(s);
            getEntityManager().clear();
        }
        return result;
    }

    @Override
    public <S extends UsersRoles> S findOneById(S entity) {
        Class<? extends UsersRoles> entityClass = entity.getClass();
        @SuppressWarnings("unchecked")
        S result = (S) getEntityManager().find(entityClass, entity.getUsers().getEmail());
        return result;
    }

    @Override
    public <S extends UsersRoles> Collection<S> findAllById(Iterable<S> entities) {
        Set<S> result = new HashSet<>();
        for (S s : entities) {
            Class<? extends UsersRoles> entityClass = s.getClass();
            @SuppressWarnings("unchecked")
            S entityFound = (S) getEntityManager().find(entityClass, s.getUsers().getEmail());
            result.add(entityFound);
            getEntityManager().flush();
            getEntityManager().clear();
        }
        return result;
    }
}
