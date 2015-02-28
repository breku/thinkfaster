package com.kcal.model.json;

import com.kcal.model.UserProfile;
import com.kcal.model.builder.UserProfileBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * User: Breku
 * Date: 2014-10-05
 */
public class UserProfileSliderJson {

    private double proteinPercent;

    private double carbohydratePercent;

    private double fatPercent;

    private double protein;

    private double carbohydrate;

    private double fat;

    public UserProfileSliderJson(){

    }

    public UserProfileSliderJson(double proteinPercent, double carbohydratePercent, double fatPercent) {
        this.proteinPercent = proteinPercent;
        this.carbohydratePercent = carbohydratePercent;
        this.fatPercent = fatPercent;
    }

    public UserProfileSliderJson(UserProfile userProfile) {
        this.proteinPercent = userProfile.getProteinPercent();
        this.carbohydratePercent = userProfile.getCarbohydratePercent();
        this.fatPercent = userProfile.getFatPercent();
        this.protein = userProfile.getProtein();
        this.carbohydrate = userProfile.getCarbohydrate();
        this.fat = userProfile.getFat();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("proteinPercent", proteinPercent)
                .append("carbohydratePercent", carbohydratePercent)
                .append("fatPercent", fatPercent)
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
}
