package com.horse.model;

import com.horse.model.utils.Sex;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by brekol on 15.01.15.
 */
@Entity
@Table(name = "HORSES")
public class Horse extends RootEntity {

    @Column
    private String name;
    @Column
    private String breed;
    @Column
    private Date birthDate;
    @Column
    private String color;
    @Column
    private Sex sex;
    @Column
    private String owner;
    @JoinColumn(name = "HORSE_ID")
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private HorsePassport horsePassport;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HorsePassport getHorsePassport() {
        return horsePassport;
    }

    public void setHorsePassport(HorsePassport horsePassport) {
        this.horsePassport = horsePassport;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }
}
