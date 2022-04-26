package tar1;

import java.util.HashMap;
import java.util.Map;

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
    public void writeReviewTest() throws InterruptedException {
    	login.login(driver,"origerbi@gmail.com","o9012761");
    	String stars[] = {"2","5","3"};
    	writeReview(driver, stars);
    	Thread.sleep(5000);
    }
    
    
    public static void writeReview(WebDriver driver, String[] stars) throws InterruptedException {
        driver.get("https://www.ticketor.com/demo/myreviews");
        for (String star : stars) {
			if (Integer.parseInt(star) < 1 || Integer.parseInt(star) > 5)
			{
				AssertJUnit.assertEquals(false, true);		// not valid. fail the test.
			}
		}
        driver.findElement(By.xpath("/html/body/div[1]/div/form/div[3]/div/div[2]/div[1]/div/div/div[1]/label[" + stars[0] + "]")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div/form/div[3]/div/div[2]/div[2]/div[1]/div/div/div/label[" + stars[1] + "]")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div/form/div[3]/div/div[2]/div[2]/div[2]/div/div/div/label[" + stars[2] + "]")).click();
    }
}