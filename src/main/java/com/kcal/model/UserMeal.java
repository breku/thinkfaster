package com.kcal.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

/**
 * User: Breku
 * Date: 2014-09-24
 */
@Document(collection = "USER_MEALS")
public class UserMeal extends RootEntity {

    private String name;

    @DBRef
    private User user;

    private Map<Long, Food> foodMap;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Map<Long, Food> getFoodMap() {
        return foodMap;
    }

    public void setFoodMap(Map<Long, Food> foodMap) {
        this.foodMap = foodMap;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("name", name)
                .append("user", user)
                .append("foodMap", foodMap)
                .toString();
    }
}