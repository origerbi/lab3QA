package tar1;

import java.io.IOException;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WriteReview {
    private WebDriver driver;
    JavascriptExecutor js;
    
    @BeforeMethod
	public void setUp() throws IOException {
        System.setProperty("webdriver.chrome.driver","C:\\Program Files\\chromedriver\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
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
        Login.login(driver,ExcelReader.getsheet().getRow(1).getCell(0).getStringCellValue(),ExcelReader.getsheet().getRow(2).getCell(0).getStringCellValue());
        int star = 2;
        String text = "AMITCHANGETHIS";
    	writeReview(driver, star,text);
    	Thread.sleep(5000);
    }
    
    
    public static void writeReview(WebDriver driver, int star, String text) throws InterruptedException {
        driver.get("https://www.ticketor.com/demo/myreviews");
		Assert.assertFalse(star < 1 || star > 5);	// not valid. fail the test.
		star =6-star;
        driver.findElement(By.xpath("/html/body/div[1]/div/form/div[3]/div/div[2]/div[1]/div/div/div[1]/label[" + star + "]")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div/form/div[3]/div/div[2]/div[1]/div/div/div[3]/textarea")).sendKeys(text);
        driver.findElement(By.xpath("/html/body/div[1]/div/form/div[3]/div/div[1]/div")).click(); // click to save review
        Thread.sleep(1500);
        driver.get("https://www.ticketor.com/demo/reviews");
        driver.findElement(By.id("ui-id-3")).click();
        Thread.sleep(1500);
        String gotText = driver.findElement(By.xpath("/html/body/div[1]/div/form/div[3]/div/div[2]/div[2]/div[2]/div[2]/div[1]")).getText();
        Assert.assertTrue(gotText.contains(text));
    }
}