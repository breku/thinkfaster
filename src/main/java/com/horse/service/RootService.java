package com.horse.service;

import com.horse.model.RootEntity;

import java.util.Collection;
import java.util.List;

/**
 * User: Breku
 * Date: 2014-09-23
 */
public interface RootService<E extends RootEntity> {


    E save(E obj);

    List<E> getAll();

    E get(long id);

    void save(Collection<E> collection);
}
