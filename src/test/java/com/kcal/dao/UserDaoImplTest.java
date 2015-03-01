package com.kcal.dao;

import com.googlecode.objectify.ObjectifyService;
import com.kcal.model.User;
import com.kcal.utils.BuilderTest;

public class UserDaoImplTest extends AbstractRootDaoTest<UserDaoImpl, User> {


    @Override
    protected UserDaoImpl createDao() {
        return new UserDaoImpl();
    }

    @Override
    protected void registerModels() {
        ObjectifyService.register(User.class);
    }

    @Override
    protected User createEntity() {
        return BuilderTest.createUser("Kuba");
    }
}