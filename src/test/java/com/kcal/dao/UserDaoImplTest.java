package com.kcal.dao;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.TxnType;
import com.googlecode.objectify.util.Closeable;
import com.kcal.model.User;
import com.kcal.utils.BuilderTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.fest.assertions.Assertions.assertThat;

public class UserDaoImplTest {

    private final LocalServiceTestHelper helper =
            new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig()
                    .setDefaultHighRepJobPolicyUnappliedJobPercentage(100));

    private UserDaoImpl uut;

    @Before
    public void setUp() {
        helper.setUp();
        ObjectifyService.register(User.class);

        uut = new UserDaoImpl();

    }

    @Test
    public void shouldSaveCountType() {

        // given
        User user = BuilderTest.createUser("Kuba");

        // when
        uut.save(user);


        // then
        assertThat(user.getId()).isNotNull();

    }


    @Test
    public void shouldRetrieveUserById() {
        // given
        User user = BuilderTest.createUser("Kuba");
        uut.save(user);

        // when
        User result = uut.get(user.getId());

        // then
        assertThat(result).isNotNull();

    }



    @Test
    public void shouldRetrieveUserByUsername() {
        // given
        User user = BuilderTest.createUser("Kuba");
        uut.save(user);

        // when
        User result = uut.findByUsername("Kuba");

        // then
        assertThat(result).isNotNull();


    }



    @Test
    public void shouldRetrieveAllUsers() {
        // given
        User u1 = BuilderTest.createUser("Kuba");
        User u2 = BuilderTest.createUser("Kuba");
        uut.save(u1);
        uut.save(u2);

        // when
        List<User> result = uut.getAll();

        // then
        assertThat(result).isNotNull();
        assertThat(result).hasSize(2);
    }


    @After
    public void tearDown() {

        helper.tearDown();
    }

}