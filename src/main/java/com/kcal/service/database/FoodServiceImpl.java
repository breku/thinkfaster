package com.kcal.service.database;

import com.kcal.dao.FoodDao;
import com.kcal.model.Food;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User: Breku
 * Date: 2014-09-13
 */
@Service
public class FoodServiceImpl extends AbstractRootService<Food, FoodDao> implements FoodService {

    @Autowired
    public FoodServiceImpl(FoodDao dao) {
        super(dao);
    }

    @Override
    public Food findByName(String name) {
        return dao.get(name);
    }
}
