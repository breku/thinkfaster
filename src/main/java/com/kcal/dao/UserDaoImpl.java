package com.kcal.dao;


import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.cmd.LoadType;
import com.googlecode.objectify.util.Closeable;
import com.kcal.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * User: Breku
 * Date: 2014-09-16
 */
@Repository
public class UserDaoImpl extends AbstractRootDao<User> implements UserDao {

    static {
        ObjectifyService.register(User.class);
    }


    public UserDaoImpl() {
        super(User.class);
    }

    public User findByUsername(String username) {
        Closeable closeable = ObjectifyService.begin();
        User user = ofy().load().type(User.class).filter("username",username).first().now();
//        closeable.close();
        return user;
    }




}
