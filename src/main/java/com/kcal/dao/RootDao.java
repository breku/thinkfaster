package com.kcal.dao;

import com.kcal.model.RootEntity;

import java.util.List;

/**
 * Created by brekol on 28.02.15.
 */
public interface RootDao<T extends RootEntity> {

    public void save(T obj);

    public void update(T obj, String propertyName, Object newValue);

    public void removeCollection();

    /**
     * Method is looking for an object by id
     *
     * @param id
     * @return
     */
    public T get(long id);

    public List<T> getAll();

}
