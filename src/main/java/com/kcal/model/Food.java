package com.kcal.model;

import com.kcal.model.utils.MealNumber;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * User: Breku
 * Date: 2014-09-13
 */
public class Food extends RootEntity {

    private String name;
    private String nameAng;
    private double weight;
    private double protein;
    private double carbohydrate;
    private double fat;
    private double kcal;
    private CountType countType;
    private boolean used;
    private MealNumber mealNumber;

    public MealNumber getMealNumber() {
        return mealNumber;
    }

    public void setMealNumber(MealNumber mealNumber) {
        this.mealNumber = mealNumber;
    }

    public String getNameAng() {
        return nameAng;
    }

    public void setNameAng(String nameAng) {
        this.nameAng = nameAng;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getCarbohydrate() {
        return carbohydrate;
    }

    public void setCarbohydrate(double carbohydrate) {
        this.carbohydrate = carbohydrate;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public double getKcal() {
        return kcal;
    }

    public void setKcal(double kcal) {
        this.kcal = kcal;
    }

    public CountType getCountType() {
        return countType;
    }

    public void setCountType(CountType countType) {
        this.countType = countType;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("name", name)
                .append("nameAng", nameAng)
                .append("weight", weight)
                .append("protein", protein)
                .append("carbohydrate", carbohydrate)
                .append("fat", fat)
                .append("kcal", kcal)
                .append("countType", countType)
                .append("used", used)
                .append("mealNumber", mealNumber)
                .toString();
    }
}
