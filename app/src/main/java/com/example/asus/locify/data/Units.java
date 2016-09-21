package com.example.asus.locify.data;

import org.json.JSONObject;

/**
 * Created by ASUS on 9/21/2016.
 */
public class Units implements JSONPopulator {
    private String temperature;

    public String getTemperature() {
        return temperature;
    }

    @Override
    public void populate(JSONObject data) {
        temperature= data.optString("temperature");

    }
}
