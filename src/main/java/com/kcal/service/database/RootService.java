package com.kcal.service.database;

import com.kcal.model.RootEntity;

import java.util.Collection;
import java.util.List;

/**
 * User: Breku
 * Date: 2014-09-23
 */
public interface RootService<E extends RootEntity> {

    void save(E obj);

    void update(E obj, String propertyName, Object newValue);

    List<E> getAll();

    void removeAll();

    E get(long id);

    void save(Collection<E> collection);
}
