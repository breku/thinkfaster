package com.kcal.dao;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.util.Closeable;
import com.kcal.model.CountType;
import com.kcal.model.User;
import com.kcal.utils.BuilderTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static com.googlecode.objectify.ObjectifyService.begin;
import static com.googlecode.objectify.ObjectifyService.ofy;
import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.*;

public class CountTypeDaoImplTest {

    private final LocalServiceTestHelper helper =
            new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig()
                    .setDefaultHighRepJobPolicyUnappliedJobPercentage(100));

    private CountTypeDaoImpl uut;

    protected Closeable session;



    @Before
    public void setUp() {
        helper.setUp();
        this.session = ObjectifyService.begin();
        ObjectifyService.register(User.class);

        uut = new CountTypeDaoImpl();

    }

    @After
    public void tearDown() {
        session.close();

    }


    @Test
    public void shouldSaveCountType() {

        // given
        CountType countType = new CountType();
        countType.setName("aaa");

        // when
        uut.save(countType);


        // then
        assertThat(countType.getId()).isNotNull();

    }


    @Test
    public void shouldGetAll() {




        // given
        CountType countType = new CountType();
        countType.setName("aaa");

        CountType countType2 = new CountType();
        countType2.setName("aaa");

        ObjectifyService.begin();
        ofy().save().entities(countType,countType2).now();

//        uut.save(countType);
//        uut.save(countType2);

        begin();
//        List<CountType> all = ofy().load().type(CountType.class).list();

        // when
        List<CountType> all = uut.getAll();


        // then
        assertThat(all).isNotNull();
        assertThat(all).hasSize(2);

    }


}