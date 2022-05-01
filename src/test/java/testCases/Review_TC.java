package testCases;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.LoginPage;
import pages.MyReviewsPage;
import pages.ReviewsPage;

public class Review_TC {
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
    public void writeReviewTest() throws InterruptedException {
    	LoginPage login = PageFactory.initElements(driver,LoginPage.class);
        login.login(ExcelReader.getsheet().getRow(1).getCell(0).getStringCellValue(),ExcelReader.getsheet().getRow(2).getCell(0).getStringCellValue(), logger);
        int star = 2;
		String text = "The prices are way too high... I found another website much cheaper";
		MyReviewsPage myReview = PageFactory.initElements(driver,MyReviewsPage.class);
		myReview.writeReview(star, text, logger);
		ReviewsPage review = new ReviewsPage(driver);
		String gotText = review.checkReview(text, logger);
        Assert.assertTrue(gotText.contains(text));
        logger.info("Reviews written successfully");
    }
}