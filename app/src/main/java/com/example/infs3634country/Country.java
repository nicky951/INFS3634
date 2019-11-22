package com.example.infs3634country;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Country implements Serializable {


    @PrimaryKey
    @NonNull
    public String name;
    public String region;
    public String capital;
    public String population;
    public String flag;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    /*
    public String[] getCurrencies() {
        return currencies;
    }

    public void setCurrencies(String[] currencies) {
        this.currencies = currencies;
    }
    */

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    /*
    public String[] getTimezone() {
        return timezones;
    }

    public void setTimezone(String[] timezones) {
        this.timezones = timezones;
    }
    */

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }



}
