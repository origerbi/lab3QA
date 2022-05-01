package testCases;

import java.io.IOException;
import org.testng.Reporter;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.LoginPage;
import pages.MyReviewsPage;
import pages.ReviewsPage;

public class Review_TC {
    private WebDriver driver;
    
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
        login.login(ExcelReader.getsheet().getRow(1).getCell(0).getStringCellValue(),ExcelReader.getsheet().getRow(2).getCell(0).getStringCellValue());
        int star = 2;
		String text = "The prices are way too high... I found another website much cheaper";
		MyReviewsPage myReview = PageFactory.initElements(driver,MyReviewsPage.class);
		myReview.writeReview(star, text);
		ReviewsPage review = PageFactory.initElements(driver,ReviewsPage.class);
		String gotText = review.checkReview(text);
        Assert.assertTrue(gotText.contains(text));
        Reporter.log("Reviews written successfully");
    }
}