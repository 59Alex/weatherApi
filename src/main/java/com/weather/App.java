package com.weather;
import com.weather.detection.DetectionWeather;
import com.weather.detection.DetectionWeatherImpl;
import com.weather.exceptions.CityNotFoundException;
import com.weather.url.CreateUrlImpl;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.jsoup.*;
import org.jsoup.nodes.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class App {
    private static Logger LOGGER = Logger.getLogger(App.class);

    public static void main(String[] args) {
        BasicConfigurator.configure();
        DetectionWeather detectionWeather = new DetectionWeatherImpl(new CreateUrlImpl());
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the name of the city from the list to get the current temperature, \"ALL\" to get a list of all cities and temperatures, \"ESC\" to exit, to get a list of cities \"LIST\"");
        boolean esc = true;

        while(esc) {
            String input = scanner.next();
            if(input.equals("ESC")) {
                break;
            } else if(input.equals("ALL")) {
                HashMap<String, String> allResult = detectionWeather.getAllCurrentWeather();
                allResult.entrySet().stream()
                        .forEach(e -> System.out.println(e.getKey() + ":  " + e.getValue()));
            } else if(input.equals("LIST")) {
                List<String> listOfCities = detectionWeather.getCities();
                listOfCities.forEach(System.out::println);
            } else {
                try {
                    String currentTemperature = detectionWeather.getCurrentWeather(input);
                    System.out.println(input + ":  " + currentTemperature);
                } catch (CityNotFoundException ex) {
                    System.out.println("Incorrect city name, try again or input LIST");
                }
            }
        }
    }
}
