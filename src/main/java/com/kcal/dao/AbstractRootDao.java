package com.kcal.dao;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Work;
import com.googlecode.objectify.cmd.LoadType;
import com.googlecode.objectify.util.Closeable;
import com.kcal.model.RootEntity;
import com.kcal.model.User;

import java.util.List;

import static com.googlecode.objectify.ObjectifyService.ofy;
import static com.googlecode.objectify.ObjectifyService.run;

/**
 * User: Breku
 * Date: 2014-09-13
 */
public abstract class AbstractRootDao<T extends RootEntity> implements RootDao<T> {


    private Class<T> type;

    public AbstractRootDao(Class<T> type) {
        this.type = type;
    }

    public void save(T obj) {
        Closeable closeable = ObjectifyService.begin();
        ObjectifyService.ofy().save().entity(obj).now();
        closeable.close();
    }

    public void update(T obj, String propertyName, Object newValue) {

    }

    public void removeCollection() {
    }

    /**
     * Method is looking for an object by id
     *
     * @param id
     * @return
     */
    public T get(long id) {
        Closeable closeable = ObjectifyService.begin();
        T result = ofy().load().type(type).id(id).now();
        closeable.close();
        return result;
    }

    public List<T> getAll() {
        Closeable closeable = ObjectifyService.begin();
        List<T> list = ofy().load().type(type).list();
        closeable.close();
        return list;
    }


}
