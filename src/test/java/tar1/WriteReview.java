package tar1;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

public class WriteReview {
    private WebDriver driver;
    JavascriptExecutor js;
    Logger logger;
    
    @BeforeMethod
	public void setUp() {
        System.setProperty("webdriver.chrome.driver","C:\\Program Files\\chromedriver\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        logger = Logger.getLogger(WriteReview.class);
        //options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors","--disable-extensions","--no-sandbox","--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        js = (JavascriptExecutor) driver;
    }
    @AfterMethod
	public void tearDown() {
        driver.quit();
    }
    
    @Test
    public void writeReviewTest() throws InterruptedException {
        Login.login(driver,ExcelReader.getsheet().getRow(1).getCell(0).getStringCellValue(),ExcelReader.getsheet().getRow(2).getCell(0).getStringCellValue(), logger);
        char stars[] = {'2','5','3'};
    	writeReview(driver, stars, logger);
    	Thread.sleep(3000);
    }
    
    
    public static void writeReview(WebDriver driver, char[] stars, Logger logger) throws InterruptedException {
        driver.get("https://www.ticketor.com/demo/myreviews");
        logger.info("Moving to my reviews page");
        for (char star : stars) {
			if (star < '1' || star > '5')
			{
				logger.warn("star out of bounds");	
			}
		}
        driver.findElement(By.xpath("/html/body/div[1]/div/form/div[3]/div/div[2]/div[1]/div/div/div[1]/label[" + stars[0] + "]")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div/form/div[3]/div/div[2]/div[2]/div[1]/div/div/div/label[" + stars[1] + "]")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div/form/div[3]/div/div[2]/div[2]/div[2]/div/div/div/label[" + stars[2] + "]")).click();
        logger.info("Reviews written successfully");
    }
}