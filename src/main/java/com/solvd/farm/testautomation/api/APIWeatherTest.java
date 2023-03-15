package com.solvd.farm.testautomation.api;

import com.qaprosoft.carina.core.foundation.AbstractTest;
import com.qaprosoft.carina.core.foundation.api.http.HttpResponseStatusType;
import com.zebrunner.carina.core.registrar.ownership.MethodOwner;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class APIWeatherTest extends AbstractTest {

    // ... Previous test cases ...

    @Test
    @MethodOwner(owner = "John")
    public void verifyUserCanSearchWeatherByCityName() {
        String cityName = "New York";
        GetWeatherByCityMethod getWeatherByCityMethod = new GetWeatherByCityMethod(cityName);
        getWeatherByCityMethod.expectResponseStatus(HttpResponseStatusType.OK_200);
        String response = getWeatherByCityMethod.callAPI().asString();
        Assert.assertTrue(response.contains(cityName), "Weather information for " + cityName + " is not displayed!");
    }

    @Test
    @MethodOwner(owner = "John")
    public void verifyWeatherInfoDisplayedIsAccurateForCityOrZipCodeEntered() {
        String cityName = "New York";
        String zipCode = "10001";

        // Get the weather information by city name
        GetWeatherByCityMethod getWeatherByCityMethod = new GetWeatherByCityMethod(cityName);
        getWeatherByCityMethod.expectResponseStatus(HttpResponseStatusType.OK_200);
        JSONObject cityResponse = new JSONObject(getWeatherByCityMethod.callAPI().asString());

        // Get the weather information by zip code
        GetWeatherByZipCodeMethod getWeatherByZipCodeMethod = new GetWeatherByZipCodeMethod(zipCode);
        getWeatherByZipCodeMethod.expectResponseStatus(HttpResponseStatusType.OK_200);
        JSONObject zipResponse = new JSONObject(getWeatherByZipCodeMethod.callAPI().asString());

        // Define acceptable tolerance for weather data comparison
        double temperatureTolerance = 2.0;
        double humidityTolerance = 5.0;
        double windSpeedTolerance = 1.0;

        // Extract weather data for city name and zip code
        double cityTemperature = cityResponse.getJSONObject("main").getDouble("temp");
        double zipTemperature = zipResponse.getJSONObject("main").getDouble("temp");

        int cityHumidity = cityResponse.getJSONObject("main").getInt("humidity");
        int zipHumidity = zipResponse.getJSONObject("main").getInt("humidity");

        double cityWindSpeed = cityResponse.getJSONObject("wind").getDouble("speed");
        double zipWindSpeed = zipResponse.getJSONObject("wind").getDouble("speed");

        // Compare weather data
        Assert.assertTrue(Math.abs(cityTemperature - zipTemperature) <= temperatureTolerance, "Temperature difference is too large between city and zip code search results.");
        Assert.assertTrue(Math.abs(cityHumidity - zipHumidity) <= humidityTolerance, "Humidity difference is too large between city and zip code search results.");
        Assert.assertTrue(Math.abs(cityWindSpeed - zipWindSpeed) <= windSpeedTolerance, "Wind speed difference is too large between city and zip code search results.");
    }
}

