package com.kcal.service.database;

import com.kcal.dao.CountTypeDao;
import com.kcal.dao.CountTypeDaoImpl;
import com.kcal.model.CountType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User: Breku
 * Date: 2014-09-14
 */
@Service
public class CountTypeServiceImpl extends AbstractRootService<CountType, CountTypeDao> implements CountTypeService {

    @Autowired
    public CountTypeServiceImpl(CountTypeDao dao) {
        super(dao);
    }
}
