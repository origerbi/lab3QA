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

public class AddToCart {
    private WebDriver driver;
    JavascriptExecutor js;
    Logger logger;
    
    @BeforeMethod
	public void setUp() throws IOException {
        System.setProperty("webdriver.chrome.driver","chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors","--disable-extensions","--no-sandbox","--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        js = (JavascriptExecutor) driver;
        logger = Logger.getLogger(AddToCart.class);
        ExcelReader.readExcel("", "data.xls", "Sheet1");
    }
    @AfterMethod
	public void tearDown() {
        driver.quit();
    }
    
    @Test
    public void AddToCartTest() throws InterruptedException {
    	RunAddToCart(driver, logger);
    }
    
    public static void RunAddToCart(WebDriver driver, Logger logger) throws InterruptedException {
    	Login.login(driver,ExcelReader.getsheet().getRow(1).getCell(0).getStringCellValue(),ExcelReader.getsheet().getRow(2).getCell(0).getStringCellValue(),logger);
        logger.info("Running add to cart");
        driver.findElement(By.xpath("/html/body/div[1]/div/form/div[3]/div/div[2]/div[7]/div[2]/div[2]/div/div[2]/div[3]/div/a")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div/form/div[3]/div[2]/div[2]/div[3]/div[3]/div[5]/div[3]/div[2]/a/div/p[1]")).click();
        Thread.sleep(1000);
        logger.info("Adding tickets to cart...");
        WebElement ticketCount = driver.findElement(By.xpath("/html/body/div[1]/div/form/div[3]/div[2]/div[2]/div[4]/form/div[4]/div[2]/table/tbody/tr[2]/td[2]/label/select"));
        ticketCount.findElement(By.xpath("//option[" + (int)(ExcelReader.getsheet().getRow(1).getCell(1).getNumericCellValue())+ "]")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div/form/div[3]/div[2]/div[2]/div[4]/form/div[4]/div[2]/div/button")).click();
        logger.info("Tickets added to cart");
    }

}
