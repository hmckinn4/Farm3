package com.solvd.farm;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class YahooFinanceTests {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    // ...

    @Test
    public void testFC001_CreateAndManageWatchlist() {
        // Navigate to finance.yahoo.com
        driver.get("https://finance.yahoo.com/");

        // Log in and create a watchlist
        // ...

        // Add a few stocks to the list and verify that they have been added
        // Replace STOCK_SYMBOL_1 and STOCK_SYMBOL_2 with actual stock symbols
        String[] stockSymbols = {"STOCK_SYMBOL_1", "STOCK_SYMBOL_2"};
        for (String stockSymbol : stockSymbols) {
            // Click on the "Add Symbol" button
            // Replace ADD_SYMBOL_SELECTOR with the appropriate CSS selector
            WebElement addSymbolButton = driver.findElement(By.cssSelector("ADD_SYMBOL_SELECTOR"));
            addSymbolButton.click();

            // Search for the desired stock
            // Replace SEARCH_INPUT_SELECTOR with the appropriate CSS selector
            WebElement searchInput = driver.findElement(By.cssSelector("SEARCH_INPUT_SELECTOR"));
            searchInput.sendKeys(stockSymbol);

            // Select the stock from the search results
            // Replace SEARCH_RESULT_SELECTOR with the appropriate CSS selector
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement searchResult = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("SEARCH_RESULT_SELECTOR")));
            searchResult.click();

            // Verify that the stock has been successfully added to the list
            // Replace STOCK_LIST_SELECTOR with the appropriate CSS selector
            WebElement stockList = driver.findElement(By.cssSelector("STOCK_LIST_SELECTOR"));
            Assert.assertTrue(stockList.getText().contains(stockSymbol));
        }
    }

    @Test
    public void testFC002_ViewDetailedStockPerformance() {
        // Navigate to finance.yahoo.com
        driver.get("https://finance.yahoo.com/");

        // Click on the "Market Data" tab
        // Replace MARKET_DATA_TAB_SELECTOR with the appropriate CSS selector
        WebElement marketDataTab = driver.findElement(By.cssSelector("MARKET_DATA_TAB_SELECTOR"));
        marketDataTab.click();

        // Select a stock from the "Top Gainers" or "Top Losers" list
        // Replace STOCK_SELECTOR with the appropriate CSS selector
        WebElement stock = driver.findElement(By.cssSelector("STOCK_SELECTOR"));
        stock.click();

        // Verify that the stock's details are displayed
        // Replace STOCK_DETAILS_SELECTOR with the appropriate CSS selector
        WebElement stockDetails = driver.findElement(By.cssSelector("STOCK_DETAILS_SELECTOR"));
        Assert.assertTrue(stockDetails.isDisplayed());
    }

    @Test
    public void testFC003_CreateAndManagePortfolio() {
        // Navigate to finance.yahoo.com
        driver.get("https://finance.yahoo.com/");

        // Log in and create a portfolio
        // ...

        // Add a few stocks to the portfolio and verify that they have been added
        // Similar to testFC001_CreateAndManageWatchlist, with different selectors
        // ...
    }

    @Test
    public void testFC004_ViewFinancialNewsArticles() {
        // Navigate to finance.yahoo.com
        driver.get("https://finance.yahoo.com/");

        // Click on the "News" tab
        // Replace NEWS_TAB_SELECTOR with the appropriate CSS selector
        WebElement newsTab = driver.findElement(By.cssSelector("NEWS_TAB_SELECTOR"));
        newsTab.click();

        // Select a news article related to a stock or financial topic
        // Replace NEWS_ARTICLE_SELECTOR with the appropriate CSS selector
        WebElement newsArticle = driver.findElement(By.cssSelector("NEWS_ARTICLE_SELECTOR"));
        newsArticle.click();
        // Verify that the article is displayed along with any relevant images or videos
        // Replace ARTICLE_CONTENT_SELECTOR with the appropriate CSS selector
        WebElement articleContent = driver.findElement(By.cssSelector("ARTICLE_CONTENT_SELECTOR"));
        Assert.assertTrue(articleContent.isDisplayed());

        // Replace ARTICLE_MEDIA_SELECTOR with the appropriate CSS selector for images or videos
        WebElement articleMedia = driver.findElement(By.cssSelector("ARTICLE_MEDIA_SELECTOR"));
        Assert.assertTrue(articleMedia.isDisplayed());
    }

    @Test
    public void testFC005_ExportPortfolioAsCSV() {
        // Navigate to finance.yahoo.com
        driver.get("https://finance.yahoo.com/");

        // Log in and go to "My Portfolio" tab
        // ...

        // Select a portfolio from the list
        // Replace PORTFOLIO_SELECTOR with the appropriate CSS selector
        WebElement portfolio = driver.findElement(By.cssSelector("PORTFOLIO_SELECTOR"));
        portfolio.click();

        // Click on the "Export" button
        // Replace EXPORT_BUTTON_SELECTOR with the appropriate CSS selector
        WebElement exportButton = driver.findElement(By.cssSelector("EXPORT_BUTTON_SELECTOR"));
        exportButton.click();

        // Verify that the portfolio is successfully exported as a CSV file
        // This step depends on your browser settings and environment.
        // You may need to check the browser's download folder for the exported CSV file
        // or use a third-party library to handle the file download.
    }
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}



