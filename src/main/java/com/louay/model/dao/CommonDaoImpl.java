package com.louay.model.dao;

import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository
public abstract class CommonDaoImpl<T> implements CommonDao<T>, Serializable {
    private static final long serialVersionUID = -3503295155238414658L;
    private EntityManager entityManager;
    private final String NOT_NULL_ENTITY = "entity must not be null!.";

    public EntityManager getEntityManager() {
        return entityManager;
    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public <S extends T> S save(S entity) {
        Assert.notNull(entity, NOT_NULL_ENTITY);
        getEntityManager().persist(entity);
        return entity;
    }

    @Override
    public <S extends T> Collection<S> saveAll(Iterable<S> entities) {
        List<S> result = new ArrayList<>();
        for (S s : entities) {
            Assert.notNull(s, NOT_NULL_ENTITY);
            getEntityManager().persist(s);
            result.add(s);
        }
        return result;
    }

    @Override
    public <S extends T> S update(S entity) {
        Assert.notNull(entity, NOT_NULL_ENTITY);
        if (isExist(entity)) {
            getEntityManager().merge(entity);
            return entity;
        } else {
            throw new UnsupportedOperationException("Can not update not exist entity!.");
        }
    }

    @Override
    public <S extends T> Collection<S> updateAll(Iterable<S> entities) {
        List<S> result = new ArrayList<>();
        try {
            for (S s : entities) {
                Assert.notNull(s, NOT_NULL_ENTITY);
                if (isExist(s)) {
                    getEntityManager().merge(s);
                    result.add(s);
                } else {
                    @SuppressWarnings("unchecked")
                    S emptyClass = (S) s.getClass().getConstructor().newInstance();
                    result.add(emptyClass);
                }
            }
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    abstract public <S extends T> Boolean isExist(S entity);
}
