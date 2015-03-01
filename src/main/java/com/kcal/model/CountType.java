package com.kcal.model;

import com.googlecode.objectify.annotation.Entity;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * User: Breku
 * Date: 2014-09-14
 */
@Entity
public class CountType extends RootEntity implements Serializable {

//    private static final long serialVersionUID = 1L;

    private String name;

    private String nameAng;

    private double weight;

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getNameAng() {
        return nameAng;
    }

    public void setNameAng(String nameAng) {
        this.nameAng = nameAng;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("name", name)
                .append("nameAng", nameAng)
                .append("weight", weight)
                .toString();
    }
}
