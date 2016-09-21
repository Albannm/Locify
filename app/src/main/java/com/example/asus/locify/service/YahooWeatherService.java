package com.example.asus.locify.service;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import com.example.asus.locify.data.Channel;



public class YahooWeatherService {

    private WeatherServiceCallBack callback;
    private String location;
    private Exception error;

    public YahooWeatherService(WeatherServiceCallBack callback) {
        this.callback = callback;
    }

    public String getLocation() {
        return location;
    }


    public void refreshWeather(final String l){
        this.location=l;
        new AsyncTask<String , Void, String >(){
            @Override
            protected String doInBackground(String... params) {

                String YQL = String.format("select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"%s\") and u='c'", l);
                String endpoint = String.format("https://query.yahooapis.com/v1/public/yql?q=%s&format=json" , Uri.encode(YQL));
                try{
                    Log.d("URL", endpoint);
                    URL url = new URL(endpoint);

                    URLConnection connection = url.openConnection();

                    InputStream inputStream= connection.getInputStream();

                    BufferedReader reader= new BufferedReader(new InputStreamReader(inputStream));

                    StringBuilder result= new StringBuilder();
                    String line;
                    while((line=reader.readLine()) != null){
                        result.append(line);

                    }

                    return result.toString();


                }catch (Exception e){
                    error=e;

                }

                return null;
            }

            @Override
            protected void onPostExecute(String s) {

                if(s==null && error !=null){
                    callback.serviceFaliure(error);
                    return;

                }

                try {
                    JSONObject data = new JSONObject(s);

                    JSONObject queryResults = data.optJSONObject("query");

                    int count = queryResults.optInt("count");

                    if(count==0){
                        callback.serviceFaliure(new LocationWeatherException("No weather information found for "+location));

                    }

                    Channel channel=new Channel();
                    JSONObject channelJSON = queryResults.optJSONObject("results").optJSONObject("channel");
                    channel.populate(channelJSON);

                    callback.serviceSuccess(channel);
                } catch (JSONException e) {
                    callback.serviceFaliure(e);
                }

            }
        }.execute(location);

    }

    public class LocationWeatherException extends Exception{
        public LocationWeatherException(String message) {
            super(message);
        }
    }
}
