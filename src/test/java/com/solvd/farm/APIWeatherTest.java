package com.solvd.farm;

import com.qaprosoft.carina.core.foundation.AbstractTest;
import com.solvd.farm.testautomation.api.GetWeatherByCityMethod;
import com.solvd.farm.testautomation.api.GetWeatherByZipCodeMethod;
import com.solvd.farm.testautomation.api.WeatherPage;
import com.zebrunner.carina.core.registrar.ownership.MethodOwner;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.qaprosoft.carina.core.foundation.api.http.HttpResponseStatusType;


public class
APIWeatherTest extends AbstractTest {

    @Test
    @MethodOwner(owner = "John")
    public void verifyTemperatureDisplayedInCelsiusByDefault() {
        WeatherPage weatherPage = new WeatherPage(getDriver());
        weatherPage.open();
        weatherPage.waitForPageToLoad();
        Assert.assertEquals(weatherPage.getTemperatureUnit(), "Celsius", "Temperature unit is not displayed in Celsius by default!");
    }

    @Test
    @MethodOwner(owner = "John")
    public void verifyUserCanSwitchTemperatureUnitFromCelsiusToFahrenheit() {
        WeatherPage weatherPage = new WeatherPage(getDriver());
        weatherPage.open();
        weatherPage.waitForPageToLoad();
        weatherPage.switchTemperatureUnit();
        Assert.assertEquals(weatherPage.getTemperatureUnit(), "Fahrenheit", "Temperature unit is not switched to Fahrenheit!");
    }


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
    public void verifyWeatherInfoDisplayedIsAccurateForCityOrZipCodeEntered() {
        WeatherPage weatherPage = new WeatherPage(getDriver());
        weatherPage.open();
        weatherPage.waitForPageToLoad();
        String cityName = "New York";
        weatherPage.searchWeatherByCityName(cityName);
        Assert.assertTrue(weatherPage.isWeatherInfoDisplayed(cityName), "Weather information for " + cityName + " is not displayed!");
        Assert.assertTrue(weatherPage.isTemperatureInRange(cityName), "Temperature is not accurate for " + cityName + "!");
        Assert.assertTrue(weatherPage.isHumidityInRange(cityName), "Humidity is not accurate for " + cityName + "!");
        Assert.assertTrue(weatherPage.isWindSpeedInRange(cityName), "Wind speed is not accurate for " + cityName + "!");
    }
}
