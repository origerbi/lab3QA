package tar1;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

public class FilterTickets {
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
        logger = Logger.getLogger(FilterTickets.class);
    }
    @AfterMethod
	public void tearDown() {
        driver.quit();
    }
    
    @Test
    public void filterTickets() throws InterruptedException {
        driver.get("https://www.ticketor.com/demo/tickets");
        logger.info("Navigated to tickets page");
        driver.findElement(By.id("search")).sendKeys(ExcelReader.getsheet().getRow(1).getCell(2).getStringCellValue());
        Thread.sleep(500);
        logger.info("Filter applied");
        WebElement allShows = driver.findElement(By.xpath("/html/body/div[1]/div/form/div[3]/div/div[2]/div[7]/div[2]"));
        List<WebElement> tickets = allShows.findElements(By.className("upcomingEvent"));
        logger.info("Testing the filter");
        for (WebElement webElement : tickets) {
			if( webElement.isDisplayed())
				Assert.assertTrue(webElement.getText().contains(ExcelReader.getsheet().getRow(1).getCell(2).toString()));
		}
        logger.info("Tickets filtered correctly");
    }

}