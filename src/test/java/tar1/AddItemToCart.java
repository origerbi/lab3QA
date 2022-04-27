package tar1;

import java.io.IOException;

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

public class AddItemToCart {
    private WebDriver driver;
    JavascriptExecutor js;
    Logger logger;
    
    @BeforeMethod
	public void setUp() throws IOException {
        System.setProperty("webdriver.chrome.driver","C:\\Program Files\\chromedriver\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors","--disable-extensions","--no-sandbox","--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        js = (JavascriptExecutor) driver;
        ExcelReader.readExcel("", "data.xls", "Sheet1");
        logger = Logger.getLogger(AddItemToCart.class);
    }
    @AfterMethod
	public void tearDown() {
        driver.quit();
    }
    
    @Test
    public void AddToCartTest() throws InterruptedException {
    	addItemToCart(driver, logger);
    }
    
    public static void addItemToCart(WebDriver driver, Logger logger) throws InterruptedException {
    	Login.login(driver,ExcelReader.getsheet().getRow(1).getCell(0).getStringCellValue(),ExcelReader.getsheet().getRow(2).getCell(0).getStringCellValue(),logger);
        driver.get("https://www.ticketor.com/demo/store");
        logger.info("Navigated to store page");
        driver.findElement(By.xpath("/html/body/div[1]/div/form/div[3]/div/div[2]/div[7]/div[2]/div[2]/div/div[2]/div[3]/div/a")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div/form/div[3]/div[2]/div[2]/div[3]/div[3]/div[5]/div[3]/div[2]/a/div/p[1]")).click();
        Thread.sleep(1000);
        WebElement ticketCount = driver.findElement(By.xpath("/html/body/div[1]/div/form/div[3]/div[2]/div[2]/div[4]/form/div[4]/div[2]/table/tbody/tr[2]/td[2]/label/select"));
        ticketCount.findElement(By.xpath("//option[2]")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div/form/div[3]/div[2]/div[2]/div[4]/form/div[4]/div[2]/div/button")).click();
    }

}
