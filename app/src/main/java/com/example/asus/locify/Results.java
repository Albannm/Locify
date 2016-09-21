package com.example.asus.locify;

import android.os.Bundle;

/**
 * Created by ASUS on 9/21/2016.
 */

public class Results {



    private int id;
    private String city;
    private String country;
    private int temperature;

    public Results(){}

    public Results(int id){
        this.id=id;
    }

    protected void onCreate(Bundle savedInstanceState) {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

}
