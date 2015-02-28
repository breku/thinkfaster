package com.kcal.model;

/**
 * User: Breku
 * Date: 2014-10-05
 */
public class UserMealSum {

    private double kcal;

    private double weight;

    private double protein;

    private double carbohydrate;

    private double fat;

    private double proteinPercent;

    private double fatPercent;

    private double carbohydratePercent;

    private double kcalPercent;

    public double getProteinPercent() {
        return proteinPercent;
    }

    public void setProteinPercent(double proteinPercent) {
        this.proteinPercent = proteinPercent;
    }

    public double getFatPercent() {
        return fatPercent;
    }

    public void setFatPercent(double fatPercent) {
        this.fatPercent = fatPercent;
    }

    public double getCarbohydratePercent() {
        return carbohydratePercent;
    }

    public void setCarbohydratePercent(double carbohydratePercent) {
        this.carbohydratePercent = carbohydratePercent;
    }

    public double getKcalPercent() {
        return kcalPercent;
    }

    public void setKcalPercent(double kcalPercent) {
        this.kcalPercent = kcalPercent;
    }

    public void setKcal(double kcal) {
        this.kcal = kcal;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public void setCarbohydrate(double carbohydrate) {
        this.carbohydrate = carbohydrate;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public double getKcal() {
        return kcal;
    }

    public double getWeight() {
        return weight;
    }

    public double getProtein() {
        return protein;
    }

    public double getCarbohydrate() {
        return carbohydrate;
    }

    public double getFat() {
        return fat;
    }
}
