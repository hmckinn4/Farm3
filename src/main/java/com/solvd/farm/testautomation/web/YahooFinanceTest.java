package com.solvd.farm.testautomation.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class YahooFinanceTest {

    public static void main(String[] args) {

        // Set the path to the ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");

        // Create a new instance of the ChromeDriver
        WebDriver driver = new ChromeDriver();

        // Navigate to finance.yahoo.com
        driver.get("https://finance.yahoo.com/");

        // Click on the "Watchlist" tab
        WebElement watchlistTab = driver.findElement(By.xpath("//a[@href='/watchlists']"));
        watchlistTab.click();

        // Click on the "Create a List" button
        WebElement createListButton = driver.findElement(By.xpath("//a[@href='/watchlist/create']"));
        createListButton.click();

        // Enter a name for the list and click on the "Create List" button
        WebElement listNameInput = driver.findElement(By.id("name"));
        listNameInput.sendKeys("My Watchlist");
        WebElement createListForm = driver.findElement(By.xpath("//form[@class='watchlist-create-form']"));
        createListForm.submit();

        // Add a few stocks to the list
        WebElement addSymbolInput = driver.findElement(By.id("add-symbol-input"));
        addSymbolInput.sendKeys("AAPL");
        WebElement addSymbolButton = driver.findElement(By.xpath("//button[@type='submit']"));
        addSymbolButton.click();
        addSymbolInput.sendKeys("MSFT");
        addSymbolButton.click();

        // Verify that the stocks have been successfully added to the list
        WebElement stock1 = driver.findElement(By.xpath("//a[@href='/quote/AAPL']"));
        WebElement stock2 = driver.findElement(By.xpath("//a[@href='/quote/MSFT']"));
        if (stock1.isDisplayed() && stock2.isDisplayed()) {
            System.out.println("Test case passed.");
        } else {
            System.out.println("Test case failed.");
        }

        // Close the browser
        driver.quit();
    }
}
