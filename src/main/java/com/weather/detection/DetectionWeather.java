package com.weather.detection;

import com.weather.exceptions.CityNotFoundException;

import java.util.HashMap;
import java.util.List;

public interface DetectionWeather {
    String getCurrentWeather(String city) throws CityNotFoundException;
    List<String> getCities();
    HashMap<String, String> getAllCurrentWeather();
}
