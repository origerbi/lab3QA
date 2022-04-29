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
import org.apache.logging.log4j.*;

public class WriteReview {
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
    	writeReview(driver, star, text, logger);
    	Thread.sleep(3000);
    }
    
    
    public static void writeReview(WebDriver driver, int star, String text, Logger logger) throws InterruptedException {
        driver.get("https://www.ticketor.com/demo/myreviews");
        logger.info("Moving to my reviews page");
		if (star < 1 || star > 5)
			logger.warn("star out of bounds");
		star = 6-star;
        driver.findElement(By.xpath("/html/body/div[1]/div/form/div[3]/div/div[2]/div[1]/div/div/div[1]/label[" + star + "]")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div/form/div[3]/div/div[2]/div[1]/div/div/div[3]/textarea")).sendKeys(text);
        driver.findElement(By.xpath("/html/body/div[1]/div/form/div[3]/div/div[1]/div")).click(); // click to save review
		Thread.sleep(1500);
        driver.get("https://www.ticketor.com/demo/reviews");
        driver.findElement(By.id("ui-id-3")).click();
        Thread.sleep(1500);
        String gotText = driver.findElement(By.xpath("/html/body/div[1]/div/form/div[3]/div/div[2]/div[2]/div[2]/div[2]/div[1]")).getText();
        Assert.assertTrue(gotText.contains(text));
        logger.info("Reviews written successfully");
    }
}