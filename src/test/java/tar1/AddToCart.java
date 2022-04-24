package tar1;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddToCart {
    private WebDriver driver;
    private Map<String, Object> vars;
    JavascriptExecutor js;
    
    @BeforeMethod
	public void setUp() {
        System.setProperty("webdriver.chrome.driver","C:\\Program Files\\chromedriver\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors","--disable-extensions","--no-sandbox","--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object>();
    }
    @AfterMethod
	public void tearDown() {
        driver.quit();
    }
    
    @Test
    public void AddToCartTest() throws InterruptedException {
    	AddToCart(driver);
        // TODO: add to log - fail or succeed
    }
    
    public static void AddToCart(WebDriver driver) throws InterruptedException {
    	driver.get("https://www.ticketor.com/demo/tickets");
    	login.login(driver,"origerbi@gmail.com","9012761");
        driver.findElement(By.xpath("/html/body/div[1]/div/form/div[3]/div/div[2]/div[7]/div[2]/div[2]/div/div[2]/div[3]/div/a")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div/form/div[3]/div[2]/div[2]/div[3]/div[3]/div[5]/div[3]/div[2]/a/div/p[1]")).click();
        Thread.sleep(1000);
        WebElement ticketCount = driver.findElement(By.xpath("/html/body/div[1]/div/form/div[3]/div[2]/div[2]/div[4]/form/div[4]/div[2]/table/tbody/tr[2]/td[2]/label/select"));
        ticketCount.findElement(By.xpath("//option[2]")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div/form/div[3]/div[2]/div[2]/div[4]/form/div[4]/div[2]/div/button")).click();
    }

}
