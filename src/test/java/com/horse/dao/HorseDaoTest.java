package com.horse.dao;

import com.horse.AbstractSpringTestConfiguration;
import com.horse.model.Horse;
import com.horse.model.HorsePassport;
import com.horse.model.utils.Sex;
import com.horse.utils.TestBuilder;
import org.fest.assertions.Assertions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class HorseDaoTest extends AbstractSpringTestConfiguration{


    @Autowired
    private HorseDao horseDao;


    @Test
    public void shouldReturnHorse(){

        // given
        HorsePassport horsePassport = TestBuilder.createHorsePassport("a1", "b2", "c3");
        Horse horse = TestBuilder.createHorse("kuba", Sex.MALE, horsePassport);

        // when
        horseDao.save(horse);
        List<Horse> horseList = horseDao.findAll();

        // then
        Assertions.assertThat(horseList).hasSize(1);
        Assertions.assertThat(horseList.get(0).getHorsePassport().getBreedingNumber()).isEqualTo("a1");
        Assertions.assertThat(horseList.get(0).getSex()).isEqualTo(Sex.MALE);



    }

}