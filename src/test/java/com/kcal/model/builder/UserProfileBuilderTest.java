package com.kcal.model.builder;

import com.kcal.model.UserProfile;
import org.fest.assertions.Assertions;
import org.junit.Test;

public class UserProfileBuilderTest {


    @Test
    public void defualtShouldReturnHigherThanZeroValues(){
        UserProfile userProfile = new UserProfileBuilder().defaultValues().build();
        Assertions.assertThat(userProfile.getHeight()).isPositive();
        Assertions.assertThat(userProfile.getKcalPerDay()).isPositive();
        Assertions.assertThat(userProfile.getMealsPerDay()).isPositive();
        Assertions.assertThat(userProfile.getProteinPercent()).isPositive();
        Assertions.assertThat(userProfile.getCarbohydratePercent()).isPositive();
        Assertions.assertThat(userProfile.getFatPercent()).isPositive();
        Assertions.assertThat(userProfile.getWeight()).isPositive();
        Assertions.assertThat(userProfile.getProtein()).isPositive();
        Assertions.assertThat(userProfile.getCarbohydrate()).isPositive();
        Assertions.assertThat(userProfile.getFat()).isPositive();
    }

    @Test
    public void shouldNotCrashOnZero(){
        UserProfile profile = new UserProfileBuilder().proteinPercent(0).build();
        Assertions.assertThat(profile.getProtein()).isZero();
    }
}