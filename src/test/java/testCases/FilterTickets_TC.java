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
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.apache.logging.log4j.Logger;

import pages.LoginPage;
import pages.TicketsPage;

public class FilterTickets_TC {
    private WebDriver driver;
    JavascriptExecutor js;
    Logger logger = LogManager.getLogger();
    
    @BeforeMethod
	public void setUp() throws IOException {
    	driver = TestUtils.setUp();
    }
    @AfterMethod
	public void tearDown() {
        driver.quit();
    }
    
    @Test
    public void filterTickets() throws InterruptedException {
    	TicketsPage filter = PageFactory.initElements(driver,TicketsPage.class);
    	List<WebElement> events = filter.filterTickets(logger);
        logger.info("Testing the filter");
        for (WebElement webElement : events) {
			if(webElement.isDisplayed())
				Assert.assertTrue(webElement.getText().contains(ExcelReader.getsheet().getRow(1).getCell(2).toString()));
		}
        logger.info("Tickets filtered correctly");
    }

}