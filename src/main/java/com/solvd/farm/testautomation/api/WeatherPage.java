package com.solvd.farm.testautomation.api;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class WeatherPage {
    private WebDriver driver;
    private String baseURL = "https://your-weather-website.com";
    // Replace with the appropriate selectors for your website
    private By temperatureUnitSelector = By.id("temperature-unit");
    private By switchTemperatureUnitSelector = By.id("switch-temperature-unit");
    private By searchInputSelector = By.id("search-input");
    private By searchButtonSelector = By.id("search-button");
    private By weatherInfoSelector = By.id("weather-info");
    private By temperatureSelector = By.id("temperature");
    private By humiditySelector = By.id("humidity");
    private By windSpeedSelector = By.id("wind-speed");

    public WeatherPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get(baseURL);
    }

    public void waitForPageToLoad() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(temperatureUnitSelector));
    }

    public String getTemperatureUnit() {
        WebElement temperatureUnitElement = driver.findElement(temperatureUnitSelector);
        return temperatureUnitElement.getText();
    }

    public void switchTemperatureUnit() {
        WebElement switchTemperatureUnitElement = driver.findElement(switchTemperatureUnitSelector);
        switchTemperatureUnitElement.click();
    }

    public void searchWeatherByCityName(String cityName) {
        WebElement searchInput = driver.findElement(searchInputSelector);
        searchInput.sendKeys(cityName);
        WebElement searchButton = driver.findElement(searchButtonSelector);
        searchButton.click();
    }

    public boolean isWeatherInfoDisplayed(String location) {
        WebElement weatherInfoElement = driver.findElement(weatherInfoSelector);
        return weatherInfoElement.getText().contains(location);
    }

    public boolean isTemperatureInRange(String cityName) {
        // Replace the logic below with the appropriate way to get the expected temperature range for the city
        double expectedMinTemperature = 10.0;
        double expectedMaxTemperature = 30.0;
        WebElement temperatureElement = driver.findElement(temperatureSelector);
        double currentTemperature = Double.parseDouble(temperatureElement.getText());
        return currentTemperature >= expectedMinTemperature && currentTemperature <= expectedMaxTemperature;
    }

    public boolean isHumidityInRange(String cityName) {
        // Replace the logic below with the appropriate way to get the expected humidity range for the city
        double expectedMinHumidity = 30.0;
        double expectedMaxHumidity = 70.0;
        WebElement humidityElement = driver.findElement(humiditySelector);
        double currentHumidity = Double.parseDouble(humidityElement.getText());
        return currentHumidity >= expectedMinHumidity && currentHumidity <= expectedMaxHumidity;
    }

    public boolean isWindSpeedInRange(String cityName) {
        // Replace the logic below with the appropriate way to get the expected wind speed range for the city
        double expectedMinWindSpeed = 0.0;
        double expectedMaxWindSpeed = 20.0;
        WebElement windSpeedElement = driver.findElement(windSpeedSelector);
        double currentWindSpeed = Double.parseDouble(windSpeedElement.getText());
        return currentWindSpeed >= expectedMinWindSpeed && currentWindSpeed <= expectedMaxWindSpeed;
    }
}
