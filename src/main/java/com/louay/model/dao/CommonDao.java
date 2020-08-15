package com.louay.model.dao;

import java.util.Collection;

public interface CommonDao<T> {
    <S extends T> S save(S entity);

    <S extends T> Collection<S> saveAll(Iterable<S> entities);

    <S extends T> S update(S entity);

    <S extends T> Collection<S> updateAll(Iterable<S> entities);

    <S extends T> Boolean isExist(S entity);
}
