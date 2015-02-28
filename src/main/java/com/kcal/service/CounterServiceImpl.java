package com.kcal.service;

import com.kcal.model.Counter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

/**
 * User: Breku
 * Date: 02.07.14
 */
@Service
public class CounterServiceImpl implements CounterService {

    private MongoTemplate template;

    @Autowired
    public CounterServiceImpl(MongoTemplate template) {
        this.template = template;
    }

    @Override
    public long getNextSequence(String collectionName) {
        Query query = Query.query(Criteria.where("_id").is(collectionName));
        Update update = new Update().inc("seq", 1);
        Counter counter = template.findAndModify(query, update,
                FindAndModifyOptions.options().returnNew(true), Counter.class);
        if (counter == null) {
            template.save(new Counter(collectionName, 0));
            counter = template.findAndModify(query, update,
                    FindAndModifyOptions.options().returnNew(true), Counter.class);
        }

        return counter.getSeq();
    }
}
