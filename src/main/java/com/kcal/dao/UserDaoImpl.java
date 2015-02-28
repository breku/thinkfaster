package com.kcal.dao;


import com.kcal.model.User;
import com.mongodb.Mongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: Breku
 * Date: 2014-09-16
 */
@Repository
public class UserDaoImpl extends AbstractRootDao<User> implements UserDao{

    @Autowired
    public UserDaoImpl(MongoTemplate template) {
        super(template,User.class);
    }

    public User findByUsername(String username) {
        Query query = new Query(Criteria.where("username").is(username));
        return template.findOne(query, User.class);
    }

    @Override
    public List<User> getAll() {
        Query query = new Query();
        query.with(new Sort(Sort.Direction.ASC, "username"));
        return template.find(query, User.class);
    }


}
