package com.kcal.dao;

import com.kcal.model.User;
import com.kcal.model.UserMeal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * User: Breku
 * Date: 2014-09-24
 */
@Repository
public class UserMealDaoImpl extends AbstractRootDao<UserMeal> implements UserMealDao {

    public UserMealDaoImpl() {
        super(UserMeal.class);
    }

    public UserMeal findMealByNameAndUser(String name, User user) {
        return null;
    }


}
