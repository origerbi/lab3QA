package testCases;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.CheckOutPage;
import pages.LoginPage;
import pages.StorePage;
import pages.TicketsPage;


public class CheckOut_TC {
    private WebDriver driver;
    JavascriptExecutor js;
	Logger logger;
    
    @BeforeMethod
	public void setUp() throws IOException {
    	System.setProperty("webdriver.chrome.driver","chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors","--disable-extensions","--no-sandbox","--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        js = (JavascriptExecutor) driver;
        logger = LogManager.getLogger();
        ExcelReader.readExcel("", "data.xls", "Sheet1");
    }
    
    @AfterMethod
	public void tearDown() {
        driver.quit();
    }
    
    @Test
    public void CheckOutCart() throws InterruptedException {
    	LoginPage login = new LoginPage(driver);
    	login.login(ExcelReader.getsheet().getRow(1).getCell(0).getStringCellValue(), ExcelReader.getsheet().getRow(2).getCell(0).getStringCellValue(), logger);
    	TicketsPage addToCart = new TicketsPage(driver);
    	addToCart.RunAddToCart(logger);
    	StorePage addItemToCart = new StorePage(driver);
    	addItemToCart.addItemToCart(2, 1, 1, logger);
    	try {
    		CheckOutPage checkout = new CheckOutPage(driver);
        	Assert.assertTrue(checkout.CheckOutCart(logger));
        	// log -> sucsess
		} catch (Exception e) {
			// log -> failed
		}
    	
    }
}
