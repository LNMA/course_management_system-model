package com.louay.model.dao;

import java.util.Collection;

public interface GenericDao<T> {
    <S extends T> S delete(S entity);

    <S extends T> Collection<S> deleteAll(Iterable<S> entities);

    <S extends T> S findOneById(S entity);

    <S extends T> Collection<S>  findAllById(Iterable<S> entities);
}
