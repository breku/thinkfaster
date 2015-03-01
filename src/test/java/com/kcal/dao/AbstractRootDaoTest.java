package com.kcal.dao;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.googlecode.objectify.ObjectifyService;
import com.kcal.model.RootEntity;
import com.kcal.model.User;
import com.kcal.utils.BuilderTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Created by brekol on 01.03.15.
 */
public abstract class AbstractRootDaoTest<T extends AbstractRootDao, E extends RootEntity> {

    protected T uut;

    protected abstract T createDao();

    protected abstract void registerModels();

    protected abstract E createEntity();

    private final LocalServiceTestHelper helper =
            new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig()
                    .setDefaultHighRepJobPolicyUnappliedJobPercentage(100));


    @Before
    public void setUp() {
        registerModels();
        helper.setUp();
        uut = createDao();
    }

    @After
    public void tearDown() {
        helper.tearDown();
    }



    @Test
    public void shouldSaveEntity() {
        // given
        E entity = createEntity();

        // when
        uut.save(entity);

        // then
        assertThat(entity.getId()).isNotNull();

    }


    @Test
    public void shouldRetrieveEntityById() {
        // given
        E entity = createEntity();
        uut.save(entity);

        // when
        RootEntity result = uut.get(entity.getId());

        // then
        assertThat(result).isNotNull();

    }






}
