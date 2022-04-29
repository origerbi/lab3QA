package testCases;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.apache.logging.log4j.Logger;

import pages.TicketsPage;

public class FilterTickets_TC {
    private WebDriver driver;
    JavascriptExecutor js;
    Logger logger;
    
    @BeforeMethod
	public void setUp() throws IOException {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors","--disable-extensions","--no-sandbox","--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        js = (JavascriptExecutor) driver;
        ExcelReader.readExcel("", "data.xls", "Sheet1");
        logger = LogManager.getLogger();
    }
    @AfterMethod
	public void tearDown() {
        driver.quit();
    }
    
    @Test
    public void filterTickets() throws InterruptedException {
    	TicketsPage filter = new TicketsPage(driver);
    	List<WebElement> events = filter.filterTickets(logger);
        logger.info("Testing the filter");
        for (WebElement webElement : events) {
			if(webElement.isDisplayed())
				Assert.assertTrue(webElement.getText().contains(ExcelReader.getsheet().getRow(1).getCell(2).toString()));
		}
        logger.info("Tickets filtered correctly");
    }

}