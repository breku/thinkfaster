package com.horse.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by brekol on 15.01.15.
 */
@Entity
@Table(name = "HORSE_PASSPORTS")
public class HorsePassport extends RootEntity{
    @Column
    private String breedingNumber;
    @Column
    private String pzjNumber;
    @Column
    private String feiNumber;

    public String getBreedingNumber() {
        return breedingNumber;
    }

    public void setBreedingNumber(String breedingNumber) {
        this.breedingNumber = breedingNumber;
    }

    public String getPzjNumber() {
        return pzjNumber;
    }

    public void setPzjNumber(String pzjNumber) {
        this.pzjNumber = pzjNumber;
    }

    public String getFeiNumber() {
        return feiNumber;
    }

    public void setFeiNumber(String feiNumber) {
        this.feiNumber = feiNumber;
    }
}
