package com.kcal.service.database;

import com.kcal.dao.RootDao;
import com.kcal.model.RootEntity;

import java.util.Collection;
import java.util.List;

/**
 * User: Breku
 * Date: 2014-09-13
 */
public abstract class AbstractRootService<E extends RootEntity, T extends RootDao> implements RootService<E> {

    protected T dao;

    protected AbstractRootService(T dao) {
        this.dao = dao;
    }

    public void save(E obj) {
        dao.save(obj);
    }

    public E get(long id) {
        return (E) dao.get(id);
    }

    public void save(Collection<E> collection) {
        for (E obj : collection) {
            dao.save(obj);
        }
    }

    public void update(E obj, String propertyName, Object newValue) {
        dao.update(obj, propertyName, newValue);
    }

    public List<E> getAll() {
        return dao.getAll();
    }

    public void removeAll() {
        dao.removeCollection();
    }


}
