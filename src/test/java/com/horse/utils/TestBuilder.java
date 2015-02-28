package com.horse.utils;

import com.google.common.collect.Sets;
import com.horse.model.*;
import com.horse.model.utils.Sex;
import com.horse.utils.security.RoleName;

/**
 * Created by brekol on 01.12.14.
 */
public class TestBuilder {

    private static final String EMAIL_SUFFIX = "@gmail.com";


    public static Horse createHorse(String name, Sex sex, HorsePassport horsePassport){
        Horse horse = new Horse();
        horse.setName(name);
        horse.setSex(sex);
        horse.setHorsePassport(horsePassport);
        return horse;

    }

    public static HorsePassport createHorsePassport(String breedingNumber, String feiNumber, String pzjNumber){
        HorsePassport horsePassport = new HorsePassport();
        horsePassport.setBreedingNumber(breedingNumber);
        horsePassport.setFeiNumber(feiNumber);
        horsePassport.setPzjNumber(pzjNumber);
        return horsePassport;
    }


    public static User createDBUser(String username){
        UserRole userRole = new UserRole(RoleName.ROLE_USER.name());
        User user = new User(username,username+EMAIL_SUFFIX,"password",true,true,true,true, Sets.newHashSet(userRole));
        return user;
    }

    public static User createUser(long id, String username){
        UserRole userRole = new UserRole(RoleName.ROLE_USER.name());
        userRole.setId(id + 10);
        User user = new User(username,username+EMAIL_SUFFIX,"password",true,true,true,true, Sets.newHashSet(userRole));
        user.setId(id);
        return user;
    }

    public static Registration createRegistrationObject(String username,String password){
        Registration registration = new Registration();
        registration.setUsername(username);
        registration.setEmail(username+EMAIL_SUFFIX);
        registration.setPassword(password);
        registration.setConfirmPassword(password);
        return registration;
    }
}
