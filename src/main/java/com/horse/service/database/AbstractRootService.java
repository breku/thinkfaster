package com.horse.service.database;

import com.horse.model.RootEntity;
import com.horse.service.RootService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

/**
 * User: Breku
 * Date: 2014-09-13
 */
public abstract class AbstractRootService<E extends RootEntity, T extends JpaRepository<E,Long>> implements RootService<E> {

    protected T dao;

    protected AbstractRootService() {
    }


    public E get(long id) {
        return (E) dao.findOne(id);
    }

    public void save(Collection<E> collection) {
        for (E obj : collection) {
            dao.save(obj);
        }
    }

    public E save(E obj) {
        return dao.save(obj);
    }

    public List<E> getAll() {
        return dao.findAll();
    }

}
