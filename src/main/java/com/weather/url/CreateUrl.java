package com.weather.url;

import com.weather.exceptions.CityNotFoundException;

import java.util.HashMap;

public interface CreateUrl {
    String createUrlByCityName(String city) throws CityNotFoundException;
    String getEndpointForCity(String city) throws CityNotFoundException;
    HashMap<String, String> getCitiesAndEndpoints();
}
