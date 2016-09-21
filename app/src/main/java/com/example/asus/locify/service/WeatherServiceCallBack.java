package com.example.asus.locify.service;

/**
 * Created by ASUS on 9/21/2016.
 */
import com.example.asus.locify.data.Channel;

public interface WeatherServiceCallBack {
    void serviceSuccess(Channel channel);
    void serviceFaliure(Exception exception);
}
