package com.weather.url;

import java.util.HashMap;

import com.weather.exceptions.CityNotFoundException;
import org.apache.log4j.*;

public class CreateUrlImpl implements CreateUrl {
    private final static HashMap<String, String> cities = new HashMap<>();
    private final static String URL = "https://www.gismeteo.ru/%s";
    private final static Logger LOGGER = Logger.getLogger("Service initializing links");
    {
        LOGGER.info("Initializing the image of cities and endpoints");
        cities.put("Moscow", "weather-moscow-4368");
        cities.put("Perm", "weather-perm-4476");
        cities.put("Yekaterinburg", "weather-yekaterinburg-4517");
        cities.put("Ufa", "weather-ufa-4588");
        cities.put("Barnaul", "weather-barnaul-4720");
        cities.put("Krasnoyarsk", "weather-krasnoyarsk-4674");
        cities.put("Omsk", "weather-omsk-4578");
        cities.put("Rostov-Na-Donu", "weather-rostov-na-donu-5110");
        cities.put("Tolyatti", "weather-tolyatti-4429");
        cities.put("Belgorod", "weather-belgorod-5039");
        cities.put("Kazan", "weather-kazan-4364");
        cities.put("Samara", "weather-samara-4618");
        cities.put("Tyumen", "weather-tyumen-4501");
        cities.put("Volgograd", "weather-volgograd-5089");
        cities.put("Kaliningrad", "weather-kaliningrad-4225");
        cities.put("Kaliningrad", "weather-kaliningrad-4225");
        cities.put("Nizhny-Novgorod", "weather-nizhny-novgorod-4355");
        cities.put("Penza", "weather-penza-4445");
        cities.put("Sankt-Peterburg", "weather-sankt-peterburg-4079");
        cities.put("Voronezh", "weather-voronezh-5026");
        cities.put("Krasnodar", "weather-krasnodar-5136");
        cities.put("Novosibirsk", "weather-novosibirsk-4690");
        cities.put("Novosibirsk", "weather-novosibirsk-4690");
        cities.put("Saratov", "weather-saratov-5032");
        cities.put("Chelyabinsk", "weather-chelyabinsk-4565");
    }
    public CreateUrlImpl() {

    }
    @Override
    public String createUrlByCityName(String city) throws CityNotFoundException {
        return String.format(URL, getEndpointForCity(city));
    }

    @Override
    public String getEndpointForCity(String city) throws CityNotFoundException {
        String endpoint = cities.get(city);
        if(endpoint == null) {
            LOGGER.log(Level.WARN, "the city is not specified correctly");
            throw new CityNotFoundException();
        }
        return endpoint;
    }

    @Override
    public HashMap<String, String> getCitiesAndEndpoints() {
        return (HashMap<String, String>) cities.clone();
    }


}
