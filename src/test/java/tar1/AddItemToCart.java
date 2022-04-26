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
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class AddItemToCart {
    private WebDriver driver;
    JavascriptExecutor js;
    
    @BeforeMethod
	public void setUp() throws IOException {
    	System.setProperty("webdriver.chrome.driver","chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors","--disable-extensions","--no-sandbox","--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        js = (JavascriptExecutor) driver;
        ExcelReader.readExcel("", "data.xls", "Sheet1");
    }
    @AfterMethod
	public void tearDown() {
        driver.quit();
    }
    
    @Test
    public void AddToCartTest() throws InterruptedException {
    	addItemToCart(driver,2,1,1);
    	Thread.sleep(5000);
        // TODO: add to log - fail or succeed
    }
    
    /*
     * quantity
     * color = 0 - black
     * color = 1 - blue  +1.50$
     * size = 0 - small -1.00$
     * size = 1 - medium
     * size = 2 - large
     */
    public static void addItemToCart(WebDriver driver,int quantity, int color, int size) throws InterruptedException {
    	Login.login(driver,ExcelReader.getsheet().getRow(1).getCell(0).getStringCellValue(),ExcelReader.getsheet().getRow(2).getCell(0).getStringCellValue());
    	driver.get("https://www.ticketor.com/demo/shop/t-shirt-10520");
    	driver.findElement(By.xpath("/html/body/div[1]/div/form/div[3]/div[2]/div[2]/div[2]/div[1]/input")).sendKeys(String.valueOf(quantity));
    	driver.findElement(By.xpath("/html/body/div[1]/div/form/div[3]/div[2]/div[2]/div[2]/div[2]/div[1]/select")).findElement(By.xpath("//option[" + color + "]")).click();
    	driver.findElement(By.xpath("/html/body/div[1]/div/form/div[3]/div[2]/div[2]/div[2]/div[2]/div[2]/select")).findElement(By.xpath("//option[" + color + "]")).click();
    	driver.findElement(By.xpath("/html/body/div[1]/div/form/div[3]/div[2]/div[2]/div[2]/div[4]/button")).click();
        
    	
    }

}
