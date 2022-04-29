package tar1;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pages.CheckOutPage;

import org.apache.logging.log4j.*;
import org.junit.Assert;


public class CheckOut_TC {
    private WebDriver driver;
    JavascriptExecutor js;
	Logger logger;
    
    @BeforeMethod
	public void setUp() {
    	System.setProperty("webdriver.chrome.driver","chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors","--disable-extensions","--no-sandbox","--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        js = (JavascriptExecutor) driver;
        logger = LogManager.getLogger();
    }
    
    @AfterMethod
	public void tearDown() {
        driver.quit();
    }
    
    @Test
    public void CheckOutCart() throws InterruptedException {
    	CheckOutPage checkout = new CheckOutPage(driver);
    	Assert.assertTrue(checkout.CheckOutCart(logger));
    	//TODO: write to log
    }
}
