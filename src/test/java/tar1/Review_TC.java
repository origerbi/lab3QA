package tar1;

import java.io.IOException;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pages.myReviewsPage;
import Pages.reviewsPage;

import org.apache.logging.log4j.*;

public class Review_TC {
    private WebDriver driver;
    JavascriptExecutor js;
    Logger logger;
    
    @BeforeMethod
	public void setUp() throws IOException {
    	System.setProperty("webdriver.chrome.driver","chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        logger = LogManager.getLogger();
        //options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors","--disable-extensions","--no-sandbox","--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        js = (JavascriptExecutor) driver;
        ExcelReader.readExcel("", "data.xls", "Sheet1");
    }
    @AfterMethod
	public void tearDown() {
        driver.quit();
    }
    
    @Test
    public void writeReviewTest() throws InterruptedException {
        Login.login(driver,ExcelReader.getsheet().getRow(1).getCell(0).getStringCellValue(),ExcelReader.getsheet().getRow(2).getCell(0).getStringCellValue(), logger);
        int star = 2;
		String text = "The prices are way too high... I found another website much cheaper";
		myReviewsPage myReview = new myReviewsPage(driver);
		myReview.writeReview(star, text, logger);
		reviewsPage review = new reviewsPage(driver);
		String gotText = review.checkReview(text, logger);
        Assert.assertTrue(gotText.contains(text));
        logger.info("Reviews written successfully");
    }
}