package com.kcal.dao;

import com.kcal.model.User;

import java.util.List;

/**
 * Created by brekol on 28.02.15.
 */
public interface UserDao extends RootDao<User> {

    User findByUsername(String username);

    List<User> getAll();
}
