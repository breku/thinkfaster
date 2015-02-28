package com.horse.dao;

import com.horse.AbstractSpringTestConfiguration;
import com.horse.model.User;
import com.horse.utils.TestBuilder;
import org.fest.assertions.Assertions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserDaoTest extends AbstractSpringTestConfiguration{

    @Autowired
    UserDao userDao;

    @Test
    public void shouldRetriveUserFromDatabase(){

        // given
        User user = TestBuilder.createDBUser("kuba");

        // when
        userDao.save(user);

        // then
        User kuba = userDao.findByUsername("kuba");
        Assertions.assertThat(kuba.getEmail()).isEqualTo("kuba@gmail.com");

    }

    @Test
    public void shouldPasswordBeProtected(){

        // given
        User user = TestBuilder.createDBUser("kuba");

        // when
        userDao.save(user);

        // then
        User result = userDao.findByUsername("kuba");
        Assertions.assertThat(result.toString()).contains("password=[PROTECTED]");
        Assertions.assertThat(result.toString()).doesNotContain("password=password");

    }



}