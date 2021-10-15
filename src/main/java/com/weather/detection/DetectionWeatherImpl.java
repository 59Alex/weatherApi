package com.weather.detection;

import com.weather.exceptions.CityNotFoundException;
import com.weather.url.CreateUrl;
import com.weather.url.CreateUrlImpl;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class DetectionWeatherImpl implements DetectionWeather{

    private CreateUrl createUrl;
    private final static Logger LOGGER = Logger.getLogger("Weather detecting service");
    {
        System.out.println("────╔╦╗╔╗────────╔═╗──────────╔╗──────────\n" +
                "╔══╗║║║║╚╗╔═╗╔══╗║╬║╔═╗╔╦╗╔═╗─║╚╗╔╦╗╔╦╗╔═╗\n" +
                "║║║║╠╗║║╔╣║╩╣║║║║║╔╝║╩╣║╔╝║╬╚╗║╔╣║║║║╔╝║╩╣\n" +
                "╚╩╩╝╚═╝╚═╝╚═╝╚╩╩╝╚╝─╚═╝╚╝─╚══╝╚═╝╚═╝╚╝─╚═╝");
    }
    public DetectionWeatherImpl(CreateUrl createUrl) {
        this.createUrl = createUrl;
    }

    @Override
    public String getCurrentWeather(String city) throws CityNotFoundException {
        String url = createUrl.createUrlByCityName(city);
        String endpoint = createUrl.getEndpointForCity(city);
        String temperature = null;

        try {
            LOGGER.log(Level.INFO, "Getting the necessary data from the service for " + city);
            Document document = Jsoup.connect(url).get();
            String attrUrl = String.format("a[href=/%s/now/]", endpoint);
            temperature = document.select(attrUrl)
                    .select(".js_value.tab-weather__value_l").first().text();
        } catch (IOException ex) {
            ex.getStackTrace();
        }
        if(temperature == null) {
            LOGGER.log(Level.WARN, "Failed to contact the service");
        }

        return temperature;
    }

    @Override
    public List<String> getCities() {
        return createUrl.getCitiesAndEndpoints()
                .entrySet().stream()
                .map(s -> s.getKey())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public HashMap<String, String> getAllCurrentWeather() {
        HashMap<String, String> cityAndTemperature = new HashMap<>();
        List<String> cities = getCities();
        cities.forEach( e -> {
            String temp = null;
            try {
                temp = getCurrentWeather(e);
            } catch (CityNotFoundException ignore) {}
            cityAndTemperature.put(e, temp);
        });
        return cityAndTemperature;
    }
}
