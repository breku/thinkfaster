package com.kcal.service.database;

import com.kcal.model.User;
import com.kcal.model.UserProfile;
import com.kcal.model.json.UserProfileSliderJson;
import com.kcal.model.json.XEditableForm;
import com.kcal.utils.BuilderTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.fest.assertions.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(JUnitParamsRunner.class)
public class UserProfileServiceImplTest {

    UserProfileService uut;


    @Before
    public void init(){
        uut = new UserProfileServiceImpl();
    }


    @Test
    @Parameters({"10,20,70,30,40,10",
            "10,20,70,1,1,98",
            "10,20,70,0,0,100",
            "95,4,1,50,50,0"})
    public void shouldUpdateProfileForGivenJason(double proteinPercent,double carbohydratePercent,double fatPercent, double proteinPercentExpected,double carbohydratePercentExpected,double fatPercentExpected){

        // given
        UserProfile profile = BuilderTest.createProfile(proteinPercent, carbohydratePercent, fatPercent);
        User user = BuilderTest.createUser(1, "kuba");
        UserProfileSliderJson json =BuilderTest.createUserProfileSliderJson(proteinPercentExpected, carbohydratePercentExpected, fatPercentExpected);
        user.setUserProfile(profile);


        // when
        uut.updateProfile(user,json);

        // then
        Assertions.assertThat(profile.getProteinPercent()).isEqualTo(proteinPercentExpected);
        Assertions.assertThat(profile.getCarbohydratePercent()).isEqualTo(carbohydratePercentExpected);
        Assertions.assertThat(profile.getFatPercent()).isEqualTo(fatPercentExpected);
    }


    @Test
    public void shouldUpdateWeight(){
        // given
        UserProfile profile = BuilderTest.createProfile(10, 20, 70);
        User user = BuilderTest.createUser(1, "kuba");
        user.setUserProfile(profile);
        XEditableForm form = BuilderTest.createXEditableForm("weight","33");

        // when
        uut.updateProfile(user,form);

        // then
        Assertions.assertThat(profile.getWeight()).isEqualTo(33);

    }

    @Test
    public void shouldUpdateKcalPerDay(){
        // given
        UserProfile profile = BuilderTest.createProfile(10, 20, 70);
        User user = BuilderTest.createUser(1, "kuba");
        user.setUserProfile(profile);
        XEditableForm form = BuilderTest.createXEditableForm("kcalPerDay","33");

        // when
        uut.updateProfile(user,form);

        // then
        Assertions.assertThat(profile.getKcalPerDay()).isEqualTo(33);

    }

    @Test
    public void shouldUpdateMealsPerDay(){
        // given
        UserProfile profile = BuilderTest.createProfile(10, 20, 70);
        User user = BuilderTest.createUser(1, "kuba");
        user.setUserProfile(profile);
        XEditableForm form = BuilderTest.createXEditableForm("mealsPerDay","33");

        // when
        uut.updateProfile(user,form);

        // then
        Assertions.assertThat(profile.getMealsPerDay()).isEqualTo(33);

    }

    @Test
    public void shouldUpdateHeight(){
        // given
        UserProfile profile = BuilderTest.createProfile(10, 20, 70);
        User user = BuilderTest.createUser(1, "kuba");
        user.setUserProfile(profile);
        XEditableForm form = BuilderTest.createXEditableForm("height","33");

        // when
        uut.updateProfile(user,form);

        // then
        Assertions.assertThat(profile.getHeight()).isEqualTo(33);

    }





}