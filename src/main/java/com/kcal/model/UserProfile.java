package com.kcal.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * User: Breku
 * Date: 2014-10-04
 */
public class UserProfile {

    public static final double ENERGY_FACTOR_PROTEIN = 5.65;

    public static final double ENERGY_FACTOR_CARBOHYDRATE = 4.1;

    public static final double ENERGY_FACTOR_FAT = 9.45;

    private double weight;

    private double height;

    private double kcalPerDay;

    private double proteinPercent;

    private double carbohydratePercent;

    private double fatPercent;

    private int mealsPerDay;

    private double protein;

    private double carbohydrate;

    private double fat;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("weight", weight)
                .append("height", height)
                .append("kcalPerDay", kcalPerDay)
                .append("proteinPercent", proteinPercent)
                .append("carbohydratePercent", carbohydratePercent)
                .append("fatPercent", fatPercent)
                .append("mealsPerDay", mealsPerDay)
                .append("protein", protein)
                .append("carbohydrate", carbohydrate)
                .append("fat", fat)
                .toString();
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

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getKcalPerDay() {
        return kcalPerDay;
    }

    public void setKcalPerDay(double kcalPerDay) {
        this.kcalPerDay = kcalPerDay;
    }

    public double getProteinPercent() {
        return proteinPercent;
    }

    public void setProteinPercent(double proteinPercent) {
        this.proteinPercent = proteinPercent;
    }

    public double getCarbohydratePercent() {
        return carbohydratePercent;
    }

    public void setCarbohydratePercent(double carbohydratePercent) {
        this.carbohydratePercent = carbohydratePercent;
    }

    public double getFatPercent() {
        return fatPercent;
    }

    public void setFatPercent(double fatPercent) {
        this.fatPercent = fatPercent;
    }

    public int getMealsPerDay() {
        return mealsPerDay;
    }

    public void setMealsPerDay(int mealsPerDay) {
        this.mealsPerDay = mealsPerDay;
    }
}
