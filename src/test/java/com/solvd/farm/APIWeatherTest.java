package com.solvd.farm;

import com.qaprosoft.carina.core.foundation.AbstractTest;
import com.qaprosoft.carina.core.foundation.api.http.HttpResponseStatusType;
import com.solvd.farm.testautomation.api.GetWeatherByCityMethod;
import com.solvd.farm.testautomation.api.GetWeatherByZipCodeMethod;
import com.zebrunner.carina.core.registrar.ownership.MethodOwner;
import kong.unirest.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class APIWeatherTest extends AbstractTest {

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
    public void verifyUserCanSearchWeatherByZipCode() {
        String zipCode = "10001";
        GetWeatherByZipCodeMethod getWeatherByZipCodeMethod = new GetWeatherByZipCodeMethod(zipCode);
        getWeatherByZipCodeMethod.expectResponseStatus(HttpResponseStatusType.OK_200);
        String response = getWeatherByZipCodeMethod.callAPI().asString();
        Assert.assertTrue(response.contains(zipCode), "Weather information for " + zipCode + " is not displayed!");
    }

    @Test
    @MethodOwner(owner = "John")
    public void verifyInvalidCityNameReturns404() {
        String invalidCityName = "InvalidCity";
        GetWeatherByCityMethod getWeatherByCityMethod = new GetWeatherByCityMethod(invalidCityName);
        getWeatherByCityMethod.expectResponseStatus(HttpResponseStatusType.NOT_FOUND_404);
        getWeatherByCityMethod.callAPI();
    }

    @Test
    @MethodOwner(owner = "John")
    public void verifyInvalidZipCodeReturns404() {
        String invalidZipCode = "99999";
        GetWeatherByZipCodeMethod getWeatherByZipCodeMethod = new GetWeatherByZipCodeMethod(invalidZipCode);
        getWeatherByZipCodeMethod.expectResponseStatus(HttpResponseStatusType.NOT_FOUND_404);
        getWeatherByZipCodeMethod.callAPI();
    }

    @Test
    @MethodOwner(owner = "John")
    public void verifyWeatherDataStructure() {
        String cityName = "New York";
        GetWeatherByCityMethod getWeatherByCityMethod = new GetWeatherByCityMethod(cityName);
        getWeatherByCityMethod.expectResponseStatus(HttpResponseStatusType.OK_200);
        JSONObject cityResponse = new JSONObject(getWeatherByCityMethod.callAPI().asString());

        Assert.assertTrue(cityResponse.has("main"), "Weather data does not contain 'main' object.");
        Assert.assertTrue(cityResponse.getJSONObject("main").has("temp"), "Weather data does not contain 'temp' field.");
        Assert.assertTrue(cityResponse.getJSONObject("main").has("humidity"), "Weather data does not contain 'humidity' field.");

        Assert.assertTrue(cityResponse.has("wind"), "Weather data does not contain 'wind' object.");
        Assert.assertTrue(cityResponse.getJSONObject("wind").has("speed"), "Weather data does not contain 'speed' field.");
    }

}
