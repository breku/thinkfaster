package com.kcal.model.builder;

import com.kcal.model.UserProfile;

/**
 * Created by brekol on 05.11.14.
 */
public class UserProfileBuilder extends AbstractBuilder<UserProfile>{

    public UserProfileBuilder(UserProfile obj) {
        super(obj);
    }

    public UserProfileBuilder() {
    }

    public UserProfileBuilder weight(double weight){
        obj.setWeight(weight);
        return this;
    }
    public UserProfileBuilder height(double height){
        obj.setHeight(height);
        return this;
    }
    public UserProfileBuilder kcalPerDay(double kcalPerDay){
        obj.setKcalPerDay(kcalPerDay);
        calculateProtein();
        calculateCarbohydrate();
        calculateFat();
        return this;
    }
    public UserProfileBuilder proteinPercent(double proteinPercent){
        obj.setProteinPercent(proteinPercent);
        calculateProtein();
        return this;
    }
    public UserProfileBuilder carbohydratePercent(double carbohydratePercent){
        obj.setCarbohydratePercent(carbohydratePercent);
        calculateCarbohydrate();
        return this;
    }
    public UserProfileBuilder fatPercent(double fatPercent){
        obj.setFatPercent(fatPercent);
        calculateFat();
        return this;
    }
    public UserProfileBuilder mealsPerDay(int mealsPerDay){
        obj.setMealsPerDay(mealsPerDay);
        return this;
    }


    private void calculateProtein(){
        double protein = parseDouble(obj.getProteinPercent()* obj.getKcalPerDay() / 100 / UserProfile.ENERGY_FACTOR_PROTEIN);
        obj.setProtein(protein);
    }

    private void calculateCarbohydrate(){
        double carbohydrate = parseDouble(obj.getCarbohydratePercent()* obj.getKcalPerDay() / 100 / UserProfile.ENERGY_FACTOR_CARBOHYDRATE);
        obj.setCarbohydrate(carbohydrate);
    }

    private void calculateFat(){
        double fat = parseDouble(obj.getFatPercent()* obj.getKcalPerDay() / 100 / UserProfile.ENERGY_FACTOR_FAT);
        obj.setFat(fat);
    }



    public UserProfileBuilder defaultValues(){
        weight(70);
        height(180);
        kcalPerDay(2500);
        proteinPercent(25);
        carbohydratePercent(50);
        fatPercent(25);
        mealsPerDay(3);
        return this;
    }


}
