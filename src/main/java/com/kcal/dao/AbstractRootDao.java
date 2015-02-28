package com.kcal.dao;

import com.kcal.model.RootEntity;
import com.mongodb.Mongo;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

/**
 * User: Breku
 * Date: 2014-09-13
 */
public abstract class AbstractRootDao<T extends RootEntity> implements RootDao<T>{


    protected MongoTemplate template;

    private Class<T> type;

    public AbstractRootDao(MongoTemplate mongoTemplate, Class<T> type) {
        this.template =mongoTemplate;
        this.type = type;
    }

    public void save(T obj) {
        template.save(obj);
    }

    public void update(T obj, String propertyName, Object newValue) {
        Query query = new Query(Criteria.where("_id").is(obj.getId()));
        Update update = new Update();
        update.set(propertyName, newValue);
        template.updateFirst(query, update, type);
    }

    public void removeCollection() {
        template.dropCollection(type);
    }

    /**
     * Method is looking for an object by id
     *
     * @param id
     * @return
     */
    public T get(long id) {
        Query query = new Query(Criteria.where("_id").is(id));
        return (T) template.findOne(query, type);
    }

    public List<T> getAll() {
        return template.findAll(type);
    }


}
