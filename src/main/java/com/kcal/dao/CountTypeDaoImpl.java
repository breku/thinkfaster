package com.kcal.dao;

import com.googlecode.objectify.ObjectifyService;
import com.kcal.model.CountType;
import com.kcal.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * User: Breku
 * Date: 2014-09-14
 */
@Repository
public class CountTypeDaoImpl extends AbstractRootDao<CountType> implements CountTypeDao {

    static {
        ObjectifyService.register(CountType.class);
    }


    public CountTypeDaoImpl() {
        super(CountType.class);

    }


}
